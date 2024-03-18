package edu.westga.cs1302.project2.controller;

import java.time.LocalDate;
import java.util.Random;

import edu.westga.cs1302.project2.model.AppointmentBook;
import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.model.Yearly;

/**
 * AppointmentBookController generates random appointments and gets appointments
 * grouped by types.
 * 
 * @author CS1302
 * @version Fall 2021
 */
public class AppointmentBookController {

	private static String[] descriptions = { "dental appointment", "car maintenance", "dance lessons",
			"drawing lessongs", "birthday party", "grocery shopping" };

	private AppointmentBook appointmentBook;

	/**
	 * Instantiates a new appointment book controller.
	 *
	 * @precondition: none
	 * @postcondition: getAppointmentBook().size()==0
	 */
	public AppointmentBookController() {
		this.appointmentBook = new AppointmentBook();
	}

	/**
	 * Part 3 TODO: Generate random appointments and add them into the
	 * appointmentBook.
	 * 
	 * Randomly generate a number between 20 and 30. Create that number of Single,
	 * Monthly, Daily, and Yearly appointments
	 * 
	 * For each appointment, the date should be a random number within 0-364 days
	 * after today, the description should be any random description in the
	 * descriptions array, the nmberOfYears should be a random number b/w 1 to the
	 * length of the descriptions array, the priority should be a random number b/w
	 * 1 - 10.
	 * 
	 * @precondition: none
	 * @postcondition: appointmentBook has 20-30 randomly generated appointments of
	 *                 each type
	 * 
	 */
	public void generateRandomAppointments() {
		Random rand = new Random();
		LocalDate today = LocalDate.now();
		int numSingle = rand.nextInt(31);
		int numMonthly = rand.nextInt(31);
		int numDaily = rand.nextInt(31); 
		int numYearly = rand.nextInt(31); 

		for (int index = 0; index < numSingle; index++) {
			int daysToAdd = rand.nextInt(365);
			LocalDate date = today.plusDays(daysToAdd);
			String description = descriptions[rand.nextInt(descriptions.length)];
			int priority = rand.nextInt(descriptions.length) + 1;
			Single appointment = new Single(date, description, priority);
			this.appointmentBook.add(appointment);
		}

		for (int index = 0; index < numMonthly; index++) {
			int daysToAdd = rand.nextInt(365);
			LocalDate date = today.plusDays(daysToAdd);
			String description = descriptions[rand.nextInt(descriptions.length)];
			Monthly appointment = new Monthly(date, description);
			this.appointmentBook.add(appointment);
		}

		for (int index = 0; index < numDaily; index++) {
			int daysToAdd = rand.nextInt(365);
			LocalDate date = today.plusDays(daysToAdd);
			String description = descriptions[rand.nextInt(descriptions.length)];
			Daily appointment = new Daily(date, description);
			this.appointmentBook.add(appointment);
		}

		for (int index = 0; index < numYearly; index++) {
			int daysToAdd = rand.nextInt(365);
			LocalDate date = today.plusDays(daysToAdd);
			String description = descriptions[rand.nextInt(descriptions.length)];
			int numberOfYears = rand.nextInt(descriptions.length) + 1;
			Yearly appointment = new Yearly(date, description, numberOfYears);
			this.appointmentBook.add(appointment);
		}

	}

	/**
	 * Gets the appointment book.
	 * 
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the appointment book
	 */
	public AppointmentBook getAppointmentBook() {
		return this.appointmentBook;
	}

}
