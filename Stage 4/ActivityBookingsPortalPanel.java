import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityBookingsPortalPanel extends JPanel {
    private ActivityBookingsPortal<ConcreteBookingDetails> bookingsPortal;

    private JTextField activityIdField;
    private JTextField activityDateField;
    private JTextField participantNameField;

    private JButton makeBookingButton;
    private JButton cancelBookingButton;
    private JButton getBookingDetailsButton;

    public ActivityBookingsPortalPanel(ActivityBookingsPortal<ConcreteBookingDetails> bookingsPortal) {
        this.bookingsPortal = bookingsPortal;

        setLayout(new GridLayout(4, 2, 10, 10)); // Grid layout with 4 rows and 2 columns

        JLabel activityIdLabel = new JLabel("Activity ID:");
        JLabel activityDateLabel = new JLabel("Activity Date:");
        JLabel participantNameLabel = new JLabel("Participant Name:");

        activityIdField = new JTextField();
        activityDateField = new JTextField();
        participantNameField = new JTextField();

        makeBookingButton = new JButton("Make Booking");
        cancelBookingButton = new JButton("Cancel Booking");
        getBookingDetailsButton = new JButton("Get Booking Details");

        add(activityIdLabel);
        add(activityIdField);
        add(activityDateLabel);
        add(activityDateField);
        add(participantNameLabel);
        add(participantNameField);
        add(makeBookingButton);
        add(cancelBookingButton);
        add(getBookingDetailsButton);

        makeBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeBooking();
            }
        });

        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelBooking();
            }
        });

        getBookingDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBookingDetails();
            }
        });
    }

    private void makeBooking() {
        int activityId = Integer.parseInt(activityIdField.getText());
        String activityDate = activityDateField.getText();
        String participantName = participantNameField.getText();

        ConcreteBookingDetails bookingDetails = new ConcreteBookingDetails(activityDate, participantName);

        bookingsPortal.makeBooking(activityId, bookingDetails);
    }

    private void cancelBooking() {
        int activityId = Integer.parseInt(activityIdField.getText());
        bookingsPortal.cancelBooking(activityId);
    }

    private void getBookingDetails() {
        int activityId = Integer.parseInt(activityIdField.getText());
        ConcreteBookingDetails bookingDetails = bookingsPortal.getBookingDetails(activityId);
        if (bookingDetails != null) {
            JOptionPane.showMessageDialog(this, bookingDetails.toString(), "Booking Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Booking not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}