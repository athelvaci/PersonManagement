package com.PersonManagement.service.impl;

import com.PersonManagement.exception.InconsistentException;
import com.PersonManagement.model.Person;
import com.PersonManagement.repository.IPersonRepository;
import com.PersonManagement.service.IPersonService;
import com.PersonManagement.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {
	
	private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

	@Autowired
	private IPersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(String id) {
		return personRepository.findOne(id);
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person update(Person person) {
			return personRepository.save(person);
	}

	@Override
	public void remove(String id) {
		
			personRepository.delete(id);
		
	}
}
