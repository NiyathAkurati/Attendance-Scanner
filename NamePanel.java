import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * NamePanel represents one of the two panels (Present or Absent).
 * It displays student name buttons and provides functionality
 * to move buttons between panels.
 */
public class NamePanel extends JPanel {

    // Stores all the buttons currently in this panel
    private ArrayList<JButton> nameButtons = new ArrayList<>();

    /**
     * Constructor: sets layout and initializes panel.
     */
    public NamePanel() {
        setBorder(getBorder()); // optional — could be customized
        nameButtons = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Replaces all buttons with a new set.
     * @param buttons list of buttons to display
     */
    public void setButtons(ArrayList<JButton> buttons) {
        removeAll();
        nameButtons.clear();
        for (JButton b : buttons) {
            addButton(b);
        }
    }

    /**
     * Adds a single button to the panel.
     * @param button student button
     */
    public void addButton(JButton button) {
        styleButton(button);
        nameButtons.add(button);
        add(button);
        revalidate();
        repaint();
    }

    /**
     * Removes a button from the panel.
     * @param button student button
     */
    public void removeButton(JButton button) {
        nameButtons.remove(button);
        remove(button);
        revalidate();
        repaint();
    }

    /**
     * Applies consistent styling to all student buttons.
     */
    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 30));
        button.setMaximumSize(new Dimension(200, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Adjusts the panel height dynamically to fit the buttons.
     */
    @Override
    public Dimension getPreferredSize() {
        // Minimum height = 400px, otherwise grows by 35px per button
        int h = Math.max(400, nameButtons.size() * 35);
        return new Dimension(288, h);
    }

    /**
     * Switches a student (button) from one panel to the other.
     * @param name student name
     */
    public static void switchSide(String name) {
        Display.clearSadnessBox();
        JButton b = Name.findButton(name);
        if (b == null) return;

        NamePanel from, to;

        // Determine which panel currently has the button
        if (foundButton(Display.getPresentPanel(), b)) {
            from = Display.getPresentPanel();
            to   = Display.getAbsentPanel();
        } else if (foundButton(Display.getAbsentPanel(), b)) {
            from = Display.getAbsentPanel();
            to   = Display.getPresentPanel();
        } else {
            return; // button not found anywhere
        }

        // Move the button
        from.removeButton(b);
        to.addButton(b);

        // Refresh UI
        Display.updateDisplay();
    }

    /**
     * Utility: check if a button belongs to a given panel.
     */
    public static boolean foundButton(NamePanel panel, JButton b) {
        for (Component c : panel.getComponents()) {
            if (c == b) return true;
        }
        return false;
    }
}
