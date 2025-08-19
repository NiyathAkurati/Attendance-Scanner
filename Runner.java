// The Runner class is the entry point of the Attendance Scanner project.
// It initializes the GUI (Display) and the ID scanner system.
public class Runner {
    
    // A reference to the GUI (Display) object that will handle user interaction.
    private static Display gui;

    // The main method: the starting point of the Java program.
    public static void main(String[] args) {
        
        // Create and initialize the GUI for the attendance scanner.
        gui = new Display();
        
        // Create and initialize the ID scanner system.
        // This will likely handle scanning ID cards for attendance.
        IdScanner scan = new IdScanner();
    }
}
