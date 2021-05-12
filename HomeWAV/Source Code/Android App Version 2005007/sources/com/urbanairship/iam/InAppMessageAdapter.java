package com.urbanairship.iam;

import android.content.Context;
import com.urbanairship.iam.assets.Assets;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface InAppMessageAdapter {
    public static final int CANCEL = 2;
    public static final int OK = 0;
    public static final int RETRY = 1;

    public interface Factory {
        InAppMessageAdapter createAdapter(InAppMessage inAppMessage);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrepareResult {
    }

    boolean isReady(Context context);

    void onDisplay(Context context, DisplayHandler displayHandler);

    void onFinish(Context context);

    int onPrepare(Context context, Assets assets);
}
