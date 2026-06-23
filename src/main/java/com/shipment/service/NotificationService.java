package com.shipment.service;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private final List<String> sentNotifications = new ArrayList<>();

    public void notifyDispatched(String shipmentId, String email) {
        sentNotifications.add("DISPATCHED:" + shipmentId + ":" + email);
    }

    public void notifyDelivered(String shipmentId, String email) {
        sentNotifications.add("DELIVERED:" + shipmentId + ":" + email);
    }

    public List<String> getSentNotifications() {
        return sentNotifications;
    }

    // Not yet tested — untested flow
    public void notifyDelay(String shipmentId, String email, int delayDays) {
        sentNotifications.add("DELAYED:" + shipmentId + ":" + email + ":" + delayDays + "d");
    }

    // Not yet tested — untested flow
    public void notifyException(String shipmentId, String reason) {
        sentNotifications.add("EXCEPTION:" + shipmentId + ":" + reason);
    }

    // Not yet tested — untested flow
    public void sendBulkUpdate(List<String> shipmentIds, String message) {
        shipmentIds.forEach(id -> sentNotifications.add("BULK:" + id + ":" + message));
    }
}
