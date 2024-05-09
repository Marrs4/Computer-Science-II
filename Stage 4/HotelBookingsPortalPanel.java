import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelBookingsPortalPanel extends JPanel {
    private HotelBookingsPortal bookingsPortal;

    public HotelBookingsPortalPanel() {
        bookingsPortal = new HotelBookingsPortal(new ManageMemberBooking());

        JButton makeBookingButton = new JButton("Make a booking");
        JButton cancelBookingButton = new JButton("Cancel a booking");
        JButton viewBookingsButton = new JButton("View bookings");

        makeBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingsPortal.makeBooking();
            }
        });

        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingsPortal.cancelBooking();
            }
        });

        viewBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingsPortal.viewBookings();
            }
        });

        add(makeBookingButton);
        add(cancelBookingButton);
        add(viewBookingsButton);
    }
}