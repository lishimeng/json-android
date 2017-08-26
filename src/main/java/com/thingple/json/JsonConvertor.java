package com.thingple.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConvertor {

	private static ObjectMapper objectMapper = null;
	
	private synchronized static void init() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	}
	
	private static ObjectMapper getInstance() {
		
		if (objectMapper == null) {
			init();
		}
		return objectMapper;
	}
	
	public static String convert2String(Object entity) {
		try {
			return getInstance().writeValueAsString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <E> E convert2Object(String json, Class<E> clazz) {
		E entity = null;
		if (json == null || json.trim().equals("")) {
			return null;
		}
		try {
			entity = getInstance().readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}
