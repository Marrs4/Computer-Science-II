import java.io.Serializable;

public class ScheduleEntry implements Serializable {
    private String date;
    private String activity;
    private int memberId;

    public ScheduleEntry(String date, String activity, int memberId) {
        this.date = date;
        this.activity = activity;
        this.memberId = memberId;
    }

    // Getter methods for date, activity, and memberId
    public String getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "ScheduleEntry{" +
                "date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
