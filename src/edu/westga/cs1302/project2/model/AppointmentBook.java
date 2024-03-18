package edu.westga.cs1302.project2.model;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs1302.project2.resources.UI;

/**
 * Appointment Book
 * @author jabes
 * @version Spring 2023
 */
public class AppointmentBook {
	
	private List<Appointment> appointments;

	/**
	 * Create a new Instance of an appointment book
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public AppointmentBook() {
		this.appointments = new ArrayList<Appointment>();
	}

	/**
	 * Create an Instance of an appointment book and populates it with appointmenst
	 * from a spesifed ArrayList
	 *
	 * @precondition none
	 * @postcondition none
	 * @param appointments an list of appointments
	 */
	public AppointmentBook(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * Gets and returns the size of the appointment book.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the size of the appointment book
	 */
	public int size() {
		return this.appointments.size();
	}

	/**
	 * Gets and returns a list of appointments
	 * 
	 * @precondition none
	 * @postcindition none
	 * @return a list of appointments
	 */
	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	/**
	 * Adds the specified Appointments to the ApppintmentBook.
	 * 
	 * @precondition appointment != null
	 * @postcondition size() = size()@prev + 1
	 * @param appointment the appointment to be added
	 * @return false if the appointment alread exist in the appointment book
	 */
	public boolean add(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_APPOINTMENT);
		}
		if (this.contains(appointment)) {
			return false;
		}
		return this.appointments.add(appointment);
	}

	/**
	 * Checks if a specified appointment is in the appointment book
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param appointment the appointment to check for
	 * @return true if the AppointmentBook has the specified appointment returns 
	 */
	public boolean contains(Appointment appointment) {
		if (appointment == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_APPOINTMENT);
		}
		for (Appointment currAppointment : this.appointments) {
			if (currAppointment.equals(appointment)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clears the Appointment Book
	 * 
	 *@precondition none
	 *@postcondition size() = 0
	 */
	public void clear() {
		this.appointments.clear();
	}
}
