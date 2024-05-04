import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Club Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize all panels
        MemberService memberService = new MemberService();
        ManageMembershipFees membershipFeesManager = new ManageMembershipFees();
        ManageMemberSchedule memberScheduleManager = new ManageMemberSchedule(memberService);
        ManageMemberBooking memberBookingManager = new ManageMemberBooking(memberService);
        MemberManagementPanel memberManagementPanel = new MemberManagementPanel(memberService, membershipFeesManager, memberScheduleManager, memberBookingManager);
        EmployeeService employeeService = new EmployeeService();
        EmployeeManagementPanel employeeManagementPanel = new EmployeeManagementPanel(employeeService);
        ApplicantManagementPanel applicantManagementPanel = new ApplicantManagementPanel();
        TravelManagementPanel travelManagementPanel = new TravelManagementPanel();
        ActivitySchedulePanel activitySchedulePanel = new ActivitySchedulePanel();

        // Add panels to the card layout
        mainPanel.add(memberManagementPanel, "MemberManagement");
        mainPanel.add(employeeManagementPanel, "EmployeeManagement");
        mainPanel.add(applicantManagementPanel, "ApplicantManagement");
        mainPanel.add(travelManagementPanel, "TravelManagement");
        mainPanel.add(activitySchedulePanel, "ActivitySchedule");

        // Menu for navigation
        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Main Menu");
        menuBar.add(navigationMenu);

        // Menu items for switching panels
        JMenuItem memberItem = new JMenuItem("Member Management");
        JMenuItem employeeItem = new JMenuItem("Employee Management");
        JMenuItem applicantItem = new JMenuItem("Applicant Management");
        JMenuItem travelItem = new JMenuItem("Travel Management");
        JMenuItem activityItem = new JMenuItem("Activity Schedule");

        // Add action listeners to menu items
        memberItem.addActionListener(e -> cardLayout.show(mainPanel, "MemberManagement"));
        employeeItem.addActionListener(e -> cardLayout.show(mainPanel, "EmployeeManagement"));
        applicantItem.addActionListener(e -> cardLayout.show(mainPanel, "ApplicantManagement"));
        travelItem.addActionListener(e -> cardLayout.show(mainPanel, "TravelManagement"));
        activityItem.addActionListener(e -> cardLayout.show(mainPanel, "ActivitySchedule"));

        navigationMenu.add(memberItem);
        navigationMenu.add(employeeItem);
        navigationMenu.add(applicantItem);
        navigationMenu.add(travelItem);
        navigationMenu.add(activityItem);

        // Exit button
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this::exitApplication);
        navigationMenu.add(exitItem);

        setJMenuBar(menuBar);
        add(mainPanel);
    }

    private void exitApplication(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
