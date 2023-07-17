package com.myspringproject.microservices;

import com.myspringproject.microservices.entity.User;
import com.myspringproject.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootMicroservicesApplication implements CommandLineRunner {

	private UserService userService;

	@Autowired
	public SpringBootMicroservicesApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Crear usuarios
	/*	User user1 = new User();
		user1.setName("Karla");
		user1.setEmail("karla@example.com");

		User user2 = new User();
		user2.setName("William");
		user2.setEmail("william@example.com");

		User user3 = new User();
		user3.setName("Ivan");
		user3.setEmail("ivan@example.com");

		// Guardar usuarios en la base de datos
		userService.saveUser(user1);
		userService.saveUser(user2);
		userService.saveUser(user3);*/
	}
}

