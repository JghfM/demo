package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.CourseAttendance;
import com.example.demo.entities.CourseItem;

public interface CourseAttendanceRepository extends CrudRepository<CourseAttendance, Long> {
	List<CourseAttendance> findByUserId(Long userId);
	
	List<CourseAttendance> findByCourseId(Long courseId);
}
