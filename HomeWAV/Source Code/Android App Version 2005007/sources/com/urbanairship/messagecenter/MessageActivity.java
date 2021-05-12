package com.urbanairship.messagecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.activity.ThemedActivity;

public class MessageActivity extends ThemedActivity {
    private static final String FRAGMENT_TAG = "MessageFragment";
    /* access modifiers changed from: private */
    public String messageId;
    private final InboxListener updateMessageListener = new InboxListener() {
        public void onInboxUpdated() {
            if (MessageActivity.this.messageId != null) {
                MessageActivity messageActivity = MessageActivity.this;
                messageActivity.updateTitle(messageActivity.messageId);
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Autopilot.automaticTakeOff(getApplication());
        if (UAirship.isTakingOff() || UAirship.isFlying()) {
            setDisplayHomeAsUpEnabled(true);
            if (bundle == null) {
                this.messageId = MessageCenter.parseMessageId(getIntent());
            } else {
                this.messageId = bundle.getString("messageId");
            }
            if (this.messageId == null) {
                finish();
            } else {
                loadMessage();
            }
        } else {
            Logger.error("MessageActivity - unable to create activity, takeOff not called.", new Object[0]);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("messageId", this.messageId);
    }

    private void loadMessage() {
        if (this.messageId != null) {
            MessageFragment messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            if (messageFragment == null || !this.messageId.equals(messageFragment.getMessageId())) {
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                if (messageFragment != null) {
                    beginTransaction.remove(messageFragment);
                }
                beginTransaction.add(16908290, (Fragment) MessageFragment.newInstance(this.messageId), FRAGMENT_TAG).commitNow();
            }
            updateTitle(this.messageId);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        MessageCenter.shared().getInbox().addListener(this.updateMessageListener);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        MessageCenter.shared().getInbox().removeListener(this.updateMessageListener);
    }

    /* access modifiers changed from: private */
    public void updateTitle(String str) {
        Message message = MessageCenter.shared().getInbox().getMessage(str);
        if (message == null) {
            setTitle((CharSequence) null);
        } else {
            setTitle(message.getTitle());
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String parseMessageId = MessageCenter.parseMessageId(intent);
        if (parseMessageId != null) {
            this.messageId = parseMessageId;
            loadMessage();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        finish();
        return true;
    }
}
