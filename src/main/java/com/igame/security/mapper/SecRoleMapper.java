package com.igame.security.mapper;

import java.util.List;

import com.igame.security.entity.SecRole;

public interface SecRoleMapper {

	public List<SecRole> selectlist();

	SecRole selectOneById(int id);

	void updateOne(SecRole secRole);

	void insertOne(SecRole secRole);

	SecRole deleteOne(int id);
}
