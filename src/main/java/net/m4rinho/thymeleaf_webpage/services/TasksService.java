package net.m4rinho.thymeleaf_webpage.services;

import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	public Optional<List<Tasks>> getAllTasksById(Long id){
		return tasksRepository.findAllByUserId(id);
	}
	
	public Optional<Tasks> getTaskById(Long id){
		return tasksRepository.findById(id);
	}
	
	
	public void deleteById(Long id){
		tasksRepository.deleteById(id);
	}
	
	public void saveTask(Tasks tasks){
		tasksRepository.save(tasks);
	}
}
