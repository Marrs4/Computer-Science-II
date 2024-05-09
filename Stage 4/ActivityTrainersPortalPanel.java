import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ActivityTrainersPortalPanel extends JPanel {
    private ActivityTrainersPortal trainersPortal;

    private JTextField trainerIdField;
    private JTextField trainerNameField;
    private JTextField expertiseField;
    private JTextField contactInfoField;

    private JButton addTrainerButton;
    private JButton removeTrainerButton;
    private JButton getTrainerDetailsButton;

    public ActivityTrainersPortalPanel(ActivityTrainersPortal trainersPortal) {
        this.trainersPortal = trainersPortal;

        setLayout(new GridLayout(6, 2, 10, 10)); // Grid layout with 6 rows and 2 columns

        JLabel trainerIdLabel = new JLabel("Trainer ID:");
        JLabel trainerNameLabel = new JLabel("Trainer Name:");
        JLabel expertiseLabel = new JLabel("Expertise:");
        JLabel contactInfoLabel = new JLabel("Contact Info:");

        trainerIdField = new JTextField();
        trainerNameField = new JTextField();
        expertiseField = new JTextField();
        contactInfoField = new JTextField();

        addTrainerButton = new JButton("Add Trainer");
        removeTrainerButton = new JButton("Remove Trainer");
        getTrainerDetailsButton = new JButton("Get Trainer Details");

        add(trainerIdLabel);
        add(trainerIdField);
        add(trainerNameLabel);
        add(trainerNameField);
        add(expertiseLabel);
        add(expertiseField);
        add(contactInfoLabel);
        add(contactInfoField);
        add(addTrainerButton);
        add(removeTrainerButton);
        add(getTrainerDetailsButton);

        addTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrainer();
            }
        });

        removeTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTrainer();
            }
        });

        getTrainerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTrainerDetails();
            }
        });
    }

    private void addTrainer() {
        int trainerId = Integer.parseInt(trainerIdField.getText());
        String trainerName = trainerNameField.getText();
        String expertise = expertiseField.getText();
        String contactInfo = contactInfoField.getText();

        Trainer trainer = new Trainer(trainerId, trainerName, expertise, contactInfo);
        trainersPortal.addTrainer(trainer);
    }

    private void removeTrainer() {
        int trainerId = Integer.parseInt(trainerIdField.getText());
        trainersPortal.removeTrainer(trainerId);
    }

    private void getTrainerDetails() {
        int trainerId = Integer.parseInt(trainerIdField.getText());
        Trainer trainer = trainersPortal.getTrainerDetails(trainerId);
        if (trainer != null) {
            JOptionPane.showMessageDialog(this, trainer.toString(), "Trainer Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Trainer not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}