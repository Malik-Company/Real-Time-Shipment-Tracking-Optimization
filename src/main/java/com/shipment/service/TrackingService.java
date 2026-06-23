package com.shipment.service;

import com.shipment.model.Shipment;
import java.util.ArrayList;
import java.util.List;

public class TrackingService {

    private final ShipmentService shipmentService;

    public TrackingService(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getStatus(String shipmentId) {
        Shipment s = shipmentService.getById(shipmentId);
        if (s == null) return "NOT_FOUND";
        return s.getStatus();
    }

    public List<String> getTrackingHistory(String shipmentId) {
        Shipment s = shipmentService.getById(shipmentId);
        List<String> history = new ArrayList<>();
        if (s == null) return history;
        history.add("CREATED at origin: " + s.getOrigin());
        if (!"CREATED".equals(s.getStatus())) {
            history.add("IN_TRANSIT via: " + s.getCarrier());
        }
        if ("DELIVERED".equals(s.getStatus())) {
            history.add("DELIVERED to: " + s.getDestination());
        }
        return history;
    }

    // Not yet tested — untested flow
    public boolean isDelayed(String shipmentId, int expectedDays) {
        return expectedDays > 7;
    }

    // Not yet tested — untested flow
    public String getCarrierDetails(String shipmentId) {
        Shipment s = shipmentService.getById(shipmentId);
        if (s == null || s.getCarrier() == null) return "No carrier assigned";
        return "Carrier: " + s.getCarrier();
    }
}
