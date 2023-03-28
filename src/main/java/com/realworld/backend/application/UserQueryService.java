package com.realworld.backend.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realworld.backend.application.data.UserData;
import com.realworld.backend.infrastructure.mybatis.readservice.UserReadService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserQueryService {
	private UserReadService userReadService;

	public Optional<UserData> findById(String id) {
		return Optional.ofNullable(userReadService.findById(id));
	}
}
