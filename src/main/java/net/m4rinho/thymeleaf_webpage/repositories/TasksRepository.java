package net.m4rinho.thymeleaf_webpage.repositories;

import net.m4rinho.thymeleaf_webpage.entities.Tasks;
import net.m4rinho.thymeleaf_webpage.entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	@Query("SELECT t FROM Tasks t WHERE t.users.id = :id ORDER BY t.status DESC")
	Optional<List<Tasks>> findAllByUserId(Long id);
}
