package com.PersonManagement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.PersonManagement.exception.InconsistentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class.getName());

	public static ObjectNode makeResponse(Status status, String content, Object params) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("status", status.name());
			node.put("content", content);
			node.put("params", mapper.writeValueAsString(params));
			return node;
		} catch (Exception e) {
			logger.error("Status({}) Content({}) Params({}) " + e.getMessage(), status, content, params);
			throw new InconsistentException(Constants.SERVER_ERROR);
		}
	}
}
