package com.accolite.course.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.course.entities.CourseEntity;


	@Repository
	public interface CreatorRepository extends CrudRepository<CourseEntity, Long> {

		Optional<CourseEntity> findByLocation(String location);

		
	}

