package org.appspot.apprtc;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.twilio.video.TestUtils;
import java.util.List;
import java.util.Set;
import org.appspot.apprtc.util.AppRTCUtils;
import tvi.webrtc.ThreadUtils;

public class AppRTCBluetoothManager {
    private static final int BLUETOOTH_SCO_TIMEOUT_MS = 4000;
    private static final int MAX_SCO_CONNECTION_ATTEMPTS = 2;
    private static final String TAG = "AppRTCBluetoothManager";
    private final AppRTCAudioManager apprtcAudioManager;
    private final Context apprtcContext;
    private final AudioManager audioManager;
    private BluetoothAdapter bluetoothAdapter;
    /* access modifiers changed from: private */
    public BluetoothDevice bluetoothDevice;
    /* access modifiers changed from: private */
    public BluetoothHeadset bluetoothHeadset;
    private final BroadcastReceiver bluetoothHeadsetReceiver;
    private final BluetoothProfile.ServiceListener bluetoothServiceListener;
    /* access modifiers changed from: private */
    public State bluetoothState;
    private final Runnable bluetoothTimeoutRunnable = new Runnable() {
        public void run() {
            AppRTCBluetoothManager.this.bluetoothTimeout();
        }
    };
    private final Handler handler;
    int scoConnectionAttempts;

    public enum State {
        UNINITIALIZED,
        ERROR,
        HEADSET_UNAVAILABLE,
        HEADSET_AVAILABLE,
        SCO_DISCONNECTING,
        SCO_CONNECTING,
        SCO_CONNECTED
    }

    /* access modifiers changed from: private */
    public String stateToString(int i) {
        if (i == 0) {
            return "DISCONNECTED";
        }
        if (i == 1) {
            return "CONNECTING";
        }
        if (i == 2) {
            return "CONNECTED";
        }
        if (i == 3) {
            return "DISCONNECTING";
        }
        switch (i) {
            case 10:
                return "OFF";
            case 11:
                return "TURNING_ON";
            case 12:
                return "ON";
            case 13:
                return "TURNING_OFF";
            default:
                return "INVALID";
        }
    }

    private class BluetoothServiceListener implements BluetoothProfile.ServiceListener {
        private BluetoothServiceListener() {
        }

        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 1 && AppRTCBluetoothManager.this.bluetoothState != State.UNINITIALIZED) {
                Log.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceConnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
                BluetoothHeadset unused = AppRTCBluetoothManager.this.bluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
                AppRTCBluetoothManager.this.updateAudioDeviceState();
                Log.d(AppRTCBluetoothManager.TAG, "onServiceConnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            }
        }

        public void onServiceDisconnected(int i) {
            if (i == 1 && AppRTCBluetoothManager.this.bluetoothState != State.UNINITIALIZED) {
                Log.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceDisconnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
                AppRTCBluetoothManager.this.stopScoAudio();
                BluetoothHeadset unused = AppRTCBluetoothManager.this.bluetoothHeadset = null;
                BluetoothDevice unused2 = AppRTCBluetoothManager.this.bluetoothDevice = null;
                State unused3 = AppRTCBluetoothManager.this.bluetoothState = State.HEADSET_UNAVAILABLE;
                AppRTCBluetoothManager.this.updateAudioDeviceState();
                Log.d(AppRTCBluetoothManager.TAG, "onServiceDisconnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            }
        }
    }

    private class BluetoothHeadsetBroadcastReceiver extends BroadcastReceiver {
        private BluetoothHeadsetBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (AppRTCBluetoothManager.this.bluetoothState != State.UNINITIALIZED) {
                String action = intent.getAction();
                if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                    Log.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_CONNECTION_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                    if (intExtra == 2) {
                        AppRTCBluetoothManager.this.scoConnectionAttempts = 0;
                        AppRTCBluetoothManager.this.updateAudioDeviceState();
                    } else if (!(intExtra == 1 || intExtra == 3 || intExtra != 0)) {
                        AppRTCBluetoothManager.this.stopScoAudio();
                        AppRTCBluetoothManager.this.updateAudioDeviceState();
                    }
                } else if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                    int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                    Log.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_AUDIO_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra2) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                    if (intExtra2 == 12) {
                        AppRTCBluetoothManager.this.cancelTimer();
                        if (AppRTCBluetoothManager.this.bluetoothState == State.SCO_CONNECTING) {
                            Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connected");
                            State unused = AppRTCBluetoothManager.this.bluetoothState = State.SCO_CONNECTED;
                            AppRTCBluetoothManager.this.scoConnectionAttempts = 0;
                            AppRTCBluetoothManager.this.updateAudioDeviceState();
                        } else {
                            Log.w(AppRTCBluetoothManager.TAG, "Unexpected state BluetoothHeadset.STATE_AUDIO_CONNECTED");
                        }
                    } else if (intExtra2 == 11) {
                        Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connecting...");
                    } else if (intExtra2 == 10) {
                        Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now disconnected");
                        if (isInitialStickyBroadcast()) {
                            Log.d(AppRTCBluetoothManager.TAG, "Ignore STATE_AUDIO_DISCONNECTED initial sticky broadcast.");
                            return;
                        }
                        AppRTCBluetoothManager.this.updateAudioDeviceState();
                    }
                }
                Log.d(AppRTCBluetoothManager.TAG, "onReceive done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            }
        }
    }

    static AppRTCBluetoothManager create(Context context, AppRTCAudioManager appRTCAudioManager) {
        Log.d(TAG, "create" + AppRTCUtils.getThreadInfo());
        return new AppRTCBluetoothManager(context, appRTCAudioManager);
    }

    protected AppRTCBluetoothManager(Context context, AppRTCAudioManager appRTCAudioManager) {
        Log.d(TAG, "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.apprtcContext = context;
        this.apprtcAudioManager = appRTCAudioManager;
        this.audioManager = getAudioManager(context);
        this.bluetoothState = State.UNINITIALIZED;
        this.bluetoothServiceListener = new BluetoothServiceListener();
        this.bluetoothHeadsetReceiver = new BluetoothHeadsetBroadcastReceiver();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public State getState() {
        ThreadUtils.checkIsOnMainThread();
        return this.bluetoothState;
    }

    public void start() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, TtmlNode.START);
        if (!hasPermission(this.apprtcContext, "android.permission.BLUETOOTH")) {
            Log.w(TAG, "Process (pid=" + Process.myPid() + ") lacks BLUETOOTH permission");
        } else if (this.bluetoothState != State.UNINITIALIZED) {
            Log.w(TAG, "Invalid BT state");
        } else {
            this.bluetoothHeadset = null;
            this.bluetoothDevice = null;
            this.scoConnectionAttempts = 0;
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.bluetoothAdapter = defaultAdapter;
            if (defaultAdapter == null) {
                Log.w(TAG, "Device does not support Bluetooth");
            } else if (!this.audioManager.isBluetoothScoAvailableOffCall()) {
                Log.e(TAG, "Bluetooth SCO audio is not available off call");
            } else {
                logBluetoothAdapterInfo(this.bluetoothAdapter);
                if (!getBluetoothProfileProxy(this.apprtcContext, this.bluetoothServiceListener, 1)) {
                    Log.e(TAG, "BluetoothAdapter.getProfileProxy(HEADSET) failed");
                    return;
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
                registerReceiver(this.bluetoothHeadsetReceiver, intentFilter);
                Log.d(TAG, "HEADSET profile state: " + stateToString(this.bluetoothAdapter.getProfileConnectionState(1)));
                Log.d(TAG, "Bluetooth proxy for headset profile has started");
                this.bluetoothState = State.HEADSET_UNAVAILABLE;
                Log.d(TAG, "start done: BT state=" + this.bluetoothState);
            }
        }
    }

    public void stop() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "stop: BT state=" + this.bluetoothState);
        if (this.bluetoothAdapter != null) {
            stopScoAudio();
            if (this.bluetoothState != State.UNINITIALIZED) {
                unregisterReceiver(this.bluetoothHeadsetReceiver);
                cancelTimer();
                BluetoothHeadset bluetoothHeadset2 = this.bluetoothHeadset;
                if (bluetoothHeadset2 != null) {
                    this.bluetoothAdapter.closeProfileProxy(1, bluetoothHeadset2);
                    this.bluetoothHeadset = null;
                }
                this.bluetoothAdapter = null;
                this.bluetoothDevice = null;
                this.bluetoothState = State.UNINITIALIZED;
                Log.d(TAG, "stop done: BT state=" + this.bluetoothState);
            }
        }
    }

    public boolean startScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "startSco: BT state=" + this.bluetoothState + ", attempts: " + this.scoConnectionAttempts + ", SCO is on: " + isScoOn());
        if (this.scoConnectionAttempts >= 2) {
            Log.e(TAG, "BT SCO connection fails - no more attempts");
            return false;
        } else if (this.bluetoothState != State.HEADSET_AVAILABLE) {
            Log.e(TAG, "BT SCO connection fails - no headset available");
            return false;
        } else {
            Log.d(TAG, "Starting Bluetooth SCO and waits for ACTION_AUDIO_STATE_CHANGED...");
            this.bluetoothState = State.SCO_CONNECTING;
            this.audioManager.startBluetoothSco();
            this.audioManager.setBluetoothScoOn(true);
            this.scoConnectionAttempts++;
            startTimer();
            Log.d(TAG, "startScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
            return true;
        }
    }

    public void stopScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "stopScoAudio: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        if (this.bluetoothState == State.SCO_CONNECTING || this.bluetoothState == State.SCO_CONNECTED) {
            cancelTimer();
            this.audioManager.stopBluetoothSco();
            this.audioManager.setBluetoothScoOn(false);
            this.bluetoothState = State.SCO_DISCONNECTING;
            Log.d(TAG, "stopScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        }
    }

    public void updateDevice() {
        if (this.bluetoothState != State.UNINITIALIZED && this.bluetoothHeadset != null) {
            Log.d(TAG, "updateDevice");
            List<BluetoothDevice> connectedDevices = this.bluetoothHeadset.getConnectedDevices();
            if (connectedDevices.isEmpty()) {
                this.bluetoothDevice = null;
                this.bluetoothState = State.HEADSET_UNAVAILABLE;
                Log.d(TAG, "No connected bluetooth headset");
            } else {
                this.bluetoothDevice = connectedDevices.get(0);
                this.bluetoothState = State.HEADSET_AVAILABLE;
                Log.d(TAG, "Connected bluetooth headset: name=" + this.bluetoothDevice.getName() + ", state=" + stateToString(this.bluetoothHeadset.getConnectionState(this.bluetoothDevice)) + ", SCO audio=" + this.bluetoothHeadset.isAudioConnected(this.bluetoothDevice));
            }
            Log.d(TAG, "updateDevice done: BT state=" + this.bluetoothState);
        }
    }

    /* access modifiers changed from: protected */
    public AudioManager getAudioManager(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    /* access modifiers changed from: protected */
    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        this.apprtcContext.unregisterReceiver(broadcastReceiver);
    }

    /* access modifiers changed from: protected */
    public boolean getBluetoothProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i) {
        return this.bluetoothAdapter.getProfileProxy(context, serviceListener, i);
    }

    /* access modifiers changed from: protected */
    public boolean hasPermission(Context context, String str) {
        return this.apprtcContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* access modifiers changed from: protected */
    public void logBluetoothAdapterInfo(BluetoothAdapter bluetoothAdapter2) {
        Log.d(TAG, "BluetoothAdapter: enabled=" + bluetoothAdapter2.isEnabled() + ", state=" + stateToString(bluetoothAdapter2.getState()) + ", name=" + bluetoothAdapter2.getName() + ", address=" + bluetoothAdapter2.getAddress());
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter2.getBondedDevices();
        if (!bondedDevices.isEmpty()) {
            Log.d(TAG, "paired devices:");
            for (BluetoothDevice next : bondedDevices) {
                Log.d(TAG, " name=" + next.getName() + ", address=" + next.getAddress());
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateAudioDeviceState() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "updateAudioDeviceState");
        this.apprtcAudioManager.updateAudioDeviceState();
    }

    private void startTimer() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "startTimer");
        this.handler.postDelayed(this.bluetoothTimeoutRunnable, TestUtils.FOUR_SECONDS);
    }

    /* access modifiers changed from: private */
    public void cancelTimer() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "cancelTimer");
        this.handler.removeCallbacks(this.bluetoothTimeoutRunnable);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bluetoothTimeout() {
        /*
            r4 = this;
            tvi.webrtc.ThreadUtils.checkIsOnMainThread()
            org.appspot.apprtc.AppRTCBluetoothManager$State r0 = r4.bluetoothState
            org.appspot.apprtc.AppRTCBluetoothManager$State r1 = org.appspot.apprtc.AppRTCBluetoothManager.State.UNINITIALIZED
            if (r0 == r1) goto L_0x00c2
            android.bluetooth.BluetoothHeadset r0 = r4.bluetoothHeadset
            if (r0 != 0) goto L_0x000f
            goto L_0x00c2
        L_0x000f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bluetoothTimeout: BT state="
            r0.append(r1)
            org.appspot.apprtc.AppRTCBluetoothManager$State r1 = r4.bluetoothState
            r0.append(r1)
            java.lang.String r1 = ", attempts: "
            r0.append(r1)
            int r1 = r4.scoConnectionAttempts
            r0.append(r1)
            java.lang.String r1 = ", SCO is on: "
            r0.append(r1)
            boolean r1 = r4.isScoOn()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "AppRTCBluetoothManager"
            android.util.Log.d(r1, r0)
            org.appspot.apprtc.AppRTCBluetoothManager$State r0 = r4.bluetoothState
            org.appspot.apprtc.AppRTCBluetoothManager$State r2 = org.appspot.apprtc.AppRTCBluetoothManager.State.SCO_CONNECTING
            if (r0 == r2) goto L_0x0044
            return
        L_0x0044:
            android.bluetooth.BluetoothHeadset r0 = r4.bluetoothHeadset
            java.util.List r0 = r0.getConnectedDevices()
            int r2 = r0.size()
            r3 = 0
            if (r2 <= 0) goto L_0x0097
            java.lang.Object r0 = r0.get(r3)
            android.bluetooth.BluetoothDevice r0 = (android.bluetooth.BluetoothDevice) r0
            r4.bluetoothDevice = r0
            android.bluetooth.BluetoothHeadset r2 = r4.bluetoothHeadset
            boolean r0 = r2.isAudioConnected(r0)
            if (r0 == 0) goto L_0x007d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SCO connected with "
            r0.append(r2)
            android.bluetooth.BluetoothDevice r2 = r4.bluetoothDevice
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            r0 = 1
            goto L_0x0098
        L_0x007d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SCO is not connected with "
            r0.append(r2)
            android.bluetooth.BluetoothDevice r2 = r4.bluetoothDevice
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L_0x0097:
            r0 = 0
        L_0x0098:
            if (r0 == 0) goto L_0x00a1
            org.appspot.apprtc.AppRTCBluetoothManager$State r0 = org.appspot.apprtc.AppRTCBluetoothManager.State.SCO_CONNECTED
            r4.bluetoothState = r0
            r4.scoConnectionAttempts = r3
            goto L_0x00a9
        L_0x00a1:
            java.lang.String r0 = "BT failed to connect after timeout"
            android.util.Log.w(r1, r0)
            r4.stopScoAudio()
        L_0x00a9:
            r4.updateAudioDeviceState()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "bluetoothTimeout done: BT state="
            r0.append(r2)
            org.appspot.apprtc.AppRTCBluetoothManager$State r2 = r4.bluetoothState
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.appspot.apprtc.AppRTCBluetoothManager.bluetoothTimeout():void");
    }

    private boolean isScoOn() {
        return this.audioManager.isBluetoothScoOn();
    }
}
