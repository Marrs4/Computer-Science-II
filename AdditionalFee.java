class AdditionalFee {
    private int feeId;
    private String feeName;
    private double amount;
    private String description;

    public AdditionalFee(int feeId, String feeName, double amount, String description) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.amount = amount;
        this.description = description;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Fee ID: " + feeId +
                ", Fee Name: " + feeName +
                ", Amount: $" + amount +
                ", Description: " + description;
    }
}