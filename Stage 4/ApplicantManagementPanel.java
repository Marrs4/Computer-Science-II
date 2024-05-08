import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantManagementPanel extends JPanel {
    private ApplicantManagement applicantManagement;
    private ApplicantPortal applicantPortal;
    private JTextField nameField, addressField, phoneNumberField, emailField;
    private JButton createApplicantButton, updateApplicantButton, deleteApplicantButton, reviewApplicantButton, backButton;
    private JTextArea displayArea;

    public ApplicantManagementPanel(ApplicantPortal applicantPortal) {
        this.applicantPortal = applicantPortal;
        setLayout(new BorderLayout());
        
         // Initialize text fields
        nameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();
        
        // Add fields and labels to the form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        createApplicantButton = new JButton("Create Applicant");
        updateApplicantButton = new JButton("Update Applicant");
        deleteApplicantButton = new JButton("Delete Applicant");
        reviewApplicantButton = new JButton("Review Applicant");

        buttonPanel.add(createApplicantButton);
        buttonPanel.add(updateApplicantButton);
        buttonPanel.add(deleteApplicantButton);
        buttonPanel.add(reviewApplicantButton);
        
        //Display Area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        // Add components to panel
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //styling
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Action listeners for buttons
        createApplicantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createApplicant();
            }
        });
        
        updateApplicantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateApplicant();
        }
        });
        
        deleteApplicantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteApplicant();
            }
        });
        
        reviewApplicantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reviewApplicant();
        }
    });
        
    }

    private void createApplicant() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled to create an applicant.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        applicantPortal.createApplicant(name, address, phoneNumber, email);
        JOptionPane.showMessageDialog(this, "Applicant created successfully.");
        
         // Clear the form fields after successful creation
        nameField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        
    }

    private void updateApplicant() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();

        // Check if the applicant exists
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
        // Call updateApplicant method
        applicantPortal.updateApplicant(name, address, phoneNumber, email);
        JOptionPane.showMessageDialog(this, "Applicant updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Applicant with the given name does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void deleteApplicant() {
        String name = nameField.getText(); // Get the name of the applicant to delete
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Check if the applicant exists
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
        // Call deleteApplicant method
        applicantPortal.deleteApplicant(name);
        JOptionPane.showMessageDialog(this, "Applicant deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Applicant with the given name does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reviewApplicant() {
        String name = nameField.getText(); // Get the name of the applicant to review
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to review.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if the applicant exists
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
        // Call the reviewApplicant method in ApplicantPortal
        applicantPortal.reviewApplicant(name);
        JOptionPane.showMessageDialog(this, "Applicant found.");
        } else {
            JOptionPane.showMessageDialog(this, "Applicant with the given name does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

