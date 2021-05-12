package com.urbanairship.iam.html;

import android.content.Context;
import android.content.Intent;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.iam.DisplayHandler;
import com.urbanairship.iam.ForegroundDisplayAdapter;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.iam.assets.Assets;
import com.urbanairship.util.Network;

public class HtmlDisplayAdapter extends ForegroundDisplayAdapter {
    private final HtmlDisplayContent displayContent;
    private final InAppMessage message;

    public void onFinish(Context context) {
    }

    protected HtmlDisplayAdapter(InAppMessage inAppMessage, HtmlDisplayContent htmlDisplayContent) {
        this.message = inAppMessage;
        this.displayContent = htmlDisplayContent;
    }

    public static HtmlDisplayAdapter newAdapter(InAppMessage inAppMessage) {
        HtmlDisplayContent htmlDisplayContent = (HtmlDisplayContent) inAppMessage.getDisplayContent();
        if (htmlDisplayContent != null) {
            return new HtmlDisplayAdapter(inAppMessage, htmlDisplayContent);
        }
        throw new IllegalArgumentException("Invalid message for adapter: " + inAppMessage);
    }

    public int onPrepare(Context context, Assets assets) {
        if (UAirship.shared().getUrlAllowList().isAllowed(this.displayContent.getUrl(), 2)) {
            return 0;
        }
        Logger.error("HTML in-app message URL is not allowed. Unable to display message.", new Object[0]);
        return 2;
    }

    public boolean isReady(Context context) {
        if (!super.isReady(context)) {
            return false;
        }
        if (!this.displayContent.getRequireConnectivity() || Network.isConnected()) {
            return true;
        }
        return false;
    }

    public void onDisplay(Context context, DisplayHandler displayHandler) {
        context.startActivity(new Intent(context, HtmlActivity.class).setFlags(268435456).putExtra(InAppMessageActivity.DISPLAY_HANDLER_EXTRA_KEY, displayHandler).putExtra("in_app_message", this.message));
    }
}
