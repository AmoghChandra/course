package com.accolite.course.models;


import java.util.Date;
import java.util.List;

import com.accolite.course.entities.Creator;
import com.accolite.course.entities.Skill;




	public class Course {
		private Long id;
		private String description;
		private String prerequesite;
		private String lastupdated;
		private String feedback;
		private List<Skill> skills;
		private List<Creator> creator;
		private String location;

		public Course() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPrerequesite() {
			return prerequesite;
		}

		public void setPrerequesite(String prerequesite) {
			this.prerequesite = prerequesite;
		}

		

		

		public String getLastupdated() {
			return lastupdated;
		}

		public void setLastupdated(String lastupdated) {
			this.lastupdated = lastupdated;
		}

		public String getFeedback() {
			return feedback;
		}

		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}

		public List<Skill> getSkills() {
			return skills;
		}

		public void setSkills(List<Skill> skills) {
			this.skills = skills;
		}

		

		

		public List<Creator> getCreator() {
			return creator;
		}

		public void setCreator(List<Creator> creator) {
			this.creator = creator;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		@Override
		public String toString() {
			return "CourseEntity [id=" + id + ", desc=" + description + ", prerequesite=" + prerequesite + ", lastUpdated="
					+ lastupdated + ", feedback=" + feedback + ", skills=" + skills + ", creator=" + creator + ", location="
					+ location + "]";
		}

}
