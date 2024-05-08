import java.util.Scanner;

public class ApplicantPortal {
    private ApplicantManagement applicantManagement;
    private Scanner scanner;

    public ApplicantPortal(ApplicantManagement applicantManagement) {
        this.applicantManagement = applicantManagement;
        this.scanner = new Scanner(System.in);
        
    }

    public void createApplicant(String name, String address, String phoneNumber, String email) {
        Applicant newApplicant = new Applicant(name, address, phoneNumber, email);
        applicantManagement.addApplicant(newApplicant);
        System.out.println("Applicant created successfully.");
    }

    public void updateApplicant(String name, String address, String phoneNumber, String email) {
        System.out.println("Enter applicant name to update: ");
        name = scanner.nextLine();
        System.out.println("Enter new address: ");
        address = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.println("Enter new email: ");
        email = scanner.nextLine();
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

    public void deleteApplicant(String name) {
        System.out.println("Enter applicant name to delete: ");
        name = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            applicantManagement.deleteApplicant(foundApplicant);
            System.out.println("Applicant deleted successfully.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void reviewApplicant(String name) {
        System.out.println("Enter applicant name to review: ");
        name = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            // Perform the review process
            System.out.println("Applicant found.");
        } else {
            System.out.println("Applicant not found.");
        }
    }
}
