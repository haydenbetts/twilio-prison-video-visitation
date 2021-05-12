package com.urbanairship.iam.html;

import android.net.Uri;
import android.webkit.WebView;
import com.urbanairship.Logger;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonValue;
import com.urbanairship.webkit.AirshipWebViewClient;

public abstract class HtmlWebViewClient extends AirshipWebViewClient {
    private static final String DISMISS_COMMAND = "dismiss";

    public abstract void onMessageDismissed(JsonValue jsonValue);

    public HtmlWebViewClient() {
    }

    protected HtmlWebViewClient(ActionRunRequestFactory actionRunRequestFactory) {
        super(actionRunRequestFactory);
    }

    /* access modifiers changed from: protected */
    public void onAirshipCommand(WebView webView, String str, Uri uri) {
        if (str.equals("dismiss")) {
            String encodedPath = uri.getEncodedPath();
            if (encodedPath != null) {
                String[] split = encodedPath.split("/");
                if (split.length > 1) {
                    try {
                        onMessageDismissed(JsonValue.parseString(Uri.decode(split[1])));
                    } catch (JsonException e) {
                        Logger.error("Unable to decode message resolution from JSON.", e);
                    }
                } else {
                    Logger.error("Unable to decode message resolution, invalid path", new Object[0]);
                }
            } else {
                Logger.error("Unable to decode message resolution, missing path", new Object[0]);
            }
        }
    }
}
