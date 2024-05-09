import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActivitySchedulePanel extends JPanel {
    private JTextArea displayArea;
    private JButton addScheduleButton;
    private JButton backButton;
    private JButton manageTrainersButton;
    private JButton viewSchedulesButton;
    private JButton removeScheduleButton;
    private JButton updateScheduleButton;

    public ActivitySchedulePanel() {
        setLayout(new BorderLayout());

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        JButton addScheduleButton = new JButton("Add Schedule");
        JButton updateScheduleButton = new JButton("Update Schedule");
        JButton removeScheduleButton = new JButton("Remove Schedule");
        JButton viewSchedulesButton = new JButton("View Schedules");
        JButton manageTrainersButton = new JButton("Manage Trainers");
        JButton backButton = new JButton("Back to Main Menu");

        buttonPanel.add(addScheduleButton);
        buttonPanel.add(updateScheduleButton);
        buttonPanel.add(removeScheduleButton);
        buttonPanel.add(viewSchedulesButton);
        buttonPanel.add(manageTrainersButton);
        buttonPanel.add(backButton);

        // Display Area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to panel
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Styling
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Action Listeners
        addScheduleButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter new schedule:");
            if (input != null && !input.isEmpty()) {
                displayArea.append("Added Schedule: " + input + "\n");
            }
        });

        updateScheduleButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter schedule to update:");
            if (input != null && !input.isEmpty()) {
                displayArea.append("Updated Schedule: " + input + "\n");
            }
        });

        removeScheduleButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter schedule to remove:");
            if (input != null && !input.isEmpty()) {
                displayArea.append("Removed Schedule: " + input + "\n");
            }
        });

        viewSchedulesButton.addActionListener(e -> {
            // Simulate viewing schedules
            displayArea.append("Viewing all schedules...\n");
        });

        manageTrainersButton.addActionListener(e -> {
            // Simulate managing trainers
            displayArea.append("Managing trainers...\n");
        });

        backButton.addActionListener(e -> {
            // Handle back button action, navigate back to the main menu or previous screen
            // For example, you can use CardLayout to switch panels
            // cardLayout.show(mainPanel, "MainMenuPanel"); // Assuming mainPanel is a reference to the main panel
        });
    }
}