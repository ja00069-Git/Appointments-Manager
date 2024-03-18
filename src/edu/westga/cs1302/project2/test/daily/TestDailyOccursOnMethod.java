package edu.westga.cs1302.project2.test.daily;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Daily;

public class TestDailyOccursOnMethod {

	@Test
	void testNullDate() {
		Daily daily = new Daily("Some Appointment");
		assertThrows(IllegalArgumentException.class, () -> daily.occursOn(null));
	}

	@Test
	void testDateIsOnInitialAppointment() {
		LocalDate initialDate = LocalDate.now();
		Daily daily = new Daily("Some Appointment");

		assertTrue(daily.occursOn(initialDate));
	}

	@Test
	void testDateIsAfterInitalAppointment() {
		LocalDate tommorow = LocalDate.now().plusDays(1);
		Daily daily = new Daily("Some Appointment");

		assertTrue(daily.occursOn(tommorow));
	}

	@Test
	void testIsBeforeInitialAppointment() {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		Daily daily = new Daily("Some Appointment");

		assertFalse(daily.occursOn(yesterday));
	}

}
