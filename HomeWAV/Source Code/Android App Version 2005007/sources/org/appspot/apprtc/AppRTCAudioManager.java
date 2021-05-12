package org.appspot.apprtc;

import air.HomeWAV.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.appspot.apprtc.AppRTCBluetoothManager;
import org.appspot.apprtc.util.AppRTCUtils;
import tvi.webrtc.ThreadUtils;

public class AppRTCAudioManager {
    private static final String SPEAKERPHONE_AUTO = "auto";
    private static final String SPEAKERPHONE_FALSE = "false";
    private static final String SPEAKERPHONE_TRUE = "true";
    private static final String TAG = "AppRTCAudioManager";
    private AudioManagerState amState;
    private final Context apprtcContext;
    private Set<AudioDevice> audioDevices = new HashSet();
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private AudioManager audioManager;
    private AudioManagerEvents audioManagerEvents;
    private final AppRTCBluetoothManager bluetoothManager;
    private AudioDevice defaultAudioDevice;
    /* access modifiers changed from: private */
    public boolean hasWiredHeadset;
    private AppRTCProximitySensor proximitySensor;
    private int savedAudioMode = -2;
    private boolean savedIsMicrophoneMute;
    private boolean savedIsSpeakerPhoneOn;
    private AudioDevice selectedAudioDevice;
    private final String useSpeakerphone;
    private AudioDevice userSelectedAudioDevice;
    private BroadcastReceiver wiredHeadsetReceiver;

    public enum AudioDevice {
        SPEAKER_PHONE,
        WIRED_HEADSET,
        EARPIECE,
        BLUETOOTH,
        NONE
    }

    public interface AudioManagerEvents {
        void onAudioDeviceChanged(AudioDevice audioDevice, Set<AudioDevice> set);
    }

    public enum AudioManagerState {
        UNINITIALIZED,
        PREINITIALIZED,
        RUNNING
    }

    /* access modifiers changed from: private */
    public void onProximitySensorChangedState() {
        if (!this.useSpeakerphone.equals("auto") || this.audioDevices.size() != 2 || !this.audioDevices.contains(AudioDevice.EARPIECE) || !this.audioDevices.contains(AudioDevice.SPEAKER_PHONE)) {
            return;
        }
        if (this.proximitySensor.sensorReportsNearState()) {
            setAudioDeviceInternal(AudioDevice.EARPIECE);
        } else {
            setAudioDeviceInternal(AudioDevice.SPEAKER_PHONE);
        }
    }

    private class WiredHeadsetReceiver extends BroadcastReceiver {
        private static final int HAS_MIC = 1;
        private static final int HAS_NO_MIC = 0;
        private static final int STATE_PLUGGED = 1;
        private static final int STATE_UNPLUGGED = 0;

        private WiredHeadsetReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            int intExtra = intent.getIntExtra("state", 0);
            int intExtra2 = intent.getIntExtra("microphone", 0);
            String stringExtra = intent.getStringExtra("name");
            StringBuilder sb = new StringBuilder();
            sb.append("WiredHeadsetReceiver.onReceive");
            sb.append(AppRTCUtils.getThreadInfo());
            sb.append(": a=");
            sb.append(intent.getAction());
            sb.append(", s=");
            sb.append(intExtra == 0 ? "unplugged" : "plugged");
            sb.append(", m=");
            sb.append(intExtra2 == 1 ? "mic" : "no mic");
            sb.append(", n=");
            sb.append(stringExtra);
            sb.append(", sb=");
            sb.append(isInitialStickyBroadcast());
            Log.d(AppRTCAudioManager.TAG, sb.toString());
            AppRTCAudioManager appRTCAudioManager = AppRTCAudioManager.this;
            if (intExtra == 1) {
                z = true;
            }
            boolean unused = appRTCAudioManager.hasWiredHeadset = z;
            AppRTCAudioManager.this.updateAudioDeviceState();
        }
    }

    public static AppRTCAudioManager create(Context context) {
        return new AppRTCAudioManager(context);
    }

    private AppRTCAudioManager(Context context) {
        Log.d(TAG, "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.apprtcContext = context;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.bluetoothManager = AppRTCBluetoothManager.create(context, this);
        this.wiredHeadsetReceiver = new WiredHeadsetReceiver();
        this.amState = AudioManagerState.UNINITIALIZED;
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_speakerphone_key), context.getString(R.string.pref_speakerphone_default));
        this.useSpeakerphone = string;
        Log.d(TAG, "useSpeakerphone: " + string);
        if (string.equals(SPEAKERPHONE_FALSE)) {
            this.defaultAudioDevice = AudioDevice.EARPIECE;
        } else {
            this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
        }
        this.proximitySensor = AppRTCProximitySensor.create(context, new Runnable() {
            public final void run() {
                AppRTCAudioManager.this.onProximitySensorChangedState();
            }
        });
        Log.d(TAG, "defaultAudioDevice: " + this.defaultAudioDevice);
        AppRTCUtils.logDeviceInfo(TAG);
    }

    public void start(AudioManagerEvents audioManagerEvents2) {
        Log.d(TAG, TtmlNode.START);
        ThreadUtils.checkIsOnMainThread();
        if (this.amState == AudioManagerState.RUNNING) {
            Log.e(TAG, "AudioManager is already active");
            return;
        }
        Log.d(TAG, "AudioManager starts...");
        this.audioManagerEvents = audioManagerEvents2;
        this.amState = AudioManagerState.RUNNING;
        this.savedAudioMode = this.audioManager.getMode();
        this.savedIsSpeakerPhoneOn = this.audioManager.isSpeakerphoneOn();
        this.savedIsMicrophoneMute = this.audioManager.isMicrophoneMute();
        this.hasWiredHeadset = hasWiredHeadset();
        AnonymousClass1 r5 = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int i) {
                String str = i != -3 ? i != -2 ? i != -1 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "AUDIOFOCUS_INVALID" : "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE" : "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK" : "AUDIOFOCUS_GAIN_TRANSIENT" : "AUDIOFOCUS_GAIN" : "AUDIOFOCUS_LOSS" : "AUDIOFOCUS_LOSS_TRANSIENT" : "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                Log.d(AppRTCAudioManager.TAG, "onAudioFocusChange: " + str);
            }
        };
        this.audioFocusChangeListener = r5;
        if (this.audioManager.requestAudioFocus(r5, 0, 2) == 1) {
            Log.d(TAG, "Audio focus request granted for VOICE_CALL streams");
        } else {
            Log.e(TAG, "Audio focus request failed");
        }
        this.audioManager.setMode(3);
        setMicrophoneMute(false);
        this.userSelectedAudioDevice = AudioDevice.NONE;
        this.selectedAudioDevice = AudioDevice.NONE;
        this.audioDevices.clear();
        this.bluetoothManager.start();
        updateAudioDeviceState();
        registerReceiver(this.wiredHeadsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        Log.d(TAG, "AudioManager started");
    }

    public void stop() {
        Log.d(TAG, "stop");
        ThreadUtils.checkIsOnMainThread();
        if (this.amState != AudioManagerState.RUNNING) {
            Log.e(TAG, "Trying to stop AudioManager in incorrect state: " + this.amState);
            return;
        }
        this.amState = AudioManagerState.UNINITIALIZED;
        unregisterReceiver(this.wiredHeadsetReceiver);
        this.bluetoothManager.stop();
        setSpeakerphoneOn(this.savedIsSpeakerPhoneOn);
        setMicrophoneMute(this.savedIsMicrophoneMute);
        this.audioManager.setMode(this.savedAudioMode);
        this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
        this.audioFocusChangeListener = null;
        Log.d(TAG, "Abandoned audio focus for VOICE_CALL streams");
        AppRTCProximitySensor appRTCProximitySensor = this.proximitySensor;
        if (appRTCProximitySensor != null) {
            appRTCProximitySensor.stop();
            this.proximitySensor = null;
        }
        this.audioManagerEvents = null;
        Log.d(TAG, "AudioManager stopped");
    }

    private void setAudioDeviceInternal(AudioDevice audioDevice) {
        Log.d(TAG, "setAudioDeviceInternal(device=" + audioDevice + ")");
        AppRTCUtils.assertIsTrue(this.audioDevices.contains(audioDevice));
        int i = AnonymousClass2.$SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice[audioDevice.ordinal()];
        if (i == 1) {
            setSpeakerphoneOn(true);
        } else if (i == 2) {
            setSpeakerphoneOn(false);
        } else if (i == 3) {
            setSpeakerphoneOn(false);
        } else if (i != 4) {
            Log.e(TAG, "Invalid audio device selection");
        } else {
            setSpeakerphoneOn(false);
        }
        this.selectedAudioDevice = audioDevice;
    }

    /* renamed from: org.appspot.apprtc.AppRTCAudioManager$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.appspot.apprtc.AppRTCAudioManager$AudioDevice[] r0 = org.appspot.apprtc.AppRTCAudioManager.AudioDevice.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice = r0
                org.appspot.apprtc.AppRTCAudioManager$AudioDevice r1 = org.appspot.apprtc.AppRTCAudioManager.AudioDevice.SPEAKER_PHONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice     // Catch:{ NoSuchFieldError -> 0x001d }
                org.appspot.apprtc.AppRTCAudioManager$AudioDevice r1 = org.appspot.apprtc.AppRTCAudioManager.AudioDevice.EARPIECE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.appspot.apprtc.AppRTCAudioManager$AudioDevice r1 = org.appspot.apprtc.AppRTCAudioManager.AudioDevice.WIRED_HEADSET     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.appspot.apprtc.AppRTCAudioManager$AudioDevice r1 = org.appspot.apprtc.AppRTCAudioManager.AudioDevice.BLUETOOTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.appspot.apprtc.AppRTCAudioManager.AnonymousClass2.<clinit>():void");
        }
    }

    public void setDefaultAudioDevice(AudioDevice audioDevice) {
        ThreadUtils.checkIsOnMainThread();
        int i = AnonymousClass2.$SwitchMap$org$appspot$apprtc$AppRTCAudioManager$AudioDevice[audioDevice.ordinal()];
        if (i == 1) {
            this.defaultAudioDevice = audioDevice;
        } else if (i != 2) {
            Log.e(TAG, "Invalid default audio device selection");
        } else if (hasEarpiece()) {
            this.defaultAudioDevice = audioDevice;
        } else {
            this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
        }
        Log.d(TAG, "setDefaultAudioDevice(device=" + this.defaultAudioDevice + ")");
        updateAudioDeviceState();
    }

    public void selectAudioDevice(AudioDevice audioDevice) {
        ThreadUtils.checkIsOnMainThread();
        if (!this.audioDevices.contains(audioDevice)) {
            Log.e(TAG, "Can not select " + audioDevice + " from available " + this.audioDevices);
        }
        this.userSelectedAudioDevice = audioDevice;
        updateAudioDeviceState();
    }

    public Set<AudioDevice> getAudioDevices() {
        ThreadUtils.checkIsOnMainThread();
        return Collections.unmodifiableSet(new HashSet(this.audioDevices));
    }

    public AudioDevice getSelectedAudioDevice() {
        ThreadUtils.checkIsOnMainThread();
        return this.selectedAudioDevice;
    }

    private void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        this.apprtcContext.unregisterReceiver(broadcastReceiver);
    }

    private void setSpeakerphoneOn(boolean z) {
        if (this.audioManager.isSpeakerphoneOn() != z) {
            this.audioManager.setSpeakerphoneOn(z);
        }
    }

    private void setMicrophoneMute(boolean z) {
        if (this.audioManager.isMicrophoneMute() != z) {
            this.audioManager.setMicrophoneMute(z);
        }
    }

    private boolean hasEarpiece() {
        return this.apprtcContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    private boolean hasWiredHeadset() {
        if (Build.VERSION.SDK_INT < 23) {
            return this.audioManager.isWiredHeadsetOn();
        }
        AudioDeviceInfo[] devices = this.audioManager.getDevices(3);
        int length = devices.length;
        int i = 0;
        while (i < length) {
            int type = devices[i].getType();
            if (type == 3) {
                Log.d(TAG, "hasWiredHeadset: found wired headset");
                return true;
            } else if (type == 11) {
                Log.d(TAG, "hasWiredHeadset: found USB audio device");
                return true;
            } else {
                i++;
            }
        }
        return false;
    }

    public void updateAudioDeviceState() {
        AudioDevice audioDevice;
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "--- updateAudioDeviceState: wired headset=" + this.hasWiredHeadset + ", BT state=" + this.bluetoothManager.getState());
        Log.d(TAG, "Device status: available=" + this.audioDevices + ", selected=" + this.selectedAudioDevice + ", user selected=" + this.userSelectedAudioDevice);
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_UNAVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_DISCONNECTING) {
            this.bluetoothManager.updateDevice();
        }
        HashSet hashSet = new HashSet();
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTED || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE) {
            hashSet.add(AudioDevice.BLUETOOTH);
        }
        if (this.hasWiredHeadset) {
            hashSet.add(AudioDevice.WIRED_HEADSET);
        } else {
            hashSet.add(AudioDevice.SPEAKER_PHONE);
        }
        boolean z = true;
        boolean z2 = !this.audioDevices.equals(hashSet);
        this.audioDevices = hashSet;
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_UNAVAILABLE && this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
            this.userSelectedAudioDevice = AudioDevice.NONE;
        }
        if (this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.SPEAKER_PHONE) {
            this.userSelectedAudioDevice = AudioDevice.WIRED_HEADSET;
        }
        if (!this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) {
            this.userSelectedAudioDevice = AudioDevice.SPEAKER_PHONE;
        }
        boolean z3 = false;
        boolean z4 = this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE && (this.userSelectedAudioDevice == AudioDevice.NONE || this.userSelectedAudioDevice == AudioDevice.BLUETOOTH);
        if (!((this.bluetoothManager.getState() != AppRTCBluetoothManager.State.SCO_CONNECTED && this.bluetoothManager.getState() != AppRTCBluetoothManager.State.SCO_CONNECTING) || this.userSelectedAudioDevice == AudioDevice.NONE || this.userSelectedAudioDevice == AudioDevice.BLUETOOTH)) {
            z3 = true;
        }
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTED) {
            Log.d(TAG, "Need BT audio: start=" + z4 + ", stop=" + z3 + ", BT state=" + this.bluetoothManager.getState());
        }
        if (z3) {
            this.bluetoothManager.stopScoAudio();
            this.bluetoothManager.updateDevice();
        }
        if (!z4 || z3 || this.bluetoothManager.startScoAudio()) {
            z = z2;
        } else {
            this.audioDevices.remove(AudioDevice.BLUETOOTH);
        }
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTED) {
            audioDevice = AudioDevice.BLUETOOTH;
        } else if (this.hasWiredHeadset) {
            audioDevice = AudioDevice.WIRED_HEADSET;
        } else {
            audioDevice = this.defaultAudioDevice;
        }
        if (audioDevice != this.selectedAudioDevice || z) {
            setAudioDeviceInternal(audioDevice);
            Log.d(TAG, "New device status: available=" + this.audioDevices + ", selected=" + audioDevice);
            AudioManagerEvents audioManagerEvents2 = this.audioManagerEvents;
            if (audioManagerEvents2 != null) {
                audioManagerEvents2.onAudioDeviceChanged(this.selectedAudioDevice, this.audioDevices);
            }
        }
        Log.d(TAG, "--- updateAudioDeviceState done");
    }
}
