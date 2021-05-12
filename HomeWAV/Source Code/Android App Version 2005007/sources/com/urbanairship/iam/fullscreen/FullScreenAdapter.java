package com.urbanairship.iam.fullscreen;

import android.content.Context;
import android.content.Intent;
import com.urbanairship.iam.DisplayHandler;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.iam.MediaDisplayAdapter;

public class FullScreenAdapter extends MediaDisplayAdapter {
    protected FullScreenAdapter(InAppMessage inAppMessage, FullScreenDisplayContent fullScreenDisplayContent) {
        super(inAppMessage, fullScreenDisplayContent.getMedia());
    }

    public static FullScreenAdapter newAdapter(InAppMessage inAppMessage) {
        FullScreenDisplayContent fullScreenDisplayContent = (FullScreenDisplayContent) inAppMessage.getDisplayContent();
        if (fullScreenDisplayContent != null) {
            return new FullScreenAdapter(inAppMessage, fullScreenDisplayContent);
        }
        throw new IllegalArgumentException("Invalid message for adapter: " + inAppMessage);
    }

    public void onDisplay(Context context, DisplayHandler displayHandler) {
        context.startActivity(new Intent(context, FullScreenActivity.class).setFlags(268435456).putExtra(InAppMessageActivity.DISPLAY_HANDLER_EXTRA_KEY, displayHandler).putExtra("in_app_message", getMessage()).putExtra(InAppMessageActivity.IN_APP_ASSETS, getAssets()));
    }
}
