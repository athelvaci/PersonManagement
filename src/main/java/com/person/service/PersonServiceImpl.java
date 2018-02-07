package com.person.service;

import java.util.List;

import com.person.model.Person;

public interface PersonServiceImpl {

	List<Person> findAll();
	
	Person findById(String id);
	
	Person save(Person person);
	
	Person update(Person person);
	
	void remove(String id);
}
