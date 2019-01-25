package service;

import model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingBuilding {

    private HashMap<Integer, ArrayList<Vehicle>> parkingBuilding;
    private Integer minFloor;
    private Integer maxFloor;
    private final Integer parkingFreePlaces = 4;

    public ParkingBuilding(Integer minFloor, Integer maxFloor) {
        this.parkingBuilding = new HashMap<Integer, ArrayList<Vehicle>>();
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        initParkingBuilding(minFloor, maxFloor);
    }

    private void initParkingBuilding (Integer minFloor, Integer maxFloor) {
        for(int i=minFloor; i<=maxFloor; i++){
            if(i != 0) {
                parkingBuilding.put(i, new ArrayList<Vehicle>(4));
            }
        }
    }
    public void parkingOcupancyLevel(){
        for (Map.Entry<Integer, ArrayList<Vehicle>> entry : parkingBuilding.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue().size();
            if(maxFloor - 2 < key) {
                System.out.println("Floor: " + key + " Free parking places: " + (parkingFreePlaces - value) + " " + VehicleTypes.ElectricCar.toString());
            }
            else if(minFloor + 2 > key){
                System.out.println("Floor: " + key + " Free parking places: " + (parkingFreePlaces - value)  + " " + VehicleTypes.Van.toString());
            }
            else{
                System.out.println("Floor: " + key + " Free parking places: " + (parkingFreePlaces - value)  + " " + VehicleTypes.PetrolDiesel.toString());
            }
        }
    }

    public void addVechileToParking(int floor, Vehicle vehicle){
        if(floor != 0) {
            ArrayList<Vehicle> vehicles = parkingBuilding.get(floor);
            vehicles.add(vehicle);
            parkingBuilding.put(floor, vehicles);
        }

    }

    public void suggestedFloorForParking(int floor){
        if(floor != 0){
            System.out.println("Suggested parking floor: " + floor);
        }else {
            System.out.println("Do not exists free parking places for this vechile");
        }
    }

    public int getSuggestedParkingFloor(Vehicle vehicle){
        Integer entrance = vehicle.getEntranceFloor();
        String vehicleType = vehicle.getType();


        if(vehicleType.equals(VehicleTypes.Van.toString())){
            ArrayList<Vehicle> vehicles = parkingBuilding.get(minFloor);
            ArrayList<Vehicle> vehicles1 = parkingBuilding.get(minFloor + 1);

            if(vehicles.size() >= parkingFreePlaces && vehicles1.size() >= parkingFreePlaces){
                return 0;
            }

            if (entrance >= minFloor + 2){
                if(vehicles1.size() < parkingFreePlaces){
                    return minFloor + 1;
                } else{
                    return minFloor;
                }
            }
            if(entrance == minFloor){
                if(vehicles.size() < parkingFreePlaces) {
                    return minFloor;
                }else{
                    return minFloor + 1;
                }
            }
            if(entrance == minFloor + 1){
                if(vehicles1.size() < parkingFreePlaces){
                    return minFloor + 1;
                }else{
                    return  minFloor;
                }
            }
        } else if (vehicleType.equals(VehicleTypes.ElectricCar.toString())){
            ArrayList<Vehicle> vehicles = parkingBuilding.get(maxFloor);
            ArrayList<Vehicle> vehicles1 = parkingBuilding.get(maxFloor - 1);

            if(vehicles.size() >= parkingFreePlaces && vehicles1.size() >= parkingFreePlaces){
                return 0;
            }

            if (entrance <= maxFloor - 2){
                if(vehicles1.size() < parkingFreePlaces){
                    return  maxFloor - 1;
                } else{
                    return maxFloor;
                }
            }

            if(entrance == maxFloor){
                if(vehicles.size() < parkingFreePlaces) {
                    return maxFloor;
                }else{
                    return maxFloor -1;
                }
            }

            if(entrance == maxFloor - 1){
                if(vehicles1.size() < parkingFreePlaces){
                    return maxFloor-1;
                }else{
                    return maxFloor;
                }
            }
        }
        else if(vehicleType.equals(VehicleTypes.PetrolDiesel.toString())){
            if(ifExistsFreePlaces()){
               if(ifExistsFreePlacesPetrolCars()){
                   if(entrance >= maxFloor-1){
                       for(int i=0; i<parkingBuilding.size(); i++) {
                           Integer top = 2;
                           for (Map.Entry<Integer, ArrayList<Vehicle>> entry : parkingBuilding.entrySet()) {
                               Integer key = entry.getKey();
                               Integer value = entry.getValue().size();
                               if (key < maxFloor - 1 && key > minFloor + 1) {
                                   if (maxFloor - key == top) {
                                       top++;
                                       if (parkingFreePlaces - value > 0) {
                                           return key;
                                       }
                                   }
                               }
                           }
                       }
                   }else if(entrance <= minFloor+1) {
                       for (Map.Entry<Integer, ArrayList<Vehicle>> entry : parkingBuilding.entrySet()) {
                           Integer key = entry.getKey();
                           Integer value = entry.getValue().size();
                           if (key < maxFloor - 1 && key > minFloor + 1) {
                               if (parkingFreePlaces - value > 0) {
                                   return key;
                               }
                           }
                       }
                   }else{
                       ArrayList<Vehicle> vehicles;
                           int suggestedTop = 0;
                           int suggestedBottom = 0;
                           for(int it = entrance; it < maxFloor-1; it++){
                               vehicles = parkingBuilding.get(it);
                                if(parkingFreePlaces - vehicles.size() > 0){
                                    suggestedTop = it;
                                    break;
                                }
                           }
                           for(int it=entrance; it > 0; it--){
                               vehicles = parkingBuilding.get(it);
                               if(parkingFreePlaces - vehicles.size() > 0){
                                   suggestedBottom = it;
                                   break;
                               }
                           }
                           if(Math.abs(entrance - suggestedBottom) < Math.abs(entrance - suggestedTop) && suggestedBottom != 0){
                                return suggestedBottom;
                           }else{
                               return suggestedTop;
                           }
                   }
               }else{
                   Integer[] suggestions = { minFloor + 2, minFloor + 3, maxFloor + 1, maxFloor + 2};
                   Integer[] enableFloors = { minFloor, minFloor + 1, maxFloor -1, maxFloor};
                   int temp = entrance + 2;
                   for(int i=0; i<suggestions.length; i++){
                       suggestions[i] = Math.abs(suggestions[i] - temp);
                   }
                   for(int i=0; i<enableFloors.length; i++){
                       ArrayList<Vehicle> vehicles = parkingBuilding.get(enableFloors[i]);
                       if(parkingFreePlaces - vehicles.size() > 0){
                           enableFloors[i] = 1;
                       }
                       else{
                           enableFloors[i] = 0;
                       }
                   }

                   int tmp = 1000;
                   for(int i=0; i<suggestions.length; i++){
                       if(enableFloors[i] == 1){
                           tmp = Math.min(tmp, suggestions[i]);
                       }
                   }
                   int answer = 0;
                   for(int i=0; i<suggestions.length; i++){
                       if(enableFloors[i] == 1 && suggestions[i] == tmp){
                           answer = i;
                       }
                   }
                   if(answer == 0){
                       return minFloor;
                   }else if(answer == 1){
                       return  minFloor+1;
                   }else if(answer == 2){
                       return maxFloor-1;
                   }else{
                       return maxFloor;
                   }
               }
            }
            return 0;
        }
        return 0;
    }

    private boolean ifExistsFreePlaces (){
        int freePlaces = 0;
        for (ArrayList value : parkingBuilding.values()) {
            freePlaces += parkingFreePlaces - value.size();
        }
       return  (freePlaces > 0) ? true : false;
    }

    private boolean ifExistsFreePlacesPetrolCars(){
        int freePlacesForPetrolCars = 0;
        for (Map.Entry<Integer, ArrayList<Vehicle>> entry : parkingBuilding.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue().size();
            if(key != minFloor && key != minFloor + 1 && key != maxFloor && key != maxFloor -1){
                freePlacesForPetrolCars += parkingFreePlaces - value;
            }
        }
        return (freePlacesForPetrolCars > 0) ? true : false;
    }
}
