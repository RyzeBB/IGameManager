package com.igame.security.mapper;

import java.util.List;

import com.igame.security.entity.SecModule;

public interface SecModuleMapper {

	public List<SecModule> selectlist();
	
	public List<SecModule> selectlistModuleByRoleIds(List<Integer> roleIds);
	
	SecModule selectOneById(int id);

	void updateOne(SecModule secModule);

	void insertOne(SecModule secModule);

	SecModule deleteOne(int id);

}
