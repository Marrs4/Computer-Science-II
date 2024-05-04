import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
public class ActivitySchedulePortal {
    private HashMap<Integer, Schedule> scheduleMap;
    private ManageMemberSchedule memberScheduleManager;
    private ActivityTrainersPortal trainersPortal;
    private Scanner scanner;

    public ActivitySchedulePortal(ManageMemberSchedule memberScheduleManager, ActivityTrainersPortal trainersPortal) {
        this.scheduleMap = new HashMap<>();
        this.memberScheduleManager = memberScheduleManager;
        this.trainersPortal = trainersPortal;
        this.scanner = new Scanner(System.in);
    }

    public void manageSchedules() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Activity Schedule Portal ---");
            System.out.println("1. Add Schedule with Trainer");
            System.out.println("2. Update Schedule");
            System.out.println("3. Remove Schedule");
            System.out.println("4. View All Schedules");
            System.out.println("5. Assign Trainer to Schedule");
            System.out.println("6. Remove Trainer from Schedule");
            System.out.println("7. View All Trainers");
            System.out.println("8. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addScheduleWithTrainer();
                    break;
                case 2:
                    updateSchedule();
                    break;
                case 3:
                    removeSchedule();
                    break;
                case 4:
                    viewAllSchedules();
                    break;
                case 5:
                    assignTrainerToSchedule();
                    break;
                case 6:
                    removeTrainerFromSchedule();
                    break;
                case 7:
                    viewAllTrainers();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void addScheduleWithTrainer() {
        System.out.println("Adding a new schedule...");
        System.out.print("Enter activity ID: ");
        int id = getIntInput();
        LocalDate startDate = inputDate("start");
        LocalDate endDate = inputDate("end");
        System.out.println("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Trainer ID (or -1 for no trainer): ");
        int trainerId = getIntInput();
        Trainer trainer = trainerId != -1 ? trainersPortal.getTrainerDetails(trainerId) : null;

        Schedule schedule = new Schedule(startDate, endDate, description, trainer);
        scheduleMap.put(id, schedule);
        System.out.println("Schedule set for activity ID: " + id);
    }

    private void updateSchedule() {
        System.out.println("Updating an existing schedule...");
        System.out.print("Enter activity ID: ");
        int id = getIntInput();
        if (scheduleMap.containsKey(id)) {
            LocalDate startDate = inputDate("start");
            LocalDate endDate = inputDate("end");
            System.out.println("Enter new description: ");
            String description = scanner.nextLine();

            Trainer trainer = scheduleMap.get(id).getTrainer();
            Schedule schedule = new Schedule(startDate, endDate, description, trainer);
            scheduleMap.put(id, schedule);
            System.out.println("Schedule updated for activity ID: " + id);
        } else {
            System.out.println("No existing schedule found for activity ID: " + id);
        }
    }

    private void removeSchedule() {
        System.out.println("Removing a schedule...");
        System.out.print("Enter activity ID: ");
        int id = getIntInput();
        if (scheduleMap.containsKey(id)) {
            scheduleMap.remove(id);
            System.out.println("Schedule removed for activity ID: " + id);
        } else {
            System.out.println("No schedule found to remove for activity ID: " + id);
        }
    }

    private void viewAllSchedules() {
        if (scheduleMap.isEmpty()) {
            System.out.println("No schedules found.");
        } else {
            System.out.println("All Schedules:");
            scheduleMap.forEach((id, sch) -> System.out.println("Activity ID: " + id + ", Schedule: " + sch));
        }
    }

    private void assignTrainerToSchedule() {
        System.out.print("Enter Activity ID to assign trainer: ");
        int activityId = getIntInput();
        if (scheduleMap.containsKey(activityId)) {
            System.out.print("Enter Trainer ID: ");
            int trainerId = getIntInput();
            Trainer trainer = trainersPortal.getTrainerDetails(trainerId);
            if (trainer != null) {
                scheduleMap.get(activityId).setTrainer(trainer);
                System.out.println("Trainer assigned to activity ID: " + activityId);
            } else {
                System.out.println("Trainer not found.");
            }
        } else {
            System.out.println("Schedule not found for activity ID: " + activityId);
        }
    }

    private void removeTrainerFromSchedule() {
        System.out.print("Enter Activity ID to remove trainer: ");
        int activityId = getIntInput();
        if (scheduleMap.containsKey(activityId) && scheduleMap.get(activityId).getTrainer() != null) {
            scheduleMap.get(activityId).setTrainer(null);
            System.out.println("Trainer removed from activity ID: " + activityId);
        } else {
            System.out.println("No trainer assigned or schedule not found for activity ID: " + activityId);
        }

    }
    private void viewAllTrainers() {
        System.out.println("\nAll Trainers:");
        for (Map.Entry<Integer, Trainer> entry : trainersPortal.getTrainersMap().entrySet()) {
            System.out.println("Trainer ID: " + entry.getKey() + ", Trainer: " + entry.getValue());
        }
    }
    private LocalDate inputDate(String type) {
        System.out.print("Enter " + type + " date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        try {
            return LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD. Re-enter the date.");
            return inputDate(type);  // Recursive call until a valid date is entered
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    public class Schedule {
        private LocalDate startDate;
        private LocalDate endDate;
        private String description;
        private Trainer trainer;

        public Schedule(LocalDate startDate, LocalDate endDate, String description, Trainer trainer) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
            this.trainer = trainer;
        }

        @Override
        public String toString() {
            return "Start: " + startDate + ", End: " + endDate + ", Description: " + description +
                    ", Trainer: " + (trainer != null ? trainer.getTrainerName() : "No trainer assigned");
        }

        public Trainer getTrainer() {
            return trainer;
        }

        public void setTrainer(Trainer trainer) {
            this.trainer = trainer;
        }
    }}

