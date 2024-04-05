public class EmployeeScheduleEntry {
    private String date;
    private String activity;
    private int employeeId;

    public EmployeeScheduleEntry(String date, String activity, int employeeId) {
        this.date = date;
        this.activity = activity;
        this.employeeId = employeeId;
    }

    public String getDate() { return date; }
    public String getActivity() { return activity; }
    public int getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return "EmployeeScheduleEntry{" +
                "date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
