package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.CourseAttendance;
import com.example.demo.repositories.CourseAttendanceRepository;

@RestController
@RequestMapping("/api/courseAttendance")
public class CourseAttendanceController {
	
	@Autowired
	CourseAttendanceRepository courseAttendanceRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<CourseAttendance>> getAll()  {
		
		List<CourseAttendance> courseAttendances = (List<CourseAttendance>) courseAttendanceRepository.findAll();
		return new ResponseEntity<>(courseAttendances, HttpStatus.OK);
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<CourseAttendance>> getAllAttendanceByCourseId(@PathVariable Long courseId)  {
		
		List<CourseAttendance> courseAttendances = (List<CourseAttendance>) courseAttendanceRepository.findByCourseId(courseId);
		return new ResponseEntity<>(courseAttendances, HttpStatus.OK);
	}

}
