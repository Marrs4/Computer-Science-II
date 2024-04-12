public class ChangeHotelRates {
    private double currentRate;

    public ChangeHotelRates(double currentRate) {
        this.currentRate = currentRate;
    }

    public void setRate(double rate) {
        this.currentRate = rate;
        System.out.println("Hotel rate changed to $" + currentRate + " per night.");
    }

    public double getRate() {
        return currentRate;
    }

    public void manageHotelRates() {
        System.out.println("Change Hotel Rates Portal");
        System.out.println("Current hotel rate: $" + currentRate + " per night.");
    }
}