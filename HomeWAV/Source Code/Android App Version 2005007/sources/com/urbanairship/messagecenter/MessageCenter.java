package com.urbanairship.messagecenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.Predicate;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.job.JobInfo;
import com.urbanairship.push.PushListener;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.UAStringUtil;

public class MessageCenter extends AirshipComponent {
    public static final String MESSAGE_DATA_SCHEME = "message";
    public static final String VIEW_MESSAGE_CENTER_INTENT_ACTION = "com.urbanairship.VIEW_RICH_PUSH_INBOX";
    public static final String VIEW_MESSAGE_INTENT_ACTION = "com.urbanairship.VIEW_RICH_PUSH_MESSAGE";
    private final Inbox inbox;
    private OnShowMessageCenterListener onShowMessageCenterListener;
    private Predicate<Message> predicate;
    private final PushListener pushListener;
    private final PushManager pushManager;

    public interface OnShowMessageCenterListener {
        boolean onShowMessageCenter(String str);
    }

    public int getComponentGroup() {
        return 2;
    }

    public static MessageCenter shared() {
        return (MessageCenter) UAirship.shared().requireComponent(MessageCenter.class);
    }

    public MessageCenter(Context context, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel, PushManager pushManager2) {
        this(context, preferenceDataStore, new Inbox(context, preferenceDataStore, airshipChannel), pushManager2);
    }

    MessageCenter(Context context, PreferenceDataStore preferenceDataStore, Inbox inbox2, PushManager pushManager2) {
        super(context, preferenceDataStore);
        this.pushManager = pushManager2;
        this.inbox = inbox2;
        this.pushListener = new PushListener() {
            public void onPushReceived(PushMessage pushMessage, boolean z) {
                if (!UAStringUtil.isEmpty(pushMessage.getRichPushMessageId()) && MessageCenter.this.getInbox().getMessage(pushMessage.getRichPushMessageId()) == null) {
                    Logger.debug("MessageCenter - Received a Rich Push.", new Object[0]);
                    MessageCenter.this.getInbox().fetchMessages();
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.inbox.init();
        this.pushManager.addPushListener(this.pushListener);
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        return this.inbox.onPerformJob(uAirship, jobInfo);
    }

    public void tearDown() {
        this.inbox.tearDown();
        this.pushManager.removePushListener(this.pushListener);
    }

    public Inbox getInbox() {
        return this.inbox;
    }

    public User getUser() {
        return this.inbox.getUser();
    }

    public Predicate<Message> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(Predicate<Message> predicate2) {
        this.predicate = predicate2;
    }

    public void setOnShowMessageCenterListener(OnShowMessageCenterListener onShowMessageCenterListener2) {
        this.onShowMessageCenterListener = onShowMessageCenterListener2;
    }

    public void showMessageCenter() {
        showMessageCenter((String) null);
    }

    public void showMessageCenter(String str) {
        OnShowMessageCenterListener onShowMessageCenterListener2 = this.onShowMessageCenterListener;
        if (onShowMessageCenterListener2 == null || !onShowMessageCenterListener2.onShowMessageCenter(str)) {
            Intent addFlags = new Intent(VIEW_MESSAGE_CENTER_INTENT_ACTION).setPackage(getContext().getPackageName()).addFlags(805306368);
            if (str != null) {
                addFlags.setData(Uri.fromParts("message", str, (String) null));
            }
            if (addFlags.resolveActivity(getContext().getPackageManager()) != null) {
                getContext().startActivity(addFlags);
                return;
            }
            if (str != null) {
                addFlags.setAction(VIEW_MESSAGE_INTENT_ACTION);
                if (addFlags.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(addFlags);
                    return;
                }
            }
            addFlags.setClass(getContext(), MessageCenterActivity.class);
            getContext().startActivity(addFlags);
        }
    }

    public static String parseMessageId(Intent intent) {
        if (intent == null || intent.getData() == null || intent.getAction() == null || !"message".equalsIgnoreCase(intent.getData().getScheme())) {
            return null;
        }
        String action = intent.getAction();
        action.hashCode();
        if (action.equals(VIEW_MESSAGE_CENTER_INTENT_ACTION) || action.equals(VIEW_MESSAGE_INTENT_ACTION)) {
            return intent.getData().getSchemeSpecificPart();
        }
        return null;
    }
}
