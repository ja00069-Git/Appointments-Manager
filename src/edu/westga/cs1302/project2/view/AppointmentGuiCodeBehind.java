package edu.westga.cs1302.project2.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import edu.westga.cs1302.project2.model.Daily;
import edu.westga.cs1302.project2.model.Appointment;
import edu.westga.cs1302.project2.model.Monthly;
import edu.westga.cs1302.project2.model.Single;
import edu.westga.cs1302.project2.model.Yearly;
import edu.westga.cs1302.project2.resources.UI;
import edu.westga.cs1302.project2.viewmodel.AppointmentGuiViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * AppointmentGuiCodeBehind defines the "controller" for AppointmentGui.fxml.
 * 
 * @author CS1302
 * @version Spring 2023
 */
public class AppointmentGuiCodeBehind {

	private AppointmentGuiViewModel viewModel;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private Button addButton;

	@FXML
	private RadioButton dailyRadioButton;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton monthlyRadiobutton;

	@FXML
	private RadioButton singleRadioButton;

	@FXML
	private RadioButton yearlyRadioButton;

	@FXML
	private Button clearButton;

	@FXML
	private ListView<Appointment> appointmentListView;

	@FXML
	private Button searchButton;

	@FXML
	private Button reloadButton;

	@FXML
	private Button clearListButton;

	@FXML
	private DatePicker appointmentDate;

	@FXML
	private TextField numberOfYearsTextField;

	@FXML
	private TextField priorityTextField;

	@FXML
	private MenuItem loadFileMenuItem;

	@FXML
	private MenuItem loadWebMenuItem;

	@FXML
	private MenuItem loadRandomMenuItem;

	@FXML
	private MenuItem saveMenuItem;
	@FXML
	private MenuItem saveMenuItem2;
	@FXML
	private MenuItem saveMenuItem3;

	@FXML
	private MenuItem exitMenuItem;

	@FXML
	private MenuItem sortbyDateMenuItem;

	@FXML
	private MenuItem sortbyTypeDateMenuItem;

	@FXML
	private MenuItem earliestDateByTypeMenuItem;

	@FXML
	private Label errorMessageLabel;

	/**
	 * Create an AppointmentGuiController object.
	 */
	public AppointmentGuiCodeBehind() {

		this.viewModel = new AppointmentGuiViewModel();

	}

	/**
	 * Initializes the GUI components, binding them to the view model properties,
	 * setListener for ListView and error message.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {

		this.bindComponentsToViewModel();
		this.setupListenerForListView();
		this.setupListenerForErrorMessage();

	}

	private void bindComponentsToViewModel() {
		this.descriptionTextField.textProperty().bindBidirectional(this.viewModel.descriptionProperty());

		this.appointmentDate.valueProperty().bindBidirectional(this.viewModel.dateProperty());

		this.numberOfYearsTextField.textProperty().bindBidirectional(this.viewModel.numberOfYearsProperty());

		this.priorityTextField.textProperty().bindBidirectional(this.viewModel.priorityProperty());

		this.errorMessageLabel.textProperty().bindBidirectional(this.viewModel.errorMessageProperty());

	}

	private void setupListenerForErrorMessage() {
		this.appointmentDate.setOnAction(e -> this.errorMessageLabel.setText(""));

		this.descriptionTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> this.errorMessageLabel.setText(""));
	}

	private void setupListenerForListView() {
		this.appointmentListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldAppointment, newAppointment) -> {
					if (newAppointment != null) {

						this.descriptionTextField.textProperty().set(newAppointment.getDescription());
						this.appointmentDate.valueProperty().set(newAppointment.getDate());

						this.clearNumberOfYearstextfield();
						this.prioritytextfield();
						if (newAppointment instanceof Daily) {
							this.dailyRadioButton.setSelected(true);
						} else if (newAppointment instanceof Monthly) {
							this.monthlyRadiobutton.setSelected(true);
						} else if (newAppointment instanceof Single) {
							this.singleRadioButton.setSelected(true);
							int sigLevel = ((Single) newAppointment).getPriority();
							this.priorityTextField.textProperty().set(Integer.toString(sigLevel));
							} else if (newAppointment instanceof Yearly) {
							this.yearlyRadioButton.setSelected(true);
							int nyear = ((Yearly) newAppointment).getNumberOfYears();
							this.numberOfYearsTextField.textProperty().set(Integer.toString(nyear));
						} else {
							this.errorMessageLabel.setText("invalid appointment");
						}

					}
				});
	}

	@FXML
	private void onEarliestAppointmentByTypeClick(ActionEvent event) {
		 this.errorMessageLabel.setText("");
		 this.viewModel.getEarliestAppointmentByType();
		 this.displayAppointments();
	}

	@FXML
	private void onLoadRandomClick(ActionEvent event) {
		 this.errorMessageLabel.setText("");
		 this.viewModel.loadRandom();
		 this.displayAppointments();
	}

	@FXML
	private void onSortbyDateClick(ActionEvent event) {
		this.errorMessageLabel.setText("");
		this.viewModel.sortByDate();
		this.displayAppointments();
	}

	@FXML
	private void onSortbyTypeDateClick(ActionEvent event) {
		this.errorMessageLabel.setText("");
		this.viewModel.sortByTypeDate();
		this.displayAppointments();
	}

	@FXML
	private void onYearlyRadioClick(ActionEvent event) {
		this.numberOfYearsTextField.setDisable(false);
		this.prioritytextfield();
	}

	@FXML
	private void onMonthlyRadioClick(ActionEvent event) {
		this.clearNumberOfYearstextfield();
		this.prioritytextfield();
	}

	@FXML
	private void onDailyRadioClick(ActionEvent event) {
		this.clearNumberOfYearstextfield();
		this.prioritytextfield();
	}

	@FXML
	private void onSingleRadioClick(ActionEvent event) {
		this.priorityTextField.setDisable(false);
		this.clearNumberOfYearstextfield();
	}

	private void clearNumberOfYearstextfield() {
		this.numberOfYearsTextField.setText("");
		this.numberOfYearsTextField.setDisable(true);
	}

	private void prioritytextfield() {
		this.priorityTextField.setText("");
		this.priorityTextField.setDisable(true);
	}

	@FXML
	private void onAddButtonClick(ActionEvent event) {
		RadioButton selected = (RadioButton) this.group.getSelectedToggle();
		String type = selected.getText();
		if (this.viewModel.addAppointment(type)) {
			this.clear();
			this.displayAppointments();
		}

	}

	@FXML
	private void onClearButtonClick(ActionEvent event) {
		this.clear();

	}

	@FXML
	private void onClearListButtonClick(ActionEvent event) {
		this.clearList();

	}

	private void clearList() {
		this.viewModel.clearAppointmentBookandListView();

	}

	@FXML
	private void onSearchButtonClick(ActionEvent event) {

		this.viewModel.search();
		this.displayAppointments();

	}

	@FXML
	private void onReloadButtonClick(ActionEvent event) {
		this.clear();
		this.viewModel.reloadAppointments();

	}

	@FXML
	private void onSaveClick(ActionEvent event) {
		 if (this.viewModel.getAppointmentBook().size() == 0) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.NO_APPOINTMENT_WHILE_SAVING);
		 return;
		 }
		
		 FileChooser chooser = this.initializeFileChooser("Output file");
		
		 File outputFile = chooser.showSaveDialog(null);
		 if (outputFile == null) {
		 return;
		 }
		 try {
		
		 this.viewModel.saveFile(outputFile);
		 } catch (IOException saveException) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.SAVE_DATA_ERROR_MESSAGE);
		 } catch (Exception ex) {
		 this.showErrorDialog("Error!", ex.getMessage());
		
		 }
	}

	@FXML
	private void onSave2Click(ActionEvent event) {

		 if (this.viewModel.getAppointmentBook().size() == 0) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.NO_APPOINTMENT_WHILE_SAVING);
		 return;
		 }
		
		 FileChooser chooser = this.initializeFileChooser("Output file");
		
		 File outputFile = chooser.showSaveDialog(null);
		 if (outputFile == null) {
		 return;
		 }
		 try {
		
		 this.viewModel.saveFile2(outputFile);
		 } catch (IOException saveException) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.SAVE_DATA_ERROR_MESSAGE);
		 } catch (Exception ex) {
		 this.showErrorDialog("Error!", ex.getMessage());
		
		 }

	}

	@FXML
	private void onSave3Click(ActionEvent event) {
		
		 if (this.viewModel.getAppointmentBook().size() == 0) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.NO_APPOINTMENT_WHILE_SAVING);
		 return;
		 }
		
		 FileChooser chooser = this.initializeFileChooser("Output file");
		
		 File outputFile = chooser.showSaveDialog(null);
		 if (outputFile == null) {
		 return;
		 }
		
		 try {
		
		 this.viewModel.saveFile3(outputFile);
		 } catch (IOException saveException) {
		 this.showErrorDialog(UI.ExceptionMessages.SAVE_DIALOG_ERROR_TITLE,
		 UI.ExceptionMessages.SAVE_DATA_ERROR_MESSAGE);
		 } catch (Exception ex) {
		 this.showErrorDialog("Error!", ex.getMessage());
		
		 }
	}

	@FXML
	private void onLoadWebClick(ActionEvent event) {
		this.clear();
		this.handleUrlLoad();
	}

	@FXML
	private void onLoadFileClick(ActionEvent event) {
		this.clear();
		this.handleFileLoad();
	}

	@FXML
	private void onExitCick(ActionEvent event) {
		Platform.exit();
	}

	private void showErrorDialog(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("Error!");
		alert.setContentText(message);

		alert.showAndWait();

	}

	private void displayAppointments() {
		if (this.viewModel.appointmentListProperty() != null && this.viewModel.appointmentListProperty().size() != 0) {
			this.appointmentListView.setItems(this.viewModel.appointmentListProperty());
		}
	}

	private void handleUrlLoad() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(UI.ExceptionMessages.TEXT_INPUT_TITLE);
		dialog.setContentText(UI.ExceptionMessages.ENTER_URL);

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(urlString -> this.loadUrl(urlString));

	}

	private void loadUrl(String urlString) {
		try {
			this.viewModel.loadUrl(new URL(urlString));

			this.displayAppointments();

		} catch (MalformedURLException urlException) {
			this.showErrorDialog(UI.ExceptionMessages.LOAD_DIALOG_ERROR_TITLE,
					UI.ExceptionMessages.INVALID_URL_MESSAGE);

		} catch (IOException readException) {
			this.showErrorDialog(UI.ExceptionMessages.LOAD_DIALOG_ERROR_TITLE,
					UI.ExceptionMessages.READ_DATA_ERROR_MESSAGE);

		}
	}

	private void handleFileLoad() {
		FileChooser chooser = this.initializeFileChooser("Input file");

		File inputFile = chooser.showOpenDialog(null);
		if (inputFile == null) {
			return;
		}
		try {
			this.viewModel.loadFile(inputFile);
			this.displayAppointments();
		} catch (IOException readException) {
			this.showErrorDialog(UI.ExceptionMessages.LOAD_DIALOG_ERROR_TITLE,
					UI.ExceptionMessages.READ_DATA_ERROR_MESSAGE);
		} catch (Exception ex) {
			this.showErrorDialog("Error", ex.getMessage());
		}

	}

	private FileChooser initializeFileChooser(String title) {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new ExtensionFilter("txt Files", "*.txt"));
		chooser.setTitle(title);
		return chooser;
	}

	private void clear() {
		this.descriptionTextField.setText("");
		this.appointmentDate.setValue(null);
		this.priorityTextField.setText("");
		this.numberOfYearsTextField.setText("");
		this.errorMessageLabel.setText("");
		this.dailyRadioButton.setSelected(true);
		this.priorityTextField.setDisable(true);
		this.numberOfYearsTextField.setDisable(true);
	}

}
