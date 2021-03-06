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

import com.igame.app.entity.Image;

/**
 *
 * @author jdmr
 */
public interface ImageMapper {

	@Select("select * from t_images where appid = #{appid} limit #{start},#{end}")
	public List<Image> list(@Param("appid") long appid, @Param("start") int start, @Param("end") int end);

	@Select("select count(*) from t_images where appid = #{appid}")
	public long getSize(long appid);

	@Insert("INSERT INTO t_images(id,appid,name,thumbnailFilename,newFilename,contentType,size,thumbnailSize,dateCreated,lastUpdated) VALUES(#{id},#{appid},#{name},#{thumbnailFilename},#{newFilename},#{contentType},#{size},#{thumbnailSize},SYSDATE(),SYSDATE())")
	// @Options(useGeneratedKeys = true, keyProperty = "id")
	public long create(Image image);

	// @Update("UPDATE t_images SET name=#{name},thumbnailFilename = #{thumbnailFilename},newFilename = #{newFilename},contentType = #{contentType},size = #{size},thumbnailSize = #{thumbnailSize},lastUpdated = sysdate where id = #{id}")
	@Select("select * from t_images where id = #{id}")
	public Image get(Long id);

	@Delete("delete from t_images where id = #{id}")
	public void delete(long id);

}
