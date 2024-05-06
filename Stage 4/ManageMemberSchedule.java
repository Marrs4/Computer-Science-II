import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageMemberSchedule implements Serializable {
    private List<ScheduleEntry> scheduleEntries = new ArrayList<>();
    private final MemberService memberService;
    private final String FILE_NAME = "schedule_entries.ser";

    public ManageMemberSchedule(MemberService memberService) {
        this.memberService = memberService;
        loadScheduleEntriesFromFile();
    }

    public void addMemberToSchedule(String date, String activity, int memberId) {
        ScheduleEntry newEntry = new ScheduleEntry(date, activity, memberId);
        scheduleEntries.add(newEntry);
        saveScheduleEntriesToFile();
        System.out.println("Added schedule for Member ID: " + memberId + " for activity: " + activity + " on " + date);
    }

    public void removeMemberFromSchedule(String date, String activity, int memberId) {
        boolean removed = false;
        for (int i = 0; i < scheduleEntries.size(); i++) {
            ScheduleEntry entry = scheduleEntries.get(i);
            if (entry.getDate().equals(date) && entry.getActivity().equals(activity) && entry.getMemberId() == memberId) {
                scheduleEntries.remove(i);
                removed = true;
                saveScheduleEntriesToFile();
                System.out.println("Removed schedule for Member ID: " + memberId + " for activity: " + activity + " on " + date);
                break;
            }
        }
        if (!removed) {
            System.out.println("No matching schedule found for removal.");
        }
    }

    public List<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }

    public String[][] getScheduleDataForTable() {
        if (scheduleEntries.isEmpty()) {
            return new String[0][];
        }

        String[][] data = new String[scheduleEntries.size()][3];  // Assuming 3 columns: Member ID, Date, Activity
        for (int i = 0; i < scheduleEntries.size(); i++) {
            ScheduleEntry entry = scheduleEntries.get(i);
            Member member = memberService.getMemberById(entry.getMemberId());
            if (member != null) {
                data[i][0] = String.valueOf(member.getId());
                data[i][1] = entry.getDate();
                data[i][2] = entry.getActivity();
            } else {
                data[i][0] = "Not found";
                data[i][1] = entry.getDate();
                data[i][2] = entry.getActivity();
            }
        }
        return data;
    }
    public void viewSchedule() {
        if (scheduleEntries.isEmpty()) {
            System.out.println("No scheduled activities.");
            return;
        }
        System.out.println("Scheduled Activities:");
        for (ScheduleEntry entry : scheduleEntries) {
            Member member = memberService.getMemberById(entry.getMemberId());
            if (member != null) {
                System.out.println(entry + ", Member Details: " + member);
            } else {
                System.out.println(entry + ", Member Details: Member ID not found.");
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
            scheduleEntries = (List<ScheduleEntry>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("No previous schedule entries found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading schedule entries from file: " + e.getMessage());
        }
    }
}
