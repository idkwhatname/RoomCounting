package com.mongodb.validation;

public class ValidString {
	public String validString = new String();
	public boolean isValidString = false;
	String originalInput = new String();

	public ValidString(String input) {
		originalInput = input;
		validString = null;
		if(Validation.isValid(ValidTypesEnum.V_STRING, input)) {
			isValidString = true;
			validString = input;
		}
	}

}
