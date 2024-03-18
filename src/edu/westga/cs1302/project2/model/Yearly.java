package edu.westga.cs1302.project2.model;

import java.time.LocalDate;

import edu.westga.cs1302.project2.resources.UI;

/**
 * Yearly Apppointment class
 * 
 * @author jabes
 * @version Spring 2023
 */
public class Yearly extends Appointment {

	private int numberOfYears;
	private int occurrences;

	/**
	 * 3 param Constructor:Creates a new Yearly appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param date the appointment date
	 * @param description the appointment description
	 * @param numberOfYears how many years the appointment will occur
	 */
	public Yearly(LocalDate date, String description, int numberOfYears) {
		super(date, description);
		this.numberOfYears = numberOfYears;
		this.occurrences = 1;
	}

	/**
	 * 2 param Constructor: Creates a new Yearly appointment
	 *
	 * @precondition none
	 * @postcondition none
	 * @param description the appointment description
	 * @param numberOfYears how many years the appointment will occur
	 */
	public Yearly(String description, int numberOfYears) {
		this(LocalDate.now(), description, numberOfYears);
	}

	/**
	 * Gets the number of years
	 * @precondition none
	 * @postcondition none
	 * @return the numberOfYears
	 */
	public int getNumberOfYears() {
		return this.numberOfYears;
	}

	@Override
	public boolean occursOn(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATE);
		}
		
		if (date.getMonthValue() == getDate().getMonthValue() && date.getDayOfMonth() == getDate().getDayOfMonth()) {
			if (this.occurrences <= this.numberOfYears) {
				this.occurrences++;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
