import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantPortalPanel extends JPanel {
    private final ApplicantManagement applicantManagement;

    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailField;

    public ApplicantPortalPanel(ApplicantManagement applicantManagement) {
        this.applicantManagement = applicantManagement;

        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailLabel = new JLabel("Email:");

        nameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();

        JButton createButton = new JButton("Create Applicant");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createApplicant();
            }
        });

        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(phoneNumberLabel);
        add(phoneNumberField);
        add(emailLabel);
        add(emailField);
        add(createButton);
    }

    private void createApplicant() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        Applicant newApplicant = new Applicant(name, address, phoneNumber, email);
        applicantManagement.addApplicant(newApplicant);
        JOptionPane.showMessageDialog(this, "Applicant created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}