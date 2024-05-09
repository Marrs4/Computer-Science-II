import java.util.Scanner;
import java.util.List;

/** Interface for user interaction with the applicant management system. */
public class ApplicantPortal {
    private final ApplicantManagement applicantManagement;
    private final Scanner scanner;

    public ApplicantPortal(ApplicantManagement applicantManagement) {
        this.applicantManagement = applicantManagement;
        this.scanner = new Scanner(System.in);
    }

    /** A console interface to manage applicants. */
    public void manageApplicant() {
        boolean run = true;

        while (run) {
            System.out.println("--- Applicant Portal ---");
            System.out.println("1. Create Applicant");
            System.out.println("2. Search Applicant");
            System.out.println("3. Update Applicant");
            System.out.println("4. Delete Applicant");
            System.out.println("5. Review Applicant");
            System.out.println("6. Return to Main Menu");
            int userChoice = getInput();

            switch (userChoice) {
                case 1:
                    createApplicantUsingInput();
                    break;
                case 2:
                    readApplicant();
                    break;
                case 3:
                    updateApplicantUsingInput();
                    break;
                case 4:
                    deleteApplicantUsingInput();
                    break;
                case 5:
                    reviewApplicantUsingInput();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Helper methods to collect user input and execute other management tasks
    private void createApplicantUsingInput() {
        System.out.println("Enter applicant name: ");
        String name = scanner.nextLine();
        System.out.println("Enter applicant address: ");
        String address = scanner.nextLine();
        System.out.println("Enter applicant phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter applicant email: ");
        String email = scanner.nextLine();

        createApplicant(name, address, phoneNumber, email);
    }

    private void updateApplicantUsingInput() {
        System.out.println("Enter applicant name to update: ");
        String name = scanner.nextLine();
        System.out.println("Enter new address: ");
        String address = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter new email: ");
        String email = scanner.nextLine();

        updateApplicant(name, address, phoneNumber, email);
    }
    private void readApplicant() {
        System.out.println("Enter applicant name to search for: ");
        String searchName = scanner.nextLine();
        Applicant foundApplicant = applicantManagement.getApplicantByName(searchName);
        if (foundApplicant != null) {
            System.out.println("Applicant found: ");
            System.out.println(foundApplicant);
        } else {
            System.out.println("Applicant not found.");
        }
    }
    private void deleteApplicantUsingInput() {
        System.out.print("Enter applicant name to delete: ");
        String name = scanner.nextLine();
        deleteApplicant(name);
    }

    private void reviewApplicantUsingInput() {
        System.out.print("Enter applicant name to review: ");
        String name = scanner.nextLine();

        // Ask whether to approve or reject the applicant
        System.out.println("Do you want to approve or reject this applicant?");
        System.out.println("1. Approve");
        System.out.println("2. Reject");

        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice (1 for Approve, 2 for Reject): ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) {
                    break; // Exit the loop on a valid choice
                }
                System.out.println("Invalid choice. Please enter 1 to Approve or 2 to Reject.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        boolean approve = (choice == 1);

        // Call the reviewApplicant method with the name and approval status
        reviewApplicant(name, approve);
    }

    /** Utility method to get an integer input from the user. */
    private int getInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Existing create, update, delete, and review methods
    public void createApplicant(String name, String address, String phoneNumber, String email) {
        Applicant newApplicant = new Applicant(name, address, phoneNumber, email);
        applicantManagement.addApplicant(newApplicant);
        System.out.println("Applicant created successfully.");
    }

    public void updateApplicant(String name, String address, String phoneNumber, String email) {
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            foundApplicant.setAddress(address);
            foundApplicant.setPhoneNumber(phoneNumber);
            foundApplicant.setEmail(email);
            applicantManagement.updateApplicant(foundApplicant);
            System.out.println("Applicant updated successfully.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void deleteApplicant(String name) {
        Applicant foundApplicant = applicantManagement.getApplicantByName(name);
        if (foundApplicant != null) {
            applicantManagement.deleteApplicant(foundApplicant);
            System.out.println("Applicant deleted successfully.");
        } else {
            System.out.println("Applicant not found.");
        }
    }

    /**
     * Review an applicant by name and decide whether to approve or reject.
     * @param name The name of the applicant.
     * @param approve `true` to approve the applicant, `false` to reject.
     */
    public void reviewApplicant(String name, boolean approve) {
        Applicant applicant = applicantManagement.getApplicantByName(name);
        if (applicant != null) {
            if (approve) {
                double startingSalary = 50000.0; // Example starting salary
                applicantManagement.approveAndTransferApplicant(name, startingSalary); // Transfer to the employee list
            } else {
                applicantManagement.deleteApplicant(applicant); // Delete if rejected
                System.out.println("Applicant " + name + " rejected and removed from the system.");
            }
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public List<Applicant> getAllApplicants() {
        return applicantManagement.getAllApplicants();
    }
}
