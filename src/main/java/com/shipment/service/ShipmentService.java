package com.shipment.service;

import com.shipment.model.Shipment;
import java.util.HashMap;
import java.util.Map;

public class ShipmentService {

    private final Map<String, Shipment> shipments = new HashMap<>();

    public Shipment create(String id, String origin, String destination) {
        Shipment s = new Shipment(id, origin, destination);
        shipments.put(id, s);
        return s;
    }

    public Shipment getById(String id) {
        return shipments.get(id);
    }

    public void dispatch(String id, String carrier) {
        Shipment s = getById(id);
        if (s == null) throw new IllegalArgumentException("Shipment not found: " + id);
        s.setCarrier(carrier);
        s.setStatus("IN_TRANSIT");
    }

    public void deliver(String id) {
        Shipment s = getById(id);
        if (s == null) throw new IllegalArgumentException("Shipment not found: " + id);
        s.setStatus("DELIVERED");
    }

    public void cancel(String id) {
        Shipment s = getById(id);
        if (s == null) throw new IllegalArgumentException("Shipment not found: " + id);
        s.setStatus("CANCELLED");
    }

    // Not yet tested — will appear as untested flow
    public double calculateShippingCost(double weight, String zone) {
        double base = switch (zone) {
            case "LOCAL"        -> 5.0;
            case "REGIONAL"     -> 12.0;
            case "NATIONAL"     -> 25.0;
            case "INTERNATIONAL"-> 60.0;
            default             -> 30.0;
        };
        return base + (weight * 0.5);
    }

    // Not yet tested — will appear as untested flow
    public String estimateDelivery(String carrier, String zone) {
        return switch (carrier) {
            case "EXPRESS" -> "1-2 days";
            case "STANDARD"-> "3-5 days";
            default        -> "5-7 days";
        };
    }
}
