package com.hk.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class TestController {

	@GetMapping("/main")
	public String mainTest() {
		System.out.println("Main 페이지 이동!!");
		
		return "home/main";
	}
}
