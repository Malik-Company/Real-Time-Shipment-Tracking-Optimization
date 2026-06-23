Feature: Shipment Tracking
  As a logistics operator
  I want to track shipments in real time
  So that I can keep customers informed

  # ── IMPLEMENTED scenarios (will PASS) ──────────────────────────────────────

  Scenario: Create a new shipment
    Given a new shipment with id "SHP001" from "New York" to "Los Angeles"
    Then the shipment status should be "CREATED"

  Scenario: Dispatch a shipment to carrier
    Given a new shipment with id "SHP002" from "Chicago" to "Houston"
    When the shipment is dispatched with carrier "EXPRESS"
    Then the shipment status should be "IN_TRANSIT"

  Scenario: Mark a shipment as delivered
    Given a new shipment with id "SHP003" from "Seattle" to "Miami"
    When the shipment is dispatched with carrier "STANDARD"
    And the shipment is delivered
    Then the shipment status should be "DELIVERED"

  Scenario: View tracking history for a delivered shipment
    Given a new shipment with id "SHP004" from "Boston" to "Denver"
    When the shipment is dispatched with carrier "EXPRESS"
    And the shipment is delivered
    Then the tracking history should contain 3 entries

  # ── PENDING scenarios (tagged @Pending → skipped = untested flows) ──────────

  @Pending
  Scenario: Calculate shipping cost for a local zone
    Given a shipment weighing 2.5 kg
    When the shipping zone is "LOCAL"
    Then the shipping cost should be 6.25

  @Pending
  Scenario: Calculate shipping cost for international delivery
    Given a shipment weighing 5.0 kg
    When the shipping zone is "INTERNATIONAL"
    Then the shipping cost should be 62.5

  @Pending
  Scenario: Get estimated delivery time for express carrier
    Given a shipment with carrier "EXPRESS" in zone "NATIONAL"
    Then the estimated delivery should be "1-2 days"

  @Pending
  Scenario: Cancel a shipment before dispatch
    Given a new shipment with id "SHP005" from "Austin" to "Portland"
    When the shipment is cancelled
    Then the shipment status should be "CANCELLED"
