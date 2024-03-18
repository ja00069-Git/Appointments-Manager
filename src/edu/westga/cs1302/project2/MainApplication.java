package edu.westga.cs1302.project2;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;

/**
 * MainApplication extends the JavaFX Application class to build the GUI
 * and start program execution.
 * 
 * @author	CS1302
 * @version	Spring 2023
 */
public class MainApplication extends Application {
	private static final String WINDOW_TITLE = "Project 2 by Jabesi Abwe";
	private static final String GUI_FXML = "view/AppointmentGui.fxml";
	
	/**
	 * Constructs a new MainApplication object.
	 * 
	 * @precondition	none
	 * @postcondition	the object is ready to execute
	 */
	public MainApplication() {
		super();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = this.loadGui();
			Scene scene = new Scene(root);
			root.requestFocus();
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
		} catch (Exception theException) {
			theException.printStackTrace();
		}
	}
	
	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}
	
	/**
	 * Entry point into the application
	 * @param args	not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
