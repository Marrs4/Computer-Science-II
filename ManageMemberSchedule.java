public class ManageMemberSchedule {
    private final ScheduleEntry[] scheduleEntries = new ScheduleEntry[10];
    private int entryCount = 0;
    private final MemberService memberService;

    public ManageMemberSchedule(MemberService memberService) {
        this.memberService = memberService;
    }

    public void addMemberToSchedule(String date, String activity, int memberId) {
        if (entryCount >= scheduleEntries.length) {
            System.out.println("Schedule is full.");
            return;
        }
        scheduleEntries[entryCount++] = new ScheduleEntry(date, activity, memberId);
        System.out.println("Schedule added for Member ID: " + memberId + " for activity: " + activity + " on " + date);
    }

    public void removeMemberFromSchedule(String date, String activity, int memberId) {
        for (int i = 0; i < entryCount; i++) {
            ScheduleEntry entry = scheduleEntries[i];
            if (entry.date.equals(date) && entry.activity.equals(activity) && entry.memberId == memberId) {
                System.arraycopy(scheduleEntries, i + 1, scheduleEntries, i, entryCount - i - 1);
                scheduleEntries[--entryCount] = null;
                System.out.println("Removed schedule for Member ID: " + memberId + " for activity: " + activity + " on " + date);
                return;
            }
        }
        System.out.println("No matching schedule found for removal.");
    }

    public void viewSchedule() {
        if (entryCount == 0) {
            System.out.println("No scheduled activities.");
            return;
        }
        System.out.println("Scheduled Activities:");
        for (int i = 0; i < entryCount; i++) {
            ScheduleEntry entry = scheduleEntries[i];
            Member member = memberService.getMemberById(entry.memberId);
            if (member != null) {
                System.out.println(entry + ", Member Details: " + member);
            } else {
                System.out.println(entry + ", Member Details: Member ID not found.");
            }
        }
    }
}
