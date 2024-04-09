import java.util.Scanner;

public class Main {
    private static MemberManagementPortal memberManagementPortal = new MemberManagementPortal();
    // Initialize EmployeeManagementPortal
    private static EmployeeManagementPortal employeeManagementPortal = new EmployeeManagementPortal();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while(run) {
            System.out.println("\nMain Menu");
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
                    // Invoke the manageEmployees method of the EmployeeManagementPortal
                    employeeManagementPortal.manageEmployees();
                    break;
                case 3:
                    System.out.println("Applicant Portal selected.");
                    // Implementation or call to applicantPortal.manageApplicants() goes here
                    break;
                case 4:
                    System.out.println("Travel Management Portal selected.");
                    // Implementation or call to travelManagementPortal.manageTravel() goes here
                    break;
                case 5:
                    System.out.println("Activity Schedule Portal selected.");
                    // Implementation or call to activitySchedulePortal.manageActivities() goes here
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

    // Utility method to handle integer input safely
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
