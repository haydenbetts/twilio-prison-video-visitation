package com.urbanairship.iam.fullscreen;

import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageAdapter;

public class FullScreenAdapterFactory implements InAppMessageAdapter.Factory {
    public InAppMessageAdapter createAdapter(InAppMessage inAppMessage) {
        return FullScreenAdapter.newAdapter(inAppMessage);
    }
}
