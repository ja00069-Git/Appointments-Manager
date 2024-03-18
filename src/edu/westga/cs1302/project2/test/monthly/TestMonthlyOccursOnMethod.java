package edu.westga.cs1302.project2.test.monthly;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Monthly;

public class TestMonthlyOccursOnMethod {

	@Test
	void testNullDate() {
		Monthly month = new Monthly("Some Appointment");

		assertThrows(IllegalArgumentException.class, () -> month.occursOn(null));
	}

	@Test
	void testDateDayIsOnInitialAppointmentDateDay() {
		LocalDate date = LocalDate.now();
		Monthly month = new Monthly("Some Appointment");

		assertTrue(month.occursOn(date));
	}

	@Test
	void testDateDayIsBeforInitialAppointmentDay() {
		LocalDate date = LocalDate.now().minusDays(1);
		Monthly month = new Monthly("Some Appointment");

		assertFalse(month.occursOn(date));
	}

	@Test
	void testDateDayIsAfterInitialAppointmentDay() {
		LocalDate date = LocalDate.now().plusDays(1);
		Monthly month = new Monthly("Some Appointment");

		assertFalse(month.occursOn(date));
	}
	
	@Test
	void testDateMonthIsAfterInitialAppointmentDay() {
		LocalDate date = LocalDate.now().plusMonths(1);
		Monthly month = new Monthly("Some Appointment");

		assertTrue(month.occursOn(date));
	}
	
	@Test 
	void testDateMonthIsBeforeInitialAppointmentDay() {
		LocalDate date = LocalDate.now().minusMonths(1);
		Monthly month = new Monthly("Some Appointment");

		assertFalse(month.occursOn(date));
	}

}
