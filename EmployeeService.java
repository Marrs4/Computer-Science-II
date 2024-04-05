public class EmployeeService {
    private Employee[] employees = new Employee[10];
    private int employeeCount = 0;

    public void addEmployee(String name, String address, String email, double salary) {
        if (employeeCount >= employees.length) {
            System.out.println("Employee list is full.");
            return;
        }
        int id = employeeCount + 1;
        employees[employeeCount++] = new Employee(id, name, address, email, salary);
        System.out.println("Employee added with ID: " + id);
    }

    public Employee getEmployeeById(int id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

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

    public void viewSalary(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            System.out.println("Salary for Employee ID " + id + ": $" + employee.getSalary());
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void writeReview(int id, String review) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setReview(review);
            System.out.println("Review written for Employee ID " + id);
        } else {
            System.out.println("Employee not found.");
        }
    }
    public String viewReview(int employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            return employee.getReview().isEmpty() ? "No review available for this employee." : employee.getReview();
        } else {
            return "Employee not found.";
        }
    }
}
