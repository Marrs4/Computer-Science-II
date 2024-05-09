import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeHotelRatesPanel extends JPanel {
    private ChangeHotelRates hotelRates;
    private JPanel travelManagementPanel; // Reference to the TravelManagementPanel

    private JTextField currentRateField;
    private JTextField newRateField;
    private JButton changeRateButton;
    private JButton backButton;

    public ChangeHotelRatesPanel(ChangeHotelRates hotelRates, JPanel travelManagementPanel) {
        this.hotelRates = hotelRates;
        this.travelManagementPanel = travelManagementPanel;

        setLayout(new GridLayout(4, 2, 10, 10)); // Grid layout with 4 rows and 2 columns

        JLabel currentRateLabel = new JLabel("Current Rate:");
        JLabel newRateLabel = new JLabel("New Rate:");

        currentRateField = new JTextField(String.valueOf(hotelRates.getCurrentRate()));
        currentRateField.setEditable(false); // Current rate should not be editable

        newRateField = new JTextField();

        changeRateButton = new JButton("Change Rate");
        backButton = new JButton("Back");

        add(currentRateLabel);
        add(currentRateField);
        add(newRateLabel);
        add(newRateField);
        add(changeRateButton);
        add(backButton);

        changeRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRate();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
    }

    private void changeRate() {
        double newRate = Double.parseDouble(newRateField.getText());
        hotelRates.changeRate(newRate);
        JOptionPane.showMessageDialog(this, "Hotel rate updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void goBack() {
        // Navigate back to the TravelManagementPanel using the parent container's CardLayout
        Container parent = getParent();
        if (parent.getLayout() instanceof CardLayout) {
            CardLayout cardLayout = (CardLayout) parent.getLayout();
            cardLayout.show(parent, travelManagementPanel.getName());
        }
    }
}