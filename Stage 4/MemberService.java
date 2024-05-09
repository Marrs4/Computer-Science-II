import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private List<Member> members; // List to store members
    private final String fileName = "members.txt"; // File name to store member data

    // Constructor to initialize the list of members
    public MemberService() {
        members = new ArrayList<>();
        loadMembersFromFile(); // Load members from file upon initialization
    }

    /**
     * Creates and adds a new member to the service.
     *
     * @param name    The name of the new member.
     * @param address The address of the new member.
     * @param email   The email of the new member.
     */
    public void createMember(String name, String address, String email) {
        // Assuming ID starts from 1 for the first member
        int id = members.size() + 1;
        Member newMember = new Member(id, name, address, email);
        members.add(newMember);
        System.out.println("New member created with ID: " + id);
        saveMembersToFile(); // Save members to file after adding a new member
    }

    /**
     * Retrieves a member by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member if found; otherwise, returns null.
     */
    public Member getMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null; // Member not found
    }

    /**
     * Retrieves all members.
     *
     * @return A list of all members.
     */
    public ArrayList<Member> getAllMembers() {
        return (ArrayList<Member>) members;
    }

    /**
     * Updates the details of an existing member.
     *
     * @param id      The ID of the member to update.
     * @param name    The new name for the member.
     * @param address The new address for the member.
     * @param email   The new email for the member.
     */
    public void updateMember(int id, String name, String address, String email) {
        Member member = getMemberById(id);
        if (member != null) {
            member.setName(name);
            member.setAddress(address);
            member.setEmail(email);
            System.out.println("Member updated: ID=" + id);
            saveMembersToFile(); // Save members to file after updating
        } else {
            System.out.println("Member with ID " + id + " not found.");
        }
    }

    /**
     * Deletes a member from the service by their ID.
     *
     * @param id The ID of the member to delete.
     */
    public void deleteMember(int id) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId() == id) {
                members.remove(i);
                System.out.println("Member with ID " + id + " has been deleted.");
                saveMembersToFile(); // Save members to file after deletion
                return;
            }
        }
        System.out.println("Member with ID " + id + " not found. No member was deleted.");
    }

    /**
     * Loads members from a file.
     */
    private void loadMembersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            members = (List<Member>) ois.readObject();
            System.out.println("Members loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File '" + fileName + "' not found. Creating a new file.");
            saveMembersToFile(); // Create a new file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading members from file: " + e.getMessage());
        }
    }

    /**
     * Saves members to a file.
     */
    private void saveMembersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(members);
            System.out.println("Members saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving members to file: " + e.getMessage());
        }
    }
}
