package com.urbanairship.modules.location;

public interface AirshipLocationClient {
    boolean isBackgroundLocationAllowed();

    boolean isLocationUpdatesEnabled();

    boolean isOptIn();

    void setBackgroundLocationAllowed(boolean z);

    void setLocationUpdatesEnabled(boolean z);
}
