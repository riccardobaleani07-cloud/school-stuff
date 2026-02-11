public class Car extends MotorVehicle {

    protected int doors;

    public Car(double fuelCapacity, double fuelConsumption, FuelType fuelType, int doors) {
        super(fuelCapacity, fuelConsumption, fuelType);

        if (doors <= 0) {
            throw new IllegalArgumentException();
        }
        this.doors = doors;
    }

    @Override
    public String getVehicleID() {
        return getVehicleID(getNumericID()) + "-CR";
    }

    @Override
    public double getRange() {
        return getFuelCapacity()/getFuelConsumption();
    }

    @Override
    public double getMaxSpeed() {
        return 120;
    }

    @Override
    public String toString() {
        return "Car [fuel = " + getFuelCapacity() + "\n"
        + "; consumption = " + getFuelConsumption() + "\n"
        + "vehicle ID = " + getVehicleID() + "]";
    }
}
