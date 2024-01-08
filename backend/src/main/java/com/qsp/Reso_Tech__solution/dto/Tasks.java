package com.qsp.Reso_Tech__solution.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@Entity
@Data
public class Tasks {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_id;
	
//	@Max(value = 9999999999l)
	int id;

	private String taskName;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private String createdBy;
	private String modifiedBy;
	
	private String name;
	private String newTask;

	private String status;
	  @ManyToOne 
	    private User user; 
	  


}
