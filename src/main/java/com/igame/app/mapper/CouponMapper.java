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
import org.apache.ibatis.annotations.Select;

import com.igame.app.entity.CouponEntity;

/**
 * 店铺优惠卷
 * 
 * @author jdmr
 */
public interface CouponMapper {

	@Select("SELECT id,	appid,name,score,rmb,start_time,end_time,descrip FROM t_app_coupon where appid = #{appid}")
	public List<CouponEntity> listCoupon(long appid);

	@Insert("insert into t_app_coupon(id,appid,name,score,rmb,start_time,end_time,descrip) values(#{id},#{appid},#{name},#{score},#{rmb},#{start_time},#{end_time},#{descrip})")
	public void insertCoupon(CouponEntity couponEntity);

	@Insert("update t_app_coupon set name=#{name},score=#{score},rmb=#{rmb},start_time=#{start_time},end_time=#{end_time},descrip=#{descrip} where id=#{id}")
	public void updateCoupon(CouponEntity couponEntity);

	@Delete("delete from t_app_coupon where id=#{id}")
	public void deleteCoupon(long id);
}
