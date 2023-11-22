package net.m4rinho.thymeleaf_webpage.controllers;

import jakarta.servlet.http.HttpSession;
import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String login(@ModelAttribute(name = "registerForm") Users registerForm, Model model, HttpSession session) {
		String username = registerForm.getUsername();
		Optional<Users> findSameUsername = Optional.ofNullable(usersService.getUserByUsername(username));
		if (findSameUsername.isEmpty()) {
			List<Tasks> tasksList = new ArrayList<>();
			Users users = new Users(username, registerForm.getPassword(), registerForm.getDisplayname(), registerForm.getLanguage(), registerForm.getKeyWord(), tasksList);
			usersService.saveUser(users);
			return "redirect:/login";
		}
		model.addAttribute("usernameInUse", true);
		return "/register";
	}
}
