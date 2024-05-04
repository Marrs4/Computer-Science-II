import java.util.Objects;

public class EmployeeScheduleEntry {
    private String date;
    private String activity;
    private int employeeId;

    public EmployeeScheduleEntry(String date, String activity, int employeeId) {
        this.date = date;
        this.activity = activity;
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeScheduleEntry that = (EmployeeScheduleEntry) o;
        return employeeId == that.employeeId &&
                Objects.equals(date, that.date) &&
                Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, activity, employeeId);
    }

    @Override
    public String toString() {
        return "EmployeeScheduleEntry{" +
                "date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}
