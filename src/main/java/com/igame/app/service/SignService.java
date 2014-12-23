package com.igame.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igame.app.entity.CouponEntity;
import com.igame.app.entity.SignEntity;
import com.igame.app.entity.BuyerCouponEntity;
import com.igame.app.mapper.BuyerCouponMapper;
import com.igame.app.mapper.CouponMapper;
import com.igame.app.mapper.SignMapper;
import com.igame.app.vo.ResponseVO;
import com.igame.app.vo.SignInfoResponeVO;
import com.igame.app.vo.SignResponeVO;
import com.igame.commons.util.BusinessException;
import com.igame.commons.util.DateUtil;

@Service
public class SignService {
	@Autowired
	private SignMapper signMapper;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private BuyerCouponMapper buyerCouponMapper;

	/**
	 * 获取签到信息
	 * 
	 * @param appid
	 * @param deviceId
	 * @return
	 */
	public SignInfoResponeVO getSignInfo(long appid, String deviceId) {
		SignInfoResponeVO responeVO = new SignInfoResponeVO();
		SignEntity signEntity = signMapper.getSign(appid, deviceId);
		CouponEntity couponEntity = null;
		List<CouponEntity> entities = couponMapper.listCoupon(appid);
		if (entities != null && !entities.isEmpty()) {
			couponEntity = entities.get(0);
		}
		if (signEntity != null) {
			if (signEntity.getLast_sign().after(DateUtil.getStartTime())) {
				responeVO.setSign(1);
			}
			responeVO.setTotalScore(signEntity.getScore());
			responeVO.setLastDate(signEntity.getLast_sign());
			responeVO.setSignScore(100);

		} else {
//			signEntity = new SignEntity();
//			signEntity.setAppid(appid);
//			signEntity.setDeviceId(deviceId);
//			signEntity.setLast_sign(new Date());
//			signEntity.setScore(0);
//			signMapper.insertSign(signEntity);
		}

		if (null != couponEntity) {
			responeVO.setCouponScore(couponEntity.getScore());
			responeVO.setCouponPrice(couponEntity.getRmb());
		}
		return responeVO;
	}

	public SignResponeVO modifySignInfo(long appid, String deviceId) throws BusinessException {
		SignResponeVO responeVO = new SignResponeVO();
		SignEntity signEntity = signMapper.getSign(appid, deviceId);
		if (signEntity != null) {
			if (signEntity.getLast_sign().after(DateUtil.getStartTime())) {
				throw new BusinessException("今日已签到");
			}
			signEntity.setScore(signEntity.getScore() + 100);
			signEntity.setLast_sign(new Date());
			signMapper.updateSign(signEntity);
		} else {
			signEntity = new SignEntity();
			signEntity.setAppid(appid);
			signEntity.setDeviceId(deviceId);
			signEntity.setLast_sign(new Date());
			signEntity.setScore(100);
			signMapper.insertSign(signEntity);
		}
		responeVO.setTotalScore(signEntity.getScore());
		return responeVO;
	}

	public ResponseVO modifyCouPon(long appid, String deviceId) throws BusinessException {
		SignEntity signEntity = signMapper.getSign(appid, deviceId);

		CouponEntity couponEntity = null;
		List<CouponEntity> entities = couponMapper.listCoupon(appid);
		if (entities != null && !entities.isEmpty()) {
			couponEntity = entities.get(0);
			if (couponEntity.getEnd_time().before(new Date())) {
				throw new BusinessException("无优惠卷可兑换");
			}
		} else {
			throw new BusinessException("无优惠卷可兑换");
		}
		if (signEntity != null) {
			if (signEntity.getScore() < couponEntity.getScore()) {
				throw new BusinessException("积分不够");
			}
			signEntity.setScore(signEntity.getScore() - couponEntity.getScore());
			BuyerCouponEntity entity = new BuyerCouponEntity();
			entity.setAppid(appid);
			entity.setDeviceId(deviceId);
			entity.setCreate_time(new Date());
			entity.setName(couponEntity.getName());
			entity.setScore(couponEntity.getScore());
			entity.setRmb(couponEntity.getRmb());
			buyerCouponMapper.insertCoupon(entity);
			signMapper.updateSign(signEntity);
		} else {
			throw new BusinessException("积分不够");
		}
		return new ResponseVO();
	}
}
