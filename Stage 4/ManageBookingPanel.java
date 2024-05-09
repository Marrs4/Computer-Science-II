import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ManageBookingPanel extends JPanel {
    private JTextField memberIdField, dateField, hotelNameField;
    private JButton addButton, removeButton;
    private JTable bookingTable;
    private DefaultTableModel bookingTableModel;
    private ManageMemberBooking memberBookingManager;
    private MemberService memberService;

    public ManageBookingPanel(MemberService memberService, ManageMemberBooking bookingManager) {
        this.memberService = memberService;
        this.memberBookingManager = bookingManager;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        memberIdField = new JTextField();
        dateField = new JTextField();
        hotelNameField = new JTextField();

        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdField);
        formPanel.add(new JLabel("Date (MM-dd-yyyy):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Hotel Name:"));
        formPanel.add(hotelNameField);

        addButton = new JButton("Add Booking");
        removeButton = new JButton("Remove Booking");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        bookingTableModel = new DefaultTableModel();
        bookingTableModel.addColumn("Date");
        bookingTableModel.addColumn("Hotel Name");
        bookingTableModel.addColumn("Member ID");
        bookingTable = new JTable(bookingTableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.SOUTH);

        addButton.addActionListener(this::addBooking);
        removeButton.addActionListener(this::removeBooking);

        refreshBookingTable();
    }

    private void addBooking(ActionEvent e) {
        try {
            int memberId = Integer.parseInt(memberIdField.getText());
            String date = dateField.getText();
            String hotelName = hotelNameField.getText();

            memberBookingManager.addBooking(date, hotelName, memberId);
            refreshBookingTable();
            JOptionPane.showMessageDialog(this, "Booking added successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void removeBooking(ActionEvent e) {
        try {
            int row = bookingTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a booking to remove.");
                return;
            }

            String date = (String) bookingTableModel.getValueAt(row, 0);
            String hotelName = (String) bookingTableModel.getValueAt(row, 1);
            int memberId = Integer.parseInt((String) bookingTableModel.getValueAt(row, 2));

            memberBookingManager.removeBooking(date, hotelName, memberId);
            refreshBookingTable();
            JOptionPane.showMessageDialog(this, "Booking removed successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void refreshBookingTable() {
        bookingTableModel.setRowCount(0);
        List<BookingEntry> bookings = memberBookingManager.getFormattedBookings();
        for (BookingEntry entry : bookings) {
            if (entry != null) {
                String[] rowData = new String[]{
                        entry.getDate(),
                        entry.getHotelName(),
                        String.valueOf(entry.getMemberId()),
                        // Additional columns if needed
                };
                bookingTableModel.addRow(rowData);
            } else {

            }
        }
    }

}
