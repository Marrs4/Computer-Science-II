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
            System.out.println("3. Additional Fee Editor");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    hotelBookingsPortal.manageHotelBookings();
                    break;
                case 2:
                    manageHotelRates();
                    break;
                case 3:
                    additionalFee.manageAdditionalFee();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private void manageHotelRates() {
        System.out.println("Current Rate: $" + changeHotelRates.getCurrentRate());
        System.out.print("Enter new rate: ");
        double newRate = scanner.nextDouble();
        changeHotelRates.changeRate(newRate);
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
