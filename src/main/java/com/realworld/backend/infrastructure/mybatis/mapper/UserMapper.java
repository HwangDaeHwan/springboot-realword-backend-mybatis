package com.realworld.backend.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.core.user.FollowRelation;
import com.realworld.backend.core.user.User;

@Mapper
public interface UserMapper {
	void insert(@Param("user") User user);

	User findByUsername(@Param("username") String username);

	User findByEmail(@Param("email") String email);

	User findById(@Param("id") String id);

	void update(@Param("user") User user);

	FollowRelation findRelation(@Param("userId") String userId, @Param("targetId") String targetId);

	void saveRelation(@Param("followRelation") FollowRelation followRelation);

	void deleteRelation(@Param("followRelation") FollowRelation followRelation);
}
