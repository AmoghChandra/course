package com.accolite.course.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.accolite.course.entities.Skill;



@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

	//Optional<Creator> findByLocation(String location);

	
}
