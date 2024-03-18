package edu.westga.cs1302.project2.datatier;

import java.io.IOException;
import java.util.List;

import edu.westga.cs1302.project2.model.Appointment;

/**
 * Text loader interface
 * 
 * @author jabes
 * @version Spring 2023
 */
public interface TextLoader {

	/**
	 * Load text data
	 * @preconditon none
	 * @postcondition none
	 * @return List of loaded data
	 */
	List<Appointment> loadData() throws IOException;
}
