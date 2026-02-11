public class ElectricCar extends Car {

    protected double batteryMaxCapacity; // 0.3 is 10 km

    public ElectricCar(double fuelCapacity, double fuelConsumption, FuelType fuelType, int doors, double batteryMaxCapacity) {
        super(fuelCapacity, fuelConsumption, fuelType, doors);

        if (batteryMaxCapacity <= 0 ||
            !(fuelType == FuelType.ELECTRIC || fuelType == FuelType.HYBRID)) {
            throw new IllegalArgumentException();
        }
        this.batteryMaxCapacity = batteryMaxCapacity;
    }

    @Override
    public String getVehicleID() {
        return "E" + super.getVehicleID();
    }

    public double getElectricCapacity() {
        return batteryMaxCapacity;
    }

    public double getElectricRange() {
        return batteryMaxCapacity*30;
    }

    @Override
    public double getRange() {
        return super.getRange() + getElectricRange();
    }

    @Override
    public String toString() {
        return "Electric Car [fuel = " + (getFuelCapacity() + getElectricCapacity()) + "\n"
        + "; consumption = " + getFuelConsumption() + "\n"
        + "vehicle ID = " + getVehicleID() + "]";
    }
}
