package edu.westga.cs1302.project2.test.single;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Single;

public class TesSingletOccursOnMethod {
	
	@Test
	void testNullDate() {
		Single single = new Single("Some Appointment",1);

		assertThrows(IllegalArgumentException.class, () -> single.occursOn(null));
	}

	@Test
	void testDateDayIsOnInitialAppointmentDateDay() {
		LocalDate date = LocalDate.now();
		Single single = new Single("Some Appointment",2);

		assertTrue(single.occursOn(date));
	}

	@Test
	void testDateDayIsBeforInitialAppointmentDay() {
		LocalDate date = LocalDate.now().minusDays(1);
		Single single = new Single("Some Appointment",3);

		assertFalse(single.occursOn(date));
	}

	@Test
	void testDateDayIsAfterInitialAppointmentDay() {
		LocalDate date = LocalDate.now().plusDays(1);
		Single single = new Single("Some Appointment",4);
		
		assertFalse(single.occursOn(date));
	}

}
