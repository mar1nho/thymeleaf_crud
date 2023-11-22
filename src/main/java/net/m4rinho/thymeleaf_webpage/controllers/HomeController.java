package net.m4rinho.thymeleaf_webpage.controllers;

import jakarta.servlet.http.HttpSession;
import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.services.TasksService;
import net.m4rinho.thymeleaf_webpage.services.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class HomeController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private TasksService tasksService;
	
	@GetMapping("/home")
	public String homePage(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		int usersRegistered = usersService.getUsersRegistered();
		String reg = String.format("Users registered: " + usersRegistered);
		Users loggedUser = usersService.getUserInSession(username);
		List<Tasks> tasksList = new ArrayList<>();
		
		if (Objects.isNull(loggedUser)) return "redirect:/login";
		else {
			Optional<List<Tasks>> data = tasksService.getAllTasksById(loggedUser.getId());
			if (data.isPresent()) {
				tasksList = data.get();
			}
		}
		if (username.equals("marinho")) model.addAttribute("registereds", reg);
		model.addAttribute("task", new Tasks());
		model.addAttribute("tasks", tasksList);
		model.addAttribute("loggedUser", loggedUser);
		return "home";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.setAttribute("username", null);
		return "redirect:/login";
	}
	
}
