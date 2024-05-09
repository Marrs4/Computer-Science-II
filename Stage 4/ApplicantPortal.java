import java.util.Scanner;

public class ApplicantPortal {
    private final ApplicantManagement applicantManagement;
    private final Scanner scanner;

    public ApplicantPortal(ApplicantManagement applicantManagement) {
        this.applicantManagement = applicantManagement;
        this.scanner = new Scanner(System.in);
        
    }

    public void createApplicant(String name, String address, String phoneNumber, String email) {
        Applicant newApplicant = new Applicant(name, address, phoneNumber, email);
        applicantManagement.addApplicant(newApplicant);
        System.out.println("Applicant created successfully.");
    }

    public void updateApplicant() {
        System.out.println("Enter applicant name to update: ");
        String name = scanner.nextLine();
        System.out.println("Enter new address: ");
        String address = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter new email: ");
        String email = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            // Update the found applicant
            foundApplicant.setName(name);
            foundApplicant.setAddress(address);
            foundApplicant.setPhoneNumber(phoneNumber);
            foundApplicant.setEmail(email);
            System.out.println("Applicant updated successfully.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void deleteApplicant() {
        System.out.println("Enter applicant name to delete: ");
        String name = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            applicantManagement.deleteApplicant(foundApplicant);
            System.out.println("Applicant deleted successfully.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void reviewApplicant() {
        System.out.println("Enter applicant name to review: ");
        String name = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            // Perform the review process
            System.out.println("Applicant found.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void manageApplicant() {
    }
}
