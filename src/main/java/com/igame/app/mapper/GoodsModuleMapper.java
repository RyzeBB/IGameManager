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

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.igame.app.entity.GoodsEntity;

/**
 * 人气王商品列表
 * 
 * @author jdmr
 */
public interface GoodsModuleMapper {

	@Select("SELECT goods_id from t_hot_goods where appid = #{appid}")
	public List<Long> listHotGoods(long appid);

	@Select("SELECT goods_id from t_sale_goods where appid = #{appid} limit #{start},#{end}")
	public List<Long> listSaleGoods(@Param("appid")long appid,@Param("start")int start,@Param("end")int end);

	@Select("SELECT count(goods_id) from t_sale_goods where appid = #{appid}")
	public Long getSize(long appid);

	@Select("SELECT id,appid,type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel,sale_type,hot_type FROM t_goods where appid = #{appid} limit #{start},#{end}")
	public List<GoodsEntity> listSaleGoodsByPage(@Param("appid") long appid, @Param("start") int start, @Param("end") int end);

}
