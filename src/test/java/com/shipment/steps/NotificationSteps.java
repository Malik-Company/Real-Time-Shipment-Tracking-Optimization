package com.shipment.steps;

import com.shipment.service.NotificationService;
import com.shipment.service.ShipmentService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationSteps {

    private ShipmentService shipmentService;
    private NotificationService notificationService;
    private String currentShipmentId;

    @Before
    public void setUp() {
        shipmentService = new ShipmentService();
        notificationService = new NotificationService();
    }

    @Given("a notification context for shipment {string}")
    public void notificationContext(String id) {
        shipmentService.create(id, "Origin City", "Destination City");
        currentShipmentId = id;
    }

    @When("a dispatch notification is sent to {string}")
    public void sendDispatchNotification(String email) {
        notificationService.notifyDispatched(currentShipmentId, email);
    }

    @When("a delivery notification is sent to {string}")
    public void sendDeliveryNotification(String email) {
        notificationService.notifyDelivered(currentShipmentId, email);
    }

    @Then("the notification log should contain a dispatch notification for {string}")
    public void verifyDispatchNotification(String shipmentId) {
        assertTrue(notificationService.getSentNotifications().stream()
                .anyMatch(n -> n.startsWith("DISPATCHED:" + shipmentId)));
    }

    @Then("the notification log should contain a delivery notification for {string}")
    public void verifyDeliveryNotification(String shipmentId) {
        assertTrue(notificationService.getSentNotifications().stream()
                .anyMatch(n -> n.startsWith("DELIVERED:" + shipmentId)));
    }
}
