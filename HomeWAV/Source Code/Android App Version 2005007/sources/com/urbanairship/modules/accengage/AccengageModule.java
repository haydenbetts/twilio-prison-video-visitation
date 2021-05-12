package com.urbanairship.modules.accengage;

import com.urbanairship.AirshipComponent;
import com.urbanairship.modules.Module;
import java.util.Collections;

public class AccengageModule extends Module {
    private final AccengageNotificationHandler notificationHandler;

    public AccengageModule(AirshipComponent airshipComponent, AccengageNotificationHandler accengageNotificationHandler) {
        super(Collections.singleton(airshipComponent));
        this.notificationHandler = accengageNotificationHandler;
    }

    public AccengageNotificationHandler getAccengageNotificationHandler() {
        return this.notificationHandler;
    }
}
