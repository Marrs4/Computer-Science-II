import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ApplicantManagement {
    private List<Applicant> applicants;
    private EmployeeService employeeService;
    private static final String APPLICANT_FILE = "applicants.txt";

    public ApplicantManagement(EmployeeService employeeService) {
        this.applicants = new ArrayList<>();
        this.employeeService = employeeService;
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
        saveApplicantsToFile();
    }

    public Applicant getApplicantByName(String name) {
        for (Applicant applicant : applicants) {
            if (applicant.getName().equals(name)) {
                return applicant;
            }
        }
        return null;
    }

    public void updateApplicant(Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getName().equals(updatedApplicant.getName())) {
                applicants.set(i, updatedApplicant);
                saveApplicantsToFile();
                break;
            }
        }
    }

    public void deleteApplicant(Applicant applicant) {
        applicants.remove(applicant);
    }

    public void approveAndTransferApplicant(String applicantName) {
        Applicant approvedApplicant = getApplicantByName(applicantName);

        if (approvedApplicant != null && approvedApplicant.isApproved()) {
            // Use EmployeeService to add the new employee
            employeeService.addEmployee(approvedApplicant.getName(), approvedApplicant.getAddress(), approvedApplicant.getEmail(), 40000.0);

            System.out.println("Applicant " + applicantName + " has been hired as an employee.");
            deleteApplicant(approvedApplicant);  // Optionally remove the applicant
        } else {
            System.out.println("Applicant " + applicantName + " is not found or not approved.");
        }
    }
    
    public void saveApplicantsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(APPLICANT_FILE))) {
            for (Applicant applicant : applicants) {
                // Write each applicant's information in CSV format
                writer.println(applicant.getName() + "," +
                             applicant.getAddress() + "," +
                             applicant.getPhoneNumber() + "," +
                             applicant.getEmail() + "\n");
            }
            System.out.println("Applicants saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
