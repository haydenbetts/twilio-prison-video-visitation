package com.urbanairship.messagecenter.webkit;

import android.os.Bundle;
import android.webkit.WebView;
import com.urbanairship.actions.ActionArguments;
import com.urbanairship.actions.ActionRunRequest;
import com.urbanairship.javascript.JavaScriptEnvironment;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.messagecenter.Message;
import com.urbanairship.messagecenter.MessageCenter;
import com.urbanairship.webkit.AirshipWebViewClient;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class MessageWebViewClient extends AirshipWebViewClient {
    private static SimpleDateFormat DATE_FORMATTER;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ", Locale.US);
        DATE_FORMATTER = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /* access modifiers changed from: protected */
    public ActionRunRequest extendActionRequest(ActionRunRequest actionRunRequest, WebView webView) {
        Bundle bundle = new Bundle();
        Message message = getMessage(webView);
        if (message != null) {
            bundle.putString(ActionArguments.RICH_PUSH_ID_METADATA, message.getMessageId());
        }
        actionRunRequest.setMetadata(bundle);
        return actionRunRequest;
    }

    /* access modifiers changed from: protected */
    public JavaScriptEnvironment.Builder extendJavascriptEnvironment(JavaScriptEnvironment.Builder builder, WebView webView) {
        Message message = getMessage(webView);
        JsonMap jsonMap = JsonMap.EMPTY_MAP;
        if (message != null) {
            jsonMap = JsonValue.wrapOpt(message.getExtrasMap()).optMap();
        }
        JavaScriptEnvironment.Builder addGetter = super.extendJavascriptEnvironment(builder, webView).addGetter("getMessageSentDateMS", message != null ? message.getSentDateMS() : -1);
        String str = null;
        JavaScriptEnvironment.Builder addGetter2 = addGetter.addGetter("getMessageId", message != null ? message.getMessageId() : null).addGetter("getMessageTitle", message != null ? message.getTitle() : null);
        if (message != null) {
            str = DATE_FORMATTER.format(message.getSentDate());
        }
        return addGetter2.addGetter("getMessageSentDate", str).addGetter("getUserId", MessageCenter.shared().getUser().getId()).addGetter("getMessageExtras", (JsonSerializable) jsonMap);
    }

    private Message getMessage(WebView webView) {
        return MessageCenter.shared().getInbox().getMessageByUrl(webView.getUrl());
    }
}
