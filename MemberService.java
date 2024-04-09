public class MemberService {
    private Member[] members = new Member[100];
    private int memberCount = 0;

    // Constructor to add pre-existing members
    public MemberService() {
        createMember("Alice Johnson", "7890 Pine Rd", "alice.johnson@example.com");
        createMember("Bob Williams", "4321 Oak St", "bob.williams@example.com");
        createMember("Charlie Smith", "1234 Maple St", "charlie.smith@example.com");
        createMember("Diane Adams", "5678 Elm St", "diane.adams@example.com");
        createMember("Evan Brown", "91011 Birch St", "evan.brown@example.com");
    }
    /**
     * Creates and adds a new member to the service.
     *
     * @param name    The name of the new member.
     * @param address The address of the new member.
     * @param email   The email of the new member.
     */
    public void createMember(String name, String address, String email) {
        if (memberCount >= members.length) {
            System.out.println("Member list is full. Cannot add more members.");
            return;
        }
        // Assuming ID starts from 1 for the first member
        int id = memberCount + 1;
        Member newMember = new Member(id, name, address, email);
        members[memberCount++] = newMember;
        System.out.println("New member created with ID: " + id);
    }

    /**
     * Retrieves a member by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member if found; otherwise, returns null.
     */
    public Member getMemberById(int id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId() == id) {
                return members[i];
            }
        }
        return null; // Member not found
    }

    /**
     * Retrieves a member's ID by their name.
     *
     * @param name The name of the member.
     * @return The ID of the member if found; otherwise, returns -1.
     */
    public int getMemberIdByName(String name) {
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getName().equalsIgnoreCase(name)) {
                return members[i].getId();
            }
        }
        return -1; // Member not found or invalid name
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
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId() == id) {
                // Shift the remaining members to the left to fill the gap
                System.arraycopy(members, i + 1, members, i, memberCount - i - 1);
                members[--memberCount] = null; // Nullify the last element and decrement count
                System.out.println("Member with ID " + id + " has been deleted.");
                return;
            }
        }
        System.out.println("Member with ID " + id + " not found. No member was deleted.");
    }

}
