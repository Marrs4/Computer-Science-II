import java.util.Scanner;

public class HotelBookingsPortal {
    private Scanner scanner;
    private ManageMemberBooking memberBookingManager;

    public HotelBookingsPortal(ManageMemberBooking memberBookingManager) {
        scanner = new Scanner(System.in);
        this.memberBookingManager = memberBookingManager;
    }

    public void manageHotelBookings() {
        System.out.println("--- Hotel Bookings Portal ---");
        // Implement logic for managing hotel bookings here
        boolean run = true;
        while (run) {
            System.out.println("1. Make a booking");
            System.out.println("2. Cancel a booking");
            System.out.println("3. View bookings");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    makeBooking();
                    break;
                case 2:
                    cancelBooking();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private void makeBooking() {
        System.out.println("Making a new booking...");
        // Placeholder logic for making a booking
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.print("Enter member ID: ");
        int memberId = getIntInput();
        memberBookingManager.addBooking(date, hotelName, memberId);
    }

    private void cancelBooking() {
        System.out.println("Canceling a booking...");
        // Placeholder logic for canceling a booking
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.print("Enter member ID: ");
        int memberId = getIntInput();
        memberBookingManager.removeBooking(date, hotelName, memberId);
    }

    private void viewBookings() {
        System.out.println("Viewing bookings...");
        memberBookingManager.viewBookings();
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}