package com.igame.security.mapper;

import java.util.List;

import com.igame.security.entity.SecRole;
import com.igame.security.entity.UsersRoles;

public interface SecUserRolesMapper {

	public List<SecRole> selectlistByUserId(int userId);

	SecRole selectOneById(int id);

	void updateOne(UsersRoles usersRoles);

	void insertOne(UsersRoles usersRoles);

	SecRole deleteOne(int id);
}
