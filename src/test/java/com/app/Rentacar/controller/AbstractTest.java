package com.app.Rentacar.controller;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class AbstractTest {
	protected static final Integer ID = 1;
	protected static final String DNI = "48903865T";
	protected static final String NAME = "LUIS GARCIA";
	protected static final String START_DATE = "01/01/2019";
	protected static final String END_DATE = "31/12/2019";
	protected static final double PRICE_RATE= 25;	
	protected static final String CAR_PLATE = "1111AAA";
	protected static final String REGISTRATION_YEAR = "2019";

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}