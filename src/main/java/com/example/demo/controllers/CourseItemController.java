package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.CourseItem;
import com.example.demo.repositories.CourseItemRepository;
import com.example.demo.repositories.CourseRepository;

@RestController
@RequestMapping("/api/courseItems")
public class CourseItemController {
	
	@Autowired
	CourseItemRepository courseItemRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/all")
	public List<CourseItem> findAll() {
		return (List<CourseItem>) courseItemRepository.findAll();
	}
	
	@GetMapping("/ownedBy/{courseId}")
	public ResponseEntity<List<CourseItem>> getAllCoursesByOwnerId(@PathVariable Long courseId) throws Exception {
		
		 if (!courseRepository.existsById(courseId)) {
		      throw new Exception("Not found Course with id = " + courseId);
		    }

		List<CourseItem> courseItems = courseItemRepository.findByCourseId(courseId);
		return new ResponseEntity<>(courseItems, HttpStatus.OK);
	}


}
