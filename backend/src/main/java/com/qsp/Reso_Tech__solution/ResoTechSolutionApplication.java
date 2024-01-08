package com.qsp.Reso_Tech__solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.qsp.Reso_Tech__solution.dto")
@EntityScan(basePackages = "com.qsp.Reso_Tech__solution.dto")
@ComponentScan(basePackages = "com.qsp.Reso_Tech__solution") // Add this line with the package of your services
public class ResoTechSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResoTechSolutionApplication.class, args);
	}

}
