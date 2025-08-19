import java.awt.*;
import javax.swing.*;

/**
 * The Display class is responsible for building and managing
 * the main GUI window of the Attendance application.
 *
 * Layout:
 * - NORTH: Import button + "Present"/"Absent" labels
 * - CENTER: Two scrollable panels (Present list, Absent list)
 * - SOUTH: Text box ("sadnessBox") for ID input
 */
public class Display {
   private static JFrame frame; // Main application window
   private static NamePanel present = new NamePanel(); // Panel for students marked present
   private static NamePanel absent  = new NamePanel(); // Panel for students marked absent
   private static JScrollPane PscrollPane, AscrollPane; // Scroll containers for panels
   private static JTextArea sadnessBox = new JTextArea(); // Text area for inputting/scanning IDs

   /**
    * Constructor:
    * Builds the JFrame, sets layout, and adds all UI components.
    */
   public Display() {
       frame = new JFrame("Attendance");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(600, 800);
       frame.setLayout(new BorderLayout());

       /* ----------------------
          NORTH: Import button + labels
       ---------------------- */
       JPanel topPanel = new JPanel(new BorderLayout());

       // Import button loads data and updates UI
       JButton importButton = new JButton("Import");
       importButton.setPreferredSize(new Dimension(600, 30));
       importButton.addActionListener(e -> {
           Import.load();       // Load student data
           updateDisplay();     // Refresh UI
       });
       topPanel.add(importButton, BorderLayout.NORTH);

       // Labels for Present / Absent sections
       JLabel presentLabel = new JLabel("Present", SwingConstants.CENTER);
       JLabel absentLabel  = new JLabel("Absent",  SwingConstants.CENTER);
       presentLabel.setPreferredSize(new Dimension(300, 30));
       absentLabel.setPreferredSize(new Dimension(300, 30));

       JPanel labelPanel = new JPanel(new GridLayout(1, 2));
       labelPanel.add(presentLabel);
       labelPanel.add(absentLabel);

       topPanel.add(labelPanel, BorderLayout.CENTER);
       frame.add(topPanel, BorderLayout.NORTH);

       /* ----------------------
          CENTER: Panels for Present / Absent students
       ---------------------- */
       PscrollPane = new JScrollPane(
           present,
           JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
           JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
       );

       AscrollPane = new JScrollPane(
           absent,
           JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
           JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
       );

       JPanel center = new JPanel(new GridLayout(1, 2));
       center.add(PscrollPane);
       center.add(AscrollPane);
       frame.add(center, BorderLayout.CENTER);

       /* ----------------------
          SOUTH: Input text area ("sadnessBox")
       ---------------------- */
       sadnessBox.setPreferredSize(new Dimension(600, 120));
       sadnessBox.setLineWrap(true);
       sadnessBox.setWrapStyleWord(true);
       frame.add(sadnessBox, BorderLayout.SOUTH);

       // Final setup: center screen & show window
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }

   /* =====================
      Getters for panels
   ===================== */
   public static NamePanel getPresentPanel() {
       return present;
   }

   public static NamePanel getAbsentPanel()  {
       return absent; 
   }

   /**
    * Refreshes the UI after adding/removing students.
    * Ensures scrollbars and panels redraw properly.
    */
   public static void updateDisplay() {
       present.revalidate();  
       present.repaint();
       PscrollPane.revalidate();  
       PscrollPane.repaint();

       absent.revalidate();  
       absent.repaint();
       AscrollPane.revalidate();  
       AscrollPane.repaint();

       frame.revalidate();    
       frame.repaint();
   }

   /* =====================
      Setters for panels
   ===================== */
   public static void setPresentPanel(NamePanel p) {
       present = p;
   }
   
   public static void setAbsentPanel(NamePanel a) {
       absent = a;
   }

   /* =====================
      SadnessBox methods
   ===================== */
   // Get text from input box
   public static String getSadnessBoxText() {
       String laChulk = sadnessBox.getText();
       return laChulk;
   }

   // Clear input box
   public static void clearSadnessBox() {
       sadnessBox.setText("");
   }
}
