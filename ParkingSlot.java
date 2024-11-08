package in.ac.adit.pwj.miniproject.parking;

public class ParkingSlot {
    private int slotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;  // Reference to the vehicle parked in this slot

    // Constructor to initialize the parking slot
    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isOccupied = false;
        this.vehicle = null;
    }

    // Method to check if the slot is occupied
    public boolean isOccupied() {
        return isOccupied;
    }

    // Method to set the occupation status of the slot
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    // Method to get the vehicle parked in this slot
    public Vehicle getVehicle() {
        return vehicle;
    }

    // Method to set the vehicle parked in this slot
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Method to get the slot number
    public int getSlotNumber() {
        return slotNumber;
    }
}
