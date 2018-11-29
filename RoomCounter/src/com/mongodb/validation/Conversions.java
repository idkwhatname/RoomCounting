package com.mongodb.validation;

import com.mongodb.validation.ValidTypesEnum;
import com.mongodb.validation.Validation;

public class Conversions {
	/**
	 * Converts string to a valid date.
	 * @param input the string to be converted, in the format dd/mm/yyyy (or d/m/yy, length of each doesn't matter)
	 * @return a ValidDate object with converted values, or null if the input was not a valid date.
	 */
	public ValidDate toValidDate(String input) {
		if(!Validation.isValid(ValidTypesEnum.V_DATE, input)) {
			return null;
		} else {
			int day, month, year;

			int firstSlashIndex = 0;
			for(;;++firstSlashIndex) {
				if(input.charAt(firstSlashIndex) == '/')
					break;
			}

			int secondSlashIndex = firstSlashIndex;
			for(;;++secondSlashIndex) {
				if(input.charAt(secondSlashIndex) == '/')
					break;
			}

			day   = stringRangeToInt(0,                  firstSlashIndex,     input);
			month = stringRangeToInt(firstSlashIndex+1,  secondSlashIndex,    input);
			year  = stringRangeToInt(secondSlashIndex+1, input.length(),      input);

			ValidDate vd = new ValidDate(day, month, year);

			return vd;
		}
	}

	/**
	 * Converts a string (representing an integer, eg "12") to a valid integer
	 * @param input the string to be converted, eg "12"
	 * @return a ValidInteger object with converted values, or null if the input was not a valid integer.
	 */
	public ValidInteger toValidInteger(String input) {
		if(!Validation.isValid(ValidTypesEnum.V_INTEGER, input)) {
			return null;
		} else {
			int rval = 0;
			for(int i = 0; i < input.length(); ++i){
				rval = rval*10 + input.charAt(i)-'0';
			}
			ValidInteger vi = new ValidInteger(rval);
			return vi;
		}
	}

	/**
	 * Converts a string to a safe string
	 * @param input the string to be converted
	 * @return a ValidString object, or null if the input was an unsafe string.
	 */
	public ValidString toValidString(String input) {
		if(!Validation.isValid(ValidTypesEnum.V_STRING, input)) {
			return null;
		} else {
			ValidString vs = new ValidString(input);
			return vs;
		}
	}

	/**
	 * Converts a string to a time
	 * @param input the string (representing a time, eg "12:43") to be converted
	 * @return a ValidTime object with converted values, or null if the input was not a valid time.
	 */
	public ValidTime toValidTime(String input) {
		if(!Validation.isValid(ValidTypesEnum.V_TIME, input)) {
			return null;
		} else {
			int hours, minutes;

			int colonIndex;
			for(colonIndex = 0;; ++colonIndex){
				if(input.charAt(colonIndex) == ':')
					break;
			}

			hours   = stringRangeToInt(0, colonIndex, input);
			minutes = stringRangeToInt(colonIndex+1, input.length(), input);

			ValidTime vt = new ValidTime(hours, minutes);
			return vt;
		}
	}

	private int stringRangeToInt(int start, int end, String input) {

		// Just a bit of defensive coding here
		if(end > start) {
			int temp = end;
			end = start;
			start = temp;
		}

		int returnval;
		for(returnval = 0; start != end; ++start) {
			returnval = (returnval*10) + input.charAt(start)-'0';
		}
		return returnval;
	}
}
