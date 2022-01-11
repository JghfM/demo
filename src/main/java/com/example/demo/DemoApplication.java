package com.example.demo;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.ERole;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);
		configurableApplicationContext.getBean(UserRepository.class);
		
		UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
		RoleRepository roleRepository = configurableApplicationContext.getBean(RoleRepository.class);
		
		Role adminRole = new Role(ERole.ROLE_ADMIN);
		
		roleRepository.save(adminRole);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "password";
		String encodedPassword = passwordEncoder.encode(password);
		User user1 = new User("username", "email@email.com", encodedPassword);
		user1.setRoles(Set.of(adminRole));
		
		userRepository.save(user1);
		
	}

}
