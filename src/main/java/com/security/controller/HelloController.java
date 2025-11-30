package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

	@GetMapping("/public")
	public String publicEndpoint() {
		return "Hello - public";
	}

	@GetMapping("/private")
	public String privateEndpoint() {
		return "Hello - private (authenticated)";
	}
}