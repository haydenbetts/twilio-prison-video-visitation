package com.urbanairship.iam.assets;

import com.urbanairship.iam.InAppMessage;

public interface CachePolicyDelegate {
    boolean shouldCacheOnSchedule(String str, InAppMessage inAppMessage);

    boolean shouldPersistCacheAfterDisplay(String str, InAppMessage inAppMessage);
}
