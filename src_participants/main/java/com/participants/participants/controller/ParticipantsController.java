package com.participants.participants.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.participants.participants.exceptions.NoContentException;
import com.participants.participants.models.Participants;
import com.participants.participants.repositories.ParticipantsRepository;
import com.participants.participants.service.ParticipantsService;
import com.participants.participants.entities.ParticipantEntity;


@RestController
@RequestMapping("/Participants")
public class ParticipantsController {
	@Autowired
	private ParticipantsService pt;
	@Autowired
	private ParticipantsRepository pr;
	
	@PostMapping("/save")
	public ResponseEntity<Participants> saveIntocourseItemTable(@RequestBody Participants course) {
		return new ResponseEntity<>(pt.saveIntocourseItemTable(course), HttpStatus.OK);
		
	}
	
	@GetMapping(path = "{id}")
	public ResponseEntity<Participants> fetchRecordFromcourseTable(@PathVariable("id") Long id) {
		Participants courseData = null;
		try {
			courseData = pt.fetchRecordFromcourseTable(id);
		}
		 catch (NoContentException e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(courseData, HttpStatus.OK);

	}
	
	
	@GetMapping(path="/getall/")
	public List<Participants> fetchAllRecords()
	{
		List<Participants> c= new ArrayList<>();
		try {
			c= pt.getParticipants();
			//c.forEach(a-> a.getEmail());
		} catch (NoContentException e) {
			e.printStackTrace();
		}
		return c;

	}
	
	@GetMapping(path="/getallemails")
	public List<String> fetchAllEmails()
	{
		List<String> courseData = new ArrayList<>();
		
		courseData.addAll(pt.fetchRecordFrom());
		
		
		return courseData;

	}

	
}
