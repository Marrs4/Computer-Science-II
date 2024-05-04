import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementPanel extends JPanel {
    private JTextField idField, nameField, addressField, emailField, salaryField, reviewField;
    private JButton createButton, viewButton, updateButton, deleteButton, scheduleButton, reviewButton;
    private EmployeeService employeeService;

    public EmployeeManagementPanel(EmployeeService employeeService) {
        this.employeeService = employeeService;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();
        salaryField = new JTextField();
        reviewField = new JTextField();

        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(idField);
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

        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(scheduleButton);
        buttonPanel.add(reviewButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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

    private void createEmployee() {
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        String result = employeeService.addEmployee(name, address, email, salary);
        JOptionPane.showMessageDialog(this, result);
    }

    private void viewEmployee() {
        int id = Integer.parseInt(idField.getText());
        Employee employee = employeeService.getEmployeeById(id);
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
    }

    private void updateEmployee() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        employeeService.updateEmployee(id, name, address, email, salary);
        JOptionPane.showMessageDialog(this, "Employee updated successfully.");
    }

    private void deleteEmployee() {
        int id = Integer.parseInt(idField.getText());
        employeeService.deleteEmployee(id);
        JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
    }

    private void manageSchedule() {
        // Create an instance of ManageEmployeeSchedulePanel
        ManageEmployeeSchedulePanel schedulePanel = new ManageEmployeeSchedulePanel(employeeService);

        // Show the schedule panel in a dialog
        JOptionPane.showMessageDialog(this, schedulePanel);
    }

    private void writeReview() {
        int id = Integer.parseInt(idField.getText());
        String review = reviewField.getText();
        String result = employeeService.writeReview(id, review);
        JOptionPane.showMessageDialog(this, result);
    }
}
