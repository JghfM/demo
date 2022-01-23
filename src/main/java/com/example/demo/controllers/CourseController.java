package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Course;
import com.example.demo.entities.User;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/{id}")
	public Optional<Course> findById(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());
		return courseRepository.findById(id);
	}

	@GetMapping("/all")
	public List<Course> findAll() {
		return (List<Course>) courseRepository.findAll();
	}

	@GetMapping("/createdBy/{createdBy}")
	public ResponseEntity<List<Course>> getAllCoursesByOwnerId(@PathVariable Long createdBy) throws Exception {
		
		 if (!userRepository.existsById(createdBy)) {
		      throw new Exception("Not found User with id = " + createdBy);
		    }

		List<Course> courses = courseRepository.findByOwnerId(createdBy);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

}
