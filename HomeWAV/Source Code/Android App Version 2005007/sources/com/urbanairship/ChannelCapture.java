package com.urbanairship;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.SimpleApplicationListener;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.util.UAStringUtil;
import java.util.Calendar;

public class ChannelCapture extends AirshipComponent {
    private static final long KNOCKS_MAX_TIME_IN_MS = 30000;
    private static final int KNOCKS_TO_TRIGGER_CHANNEL_CAPTURE = 6;
    private final ActivityMonitor activityMonitor;
    private final AirshipChannel airshipChannel;
    private ClipboardManager clipboardManager;
    private final AirshipConfigOptions configOptions;
    private final Context context;
    private boolean enabled;
    private int indexOfKnocks;
    private long[] knockTimes = new long[6];
    private final ApplicationListener listener = new SimpleApplicationListener() {
        public void onForeground(long j) {
            ChannelCapture.this.countForeground(j);
        }
    };

    ChannelCapture(Context context2, AirshipConfigOptions airshipConfigOptions, AirshipChannel airshipChannel2, PreferenceDataStore preferenceDataStore, ActivityMonitor activityMonitor2) {
        super(context2, preferenceDataStore);
        this.context = context2.getApplicationContext();
        this.configOptions = airshipConfigOptions;
        this.airshipChannel = airshipChannel2;
        this.activityMonitor = activityMonitor2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.enabled = this.configOptions.channelCaptureEnabled;
        this.activityMonitor.addApplicationListener(this.listener);
    }

    /* access modifiers changed from: private */
    public void countForeground(long j) {
        if (isEnabled()) {
            if (this.indexOfKnocks >= 6) {
                this.indexOfKnocks = 0;
            }
            long[] jArr = this.knockTimes;
            int i = this.indexOfKnocks;
            jArr[i] = j;
            this.indexOfKnocks = i + 1;
            if (checkKnock()) {
                writeClipboard();
            }
        }
    }

    private boolean checkKnock() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        for (long j : this.knockTimes) {
            if (j + 30000 < timeInMillis) {
                return false;
            }
        }
        return true;
    }

    private void writeClipboard() {
        if (this.clipboardManager == null) {
            try {
                this.clipboardManager = (ClipboardManager) this.context.getSystemService("clipboard");
            } catch (Exception e) {
                Logger.error(e, "Unable to initialize clipboard manager: ", new Object[0]);
            }
        }
        if (this.clipboardManager == null) {
            Logger.debug("Unable to attempt channel capture, clipboard manager uninitialized", new Object[0]);
            return;
        }
        this.knockTimes = new long[6];
        this.indexOfKnocks = 0;
        String id = this.airshipChannel.getId();
        String str = "ua:";
        if (!UAStringUtil.isEmpty(id)) {
            str = str + id;
        }
        this.clipboardManager.setPrimaryClip(ClipData.newPlainText("UA Channel ID", str));
        Logger.debug("ChannelCapture - Channel ID copied to clipboard", new Object[0]);
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        this.activityMonitor.removeApplicationListener(this.listener);
    }
}
