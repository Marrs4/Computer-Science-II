/**
 * Manages the membership fees for the club. This class provides
 * functionalities to display the current membership fee and update it to a new value.
 * A default membership fee is set initially but can be changed as needed.
 */
public class ManageMembershipFees {
    // Holds the current membership fee with a default value.
    private double membershipFee = 100.00; // Default fee

    /**
     * Displays the current membership fee to the console. This method
     * allows members or administrators to view the current fee required
     * for membership in the organization.
     */
    public void displayFee() {
        System.out.println("Current membership fee: $" + membershipFee);
    }

    /**
     * Updates the membership fee to a new specified value. This method allows
     * administrators to adjust the membership fee based on the organization's
     * needs, financial requirements, or other considerations.
     *
     * @param newFee The new membership fee to be set.
     */
    public void changeFee(double newFee) {
        membershipFee = newFee;
        System.out.println("Membership fee updated to: $" + membershipFee);
    }
}
