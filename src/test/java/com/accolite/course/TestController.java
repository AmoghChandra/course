package com.accolite.course;


import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accolite.course.controller.CourseController;
import com.accolite.course.entities.CourseEntity;
import com.accolite.course.entities.Creator;
import com.accolite.course.entities.Skill;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.service.CourseService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import junit.framework.Assert;
//import com.participants.participants.service.ParticipantsService;

//@RunWith(SpringRunner.class)
@Import({CourseService.class})
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class TestController{

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	ObjectMapper objectmapper=new ObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@InjectMocks
	private CourseService courseservice;
	
	@Mock
	private CourseRepository courserepo;
	
	@Mock
	private Course course;
	
	@Mock
	private CourseEntity courseentity;
	
	@InjectMocks
	private CourseController coursecontroller;
	
	
	@Test
	public void test1() throws NoContentException
	{
		int res=courseservice.add1(10, 20);
		assertEquals(res,30);
	}
	
	@Test
	public void test2() throws NoContentException
	{
		String str1="hello";
		assertEquals(str1,"hello");
	}
	
	@SuppressWarnings("null")
	@Test
	public void mapping_function()
	{
		Skill s = new Skill(1, "c");
		//s.setId((long)1);
		//s.setName("c");		
		List<Skill> skill = new ArrayList<>();
		skill.add(s);
		
		Creator c = new Creator(1, "Ana");
		//c.setId((long)1);
		//c.setName("Ana");
		List<Creator> creator = new ArrayList<>();
		creator.add(c);
		
		Course c1=new Course();
		c1.setCreator(creator);
		c1.setDescription("c");
		c1.setFeedback("good");
		c1.setId((long)1);
		c1.setLastupdated(null);
		c1.setLocation("bangalore");
		c1.setPrerequesite(null);
		c1.setSkills(skill);
		
		CourseEntity c2= new CourseEntity();
		assertNotEquals(c2, courseservice.mapObjectToEntity(c1));
	}
	

@Test
public void fetchlocation() throws NoContentException
{
	List<Creator> creator1 = new ArrayList<>();
	Creator createrobj = new Creator(1, "Amogha");
	creator1.add(createrobj);
	List<Skill> skill1 = new ArrayList<>();
	Skill skillobj = new Skill(1, "Programming");
	skill1.add(skillobj);
	List<CourseEntity> courseentity1=new ArrayList<>();
	
	List<Course> course1 = new ArrayList<>();
	
	CourseEntity ce =new CourseEntity();
	ce.setCreator(creator1);
	ce.setDescription("Mathematics");
	ce.setFeedback("good");
	ce.setId((long)1);
	ce.setLastupdated("29/06/2021");
	ce.setLocation("chennai");
	ce.setPrerequesite(null);
	ce.setSkill(skill1);
	
	courseentity1.add(ce);
    
	when(courserepo.findByLocation("Chennai")).thenReturn(courseentity1);
	assertNotNull(courseservice.fetchCoursesByLocation("Chennai"));
	//assertEquals(course1,test1.fetchCoursesByLocation("Chennai"));
}

@Test

public void addskill()
{
	List<Creator> creator1 = new ArrayList<>();
	Creator createrobj = new Creator(1, "Menaka");
	creator1.add(createrobj);
	List<Skill> skill1 = new ArrayList<>();
	Skill skillobj = new Skill(1, "Programming");
	skill1.add(skillobj);
	
	
	Course c1=new Course();
	c1.setCreator(creator1);
	c1.setDescription("Mathematics");
	c1.setFeedback("useful");
	c1.setId((long)1);
	c1.setLastupdated("29/06/21");
	c1.setLocation("Chennai");
	c1.setPrerequesite("programming");
	c1.setSkills(skill1);
	
	
	List<Skill> skill_list = courseservice.addSkill(c1);
	assertEquals(1,skill_list.size());
}


/**
@SuppressWarnings("unchecked")
@Test
@JsonCreator
public void testAddcourse() throws Exception 
{
   // MockHttpServletRequest request = new MockHttpServletRequest();
   // RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
     
    //when(coursecontroller.saveIntocourseItemTable(any(Course.class)).thenReturn(true);
     
	List<Creator> creator1 = new ArrayList<>();
	Creator createrobj = new Creator(12, "Menaka");
	creator1.add(createrobj);
	List<Skill> skill1 = new ArrayList<>();
	Skill skillobj = new Skill(12, "Programming");
	skill1.add(skillobj);
	
    Course c1 = new Course();
	c1.setCreator(creator1);
	c1.setDescription("Mathematics");
	c1.setFeedback("useful");
	c1.setId((long)12);
	c1.setLastupdated("29/06/21");
	c1.setLocation("Chennai");
	c1.setPrerequesite("programming");
	c1.setSkills(skill1);
	 
    ResponseEntity<Course> responseEntity = coursecontroller.saveIntocourseItemTable(c1);
    String s= responseEntity.getStatusCode().toString();
    
    //List<String> str=null;
    
    //String[] tokens=s.split(",");
	//for (String a : tokens)
		//str.add(s);
	
   // String JsonRequest= objectmapper.writeValueAsString(c1);
    //MvcResult res= mockMvc.perform(post("/course/save").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
    		//.andExpect(status().isOk()).andReturn();
    
    //String resultcontext= res.getResponse().getContentAsString();
    //System.out.println(res);
    
    //ResponseEntity<Course> response= objectmapper.readValue(resultcontext, ResponseEntity.class);
    
    assertEquals(s,"200");
    
}

**/

@Test
public void injectedcomponentsNotNull() {
	Optional<CourseEntity> entity= courserepo.findById((long) 1);
	//String res=entity.get().getFeedback().toString();
	//int res1=entity.get().getFeedback().compareTo("good");
	assertNotNull(entity);
}



}


