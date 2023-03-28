package com.realworld.backend.application.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {
	@NotBlank(message = "can't be empty")
	@Email(message = "should be an email")
	@DuplicatedEmailConstraint
	private String email;

	@NotBlank(message = "can't be empty")
	@DuplicatedUsernameConstraint
	private String username;

	@NotBlank(message = "can't be empty")
	private String password;
}
