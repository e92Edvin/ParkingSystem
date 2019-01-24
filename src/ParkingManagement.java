import model.Vehicle;
import service.ParkingBuilding;

public class ParkingManagement {

    public static void main(String[] args) {
        System.out.println("***Welcome to parking lot suggestion system***");
        ParkingBuilding parking = new ParkingBuilding(-2, 8);
        //for(int i=0; i< 2; i++) {
            parking.parkingOcupancyLevel();
            Vehicle vehicle = new Vehicle();
            vehicle.setEntranceFloor();
            vehicle.setType();
            System.out.println(vehicle.getEntranceFloor() + " " + vehicle.getType());
            parking.getSuggestedParkingFloor(vehicle);
            parking.parkingOcupancyLevel();
        //}

    }
}
