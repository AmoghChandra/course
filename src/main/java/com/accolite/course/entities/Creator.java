package com.accolite.course.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Creator {
	
	@Id
    private long id;
	private String name;

	public Creator() {

	}

	

	public Creator(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



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
		return "Creator [creatorId=" + id + ", name=" + name + "]";
	}

}
