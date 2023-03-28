package com.realworld.backend.infrastructure.mybatis.readservice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.realworld.backend.application.data.UserData;

@Mapper
public interface UserReadService {

	UserData findByUsername(@Param("username") String username);

	UserData findById(@Param("id") String id);
}
