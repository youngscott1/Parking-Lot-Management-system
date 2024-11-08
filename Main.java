package in.ac.adit.pwj.miniproject.parking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Parking Management System");

        // Initialize the parking manager with a user-specified number of slots
        System.out.print("Enter the number of parking slots: ");
        int numberOfSlots = scanner.nextInt();
        ParkingManager parkingManager = new ParkingManager(numberOfSlots);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Park Vehicle\n2. Unpark Vehicle\n3. Make Payment\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1: // Park a vehicle
                    System.out.print("Enter Vehicle Type (Car/Bike): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter Vehicle ID: ");
                    String vehicleId = scanner.nextLine();

                    System.out.print("Enter License Plate: ");
                    String licensePlate = scanner.nextLine();

                    Vehicle vehicle;
                    if (type.equalsIgnoreCase("Car")) {
                        vehicle = new Car(vehicleId, licensePlate);
                    } else if (type.equalsIgnoreCase("Bike")) {
                        vehicle = new Bike(vehicleId, licensePlate);
                    } else {
                        System.out.println("Invalid vehicle type. Try again.");
                        break;
                    }

                    try {
                        int slotNumber = parkingManager.parkVehicle(vehicle);
                        System.out.println(type + " parked in slot: " + slotNumber);
                    } catch (InvalidSlotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // Unpark a vehicle
                    System.out.print("Enter Vehicle ID to unpark: ");
                    String unparkVehicleId = scanner.nextLine();

                    try {
                        parkingManager.unParkVehicle(unparkVehicleId);
                        System.out.println("Vehicle with ID " + unparkVehicleId + " has been unparked.");
                    } catch (InvalidSlotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Make payment
                    System.out.print("Enter Vehicle ID to make payment: ");
                    String paymentVehicleId = scanner.nextLine();

                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();

                    ParkingManager.Payment payment = parkingManager.new Payment();
                    try {
                        payment.processPayment(paymentVehicleId, amount);
                        System.out.println("Payment of " + amount + " processed successfully for vehicle ID: " + paymentVehicleId);
                    } catch (PaymentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4: // Exit
                    exit = true;
                    System.out.println("Thank you for using the Parking Management System!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }
}
