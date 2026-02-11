//import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Garage garage = new Garage();

        // create some vehicles
        Car car1 = new Car(50, 5, FuelType.ELECTRIC, 4);
        Car car2 = new Car(60, 6, FuelType.DIESEL, 2);
        Truck truck1 = new Truck(120, 20, FuelType.HYBRID,  5000);
        Motorcycle moto1 = new Motorcycle(15, FuelType.PETROL,  3);
        ElectricCar eCar1 = new ElectricCar(20, 4, FuelType.HYBRID, 4, 30);

        // add them to garage
        garage.addVehicle(car1);
        garage.addVehicle(car2);
        garage.addVehicle(truck1);
        garage.addVehicle(moto1);
        garage.addVehicle(eCar1);

        // print results
        System.out.println("Garage contents:");
        System.out.println(garage);

        // optional: loop through vehicles to show each one individually
        System.out.println("\nIndividual vehicles:");
        for (int i = 0; i < garage.getGarageFullness(); i++) {
            System.out.println("Vehicle " + (i+1) + ": " + garage.getVehicle(i));
        }
    }
}

