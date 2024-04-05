public class ScheduleEntry {
    String date;
    String activity;
    int memberId;

    public ScheduleEntry(String date, String activity, int memberId) {
        this.date = date;
        this.activity = activity;
        this.memberId = memberId;
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
