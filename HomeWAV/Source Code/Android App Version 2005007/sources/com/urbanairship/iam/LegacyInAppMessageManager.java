package com.urbanairship.iam;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.ResultCallback;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.automation.InAppAutomation;
import com.urbanairship.automation.Schedule;
import com.urbanairship.automation.ScheduleData;
import com.urbanairship.automation.Trigger;
import com.urbanairship.automation.Triggers;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.banner.BannerDisplayContent;
import com.urbanairship.json.JsonException;
import com.urbanairship.push.InternalNotificationListener;
import com.urbanairship.push.NotificationActionButtonInfo;
import com.urbanairship.push.NotificationInfo;
import com.urbanairship.push.PushListener;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushMessage;
import com.urbanairship.push.notifications.NotificationActionButton;
import com.urbanairship.push.notifications.NotificationActionButtonGroup;
import java.util.concurrent.TimeUnit;

public class LegacyInAppMessageManager extends AirshipComponent {
    private static final String AUTO_DISPLAY_ENABLED_KEY = "com.urbanairship.push.iam.AUTO_DISPLAY_ENABLED";
    public static final float DEFAULT_BORDER_RADIUS_DP = 2.0f;
    public static final int DEFAULT_PRIMARY_COLOR = -1;
    public static final int DEFAULT_SECONDARY_COLOR = -16777216;
    private static final String KEY_PREFIX = "com.urbanairship.push.iam.";
    private static final String LAST_DISPLAYED_ID_KEY = "com.urbanairship.push.iam.LAST_DISPLAYED_ID";
    private static final String PENDING_IN_APP_MESSAGE_KEY = "com.urbanairship.push.iam.PENDING_IN_APP_MESSAGE";
    private static final String PENDING_MESSAGE_ID = "com.urbanairship.push.iam.PENDING_MESSAGE_ID";
    /* access modifiers changed from: private */
    public final Analytics analytics;
    private boolean displayAsapEnabled = true;
    /* access modifiers changed from: private */
    public final InAppAutomation inAppAutomation;
    private MessageBuilderExtender messageBuilderExtender;
    /* access modifiers changed from: private */
    public final PreferenceDataStore preferenceDataStore;
    private final PushManager pushManager;
    private ScheduleBuilderExtender scheduleBuilderExtender;

    public interface MessageBuilderExtender {
        InAppMessage.Builder extend(Context context, InAppMessage.Builder builder, LegacyInAppMessage legacyInAppMessage);
    }

    public interface ScheduleBuilderExtender {
        Schedule.Builder<InAppMessage> extend(Context context, Schedule.Builder<InAppMessage> builder, LegacyInAppMessage legacyInAppMessage);
    }

    public int getComponentGroup() {
        return 3;
    }

    public static LegacyInAppMessageManager shared() {
        return (LegacyInAppMessageManager) UAirship.shared().requireComponent(LegacyInAppMessageManager.class);
    }

    public LegacyInAppMessageManager(Context context, PreferenceDataStore preferenceDataStore2, InAppAutomation inAppAutomation2, Analytics analytics2, PushManager pushManager2) {
        super(context, preferenceDataStore2);
        this.preferenceDataStore = preferenceDataStore2;
        this.inAppAutomation = inAppAutomation2;
        this.analytics = analytics2;
        this.pushManager = pushManager2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.preferenceDataStore.remove(PENDING_IN_APP_MESSAGE_KEY);
        this.preferenceDataStore.remove(AUTO_DISPLAY_ENABLED_KEY);
        this.preferenceDataStore.remove(LAST_DISPLAYED_ID_KEY);
        this.pushManager.addPushListener(new PushListener() {
            public void onPushReceived(PushMessage pushMessage, boolean z) {
                LegacyInAppMessage legacyInAppMessage;
                Schedule access$000;
                try {
                    legacyInAppMessage = LegacyInAppMessage.fromPush(pushMessage);
                } catch (JsonException | IllegalArgumentException e) {
                    Logger.error(e, "LegacyInAppMessageManager - Unable to create in-app message from push payload", new Object[0]);
                    legacyInAppMessage = null;
                }
                if (legacyInAppMessage != null && (access$000 = LegacyInAppMessageManager.this.createSchedule(UAirship.getApplicationContext(), legacyInAppMessage)) != null) {
                    final String id = access$000.getId();
                    Logger.debug("LegacyInAppMessageManager - Received a Push with an in-app message.", new Object[0]);
                    final String string = LegacyInAppMessageManager.this.preferenceDataStore.getString(LegacyInAppMessageManager.PENDING_MESSAGE_ID, (String) null);
                    if (string != null) {
                        LegacyInAppMessageManager.this.inAppAutomation.cancelSchedule(string).addResultCallback(new ResultCallback<Boolean>() {
                            public void onResult(Boolean bool) {
                                if (bool != null && bool.booleanValue()) {
                                    Logger.debug("LegacyInAppMessageManager - Pending in-app message replaced.", new Object[0]);
                                    LegacyInAppMessageManager.this.analytics.addEvent(ResolutionEvent.legacyMessageReplaced(string, id));
                                }
                            }
                        });
                    }
                    LegacyInAppMessageManager.this.inAppAutomation.schedule((Schedule<? extends ScheduleData>) access$000);
                    LegacyInAppMessageManager.this.preferenceDataStore.put(LegacyInAppMessageManager.PENDING_MESSAGE_ID, id);
                }
            }
        });
        this.pushManager.addInternalNotificationListener(new InternalNotificationListener() {
            public void onNotificationResponse(NotificationInfo notificationInfo, NotificationActionButtonInfo notificationActionButtonInfo) {
                final PushMessage message = notificationInfo.getMessage();
                if (message.getSendId() != null && message.containsKey(PushMessage.EXTRA_IN_APP_MESSAGE)) {
                    LegacyInAppMessageManager.this.inAppAutomation.cancelSchedule(message.getSendId()).addResultCallback(new ResultCallback<Boolean>() {
                        public void onResult(Boolean bool) {
                            if (bool != null && bool.booleanValue()) {
                                Logger.debug("Clearing pending in-app message due to directly interacting with the message's push notification.", new Object[0]);
                                LegacyInAppMessageManager.this.analytics.addEvent(ResolutionEvent.legacyMessagePushOpened(message.getSendId()));
                            }
                        }
                    });
                }
            }
        });
    }

    public void setMessageBuilderExtender(MessageBuilderExtender messageBuilderExtender2) {
        this.messageBuilderExtender = messageBuilderExtender2;
    }

    public void setScheduleBuilderExtender(ScheduleBuilderExtender scheduleBuilderExtender2) {
        this.scheduleBuilderExtender = scheduleBuilderExtender2;
    }

    public void setDisplayAsapEnabled(boolean z) {
        this.displayAsapEnabled = z;
    }

    public boolean getDisplayAsapEnabled() {
        return this.displayAsapEnabled;
    }

    /* access modifiers changed from: private */
    public Schedule<InAppMessage> createSchedule(Context context, LegacyInAppMessage legacyInAppMessage) {
        Trigger trigger;
        try {
            if (this.displayAsapEnabled) {
                trigger = Triggers.newActiveSessionTriggerBuilder().build();
            } else {
                trigger = Triggers.newForegroundTriggerBuilder().build();
            }
            Schedule.Builder<InAppMessage> id = Schedule.newBuilder(createMessage(context, legacyInAppMessage)).addTrigger(trigger).setEnd(legacyInAppMessage.getExpiry()).setId(legacyInAppMessage.getId());
            ScheduleBuilderExtender scheduleBuilderExtender2 = this.scheduleBuilderExtender;
            if (scheduleBuilderExtender2 != null) {
                scheduleBuilderExtender2.extend(context, id, legacyInAppMessage);
            }
            return id.build();
        } catch (Exception e) {
            Logger.error(e, "Error during factory method to convert legacy in-app message.", new Object[0]);
            return null;
        }
    }

    private InAppMessage createMessage(Context context, LegacyInAppMessage legacyInAppMessage) {
        NotificationActionButtonGroup notificationActionGroup;
        int intValue = legacyInAppMessage.getPrimaryColor() == null ? -1 : legacyInAppMessage.getPrimaryColor().intValue();
        int intValue2 = legacyInAppMessage.getSecondaryColor() == null ? -16777216 : legacyInAppMessage.getSecondaryColor().intValue();
        BannerDisplayContent.Builder body = BannerDisplayContent.newBuilder().setBackgroundColor(intValue).setDismissButtonColor(intValue2).setBorderRadius(2.0f).setButtonLayout(DisplayContent.BUTTON_LAYOUT_SEPARATE).setPlacement(legacyInAppMessage.getPlacement()).setActions(legacyInAppMessage.getClickActionValues()).setBody(TextInfo.newBuilder().setText(legacyInAppMessage.getAlert()).setColor(intValue2).build());
        if (legacyInAppMessage.getDuration() != null) {
            body.setDuration(legacyInAppMessage.getDuration().longValue(), TimeUnit.MILLISECONDS);
        }
        if (!(legacyInAppMessage.getButtonGroupId() == null || (notificationActionGroup = this.pushManager.getNotificationActionGroup(legacyInAppMessage.getButtonGroupId())) == null)) {
            int i = 0;
            while (i < notificationActionGroup.getNotificationActionButtons().size() && i < 2) {
                NotificationActionButton notificationActionButton = notificationActionGroup.getNotificationActionButtons().get(i);
                body.addButton(ButtonInfo.newBuilder().setActions(legacyInAppMessage.getButtonActionValues(notificationActionButton.getId())).setId(notificationActionButton.getId()).setBackgroundColor(intValue2).setBorderRadius(2.0f).setLabel(TextInfo.newBuilder().setDrawable(context, notificationActionButton.getIcon()).setColor(intValue).setAlignment("center").setText(notificationActionButton.getLabel(context)).build()).build());
                i++;
            }
        }
        InAppMessage.Builder source = InAppMessage.newBuilder().setDisplayContent(body.build()).setExtras(legacyInAppMessage.getExtras()).setSource(InAppMessage.SOURCE_LEGACY_PUSH);
        MessageBuilderExtender messageBuilderExtender2 = this.messageBuilderExtender;
        if (messageBuilderExtender2 != null) {
            messageBuilderExtender2.extend(context, source, legacyInAppMessage);
        }
        return source.build();
    }
}
