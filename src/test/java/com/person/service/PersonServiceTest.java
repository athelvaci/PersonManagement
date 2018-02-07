package com.person.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.person.model.Person;
import com.person.service.PersonServiceImpl;
import com.person.service.impl.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml", "classpath:mongodb-config.xml",
		"classpath:servlet-context.xml" })
public class PersonServiceTest {

	@Autowired
	PersonServiceImpl personService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<Person> personList = personService.findAll();
		System.out.println("********************* PERSONNEL SIZE:" + personList.size() + "*********************\n");
		System.out.println(personList);

		assertNotNull(personList);

		// assert correct type/impl
		assertThat(personService, instanceOf(PersonService.class));

		assertEquals("class com.person.service.impl.PersonService", this.personService.getClass().toString());
	}

	@Test
	public void testFindById() {
		Person randPerson = getRandomPerson();
		personService.save(randPerson);
		Person savedPerson = personService.findById(randPerson.getId());
		assertNotNull(savedPerson);

		// remove test user
		personService.remove(savedPerson.getId());

	}

	@Test
	@Rollback(true)
	public void testSave() {
		Person randPerson = getRandomPerson();
		Person savedPerson = personService.save(randPerson);
		assertNotNull(savedPerson.getId());
		assertEquals(randPerson.getName(), savedPerson.getName());
		assertEquals(randPerson.getSurname(), savedPerson.getSurname());
		assertEquals(randPerson.getPhoneNumber(), savedPerson.getPhoneNumber());
		// remove test user
		personService.remove(savedPerson.getId());

	}

	@Test
	public void testUpdate() {
		Person updatedPerson = getRandomPerson();
		Person savedPerson = personService.save(getRandomPerson());
		updatedPerson.setId(savedPerson.getId());
		personService.update(updatedPerson);
		assertNotEquals(updatedPerson.getName(), savedPerson.getName());
		assertNotEquals(updatedPerson.getSurname(), savedPerson.getSurname());
		assertNotEquals(updatedPerson.getPhoneNumber(), savedPerson.getPhoneNumber());

		// remove test user
		personService.remove(updatedPerson.getId());

	}

	@Test
	public void testRemove() {
		Person randPerson = getRandomPerson();
		personService.save(randPerson);
		personService.remove(randPerson.getId());
		assertNull(personService.findById(randPerson.getId()));
	}

	private Person getRandomPerson() {
		return new Person(getRandomFixedString(false), getRandomFixedString(false), getRandomFixedString(true));
	}

	private String getRandomFixedString(boolean numberOnly) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		if (numberOnly) {
			SALTCHARS = "1234567890";
		}
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

}
