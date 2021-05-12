package com.urbanairship.push.notifications;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.app.NotificationCompat;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.UAStringUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class StyleNotificationExtender implements NotificationCompat.Extender {
    static final String BIG_PICTURE_KEY = "big_picture";
    static final String BIG_TEXT_KEY = "big_text";
    static final String INBOX_KEY = "inbox";
    static final String LINES_KEY = "lines";
    static final String SUMMARY_KEY = "summary";
    static final String TITLE_KEY = "title";
    static final String TYPE_KEY = "type";
    private final Context context;
    private NotificationCompat.Style defaultStyle;
    private final PushMessage message;

    public StyleNotificationExtender(Context context2, PushMessage pushMessage) {
        this.context = context2.getApplicationContext();
        this.message = pushMessage;
    }

    public StyleNotificationExtender setDefaultStyle(NotificationCompat.Style style) {
        this.defaultStyle = style;
        return this;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
        NotificationCompat.Style style;
        if (!applyStyle(builder) && (style = this.defaultStyle) != null) {
            builder.setStyle(style);
        }
        return builder;
    }

    private boolean applyStyle(NotificationCompat.Builder builder) {
        String stylePayload = this.message.getStylePayload();
        if (stylePayload == null) {
            return false;
        }
        try {
            JsonMap optMap = JsonValue.parseString(stylePayload).optMap();
            String optString = optMap.opt("type").optString();
            optString.hashCode();
            char c = 65535;
            switch (optString.hashCode()) {
                case 100344454:
                    if (optString.equals(INBOX_KEY)) {
                        c = 0;
                        break;
                    }
                    break;
                case 735420684:
                    if (optString.equals(BIG_TEXT_KEY)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1129611455:
                    if (optString.equals(BIG_PICTURE_KEY)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    applyInboxStyle(builder, optMap);
                    return true;
                case 1:
                    applyBigTextStyle(builder, optMap);
                    return true;
                case 2:
                    return applyBigPictureStyle(builder, optMap);
                default:
                    Logger.error("Unrecognized notification style type: %s", optString);
                    return false;
            }
        } catch (JsonException e) {
            Logger.error(e, "Failed to parse notification style payload.", new Object[0]);
            return false;
        }
    }

    private boolean applyBigTextStyle(NotificationCompat.Builder builder, JsonMap jsonMap) {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        String string = jsonMap.opt("title").getString();
        String string2 = jsonMap.opt("summary").getString();
        String string3 = jsonMap.opt(BIG_TEXT_KEY).getString();
        if (!UAStringUtil.isEmpty(string3)) {
            bigTextStyle.bigText(string3);
        }
        if (!UAStringUtil.isEmpty(string)) {
            bigTextStyle.setBigContentTitle(string);
        }
        if (!UAStringUtil.isEmpty(string2)) {
            bigTextStyle.setSummaryText(string2);
        }
        builder.setStyle(bigTextStyle);
        return true;
    }

    private boolean applyBigPictureStyle(NotificationCompat.Builder builder, JsonMap jsonMap) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        String string = jsonMap.opt("title").getString();
        String string2 = jsonMap.opt("summary").getString();
        try {
            Bitmap fetchBigImage = NotificationUtils.fetchBigImage(this.context, new URL(jsonMap.opt(BIG_PICTURE_KEY).optString()));
            if (fetchBigImage == null) {
                return false;
            }
            bigPictureStyle.bigPicture(fetchBigImage);
            bigPictureStyle.bigLargeIcon((Bitmap) null);
            builder.setLargeIcon(fetchBigImage);
            if (!UAStringUtil.isEmpty(string)) {
                bigPictureStyle.setBigContentTitle(string);
            }
            if (!UAStringUtil.isEmpty(string2)) {
                bigPictureStyle.setSummaryText(string2);
            }
            builder.setStyle(bigPictureStyle);
            return true;
        } catch (MalformedURLException e) {
            Logger.error(e, "Malformed big picture URL.", new Object[0]);
            return false;
        }
    }

    private void applyInboxStyle(NotificationCompat.Builder builder, JsonMap jsonMap) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String string = jsonMap.opt("title").getString();
        String string2 = jsonMap.opt("summary").getString();
        Iterator<JsonValue> it = jsonMap.opt(LINES_KEY).optList().iterator();
        while (it.hasNext()) {
            String string3 = it.next().getString();
            if (!UAStringUtil.isEmpty(string3)) {
                inboxStyle.addLine(string3);
            }
        }
        if (!UAStringUtil.isEmpty(string)) {
            inboxStyle.setBigContentTitle(string);
        }
        if (!UAStringUtil.isEmpty(string2)) {
            inboxStyle.setSummaryText(string2);
        }
        builder.setStyle(inboxStyle);
    }
}
