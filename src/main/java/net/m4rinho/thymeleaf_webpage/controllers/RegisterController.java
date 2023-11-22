package net.m4rinho.thymeleaf_webpage.controllers;

import jakarta.servlet.http.HttpSession;
import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RegisterController {
	
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getLoginForm(HttpSession session, Model model) {
		model.addAttribute("registerForm", new Users());
		return "register";
	}
	
	@RequestMapping(value = "/logoutRegister")
	public String logout(HttpSession session) {
		return "redirect:/login";
	}
}
