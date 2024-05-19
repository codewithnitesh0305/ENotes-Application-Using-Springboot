package com.springboot.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_Notes";
	}
	
	@GetMapping("/editNotes")
	public String editNotes() {
		return "edit_Notes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes() {
		return "view_Notes";
	}
	
	@ModelAttribute
	public void getUser(Principal principal, Model model) {
		String email = principal.getName();
		User user = repository.findByEmail(email);
		model.addAttribute("user", user);
		
	}
	
	
}
