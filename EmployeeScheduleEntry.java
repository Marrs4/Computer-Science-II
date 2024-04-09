/**
 * Represents a single entry in an employee's schedule. This class encapsulates
 * the details of a scheduled activity for an employee, including the date of
 * the activity, the nature of the activity itself, and the ID of the employee
 * to whom the schedule entry belongs.
 */
public class EmployeeScheduleEntry {
    // Date of the scheduled activity
    private String date;
    // Description or name of the activity
    private String activity;
    // The unique identifier for the employee associated with this entry
    private int employeeId;

    /**
     * Constructs a new EmployeeScheduleEntry with the specified details.
     *
     * @param date The date of the scheduled activity.
     * @param activity The description or name of the activity.
     * @param employeeId The ID of the employee associated with this schedule entry.
     */
    public EmployeeScheduleEntry(String date, String activity, int employeeId) {
        this.date = date;
        this.activity = activity;
        this.employeeId = employeeId;
    }

    /**
     * Returns the date of the scheduled activity.
     *
     * @return The date of the activity.
     */
    public String getDate() { return date; }

    /**
     * Returns the description or name of the scheduled activity.
     *
     * @return The activity description or name.
     */
    public String getActivity() { return activity; }

    /**
     * Returns the ID of the employee associated with this schedule entry.
     *
     * @return The employee ID.
     */
    public int getEmployeeId() { return employeeId; }

    /**
     * Provides a string representation of the EmployeeScheduleEntry, including
     * the date, activity, and employee ID. Useful for logging or displaying the
     * schedule entry information.
     *
     * @return A string representation of the EmployeeScheduleEntry.
     */
    @Override
    public String toString() {
        return "EmployeeScheduleEntry" +
                "date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                ", employeeId=" + employeeId +
                ' ';
    }
}
