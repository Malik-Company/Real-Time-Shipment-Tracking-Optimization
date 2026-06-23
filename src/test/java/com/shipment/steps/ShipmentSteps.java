package com.shipment.steps;

import com.shipment.service.ShipmentService;
import com.shipment.service.TrackingService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentSteps {

    private ShipmentService shipmentService;
    private TrackingService trackingService;
    private String currentShipmentId;

    @Before
    public void setUp() {
        shipmentService = new ShipmentService();
        trackingService = new TrackingService(shipmentService);
    }

    @Given("a new shipment with id {string} from {string} to {string}")
    public void createShipment(String id, String origin, String destination) {
        shipmentService.create(id, origin, destination);
        currentShipmentId = id;
    }

    @When("the shipment is dispatched with carrier {string}")
    public void dispatchShipment(String carrier) {
        shipmentService.dispatch(currentShipmentId, carrier);
    }

    @And("the shipment is delivered")
    public void deliverShipment() {
        shipmentService.deliver(currentShipmentId);
    }

    @When("the shipment is cancelled")
    public void cancelShipment() {
        shipmentService.cancel(currentShipmentId);
    }

    @Then("the shipment status should be {string}")
    public void verifyStatus(String expectedStatus) {
        String actual = trackingService.getStatus(currentShipmentId);
        assertEquals(expectedStatus, actual);
    }

    @Then("the tracking history should contain {int} entries")
    public void verifyTrackingHistorySize(int expectedSize) {
        List<String> history = trackingService.getTrackingHistory(currentShipmentId);
        assertEquals(expectedSize, history.size());
    }
}
