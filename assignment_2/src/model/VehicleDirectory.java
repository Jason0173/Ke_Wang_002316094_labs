/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jason
 */

import java.util.ArrayList;
import java.util.List;

public class VehicleDirectory {
    private List<Vehicle> vehicleList;

    public VehicleDirectory() {
        vehicleList = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicleList.remove(vehicle);
    }

    public Vehicle searchByVehicleId(int id) {
        for (Vehicle v : vehicleList) {
            if (v.getVehicleId() == id) {
                return v;
            }
        }
        return null;
    }

    public List<Vehicle> searchByVehicleName(String name) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getMake().equalsIgnoreCase(name) || v.getModel().equalsIgnoreCase(name)) {
                result.add(v);
            }
        }
        return result;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleList;
    }
}

