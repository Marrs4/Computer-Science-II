/**
 * Represents an employee in the club. This class encapsulates
 * key details about an employee, including their identification, personal
 * information, employment details such as salary, and performance reviews.
 */
public class Employee {
    // Unique identifier for the employee
    private int id;
    // Name of the employee
    private String name;
    // Address of the employee
    private String address;
    // Email of the employee
    private String email;
    // Current salary of the employee
    private double salary;
    // Performance review for the employee
    private String review;

    /**
     * Constructs a new Employee instance with specified details.
     * Initializes the review to an empty string indicating no review yet.
     *
     * @param id The unique identifier for the employee.
     * @param name The name of the employee.
     * @param address The address of the employee.
     * @param email The email address of the employee.
     * @param salary The current salary of the employee.
     */
    public Employee(int id, String name, String address, String email, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
        this.review = ""; // Initialize with no review
    }

    // Getter methods to access the properties of the Employee

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getReview() { return review; }

    // Setter methods to update the properties of the Employee

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setReview(String review) { this.review = review; }

    /**
     * Returns a string representation of the employee, including all
     * properties to provide a comprehensive overview of the employee's
     * current profile and status within the organization.
     *
     * @return A string detailing the employee's information.
     */
    @Override
    public String toString() {
        return "Employee {" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", Email='" + email + '\'' +
                ", Salary=" + salary +
                ", Review='" + review + '\'' +
                '}';
    }
}
