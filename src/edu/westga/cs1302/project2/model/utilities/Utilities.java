package edu.westga.cs1302.project2.model.utilities;

import java.time.LocalDate;

import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.AppointmentType;
import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.resources.UI;

/**
 * Util Class
 * @author jabes
 * @version Spring 2023
 */
public class Utilities {
	
	/**
	 * Users created appointment
	 * 
	 * @precondition type != null && date != null && description != null && type !=
	 *               "" && description != "" && date cannot be in the past
	 * @postcondition
	 * @param type the appointment type
	 * @param date the appointment date
	 * @param description the apointment description
	 * @param numberOfYears the number of years the apppointment occurs
	 * @param priority the appointment priority
	 * @return a single appointment with priority 1 if type is not an appointment type
	 */
	public static Appointment createAppointment(String type, LocalDate date, String description, int numberOfYears,
			int priority) {
		if (type == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_TYPE);
		}
		if (type.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_TYPE);
		}
		if (date == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATE);
		}
		if (description == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DESCRIPTION);
		}
		if (description.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DESCRIPTION);
		}
		if (date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LATER_THAN_TODAY);
		}
		
		if (type.equalsIgnoreCase(AppointmentType.DAILY.name())) {
			return new Daily(date, description);
		}
		if (type.equalsIgnoreCase(AppointmentType.MONTHLY.name())) {
			return new Monthly(date, description);
		}
		if (type.equalsIgnoreCase(AppointmentType.SINGLE.name())) {
			return new Single(date, description, priority);
		} else {
			return new Single(date, description, 1);
		}

	}

}
