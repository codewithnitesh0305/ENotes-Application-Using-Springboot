package com.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.Entity.User;
import com.springboot.Service.UserServiceImp;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImp service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_Notes";
	}
	
	@GetMapping("/editNotes")
	public String editNotes() {
		return "edit_Notes";
	}
	
	@GetMapping("viewNotes")
	public String viewNotes() {
		return "view_Notes";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		if(user.getName().equals("") || user.getAddress().equals("") || user.getEmail().equals("") || user.getGender().equals("") || user.getPassword().equals("")) {
			session.setAttribute("msg", "Please fill all fields...");
			//System.out.println("Please fill all field");
		}else {
			if(service.existtEmail(user.getEmail())) {
				session.setAttribute("msg", "Email already exist");
				//System.out.println("Email already exist....");
			}else {
				User saveUser = service.saveUser(user);
				if(saveUser != null) {
					session.setAttribute("msg", "Register Successfully...");
					//System.out.println("Register Successfully....");
				}else {
					session.setAttribute("msg", "Somethings wrong in server...");
					//System.out.println("Somethings wrong in server....");
				}
			}
		}
				
		return "redirect:/register";
	}
}
