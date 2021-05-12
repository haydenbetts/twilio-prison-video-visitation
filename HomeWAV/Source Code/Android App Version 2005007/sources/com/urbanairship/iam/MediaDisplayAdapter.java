package com.urbanairship.iam;

import android.content.Context;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.iam.assets.Assets;
import com.urbanairship.util.Network;

public abstract class MediaDisplayAdapter extends ForegroundDisplayAdapter {
    private Assets assets;
    private final MediaInfo mediaInfo;
    private final InAppMessage message;

    public void onFinish(Context context) {
    }

    protected MediaDisplayAdapter(InAppMessage inAppMessage, MediaInfo mediaInfo2) {
        this.message = inAppMessage;
        this.mediaInfo = mediaInfo2;
    }

    public int onPrepare(Context context, Assets assets2) {
        this.assets = assets2;
        MediaInfo mediaInfo2 = this.mediaInfo;
        if (mediaInfo2 == null || isAllowed(mediaInfo2.getUrl()) || "image".equals(this.mediaInfo.getType())) {
            return 0;
        }
        Logger.error("URL not allowed. Unable to load: %s", this.mediaInfo.getUrl());
        return 2;
    }

    /* access modifiers changed from: protected */
    public InAppMessage getMessage() {
        return this.message;
    }

    public boolean isReady(Context context) {
        if (!super.isReady(context)) {
            return false;
        }
        MediaInfo mediaInfo2 = this.mediaInfo;
        if (mediaInfo2 == null) {
            return true;
        }
        Assets assets2 = this.assets;
        if (assets2 == null || !assets2.file(mediaInfo2.getUrl()).exists()) {
            return Network.isConnected();
        }
        return true;
    }

    public Assets getAssets() {
        return this.assets;
    }

    private static boolean isAllowed(String str) {
        return UAirship.shared().getUrlAllowList().isAllowed(str, 2);
    }
}
