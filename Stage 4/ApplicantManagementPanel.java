import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ApplicantManagementPanel extends JPanel {
    private JButton createApplicantButton, updateApplicantButton, deleteApplicantButton, reviewApplicantButton, backButton;
    private JTextArea displayArea;

    public ApplicantManagementPanel() {
        setLayout(new BorderLayout());

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


        // Display Area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Action Listeners
        createApplicantButton.addActionListener(this::createApplicant);
        updateApplicantButton.addActionListener(this::updateApplicant);
        deleteApplicantButton.addActionListener(this::deleteApplicant);
        reviewApplicantButton.addActionListener(this::reviewApplicant);


        // Add components to panel
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Styling
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void createApplicant(ActionEvent e) {
        displayArea.setText("Interface to create a new applicant...\n");
        // Implementation for creating a new applicant would go here
    }

    private void updateApplicant(ActionEvent e) {
        displayArea.setText("Interface to update an existing applicant...\n");
        // Implementation for updating an applicant would go here
    }

    private void deleteApplicant(ActionEvent e) {
        displayArea.setText("Interface to delete an applicant...\n");
        // Implementation for deleting an applicant would go here
    }

    private void reviewApplicant(ActionEvent e) {
        displayArea.setText("Interface to review an applicant...\n");
        // Implementation for reviewing an applicant would go here
    }


}
