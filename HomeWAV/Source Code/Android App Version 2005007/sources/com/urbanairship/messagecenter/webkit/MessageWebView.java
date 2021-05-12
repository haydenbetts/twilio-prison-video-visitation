package com.urbanairship.messagecenter.webkit;

import android.content.Context;
import android.util.AttributeSet;
import com.microsoft.appcenter.Constants;
import com.urbanairship.messagecenter.Message;
import com.urbanairship.messagecenter.MessageCenter;
import com.urbanairship.messagecenter.User;
import com.urbanairship.webkit.AirshipWebView;
import java.util.HashMap;

public class MessageWebView extends AirshipWebView {
    public MessageWebView(Context context) {
        super(context);
    }

    public MessageWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MessageWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MessageWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void loadMessage(Message message) {
        User user = MessageCenter.shared().getUser();
        HashMap hashMap = new HashMap();
        if (!(user.getId() == null || user.getPassword() == null)) {
            setClientAuthRequest(message.getMessageBodyUrl(), user.getId(), user.getPassword());
            hashMap.put(Constants.AUTHORIZATION_HEADER, createBasicAuth(user.getId(), user.getPassword()));
        }
        loadUrl(message.getMessageBodyUrl(), hashMap);
    }
}
