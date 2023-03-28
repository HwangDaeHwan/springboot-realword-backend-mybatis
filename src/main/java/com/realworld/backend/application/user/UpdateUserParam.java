package com.realworld.backend.application.user;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonRootName("user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserParam {

	@Builder.Default
	@Email(message = "should be an email")
	private String email = "";

	@Builder.Default
	private String password = "";
	@Builder.Default
	private String username = "";
	@Builder.Default
	private String bio = "";
	@Builder.Default
	private String image = "";
}
