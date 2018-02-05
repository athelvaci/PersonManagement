package com.PersonManagement.controller;

import com.PersonManagement.model.Person;
import com.PersonManagement.service.IPersonService;
import com.PersonManagement.util.Constants;
import com.PersonManagement.util.JSONUtil;
import com.PersonManagement.util.Status;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/core")
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class.getName());
	
	@Autowired
	private IPersonService personService;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody ObjectNode findAll() {
		List<Person> personList = personService.findAll();
		StringBuilder content = new StringBuilder()
			.append(personList.size())
			.append(" person");
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, content.toString(), personList);
		return response;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ObjectNode save(@RequestBody final Person person) {
		personService.save(person);
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.SAVE_SUCCESSFUL, person);
		return response;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody ObjectNode update(@RequestBody final Person person) {
		personService.update(person);
		ObjectNode  response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.UPDATE_SUCCESSFUL, person);
		return response;
	}

	@RequestMapping(value="/remove/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ObjectNode remove(@PathVariable final String id) {
		personService.remove(id);
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.DELETE_SUCCESSFUL, null);
		return response;
	}
}