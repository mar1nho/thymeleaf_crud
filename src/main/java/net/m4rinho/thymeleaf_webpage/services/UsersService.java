package net.m4rinho.thymeleaf_webpage.services;

import net.m4rinho.thymeleaf_webpage.entities.Users;
import net.m4rinho.thymeleaf_webpage.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	public void saveUser(Users user){
		usersRepository.save(user);
	}
	
	public Users getUserByUsername(String username){
		return usersRepository.findByUsername(username);
	}
	
	public Users getUserInSession(String username){
		return usersRepository.findByUsername(username);
	}
	
	public boolean hasCredentials(String username, String password){
		List<Users> users = usersRepository.findAll();
		for (Users user : users) {
			Object[] objects = {"username","password"};
			objects[0] = user.getUsername();
			objects[1] = user.getPassword();
			if (username.equals(objects[0]) && password.equals(objects[1])) {
				System.err.println("Success");
				return true;
			}
		}
		return false;
	}
	
	public int getUsersRegistered() {
		List<Users> users = usersRepository.findAll();
		int count = 0;
		for (Users user : users) {
			count++;
		}
		return count;
	}
}
