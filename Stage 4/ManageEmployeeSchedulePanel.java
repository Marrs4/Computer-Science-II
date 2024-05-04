import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageEmployeeSchedulePanel extends JPanel {
    private JTextField dateField, activityField, employeeIdField;
    private JButton addScheduleButton, removeScheduleButton;
    private JTable scheduleTable;
    private DefaultTableModel tableModel;
    private EmployeeService employeeService;

    public ManageEmployeeSchedulePanel(EmployeeService employeeService) {
        this.employeeService = employeeService;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Form panel for input fields
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        dateField = new JTextField();
        activityField = new JTextField();
        employeeIdField = new JTextField();

        formPanel.add(new JLabel("Date (MM-DD-YYYY):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Activity:"));
        formPanel.add(activityField);
        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(employeeIdField);

        // Button panel for add and remove buttons
        JPanel buttonPanel = new JPanel();
        addScheduleButton = new JButton("Add to Schedule");
        removeScheduleButton = new JButton("Remove from Schedule");

        buttonPanel.add(addScheduleButton);
        buttonPanel.add(removeScheduleButton);

        // Schedule table initialization
        String[] columns = {"Date", "Activity", "Employee ID"};
        tableModel = new DefaultTableModel(columns, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);

        // Add components to the panel
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeToSchedule();
            }
        });

        removeScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployeeFromSchedule();
            }
        });

        // Populate schedule table initially
        populateScheduleTable();
    }

    private void addEmployeeToSchedule() {
        String date = dateField.getText();
        String activity = activityField.getText();
        int employeeId = Integer.parseInt(employeeIdField.getText());

        // Check if the employee exists
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            // Create the schedule entry
            EmployeeScheduleEntry scheduleEntry = new EmployeeScheduleEntry(date, activity, employeeId);
            // Add the schedule entry to the employee's schedule
            String result = employeeService.addScheduleEntry(scheduleEntry);
            // Update the schedule table
            populateScheduleTable();
            JOptionPane.showMessageDialog(this, result);
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }
    }

    private void removeEmployeeFromSchedule() {
        String date = dateField.getText();
        String activity = activityField.getText();
        int employeeId = Integer.parseInt(employeeIdField.getText());

        // Check if the employee exists
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            // Create the schedule entry
            EmployeeScheduleEntry scheduleEntry = new EmployeeScheduleEntry(date, activity, employeeId);
            // Remove the schedule entry from the employee's schedule
            String result = employeeService.removeScheduleEntry(scheduleEntry);
            // Update the schedule table
            populateScheduleTable();
            JOptionPane.showMessageDialog(this, result);
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }
    }

    private void populateScheduleTable() {
        // Clear existing data from the table
        tableModel.setRowCount(0);

        // Retrieve schedule entries from the service
        List<EmployeeScheduleEntry> scheduleEntries = employeeService.getAllScheduleEntries();

        // Populate the table with schedule entries
        for (EmployeeScheduleEntry entry : scheduleEntries) {
            Object[] rowData = {entry.getDate(), entry.getActivity(), entry.getEmployeeId()};
            tableModel.addRow(rowData);
        }
    }
}
