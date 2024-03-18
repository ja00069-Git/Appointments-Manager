package edu.westga.cs1302.project2.model;

import java.time.LocalDate;
import java.util.Objects;

import edu.westga.cs1302.project2.resources.UI;

/**
 * Appointment class
 * 
 * @author jabes
 * @version Spring 2023
 */
public abstract class Appointment {

	private LocalDate appointmentDate;

	private String description;

	/**
	 * 2 param Constructor: Creates a new instance of an appointment
	 *
	 * @precondition description != null && description != "" && date != null &&
	 *               date connot be earlier than present day
	 * @postcondition date = this.getDate() && description = this.getDexcription()
	 * @param date the appointment date
	 * @param description the appointment description
	 */
	protected Appointment(LocalDate date, String description) {
		if (date == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATE);
		}
		if (description == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DESCRIPTION);
		}
		if (description.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DESCRIPTION);
		}
		if (date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LATER_THAN_TODAY);
		}
		this.appointmentDate = date;
		this.description = description;
	}

	/**
	 * 1 param Constructor: Creates a new instance of an appointment
	 *
	 * @precondition description != null && !description.isEmply
	 * @postcondition none
	 * @param description the appointment description
	 */
	protected Appointment(String description) {		
		this(LocalDate.now(), description);
	}

	/**
	 * Gets the appointment date
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the appointment date
	 */
	public LocalDate getDate() {
		return this.appointmentDate;
	}

	/**
	 * Gets the appointment descriptionS
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the appointment description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the day of the appointment number value
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the day of the appointment
	 */
	public int getDayOfMonth() {
		return this.appointmentDate.getDayOfMonth();
	}

	/**
	 * Gets the month of the appointment number value
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the month number of the appointment
	 */
	public int getMonthNumber() {
		return this.appointmentDate.getMonthValue();
	}

	/**
	 * Gets the year of the appointment number value
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the year of the appointment
	 */
	public int getYear() {
		return this.appointmentDate.getYear();
	}

	@Override
	public String toString() {
		return this.getDate() + ", " + this.getClass().getSimpleName() + ", " + this.description;
	}

	@Override
	public boolean equals(Object otherObject) {
		Appointment anAptObject = (Appointment) otherObject;
		if (otherObject == null) {
			return false;
		}
		if (this == otherObject) {
			return true;
		}
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		if (this.appointmentDate.equals(anAptObject.getDate()) && this.description.equals(anAptObject.getDescription())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.appointmentDate, this.description);
	}

	/**
	 * Checks if an appoinments that occurs on the appoiment date should on a specified date
	 * 
	 * @precondition date != null
	 * @postcondition none
	 * @param date the date to check
	 * @return true if the appoiment should occurs on the specified date
	 */
	public abstract boolean occursOn(LocalDate date);

}
