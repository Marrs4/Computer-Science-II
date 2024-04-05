import java.util.Scanner;

public class MemberManagementPortal {
    private final MemberService memberService = new MemberService();
    private final ManageMembershipFees membershipFeesManager = new ManageMembershipFees();
    private ManageMemberSchedule memberScheduleManager;
    private ManageMemberBooking memberBookingManager;
    private final Scanner scanner = new Scanner(System.in);

    public MemberManagementPortal() {
        // Initialize memberScheduleManager and memberBookingManager with memberService
        this.memberScheduleManager = new ManageMemberSchedule(memberService);
        this.memberBookingManager = new ManageMemberBooking(memberService);
    }

    public void manageMembers() {
        boolean run = true;

        while (run) {
            System.out.println("\nMember Management Portal");
            System.out.println("1. Create Member");
            System.out.println("2. Read Member");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Membership Fees");
            System.out.println("6. Member Schedule");
            System.out.println("7. Member Booking");
            System.out.println("8. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createMember();
                    break;
                case 2:
                    readMember();
                    break;
                case 3:
                    updateMember();
                    break;
                case 4:
                    deleteMember();
                    break;
                case 5:
                    manageFees();
                    break;
                case 6:
                    manageSchedule();
                    break;
                case 7:
                    manageBooking();
                    break;
                case 8:
                    System.out.println("Exiting Member Management Portal.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 8.");
            }
        }
    }

    private void createMember() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        memberService.createMember(name, address, email);
    }

    private void readMember() {
        System.out.print("Enter Member ID: ");
        int id = getIntInput();
        Member member = memberService.getMemberById(id);
        if (member != null) {
            System.out.println("Member Found: " + member);
        } else {
            System.out.println("Member with ID " + id + " not found.");
        }
    }

    private void updateMember() {
        System.out.print("Enter Member ID to Update: ");
        int id = getIntInput();
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        memberService.updateMember(id, name, address, email);
    }

    private void deleteMember() {
        System.out.print("Enter Member ID to Delete: ");
        int id = getIntInput();
        memberService.deleteMember(id);
    }

    private void manageFees() {
        membershipFeesManager.displayFee();
        System.out.print("Enter new fee amount or press Enter to keep current: ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            try {
                double newFee = Double.parseDouble(input);
                membershipFeesManager.changeFee(newFee);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void manageSchedule() {
        System.out.println("\nMember Schedule Management");
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
        System.out.println("\nMember Booking Management");
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
