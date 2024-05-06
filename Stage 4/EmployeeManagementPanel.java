import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementPanel extends JPanel {
    private JList<Integer> idList;
    private JTextField nameField, addressField, emailField, salaryField, reviewField;
    private JButton createButton, viewButton, updateButton, deleteButton, scheduleButton, reviewButton;
    private EmployeeService employeeService;

    public EmployeeManagementPanel(EmployeeService employeeService) {
        this.employeeService = employeeService;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Create the ID list and wrap it in a scroll pane
        idList = new JList<>(getAllEmployeeIds());
        JScrollPane idScrollPane = new JScrollPane(idList);

        // Initialize other text fields
        nameField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();
        salaryField = new JTextField();
        reviewField = new JTextField();

        // Add fields and labels to the form panel
        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idScrollPane);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Salary:"));
        formPanel.add(salaryField);
        formPanel.add(new JLabel("Review:"));
        formPanel.add(reviewField);

        JPanel buttonPanel = new JPanel();
        createButton = new JButton("Create Employee");
        viewButton = new JButton("View Employee");
        updateButton = new JButton("Update Employee");
        deleteButton = new JButton("Delete Employee");
        scheduleButton = new JButton("Manage Schedule");
        reviewButton = new JButton("Write Review");

        // Add buttons to the button panel
        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(scheduleButton);
        buttonPanel.add(reviewButton);

        // Add panels to the main layout
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEmployee();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployee();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageSchedule();
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeReview();
            }
        });
    }

    // Helper method to get all employee IDs
    private Integer[] getAllEmployeeIds() {
        List<Integer> ids = employeeService.getAllEmployeeIds(); // Implement this in EmployeeService
        return ids.toArray(new Integer[0]);
    }

    // Method to create a new employee
    private void createEmployee() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        String salaryText = salaryField.getText().trim();

        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled to create an employee.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = employeeService.addEmployee(name, address, email, salary);
        JOptionPane.showMessageDialog(this, result);

        // Clear the form fields after successful creation
        refreshEmployeeIds();
        nameField.setText("");
        addressField.setText("");
        emailField.setText("");
        salaryField.setText("");
        reviewField.setText("");
    }

    // Method to view an employee by the selected ID in the list
    private void viewEmployee() {
        Integer selectedId = idList.getSelectedValue();
        if (selectedId != null) {
            Employee employee = employeeService.getEmployeeById(selectedId);
            if (employee != null) {
                nameField.setText(employee.getName());
                addressField.setText(employee.getAddress());
                emailField.setText(employee.getEmail());
                salaryField.setText(String.valueOf(employee.getSalary()));
                reviewField.setText(employee.getReview());
                JOptionPane.showMessageDialog(this, "Employee found: " + employee);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee ID.");
        }
    }

    // Method to update an employee's information
    private void updateEmployee() {
        Integer selectedId = idList.getSelectedValue();
        if (selectedId != null) {
            String name = nameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            employeeService.updateEmployee(selectedId, name, address, email, salary);
            JOptionPane.showMessageDialog(this, "Employee updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee ID.");
        }
    }

    // Method to delete an employee
    private void deleteEmployee() {
        Integer selectedId = idList.getSelectedValue();
        if (selectedId != null) {
            employeeService.deleteEmployee(selectedId);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
            refreshEmployeeIds(); // Refresh the list after deletion
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee ID.");
        }
    }

    // Method to refresh the employee ID list
    private void refreshEmployeeIds() {
        idList.setListData(getAllEmployeeIds());
    }
    
    private void manageSchedule() {
    ManageEmployeeSchedulePanel schedulePanel = new ManageEmployeeSchedulePanel(employeeService);
    JOptionPane.showMessageDialog(this, schedulePanel, "Manage Employee Schedule", JOptionPane.PLAIN_MESSAGE);
}

    private void writeReview() {
        Integer selectedId = idList.getSelectedValue();
        if (selectedId != null) {
            String review = reviewField.getText();
            String result = employeeService.writeReview(selectedId, review);
            JOptionPane.showMessageDialog(this, result);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee ID.");
        }
    }
}
