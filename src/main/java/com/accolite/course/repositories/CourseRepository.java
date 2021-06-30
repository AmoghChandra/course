package com.accolite.course.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.accolite.course.entities.CourseEntity;

import java.util.*;



@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {

	List<CourseEntity> findByLocation(String location);
	

	
}
