import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 * The Import class handles loading student data from a CSV file.
 * It builds two structures:
 *   - FullArrayList: stores [name, ID] for each student
 *   - justTheNames:  stores only the student names (for buttons)
 */
public class Import {
    // Stores all names + IDs from the loaded file
    private static ArrayList<ArrayList<String>> FullArrayList = new ArrayList<ArrayList<String>>();

    // Panel used for displaying the file chooser
    private static JFrame fileChooserPanel = new JFrame("panel");

    // Stores just the student names
    private static ArrayList<String> justTheNames = new ArrayList<String>();

    /**
     * Opens a file chooser, filters for CSV files, reads the data,
     * and populates both FullArrayList and justTheNames.
     *
     * @return ArrayList<ArrayList<String>> with [name, ID] pairs
     */
    public static ArrayList<ArrayList<String>> load() {
        String loadedClassName = "default";

        // Create file chooser dialog
        final JFileChooser fc = new JFileChooser();

        /* ----------------------
           File filter: only .csv allowed
        ---------------------- */
        FileFilter filter = new FileFilter() {  
            public boolean accept(File file) {     
                return file.getName().toLowerCase().endsWith(".csv"); // only CSV
            }

            @Override
            public String getDescription() {
                return "CSV files only";
            }
        };

        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);

        // Show the dialog on the hidden JFrame
        fc.showOpenDialog(fileChooserPanel);

        try {
            justTheNames = new ArrayList<String>(); 

            // Open the selected CSV file
            Scanner reader = new Scanner(fc.getSelectedFile());

            /* ----------------------
               Read each line of CSV
            ---------------------- */
            while(reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(","); // split by commas

                // Extract first name field, trimming quotes
                String x = temp[2].substring(1, temp[2].length()).trim();
                int y = x.indexOf("\"");
                x = x.substring(0,y).trim()+ " " +x.substring(y+1).trim();

                // Add last name
                x = x + temp[1].substring(1).trim(); 

                // Extract ID
                String z = temp[3].substring(1);

                // Add to storage lists
                justTheNames.add(x);
                FullArrayList.add(new ArrayList<String>(Arrays.asList(x, z)));
            }

            // Create Name buttons with just names
            Name importedNames = new Name(justTheNames);
        
        } catch(Exception e) {
            System.out.println("Unable to Read File"); // error reading file
        }

        // Return the dataset
        return FullArrayList;
    }   

    /**
     * Provides access to the loaded student data.
     * @return ArrayList<[Name, ID]>
     */
    public static ArrayList<ArrayList<String>> getImportData(){
        return FullArrayList;
    }
}
