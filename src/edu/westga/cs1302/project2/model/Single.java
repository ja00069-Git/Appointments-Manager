package edu.westga.cs1302.project2.model;

import java.time.LocalDate;

import edu.westga.cs1302.project2.resources.UI;

/**
 * Single Appointment class
 * 
 * @author jabes
 * @version Spring 2023
 */
public class Single extends Appointment {

	private int priority;

	/**
	 * 3 param Constructor: Creates a new single appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param appointmentDate the appointment date
	 * @param description the appointment description
	 * @param priority the appaointment priority level
	 */
	public Single(LocalDate appointmentDate, String description, int priority) {
		super(appointmentDate, description);
		this.priority = priority;
	}

	/**
	 * 2 param Constructor: Creates a new single appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param description the appointment description
	 * @param priority the appaointment priority
	 */

	public Single(String description, int priority) {
		this(LocalDate.now(), description, priority);

	}

	/**
	 * return the appointment priority
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return priority the appointment priority
	 */
	public int getPriority() {
		return this.priority;
	}

	@Override
	public boolean occursOn(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATE);
		}
		if (date.isEqual(super.getDate())) {
			return true;
		}
		return false;

	}

	@Override
	public String toString() {
		return super.getDate() + ", " + "Single" + ", " + this.getDescription() + ", " + "priority level ="
				+ this.priority;

	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.getClass().getName().hashCode();
	}

}
