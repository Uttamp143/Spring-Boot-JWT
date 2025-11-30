package com.security.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthRequest;
import com.security.dto.AuthResponse;
import com.security.dto.RegisterRequest;
import com.security.entity.User;
import com.security.repository.UserRepository;
import com.security.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
			PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest req) {
		if (userRepository.existsByUsername(req.getUsername())) {
			return ResponseEntity.badRequest().body("Username already taken");
		}

		User user = new User();
		user.setUsername(req.getUsername());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setRoles(Set.of("ROLE_USER"));
		userRepository.save(user);

		return ResponseEntity.ok("User registered");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody AuthRequest req) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

		Map<String, Object> extra = new HashMap<>();
		extra.put("roles", "ROLE_USER");
		String token = jwtUtil.generateToken(req.getUsername(), extra);

		return ResponseEntity.ok(new AuthResponse(token));
	}
}
