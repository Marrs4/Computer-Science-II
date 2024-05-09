import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ApplicantManagementPanel extends JPanel {
    private final ApplicantPortal applicantPortal;
    private JTextField nameField, addressField, phoneNumberField, emailField;
    private JButton createApplicantButton, updateApplicantButton, deleteApplicantButton, reviewApplicantButton, viewApplicantsButton;
    private JTextArea displayArea;

    public ApplicantManagementPanel(ApplicantPortal applicantPortal) {
        this.applicantPortal = applicantPortal;
        setLayout(new BorderLayout());

        // Initialize text fields
        nameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();

        // Form panel with labels and text fields
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        // Button panel with actions
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        createApplicantButton = new JButton("Create Applicant");
        updateApplicantButton = new JButton("Update Applicant");
        deleteApplicantButton = new JButton("Delete Applicant");
        reviewApplicantButton = new JButton("Review Applicant");
        viewApplicantsButton = new JButton("View Applicants"); // New button to view applicants

        buttonPanel.add(createApplicantButton);
        buttonPanel.add(updateApplicantButton);
        buttonPanel.add(deleteApplicantButton);
        buttonPanel.add(reviewApplicantButton);
        buttonPanel.add(viewApplicantsButton);

        // Display area for messages and viewing applicants
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to the main panel
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.SOUTH);

        // Styling
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

        viewApplicantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewApplicants();
            }
        });
    }

    // Create an applicant using the provided details
    private void createApplicant() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled to create an applicant.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        applicantPortal.createApplicant(name, address, phoneNumber, email);
        JOptionPane.showMessageDialog(this, "Applicant created successfully.");

        // Clear the fields after successful creation
        nameField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
    }

    // Update an applicant based on the details provided
    private void updateApplicant() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        applicantPortal.updateApplicant(name, address, phoneNumber, email);
        JOptionPane.showMessageDialog(this, "Applicant updated successfully.");
    }

    // Delete an applicant using their name
    private void deleteApplicant() {
        String name = nameField.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        applicantPortal.deleteApplicant(name);
        JOptionPane.showMessageDialog(this, "Applicant deleted successfully.");
    }

    // Review an applicant with a confirmation dialog
    private void reviewApplicant() {
        String name = nameField.getText(); // Get the name of the applicant to review

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the name of the applicant to review.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show a dialog to ask for approval or rejection
        int option = JOptionPane.showOptionDialog(
                this,
                "Do you want to approve or reject this applicant?",
                "Review Applicant",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Approve", "Reject"},
                "Approve"
        );

        if (option == JOptionPane.YES_OPTION) {
            applicantPortal.reviewApplicant(name, true); // Approve
            displayArea.append("Applicant " + name + " approved.\n");
        } else if (option == JOptionPane.NO_OPTION) {
            applicantPortal.reviewApplicant(name, false); // Reject
            displayArea.append("Applicant " + name + " rejected and deleted.\n");
        } else {
            displayArea.append("No action taken on applicant " + name + ".\n");
        }
    }
    // View the list of all applicants
    private void viewApplicants() {
        List<Applicant> applicants = applicantPortal.getAllApplicants();
        displayArea.setText("All Applicants:\n");

        for (Applicant applicant : applicants) {
            displayArea.append(applicant + "\n");
        }
    }
}
