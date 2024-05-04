import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class MemberManagementPanel extends JPanel {
    private JComboBox<String> memberIdComboBox; // Declared a JComboBox for member ID selection
    private JTextField nameField, addressField, emailField, feeField;
    private JButton createButton, viewButton, updateButton, deleteButton, changeFeeButton, memberBookingButton, scheduleButton;
    private MemberService memberService;
    private ManageMembershipFees membershipFees;
    private ManageMemberSchedule memberSchedule;
    private ManageMemberBooking memberBooking;

    public MemberManagementPanel(MemberService memberService, ManageMembershipFees membershipFees, ManageMemberSchedule memberSchedule, ManageMemberBooking memberBooking) {
        this.memberService = memberService;
        this.membershipFees = membershipFees;
        this.memberSchedule = memberSchedule;
        this.memberBooking = memberBooking;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        memberIdComboBox = new JComboBox<>(); // Initialize JComboBox for member ID selection
        populateMemberIdComboBox(); // Populate combo box with existing member IDs

        memberIdComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateFieldsFromSelectedMember();
            }
        });

        nameField = new JTextField();
        addressField = new JTextField();
        emailField = new JTextField();
        feeField = new JTextField();

        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdComboBox); // Add JComboBox to the formPanel
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Membership Fee:"));
        formPanel.add(feeField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        createButton = new JButton("Create Member");
        viewButton = new JButton("View Member");
        updateButton = new JButton("Update Member");
        deleteButton = new JButton("Delete Member");
        changeFeeButton = new JButton("Change Fee");
        memberBookingButton = new JButton("Member Booking");
        scheduleButton = new JButton("Member Schedule");

        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(changeFeeButton);
        buttonPanel.add(memberBookingButton);
        buttonPanel.add(scheduleButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setupButtonActions();
    }

    private void populateMemberIdComboBox() {
        ArrayList<Member> members = memberService.getAllMembers();
        for (Member member : members) {
            memberIdComboBox.addItem(String.valueOf(member.getId()));
        }
    }

    private void updateFieldsFromSelectedMember() {
        String selectedMemberId = (String) memberIdComboBox.getSelectedItem();
        Member selectedMember = memberService.getMemberById(Integer.parseInt(selectedMemberId));
        if (selectedMember != null) {
            nameField.setText(selectedMember.getName());
            addressField.setText(selectedMember.getAddress());
            emailField.setText(selectedMember.getEmail());
        }
    }

    private void setupButtonActions() {
        createButton.addActionListener(e -> createMember());
        viewButton.addActionListener(e -> viewMember());
        updateButton.addActionListener(e -> updateMember());
        deleteButton.addActionListener(e -> deleteMember());
        changeFeeButton.addActionListener(e -> changeMembershipFee());
        memberBookingButton.addActionListener(e -> showBookingPanel());
        scheduleButton.addActionListener(e -> showSchedulePanel());
    }

    private void createMember() {
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        memberService.createMember(name, address, email);
        JOptionPane.showMessageDialog(this, "Member created successfully.");
        memberIdComboBox.removeAllItems();
        populateMemberIdComboBox();
    }

    private void viewMember() {
        String selectedMemberId = (String) memberIdComboBox.getSelectedItem();
        int id = Integer.parseInt(selectedMemberId);
        Member member = memberService.getMemberById(id);
        if (member != null) {
            nameField.setText(member.getName());
            addressField.setText(member.getAddress());
            emailField.setText(member.getEmail());
            JOptionPane.showMessageDialog(this, "Member found: " + member);
        } else {
            JOptionPane.showMessageDialog(this, "Member not found.");
        }
    }

    private void updateMember() {
        String selectedMemberId = (String) memberIdComboBox.getSelectedItem();
        int id = Integer.parseInt(selectedMemberId);
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        memberService.updateMember(id, name, address, email);
        JOptionPane.showMessageDialog(this, "Member updated successfully.");
    }

    private void deleteMember() {
        String selectedMemberId = (String) memberIdComboBox.getSelectedItem();
        int id = Integer.parseInt(selectedMemberId);
        memberService.deleteMember(id);
        JOptionPane.showMessageDialog(this, "Member deleted successfully.");
        memberIdComboBox.removeItem(selectedMemberId);
    }

    private void changeMembershipFee() {
        try {
            double newFee = Double.parseDouble(feeField.getText());
            membershipFees.changeFee(newFee);
            JOptionPane.showMessageDialog(this, "Membership fee updated to $" + newFee);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid fee value. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBookingPanel() {
        JDialog bookingDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Member Booking", Dialog.ModalityType.APPLICATION_MODAL);
        bookingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        try {
            ManageBookingPanel bookingPanel = new ManageBookingPanel(memberService, memberBooking);
            bookingDialog.add(bookingPanel);
            bookingDialog.pack();
            bookingDialog.setLocationRelativeTo(this);
            bookingDialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening booking panel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showSchedulePanel() {
        JDialog scheduleDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Member Schedule", Dialog.ModalityType.APPLICATION_MODAL);
        scheduleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Ensure the dialog closes only on user interaction
        try {
            ManageSchedulePanel schedulePanel = new ManageSchedulePanel(memberSchedule);
            scheduleDialog.add(schedulePanel);
            scheduleDialog.pack();
            scheduleDialog.setLocationRelativeTo(this);
            scheduleDialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error opening schedule view: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
