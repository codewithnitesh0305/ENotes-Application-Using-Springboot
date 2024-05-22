package com.springboot.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;
import com.springboot.Service.NotesServiceImp;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private NotesServiceImp notesService;
	
	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_Notes";
	}
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model model) {
		Notes notes = notesService.getNotesById(id);
		model.addAttribute("notes", notes);
		return "edit_Notes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes(Model model, Principal principal) {
		User user = getUser(principal, model);
		List<Notes> notes = notesService.getNotesByUser(user);
		model.addAttribute("notesList",notes);
		
		return "view_Notes";
	}
	
	@ModelAttribute
	public User getUser(Principal principal, Model model) {
		String email = principal.getName();
		User user = repository.findByEmail(email);
		model.addAttribute("user", user);
		return user;
		
	}
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
		if(notes.getTitle().equals("") || notes.getDescription().equals("") ) {
			session.setAttribute("msg", "Please fill all fields...");
		}else {
				notes.setDate(LocalDate.now());
				notes.setUser(getUser(principal, model));
				Notes saveNotes = notesService.saveNotes(notes);
				if(saveNotes != null) {
					session.setAttribute("msg", "Notes Save Successfully...");
				}else {
					session.setAttribute("msg", "Somethings wrong in server...");
				}
			}		
		return "redirect:/user/addNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
		if(notes.getTitle().equals("") || notes.getDescription().equals("")) {
			session.setAttribute("msg", "Please fill all fields...");
		}else {
			notes.setDate(LocalDate.now());
			notes.setUser(getUser(principal, model));
			Notes updateNotes = notesService.updateNotes(notes);
			if(updateNotes != null) {
				session.setAttribute("msg", "Notes update successfully...");
			}else {
				session.setAttribute("msg", "Something wrong in server");
			}
		}
		return "redirect:/user/viewNotes";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		boolean f = notesService.deleteNotes(id);
		if(f) {
			session.setAttribute("msg", "Notes delete successfully...");
		}else {
			session.setAttribute("msg", "Something wrong in server");
		}
		return "redirect:/user/viewNotes";
	}
	
}
