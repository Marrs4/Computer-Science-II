public class Applicant {
    private boolean isApproved;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Applicant(String name, String address, String phoneNumber, String email) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.isApproved = false; // Initialized to false by default
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email;
    }

    public void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    public boolean isApproved() {
        return isApproved;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Address: " + address + "\n" + "Phone Number: " +
                phoneNumber + "\n" + "Email: " + email;
    }
}
