import java.util.HashMap;

public class ActivityTrainersPortal {
    private HashMap<Integer, Trainer> trainersMap;

    public ActivityTrainersPortal() {
        this.trainersMap = new HashMap<>();
        // Initialize pre-generated trainers
        initializePreGeneratedTrainers();
    }

    // Method to initialize pre-generated trainers
    private void initializePreGeneratedTrainers() {
        trainersMap.put(1, new Trainer(1, "John Doe", "Fitness", "john@example.com"));
        trainersMap.put(2, new Trainer(2, "Jane Smith", "Yoga", "jane@example.com"));
        trainersMap.put(3, new Trainer(3, "Alex Johnson", "Pilates", "alex@example.com"));
        trainersMap.put(4, new Trainer(4, "Emily Brown", "CrossFit", "emily@example.com"));
        trainersMap.put(5, new Trainer(5, "Michael Wilson", "Swimming", "michael@example.com"));
    }

    public void addTrainer(Trainer trainer) {
        trainersMap.put(trainer.getTrainerId(), trainer);
        System.out.println("Trainer added: " + trainer.getTrainerName());
    }

    public void removeTrainer(int trainerId) {
        if (trainersMap.containsKey(trainerId)) {
            trainersMap.remove(trainerId);
            System.out.println("Trainer removed with ID: " + trainerId);
        } else {
            System.out.println("Trainer with ID " + trainerId + " not found.");
        }
    }

    public Trainer getTrainerDetails(int trainerId) {
        return trainersMap.get(trainerId);
    }

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

    public Trainer(int trainerId, String trainerName, String expertise, String contactInfo) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.expertise = expertise;
        this.contactInfo = contactInfo;
    }

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
    public String toString() {
        return "Trainer ID: " + trainerId + ", Name: " + trainerName + ", Expertise: " + expertise + ", Contact Info: " + contactInfo;
    }
}
