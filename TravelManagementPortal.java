import java.util.Scanner;

public class TravelManagementPortal {
    private HotelBookingsPortal hotelBookingsPortal;
    private ChangeHotelRates changeHotelRates;
    private AdditionalFee additionalFee;
    private Scanner scanner = new Scanner(System.in);

    public TravelManagementPortal(HotelBookingsPortal hotelBookingsPortal, ChangeHotelRates changeHotelRates, AdditionalFee additionalFee) {
        this.hotelBookingsPortal = hotelBookingsPortal;
        this.changeHotelRates = changeHotelRates;
        this.additionalFee = additionalFee;
    }

    public void manageTravel() {
        System.out.println("--- Travel Management Portal ---");
        boolean run = true;
        while (run) {
            System.out.println("1. Hotel Bookings");
            System.out.println("2. Change Hotel Rates");
            System.out.println("3. Additional Fees");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    hotelBookingsPortal.manageHotelBookings();
                    break;
                case 2:
                    changeHotelRates.manageHotelRates();
                    break;
                case 3:
                    manageAdditionalFee();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private void manageAdditionalFee() {
        System.out.println("Managing Additional Fees:");
        System.out.println("Current Fee Details: " + additionalFee);
        System.out.print("Enter new amount (Current: $" + additionalFee.getAmount() + "): ");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline left-over
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        additionalFee.setAmount(newAmount);
        additionalFee.setDescription(newDescription);
        System.out.println("Updated Fee Details: " + additionalFee);
    }

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
