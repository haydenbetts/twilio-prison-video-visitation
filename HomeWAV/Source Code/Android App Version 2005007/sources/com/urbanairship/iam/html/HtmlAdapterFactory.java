package com.urbanairship.iam.html;

import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.InAppMessageAdapter;

public class HtmlAdapterFactory implements InAppMessageAdapter.Factory {
    public InAppMessageAdapter createAdapter(InAppMessage inAppMessage) {
        return HtmlDisplayAdapter.newAdapter(inAppMessage);
    }
}
