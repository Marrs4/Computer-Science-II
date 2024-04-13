import java.util.Scanner;

public class TravelManagementPortal {
    private HotelBookingsPortal hotelBookingsPortal;
    private ChangeHotelRates changeHotelRates;
    private AdditionalFee additionalFee;

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
            System.out.println("3. Additional Fee Editor");
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
                    // Logic for managing additional fees
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private int getIntInput() {
        Scanner scanner = new Scanner(System.in);
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