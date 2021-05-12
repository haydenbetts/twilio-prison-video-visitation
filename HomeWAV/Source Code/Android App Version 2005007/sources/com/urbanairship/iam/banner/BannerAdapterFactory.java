package com.urbanairship.iam.banner;

import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageAdapter;

public class BannerAdapterFactory implements InAppMessageAdapter.Factory {
    public InAppMessageAdapter createAdapter(InAppMessage inAppMessage) {
        return BannerAdapter.newAdapter(inAppMessage);
    }
}
