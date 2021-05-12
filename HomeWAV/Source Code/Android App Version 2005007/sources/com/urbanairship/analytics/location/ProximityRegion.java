package com.urbanairship.analytics.location;

import com.urbanairship.Logger;

public class ProximityRegion {
    private static final int MAX_MAJOR_MINOR_VALUE = 65535;
    private static final int MAX_RSSI = 100;
    private static final int MIN_RSSI = -100;
    private Double latitude;
    private Double longitude;
    private final int major;
    private final int minor;
    private final String proximityId;
    private Integer rssi;

    public ProximityRegion(String str, int i, int i2) {
        this.proximityId = str;
        this.major = i;
        this.minor = i2;
    }

    public void setCoordinates(Double d, Double d2) {
        if (d == null || d2 == null) {
            this.latitude = null;
            this.longitude = null;
        } else if (!RegionEvent.regionEventLatitudeIsValid(d)) {
            Logger.error("The latitude must be greater than or equal to %s and less than or equal to %s degrees.", Double.valueOf(-90.0d), Double.valueOf(90.0d));
            this.latitude = null;
        } else if (!RegionEvent.regionEventLongitudeIsValid(d2)) {
            Logger.error("The longitude must be greater than or equal to %s and less than or equal to %s degrees.", Double.valueOf(-180.0d), Double.valueOf(180.0d));
            this.longitude = null;
        } else {
            this.latitude = d;
            this.longitude = d2;
        }
    }

    public void setRssi(Integer num) {
        if (num == null) {
            this.rssi = null;
        } else if (num.intValue() > 100 || num.intValue() < -100) {
            Logger.error("The rssi must be greater than or equal to %s and less than or equal to %s dBm.", -100, 100);
            this.rssi = null;
        } else {
            this.rssi = num;
        }
    }

    public String getProximityId() {
        return this.proximityId;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Integer getRssi() {
        return this.rssi;
    }

    public boolean isValid() {
        String str = this.proximityId;
        if (str == null) {
            Logger.error("The proximity ID must not be null.", new Object[0]);
            return false;
        } else if (!RegionEvent.regionEventCharacterCountIsValid(str)) {
            Logger.error("The proximity ID must not be greater than %s or less than %s characters in length.", 255, 1);
            return false;
        } else {
            int i = this.major;
            if (i > 65535 || i < 0) {
                Logger.error("The major must not be greater than 65535 or less than 0.", new Object[0]);
                return false;
            }
            int i2 = this.minor;
            if (i2 <= 65535 && i2 >= 0) {
                return true;
            }
            Logger.error("The minor must not be greater than %s or less than %s.", 65535, 0);
            return false;
        }
    }
}
