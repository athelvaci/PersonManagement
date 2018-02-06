package com.PersonManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.PersonManagement.model.Person;

@Repository
public interface PersonRepositoryImpl extends MongoRepository<Person, String> {
}
