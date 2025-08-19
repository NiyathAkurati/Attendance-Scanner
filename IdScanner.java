import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The IdScanner class handles:
 * - Reading student ID numbers (from input or scanner).
 * - Matching ID numbers to student names.
 * - Switching the student's attendance status (present/absent).
 *
 * It continuously runs while the system is active.
 */
public class IdScanner {
	
	/* =====================
	   Class Variables
	===================== */
	private static String IdNum; // Stores the most recent scanned ID
	private static boolean codeRunning = true; // Keeps the scanner loop running
	private static String name;     // Full student name found by ID
	private static String prename;  // (Unused here, could be for first names?)
	private static ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>(); // Holds imported student data
	
	// Scanner object used to read the master student list file
	Scanner allStudentList;
	
	/**
	 * Constructor: Initializes the IdScanner.
	 * - Opens "ScannerNames.csv" (student list).
	 * - Starts a continuous loop:
	 *   - Reads an ID.
	 *   - Finds the corresponding name.
	 *   - Switches the student’s attendance if found.
	 */
	public IdScanner() {
		try {
			// Attempt to load student list file
			allStudentList = new Scanner(new File("ScannerNames.csv"));
		} catch(Exception e) {
			// File not found or unreadable
		}

		// Main loop: keep scanning IDs as long as program is running
		while(codeRunning) {
			name = getName(readId());	// Read ID → Match with student name
			
			try {
				// If the student exists in the Name list, toggle their attendance
				if (Name.getNames().indexOf(name) != -1) {
					NamePanel.switchSide(name);
				}	
			} catch(Exception e) {
				// Handles errors if name not found
			}
		}
	}

	/**
	 * Reads an ID number entered in the Display's text box.
	 * - Trims leading/trailing spaces.
	 * - Removes leading zeros (e.g., "00123" → "123").
	 * @return Cleaned ID number as String
	 */
	public String readId() {
		Scanner scan = new Scanner(System.in); // For manual console input (not heavily used)
		
		// Get the ID entered in the "sadness box" text field
		IdNum = Display.getSadnessBoxText().trim();
		
		// Remove leading zeros
		if (IdNum.length() >= 1) {
			while (IdNum.substring(0,1).equals("0")) {
				IdNum = IdNum.substring(1);
			}
		}
		return IdNum;
	}
	
	/**
	 * Matches an ID number to a student’s name using imported CSV data.
	 * @param IdNumber ID entered or scanned
	 * @return Full student name if found, otherwise "Name Not Found"
	 */
	public static String getName(String IdNumber) {
	    String IdNum = IdNumber;
	    try {
		    // Retrieve imported student data: each row = [Name, ID]
		    ArrayList<ArrayList<String>> data = Import.getImportData();

		    // Search the data for matching ID
		    for (int i = 0; i < data.size(); i++) {
		    	if(data.get(i).get(1).equals(IdNum)) {
		    		// Match found → return name
		    		return data.get(i).get(0);
		    	}
		    }
		    // No match found
		    return "Name Not Found";
	    } catch(Exception e) {
	    	// Error with data import → return not found
	    	return "Name Not Found";
	    }
	} 
}
