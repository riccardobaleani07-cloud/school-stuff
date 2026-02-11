public class Truck extends MotorVehicle {

    protected double loadWeight;

    public Truck(double fuelCapacity, double fuelConsumption, FuelType fuelType, double loadWeight){
        super(fuelCapacity, fuelConsumption, fuelType);

        if (loadWeight <= 0 || fuelType == FuelType.ELECTRIC) {
            throw new IllegalArgumentException();
        }
        this.loadWeight = loadWeight;
    }

    @Override
    public String getVehicleID() {
        return getVehicleID(getNumericID()) + "-TRK";
    }

    @Override
    public double getRange() {
        return getFuelCapacity()/getFuelConsumption();
    }

    @Override
    public double getMaxSpeed() {
        return 90;
    }

    @Override
    public String toString() {
        return "Truck [fuel = " + getFuelCapacity() + "\n"
        + "; consumption = " + getFuelConsumption() + "\n"
        + "vehicle ID = " + getVehicleID() + "]";
    }
}
