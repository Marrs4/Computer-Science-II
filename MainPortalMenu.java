import java.util.Scanner;

public class MainPortalMenu {
    private static MemberManagementPortal memberManagementPortal = new MemberManagementPortal();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while(run) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Member Management Portal");
            System.out.println("2. Employee Management Portal");
            System.out.println("3. Applicant Portal");
            System.out.println("4. Travel Management Portal");
            System.out.println("5. Activity Schedule Portal");
            System.out.println("6. Close Application");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch(choice) {
                case 1:
                    memberManagementPortal.manageMembers();
                    break;
                case 2:
                    System.out.println("Employee Management Portal selected.");
                    // Implement or call employeeManagementPortal.manageEmployees();
                    break;
                case 3:
                    System.out.println("Applicant Portal selected.");
                    // Implement or call applicantPortal.manageApplicants();
                    break;
                case 4:
                    System.out.println("Travel Management Portal selected.");
                    // Implement or call travelManagementPortal.manageTravel();
                    break;
                case 5:
                    System.out.println("Activity Schedule Portal selected.");
                    // Implement or call activitySchedulePortal.manageActivities();
                    break;
                case 6:
                    System.out.println("Application closing...");
                    run = false; // Exit the loop and close the application
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    // Utility method to handle integer input
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
