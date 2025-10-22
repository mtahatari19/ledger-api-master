package com.ledgerx;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Main Quarkus application class for LedgerX.
 * <p>
 * This application follows ECB (Entity-Control-Boundary) architecture: - Entity: Domain objects
 * with no dependencies - Control: Business logic that depends on Entity - Boundary: REST
 * controllers that depend on Control
 */
@QuarkusMain
public class LedgerXApplication implements QuarkusApplication {

  public static void main(String... args) {
    Quarkus.run(LedgerXApplication.class, args);
  }

  @Override
  public int run(String... args) throws Exception {
    Quarkus.waitForExit();
    return 0;
  }
}
