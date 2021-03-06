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

import com.igame.app.entity.GoodsTypeEntity;

/**
 * 商品分类列表
 * 
 * @author Allen
 *
 */
public interface GoodsTypeMapper {

//	@Select("SELECT * from t_type where appid = #{appid}")
	public List<GoodsTypeEntity> listGoodsType(long appid);
	
//	@Insert("insert into t_type(appid,name,pic_url) values(#{appid},#{name},#{pic_url})")
	public void insertGoodsType(@Param("appid")long appid,@Param("name")String name,@Param("pic_url")String pic_url);
	
//	@Update("update t_type set appid=#{appid},name=#{name},pic_url=#{pic_url} where id = #{id}")
	public void updateGoodsType(GoodsTypeEntity entity);
	
//	@Select("SELECT goods_id from t_type_goods where type=#{type} and appid = #{appid} limit #{start},#{end}")
	public List<Long> listGoodsByType(@Param("appid")long appid,@Param("type")int type,@Param("start")int start,@Param("end")int end);

//	@Select("SELECT count(goods_id) from t_type_goods where type=#{type} and appid = #{appid}")
	public Long getSizeByType(@Param("appid")long appid,@Param("type")int type);
	
	public Long deleteType(List<Long> ids);

}
