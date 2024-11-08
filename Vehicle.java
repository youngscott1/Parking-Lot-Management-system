package in.ac.adit.pwj.miniproject.parking;

public abstract class Vehicle {
    protected String vehicleId;
    protected String licensePlate;
    protected String vehicleType;

    public Vehicle(String vehicleId, String licensePlate, String vehicleType) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
