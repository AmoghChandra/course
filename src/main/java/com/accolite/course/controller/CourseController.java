package com.accolite.course.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.service.CourseService;

import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;



@RestController

@RequestMapping("/course")
public class CourseController {



	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;

	@PostMapping("/save")
	public ResponseEntity<Course> saveIntocourseItemTable(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.saveIntocourseItemTable(course), HttpStatus.OK);
		
	}
	@Autowired
	   RestTemplate restTemplate;

	
	  
	@GetMapping(path = "{id}")
	public ResponseEntity<Course> fetchRecordFromcourseTable(@PathVariable("id") Long id) {
		Course courseData = null;
		try {
			courseData = courseService.fetchRecordFromcourseTable(id);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(courseData, HttpStatus.OK);

	}
	@GetMapping(path="/getbylocation/{location}")
	public List<Course> fetchlocation(@PathVariable("location") String location){
		//Course courseData = null;
		List<Course> CourseData= new ArrayList<>();
		try {
			CourseData = courseService.fetchCoursesByLocation(location);
		} catch (NoContentException e) {
			e.printStackTrace();
		}

		//return new ResponseEntity<>(CourseData, HttpStatus.OK);
			return CourseData;

	}
	
	
	@PutMapping("/updatecourse/{id}")
	public ResponseEntity<Course> updatecourse(@PathVariable("id") Long id, @RequestBody Course course) {

		Optional<CourseEntity> courseData = courseRepository.findById(id);

		if (courseData.isPresent()) {

			return new ResponseEntity<>(courseService.updateRecordIntocourseTable(course), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletecourse/{id}")
	public ResponseEntity<HttpStatus> deletecourse(@PathVariable("id") Long id) {
		try {

			courseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
