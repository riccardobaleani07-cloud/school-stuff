public class Garage {

    private final Vehicle[] vehicles = new Vehicle[50];
    private int garageFullness = 0;

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null && garageFullness < vehicles.length) {
            vehicles[garageFullness++] = vehicle;
            return;
        }
        throw new IllegalArgumentException();
    }

    public double getTotalRange() {
        double totalRange = 0;
        for (int i = 0; i < garageFullness; i++) {
            totalRange += vehicles[i].getRange();
        }
        return totalRange;
    }

    public double getAverageMaxSpeed() {
        if (garageFullness == 0) return 0;

        double sumOfMaxSpeed = 0;
        for (int i = 0; i < garageFullness; i++) {
            sumOfMaxSpeed += vehicles[i].getMaxSpeed();
        }
        return sumOfMaxSpeed / garageFullness;
    }

    public Vehicle getVehicle(int index) {
        return vehicles[index];
    }

    public int getGarageFullness() {
        return garageFullness;
    }

    @Override
    public String toString() {
        return "Number of vehicles: " + garageFullness + "\n" +
               "Total range: " + getTotalRange() + "\n" +
               "Average max speed: " + getAverageMaxSpeed();
    }
}
