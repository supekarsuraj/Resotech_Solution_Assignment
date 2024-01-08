package com.qsp.Reso_Tech__solution.dto;

import java.util.List;
import org.springframework.stereotype.Component;

import com.qsp.Reso_Tech__solution.Service.TaskService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Component
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(unique = true)
//	@Email(regexp = "[a-z 0-9  . _ $ ]+@[a-z]+\\.[a-z]{2,3}",message="Enter Proper Email")
	private String email;
	@Column(unique = true)
	
	private String name;
	
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tasks> tasks;

}
