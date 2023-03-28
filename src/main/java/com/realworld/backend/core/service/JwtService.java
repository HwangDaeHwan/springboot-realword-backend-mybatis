package com.realworld.backend.core.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realworld.backend.core.user.User;

@Service
public interface JwtService {
	String toToken(User user);

	Optional<String> getSubFromToken(String token);
}
