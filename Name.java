import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

/**
 * The Name class manages the list of student names and creates
 * corresponding JButton objects to represent each student in the
 * attendance system.
 *
 * - Each student's name is turned into a clickable button.
 * - Buttons can be switched between the "Present" and "Absent" panels.
 * - This class ensures no duplicate buttons are created for the same student.
 */
public class Name {
    // Stores all student names
    private static ArrayList<String> names = new ArrayList<String>();

    // Stores JButton objects that represent student names
    private static ArrayList<JButton> ButtonNames = new ArrayList();

    // Panels where buttons will be displayed (present and absent lists)
    private static NamePanel present = Display.getPresentPanel();
    private static NamePanel absent = Display.getAbsentPanel();

    // Keeps track of names that already have buttons, to prevent duplicates
    private static ArrayList<String> buttonExclutionList = new ArrayList<String>();

    // Stores button height (used later for layout calculations)
    private static int buttonHeight;

    /**
     * Constructor: Takes an ArrayList of names and adds them to the master list.
     * Then creates buttons for each name.
     */
    public Name(ArrayList<String> n) {
        for (String s : n) {
            names.add(s);
        }
        this.createButtons();
    }

    /**
     * Creates JButtons for each student name (unless already excluded).
     * - Each button displays the student’s name.
     * - Clicking the button switches the student between present/absent panels.
     */
    public void createButtons() {
        boolean nameIsNotExcluded = true;

        for (int i = 0; i < names.size(); i++) {
            for (String s : buttonExclutionList) {
                if (s.equals(names.get(i))) {
                    nameIsNotExcluded = false; // Prevent duplicate button creation
                }
            }

            if (nameIsNotExcluded) {
                // Create a new button with the student's name
                JButton button = new JButton(names.get(i));

                // Capture the button’s height (for layout purposes)
                buttonHeight = button.getHeight();

                // Attach an action: switch the student between panels when clicked
                button.addActionListener(e -> NamePanel.switchSide(button.getText()));

                // Add button to the main button list
                ButtonNames.add(button);

                // Mark this name as already having a button
                buttonExclutionList.add(names.get(i));
            }
            nameIsNotExcluded = true; // Reset for next iteration
        }

        // Initially, all students are set to "absent"
        absent.setButtons(ButtonNames);

        // "Present" panel starts empty
        present.setButtons(new ArrayList<JButton>());
    }

    /**
     * Returns the list of JButton objects (each representing a student).
     */
    public ArrayList<JButton> getButtons() {
        return ButtonNames;
    }

    /**
     * Finds and returns a JButton by matching the student's name.
     * @param n The student's name
     * @return JButton corresponding to that student
     */
    public static JButton findButton(String n) {
        JButton button = null;
        for (int i = 0; i < ButtonNames.size(); i++) {
            if (ButtonNames.get(i).getText().equals(n)) {
                button = ButtonNames.get(i);
            }
        }
        return button;
    }

    /**
     * Returns the list of student names.
     */
    public static ArrayList<String> getNames() {
        return names;
    }

    /**
     * Calculates the total height of all buttons in a given panel.
     * Used for adjusting layouts when many buttons are present.
     * @param p The NamePanel to calculate height for
     * @return Total pixel height of all buttons in the panel
     */
    public static int getHeight(NamePanel p) {
        int frameHeight = 0;
        int count = p.getComponentCount();

        for (int i = 0; i < count; i++) {
            frameHeight += buttonHeight;
        }
        return frameHeight;
    }
}
