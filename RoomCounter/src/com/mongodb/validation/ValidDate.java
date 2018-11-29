package com.mongodb.validation;

public class ValidDate {
	public int day   = -1;
	public int month = -1;
	public int year  = -1;
	public boolean isValidDate = false;
	String originalInput = new String();

	/**
	 * Generate valid date object
	 * @param input
	 */
	public ValidDate(String input) {
		originalInput = input;
		if(Validation.isValid(ValidTypesEnum.V_DATE, input)) {
			isValidDate = true;
			this.day = Integer.parseInt(originalInput.substring(0,2));
			this.month = Integer.parseInt(originalInput.substring(2,4));
			this.year = Integer.parseInt(originalInput.substring(4,6));
		}
	}

	/**
	 * Generate valid date object from given day, month, and year
	 * @param day
	 * @param month
	 * @param year
	 */
	public ValidDate(int _day, int _month, int _year) {
		this.day   = _day;
		this.month = _month;
		this.year  = _year;
	}

}
