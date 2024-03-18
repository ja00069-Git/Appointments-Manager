package edu.westga.cs1302.project2.resources;

/**
 * The Class UI defines output strings that are displayed for the user to see.
 * 
 * @author CS1302
 * @version Spring 2023
 */
public final class UI {

	/**
	 * Defines string messages for exception messages for the application.
	 */
	public static final class ExceptionMessages {

		public static final String NULL_APPOINTMENT = "Appointment cannot be null!";
		public static final String EXIST_APPOINTMENT = "The appointment is already in the calendar!";
		public static final String NULL_DATE = "Appointment date cannot be null!";
		public static final String EMPTY_DESCRIPTION = "Description cannot be empty!";
		public static final String NULL_DESCRIPTION = "Description cannot be null!";
		public static final String EMPTY_NULL_DESCRIPTION = "Description cannot be null or empty!";
		public static final String NONPOSITIVE_YEAR = "Year cannot be non-positive.";
		public static final String NULL_FILE = "File cannot be null";
		public static final String NULL_URL = "URL to input is null";
		public static final String NULL_APPOINTMENT_LIST = "Appointment list cannot be null";
		public static final String EMPTY_APPOINTMENT_LIST = "Appointment list cannot be empty";
		public static final String SPECIFIC_APPOINTMENT_LIST = "Specific Appointment list size cannot be less than 4";
		public static final String EMPTY_TYPE = "Type cannot be empty!";
		public static final String NULL_EMPTY_FIELD = "Please enter a number in the field: ";
		public static final String LATER_THAN_TODAY = "Date cannot be later than today!";
		public static final String READ_DATA_ERROR_MESSAGE = "Error in reading data file!";
		public static final String SAVE_DATA_ERROR_MESSAGE = "Error in saving data file!";
		public static final String LOAD_DIALOG_ERROR_TITLE = "Load Error";
		public static final String SAVE_DIALOG_ERROR_TITLE = "Save Error";
		public static final String INVALID_URL_MESSAGE = "ERROR: text entered is not a valid URL";
		public static final String NO_APPOINTMENT_WHILE_SAVING = "Please load or create appointments first before saving to a file";
		public static final String ENTER_URL = "Please enter the url:";
		public static final String TEXT_INPUT_TITLE = "Text Input Dialog";
		public static final String NO_APPOINTMENT_ERROR_MESSAGE = "Please load or create appointments first";
		public static final String PRIORITY_NOT_IN_RANGE = "Priority has to be 1-10.";
		public static final String NULL_TYPE = "Type cannot be null";
		public static final String INVALID_TYPE = "Invalid type";
		public static final String REQUIRED = "required";
		public static final String COLLECTION_CANNOT_BE_NULL = "Collection cannot be null";
		public static final String NULL_OBJECT = "Object cannot be null!";
		public static final String COLLECTION_CANNOT_CONTAIN_NULL = "Collection cannot contain null";
	}
	
	/**
	 * string messages for formating and delimiter.
	 */
	public static final class Text {
		public static final String FIELD_SEPERATOR = ",";
		public static final String SPACE = " ";
		public static final String FOUR_SPACE = "    ";
		public static final String EIGHT_SPACE = "        ";
	}
}