package net.m4rinho.thymeleaf_webpage.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	private String displayname;
	private String password;
	private String language;
	private String keyWord;
	
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Tasks> spendsList;
	
	public Users() {
	
	}
	
	public Users(String username, String password, String displayname, String language, String keyWord, List<Tasks> spendsList) {
		this.username = username;
		this.displayname = displayname;
		this.password = password;
		this.language = language;
		this.keyWord = keyWord;
		this.spendsList = spendsList;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Users users = (Users) o;
		return Objects.equals(id, users.id) && Objects.equals(username, users.username) && Objects.equals(password, users.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, username, password);
	}
}