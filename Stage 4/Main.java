import java.util.Scanner;

public class Main {
    private static MemberManagementPortal memberManagementPortal = new MemberManagementPortal();
    private static EmployeeManagementPortal employeeManagementPortal = new EmployeeManagementPortal();
    private static ApplicantPortal applicantPortal = new ApplicantPortal(new ApplicantManagement(new EmployeeService()));
    private static ManageMemberBooking manageMemberBooking = new ManageMemberBooking(new MemberService());
    private static HotelBookingsPortal hotelBookingsPortal = new HotelBookingsPortal(manageMemberBooking);
    private static ChangeHotelRates changeHotelRates = new ChangeHotelRates(120.0);
    private static AdditionalFee additionalFee = new AdditionalFee();
    private static TravelManagementPortal travelManagementPortal = new TravelManagementPortal(hotelBookingsPortal, changeHotelRates, additionalFee);
    private static ManageMemberSchedule memberScheduleManager = new ManageMemberSchedule(new MemberService());
    private static ActivityTrainersPortal trainersPortal = new ActivityTrainersPortal();
    private static ActivitySchedulePortal activitySchedulePortal = new ActivitySchedulePortal(memberScheduleManager, trainersPortal);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Member Management Portal");
            System.out.println("2. Employee Management Portal");
            System.out.println("3. Applicant Portal");
            System.out.println("4. Travel Management Portal");
            System.out.println("5. Activity Schedule Portal");
            System.out.println("6. Close Application");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    memberManagementPortal.manageMembers();
                    break;
                case 2:
                    employeeManagementPortal.manageEmployees();
                    break;
                case 3:
                    applicantPortal.manageApplicant();
                    break;
                case 4:
                    travelManagementPortal.manageTravel();
                    break;
                case 5:
                    activitySchedulePortal.manageSchedules();
                    break;
                case 6:
                    System.out.println("Application closing...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static int getIntInput() {
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
