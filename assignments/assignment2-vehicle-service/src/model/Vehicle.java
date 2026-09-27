/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jason
 */
public class Vehicle {
    private int vehicleId;
    private String make;
    private String model;
    private int year;
    private String registrationNumber;
    private String serviceDate;
    private Service serviceOpted;
    private Owner owner;

    public Vehicle(int vehicleId, String make, String model, int year, String registrationNumber,
                   String serviceDate, Service serviceOpted, Owner owner) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.serviceDate = serviceDate;
        this.serviceOpted = serviceOpted;
        this.owner = owner;
    }


    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public String getServiceDate() {
        return serviceDate;
    }
    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }
    public Service getServiceOpted() {
        return serviceOpted;
    }
    public void setServiceOpted(Service serviceOpted) {
        this.serviceOpted = serviceOpted;
    }
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}