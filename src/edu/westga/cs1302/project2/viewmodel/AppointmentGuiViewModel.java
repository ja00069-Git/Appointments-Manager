package edu.westga.cs1302.project2.viewmodel;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import edu.westga.cs1302.project2.model.AppointmentBook;
import edu.westga.cs1302.project2.model.AppointmentType;
import edu.westga.cs1302.project2.controller.AppointmentBookController;
import edu.westga.cs1302.project2.datatier.TextFileLoader;
import edu.westga.cs1302.project2.datatier.TextFileWriter;
import edu.westga.cs1302.project2.datatier.TextLoader;
import edu.westga.cs1302.project2.datatier.TextUrlLoader;
import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.Validator;
import edu.westga.cs1302.project2.model.utilities.Utilities;
import edu.westga.cs1302.project2.resources.UI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The controller manages all the appointments.
 * 
 * @author CS1302
 * @version Spring 2023
 */
public class AppointmentGuiViewModel {

	/* stores all the appointments loaded or created */
	private AppointmentBook appointmentBook;
	private StringProperty descriptionProperty;
	private ObjectProperty<LocalDate> dateProperty;
	private StringProperty numberOfYearsProperty;
	private StringProperty priorityProperty;
	private StringProperty errorMessageProperty;

	/**
	 * stores the appointments to be shown in the listview (could be all the
	 * appointments or search result)
	 */
	private ObservableList<Appointment> appointmentList;

	/**
	 * Creates an instance of the ViewModel class.
	 * 
	 * @precondition: none
	 * @postcondition: All the needed fields are initialized.
	 */
	public AppointmentGuiViewModel() {
		this.appointmentBook = new AppointmentBook();
		this.descriptionProperty = new SimpleStringProperty("");

		this.dateProperty = new SimpleObjectProperty<LocalDate>();
		this.numberOfYearsProperty = new SimpleStringProperty("");
		this.priorityProperty = new SimpleStringProperty("");

		this.errorMessageProperty = new SimpleStringProperty("");
		this.appointmentList = FXCollections.observableArrayList();

	}

	/**
	 * Get the AppointmentBook object.
	 * 
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the AppointmentBook object.
	 */
	public AppointmentBook getAppointmentBook() {
		return this.appointmentBook;
	}

	/**
	 * Get the description property.
	 * 
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the string property
	 */
	public StringProperty descriptionProperty() {
		return this.descriptionProperty;
	}

	/**
	 * Get the date property.
	 * 
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the date property
	 */
	public ObjectProperty<LocalDate> dateProperty() {
		return this.dateProperty;
	}

	/**
	 * Get the numberOfYears property.
	 *
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the numberOfYears property
	 */
	public StringProperty numberOfYearsProperty() {
		return this.numberOfYearsProperty;
	}

	/**
	 * Get the priority property.
	 *
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the priority property
	 */
	public StringProperty priorityProperty() {
		return this.priorityProperty;
	}

	/**
	 * Get the errorMessage property.
	 *
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the errorMessage property
	 */
	public StringProperty errorMessageProperty() {
		return this.errorMessageProperty;
	}

	/**
	 * Get the appointmentListProperty.
	 *
	 * @precondition: none
	 * @postcondition: none
	 * 
	 * @return the appointmentListProperty
	 */
	public ObservableList<Appointment> appointmentListProperty() {
		return this.appointmentList;
	}

	/**
	 * add a new appointment object.
	 * 
	 * @param type the type of the Appointment to be added
	 * 
	 * @precondition: none
	 * @postcondition: this.appointmentListProperty().size() ==
	 *                 this.appointmentListProperty().size()@pre +1
	 * 
	 * @return true if the appointment is added successfully, false otherwise.
	 */
	public boolean addAppointment(String type) {

		Validator validator = new Validator();

		String description = validator.validateDescription(this.descriptionProperty.get());

		LocalDate date = validator.validateDate(this.dateProperty.get());

		type = validator.validateType(type);

		String numberOfYearsString = this.numberOfYearsProperty.get();
		String priorityString = this.priorityProperty.get();

		int numberOfYears = 0;
		int priority = 0;

		try {
			if (description == null || date == null) {
				this.errorMessageProperty.set(UI.ExceptionMessages.NULL_DESCRIPTION);
				return false;
			}
			if (type.equalsIgnoreCase(AppointmentType.YEARLY.name())) {
				validator.validateTextField(numberOfYearsString);
				numberOfYears = Integer.parseInt(numberOfYearsString);
			}
			if (type.equalsIgnoreCase(AppointmentType.SINGLE.name())) {
				validator.validateTextField(priorityString);
				priority = Integer.parseInt(priorityString);
			}

			Appointment appointment = Utilities.createAppointment(type, date, description, numberOfYears, priority);

			boolean added = this.appointmentBook.add(appointment);

			if (added) {
				this.appointmentList.setAll(this.appointmentBook.getAppointments());
				return true;
			} else {
				this.errorMessageProperty.set("The appointment was not added.");
				return false;
			}
		} catch (Exception ex) {
			this.errorMessageProperty.set(ex.getMessage());
			return false;
		}

	}

	/**
	 * Load the appointments from a file.
	 * 
	 * @param file the file from which appointments are to be loaded
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentBook is unchanged if the file is
	 *                 empty;otherwise, all the non-duplicate appointments of the
	 *                 file are added to the appointmentBook and the appointments
	 *                 are shown in the ListView.
	 * 
	 * @throws IOException
	 */
	public void loadFile(File file) throws IOException {
		TextFileLoader fileLoader = new TextFileLoader(file);
		this.setAppointmentList(fileLoader);
	}

	/**
	 * Uncomment the method header below Part 1 Note that the
	 * setAppointmentList method takes an Interface as its parameter. This allows us
	 * to pass in any object that implements the TextLoader to the method. This is
	 * where polymorphism works.
	 * 
	 * Call the loadData method of the fildLoader object to load the appointments
	 * from a file and pass the result to setAppointmentList
	 * 
	 * @param fileLoader the file loader
	 */
	private void setAppointmentList(TextLoader fileLoader) throws IOException {
		this.setAppointmentList(fileLoader.loadData());
	}

	/**
	 * Part 1 Go through the appointment list and add the appointment to the
	 * appointmentBook only if the appointment is not already present if the
	 * appointment is present, set "Duplicate appointment are not added!" in the
	 * errorMessageProperty Once the list is processed, clear the appointmentList
	 * and add all the appointmentBook's appointment to the appointmentList.
	 * 
	 * @param appointments the list of appointment
	 */
	private void setAppointmentList(List<Appointment> appointments) {
		for (Appointment appointment : appointments) {
			if (this.appointmentBook.contains(appointment)) {
				this.errorMessageProperty.set("Dplicate appointment are not added");
			} else {
				this.appointmentBook.add(appointment);
			}
		}
		this.appointmentList.clear();
		this.appointmentList.setAll(this.appointmentBook.getAppointments());
	}

	/**
	 * Part 2 Load the appointments from a URL.
	 * 
	 * @param url the url to the resource where the data is stored
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentBook.size = 0 if the resource at the specified
	 *                 url is empty; otherwise, appointmentBook.size = all the non-duplicate appointments
	 *                 added.
	 * @throws IOException
	 */
	public void loadUrl(URL url) throws IOException {
		TextUrlLoader fileLoader = new TextUrlLoader(url);
		this.setAppointmentList(fileLoader);
	}

	/**
	 * Part 1: Uncomment the following code once the AppointmentBook class is
	 * completed.
	 * 
	 * reload the appointmentBook appointments into the ListView
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentBook appointments are loaded into the ListView
	 * 
	 */
	public void reloadAppointments() {

		if (this.appointmentBook.size() != 0) {
			this.appointmentList.clear();
			this.appointmentList.setAll(this.appointmentBook.getAppointments());
		} else {
			this.errorMessageProperty.set(UI.ExceptionMessages.NO_APPOINTMENT_ERROR_MESSAGE);
		}
	}

	/**
	 * Part 1: Save the appointments in the ListView into a file represented by
	 * filename
	 * 
	 * @param file name of the file that the appointments are saved to.
	 * 
	 * @precondition: none
	 * @postcondition: file exists if there are appointments in the ListView
	 * 
	 * @throws IOException
	 * 
	 */
	public void saveFile(File file) throws IOException {
		if (!this.appointmentList.isEmpty()) {
			TextFileWriter writer = new TextFileWriter(file);
			writer.save((ArrayList<Appointment>) this.appointmentBook.getAppointments());
		}

	}

	/**
	 * Part 2: Save the appointments in the ListView in natural ordering in
	 * four groups (Daily, Monthly, Yearly, Single) in the format specified into a
	 * file represented by filename.
	 * 
	 * @param file name of the file that the appointments are saved to.
	 * 
	 * @precondition: none
	 * @postcondition: file exists if there are appointments in the ListView
	 * 
	 * @throws IOException
	 * 
	 */
	public void saveFile2(File file) throws IOException {
		if (!this.appointmentList.isEmpty()) {
			TextFileWriter writer = new TextFileWriter(file);
			writer.save2((ArrayList<Appointment>) this.appointmentBook.getAppointments());
		}
	}

	/**
	 * Part 3: Save the appointments in the ListView in natural ordering in four groups
	 * (Daily, Monthly, Yearly, Single) in the format specified into a file
	 * represented by filename.
	 * 
	 * @param file name of the file that the appointments are saved to.
	 * 
	 * @precondition: none
	 * @postcondition: file exists if there are appointments in the ListView
	 * 
	 * @throws IOException
	 * 
	 */
	public void saveFile3(File file) throws IOException {
		if (!this.appointmentList.isEmpty()) {
			TextFileWriter writer = new TextFileWriter(file);
			writer.save3((ArrayList<Appointment>) this.appointmentBook.getAppointments());
		}
	}

	/**
	 * Part 1: The method shows in the ListView all the appointments that
	 * occur on the given date or contain the given description or occur on the
	 * given date and contain the given description (if both fields are filled).
	 * 
	 * The user may pick a date in the datePicker, or enter a description, or pick a
	 * date and enter a description in the GUI.
	 * 
	 * The method looks for appointments in the appointmentBook that either occur on
	 * the date specified by the date that the user picked (if any) or have
	 * description that contains the phrase specified in the description (if any) or
	 * occur on the date specified by the user and have description that contains
	 * the phrase specified in the description. If the user doesn't pick a date, or
	 * enter a description, the method should set the errorMessageProperty to prompt
	 * the user "Please choose a date or enter a description, or both!"
	 * 
	 * if no appointments found satisfy the conditions, it sets the
	 * errorMessageProperty to iicate that. otherwise, it clears the appointmentList
	 * and add the found appointments to the appointmentList.
	 * 
	 * Create private helper methods when needed.
	 * 
	 * aDate stores the LocalDate in the date picker. description stores the
	 * description that user specified.
	 * 
	 * @precondition: none
	 * @postcondition: none
	 * 
	 */
	public void search() {

	    LocalDate aDate = this.dateProperty.get();
	    String description = this.descriptionProperty.get();
	    ArrayList<Appointment> found = new ArrayList<>();

	    if (aDate == null && description.isEmpty()) {
	        this.errorMessageProperty.set("Please choose a date or enter a description, or both!");
	    } else {
	        for (Appointment appointment : this.appointmentBook.getAppointments()) {
	            if (aDate != null && description.isEmpty() && appointment.getDate().equals(aDate)) {
	                found.add(appointment);
	            } else if (aDate == null && !description.isEmpty() && appointment.getDescription().contains(description)) {
	                found.add(appointment);
	            } else if (aDate != null && !description.isEmpty() && appointment.getDate().equals(aDate) && appointment.getDescription().contains(description)) {
	                found.add(appointment);
	            }
	        }
	        this.appointmentList.clear();
	        this.appointmentList.setAll(found);

	        if (found.size() == 0) {
	            this.errorMessageProperty.set("Appointment not found");
	        }
	    }
	}

	/**
	 * Part 1 Get the appointments in the appointmentBook and sort them in
	 * their natural ordering and display them in the ListView.
	 * 
	 * To make the appointments show up in the ListView, need to clear the
	 * appointmentList and add sorted appointments to the appointmentList.
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentList contains a list of appointments sorted in
	 *                 the specified order.
	 */
	public void sortByDate() {
		Collections.sort(this.appointmentBook.getAppointments(), new Comparator<Appointment>() {
			@Override
			public int compare(Appointment date1, Appointment date2) {

				return date1.getDate().compareTo(date2.getDate());
			}
		});

		this.appointmentList.clear();
		this.appointmentList.setAll(this.appointmentBook.getAppointments());
	}

	/**
	 * Part 2 Get the appointments in the appointmentBook and sort them by
	 * type in ascending order followed by date in descending order and display them
	 * in the ListView.
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentList contains a list of appointments sorted in
	 *                 the specified order.
	 * 
	 */
	public void sortByTypeDate() {

		this.isEmpty(this.appointmentBook.getAppointments());

		Collections.sort(this.appointmentBook.getAppointments(), new Comparator<Appointment>() {
			@Override
			public int compare(Appointment a1, Appointment a2) {
				int result = a1.getClass().getSimpleName().compareTo(a2.getClass().getSimpleName());
				if (result == 0) {
					return a2.getDate().compareTo(a1.getDate());
				}
				return result;
			}
		});

		this.appointmentList.clear();
		this.appointmentList.setAll(this.appointmentBook.getAppointments());
	}

	private boolean isEmpty(List<Appointment> appointments) {
		if (appointments.size() == 0) {
			this.errorMessageProperty.set(UI.ExceptionMessages.NO_APPOINTMENT_ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	/**
	 * Part 3 load random appointments
	 * 
	 * @precondition: none
	 * @postcondition: the appointmentList contains a list of random appointments
	 * 
	 */
	public void loadRandom() {
		AppointmentBookController controller = new AppointmentBookController();
		controller.generateRandomAppointments();
		List<Appointment> appointments = controller.getAppointmentBook().getAppointments();
		this.appointmentBook.getAppointments().addAll(appointments);
		this.appointmentList.addAll(appointments);
	}

	/**
	 * Clear the appointmentBook and the appointmentList.
	 * 
	 * @precondition: none
	 * @postcondition: appointmentBook and appointmentList are empty.
	 */
	public void clearAppointmentBookandListView() {
		this.appointmentBook.clear();
		this.appointmentList.clear();
	}

	/**
	 * Clear the appointmentList and populate it with a list of the earliest
	 * appointment form the 4 type (4 appoiments at most).
	 * 
	 * @precondition: none
	 * @postcondition: appointmentList hava size 4 or less.
	 */
	public void getEarliestAppointmentByType() {
		this.appointmentList.clear();
		this.appointmentList.addAll(this.listOfEarliestApt());

	}
	
	private ArrayList<Appointment> listOfEarliestApt() {
		ArrayList<Appointment> earliestAppointments = new ArrayList<>();

		for (Appointment appointment : this.appointmentBook.getAppointments()) {
			String type = appointment.getClass().getSimpleName();
			Appointment earliestAppointment = null;
			
			for (Appointment appt : earliestAppointments) {
				if (appt.getClass().getSimpleName().equals(type)) {
					earliestAppointment = appt;
					break;
				}
			}
			if (earliestAppointment == null || appointment.getDate().isBefore(earliestAppointment.getDate())) {
				earliestAppointments.remove(earliestAppointment);
				earliestAppointments.add(appointment);
			}
		}
		return earliestAppointments;
	}
}