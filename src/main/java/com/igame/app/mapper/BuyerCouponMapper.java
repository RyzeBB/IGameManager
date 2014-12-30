/*
 * The MIT License
 *
 * Copyright 2013 jdmr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.igame.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.igame.app.entity.BuyerCouponEntity;

/**
 * 买家优惠卷
 * 
 * @author jdmr
 */
public interface BuyerCouponMapper {

	@Select("SELECT	id,appid,deviceId,name,rmb,score,state,create_time,used_time FROM t_user_coupon where appid = #{appid} and deviceId = #{deviceId} and state = 0")
	public List<BuyerCouponEntity> listCoupon(@Param("appid") long appid, @Param("deviceId") String deviceId);
	
	@Select("SELECT	id,appid,deviceId,name,rmb,score,state,create_time,used_time FROM t_user_coupon where id = #{id} and state > 0")
	public BuyerCouponEntity listCouponByid(long id);

	@Insert("insert into t_user_coupon(appid,deviceId,name,rmb,score,state,create_time,used_time) values(#{appid},#{deviceId},#{name},#{rmb},#{score},#{state},#{create_time},#{used_time})")
	public void insertCoupon(BuyerCouponEntity buyerCouponEntity);

	@Update("update t_user_coupon set appid=#{appid},deviceId=#{deviceId},name=#{name},rmb=#{rmb},score=#{score},state=#{state},create_time=#{create_time},used_time=#{used_time} where id=#{id}")
	public void updateCoupon(BuyerCouponEntity couponEntity);

	@Delete("delete from t_user_coupon where id=#{id}")
	public void deleteCoupon(long id);
}
