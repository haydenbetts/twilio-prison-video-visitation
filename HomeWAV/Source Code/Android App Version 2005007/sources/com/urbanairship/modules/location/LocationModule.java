package com.urbanairship.modules.location;

import com.urbanairship.AirshipComponent;
import com.urbanairship.modules.Module;
import java.util.Collections;

public class LocationModule extends Module {
    private final AirshipLocationClient locationClient;

    public LocationModule(AirshipComponent airshipComponent, AirshipLocationClient airshipLocationClient) {
        super(Collections.singleton(airshipComponent));
        this.locationClient = airshipLocationClient;
    }

    public AirshipLocationClient getLocationClient() {
        return this.locationClient;
    }
}
