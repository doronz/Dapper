package com.doronzehavi.dapper.common.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Grants us easy access to the Otto event bus.
 */
public class BusProvider {
    private static final Bus DATA_BUS = new Bus(ThreadEnforcer.ANY); // used for data events
    private static final Bus UI_BUS = new Bus(); // used for UI events

    private BusProvider() {}

    public static Bus getDataBusInstance() {

        return DATA_BUS;
    }

    public static Bus getUIBusInstance () {

        return UI_BUS;
    }
}
