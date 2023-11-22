package net.m4rinho.thymeleaf_webpage.controllers;

import jakarta.servlet.http.HttpSession;
import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.services.TasksService;
import net.m4rinho.thymeleaf_webpage.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TasksController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private TasksService tasksService;
	
	@RequestMapping(value = "/registerTask", method = RequestMethod.POST)
	public String registerTask(@ModelAttribute(name = "registerTask") Tasks registerTask, Model model, HttpSession session) {
		String userInSession = (String) session.getAttribute("username");
		Users users = usersService.getUserByUsername(userInSession);
		registerTask.setUsers(users);
		registerTask.setStatus("IN PROGRESS");
		tasksService.saveTask(registerTask);
		return "redirect:/home";
	}
	
	@GetMapping("/deleteTask/{id}")
	public String delete(@PathVariable("id") Long id) {
		tasksService.deleteById(id);
		return "redirect:/home";
	}
	
	@GetMapping("/finishTask/{id}")
	public String edit(@PathVariable("id") Long id) {
		Optional<Tasks> taskFind = tasksService.getTaskById(id);
		if (taskFind.isPresent()) {
			Tasks tasks = taskFind.get();
			tasks.setStatus("FINISHED!");
			tasksService.saveTask(tasks);
			ResponseEntity.ok().body(HttpStatus.ACCEPTED);
		}
		
		return "redirect:/home";
	}
}
