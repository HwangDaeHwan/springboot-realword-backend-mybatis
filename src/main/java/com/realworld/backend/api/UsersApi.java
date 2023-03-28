package com.realworld.backend.api;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.realworld.backend.api.exception.InvalidAuthenticationException;
import com.realworld.backend.application.UserQueryService;
import com.realworld.backend.application.data.UserData;
import com.realworld.backend.application.data.UserWithToken;
import com.realworld.backend.application.user.RegisterParam;
import com.realworld.backend.application.user.UserService;
import com.realworld.backend.core.service.JwtService;
import com.realworld.backend.core.user.User;
import com.realworld.backend.core.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
public class UsersApi {
	private UserRepository userRepository;
	private UserQueryService userQueryService;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	private UserService userService;

	@RequestMapping(path = "/users", method = POST)
	public ResponseEntity createUser(@Valid @RequestBody RegisterParam registerParam) {
		User user = userService.createUser(registerParam);
		UserData userData = userQueryService.findById(user.getId()).get();
		return ResponseEntity.status(201).body(userResponse(new UserWithToken(userData, jwtService.toToken(user))));
	}

	@RequestMapping(path = "/users/login", method = POST)
	public ResponseEntity userLogin(@Valid @RequestBody LoginParam loginParam) {
		Optional<User> optional = userRepository.findByEmail(loginParam.getEmail());
		if (optional.isPresent() && passwordEncoder.matches(loginParam.getPassword(), optional.get().getPassword())) {
			UserData userData = userQueryService.findById(optional.get().getId()).get();
			return ResponseEntity.ok(userResponse(new UserWithToken(userData, jwtService.toToken(optional.get()))));
		} else {
			throw new InvalidAuthenticationException();
		}
	}

	private Map<String, Object> userResponse(UserWithToken userWithToken) {
		return new HashMap<String, Object>() {
			{
				put("user", userWithToken);
			}
		};
	}
}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class LoginParam {
	@NotBlank(message = "can't be empty")
	@Email(message = "should be an email")
	private String email;

	@NotBlank(message = "can't be empty")
	private String password;
}
