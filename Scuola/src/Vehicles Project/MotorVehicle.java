public abstract class MotorVehicle implements Vehicle {

    protected double fuelCapacity;
    protected double fuelConsumption;
    protected FuelType fuelType;

    private static int counterID = 0;
    private final int vehicleID;

    public MotorVehicle(double fuelCapacity, double fuelConsumption, FuelType fuelType) {
        if (fuelCapacity <= 0 || fuelConsumption <= 0 || fuelType == null) {
            throw new IllegalArgumentException();
        }
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;

        counterID++;
        this.vehicleID = counterID;
    }

    protected int getNumericID() {
        return vehicleID;
    }

    public String getVehicleID(int vehicleID) {
        return vehicleID + "-MV";
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }
}
