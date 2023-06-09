package com.realworld.backend.application.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.realworld.backend.core.user.User;
import com.realworld.backend.core.user.UserRepository;

class UpdateUserValidator implements ConstraintValidator<UpdateUserConstraint, UpdateUserCommand> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(UpdateUserCommand value, ConstraintValidatorContext context) {
		String inputEmail = value.getParam().getEmail();
		String inputUsername = value.getParam().getUsername();
		final User targetUser = value.getTargetUser();

		boolean isEmailValid = userRepository.findByEmail(inputEmail).map(user -> user.equals(targetUser)).orElse(true);
		boolean isUsernameValid = userRepository.findByUsername(inputUsername).map(user -> user.equals(targetUser))
				.orElse(true);
		if (isEmailValid && isUsernameValid) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			if (!isEmailValid) {
				context.buildConstraintViolationWithTemplate("email already exist").addPropertyNode("email")
						.addConstraintViolation();
			}
			if (!isUsernameValid) {
				context.buildConstraintViolationWithTemplate("username already exist").addPropertyNode("username")
						.addConstraintViolation();
			}
			return false;
		}
	}
}