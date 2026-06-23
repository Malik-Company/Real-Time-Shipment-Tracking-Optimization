Feature: Shipment Notifications
  As a customer
  I want to receive notifications about my shipment
  So that I am always aware of its status

  # ── IMPLEMENTED scenarios (will PASS) ──────────────────────────────────────

  Scenario: Notify customer when shipment is dispatched
    Given a notification context for shipment "SHP010"
    When a dispatch notification is sent to "customer@example.com"
    Then the notification log should contain a dispatch notification for "SHP010"

  Scenario: Notify customer when shipment is delivered
    Given a notification context for shipment "SHP011"
    When a delivery notification is sent to "customer@example.com"
    Then the notification log should contain a delivery notification for "SHP011"

  # ── PENDING scenarios (untested flows) ──────────────────────────────────────

  @Pending
  Scenario: Notify customer when shipment is delayed
    Given a shipment "SHP012" is in transit
    When the shipment is delayed by 3 days
    Then a delay notification should be sent to "customer@example.com"

  @Pending
  Scenario: Notify customer about shipment exception
    Given a shipment "SHP013" is in transit
    When an exception occurs with reason "Customs hold"
    Then an exception notification should be sent

  @Pending
  Scenario: Send bulk status update to multiple customers
    Given 3 shipments are in transit
    When a bulk update is triggered with message "Carrier delay expected"
    Then all 3 customers should receive the update
