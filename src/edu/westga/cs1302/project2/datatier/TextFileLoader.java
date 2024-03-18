package edu.westga.cs1302.project2.datatier;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.AppointmentType;
import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.model.Yearly;
import edu.westga.cs1302.project2.resources.UI;

/**
 * Loads data from a file
 * 
 * @author jabes
 * @version Spring 2023
 */
public class TextFileLoader implements TextLoader {

	private File file;

	/**
	 * Instantiates a new text file loader.
	 *
	 * @precondition none
	 * @postcondition none
	 * @param file the file
	 */
	public TextFileLoader(File file) {
		this.file = file;
	}

	@Override
	public List<Appointment> loadData() throws IOException {
		ArrayList<Appointment> appointments = new ArrayList<>();

		try (Scanner scanner = new Scanner(this.file)) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				try {
					this.creatAptFromReadLinesByAptType(appointments, line);
				} catch (Exception ex) {
					System.err.println(
							"Error reading file: invalid " + line.replace(UI.Text.FIELD_SEPERATOR, UI.Text.SPACE));
				}
			}
		}
		return appointments;
	}

	private void creatAptFromReadLinesByAptType(ArrayList<Appointment> appointments, String line) {
		String[] lineArray = line.split(UI.Text.FIELD_SEPERATOR);
		String type = lineArray[0];

		if (type.equalsIgnoreCase(AppointmentType.DAILY.name())) {
			LocalDate date = LocalDate.parse(lineArray[1]);
			String description = lineArray[2];
			appointments.add(new Daily(date, description));
		} else if (type.equalsIgnoreCase(AppointmentType.MONTHLY.name())) {
			LocalDate date = LocalDate.parse(lineArray[1]);
			String description = lineArray[2];
			appointments.add(new Monthly(date, description));
		} else if (type.equalsIgnoreCase(AppointmentType.SINGLE.name())) {
			int priority = Integer.parseInt(lineArray[1]);
			LocalDate date = LocalDate.parse(lineArray[2]);
			String description = lineArray[3];
			appointments.add(new Single(date, description, priority));
		} else if (type.equalsIgnoreCase(AppointmentType.YEARLY.name())) {
			int numberOfYears = Integer.parseInt(lineArray[1]);
			LocalDate date = LocalDate.parse(lineArray[2]);
			String description = lineArray[3];
			appointments.add(new Yearly(date, description, numberOfYears));
		} else {
			System.err.println(line);
		}
	}

}
