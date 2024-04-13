public class Applicant {
    private boolean isApproved;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    
    public Applicant(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmail(String email) {
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
