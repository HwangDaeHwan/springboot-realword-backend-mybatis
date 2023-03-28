package com.realworld.backend.application.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.realworld.backend.core.user.User;
import com.realworld.backend.core.user.UserRepository;

@Service
@Validated
public class UserService {
	private UserRepository userRepository;
	private String defaultImage;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, @Value("${image.default}") String defaultImage,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.defaultImage = defaultImage;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUser(@Valid RegisterParam registerParam) {
		User user = new User(registerParam.getEmail(), registerParam.getUsername(),
				passwordEncoder.encode(registerParam.getPassword()), "", defaultImage);
		userRepository.save(user);
		return user;
	}

	public void updateUser(@Valid UpdateUserCommand command) {
		User user = command.getTargetUser();
		UpdateUserParam updateUserParam = command.getParam();
		user.update(updateUserParam.getEmail(), updateUserParam.getUsername(), updateUserParam.getPassword(),
				updateUserParam.getBio(), updateUserParam.getImage());
		userRepository.save(user);
	}
}