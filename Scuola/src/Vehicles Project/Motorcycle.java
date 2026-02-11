public class Motorcycle extends MotorVehicle {

    public Motorcycle(double fuelCapacity, FuelType fuelType, double fuelConsumption){
        super(fuelCapacity, fuelConsumption, fuelType);
        if (fuelType == FuelType.ELECTRIC) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getVehicleID() {
        return getVehicleID(getNumericID()) + "-MTCYC";
    }

    @Override
    public double getRange() {
        return getFuelCapacity()/getFuelConsumption();
    }

    @Override
    public double getMaxSpeed() {
        return 180;
    }

    @Override
    public String toString() {
        return "Motorcycle [fuel = " + getFuelCapacity() + "\n"
        + "; consumption = " + getFuelConsumption() + "\n"
        + "vehicle ID = " + getVehicleID() + "]";
    }
}
