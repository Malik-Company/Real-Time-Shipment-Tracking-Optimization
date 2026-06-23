package com.shipment.steps;

import io.cucumber.java.Before;
import org.junit.jupiter.api.Assumptions;

/**
 * Scenarios tagged @Pending have no step implementations yet.
 * Aborting via Assumptions causes JUnit Platform to record them as
 * "skipped" in the Surefire XML — which qbric counts as untested flows.
 */
public class Hooks {

    @Before("@Pending")
    public void skipPendingScenario() {
        Assumptions.assumeTrue(false, "Scenario is pending — not yet implemented");
    }
}
