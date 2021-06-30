package com.accolite.course.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Skill {
	@Id
	private long id;
	private String name;
	/*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_entity_id", nullable = false)
    private CourseEntity courseEntity;*/

	public Skill() {

	}
   

	public Skill(long id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}


	/*public CourseEntity getCourseEntity() {
		return courseEntity;
	}


	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}*/


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + id + ", name=" + name + "]";
	}

}
