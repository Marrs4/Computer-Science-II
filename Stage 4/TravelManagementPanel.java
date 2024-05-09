import javax.swing.*;
import java.awt.*;

public class TravelManagementPanel extends JPanel {
    private JButton hotelBookingsButton, changeRatesButton, additionalFeeButton, backButton;

    public TravelManagementPanel() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // Grid layout with 4 rows

        // Initialize buttons
        hotelBookingsButton = new JButton("Manage Hotel Bookings");
        changeRatesButton = new JButton("Change Hotel Rates");
        additionalFeeButton = new JButton("Edit Additional Fees");


        // Add buttons to panel
        buttonPanel.add(hotelBookingsButton);
        buttonPanel.add(changeRatesButton);
        buttonPanel.add(additionalFeeButton);


        // Add action listeners (placeholders for actual functionality)
        hotelBookingsButton.addActionListener(e -> manageHotelBookings());
        changeRatesButton.addActionListener(e -> changeHotelRates());
        additionalFeeButton.addActionListener(e -> manageAdditionalFees());


        // Styling options
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void manageHotelBookings() {
        // Placeholder functionality
        System.out.println("Opening hotel bookings management...");
    }

    private void changeHotelRates() {
        // Placeholder functionality
        System.out.println("Changing hotel rates...");
    }

    private void manageAdditionalFees() {
        // Placeholder functionality
        System.out.println("Managing additional fees...");
    }

}
