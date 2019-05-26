package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootDemoApplication {
//	@Autowired
//	EmployeeService userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

/*	@PostConstruct
	public void setupDbWithData(){
		Department depTech = new Department(null, "Tech");
		Department depHr = new Department(null, "HR");
		Department depHr = new Department(null, "Economix");

//		depRepo.save(
	}*/
}
