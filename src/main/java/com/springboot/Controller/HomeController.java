package com.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/add")
	public String addNotes() {
		return "add_Notes";
	}
	
	@GetMapping("/edit")
	public String editNotes() {
		return "edit_Notes";
	}
	
	@GetMapping("view")
	public String viewNotes() {
		return "view_Notes";
	}
}
