package com.mongodb.validation.tests;
/*
import static org.junit.Assert.*;
import org.junit.Test;

import com.mongodb.validation.ValidTypesEnum;
import com.mongodb.validation.Validation;

public class ValidationTest {

	@Test
	public void testIsValidInt() {
		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_INTEGER, "12"));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_INTEGER, "12b"));

		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_INTEGER, "123"));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_INTEGER, "abc"));

		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_INTEGER, "000"));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_INTEGER, "!@#@!"));

		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_INTEGER, "999"));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_INTEGER, "This is a string."));
	}

	@Test
	public void testIsValidString() {
		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_STRING, "Valid String"));

		assertEquals(false, Validation.isValid(ValidTypesEnum.V_STRING, "Invalid String."));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_STRING, "*Malicious SQL =:"));
	}

	@Test
	public void testIsValidDate() {
		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_DATE, "12/8/1998"));
		assertEquals(false, Validation.isValid(ValidTypesEnum.V_DATE, "Not a date"));
		//TODO- add more tests, check edge cases better
	}

	@Test
	public void testIsValidTime() {
		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_TIME, "7:45"));
		assertEquals(true,  Validation.isValid(ValidTypesEnum.V_TIME, "07:45"));

		assertEquals(false, Validation.isValid(ValidTypesEnum.V_TIME, "117:45"));
	}

}
*/