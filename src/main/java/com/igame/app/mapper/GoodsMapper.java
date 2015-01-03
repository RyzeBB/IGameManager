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
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.igame.app.entity.GoodsEntity;

/**
 *
 * @author jdmr
 */
public interface GoodsMapper {

//	@Select("SELECT id,appid,type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel FROM t_goods where appid = #{appid}")
	public List<GoodsEntity> list(long appid);

	public long listSize(long appid);
	
//	@Select("SELECT id,appid,type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel FROM t_goods where id = #{id}")
	public GoodsEntity getGoodsById(long id);
	
//	@Select("SELECT id,appid,type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel FROM t_goods where appid = #{appid} limit #{start},#{end}")
	public List<GoodsEntity> getGoodsByPage(@Param("appid")long appid,@Param("start")int start,@Param("end")int end);
	
	/**
	 * 人气王商品
	 * @param appid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<GoodsEntity> getGoodsByPageByHot(long appid);
	
	/**
	 * 热卖
	 * @param appid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<GoodsEntity> getGoodsByPageBySale(@Param("appid")long appid,@Param("start")int start,@Param("end")int end);

//	@SelectProvider(type = GoodsMapperProvider.class, method = "listByIds")
	public List<GoodsEntity> listByIds(List<Long> ids);

//	@Insert("INSERT INTO t_goods(type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel) VALUES(#{type},#{name},#{introduce},#{price},#{offer},#{unit},#{mulVa1Json},#{icon},#{titlePicJson},#{detailePicJson},#{paramsJson},#{address},#{stock},#{saleCount},#{shippingType},#{shippingCost},#{disStyle},#{serviecType},#{serviecTel})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
	public long create(GoodsEntity goodsEntity);
	
//	@Update("update t_goods set type=#{type},name=#{name},introduce=#{introduce},price=#{price},offer=#{offer},unit=#{unit},mulVa1Json=#{mulVa1Json},icon=#{icon},titlePicJson=#{titlePicJson},detailePicJson=#{detailePicJson},paramsJson=#{paramsJson},address=#{address},stock=#{stock},saleCount=#{saleCount},shippingType=#{shippingType},shippingCost=#{shippingCost},disStyle=#{disStyle},serviecType=#{serviecType},serviecTel=#{serviecTel} where id=#{id}")
	public long update(GoodsEntity goodsEntity);
	
	public long updateForHot(Map<String, Object> params);
	
	public long updateForSale(Map<String, Object> params);

//	@DeleteProvider(type = GoodsMapperProvider2.class, method = "delByIds")
	public int delByIds(List<Long> ids);

	// @Update("UPDATE t_images SET name=#{name},thumbnailFilename = #{thumbnailFilename},newFilename = #{newFilename},contentType = #{contentType},size = #{size},thumbnailSize = #{thumbnailSize},lastUpdated = sysdate where id = #{id}")
	// @Select("select * from t_images where id = #{id}")
	// public Image get(Long id);

	// @Delete("delete from t_images where id = #{id}")
	// public void delete(long id);

}
