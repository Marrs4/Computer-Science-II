public class ChangeHotelRates {
    private double currentRate;

    public ChangeHotelRates(double currentRate) {
        this.currentRate = currentRate;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public void changeRate(double newRate) {
        setCurrentRate(newRate);
        System.out.println("Hotel rate updated to: $" + newRate);
    }
}