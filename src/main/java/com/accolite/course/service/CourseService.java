package com.accolite.course.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.entities.Creator;
import com.accolite.course.entities.Skill;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.repositories.CreatorRepository;
import com.accolite.course.repositories.SkillRepository;

@Service
@Component
public class CourseService {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private CreatorRepository creatorRepository;

	public Course saveIntocourseItemTable(Course course) {
		CourseEntity entity = courseRepository.save(mapObjectToEntity(course));		
		Sendemail(course.getDescription());
		return mapEntityToObject(entity);
	}

	
	public CourseEntity mapObjectToEntity(Course course) {
		CourseEntity entity = new CourseEntity();
		
		
		entity.setId(course.getId());
		entity.setDescription(course.getDescription());
		entity.setPrerequesite(course.getPrerequesite());
		
		entity.setLastupdated(course.getLastupdated());
		
		entity.setFeedback(course.getFeedback());
		entity.setSkill(addSkill(course));
		entity.setCreator(addCreator(course));
		entity.setLocation(course.getLocation());
		
		return entity;
	}

	public List<Skill> addSkill(Course course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkills().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkills().get(i).getId();
			name = course.getSkills().get(i).getName();

			Skill skill = new Skill(skillId, name);
			//skillRepository.save(skill);
			skillList.add(skill);

		}

		return skillList;

	}

	private List<Skill> addSkill(CourseEntity course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkill().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkill().get(i).getId();
			name = course.getSkill().get(i).getName();

			Skill skill = new Skill(skillId, name);
			
			skillList.add(skill);

		}

		return skillList;

	}

	private List<Creator> addCreator(Course course) {
		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}
   
	private List<Creator> addCreator(CourseEntity course) {
		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}

	public Course mapEntityToObject(CourseEntity entity) {

		Course course = new Course();
		course.setId(entity.getId());
		course.setDescription(entity.getDescription());
		course.setPrerequesite(entity.getPrerequesite());
		course.setLastupdated(entity.getLastupdated());
		course.setFeedback(entity.getFeedback());
		course.setSkills(addSkill(entity));
		course.setCreator(addCreator(entity));
		course.setLocation(entity.getLocation());

		return course;
	}

	public Course fetchRecordFromcourseTable(Long id) throws NoContentException {

		Optional<CourseEntity> entity = courseRepository.findById(id);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());

	}

	public Course updateRecordIntocourseTable(Course course) {
		CourseEntity entity = courseRepository.save(mapObjectToEntity(course));
		return mapEntityToObject(entity);

	}

	public List<Course> fetchCoursesByLocation(String location) throws NoContentException {
		List<Course> c=new ArrayList<Course>(); 
		List<CourseEntity> entity = courseRepository.findByLocation(location);
		entity.stream().forEach(a->{
			c.add(mapEntityToObject(a));
		});
		return c;

	} 

	public void Sendemail(String course)
    {
		
   	 SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("chandraamogha@gmail.com");
        message.setTo(getParticipants1()); 
        message.setSubject("spring boot system"); 
        message.setText("A new course related to+ "+ course+" added!");
        mailSender.send(message);
   }
	
	public String[]  getParticipants1()
	{
		String url ="http://localhost:8901/Participants/getallemails";
		//Object[] objects = restTemplate.getForObject(url,Object[].class);
		ResponseEntity<String>reponse=null;
		reponse=restTemplate.exchange(url,HttpMethod.GET,getHeaders(),String.class);
		String t=reponse.getBody().toString();
		t= t.substring(1, t.length()-1);
		System.out.println(t);
		String[] tokens=t.split(",");
		for (String a : tokens)
			System.out.println(a);
		return tokens;
			
	}
	private HttpEntity<?> getHeaders() {
		HttpHeaders header=new HttpHeaders();
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(header);		
			
	}
	 public String getParticipants() {
		 String url ="http://localhost:8901/Participants/getAllParticpants";
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
	   }
	 
	 public int add1(int a,int b) {
		 return a+b;
	 }

}