package com.urbanairship.iam.modal;

import android.content.Context;
import android.content.Intent;
import com.urbanairship.iam.DisplayHandler;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.iam.MediaDisplayAdapter;

public class ModalAdapter extends MediaDisplayAdapter {
    private ModalAdapter(InAppMessage inAppMessage, ModalDisplayContent modalDisplayContent) {
        super(inAppMessage, modalDisplayContent.getMedia());
    }

    public static ModalAdapter newAdapter(InAppMessage inAppMessage) {
        ModalDisplayContent modalDisplayContent = (ModalDisplayContent) inAppMessage.getDisplayContent();
        if (modalDisplayContent != null) {
            return new ModalAdapter(inAppMessage, modalDisplayContent);
        }
        throw new IllegalArgumentException("Invalid message for adapter: " + inAppMessage);
    }

    public void onDisplay(Context context, DisplayHandler displayHandler) {
        context.startActivity(new Intent(context, ModalActivity.class).setFlags(268435456).putExtra(InAppMessageActivity.DISPLAY_HANDLER_EXTRA_KEY, displayHandler).putExtra("in_app_message", getMessage()).putExtra(InAppMessageActivity.IN_APP_ASSETS, getAssets()));
    }
}
