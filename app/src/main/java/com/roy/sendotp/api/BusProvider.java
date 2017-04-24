package com.roy.sendotp.api;

import com.squareup.otto.Bus;

/**
 * Basic Bus Provider for Otto event bus. Use main thread bus so we
 * always return our results to the main thread even if event was posted from different thread
 *
 * @author prabhat.roy
 */
public class BusProvider {

    private static MainThreadBus BUS;

    private BusProvider() {
    }

    public static Bus getInstance() {

        if (BUS == null) {
            BUS = new MainThreadBus(new Bus());
        }
        return BUS;
    }
}
