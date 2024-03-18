package edu.westga.cs1302.project2.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.AppointmentType;
import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.model.Yearly;
import edu.westga.cs1302.project2.resources.UI;

/**
 * Saves the appointments to a file
 * 
 * @author jabes
 * @version Spring 2023
 */
public class TextFileWriter {

	private File file;

	/**
	 * Instantiates a new test file writer.
	 *
	 * @precondition tfile != null
	 * @postcondition none
	 * @param file the file to write to
	 */
	public TextFileWriter(File file) {
		if (file == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_FILE);
		}
		this.file = file;
	}

	/**
	 * Saves appointments to a cvs file
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param appointments the appointments to save
	 * @throws IOException
	 */
	public void save(ArrayList<Appointment> appointments) throws IOException, FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(this.file)) {
			for (Appointment appointment : appointments) {
				writer.print(appointment.getDate());
				writer.print(", ");
				writer.print(appointment.getClass().getSimpleName().toUpperCase());
				writer.print(", ");
				writer.print(appointment.getDescription() + System.lineSeparator());
			}
		}
	}

	/**
	 * Saves appointments to a cvs file by writing every type and it's appointment
	 * 
	 * @precondition
	 * @postcondition
	 * @param appointments the appointments to save
	 * @throws IOException
	 */
	public void save2(ArrayList<Appointment> appointments) throws IOException, FileNotFoundException {

		try (PrintWriter writer = new PrintWriter(this.file)) {
			List<Daily> dailyAppointments = new ArrayList<>();
			List<Monthly> monthlyAppointments = new ArrayList<>();
			List<Yearly> yearlyAppointments = new ArrayList<>();
			List<Single> singleAppointments = new ArrayList<>();

			appointments.sort(Comparator.comparing(Appointment::getDate));

			for (Appointment appointment : appointments) {
				if (appointment instanceof Daily) {
					dailyAppointments.add((Daily) appointment);
				} else if (appointment instanceof Monthly) {
					monthlyAppointments.add((Monthly) appointment);
				} else if (appointment instanceof Yearly) {
					yearlyAppointments.add((Yearly) appointment);
				} else if (appointment instanceof Single) {
					singleAppointments.add((Single) appointment);
				}
			}

			this.saveAppointments(writer, AppointmentType.DAILY.name(), dailyAppointments);
			this.saveAppointments(writer, AppointmentType.MONTHLY.name(), monthlyAppointments);
			this.saveAppointments(writer, AppointmentType.YEARLY.name(), yearlyAppointments);
			this.saveAppointments(writer, AppointmentType.SINGLE.name(), singleAppointments);
		}
	}

	private void saveAppointments(PrintWriter writer, String type, List<? extends Appointment> appointments) {
		writer.println(type + ", size = " + appointments.size());
		for (Appointment appointment : appointments) {
			writer.print(UI.Text.SPACE);
			writer.print(UI.Text.SPACE);
			writer.print(UI.Text.SPACE);
			writer.print(UI.Text.SPACE);
			writer.print(appointment.getDate());
			writer.print(", ");
			writer.print(appointment.getDescription());
			writer.println();
		}
		writer.println();
	}

	/**
	 * Saves appointments to a cvs file by grouping all that occurs on a particular
	 * date in all the types
	 * 
	 * @precondition
	 * @postcondition
	 * @param appointments the appointments to save
	 * @throws IOException
	 */
	public void save3(ArrayList<Appointment> appointments) throws IOException, FileNotFoundException {

		try (PrintWriter writer = new PrintWriter(this.file);) {
			Map<LocalDate, ArrayList<Appointment>> appointmentsByDate = new HashMap<>();

			for (Appointment appointment : appointments) {

				LocalDate date = appointment.getDate();
				ArrayList<Appointment> appointmentsOnDate = appointmentsByDate.get(date);

				if (appointmentsOnDate == null) {
					appointmentsOnDate = new ArrayList<>();
					appointmentsByDate.put(date, appointmentsOnDate);
				}
				appointmentsOnDate.add(appointment);
			}
			for (LocalDate date : appointmentsByDate.keySet()) {
				
				writer.println("Appointments on " + date);
				ArrayList<Appointment> appointmentsOnDate = appointmentsByDate.get(date);
				
				for (int index = 0; index < appointmentsOnDate.size(); index++) {
					writer.println(
							"Appointment " + (index + 1) + ": " + appointmentsOnDate.get(index).getDescription());
				}
				this.groupAppoimnets(writer, appointmentsOnDate);
			}
		}
	}

	private void groupAppoimnets(PrintWriter writer, ArrayList<Appointment> appointmentsOnDate) {
		
		writer.println("~~~GROUPED BY TYPE~~~");
		
		int dailyCount = 0;
		int monthlyCount = 0;
		int yearlyCount = 0;
		int singleCount = 0;
		
		for (Appointment appointment : appointmentsOnDate) {
			if (appointment instanceof Daily) {
				dailyCount++;
			} else if (appointment instanceof Monthly) {
				monthlyCount++;
			} else if (appointment instanceof Yearly) {
				yearlyCount++;
			} else if (appointment instanceof Single) {
				singleCount++;
			}
		}
		writer.print(UI.Text.FOUR_SPACE);
		writer.println(AppointmentType.DAILY.toString() + ", size = " + dailyCount);
		
		for (Appointment appointment : appointmentsOnDate) {
			if (appointment instanceof Daily) {
				writer.print(UI.Text.EIGHT_SPACE);
				writer.println(appointment.getDate() + ", " + appointment.getDescription());
			}
		}
		writer.print(UI.Text.FOUR_SPACE);
		writer.println(AppointmentType.MONTHLY.toString() + ", size = " + monthlyCount);
		
		for (Appointment appointment : appointmentsOnDate) {
			if (appointment instanceof Monthly) {
				writer.print(UI.Text.EIGHT_SPACE);
				writer.println(appointment.getDate() + ", " + appointment.getDescription());
			}
		}
		writer.print(UI.Text.FOUR_SPACE);
		writer.println(AppointmentType.YEARLY.toString() + ", size = " + yearlyCount);
		
		for (Appointment appointment : appointmentsOnDate) {
			if (appointment instanceof Yearly) {
				writer.print(UI.Text.EIGHT_SPACE);
				writer.println(appointment.getDate() + ", " + appointment.getDescription());
			}
		}
		writer.print(UI.Text.FOUR_SPACE);
		writer.println(AppointmentType.SINGLE.toString() + ", size = " + singleCount);
		
		for (Appointment appointment : appointmentsOnDate) {
			if (appointment instanceof Single) {
				writer.print(UI.Text.EIGHT_SPACE);

				writer.println(appointment.getDate() + ", " + appointment.getDescription());
			}
		}
		writer.println();
	}
}
