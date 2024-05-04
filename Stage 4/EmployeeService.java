import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    // Array to store employee objects, with a fixed size for simplicity.
    private Employee[] employees = new Employee[100];
    // Counter to track the number of employees currently in the system.
    private int employeeCount = 0;

    // List to store schedule entries
    private List<EmployeeScheduleEntry> scheduleEntries = new ArrayList<>();

    /**
     * Constructor initializes the service with five pre-made employees for immediate use.
     */
    public EmployeeService() {
        // Add predefined employees to the system upon initialization.
        addEmployee("John Doe", "1234 Main St", "john.doe@example.com", 50000);
        addEmployee("Jane Smith", "5678 Market Ave", "jane.smith@example.com", 55000);
        addEmployee("Charlie Brown", "910 Pine St", "charlie.brown@example.com", 45000);
        addEmployee("Diane Miller", "789 Elm St", "diane.miller@example.com", 52000);
        addEmployee("Evan Rogers", "654 Oak St", "evan.rogers@example.com", 48000);
    }

    /**
     * Adds a new employee to the organization.
     *
     * @param name    The name of the employee.
     * @param address The address of the employee.
     * @param email   The email of the employee.
     * @param salary  The salary of the employee.
     * @return
     */
    public String addEmployee(String name, String address, String email, double salary) {
        if (employeeCount >= employees.length) {
            System.out.println("Employee list is full.");
            return name;
        }
        int id = employeeCount + 1;
        employees[employeeCount++] = new Employee(id, name, address, email, salary);
        System.out.println("Employee added with ID: " + id);
        return name;
    }

    /**
     * Retrieves an employee by their unique ID.
     *
     * @param id The ID of the employee to find.
     * @return The Employee object if found, null otherwise.
     */
    public Employee getEmployeeById(int id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    /**
     * Updates the details of an existing employee.
     *
     * @param id      The ID of the employee to update.
     * @param name    The new name for the employee.
     * @param address The new address for the employee.
     * @param email   The new email for the employee.
     * @param salary  The new salary for the employee.
     */
    public void updateEmployee(int id, String name, String address, String email, double salary) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(name);
            employee.setAddress(address);
            employee.setEmail(email);
            employee.setSalary(salary);
            System.out.println("Employee updated: ID=" + id);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    /**
     * Deletes an employee from the organization by their ID.
     *
     * @param id The ID of the employee to delete.
     */
    public void deleteEmployee(int id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                System.arraycopy(employees, i + 1, employees, i, employeeCount - i - 1);
                employees[--employeeCount] = null;
                System.out.println("Employee with ID " + id + " has been deleted.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    /**
     * Displays the salary of an employee identified by their ID.
     *
     * @param id The ID of the employee whose salary is to be viewed.
     */
    public void viewSalary(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            System.out.println("Salary for Employee ID " + id + ": $" + employee.getSalary());
        } else {
            System.out.println("Employee not found.");
        }
    }

    /**
     * Records a performance review for an employee.
     *
     * @param id     The ID of the employee for whom the review is written.
     * @param review The content of the review.
     */
    public String writeReview(int id, String review) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                employees[i].setReview(review);
                return "Review successfully written for Employee ID: " + id;
            }
        }
        return "Employee not found with ID: " + id;
    }

    /**
     * Views the performance review of an employee identified by their ID.
     *
     * @param employeeId The ID of the employee whose review is to be viewed.
     * @return A string containing the review if available, or a message indicating no review is available.
     */
    public String viewReview(int employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            return employee.getReview().isEmpty() ? "No review available for this employee." : employee.getReview();
        } else {
            return "Employee not found.";
        }
    }

    // Method to add a schedule entry
    public String addScheduleEntry(EmployeeScheduleEntry scheduleEntry) {
        // Add the schedule entry to the list
        scheduleEntries.add(scheduleEntry);
        return "Schedule entry added successfully.";
    }

    // Method to remove a schedule entry
    public String removeScheduleEntry(EmployeeScheduleEntry scheduleEntry) {
        // Check if the schedule entry exists in the list
        if (scheduleEntries.contains(scheduleEntry)) {
            // Remove the schedule entry
            scheduleEntries.remove(scheduleEntry);
            return "Schedule entry removed successfully.";
        } else {
            return "Schedule entry not found.";
        }
    }

    // Method to retrieve all schedule entries
    public List<EmployeeScheduleEntry> getAllScheduleEntries() {
        return scheduleEntries;
    }
}
