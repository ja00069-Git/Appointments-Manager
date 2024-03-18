package edu.westga.cs1302.project2.test.yearly;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Yearly;

public class TestYearlyOccursOnMethod {

	@Test
	void testNullDate() {
		Yearly aYearlyApt = new Yearly("Some Appointment", 1);

		assertThrows(IllegalArgumentException.class, () -> aYearlyApt.occursOn(null));
	}

	@Test
	void testOccursOnYearlyAppointmentWithNumberOfYears1() {
		Yearly aYearlyApt = new Yearly(LocalDate.now(), "Some appointment", 1);
		assertTrue(aYearlyApt.occursOn(LocalDate.now()));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(1)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(2)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(3)));
	}

	@Test
	void testOccursOnYearlyAppointmentWithNumberOfYears2() {
		Yearly aYearlyApt = new Yearly(LocalDate.now(), "Some appointment", 2);
		assertTrue(aYearlyApt.occursOn(LocalDate.now()));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(1)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(2)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(3)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(4)));
	}

	@Test
	void testOccursOnYearlyAppointmentWithNumberOfYears3() {
		Yearly aYearlyApt = new Yearly(LocalDate.now(), "Some appointment", 3);
		assertTrue(aYearlyApt.occursOn(LocalDate.now()));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(1)));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(2)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(3)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(4)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(5)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(6)));
	}

	@Test
	void testOccursOnYearlyAppointmentWithNumberOfYears4() {
		Yearly aYearlyApt = new Yearly(LocalDate.now(), "Some appointment", 4);
		assertTrue(aYearlyApt.occursOn(LocalDate.now()));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(1)));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(2)));
		assertTrue(aYearlyApt.occursOn(LocalDate.now().plusYears(3)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(4)));
		assertFalse(aYearlyApt.occursOn(LocalDate.now().plusYears(5)));		
	}

}
