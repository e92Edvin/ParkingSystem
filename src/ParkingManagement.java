import model.Vehicle;
import service.ParkingBuilding;

public class ParkingManagement {

    public static void main(String[] args) {
        System.out.println("***Welcome to parking lot suggestion system***");
        ParkingBuilding parking = new ParkingBuilding(-2, 8);

        for(int i=0; i<15; i++) {
            Vehicle vehicle = new Vehicle();
            vehicle.setEntranceFloor();
            vehicle.setType();
            System.out.println("Vehicle entrance floor: " + vehicle.getEntranceFloor() + " vehicle type " + vehicle.getType());
            int status = parking.getSuggestedParkingFloor(vehicle);
            parking.suggestedFloorForParking(status);
            parking.addVechileToParking(status, vehicle);
            parking.parkingOcupancyLevel();
        }

    }
}
