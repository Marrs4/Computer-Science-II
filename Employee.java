public class Employee {
    private int id;
    private String name;
    private String address;
    private String email;
    private double salary;
    private String review;

    public Employee(int id, String name, String address, String email, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
        this.review = ""; // Initialize with no review
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getReview() { return review; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setReview(String review) { this.review = review; }

    @Override
    public String toString() {
        return "Employee" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", Email='" + email + '\'' +
                ", Salary=" + salary +
                ", Review='" + review + '\'' +
                ' ';
    }
}
