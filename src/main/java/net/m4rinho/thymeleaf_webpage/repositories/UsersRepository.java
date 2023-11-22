package net.m4rinho.thymeleaf_webpage.repositories;

import net.m4rinho.thymeleaf_webpage.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
