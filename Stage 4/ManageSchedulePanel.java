import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageSchedulePanel extends JPanel {
    private JTextField memberIdField, dateField, activityField;
    private JButton addButton, removeButton;
    private ManageMemberSchedule memberSchedule;
    private DefaultTableModel scheduleTableModel;
    private JTable scheduleTable;

    public ManageSchedulePanel(ManageMemberSchedule memberSchedule) {
        this.memberSchedule = memberSchedule;
        initializeUI();
        setupScheduleTableModel();
        viewSchedule(); // Call viewSchedule() method here to display schedule automatically
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        memberIdField = new JTextField();
        dateField = new JTextField();
        activityField = new JTextField();

        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdField);
        formPanel.add(new JLabel("Date (MM-dd-yyyy):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Activity:"));
        formPanel.add(activityField);

        addButton = new JButton("Add to Schedule");
        removeButton = new JButton("Remove from Schedule");

        addButton.addActionListener(e -> addMemberToSchedule());
        removeButton.addActionListener(e -> removeMemberFromSchedule());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Create schedule table
        scheduleTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(scheduleTable);

        // Add components to panel
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupScheduleTableModel() {
        scheduleTableModel = new DefaultTableModel();
        scheduleTableModel.addColumn("Member ID");
        scheduleTableModel.addColumn("Date");
        scheduleTableModel.addColumn("Activity");
        scheduleTable.setModel(scheduleTableModel); // Set model to the table
    }

    private void addMemberToSchedule() {
        int memberId = Integer.parseInt(memberIdField.getText());
        String date = dateField.getText();
        String activity = activityField.getText();
        memberSchedule.addMemberToSchedule(date, activity, memberId);
        JOptionPane.showMessageDialog(this, "Member added to schedule successfully.");
        refreshScheduleTable();
    }

    private void removeMemberFromSchedule() {
        int memberId = Integer.parseInt(memberIdField.getText());
        String date = dateField.getText();
        String activity = activityField.getText();
        memberSchedule.removeMemberFromSchedule(date, activity, memberId);
        JOptionPane.showMessageDialog(this, "Member removed from schedule successfully.");
        refreshScheduleTable();
    }

    private void viewSchedule() {
        refreshScheduleTable();
    }

    private void refreshScheduleTable() {
        String[][] data = memberSchedule.getScheduleDataForTable(); // Ensure this method exists
        scheduleTableModel.setRowCount(0); // Clear existing data
        for (String[] row : data) {
            scheduleTableModel.addRow(row);
        }
    }
}
