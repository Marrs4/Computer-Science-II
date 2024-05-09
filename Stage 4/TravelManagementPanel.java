import javax.swing.*;
import java.awt.*;

public class TravelManagementPanel extends JPanel {
    private JButton hotelBookingsButton, changeRatesButton, additionalFeeButton, backButton;

    private ChangeHotelRatesPanel changeHotelRatesPanel;
    private AdditionalFeePanel additionalFeePanel;

    public TravelManagementPanel(ChangeHotelRates changeHotelRates, AdditionalFee additionalFee, MemberService memberService) {
        setLayout(new BorderLayout());

        // Create buttons
        hotelBookingsButton = new JButton("Manage Hotel Bookings");
        changeRatesButton = new JButton("Change Hotel Rates");
        additionalFeeButton = new JButton("Edit Additional Fees");
        backButton = new JButton("Back");

        // Create panels
        HotelBookingsPortalPanel hotelBookingsPanel = new HotelBookingsPortalPanel(memberService);
        changeHotelRatesPanel = new ChangeHotelRatesPanel(changeHotelRates, this);
        additionalFeePanel = new AdditionalFeePanel(additionalFee, this);

        // Set initial panel
        JPanel currentPanel = hotelBookingsPanel;
        add(currentPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        hotelBookingsButton.addActionListener(e -> showPanel(hotelBookingsPanel));
        changeRatesButton.addActionListener(e -> showPanel(changeHotelRatesPanel));
        additionalFeeButton.addActionListener(e -> showPanel(additionalFeePanel));
        backButton.addActionListener(e -> showPanel(hotelBookingsPanel)); // Back button to go back to hotel bookings panel

        // Add buttons to button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(hotelBookingsButton);
        buttonPanel.add(changeRatesButton);
        buttonPanel.add(additionalFeeButton);
        buttonPanel.add(backButton);

        // Add button panel to the left
        add(buttonPanel, BorderLayout.WEST);
    }

    private void showPanel(JPanel panel) {
        removeAll(); // Remove all components
        add(panel, BorderLayout.CENTER); // Add new panel
        revalidate(); // Refresh layout
        repaint();
    }
}
