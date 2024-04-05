import java.util.Scanner;
public class EmployeeManagementPortal {
    private final EmployeeService employeeService = new EmployeeService();
    private final ManageEmployeeSchedule manageEmployeeSchedule = new ManageEmployeeSchedule(employeeService);
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeManagementPortal() {
        // Initialization already done in the field declarations
    }

    public void manageEmployees() {
        boolean run = true;

        while (run) {
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
                case 9:
                    System.out.println("Exiting Employee Management Portal...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

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


    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = getIntInput();
        employeeService.deleteEmployee(id);
    }

    private void viewEmployeeSalary() {
        System.out.print("Enter Employee ID: ");
        int id = getIntInput();
        employeeService.viewSalary(id);
    }

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

    private void writeEmployeeReview() {
        System.out.print("Enter Employee ID: ");
        int id = getIntInput();
        System.out.print("Write Review: ");
        String review = scanner.nextLine();
        employeeService.writeReview(id, review);
    }
    private void viewEmployeeReview() {
        System.out.print("Enter Employee ID to view the review: ");
        int id = getIntInput();
        String review = employeeService.viewReview(id);
        System.out.println("Review for Employee ID " + id + ": " + review);
    }

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
