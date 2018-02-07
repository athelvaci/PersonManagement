package com.person.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.person.exception.PersonException;
import com.person.util.Constants;
import com.person.util.JSONUtil;
import com.person.util.Status;

public class AjaxController {
	private final Logger logger = LoggerFactory.getLogger(AjaxController.class.getName());

	@ExceptionHandler(PersonException.class)
	protected @ResponseBody ObjectNode handleException(PersonException exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, exception.getMessage(), null);
		
		return message;
	}

	@ExceptionHandler(Exception.class)
	protected @ResponseBody ObjectNode handleException(Exception exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, Constants.SERVER_ERROR, null);
		return message;
	}
}
