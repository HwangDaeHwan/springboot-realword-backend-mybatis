package com.realworld.backend.application;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.realworld.backend.application.data.ProfileData;
import com.realworld.backend.application.data.UserData;
import com.realworld.backend.core.user.User;
import com.realworld.backend.infrastructure.mybatis.readservice.UserReadService;
import com.realworld.backend.infrastructure.mybatis.readservice.UserRelationshipQueryService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfileQueryService {
	private UserReadService userReadService;
	private UserRelationshipQueryService userRelationshipQueryService;

	public Optional<ProfileData> findByUsername(String username, User currentUser) {
		UserData userData = userReadService.findByUsername(username);
		if (userData == null) {
			return Optional.empty();
		} else {
			ProfileData profileData = new ProfileData(userData.getId(), userData.getUsername(), userData.getBio(),
					userData.getImage(), currentUser != null
							&& userRelationshipQueryService.isUserFollowing(currentUser.getId(), userData.getId()));
			return Optional.of(profileData);
		}
	}
}