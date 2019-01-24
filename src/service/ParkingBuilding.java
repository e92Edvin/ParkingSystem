package service;

import model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
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

    public void initParkingBuilding (Integer minFloor, Integer maxFloor) {
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

    public void getSuggestedParkingFloor(Vehicle vehicle){
        Integer entrance = vehicle.getEntranceFloor();
        String vehicleType = vehicle.getType();

        Vehicle v = new Vehicle();
        ArrayList<Vehicle> vehicles12 = parkingBuilding.get(6);
        vehicles12.add(v); vehicles12.add(v);vehicles12.add(v);vehicles12.add(v);
        parkingBuilding.put(6,vehicles12);
        parkingBuilding.put(5,vehicles12);
        parkingBuilding.put(4,vehicles12);
        parkingBuilding.put(3,vehicles12);
        parkingBuilding.put(2,vehicles12);
        parkingBuilding.put(1,vehicles12);
       // parkingBuilding.put(-2,vehicles12);
       // parkingBuilding.put(7,vehicles12);
        //parkingBuilding.put(-1,vehicles12);

        if(vehicleType.equals(VehicleTypes.Van.toString())){
            ArrayList<Vehicle> vehicles = parkingBuilding.get(minFloor);
            ArrayList<Vehicle> vehicles1 = parkingBuilding.get(minFloor + 1);

            if(vehicles.size() >= parkingFreePlaces && vehicles1.size() >= parkingFreePlaces){
                System.out.println("Do not exists free parking places for " + VehicleTypes.Van.toString());
                return;
            }

            if (entrance >= minFloor + 2){
                if(vehicles1.size() < parkingFreePlaces){
                    System.out.println("Suggested parking: " + (minFloor + 1));
                   /* Vehicle vch= new Vehicle();
                    vehicles1.add(vch);
                    parkingBuilding.put((minFloor + 1),vehicles1);*/
                    return;
                } else{
                    System.out.println("Suggested parking: " + minFloor);
                    return;
                }
            }
            if(entrance == minFloor){
                if(vehicles.size() < parkingFreePlaces) {
                    System.out.println("Suggested parking: " + minFloor);
                }else{
                    System.out.println("Suggested parking: " + (minFloor + 1));
                }
            }
            if(entrance == minFloor + 1){
                if(vehicles1.size() < parkingFreePlaces){
                    System.out.println("Suggested parking: " + (minFloor + 1));
                }else{
                    System.out.println("Suggested parking: " + minFloor);
                }
            }
        } else if (vehicleType.equals(VehicleTypes.ElectricCar.toString())){
            ArrayList<Vehicle> vehicles = parkingBuilding.get(maxFloor);
            ArrayList<Vehicle> vehicles1 = parkingBuilding.get(maxFloor - 1);

            if(vehicles.size() >= parkingFreePlaces && vehicles1.size() >= parkingFreePlaces){
                System.out.println("Do not exists free parking places for " + VehicleTypes.ElectricCar.toString());
                return;
            }

            if (entrance <= maxFloor - 2){
                if(vehicles1.size() < parkingFreePlaces){
                    System.out.println("Suggested parking: " + (maxFloor - 1));
                } else{
                    System.out.println("Suggested parking: " + maxFloor);
                }
            }

            if(entrance == maxFloor){
                if(vehicles.size() < parkingFreePlaces) {
                    System.out.println("Suggested parking: " + maxFloor);
                }else{
                    System.out.println("Suggested parking: " + (maxFloor - 1));
                }
            }

            if(entrance == maxFloor - 1){
                if(vehicles1.size() < parkingFreePlaces){
                    System.out.println("Suggested parking: " + (maxFloor - 1));
                }else{
                    System.out.println("Suggested parking: " + maxFloor);
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
                                           System.out.println("Suggested: " + key);
                                           return;
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
                                   System.out.println("Suggested: " + key);
                                   return;
                               }
                           }
                       }
                   }else{
                       ArrayList<Vehicle> vehicles = parkingBuilding.get(entrance);
                           int suggestedTop = 0;
                           int suggestedBottom = 0;
                           for(int it = entrance; it < maxFloor-1; it++){
                               vehicles = parkingBuilding.get(it);
                                if(parkingFreePlaces - vehicles.size() > 0){
                                    suggestedTop = it;
                                    break;
                                }
                           }
                           for(int it=entrance; it > minFloor+1; it--){
                               vehicles = parkingBuilding.get(it);
                               if(parkingFreePlaces - vehicles.size() > 0){
                                   suggestedBottom = it;
                                   break;
                               }
                           }

                           if(Math.abs(entrance - suggestedBottom) <= Math.abs(entrance - suggestedTop) ){
                               System.out.println("Suggested: " + suggestedBottom);
                           }else{
                               System.out.println("Suggested: " + suggestedTop);
                           }
                   }
               }else{
                   Integer[] suggestions = { minFloor + 2, minFloor + 3, maxFloor + 1, maxFloor + 2};
                   Integer[] enableFloors = { minFloor, minFloor + 1, maxFloor -1, maxFloor};
                   int temp = entrance + 2;
                   for(int i=0; i<suggestions.length; i++){
                       suggestions[i] = Math.abs(suggestions[i] - temp);
                       //System.out.println(suggestions[i]);
                   }
                   for(int i=0; i<enableFloors.length; i++){
                       ArrayList<Vehicle> vehicles = parkingBuilding.get(enableFloors[i]);
                       if(parkingFreePlaces - vehicles.size() > 0){
                           enableFloors[i] = 1;
                       }
                       else{
                           enableFloors[i] = 0;
                       }
                       //System.out.println(enableFloors[i]);
                   }

                   int tmp = 10000;
                   for(int i=0; i<suggestions.length; i++){
                       if(enableFloors[i] == 1){
                           tmp = Math.min(tmp, suggestions[i]);
                       }
                   }
                   int ans= 0;
                   for(int i=0; i<suggestions.length; i++){
                       if(enableFloors[i] == 1 && suggestions[i] == tmp){
                           ans = i;
                       }
                   }
                   //System.out.println(ans);
                   if(ans == 0){
                       System.out.println("Suggested: " + (minFloor));
                   }else if(ans == 1){
                       System.out.println("Suggested: " + (minFloor+1));
                   }else if(ans == 2){
                       System.out.println("Suggested: " + (maxFloor - 1));
                   }else{
                       System.out.println("Suggested: " + maxFloor);
                   }


               }
            }
        }
    }

    private boolean ifExistsFreePlaces (){
        Integer freePlaces = 0;
        for (ArrayList value : parkingBuilding.values()) {
            freePlaces += parkingFreePlaces - value.size();
        }
       return  (freePlaces > 0) ? true : false;
    }

    private boolean ifExistsFreePlacesPetrolCars(){
        Integer freePlacesForPetrolCars = 0;

        for (Map.Entry<Integer, ArrayList<Vehicle>> entry : parkingBuilding.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue().size();
            if(key != minFloor && key != minFloor + 1 && key != maxFloor && key != maxFloor -1){
                freePlacesForPetrolCars += parkingFreePlaces - value;
            }
        }
        //System.out.println(freePlacesForPetrolCars);
        return (freePlacesForPetrolCars > 0) ? true : false;
    }
}
