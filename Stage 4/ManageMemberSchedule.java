public class ManageMemberSchedule {
    // Array to store scheduled activities for members
    private final ScheduleEntry[] scheduleEntries = new ScheduleEntry[10];
    // Counter to track the number of entries in the schedule
    private int entryCount = 0;
    // Reference to the MemberService for accessing member details
    private final MemberService memberService;

    /**
     * Constructs a ManageMemberSchedule with a reference to the MemberService.
     *
     * @param memberService The MemberService instance to use for accessing member details.
     */
    public ManageMemberSchedule(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Adds a member to the schedule for a specified activity on a given date.
     *
     * @param date      The date of the scheduled activity.
     * @param activity  The name or description of the activity.
     * @param memberId  The ID of the member being added to the schedule.
     */
    public void addMemberToSchedule(String date, String activity, int memberId) {
        if (entryCount >= scheduleEntries.length) {
            System.out.println("Schedule is full.");
            return;
        }
        scheduleEntries[entryCount++] = new ScheduleEntry(date, activity, memberId);
        System.out.println("Schedule added for Member ID: " + memberId + " for activity: " + activity + " on " + date);
    }

    /**
     * Removes a member from the schedule for a specified activity on a given date.
     *
     * @param date      The date of the scheduled activity to be removed.
     * @param activity  The name or description of the activity to be removed.
     * @param memberId  The ID of the member being removed from the schedule.
     */
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

    /**
     * Displays all current scheduled activities along with the details of the members who
     * are scheduled for those activities. If a member's ID does not correspond to an existing
     * member, a message indicating that the member was not found is shown.
     */
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
    /**
     * Generates and returns a formatted string of all scheduled activities.
     * This string includes details of each activity along with the member details.
     *
     * @return A formatted string of all schedule entries.
     */
    public String getFormattedSchedule() {
        if (entryCount == 0) {
            return "No scheduled activities.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Scheduled Activities:\n");
        for (int i = 0; i < entryCount; i++) {
            ScheduleEntry entry = scheduleEntries[i];
            Member member = memberService.getMemberById(entry.memberId);
            if (member != null) {
                sb.append(String.format("Date: %s, Activity: %s, Member ID: %d, Member Name: %s\n",
                        entry.date, entry.activity, member.getId(), member.getName()));
            } else {
                sb.append(String.format("Date: %s, Activity: %s, Member ID: %d (not found)\n",
                        entry.date, entry.activity, entry.memberId));
            }
        }
        return sb.toString();
    }
    public String[][] getScheduleDataForTable() {
        if (entryCount == 0) {
            return new String[0][];
        }

        String[][] data = new String[entryCount][3];  // Assuming 3 columns: Member ID, Date, Activity
        for (int i = 0; i < entryCount; i++) {
            ScheduleEntry entry = scheduleEntries[i];
            Member member = memberService.getMemberById(entry.memberId);
            if (member != null) {
                data[i][0] = String.valueOf(member.getId());
                data[i][1] = entry.date;
                data[i][2] = entry.activity;
            } else {
                data[i][0] = "Not found";
                data[i][1] = entry.date;
                data[i][2] = entry.activity;
            }
        }
        return data;
    }
}


