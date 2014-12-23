package com.igame.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igame.app.entity.BuyerCouponEntity;
import com.igame.app.entity.GoodsEntity;
import com.igame.app.entity.MulVal;
import com.igame.app.entity.OrderEntity;
import com.igame.app.entity.OrderEntity.BuyItem;
import com.igame.app.mapper.BuyerCouponMapper;
import com.igame.app.mapper.GoodsMapper;
import com.igame.app.mapper.OrderMapper;
import com.igame.app.vo.BuyerResponeVO;
import com.igame.app.vo.GoodsBuyRequestVO;
import com.igame.app.vo.GoodsBuyRequestVO.Item;
import com.igame.app.vo.ResponseVO;
import com.igame.commons.util.BusinessException;

@Service
public class BuyerService {
	private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);
	@Autowired
	private BuyerCouponMapper buyerCouponMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private OrderMapper orderMapper;

	public BuyerResponeVO getInfo(long appid, String deviceId) {
		BuyerResponeVO responeVO = new BuyerResponeVO();
		List<BuyerCouponEntity> entities = buyerCouponMapper.listCoupon(appid, deviceId);
		responeVO.setCoupon(entities);
		return responeVO;
	}

	public BuyerCouponEntity getlistCouponByid(long id) {
		BuyerCouponEntity entities = buyerCouponMapper.listCouponByid(id);
		return entities;
	}

	public ResponseVO addBuyInfo(long appid, String deviceId, List<GoodsBuyRequestVO.Item> items, long cid, String addr, String msg) throws BusinessException {
		if (addr == null) {
			throw new BusinessException("收货地址为空");
		}
		ResponseVO responeVO = new ResponseVO();
		if (items == null || items.isEmpty()) {
			// logger.debug(arg0);
			// responeVO.setErrorInfo("购买商品为空");
			// return responeVO;
			throw new BusinessException("购买商品为空");
		}

		// 校验优惠卷是否有
		BuyerCouponEntity buyerCouponEntity = null;
		int coupon_rmb = 0;
		if (cid != 0) {
			buyerCouponEntity = buyerCouponMapper.listCouponByid(cid);
			if (buyerCouponEntity == null) {
				throw new BusinessException("优惠卷不存在");
			} else {
				coupon_rmb = buyerCouponEntity.getRmb();
			}
		}
		// StringBuffer payItems = new StringBuffer();// 购买商品的信息 id 价格 数量
		// StringBuffer itemsImgs = new StringBuffer();// 图片信息
		// StringBuffer itemBackUp = new StringBuffer();// 商品备份信息

		// List<GoodsEntity> goodsList = new
		// ArrayList<GoodsEntity>(items.size());
		List<BuyItem> buyItems = new ArrayList<OrderEntity.BuyItem>();
		List<GoodsEntity> goodses = new ArrayList<GoodsEntity>();
		int priceAll = 0;
		for (Item item : items) {
			BuyItem buyItem = new BuyItem();
			GoodsEntity goods = goodsMapper.getGoodsById(item.getGid());
			if (goods == null) {
				throw new BusinessException("商品已下架");
			} else {
				goods.decode();

				if (item.getNum() <= 0) {
					throw new BusinessException("商品数量不合法");
				}
				double price = 0;
				int offer = 0;
				int num = 0;
				String img = null;
				if (null != item.getSku()) {
					boolean isSku = false;
					for (MulVal mulVal : goods.getMulVal()) {
						String temp_sku = mulVal.getValues().get("sku");
						if (item.getSku().equals(temp_sku)) {
							isSku = true;
							offer = Integer.parseInt(mulVal.getValues().get("offer"));
							price = Double.parseDouble(mulVal.getValues().get("price"));
							num = Integer.parseInt(mulVal.getValues().get("num"));
							mulVal.getValues().put("num", String.valueOf(num - 1));
							img = mulVal.getImg().get(0);
							break;
						}
					}
					if (!isSku) {
						throw new BusinessException("商品已下架");
					}
				} else {
					price = goods.getPrice();
					num = goods.getStock();
					offer = goods.getOffer();
					img = goods.getIcon();
				}

				if (num <= 0) {// 数量不足
					throw new BusinessException("商品已下架");
				}
				// id*price*num=sku#id*price*num=sku
				price = price * offer / 100;
				priceAll += price;
				buyItem.setId(item.getGid());
				buyItem.setNum(num);
				buyItem.setPrice(price);
				buyItem.setSku(item.getSku());
				buyItem.setImg(img);
				buyItem.setBackup(goods);
				buyItems.add(buyItem);
				goods.setSaleCount(goods.getSaleCount() + 1);
				goodses.add(goods);
			}
		}
		if (coupon_rmb != 0 && coupon_rmb > priceAll) {
			throw new BusinessException("优惠卷使用不合法");
		}
		for (GoodsEntity goodsEntity : goodses) {
			goodsMapper.update(goodsEntity);
		}

		// 遍历物品生成订单
		OrderEntity entity = new OrderEntity();
		entity.setAppid(appid);
		entity.setDeviceId(deviceId);
		entity.setCoupon(coupon_rmb);
		entity.setMsg(msg);
		entity.setCreate_time(new Date());
		entity.setItems(buyItems);
		entity.setPrice(priceAll - coupon_rmb);
		entity.setAddr(addr);
		entity.encode();
		orderMapper.inserOrder(entity);
		return responeVO;
	}

	/**
	 * 商家获取订单信息
	 * 
	 * @param appid
	 * @return
	 */
	public List<OrderEntity> getOrderByApp(long appid) {
		List<OrderEntity> entities = orderMapper.listOrderByAppid(appid);
		if (entities != null) {
			for (OrderEntity orderEntity : entities) {
				orderEntity.decode();
			}
		}
		return entities;
	}

	/**
	 * 用户以获取订单信息
	 * 
	 * @param appid
	 * @param deviceId
	 * @return
	 */
	public List<OrderEntity> getOrderByCustom(long appid, String deviceId) {
		List<OrderEntity> entities = orderMapper.listOrderByDeviceId(deviceId, appid);
		if (entities != null) {
			for (OrderEntity orderEntity : entities) {
				orderEntity.decode();
			}
		}
		return entities;
	}
}
