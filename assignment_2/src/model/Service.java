/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jason
 */
public class Service {
    private int serviceId;
    private String serviceType;
    private double cost;
    private String mechanicName;
    private int serviceDuration;

    public Service(int serviceId, String serviceType, double cost, String mechanicName, int serviceDuration) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.cost = cost;
        this.mechanicName = mechanicName;
        this.serviceDuration = serviceDuration;
    }


    public int getServiceId() {
        return serviceId;
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getMechanicName() {
        return mechanicName;
    }
    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
    public int getServiceDuration() {
        return serviceDuration;
    }
    public void setServiceDuration(int serviceDuration) {
        this.serviceDuration = serviceDuration;
    }
}