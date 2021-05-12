package com.urbanairship.analytics.location;

import com.urbanairship.Logger;

public class CircularRegion {
    public static final int MAX_RADIUS = 100000;
    private final double latitude;
    private final double longitude;
    private final double radius;

    public CircularRegion(double d, double d2, double d3) {
        this.radius = d;
        this.latitude = d2;
        this.longitude = d3;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public boolean isValid() {
        double d = this.radius;
        if (d > 100000.0d || d <= 0.0d) {
            Logger.error("The radius must be greater than %s and less than or equal to %s meters.", 0, 100000);
            return false;
        } else if (!RegionEvent.regionEventLatitudeIsValid(Double.valueOf(this.latitude))) {
            Logger.error("The latitude must be greater than or equal to %s and less than or equal to %s degrees.", Double.valueOf(-90.0d), Double.valueOf(90.0d));
            return false;
        } else if (RegionEvent.regionEventLongitudeIsValid(Double.valueOf(this.longitude))) {
            return true;
        } else {
            Logger.error("The longitude must be greater than or equal to %s and less than or equal to %s degrees.", Double.valueOf(-180.0d), Double.valueOf(180.0d));
            return false;
        }
    }
}
