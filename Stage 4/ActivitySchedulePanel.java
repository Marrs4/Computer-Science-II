import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActivitySchedulePanel extends JPanel {
    private JButton addScheduleButton, updateScheduleButton, removeScheduleButton, viewSchedulesButton, manageTrainersButton, backButton;
    private JTextArea displayArea;

    public ActivitySchedulePanel() {
        setLayout(new BorderLayout());

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        addScheduleButton = new JButton("Add Schedule");
        updateScheduleButton = new JButton("Update Schedule");
        removeScheduleButton = new JButton("Remove Schedule");
        viewSchedulesButton = new JButton("View Schedules");
        manageTrainersButton = new JButton("Manage Trainers");
        backButton = new JButton("Back to Main Menu");

        buttonPanel.add(addScheduleButton);
        buttonPanel.add(updateScheduleButton);
        buttonPanel.add(removeScheduleButton);
        buttonPanel.add(viewSchedulesButton);
        buttonPanel.add(manageTrainersButton);


        // Display Area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Action Listeners
        addScheduleButton.addActionListener(this::addSchedule);
        updateScheduleButton.addActionListener(this::updateSchedule);
        removeScheduleButton.addActionListener(this::removeSchedule);
        viewSchedulesButton.addActionListener(this::viewSchedules);
        manageTrainersButton.addActionListener(this::manageTrainers);


        // Add components to panel
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Styling
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addSchedule(ActionEvent e) {
        displayArea.setText("Adding a new schedule...\n");
    }

    private void updateSchedule(ActionEvent e) {
        displayArea.setText("Updating a schedule...\n");
    }

    private void removeSchedule(ActionEvent e) {
        displayArea.setText("Removing a schedule...\n");
    }

    private void viewSchedules(ActionEvent e) {
        displayArea.setText("Viewing all schedules...\n");
    }

    private void manageTrainers(ActionEvent e) {
        displayArea.setText("Managing trainers...\n");
    }

}
