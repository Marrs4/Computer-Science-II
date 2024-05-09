/**
 * Represents a member with basic personal information.
 */
import java.io.Serializable;
public class Member implements Serializable{
    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * Constructs a new Member instance.
     *
     * @param id      The unique identifier for the member.
     * @param name    The name of the member.
     * @param address The address of the member.
     * @param email   The email address of the member.
     */
    public Member(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Gets the member's ID.
     *
     * @return The ID of the member.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the member's ID.
     *
     * @param id The new ID of the member.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the member's name.
     *
     * @return The name of the member.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the member's name.
     *
     * @param name The new name of the member.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the member's address.
     *
     * @return The address of the member.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the member's address.
     *
     * @param address The new address of the member.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the member's email.
     *
     * @return The email address of the member.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the member's email.
     *
     * @param email The new email address of the member.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the member.
     *
     * @return A string consisting of the member's ID, name, address, and email.
     */
    @Override
    public String toString() {
        return "Member" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", Email='" + email + '\'' +
                ' ';
    }
}
