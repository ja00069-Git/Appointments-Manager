package edu.westga.cs1302.project2.model;

import java.time.LocalDate;

import edu.westga.cs1302.project2.resources.UI;

/**
 * Monthly Apppointment class
 * 
 * @author jabes
 * @version Spring 2023
 */
public class Monthly extends Appointment {

	/**
	 * 2 param Constructor: Creates a new monthly appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param appointmentDate the appointment date
	 * @param description the appointment description
	 */
	public Monthly(LocalDate appointmentDate, String description) {
		super(appointmentDate, description);
	}
	
	/**
	 * 1 param Constructor: Creates a new monthly appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param description the appointment description
	 */
	public Monthly(String description) {
		this(LocalDate.now(), description);
	}
	
	@Override
	public boolean occursOn(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATE);
		}
		if (date.getDayOfMonth() == super.getDayOfMonth() && !date.isBefore(super.getDate())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.getClass().getName().hashCode();
	}

}
