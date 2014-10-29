package com.igame.security.mapper;

import java.util.List;

import com.igame.security.entity.SecUser;

public interface SecUserMapper {

	public List<SecUser> selectlist();

	SecUser selectOneById(int id);

	SecUser selectOneByUserName(String username);

	void updateOne(SecUser secUser);

	void insertOne(SecUser secUser);

	SecUser deleteOne(int id);
}
