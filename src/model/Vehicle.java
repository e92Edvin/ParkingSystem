package model;

import service.VehicleTypes;

import java.util.Scanner;

public class Vehicle {
    private String type;
    private Integer entranceFloor;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(){
        String type;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Switch vechile type: ");
        System.out.println("Press 1 : Petrol/Diesel ");
        System.out.println("Press 2 : Van ");
        System.out.println("Press 3 : Electric car");
        Integer vehicleType = scanner.nextInt();
        switch (vehicleType){
            case 1 : type = VehicleTypes.PetrolDiesel.toString();
                break;
            case 2 : type = VehicleTypes.Van.toString();
                break;
            case 3 : type = VehicleTypes.ElectricCar.toString();
                break;
            default:
                type = VehicleTypes.PetrolDiesel.toString();
        }
        this.type = type;
    }

    public Integer getEntranceFloor() {
        return entranceFloor;
    }

    public void setEntranceFloor(Integer entranceFloor) {
        this.entranceFloor = entranceFloor;
    }

    public void setEntranceFloor(){
        Integer entranceFloor;
        String entry;

        System.out.println("Enter vehicle entrance floor: ");
        Scanner scanner = new Scanner(System.in);

        entry = scanner.next();
        if ((entry.matches("[0-8]+") && entry.length() < 2 ) || entry.equals("-1") || entry.equals("-2")) {
            entranceFloor = Integer.parseInt(entry);
        }else{
            entranceFloor = 2;
        }
        this.entranceFloor = entranceFloor;
    }

}
