package com.urbanairship.iam.assets;

import com.urbanairship.iam.InAppMessage;

public interface PrepareAssetsDelegate {
    int onPrepare(String str, InAppMessage inAppMessage, Assets assets);

    void onSchedule(String str, InAppMessage inAppMessage, Assets assets);
}
