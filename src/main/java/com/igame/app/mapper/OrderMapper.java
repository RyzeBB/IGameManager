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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.igame.app.entity.OrderEntity;

/**
 * 人气王商品列表
 * 
 * @author jdmr
 */
public interface OrderMapper {

	@Select("SELECT * from t_order where appid = #{appid}")
	public List<OrderEntity> listOrderByAppid(long appid);

	@Select("SELECT * from t_order where deviceId = #{deviceId}")
	public List<OrderEntity> listOrderByDeviceId(String deviceId);

	@Insert("insert into t_order(id,appid,deviceId,gid,billno,ico,name,price,create_time,pay_time,state,back_goods) values(#{id},#{appid},#{deviceId},#{gid},#{billno},#{ico},#{name},#{price},#{create_time},#{pay_time},#{state},#{back_goods})")
	public long inserOrder(OrderEntity orderEntity);

	@Update("update t_order set create_time=#{create_time},pay_time=#{pay_time},state=#{state}) where id = #{id}")
	public long updateOrder(OrderEntity orderEntity);

}
