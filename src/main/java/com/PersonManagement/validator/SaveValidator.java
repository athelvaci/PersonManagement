package com.PersonManagement.validator;

import com.PersonManagement.exception.ValidationException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.mongodb.DBObject;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

@Component
public class SaveValidator extends AbstractMongoEventListener {
	private final Logger logger = LoggerFactory.getLogger(SaveValidator.class.getName());

	@Autowired
	private Validator validator;

	@Override
	public void onBeforeSave(Object source, DBObject dbo) {
		logger.info("Validation running for source({})", source);
		Set<ConstraintViolation<Object>> violations = validator.validate(source);
		Iterator it = violations.iterator();

		ArrayNode nodes = JsonNodeFactory.instance.arrayNode();

		while (it.hasNext()) {
			ConstraintViolationImpl constraintViolationImpl = (ConstraintViolationImpl) it.next();
			String message = constraintViolationImpl.getMessage();
			nodes.add(message);
		}

		if (nodes.size() > 0) {
			logger.info("Validation error : {}", nodes.toString());
			throw new ValidationException(nodes.toString());
		}
	}
}