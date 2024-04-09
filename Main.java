import java.util.Scanner;

/**
 * The Main class serves as the entry point to a console-based application
 * that allows users to navigate to different management portals, including
 * member and employee management, among others. This class demonstrates
 * a simple text-based user interface for interacting with various components
 * of the application.
 */
public class Main {
    // Initialize portals for managing different aspects of the application
    private static MemberManagementPortal memberManagementPortal = new MemberManagementPortal();
    private static EmployeeManagementPortal employeeManagementPortal = new EmployeeManagementPortal();
    // Scanner for reading user input from the console
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method that starts the application, presenting a menu
     * with options to access different portals or close the application.
     *
     * @param args Command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        boolean run = true;

        // Main application loop
        while(run) {
            // Display main menu options
            System.out.println("\nMain Menu");
            System.out.println("1. Member Management Portal");
            System.out.println("2. Employee Management Portal");
            System.out.println("3. Applicant Portal");
            System.out.println("4. Travel Management Portal");
            System.out.println("5. Activity Schedule Portal");
            System.out.println("6. Close Application");
            System.out.print("Enter your choice: ");

            // Read the user's menu choice
            int choice = getIntInput();

            // Handle the user's choice
            switch(choice) {
                case 1:
                    // Delegate to the member management portal
                    memberManagementPortal.manageMembers();
                    break;
                case 2:
                    // Delegate to the employee management portal
                    employeeManagementPortal.manageEmployees();
                    break;
                // Placeholder cases for additional portals
                case 3:
                    System.out.println("Applicant Portal selected.");
                    break;
                case 4:
                    System.out.println("Travel Management Portal selected.");
                    break;
                case 5:
                    System.out.println("Activity Schedule Portal selected.");
                    break;
                case 6:
                    // Exit the application
                    System.out.println("Application closing...");
                    run = false;
                    break;
                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice, please enter a number between 1 and 6.");
            }
        }
        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    /**
     * Safely reads an integer input from the user, handling any input
     * format errors and prompting again until valid input is provided.
     *
     * @return The integer input from the user.
     */
    private static int getIntInput() {
        while (true) {
            try {
                // Attempt to parse and return the user's input
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Prompt the user again in case of invalid input
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
