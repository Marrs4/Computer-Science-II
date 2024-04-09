import java.util.Scanner;
/**
 * Provides a console-based interface for managing employees within the club.
 * This class allows for the creation, reading, updating, and deletion of employee records,
 * as well as managing employee salaries, reviews, and schedules.
 */
public class EmployeeManagementPortal {
    // Services for managing employee data and schedules
    private final EmployeeService employeeService = new EmployeeService();
    private final ManageEmployeeSchedule manageEmployeeSchedule = new ManageEmployeeSchedule(employeeService);
    // Scanner for reading input from the console
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeManagementPortal() {
        // Constructor with field initializations done in declarations
    }
    /**
     * Starts the employee management interface. Presents a series of options to the user
     * and processes the selected option. Continues to display the options after each operation
     * until the user chooses to exit.
     */
    public void manageEmployees() {
        boolean run = true;

        while (run) {
            // Display menu options to the user
            System.out.println("\n--- Employee Management Portal ---");
            System.out.println("1. Create Employee");
            System.out.println("2. Read Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View Employee Salary");
            System.out.println("6. Employee Schedule");
            System.out.println("7. Write Employee Review");
            System.out.println("8. View Employee Review");
            System.out.println("9. Return to Main Menu");
            System.out.print("Enter your choice: ");

            // Read and process the user's choice
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createEmployee();
                    break;
                case 2:
                    readEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    viewEmployeeSalary();
                    break;
                case 6:
                    manageEmployeeSchedule();
                    break;
                case 7:
                    writeEmployeeReview();
                    break;
                case 8:
                    viewEmployeeReview();
                    break;
                // Exit the management portal
                case 9:
                    System.out.println("Exiting Employee Management Portal...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Guides the user through the process of creating a new employee.
     */
    private void createEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Employee Email: ");
        String email = scanner.nextLine();
        double salary = 0;
        boolean validSalary = false;
        while (!validSalary) {
            try {
                System.out.print("Enter Employee Salary: ");
                salary = Double.parseDouble(scanner.nextLine());
                validSalary = true; // Exit the loop if the input is a valid double
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input. Please enter a valid number.");
            }
        }
        employeeService.addEmployee(name, address, email, salary);
    }
    /**
     * Allows the user to read (view) details of a specific employee by their ID.
     */
    private void readEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = getIntInput();
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    /**
     * Facilitates updating an existing employee's information.
     */
    private void updateEmployee() {
        System.out.print("Enter Employee ID to Update: ");
        int id = getIntInput();
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        double salary = 0;
        boolean validSalary = false;
        while (!validSalary) {
            try {
                System.out.print("Enter new Salary: ");
                salary = Double.parseDouble(scanner.nextLine());
                validSalary = true; // Exit the loop if the input is a valid double
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input. Please enter a valid number.");
            }
        }
        employeeService.updateEmployee(id, name, address, email, salary);
    }

    /**
     * Enables the user to delete an employee from the system.
     */
    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = getIntInput();
        employeeService.deleteEmployee(id);
    }
    /**
     * Views the salary of an employee.
     */
    private void viewEmployeeSalary() {
        System.out.print("Enter Employee ID: ");
        int id = getIntInput();
        employeeService.viewSalary(id);
    }

    /**
     * Manages the scheduling interface for employee activities.
     */
    private void manageEmployeeSchedule() {
        boolean running = true;
        while (running) {
            System.out.println("\nEmployee Schedule Management");
            System.out.println("1. Add Employee to Schedule");
            System.out.println("2. Remove Employee from Schedule");
            System.out.println("3. View Employee Schedule");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addEmployeeToSchedule();
                    break;
                case 2:
                    removeEmployeeFromSchedule();
                    break;
                case 3:
                    manageEmployeeSchedule.viewSchedule();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addEmployeeToSchedule() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        System.out.print("Enter Date (e.g., MM-DD-YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Enter Activity: ");
        String activity = scanner.nextLine();

        manageEmployeeSchedule.addEmployeeToSchedule(date, activity, employeeId);
    }

    private void removeEmployeeFromSchedule() {
        System.out.print("Enter Employee ID: ");
        int employeeId = getIntInput();
        System.out.print("Enter Date (e.g., MM-DD-YYYY) of the activity to remove: ");
        String date = scanner.nextLine();
        System.out.print("Enter Activity to remove: ");
        String activity = scanner.nextLine();

        manageEmployeeSchedule.removeEmployeeFromSchedule(date, activity, employeeId);
    }
    /**
     * Provides an interface to write a review for an employee.
     */
    private void writeEmployeeReview() {
        System.out.print("Enter Employee ID: ");
        int id = getIntInput();
        System.out.print("Write Review: ");
        String review = scanner.nextLine();
        employeeService.writeReview(id, review);
    }
    /**
     * Displays the review of an employee.
     */
    private void viewEmployeeReview() {
        System.out.print("Enter Employee ID to view the review: ");
        int id = getIntInput();
        String review = employeeService.viewReview(id);
        System.out.println("Review for Employee ID " + id + ": " + review);
    }
    /**
     * Reads an integer from the console input safely, asking again if input is not an integer.
     *
     * @return The user input as an integer.
     */
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        new EmployeeManagementPortal().manageEmployees();
    }
}
