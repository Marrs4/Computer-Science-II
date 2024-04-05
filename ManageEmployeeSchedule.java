public class ManageEmployeeSchedule {
    private final EmployeeScheduleEntry[] scheduleEntries = new EmployeeScheduleEntry[10];
    private int entryCount = 0;
    private final EmployeeService employeeService;

    public ManageEmployeeSchedule(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addEmployeeToSchedule(String date, String activity, int employeeId) {
        if (entryCount >= scheduleEntries.length) {
            System.out.println("Schedule is full.");
            return;
        }
        scheduleEntries[entryCount++] = new EmployeeScheduleEntry(date, activity, employeeId);
        System.out.println("Schedule added for Employee ID: " + employeeId);
    }

    public void removeEmployeeFromSchedule(String date, String activity, int employeeId) {
        for (int i = 0; i < entryCount; i++) {
            if (scheduleEntries[i].getEmployeeId() == employeeId &&
                    scheduleEntries[i].getDate().equals(date) &&
                    scheduleEntries[i].getActivity().equals(activity)) {
                System.arraycopy(scheduleEntries, i + 1, scheduleEntries, i, entryCount - i - 1);
                scheduleEntries[--entryCount] = null;
                System.out.println("Removed Employee ID: " + employeeId + " from schedule.");
                return;
            }
        }
        System.out.println("No matching schedule entry found.");
    }

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
