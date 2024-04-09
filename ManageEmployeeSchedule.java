/**
 * Manages the scheduling of activities for employees within the club.
 * This class allows for adding, removing, and viewing schedule entries for employees.
 * It leverages an array to store schedule entries and relies on an instance of
 * EmployeeService to interact with employee data.
 */
public class ManageEmployeeSchedule {
    // Array to store employee schedule entries.
    private final EmployeeScheduleEntry[] scheduleEntries = new EmployeeScheduleEntry[100];
    // Counter to track the number of schedule entries currently in the system.
    private int entryCount = 0;
    // Reference to the employee service used to manage employee data.
    private final EmployeeService employeeService;

    /**
     * Constructs a ManageEmployeeSchedule instance with a reference to an EmployeeService.
     *
     * @param employeeService The EmployeeService instance to use for employee data operations.
     */
    public ManageEmployeeSchedule(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Adds a new schedule entry for an employee.
     *
     * @param date The date of the scheduled activity.
     * @param activity The description of the scheduled activity.
     * @param employeeId The ID of the employee to whom the schedule entry belongs.
     */
    public void addEmployeeToSchedule(String date, String activity, int employeeId) {
        if (entryCount >= scheduleEntries.length) {
            System.out.println("Schedule is full.");
            return;
        }
        scheduleEntries[entryCount++] = new EmployeeScheduleEntry(date, activity, employeeId);
        System.out.println("Schedule added for Employee ID: " + employeeId);
    }

    /**
     * Removes a schedule entry for an employee based on the date and activity description.
     *
     * @param date The date of the scheduled activity to remove.
     * @param activity The description of the scheduled activity to remove.
     * @param employeeId The ID of the employee whose schedule entry is to be removed.
     */
    public void removeEmployeeFromSchedule(String date, String activity, int employeeId) {
        for (int i = 0; i < entryCount; i++) {
            if (scheduleEntries[i].getEmployeeId() == employeeId &&
                    scheduleEntries[i].getDate().equals(date) &&
                    scheduleEntries[i].getActivity().equals(activity)) {
                // Shift remaining entries to fill the gap.
                System.arraycopy(scheduleEntries, i + 1, scheduleEntries, i, entryCount - i - 1);
                scheduleEntries[--entryCount] = null; // Nullify the last element and decrement count.
                System.out.println("Removed Employee ID: " + employeeId + " from schedule.");
                return;
            }
        }
        System.out.println("No matching schedule entry found.");
    }

    /**
     * Displays all scheduled activities for employees.
     * Lists each schedule entry currently stored.
     */
    public void viewSchedule() {
        if (entryCount == 0) {
            System.out.println("No scheduled activities.");
            return;
        }
        System.out.println("Scheduled Activities:");
        for (int i = 0; i < entryCount; i++) {
            System.out.println(scheduleEntries[i].toString());
        }
    }
}
