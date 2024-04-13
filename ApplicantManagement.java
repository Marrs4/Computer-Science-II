import java.util.ArrayList;
import java.util.List;

public class ApplicantManagement {
    private List<Applicant> applicants;
    private EmployeeService employeeService;
    private int applicantCount = 1;
    
    public ApplicantManagement(EmployeeService employeeService) {
        this.applicants = new ArrayList<>();
        this.employeeService = employeeService;
    }
    
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
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
        String name = approvedApplicant.getName();
        String address = approvedApplicant.getAddress();
        String email = approvedApplicant.getEmail();
        double salary = 40000;//Default salary
        int tempId = applicantCount++;
        
        Employee newEmployee = new Employee(tempId, name, address, email, salary);
        
        employeeService.addEmployee(name, address, email, salary);
        
        System.out.println("Applicant " + applicantName + " has been hired as an employee.");
            
        // Optional, you can remove the applicant from the list once transferred
        deleteApplicant(approvedApplicant);
        } else {
            System.out.println("Applicant " + applicantName + " is not found.");
        }
        } 
}
    
