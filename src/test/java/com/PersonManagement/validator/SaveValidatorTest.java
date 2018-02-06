package com.PersonManagement.validator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.PersonManagement.model.Person;

public class SaveValidatorTest {

	@Autowired
	private SaveValidator saveValidator;
	

	@Test
	public void shouldValidationSucceed() {
		Person p = new Person("ali", "alkan");
		try {
			saveValidator.onBeforeSave(p, null);

		} catch (Exception e) {
			fail();
		}
	}

	// empty name or surname exception
	@Test
	public void shouldValidationFail() {
		Person p = new Person("ali", "", "679123123");
		saveValidator.onBeforeSave(p, null);
	}

}
