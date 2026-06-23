package com.shipment.model;

public class Shipment {

    private String id;
    private String origin;
    private String destination;
    private String status;
    private String carrier;
    private double weight;

    public Shipment(String id, String origin, String destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.status = "CREATED";
    }

    public String getId()          { return id; }
    public String getOrigin()      { return origin; }
    public String getDestination() { return destination; }
    public String getStatus()      { return status; }
    public String getCarrier()     { return carrier; }
    public double getWeight()      { return weight; }

    public void setStatus(String status)   { this.status = status; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
    public void setWeight(double weight)   { this.weight = weight; }
}
