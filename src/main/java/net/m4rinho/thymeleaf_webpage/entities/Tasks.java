package net.m4rinho.thymeleaf_webpage.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Tasks implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	private String status;
	
	@Override
	public String toString() {
		return "Tasks{" +
				"id=" + id +
				", name='" + name + '\'' +
				", users=" + users +
				", status='" + status + '\'' +
				'}';
	}
}
