package com.igame.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.igame.app.entity.GoodsEntity;
import com.igame.app.entity.GoodsTypeEntity;
import com.igame.app.entity.MulVal;
import com.igame.app.mapper.GoodsMapper;
import com.igame.app.mapper.GoodsModuleMapper;
import com.igame.app.mapper.GoodsTypeMapper;
import com.igame.app.vo.GoodsResponeVO;
import com.igame.app.vo.GoodsVO;
import com.igame.app.vo.MutiAttr;
import com.igame.commons.util.BusinessException;
import com.igame.redis.RedisTemplate;

@Service
public class GoodsService {
	public static final String GOODS_KEY = "GOODS:";
	public static final String GOODS_KEY_ID = "GOODS:ID:";
	public static final String GOODS_HOT_KEY_ID = "GOODS:HOT:ID:";
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsModuleMapper goodsModuleMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;

	/**
	 * 获取商品分类
	 * 
	 * @param appid
	 * @return
	 */
	public List<GoodsTypeEntity> getGoodsType(long appid) {
		return goodsTypeMapper.listGoodsType(appid);
	}

	public void addType(long appid, String name, String pic_url) {
		goodsTypeMapper.insertGoodsType(appid, name, pic_url);
	}

	public void modifyType(GoodsTypeEntity entity) {
		goodsTypeMapper.updateGoodsType(entity);
	}

	public void deleteType(long appid, List<Long> ids) {
		goodsTypeMapper.deleteType(ids);
	}

	public GoodsResponeVO getGoodsByType(long appid, int type, int pageNum, int pageCount) {
		GoodsResponeVO responeVO = new GoodsResponeVO();
		int start = (pageNum - 1) * pageCount;
		int end = (pageNum) * pageCount;

		List<Long> ids = goodsTypeMapper.listGoodsByType(appid, type, start, end);
		List<GoodsEntity> entities = null;
		if (ids != null && !ids.isEmpty()) {
			// if (ids.size() > pageCount) {
			// ids = ids.subList(0, pageCount);
			// }
			entities = goodsMapper.listByIds(ids);
			for (GoodsEntity goodsEntity : entities) {
				goodsEntity.decode();
			}
			responeVO.setProductList(entities);
			long total = goodsTypeMapper.getSizeByType(appid, type);
			responeVO.setTotal((int) total);
		}

		return responeVO;
	}

	/**
	 * @param 添加商品
	 */
	public void addGoods(GoodsVO goodsVO, long appid) {
		GoodsEntity entity = new GoodsEntity();
		entity.setAppid(appid);
		// entity.setId;// 商品ID
		entity.setType(goodsVO.getType());// 商品类型ID
		// entity.setTypeName(typeName);;// 商品类型名称
		entity.setName(goodsVO.getName());// 商品名称
		entity.setIntroduce(goodsVO.getIntroduce());// 商品简介
		entity.setPrice(goodsVO.getPrice());// 商品原始价格
		entity.setOffer(goodsVO.getOffer());// 商品折扣(单位%，例如20，表示折扣20%)
		entity.setUnit(goodsVO.getUnit());// 价格单位
		// entity.setMulVa1Json;// 多属性
		entity.setIcon(goodsVO.getIcon());// 商品icon(根据排版风格1，风格2， 该Icon的 尺寸要求不一样)
		// entity.setTitlePicJson;// /商品主图片下载URL(可以有12张图片)
		// entity.setDetailePicJson;// 商品详情图片下载URL
		// entity.setParamsJson;// 商品详细参数介绍
		entity.setAddress(goodsVO.getAddress());// 商品发货地
		entity.setStock(goodsVO.getStock());// 商品库存
		entity.setSaleCount(goodsVO.getSaleCount());// 已出售商品个数
		entity.setShippingType(goodsVO.getShippingType());// 快递方式(如果这个值为null，表示免运费)
		entity.setShippingCost(goodsVO.getShippingCost());// 快递费
		entity.setDisStyle(goodsVO.getDisStyle());// 显示风格
		entity.setServiecType(goodsVO.getServiecType());// 客服方式，1为电话，2为微信号，3为QQ号
		entity.setServiecTel(goodsVO.getServiecTel());// 客服电话/或微信号/或QQ号
		entity.setTitlePic(goodsVO.getTitlePic());
		entity.setDetailePic(goodsVO.getDetailePic());
		entity.setParams(goodsVO.getParams());
		List<MulVal> mulVals = new ArrayList<MulVal>();
		if (goodsVO.getMulVal() != null && goodsVO.getMulVal().size() > 0) {
			for (MutiAttr mutiAttr : goodsVO.getMulVal()) {
				MulVal mulVal = new MulVal();
				String[] values = mutiAttr.getValues();
				Map<String, String> attr_map = new HashMap<String, String>();
				for (int i = 0; i < values.length; i++) {
					String key = goodsVO.getMulName().get(i);
					attr_map.put(key, values[i]);
				}
				mulVal.setValues(attr_map);
				values = mutiAttr.getImg();
				if (values != null && values.length > 0) {
					List<String> images = new ArrayList<String>();
					for (String img : values) {
						images.add(img);
					}
					mulVal.setImg(images);
				}
				mulVals.add(mulVal);
			}
		}
		entity.setMulVal(mulVals);
		entity.encode();
		goodsMapper.create(entity);
		// redisTemplate.hset(GOODS_KEY + appid, String.valueOf(entity.getId()),
		// entity);
		// redisTemplate.zadd(GOODS_KEY_ID + appid, entity.getId());
	}

	/**
	 * 将商品置成人气旺产品或者天天惠产品
	 * 
	 * @param goodsId
	 * @param type
	 *            1表示人气王 2表示天天惠
	 * @param state
	 *            0表示否 1表示是
	 * @param appid
	 * @throws BusinessException
	 */
	public void modifyGoodsState(long goodsId, int type, boolean state, long appid) throws BusinessException {
		GoodsEntity entity = goodsMapper.getGoodsById(goodsId);
		if (entity == null) {
			throw new BusinessException("商品不存在");
		}
		if (type == 1) {
			Map<String, Object> params = new HashMap<String, Object>(2);
			params.put("hot_type", state ? 1 : 0);
			params.put("id", goodsId);
			goodsMapper.updateForHot(params);
		} else if (type == 2) {
			Map<String, Object> params = new HashMap<String, Object>(2);
			params.put("sale_type", state ? 1 : 0);
			params.put("id", goodsId);
			goodsMapper.updateForSale(params);
		} else {
			throw new BusinessException("参数错误");
		}
	}

	public void modifyGoods(GoodsVO goodsVO, long appid) {
		GoodsEntity entity = goodsMapper.getGoodsById(goodsVO.getId());
		entity.setAppid(appid);
		// entity.setId;// 商品ID
		entity.setType(goodsVO.getType());// 商品类型ID
		// entity.setTypeName(typeName);;// 商品类型名称
		entity.setName(goodsVO.getName());// 商品名称
		entity.setIntroduce(goodsVO.getIntroduce());// 商品简介
		entity.setPrice(goodsVO.getPrice());// 商品原始价格
		entity.setOffer(goodsVO.getOffer());// 商品折扣(单位%，例如20，表示折扣20%)
		entity.setUnit(goodsVO.getUnit());// 价格单位
		entity.setIcon(goodsVO.getTitlePic().get(0));// 商品icon(根据排版风格1，风格2，
														// 该Icon的 尺寸要求不一样)
		entity.setAddress(goodsVO.getAddress());// 商品发货地
		entity.setStock(goodsVO.getStock());// 商品库存
		// entity.setSaleCount(goodsVO.getSaleCount());// 已出售商品个数
		entity.setShippingType(goodsVO.getShippingType());// 快递方式(如果这个值为null，表示免运费)
		entity.setShippingCost(goodsVO.getShippingCost());// 快递费
		entity.setDisStyle(goodsVO.getDisStyle());// 显示风格
		entity.setServiecType(goodsVO.getServiecType());// 客服方式，1为电话，2为微信号，3为QQ号
		entity.setServiecTel(goodsVO.getServiecTel());// 客服电话/或微信号/或QQ号
		entity.setTitlePic(goodsVO.getTitlePic());
		entity.setDetailePic(goodsVO.getDetailePic());
		entity.setParams(goodsVO.getParams());
		List<MulVal> mulVals = new ArrayList<MulVal>();
		if (goodsVO.getMulVal() != null && goodsVO.getMulVal().size() > 0) {
			for (MutiAttr mutiAttr : goodsVO.getMulVal()) {
				MulVal mulVal = new MulVal();
				String[] values = mutiAttr.getValues();
				Map<String, String> attr_map = new HashMap<String, String>();
				for (int i = 0; i < values.length; i++) {
					String key = goodsVO.getMulName().get(i);
					attr_map.put(key, values[i]);
				}
				mulVal.setValues(attr_map);
				values = mutiAttr.getImg();
				if (values != null && values.length > 0) {
					List<String> images = new ArrayList<String>();
					for (String img : values) {
						images.add(img);
					}
					mulVal.setImg(images);
				}
				mulVals.add(mulVal);
			}
		}
		entity.setMulVal(mulVals);

		entity.encode();
		goodsMapper.update(entity);
		// redisTemplate.hset(GOODS_KEY + appid, String.valueOf(entity.getId()),
		// entity);
		// redisTemplate.zadd(GOODS_KEY_ID + appid, entity.getId());
	}

	public static void main(String[] args) {
		List<MulVal> mulVals = new ArrayList<MulVal>();
		MulVal mulVal = new MulVal();
		Map<String, String> map = new HashMap<String, String>();
		map.put("名称", "红色");
		map.put("价格", "10");
		map.put("数量", "20");
		map.put("折扣", "100");
		mulVal.setValues(map);
		List<String> list = new ArrayList<String>();
		list.add("thumbnail/54");
		list.add("thumbnail/54");
		list.add("thumbnail/54");
		list.add("thumbnail/54");
		mulVal.setImg(list);
		mulVals.add(mulVal);
		String json = JSON.toJSONString(mulVals);
		System.out.println(json);

		List<MulVal> mulVals2 = JSON.parseArray(json, MulVal.class);
		System.out.println(JSON.toJSONString(mulVals2));
	}

	public void init(long appid) {
		// 初始化商品列表

		// 人气王列表
		List<Long> goodsIds = goodsModuleMapper.listHotGoods(appid);
		if (goodsIds != null && !goodsIds.isEmpty()) {
			redisTemplate.zaddLongList(GOODS_HOT_KEY_ID + appid, goodsIds);
		}
	}

	/**
	 * 获取人气王商品
	 * 
	 * @param appid
	 * @return
	 */
	public GoodsResponeVO getGoodsForHot(long appid) {
		GoodsResponeVO hotResponeVO = new GoodsResponeVO();
		// Set<Long> goodsId = redisTemplate.zrangeLong(GOODS_KEY_ID + appid, 0,
		// -1);
		// List<Long> goodsId = goodsModuleMapper.listHotGoods(appid);
		// if (goodsId != null && !goodsId.isEmpty()) {
		// List<GoodsEntity> list = goodsMapper.listByIds(goodsId);
		List<GoodsEntity> list = goodsMapper.getGoodsByPageByHot(appid);
		if (list != null && !list.isEmpty()) {
			for (GoodsEntity goodsEntity : list) {
				goodsEntity.decode();
			}
			long size = goodsMapper.listSize(appid);
			hotResponeVO.setProductList(list);
			hotResponeVO.setTotal((int) size);
		}
		// }
		return hotResponeVO;
	}

	/**
	 * 根据id获取商品
	 * 
	 * @param appid
	 * @param ids
	 * @return
	 */
	public GoodsResponeVO getGoodsByids(long appid, List<Long> ids) {
		GoodsResponeVO hotResponeVO = new GoodsResponeVO();
		if (ids == null || ids.isEmpty()) {
			return hotResponeVO;
		}
		List<GoodsEntity> list = goodsMapper.listByIds(ids);
		if (list != null && !list.isEmpty()) {
			for (GoodsEntity goodsEntity : list) {
				goodsEntity.decode();
			}
			hotResponeVO.setProductList(list);
			hotResponeVO.setTotal(list.size());
		}
		return hotResponeVO;
	}

	/**
	 * 分页查询所有商品
	 * 
	 * @param appid
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<GoodsEntity> getGoodsByPage(long appid, int pageNum, int pageCount) {
		int start = (pageNum - 1) * pageCount;
		int end = pageNum * pageCount;
		List<GoodsEntity> list = goodsModuleMapper.listSaleGoodsByPage(appid, start, end);
		if (list != null && !list.isEmpty()) {
			for (GoodsEntity goodsEntity : list) {
				goodsEntity.decode();
				// System.out.println(goodsEntity.getHot_type()+"=========== "+goodsEntity.getSale_type());
			}
			return list;
		}
		return null;
	}

	public void delete(long appid, List<Long> ids) {
		goodsMapper.delByIds(ids);
	}

	public long listSize(long appid) {
		return goodsMapper.listSize(appid);
	}

	/**
	 * 查询天天惠商品
	 * 
	 * @param appid
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public GoodsResponeVO getGoodsForSale(long appid, int pageNum, int pageCount) {
		// TODO 获取天天惠商品列表
		int start = (pageNum - 1) * pageCount;
		int end = pageNum * pageCount;
		GoodsResponeVO hotResponeVO = new GoodsResponeVO();
//		List<Long> goodsId = goodsModuleMapper.listSaleGoods(appid, start, end);
//		if (goodsId != null && !goodsId.isEmpty()) {
//			if (goodsId.size() > pageCount) {
//				goodsId = goodsId.subList(0, pageCount);
//			}
//			List<GoodsEntity> list = goodsMapper.listByIds(goodsId);
			List<GoodsEntity> list = goodsMapper.getGoodsByPageBySale(appid, start, end);
			if(list!=null && !list.isEmpty()){
				for (GoodsEntity goodsEntity : list) {
					goodsEntity.decode();
				}
				long size = goodsModuleMapper.getSize(appid);
				hotResponeVO.setProductList(list);
				hotResponeVO.setTotal((int) size);
			}
//		}
		return hotResponeVO;
	}

	public GoodsVO getGoodsById(long id) {
		GoodsVO goodsVO = null;
		GoodsEntity goodsEntity = goodsMapper.getGoodsById(id);
		if (goodsEntity != null) {
			goodsEntity.decode();
			goodsVO = new GoodsVO();
			// ============ 转换为goodsVO
			// entity.setId;// 商品ID
			goodsVO.setId(id);
			goodsVO.setType(goodsEntity.getType());// 商品类型ID
			// entity.setTypeName(typeName);;// 商品类型名称
			goodsVO.setName(goodsEntity.getName());// 商品名称
			goodsVO.setIntroduce(goodsEntity.getIntroduce());// 商品简介
			goodsVO.setPrice(goodsEntity.getPrice());// 商品原始价格
			goodsVO.setOffer(goodsEntity.getOffer());// 商品折扣(单位%，例如20，表示折扣20%)
			goodsVO.setUnit(goodsEntity.getUnit());// 价格单位
			// entity.setMulVa1Json;// 多属性
			goodsVO.setIcon(goodsEntity.getIcon());// 商品icon(根据排版风格1，风格2， 该Icon的
			// 尺寸要求不一样)
			goodsVO.setHot_type(goodsEntity.getHot_type());
			goodsVO.setSale_type(goodsEntity.getSale_type());

			// entity.setTitlePicJson;// /商品主图片下载URL(可以有12张图片)
			// entity.setDetailePicJson;// 商品详情图片下载URL
			// entity.setParamsJson;// 商品详细参数介绍
			goodsVO.setAddress(goodsEntity.getAddress());// 商品发货地
			goodsVO.setStock(goodsEntity.getStock());// 商品库存
			goodsVO.setSaleCount(goodsEntity.getSaleCount());// 已出售商品个数
			goodsVO.setShippingType(goodsEntity.getShippingType());// 快递方式(如果这个值为null，表示免运费)
			goodsVO.setShippingCost(goodsEntity.getShippingCost());// 快递费
			goodsVO.setDisStyle(goodsEntity.getDisStyle());// 显示风格
			goodsVO.setServiecType(goodsEntity.getServiecType());// 客服方式，1为电话，2为微信号，3为QQ号
			goodsVO.setServiecTel(goodsEntity.getServiecTel());// 客服电话/或微信号/或QQ号
			goodsVO.setTitlePic(goodsEntity.getTitlePic());
			goodsVO.setDetailePic(goodsEntity.getDetailePic());
			goodsVO.setParams(goodsEntity.getParams());

			if (goodsEntity.getMulVal() != null && goodsEntity.getMulVal().size() > 0) {
				Map<String, String> map = goodsEntity.getMulVal().get(0).getValues();
				Set<String> keys = map.keySet();
				List<String> mulName = new ArrayList<String>();
				mulName.add("sku");
				mulName.add("price");
				mulName.add("num");
				mulName.add("offer");
				for (String key : keys) {
					if (!mulName.contains(key)) {
						mulName.add(key);
					}
				}
				goodsVO.setMulName(mulName);
				List<MutiAttr> attrs = new ArrayList<MutiAttr>(goodsEntity.getMulVal().size());
				for (MulVal muval : goodsEntity.getMulVal()) {
					MutiAttr mutiAttr = new MutiAttr();
					String[] values = new String[mulName.size()];
					for (int i = 0; i < mulName.size(); i++) {
						values[i] = muval.getValues().get(mulName.get(i));
					}
					mutiAttr.setValues(values);
					mutiAttr.setImg(muval.getImg().toArray(new String[0]));
					attrs.add(mutiAttr);
				}
				goodsVO.setMulVal(attrs);
			}
		}

		return goodsVO;
	}
}
