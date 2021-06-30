package com.participants.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import com.accolite.course.exception.NoContentException;
import com.participants.participants.repositories.ParticipantsRepository;
import com.participants.participants.service.ParticipantsService;

@RunWith(SpringRunner.class)

public class ParticipantService {

	//@Autowired
	//private ParticipantsRepository pr;
	
	@InjectMocks
	private ParticipantsService ps;
	
	@Test
	public void test1(){
		
		int res= ps.add1(1,4);
		assertEquals(res,5);
		
	}
	
	@Test
	public void test2() 
	{

		String str1="hello";
		assertEquals(str1,"hello");
		

	}
	
}
