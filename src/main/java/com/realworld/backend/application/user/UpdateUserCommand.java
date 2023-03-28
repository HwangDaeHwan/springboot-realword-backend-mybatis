package com.realworld.backend.application.user;

import com.realworld.backend.core.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@UpdateUserConstraint
public class UpdateUserCommand {

	private User targetUser;
	private UpdateUserParam param;
}
