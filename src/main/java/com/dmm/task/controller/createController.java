package com.dmm.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class createController {
	@GetMapping("/main/create")
	public String adminPage() {
		return "create";
	}
}
