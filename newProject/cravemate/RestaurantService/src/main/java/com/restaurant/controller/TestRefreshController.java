package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants/messages")
@RefreshScope
public class TestRefreshController {
	@Value("${custom.message}")
	private String message;
	
	public TestRefreshController() {
		System.out.println("in ctor "+getClass());
	}

	@GetMapping
	public String getMessage() {
		return message;
	}
}
