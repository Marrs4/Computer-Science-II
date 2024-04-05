public class ManageMembershipFees {
    private double membershipFee = 100.00; // Default fee

    // Display the current membership fee
    public void displayFee() {
        System.out.println("Current membership fee: $" + membershipFee);
    }

    // Change the membership fee to a new value
    public void changeFee(double newFee) {
        membershipFee = newFee;
        System.out.println("Membership fee updated to: $" + membershipFee);
    }
}
