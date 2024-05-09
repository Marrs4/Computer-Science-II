
import java.util.Scanner;

/**
 * The MemberManagementPortal class serves as the user interface for managing
 * members the club. It supports operations such as creating, reading,
 * updating, and deleting member information, managing membership fees, and
 * handling member schedules and bookings.
 */
public class MemberManagementPortal {
    private final MemberService memberService; // Service for member operations
    private final ManageMembershipFees membershipFeesManager; // Manager for handling membership fees
    private ManageMemberSchedule memberScheduleManager; // Manager for member schedules
    private ManageMemberBooking memberBookingManager; // Manager for member bookings
    private final Scanner scanner; // Scanner for reading console input

    /**
     * Initializes the member management portal with required services and managers.
     */
    public MemberManagementPortal() {
        this.memberService = new MemberService();
        this.membershipFeesManager = new ManageMembershipFees();
        this.memberScheduleManager = new ManageMemberSchedule(memberService);
        this.memberBookingManager = new ManageMemberBooking(memberService);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main loop for the member management interface. Presents the user with a menu
     * of options and executes the selected operation.
     */
    public void manageMembers() {
        boolean run = true;

        while (run) {
            // Display the menu options to the user
            System.out.println("\n--- Member Management Portal ---");
            System.out.println("1. Create Member");
            System.out.println("2. Read Member");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Membership Fees");
            System.out.println("6. Member Schedule");
            System.out.println("7. Member Booking");
            System.out.println("8. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(); // Read the user's choice

            switch (choice) {
                case 1: createMember(); break; // Create a new member
                case 2: readMember(); break; // Display a member's details
                case 3: updateMember(); break; // Update a member's information
                case 4: deleteMember(); break; // Delete a member
                case 5: manageFees(); break; // Manage membership fees
                case 6: manageSchedule(); break; // Manage member schedules
                case 7: manageBooking(); break; // Manage member bookings
                case 8: // Exit the management interface
                    System.out.println("Exiting Member Management Portal.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 8.");
            }
        }
    }

    /**
     * Prompts the user for member details and creates a new member.
     */
    private void createMember() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        memberService.createMember(name, address, email); // Use the memberService to create a new member
    }

    /**
     * Prompts the user for a member ID and displays the member's details.
     */
    private void readMember() {
        System.out.print("Enter Member ID: ");
        int id = getIntInput(); // Safely parse the member ID
        Member member = memberService.getMemberById(id); // Retrieve the member by ID
        if (member != null) {
            System.out.println("Member Found: " + member);
        } else {
            System.out.println("Member with ID " + id + " not found.");
        }
    }

    /**
     * Prompts the user for member details and updates an existing member.
     */
    private void updateMember() {
        System.out.print("Enter Member ID to Update: ");
        int id = getIntInput(); // Safely parse the member ID
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        memberService.updateMember(id, name, address, email); // Update the member's details
    }

    /**
     * Prompts the user for a member ID and deletes the member.
     */
    private void deleteMember() {
        System.out.print("Enter Member ID to Delete: ");
        int id = getIntInput(); // Safely parse the member ID
        memberService.deleteMember(id); // Delete the member
    }

    /**
     * Displays and allows updating of the current membership fee.
     */
    private void manageFees() {
        membershipFeesManager.displayFee(); // Show the current fee
        System.out.print("Enter new fee amount or press Enter to keep current: ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            try {
                double newFee = Double.parseDouble(input); // Safely parse the new fee
                membershipFeesManager.changeFee(newFee); // Update the fee
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Manages the addition, removal, and viewing of member schedules.
     */
    private void manageSchedule() {
        System.out.println("\n--- Member Schedule Management ---");
        System.out.println("1. Add Member to Schedule");
        System.out.println("2. Remove Member from Schedule");
        System.out.println("3. View Schedule");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();

        switch (choice) {
            case 1: // Add member to schedule
                System.out.print("Enter Member ID: ");
                int memberIdToAdd = getIntInput();
                System.out.print("Enter date (MM-DD-YYYY): ");
                String dateToAdd = scanner.nextLine();
                System.out.print("Enter activity: ");
                String activityToAdd = scanner.nextLine();
                memberScheduleManager.addMemberToSchedule(dateToAdd, activityToAdd, memberIdToAdd);
                break;
            case 2: // Remove member from schedule
                System.out.print("Enter Member ID: ");
                int memberIdToRemove = getIntInput();
                System.out.print("Enter date (MM-DD-YYYY): ");
                String dateToRemove = scanner.nextLine();
                System.out.print("Enter activity: ");
                String activityToRemove = scanner.nextLine();
                memberScheduleManager.removeMemberFromSchedule(dateToRemove, activityToRemove, memberIdToRemove);
                break;
            case 3: // View schedule
                memberScheduleManager.viewSchedule();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    private void manageBooking() {
        System.out.println("\n--- Member Booking Management ---");
        System.out.println("1. Add Booking for Member");
        System.out.println("2. Remove Booking for Member");
        System.out.println("3. View Bookings");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();

        switch (choice) {
            case 1: // Add booking for member
                System.out.print("Enter Member ID: ");
                int memberIdForBooking = getIntInput();
                System.out.print("Enter date (MM-DD-YYYY): ");
                String dateForBooking = scanner.nextLine();
                System.out.print("Enter hotel name: ");
                String hotelName = scanner.nextLine();
                memberBookingManager.addBooking(dateForBooking, hotelName, memberIdForBooking);
                break;
            case 2: // Remove booking for member
                System.out.print("Enter Member ID: ");
                int memberIdForCancelation = getIntInput();
                System.out.print("Enter date (MM-DD-YYYY): ");
                String dateForCancelation = scanner.nextLine();
                System.out.print("Enter hotel name: ");
                String hotelNameForCancelation = scanner.nextLine();
                memberBookingManager.removeBooking(dateForCancelation, hotelNameForCancelation, memberIdForCancelation);
                break;
            case 3: // View bookings
                memberBookingManager.viewBookings();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Utility method to safely parse integer input from the user
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
