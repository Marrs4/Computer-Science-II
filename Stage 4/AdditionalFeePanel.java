import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalFeePanel extends JPanel {
    private AdditionalFee additionalFee;
    private JPanel travelManagementPanel; // Reference to the TravelManagementPanel

    private JTextField feeIdField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField detailsField;

    private JButton manageFeeButton;
    private JButton backButton;

    public AdditionalFeePanel(AdditionalFee additionalFee, JPanel travelManagementPanel) {
        this.additionalFee = additionalFee;
        this.travelManagementPanel = travelManagementPanel;

        setLayout(new GridLayout(6, 2, 10, 10)); // Grid layout with 6 rows and 2 columns

        JLabel feeIdLabel = new JLabel("Fee ID:");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel detailsLabel = new JLabel("Details:");

        feeIdField = new JTextField();
        feeIdField.setEditable(false); // Fee ID should not be editable
        feeIdField.setText(String.valueOf(additionalFee.getFeeId()));

        descriptionField = new JTextField(additionalFee.getDescription());
        amountField = new JTextField(String.valueOf(additionalFee.getAmount()));
        detailsField = new JTextField(additionalFee.getDetails());

        manageFeeButton = new JButton("Manage Fee");
        backButton = new JButton("Back");

        add(feeIdLabel);
        add(feeIdField);
        add(descriptionLabel);
        add(descriptionField);
        add(amountLabel);
        add(amountField);
        add(detailsLabel);
        add(detailsField);
        add(manageFeeButton);
        add(backButton);

        manageFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageFee();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
    }

    private void manageFee() {
        // Update fee details based on user input
        double newAmount = Double.parseDouble(amountField.getText());
        additionalFee.setAmount(newAmount);
        additionalFee.setDescription(descriptionField.getText());
        additionalFee.setDetails(detailsField.getText());
        JOptionPane.showMessageDialog(this, "Fee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
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