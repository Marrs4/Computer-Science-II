import java.util.HashMap;

public class ActivityTrainersPortal {
    private HashMap<Integer, Trainer> trainersMap;

    public ActivityTrainersPortal() {
        trainersMap = new HashMap<>();
    }

    public void assignTrainer(int activityId, Trainer trainer) {
        trainersMap.put(activityId, trainer);
        System.out.println("Trainer assigned for activity ID: " + activityId);
    }

    public void removeTrainer(int activityId) {
        if (trainersMap.containsKey(activityId)) {
            trainersMap.remove(activityId);
            System.out.println("Trainer removed for activity ID: " + activityId);
        } else {
            System.out.println("No trainer assigned for activity ID: " + activityId);
        }
    }

    public Trainer getTrainerDetails(int activityId) {
        return trainersMap.get(activityId);
    }

    // Getters and setters
    public HashMap<Integer, Trainer> getTrainersMap() {
        return trainersMap;
    }

    public void setTrainersMap(HashMap<Integer, Trainer> trainersMap) {
        this.trainersMap = trainersMap;
    }
}

class Trainer {
    private int trainerId;
    private String trainerName;
    private String expertise;
    private String contactInfo;

    // Constructor
    public Trainer(int trainerId, String trainerName, String expertise, String contactInfo) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.expertise = expertise;
        this.contactInfo = contactInfo;
    }

    // Getters and setters
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}