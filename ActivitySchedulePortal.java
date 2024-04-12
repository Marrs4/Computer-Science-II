import java.util.HashMap;
import java.util.Date;

public class ActivitySchedulePortal<Schedule> {
    private HashMap<Integer, Schedule> scheduleMap;
    private ManageMemberSchedule memberScheduleManager;

    public ActivitySchedulePortal(ManageMemberSchedule memberScheduleManager) {
        scheduleMap = new HashMap<>();
        this.memberScheduleManager = memberScheduleManager;
    }

    public void setSchedule(int activityId, Date startDate, Date endDate, String description) {
        Schedule schedule = new Schedule(startDate, endDate, description);
        scheduleMap.put(activityId, schedule);
        // You may need additional logic here to handle the integration with ManageMemberSchedule
        // For now, I'm just demonstrating how to set the schedule for an activity.
        System.out.println("Schedule set for activity ID: " + activityId);
    }

    public Schedule getSchedule(int activityId) {
        return scheduleMap.get(activityId);
    }

    public void updateSchedule(int activityId, Date startDate, Date endDate, String description) {
        Schedule schedule = new Schedule(startDate, endDate, description);
        scheduleMap.put(activityId, schedule);
        // You may need additional logic here to handle the integration with ManageMemberSchedule
        // For now, I'm just demonstrating how to update the schedule for an activity.
        System.out.println("Schedule updated for activity ID: " + activityId);
    }

    public void removeSchedule(int activityId) {
        scheduleMap.remove(activityId);
        // You may need additional logic here to handle the removal of a schedule
        // For now, I'm just removing the schedule from the map.
        System.out.println("Schedule removed for activity ID: " + activityId);
    }

    public void viewAllSchedules() {
        if (scheduleMap.isEmpty()) {
            System.out.println("No schedules found.");
        } else {
            System.out.println("All Schedules:");
            for (int activityId : scheduleMap.keySet()) {
                System.out.println("Activity ID: " + activityId + ", Schedule: " + scheduleMap.get(activityId));
            }
        }
    }

    // Getters and setters

    public HashMap<Integer, Schedule> getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(HashMap<Integer, Schedule> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public ManageMemberSchedule getMemberScheduleManager() {
        return memberScheduleManager;
    }

    public void setMemberScheduleManager(ManageMemberSchedule memberScheduleManager) {
        this.memberScheduleManager = memberScheduleManager;
    }
}