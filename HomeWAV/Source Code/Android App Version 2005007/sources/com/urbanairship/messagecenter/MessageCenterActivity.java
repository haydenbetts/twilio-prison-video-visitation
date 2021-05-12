package com.urbanairship.messagecenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.urbanairship.Autopilot;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.activity.ThemedActivity;

public class MessageCenterActivity extends ThemedActivity {
    private MessageCenterFragment messageCenterFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Autopilot.automaticTakeOff(getApplication());
        if (UAirship.isTakingOff() || UAirship.isFlying()) {
            setDisplayHomeAsUpEnabled(true);
            if (bundle != null) {
                this.messageCenterFragment = (MessageCenterFragment) getSupportFragmentManager().findFragmentByTag("MESSAGE_CENTER_FRAGMENT");
            }
            if (this.messageCenterFragment == null) {
                this.messageCenterFragment = MessageCenterFragment.newInstance(MessageCenter.parseMessageId(getIntent()));
                getSupportFragmentManager().beginTransaction().add(16908290, (Fragment) this.messageCenterFragment, "MESSAGE_CENTER_FRAGMENT").commitNow();
            }
            this.messageCenterFragment.setPredicate(MessageCenter.shared().getPredicate());
            return;
        }
        Logger.error("MessageCenterActivity - unable to create activity, takeOff not called.", new Object[0]);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String parseMessageId = MessageCenter.parseMessageId(intent);
        if (parseMessageId != null) {
            this.messageCenterFragment.setMessageID(parseMessageId);
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
