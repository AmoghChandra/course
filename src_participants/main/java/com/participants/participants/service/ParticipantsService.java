package com.participants.participants.service;




import com.participants.participants.entities.ParticipantEntity;
import com.participants.participants.exceptions.NoContentException;
import com.participants.participants.models.Participants;
import com.participants.participants.repositories.ParticipantsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
public class ParticipantsService {
	
	@Autowired
	private ParticipantsRepository rp;

	public Participants saveIntocourseItemTable(Participants course) {
		ParticipantEntity entity = rp.save(mapObjectToEntity(course));
		return mapEntityToObject(entity);
	}
	public ParticipantEntity mapObjectToEntity(Participants course) {
		ParticipantEntity entity = new ParticipantEntity();
		
		
		entity.setId(course.getId());
		entity.setName(course.getName());
		entity.setEmail(course.getEmail());
		
		return entity;
	}
	
	public Participants mapEntityToObject(ParticipantEntity entity) {

		Participants course = new Participants();
		course.setId(entity.getId());
		course.setName(entity.getName());
		course.setEmail(entity.getEmail());

		return course;
	}
	



	public Participants fetchRecordFromcourseTable(Long id) throws NoContentException {
		Optional<ParticipantEntity> entity = rp.findById(id);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());

	}
	public List<Participants> getParticipants() throws NoContentException {
		List<Participants> c= new ArrayList<Participants>();
		List<ParticipantEntity> entity = (List<ParticipantEntity>) rp.findAll();
		if (entity.isEmpty()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		entity.stream().forEach(a -> {
			c.add(mapEntityToObject(a));
		});
		return c;
	}

	public List<String> fetchRecordFrom() {
		List<String> course1 = new ArrayList<>();
		List<ParticipantEntity> entity=rp.findAll();
		entity.stream().forEach(a->{
			String g=a.getEmail();
			course1.add(g);
		});
		//System.out.println(course1);
		return course1;

	}
	
	public int add1(int a, int b)
	{
		return a+b;
	}
	

	
}
