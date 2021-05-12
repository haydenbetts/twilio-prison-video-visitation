package com.urbanairship.analytics;

import com.urbanairship.analytics.location.RegionEvent;

public interface AnalyticsListener {
    void onCustomEventAdded(CustomEvent customEvent);

    void onRegionEventAdded(RegionEvent regionEvent);

    void onScreenTracked(String str);
}
