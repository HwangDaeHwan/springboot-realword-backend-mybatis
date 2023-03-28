package com.realworld.backend.application.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.realworld.backend.core.user.UserRepository;

class DuplicatedUsernameValidator implements ConstraintValidator<DuplicatedUsernameConstraint, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (value == null || value.isEmpty()) || !userRepository.findByUsername(value).isPresent();
	}
}
