package com.PersonManagement.service;

import java.util.List;

import com.PersonManagement.model.Person;

public interface IPersonService {

	List<Person> findAll();
	
	Person findById(String id);
	
	Person save(Person person);
	
	Person update(Person person);
	
	void remove(String id);
}
