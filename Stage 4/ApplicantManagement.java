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
        loadApplicantsFromFile();
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

    /** Delete an applicant and save the changes to the file. */
    public void deleteApplicant(Applicant applicant) {
        applicants.remove(applicant);
        saveApplicantsToFile(); // Save the updated state to the file
    }

    public void approveAndTransferApplicant(String applicantName, double startingSalary) {
        Applicant applicant = getApplicantByName(applicantName);
        if (applicant != null) {
            // Mark as approved and transfer
            String employeeMessage = employeeService.addEmployee(applicant.getName(), applicant.getAddress(), applicant.getEmail(), startingSalary);
            System.out.println(employeeMessage);

            // Remove the applicant from the applicant list
            applicants.remove(applicant);
            saveApplicantsToFile(); // Update the file
            System.out.println("Applicant " + applicantName + " approved and added to the employee list.");
        } else {
            System.out.println("Applicant not found or already approved.");
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
    private void loadApplicantsFromFile() {
        File file = new File(APPLICANT_FILE);
        if (!file.exists()) {
            System.out.println("Applicant file not found, starting with an empty list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 || parts.length == 5) {
                    String name = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    String email = parts[3];

                    // Ensure the order of parameters matches your `Applicant` constructor
                    applicants.add(new Applicant(name, address, phoneNumber, email));
                }
            }
            System.out.println("Applicants loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public List<Applicant> getAllApplicants() {
        return new ArrayList<>(applicants); // Return a copy of the applicants list
    }
}
