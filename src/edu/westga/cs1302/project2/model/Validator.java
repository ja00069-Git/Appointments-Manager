package edu.westga.cs1302.project2.model;

import java.time.LocalDate;

import edu.westga.cs1302.project2.resources.UI;

/**
 * The class Validator validates user input on the GUI.
 * 
 * @author CS1302
 * @version Spring 2023
 */
public class Validator {

	private String errorMessage;

	/**
	 * Instantiates a new Validator.
	 * 
	 * @precondition none
	 * @postcondition getErrorMessage.isEmpty()
	 */
	public Validator() {
		this.errorMessage = "";
	}

	/**
	 * Gets the error message.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the error message.
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Validate description. Removes trailing and leading spaces from description.
	 * Checks if the resulting string is a valid description and sets a suitable
	 * error message.
	 *
	 * @precondition none
	 * @postcondition getErrorMessage().isEmpty() if the passed in description is
	 *                valid; otherwise getErrorMessage() returns an suitable error
	 *                message
	 *
	 * @param description the description
	 * @return description without leading and trailing spaces if it is a valid
	 *         description; null otherwise
	 */
	public String validateDescription(String description) {
		description = description.trim();
		if (description == null || description.isEmpty()) {
			this.errorMessage += UI.ExceptionMessages.EMPTY_NULL_DESCRIPTION + System.lineSeparator();
			return null;
		} else {

			return description;
		}
	}

	/**
	 * Validate type. Check if the input string not null and sets a suitable error
	 * message if it's null.
	 *
	 * @precondition none
	 * @postcondition getErrorMessage().isEmpty() if the passed in type is not null;
	 *                otherwise getErrorMessage() returns an suitable error message
	 * @param type the type
	 * @return type if it is a valid type; null otherwise
	 */
	public String validateType(String type) {

		if (type == null) {
			this.errorMessage += UI.ExceptionMessages.NULL_TYPE + System.lineSeparator();
			return null;
		} else {

			return type;
		}
	}

	/**
	 * Validate date. Check if the input date not null and sets a suitable error
	 * message if it's null.
	 *
	 * @precondition none
	 * @postcondition getErrorMessage().isEmpty() if the passed in date is not null;
	 *                otherwise getErrorMessage() returns an suitable error message
	 * @param date the date
	 * @return date if it is a valid date; null otherwise
	 */
	public LocalDate validateDate(LocalDate date) {

		if (date == null) {
			this.errorMessage += UI.ExceptionMessages.NULL_DATE + System.lineSeparator();
			return null;
		} else {

			return date;
		}
	}

	/**
	 * Validate a TextField. Check if the input string not null and sets a suitable
	 * error message if it's null or empty.
	 *
	 * @precondition none
	 * @postcondition getErrorMessage().isEmpty() if the passed in type is not null;
	 *                otherwise getErrorMessage() returns an suitable error message
	 *
	 * @param field the input field
	 * @return field if it is not null or empty; null otherwise
	 */
	public String validateTextField(String field) {
		if (field == null || field.isEmpty()) {
			this.errorMessage += UI.ExceptionMessages.NULL_EMPTY_FIELD;
			return null;
		} else {
			return field;
		}
	}

}
