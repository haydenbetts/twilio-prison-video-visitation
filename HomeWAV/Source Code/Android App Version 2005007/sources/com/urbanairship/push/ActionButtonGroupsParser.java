package com.urbanairship.push;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import com.urbanairship.Logger;
import com.urbanairship.R;
import com.urbanairship.push.notifications.NotificationActionButton;
import com.urbanairship.push.notifications.NotificationActionButtonGroup;
import com.urbanairship.util.UAStringUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

class ActionButtonGroupsParser {
    private static final String BUTTON_GROUP_TAG = "UrbanAirshipActionButtonGroup";
    private static final String BUTTON_TAG = "UrbanAirshipActionButton";
    private static final String DESCRIPTION_ATTRIBUTE = "description";
    private static final String FOREGROUND_ATTRIBUTE = "foreground";
    private static final String ID_ATTRIBUTE = "id";

    ActionButtonGroupsParser() {
    }

    public static Map<String, NotificationActionButtonGroup> fromXml(Context context, int i) {
        try {
            return parseGroups(context, context.getResources().getXml(i));
        } catch (Resources.NotFoundException | IOException | NullPointerException | XmlPullParserException e) {
            Logger.error(e, "Failed to parse NotificationActionButtonGroups.", new Object[0]);
            return new HashMap();
        }
    }

    private static Map<String, NotificationActionButtonGroup> parseGroups(Context context, XmlResourceParser xmlResourceParser) throws IOException, XmlPullParserException {
        HashMap hashMap = new HashMap();
        String str = null;
        NotificationActionButtonGroup.Builder builder = null;
        while (xmlResourceParser.next() != 1) {
            int eventType = xmlResourceParser.getEventType();
            String name = xmlResourceParser.getName();
            if (eventType == 2 && BUTTON_GROUP_TAG.equals(name)) {
                String attributeValue = xmlResourceParser.getAttributeValue((String) null, "id");
                if (UAStringUtil.isEmpty(attributeValue)) {
                    Logger.error("%s missing id.", BUTTON_GROUP_TAG);
                } else {
                    builder = NotificationActionButtonGroup.newBuilder();
                    str = attributeValue;
                }
            } else if (!UAStringUtil.isEmpty(str)) {
                if (eventType == 2 && BUTTON_TAG.equals(name)) {
                    String attributeValue2 = xmlResourceParser.getAttributeValue((String) null, "id");
                    if (UAStringUtil.isEmpty(attributeValue2)) {
                        Logger.error("%s missing id.", BUTTON_TAG);
                    } else {
                        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.UrbanAirshipActionButton);
                        NotificationActionButton.Builder description = NotificationActionButton.newBuilder(attributeValue2).setPerformsInForeground(xmlResourceParser.getAttributeBooleanValue((String) null, FOREGROUND_ATTRIBUTE, true)).setIcon(obtainStyledAttributes.getResourceId(R.styleable.UrbanAirshipActionButton_android_icon, 0)).setDescription(xmlResourceParser.getAttributeValue((String) null, DESCRIPTION_ATTRIBUTE));
                        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.UrbanAirshipActionButton_android_label, 0);
                        if (resourceId != 0) {
                            description.setLabel(resourceId);
                        } else {
                            description.setLabel(obtainStyledAttributes.getString(R.styleable.UrbanAirshipActionButton_android_label));
                        }
                        builder.addNotificationActionButton(description.build());
                        obtainStyledAttributes.recycle();
                    }
                } else if (eventType == 3 && BUTTON_GROUP_TAG.equals(name)) {
                    NotificationActionButtonGroup build = builder.build();
                    if (build.getNotificationActionButtons().isEmpty()) {
                        Logger.error("%s %s missing action buttons.", BUTTON_GROUP_TAG, str);
                    } else {
                        hashMap.put(str, build);
                    }
                }
            }
        }
        return hashMap;
    }
}
