package edu.westga.cs1302.project2.datatier;

import java.io.IOException;
import java.net.URL;
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
 * Loads data from an URL
 * @author jabes
 * @version Spring 2023
 */
public class TextUrlLoader implements TextLoader {

	private URL url;
	
	/**
	 * Instantiates a new text Url loader.
	 *
	 * @precondition none
	 * @postcondition none
	 * @param url the url
	 */
	public TextUrlLoader(URL url) {
		this.url = url;
	}

	@Override
	public List<Appointment> loadData() throws IOException {
		ArrayList<Appointment> appointments = new ArrayList<>();

		try (Scanner scanner = new Scanner(this.url.openStream())) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				try {
					this.createAptFromRedLinesByAptType(appointments, line);
				} catch (Exception ex) {
					System.err.println(
							"Error reading file: invalid " + line.replace(UI.Text.FIELD_SEPERATOR, UI.Text.SPACE));
				}
			}
		}
		return appointments;
	}

	private void createAptFromRedLinesByAptType(ArrayList<Appointment> appointments, String line) {
		String[] lineArray = line.split(UI.Text.SPACE);
		String type = lineArray[0];

		if (type.equalsIgnoreCase(AppointmentType.DAILY.name())) {
			LocalDate date = LocalDate.parse(lineArray[1]);
			String description = this.getDescriptionFromLineArray(lineArray, 2);
			appointments.add(new Daily(date, description));
		} else if (type.equalsIgnoreCase(AppointmentType.MONTHLY.name())) {
			LocalDate date = LocalDate.parse(lineArray[1]);
			String description = this.getDescriptionFromLineArray(lineArray, 2);
			appointments.add(new Monthly(date, description));
		} else if (type.equalsIgnoreCase(AppointmentType.SINGLE.name())) {
			int priority = Integer.parseInt(lineArray[1]);
			LocalDate date = LocalDate.parse(lineArray[2]);
			String description = this.getDescriptionFromLineArray(lineArray, 3);
			appointments.add(new Single(date, description, priority));
		} else if (type.equalsIgnoreCase(AppointmentType.YEARLY.name())) {
			int numberOfYears = Integer.parseInt(lineArray[1]);
			LocalDate date = LocalDate.parse(lineArray[2]);
			String description = this.getDescriptionFromLineArray(lineArray, 3);
			appointments.add(new Yearly(date, description, numberOfYears));
		} else {
			System.err.println(line);
		}
	}

	private String getDescriptionFromLineArray(String[] lineArray, int startIndex) {
		StringBuilder sb = new StringBuilder();
		for (int index = startIndex; index < lineArray.length; index++) {
			sb.append(lineArray[index]);
			if (index < lineArray.length - 1) {
				sb.append(UI.Text.SPACE);
			}
		}
		return sb.toString();
	}
}
