package in.ac.adit.pwj.miniproject.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private List<ParkingSlot> parkingSlots; // List of all parking slots
    private Map<String, ParkingSlot> occupiedSlots; // Map to track occupied slots by vehicle ID

    // Constructor to initialize parking slots
    public ParkingManager(int numberOfSlots) {
        parkingSlots = new ArrayList<>(numberOfSlots);
        occupiedSlots = new HashMap<>();
        for (int i = 1; i <= numberOfSlots; i++) {
            parkingSlots.add(new ParkingSlot(i)); // Adding empty parking slots
        }
    }

    // Synchronized method to park a vehicle
    public synchronized int parkVehicle(Vehicle vehicle) throws InvalidSlotException {
        // Check if the vehicle is already parked
        if (occupiedSlots.containsKey(vehicle.getVehicleId())) {
            throw new InvalidSlotException("Vehicle with ID " + vehicle.getVehicleId() + " is already parked.");
        }

        // Find an empty slot
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isOccupied()) {
                slot.setOccupied(true);
                slot.setVehicle(vehicle);
                occupiedSlots.put(vehicle.getVehicleId(), slot); // Add to occupied slots map
                return slot.getSlotNumber(); // Return slot number
            }
        }
        throw new InvalidSlotException("No available parking slots.");
    }

    // Synchronized method to unpark a vehicle
    public synchronized void unParkVehicle(String vehicleId) throws InvalidSlotException {
        // Check if the vehicle is parked
        if (!occupiedSlots.containsKey(vehicleId)) {
            throw new InvalidSlotException("Vehicle with ID " + vehicleId + " is not parked.");
        }

        // Get the parking slot where the vehicle is parked
        ParkingSlot slot = occupiedSlots.get(vehicleId);
        slot.setOccupied(false);
        slot.setVehicle(null);
        occupiedSlots.remove(vehicleId); // Remove from occupied slots map
        System.out.println("Vehicle with ID " + vehicleId + " has been removed from slot " + slot.getSlotNumber());
    }

    // Inner class to handle payment
    public class Payment {
        public void processPayment(String vehicleId, double amount) throws PaymentException {
            if (amount <= 0) {
                throw new PaymentException("Invalid payment amount.");
            }
            if (!occupiedSlots.containsKey(vehicleId)) {
                throw new PaymentException("Vehicle with ID " + vehicleId + " is not parked.");
            }
            System.out.println("Payment of " + amount + " processed successfully for vehicle ID: " + vehicleId);
        }
    }
}
