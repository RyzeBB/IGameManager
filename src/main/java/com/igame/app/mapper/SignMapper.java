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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.igame.app.entity.SignEntity;

/**
 * 签到
 * 
 * @author
 */
public interface SignMapper {

	@Select("SELECT * from t_sign where deviceId=#{deviceId} and appid = #{appid}")
	public SignEntity getSign(@Param("appid") long appid, @Param("deviceId") String deviceId);

	@Insert("insert into t_sign(appid,deviceId,score,last_sign) values(#{appid},#{deviceId},#{score},#{last_sign})")
	public long insertSign(SignEntity signEntity);

	@Update("update t_sign set score=#{score},last_sign=#{last_sign} where id = #{id} ")
	public long updateSign(SignEntity signEntity);

}
