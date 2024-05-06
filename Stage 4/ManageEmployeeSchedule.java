import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeSchedule implements Serializable {
    private final EmployeeService employeeService;
    private final List<EmployeeScheduleEntry> scheduleEntries;
    private final String FILE_NAME = "employee_schedule_entries.ser";

    public ManageEmployeeSchedule(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.scheduleEntries = new ArrayList<>();
        loadScheduleEntriesFromFile();
    }

    public void addEmployeeToSchedule(String date, String activity, int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            scheduleEntries.add(new EmployeeScheduleEntry(date, activity, employeeId));
            saveScheduleEntriesToFile();
            System.out.println("Employee schedule entry added successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void removeEmployeeFromSchedule(String date, String activity, int employeeId) {
        EmployeeScheduleEntry entryToRemove = new EmployeeScheduleEntry(date, activity, employeeId);
        if (scheduleEntries.contains(entryToRemove)) {
            scheduleEntries.remove(entryToRemove);
            saveScheduleEntriesToFile();
            System.out.println("Employee schedule entry removed successfully.");
        } else {
            System.out.println("Employee schedule entry not found.");
        }
    }

    public void viewSchedule() {
        if (scheduleEntries.isEmpty()) {
            System.out.println("No schedule entries.");
        } else {
            System.out.println("Employee Schedule:");
            for (EmployeeScheduleEntry entry : scheduleEntries) {
                Employee employee = employeeService.getEmployeeById(entry.getEmployeeId());
                if (employee != null) {
                    System.out.println(entry + ", Employee Details: " + employee);
                } else {
                    System.out.println(entry + ", Employee Details: Employee ID not found.");
                }
            }
        }
    }

    private void saveScheduleEntriesToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(scheduleEntries);
        } catch (IOException e) {
            System.err.println("Error saving schedule entries to file: " + e.getMessage());
        }
    }

    private void loadScheduleEntriesFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            scheduleEntries.addAll((List<EmployeeScheduleEntry>) inputStream.readObject());
        } catch (FileNotFoundException e) {
            System.err.println("No previous schedule entries found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading schedule entries from file: " + e.getMessage());
        }
    }
}
