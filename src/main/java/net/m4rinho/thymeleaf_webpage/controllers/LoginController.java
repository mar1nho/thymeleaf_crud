package net.m4rinho.thymeleaf_webpage.controllers;

import jakarta.servlet.http.HttpSession;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	
	@GetMapping("/")
	public String getFrontPage(){
		return "login";
	}
	
	//get login form page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginForm(HttpSession session) {
		if (session.getAttribute("username") != null) {
			return "redirect:/home";
		}
		return "login";
	}
	
	//checking for credentials
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(name = "loginForm") Users loginForm, Model model, HttpSession session) {
		boolean hasCredentials = usersService.hasCredentials(loginForm.getUsername(), loginForm.getPassword());
		if (hasCredentials) {
			model.addAttribute("username", loginForm.getUsername());
			session.setAttribute("username", loginForm.getUsername());
			return "redirect:/home";
		}
		System.err.println("Incorrect credentials, return true to HTML file.");
		model.addAttribute("invalidCredentials", true);
		boolean c = (boolean) model.getAttribute("invalidCredentials");
		return "login";
	}
}
