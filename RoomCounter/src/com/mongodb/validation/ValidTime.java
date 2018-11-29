package com.mongodb.validation;

public class ValidTime {
	public int hours = -1;
	public int minutes = -1;
	public boolean isValidTime = false;
	String originalInput = new String();

	public ValidTime(String input) {
		originalInput = input;
		if( Validation.isValid(ValidTypesEnum.V_TIME, input) ) {
			isValidTime = true;
			this.hours = Integer.parseInt(originalInput.substring(0,2));
			this.minutes = Integer.parseInt(originalInput.substring(2,4));
		}
	}

	public ValidTime(int _hours, int _minutes) {
		this.hours   = _hours;
		this.minutes = _minutes;
	}
}
