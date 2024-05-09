public class Applicant {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean approved;

    // Constructor with approval status
    public Applicant(String name, String address, String phoneNumber, String email, boolean approved) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.approved = approved;
    }

    // Constructor without approval status (default to false)
    public Applicant(String name, String address, String phoneNumber, String email) {
        this(name, address, phoneNumber, email, false); // Default to "not approved"
    }

    // Getters and setters for all fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    @Override
    public String toString() {
        return String.format("Name: %s, Address: %s, Phone: %s, Email: %s, Approved: %s",
                name, address, phoneNumber, email, approved ? "Yes" : "No");
    }
}
