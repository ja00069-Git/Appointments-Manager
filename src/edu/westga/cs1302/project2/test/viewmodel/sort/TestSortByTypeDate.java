package edu.westga.cs1302.project2.test.viewmodel.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.AppointmentBook;
import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.model.Yearly;
import edu.westga.cs1302.project2.viewmodel.AppointmentGuiViewModel;

public class TestSortByTypeDate {

	@Test
	public void testSortByTypeAndDate() {
		AppointmentGuiViewModel viewModel = new AppointmentGuiViewModel();
		AppointmentBook book = viewModel.getAppointmentBook();

		Appointment a1 = new Daily(LocalDate.now(), "Daily 1");
		Appointment a2 = new Daily(LocalDate.now().plusDays(1), "Daily 2");
		Appointment a3 = new Daily(LocalDate.now().plusDays(2), "Daily 3");
		Appointment a4 = new Monthly(LocalDate.now(), "Monthly 1");
		Appointment a5 = new Monthly(LocalDate.now().plusMonths(1), "Monthly 2");
		Appointment a6 = new Monthly(LocalDate.now().plusMonths(2), "Monthly 3");
		Appointment a7 = new Single(LocalDate.now(), "Single 1", 1);
		Appointment a8 = new Single(LocalDate.now().plusDays(1), "Single 2", 2);
		Appointment a9 = new Single(LocalDate.now().plusDays(2), "Single 3", 3);
		Appointment a10 = new Yearly(LocalDate.now(), "Yearly 1", 1);
		Appointment a11 = new Yearly(LocalDate.now().plusYears(1), "Yearly 2", 2);
		Appointment a12 = new Yearly(LocalDate.now().plusYears(2), "Yearly 3", 3);

		book.add(a1);
		book.add(a2);
		book.add(a3);
		book.add(a4);
		book.add(a5);
		book.add(a6);
		book.add(a7);
		book.add(a8);
		book.add(a9);
		book.add(a10);
		book.add(a11);
		book.add(a12);

		viewModel.sortByTypeDate();

		assertEquals(a3, book.getAppointments().get(0));
		assertEquals(a2, book.getAppointments().get(1));
		assertEquals(a1, book.getAppointments().get(2));
		assertEquals(a6, book.getAppointments().get(3));
		assertEquals(a5, book.getAppointments().get(4));
		assertEquals(a4, book.getAppointments().get(5));
		assertEquals(a9, book.getAppointments().get(6));
		assertEquals(a8, book.getAppointments().get(7));
		assertEquals(a7, book.getAppointments().get(8));
		assertEquals(a12, book.getAppointments().get(9));
		assertEquals(a11, book.getAppointments().get(10));
		assertEquals(a10, book.getAppointments().get(11));
	}
}
