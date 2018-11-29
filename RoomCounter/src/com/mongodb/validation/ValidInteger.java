package com.mongodb.validation;

public class ValidInteger {
	public int integer = 1;
	public boolean isValidInteger = false;
	String originalInput = new String();

	public ValidInteger(String input) {
		originalInput = input;
		if(Validation.isValid(ValidTypesEnum.V_INTEGER, input)) {
			isValidInteger = true;
			integer = Integer.parseInt(originalInput);
		}
	}

	public ValidInteger(int i) {
		this.integer = i;
	}

}
