public class Employee {
    private int id;
    private String name;
    private String address;
    private String email;
    private double salary;
    private String review = "";

    // Constructor with review field as optional
    public Employee(int id, String name, String address, String email, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
    }

    // Constructor with all fields
    public Employee(int id, String name, String address, String email, double salary, String review) {
        this(id, name, address, email, salary);
        this.review = review;
    }

    // Getters and Setters for each field
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", review='" + review + '\'' +
                '}';
    }
}
