import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String SCHEDULE_FILE = "schedules.txt";
    private List<Employee> employees = new ArrayList<>();
    private List<EmployeeScheduleEntry> scheduleEntries = new ArrayList<>();
    private int nextId = 1;

    public EmployeeService() {
        loadEmployeesFromFile();
        loadScheduleEntriesFromFile();
    }

    public String addEmployee(String name, String address, String email, double salary) {
        Employee newEmployee = new Employee(nextId++, name, address, email, salary);
        employees.add(newEmployee);
        saveEmployeesToFile();
        return "Employee " + newEmployee.getName() + " added successfully!";
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employeeOpt = employees.stream().filter(emp -> emp.getId() == id).findFirst();
        return employeeOpt.orElse(null);
    }

    public void updateEmployee(int id, String name, String address, String email, double salary) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(name);
            employee.setAddress(address);
            employee.setEmail(email);
            employee.setSalary(salary);
            saveEmployeesToFile();
            System.out.println("Employee updated: ID=" + id);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void deleteEmployee(int id) {
        employees.removeIf(emp -> emp.getId() == id);
        saveEmployeesToFile();
        System.out.println("Employee with ID " + id + " has been deleted.");
    }

    public String addScheduleEntry(EmployeeScheduleEntry scheduleEntry) {
        scheduleEntries.add(scheduleEntry);
        saveScheduleEntriesToFile();
        return "Schedule entry added successfully.";
    }

    public String removeScheduleEntry(EmployeeScheduleEntry scheduleEntry) {
        if (scheduleEntries.remove(scheduleEntry)) {
            saveScheduleEntriesToFile();
            return "Schedule entry removed successfully.";
        } else {
            return "Schedule entry not found.";
        }
    }

    public List<EmployeeScheduleEntry> getAllScheduleEntries() {
        return new ArrayList<>(scheduleEntries);
    }

    public String writeReview(int id, String review) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setReview(review);
            saveEmployeesToFile();
            return "Review successfully written for Employee ID: " + id;
        } else {
            return "Employee not found with ID: " + id;
        }
    }

    public String viewSalary(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            return "Salary for Employee ID " + id + ": $" + employee.getSalary();
        } else {
            return "Employee not found.";
        }
    }

    public String viewReview(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            return employee.getReview().isEmpty() ? "No review available for this employee." : employee.getReview();
        } else {
            return "Employee not found.";
        }
    }

    // Method to get all employee IDs
    public List<Integer> getAllEmployeeIds() {
        List<Integer> ids = new ArrayList<>();
        for (Employee employee : employees) {
            ids.add(employee.getId());
        }
        return ids;
    }

    private void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String address = parts[2];
                String email = parts[3];
                double salary = Double.parseDouble(parts[4]);
                String review = parts.length > 5 ? parts[5] : "";
                employees.add(new Employee(id, name, address, email, salary, review));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
        }
    }

    private void saveEmployeesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                writer.println(employee.getId() + "," + employee.getName() + "," +
                        employee.getAddress() + "," + employee.getEmail() + "," +
                        employee.getSalary() + "," + employee.getReview());
            }
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    private void loadScheduleEntriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCHEDULE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];
                String activity = parts[1];
                int employeeId = Integer.parseInt(parts[2]);
                scheduleEntries.add(new EmployeeScheduleEntry(date, activity, employeeId));
            }
        } catch (IOException e) {
            System.out.println("Error loading schedule entries from file: " + e.getMessage());
        }
    }

    private void saveScheduleEntriesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCHEDULE_FILE))) {
            for (EmployeeScheduleEntry entry : scheduleEntries) {
                writer.println(entry.getDate() + "," + entry.getActivity() + "," + entry.getEmployeeId());
            }
        } catch (IOException e) {
            System.out.println("Error saving schedule entries to file: " + e.getMessage());
        }
    }
}
