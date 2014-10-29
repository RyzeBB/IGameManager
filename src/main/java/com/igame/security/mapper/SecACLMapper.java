package com.igame.security.mapper;

import java.util.List;

import com.igame.security.entity.SecACL;

public interface SecACLMapper {

	public List<SecACL> selectlistByRoleIds(int...roleIds);
	
	public List<SecACL> selectlistByModuId(int moduleId);

	SecACL selectOneById(int id);

	void updateOne(SecACL secACL);

	void insertOne(SecACL secACL);

	SecACL deleteOne(int id);
}
