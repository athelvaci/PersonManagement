package com.PersonManagement.validator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.PersonManagement.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml", "classpath:mongodb-config.xml",
		"classpath:servlet-context.xml" })
public class SaveValidatorTest {

	@Autowired
	private SaveValidator saveValidator;

	// test if validation works for a user with name, surname but no phonenumber
	@Test
	public void shouldValidationSucceed() {
		Person p = new Person("test", "user");
		try {
			saveValidator.onBeforeSave(p, null);

		} catch (Exception e) {
			fail();

		}
	}

	// empty name or surname exception
	@Test(expected = Exception.class)
	public void shouldValidationFail() {
		Person p = new Person("test", "", "12341234123");
		saveValidator.onBeforeSave(p, null);
	}

}
