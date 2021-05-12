package fm.liveswitch.android;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTimestamp;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.AudioSource;
import fm.liveswitch.Constants;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Future;
import fm.liveswitch.IAction0;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.Promise;
import fm.liveswitch.SourceInput;
import fm.liveswitch.pcm.Format;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AudioRecordSource extends AudioSource {
    private static final int phoneSpeaker = 0;
    private static Set<String> voiceCommunicationBlackList = new HashSet(Arrays.asList(new String[]{"Nexus 5", "Nexus 9", "SC-04E", "GT-I9500", "SCH-I959", "SHV-E300K", "SHV-E300L", "SHV-E300S", "GT-I9505", "GT-I9508", "GT-I9508C", "SGH-M919N", "SAMSUNG-SGH-I337Z", "SAMSUNG-SGH-I337", "SGH-M919V", "SCH-R970C", "SCH-R970X", "SCH-I545L", "SPH-L720T", "SPH-L720", "SM-S975L", "SGH-S970G", "SGH-M919", "SCH-R970", "SCH-I545", "SCH-I545PP", "GT-I9507", "GT-I9507V", "GT-I9515", "GT-I9515L", "GT-I9505X", "GT-I9508V", "GT-I9506", "SHV-E330K", "SHV-E330L", "SC-04F", "SCL23", "SM-G900H", "SM-G9008W", "SM-G9009W", "SM-G900F", "SM-G900FQ", "SM-G900I", "SM-G900M", "SM-G900MD", "SM-G900T1", "SM-G900T4", "SM-G900R7", "SAMSUNG-SM-G900AZ", "SAMSUNG-SM-G900A", "SM-G900W8", "SM-G9006W", "SM-G900K", "SM-G900L", "SM-G900R6", "SM-G900S", "SM-G900P", "SM-S903VL", "SM-G900T", "SM-G900T3", "SM-G900R4", "SM-G900V", "SM-G900X", "SM-G906K", "SM-G906L", "SM-G906S", "HUAWEI Y530-U051", "XT1022", "HTC Desire 828 dual sim"}));
    private static final int wiredHeadSet = 1;
    /* access modifiers changed from: private */
    public AcousticEchoCanceler aec = null;
    /* access modifiers changed from: private */
    public AutomaticGainControl agc = null;
    /* access modifiers changed from: private */
    public AudioManager audioManager;
    /* access modifiers changed from: private */
    public AudioRecord audioRecord = null;
    private int audioSource = 7;
    /* access modifiers changed from: private */
    public int bufferLength = 0;
    private Context context;
    private HeadsetStateReceiver headsetStateReceiver;
    /* access modifiers changed from: private */
    public volatile boolean isCapturing;
    /* access modifiers changed from: private */
    public volatile boolean isStopped;
    /* access modifiers changed from: private */
    public int localAudioOutlet = 0;
    private HeadSetReceiver myReceiver;
    /* access modifiers changed from: private */
    public NoiseSuppressor ns = null;
    /* access modifiers changed from: private */
    public int savedAudioManagerMode;
    /* access modifiers changed from: private */
    public boolean telephonyStateIdle = true;
    private boolean useAcousticEchoCanceler = false;
    private boolean useAutomaticGainControl = true;
    private boolean useNoiseSuppressor = true;

    public String getLabel() {
        return "Android AudioRecord Source";
    }

    public static void addDeviceToVoiceCommunicationBlackList(String str) {
        voiceCommunicationBlackList.add(str);
    }

    public static void removeDeviceFromVoiceCommunicationBlackList(String str) {
        voiceCommunicationBlackList.remove(str);
    }

    public static String[] getVoiceCommunicationBlackList() {
        Set<String> set = voiceCommunicationBlackList;
        return (String[]) set.toArray(new String[set.size()]);
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    public void setAudioSource(int i) {
        this.audioSource = i;
    }

    public boolean getUseAcousticEchoCanceler() {
        return this.useAcousticEchoCanceler;
    }

    public void setUseAcousticEchoCanceler(boolean z) {
        this.useAcousticEchoCanceler = z;
    }

    public boolean getUseAutomaticGainControl() {
        return this.useAutomaticGainControl;
    }

    public void setUseAutomaticGainControl(boolean z) {
        this.useAutomaticGainControl = z;
    }

    public boolean getUseNoiseSuppressor() {
        return this.useNoiseSuppressor;
    }

    public void setUseNoiseSuppressor(boolean z) {
        this.useNoiseSuppressor = z;
    }

    public static int getBufferDelay(AudioConfig audioConfig) {
        int clockRate = audioConfig.getClockRate();
        int channelCount = audioConfig.getChannelCount();
        return AudioRecord.getMinBufferSize(clockRate, channelCount == 1 ? 16 : 12, 2) / (((clockRate * channelCount) * 2) / 1000);
    }

    public Future<SourceInput[]> getInputs() {
        Promise promise = new Promise();
        promise.resolve(new SourceInput[0]);
        return promise;
    }

    public AudioRecordSource(Context context2, AudioConfig audioConfig) {
        super(new Format(audioConfig));
        if (context2 == null) {
            throw new RuntimeException("Context cannot be null.");
        } else if (context2.checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            if (voiceCommunicationBlackList.contains(Build.MODEL)) {
                this.audioSource = 1;
                Log.info(String.format(Locale.getDefault(), "MIC mode is being used for Device : ", new Object[]{Build.MODEL}));
            } else {
                Log.info(String.format(Locale.getDefault(), "VoiceCommunication is being used for Device : ", new Object[]{Build.MODEL}));
            }
            this.context = context2.getApplicationContext();
            setOutputSynchronizable(Build.VERSION.SDK_INT >= 24);
            if (getAudioSource() == 7) {
                AudioManager audioManager2 = (AudioManager) this.context.getSystemService("audio");
                this.audioManager = audioManager2;
                this.savedAudioManagerMode = audioManager2.getMode();
                this.audioManager.setMode(3);
                this.audioManager.setSpeakerphoneOn(true);
                this.audioManager.setWiredHeadsetOn(false);
                this.localAudioOutlet = 0;
                this.myReceiver = new HeadSetReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.intent.action.PHONE_STATE");
                intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
                this.context.registerReceiver(this.myReceiver, intentFilter);
                if (isBluetoothHeadsetConnected()) {
                    routeToBluetoothHeadset();
                    return;
                }
                return;
            }
            HeadsetStateReceiver headsetStateReceiver2 = new HeadsetStateReceiver(this);
            this.headsetStateReceiver = headsetStateReceiver2;
            this.context.registerReceiver(headsetStateReceiver2, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        } else {
            throw new RuntimeException("Audio capture permission has not been granted. Please add android.permission.RECORD_AUDIO to your application manifest.");
        }
    }

    public boolean destroy() {
        HeadsetStateReceiver headsetStateReceiver2 = this.headsetStateReceiver;
        if (headsetStateReceiver2 != null) {
            this.context.unregisterReceiver(headsetStateReceiver2);
            this.headsetStateReceiver = null;
        }
        HeadSetReceiver headSetReceiver = this.myReceiver;
        if (headSetReceiver != null) {
            this.context.unregisterReceiver(headSetReceiver);
            this.myReceiver = null;
        }
        return super.destroy();
    }

    private class HeadSetReceiver extends BroadcastReceiver {
        private HeadSetReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            AudioRecordSource.this.setForceTimestampReset(true);
            if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
                int intExtra = intent.getIntExtra("state", -1);
                if (intExtra == 0) {
                    AudioRecordSource.this.audioManager.setSpeakerphoneOn(true);
                    AudioRecordSource.this.audioManager.setWiredHeadsetOn(false);
                    int unused = AudioRecordSource.this.localAudioOutlet = 0;
                    if (AudioRecordSource.this.isBluetoothHeadsetConnected()) {
                        AudioRecordSource.this.routeToBluetoothHeadset();
                    }
                } else if (intExtra == 1) {
                    AudioRecordSource.this.audioManager.setSpeakerphoneOn(false);
                    AudioRecordSource.this.audioManager.setWiredHeadsetOn(true);
                    int unused2 = AudioRecordSource.this.localAudioOutlet = 1;
                    if (AudioRecordSource.this.isBluetoothHeadsetConnected()) {
                        AudioRecordSource.this.routeToBluetoothHeadset();
                    }
                }
            }
            if (intent.getAction().equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                if (intExtra2 == 0) {
                    AudioRecordSource.this.audioManager.setBluetoothScoOn(false);
                    AudioRecordSource.this.audioManager.stopBluetoothSco();
                    if (AudioRecordSource.this.localAudioOutlet == 0) {
                        AudioRecordSource.this.audioManager.setSpeakerphoneOn(true);
                        AudioRecordSource.this.audioManager.setWiredHeadsetOn(false);
                    } else if (AudioRecordSource.this.localAudioOutlet == 1) {
                        AudioRecordSource.this.audioManager.setSpeakerphoneOn(false);
                        AudioRecordSource.this.audioManager.setWiredHeadsetOn(true);
                    }
                } else if (intExtra2 == 2) {
                    AudioRecordSource.this.routeToBluetoothHeadset();
                }
            }
            if (intent.getAction().equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED") && intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10) == 10 && AudioRecordSource.this.telephonyStateIdle && AudioRecordSource.this.isBluetoothHeadsetConnected()) {
                Log.debug("Reconnecting BlueTooth");
                AudioRecordSource.this.routeToBluetoothHeadset();
            }
            if ("OFFHOOK".equals(intent.getStringExtra("state"))) {
                boolean unused3 = AudioRecordSource.this.telephonyStateIdle = false;
                AudioRecordSource.this.resetAudioManagerSettings();
            }
            if ("IDLE".equals(intent.getStringExtra("state"))) {
                boolean unused4 = AudioRecordSource.this.telephonyStateIdle = true;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (AudioRecordSource.this.localAudioOutlet == 0) {
                            AudioRecordSource.this.audioManager.setSpeakerphoneOn(true);
                            AudioRecordSource.this.audioManager.setWiredHeadsetOn(false);
                        } else if (AudioRecordSource.this.localAudioOutlet == 1) {
                            AudioRecordSource.this.audioManager.setSpeakerphoneOn(false);
                            AudioRecordSource.this.audioManager.setWiredHeadsetOn(true);
                        }
                    }
                }, 400);
            }
        }
    }

    public boolean isBluetoothHeadsetConnected() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled() || defaultAdapter.getProfileConnectionState(1) != 2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void resetAudioManagerSettings() {
        this.audioManager.stopBluetoothSco();
        this.audioManager.setBluetoothScoOn(false);
        this.audioManager.setSpeakerphoneOn(false);
        this.audioManager.setWiredHeadsetOn(false);
    }

    public void routeToBluetoothHeadset() {
        resetAudioManagerSettings();
        this.audioManager.setSpeakerphoneOn(false);
        this.audioManager.setWiredHeadsetOn(false);
        this.audioManager.setBluetoothScoOn(true);
        this.audioManager.startBluetoothSco();
    }

    class HeadsetStateReceiver extends BroadcastReceiver {
        private AudioSource source;

        public HeadsetStateReceiver(AudioSource audioSource) {
            this.source = audioSource;
        }

        public void onReceive(Context context, Intent intent) {
            this.source.setForceTimestampReset(true);
        }
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            /* JADX WARNING: Removed duplicated region for block: B:36:0x00dd A[Catch:{ Exception -> 0x012f }] */
            /* JADX WARNING: Removed duplicated region for block: B:46:0x0129 A[Catch:{ Exception -> 0x012f }] */
            /* JADX WARNING: Removed duplicated region for block: B:55:0x0153 A[Catch:{ Exception -> 0x01a5 }] */
            /* JADX WARNING: Removed duplicated region for block: B:65:0x019f A[Catch:{ Exception -> 0x01a5 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void invoke() {
                /*
                    r10 = this;
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x01f2 }
                    int r3 = r0.getClockRate()     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.MediaFormat r0 = r0.getOutputFormat()     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.AudioFormat r0 = (fm.liveswitch.AudioFormat) r0     // Catch:{ Exception -> 0x01f2 }
                    int r0 = r0.getChannelCount()     // Catch:{ Exception -> 0x01f2 }
                    r7 = 16
                    r8 = 1
                    if (r0 != r8) goto L_0x0020
                    r4 = 16
                    goto L_0x0024
                L_0x0020:
                    r0 = 12
                    r4 = 12
                L_0x0024:
                    r0 = 2
                    int r0 = android.media.AudioRecord.getMinBufferSize(r3, r4, r0)     // Catch:{ Exception -> 0x01f2 }
                    if (r0 == 0) goto L_0x01ea
                    fm.liveswitch.android.AudioRecordSource r1 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    int unused = r1.bufferLength = r0     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    android.media.AudioRecord r9 = new android.media.AudioRecord     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r1 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    int r2 = r1.getAudioSource()     // Catch:{ Exception -> 0x01f2 }
                    r5 = 2
                    fm.liveswitch.android.AudioRecordSource r1 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    int r6 = r1.bufferLength     // Catch:{ Exception -> 0x01f2 }
                    r1 = r9
                    r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x01f2 }
                    android.media.AudioRecord unused = r0.audioRecord = r9     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x01f2 }
                    int r0 = r0.getState()     // Catch:{ Exception -> 0x01f2 }
                    if (r0 != r8) goto L_0x01e2
                    int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01f2 }
                    r1 = 0
                    if (r0 < r7) goto L_0x01bb
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    boolean r0 = r0.getUseAcousticEchoCanceler()     // Catch:{ Exception -> 0x00b9 }
                    if (r0 == 0) goto L_0x00b3
                    boolean r0 = android.media.audiofx.AcousticEchoCanceler.isAvailable()     // Catch:{ Exception -> 0x00b9 }
                    if (r0 == 0) goto L_0x00b3
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    android.media.AudioRecord r2 = r0.audioRecord     // Catch:{ Exception -> 0x00b9 }
                    int r2 = r2.getAudioSessionId()     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler r2 = android.media.audiofx.AcousticEchoCanceler.create(r2)     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler unused = r0.aec = r2     // Catch:{ Exception -> 0x00b9 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x00b9 }
                    if (r0 == 0) goto L_0x00ad
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x00b9 }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x00b9 }
                    if (r0 != 0) goto L_0x0095
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x00b9 }
                    r0.setEnabled(r8)     // Catch:{ Exception -> 0x00b9 }
                L_0x0095:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x00b9 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x00b9 }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x00b9 }
                    if (r0 == 0) goto L_0x00a7
                    java.lang.String r0 = "Acoustic echo canceler is active."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x00b9 }
                    goto L_0x00cf
                L_0x00a7:
                    java.lang.String r0 = "Acoustic echo canceler was created, but could not be enabled."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x00b9 }
                    goto L_0x00cf
                L_0x00ad:
                    java.lang.String r0 = "Acoustic echo canceler is available, but could not be created."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x00b9 }
                    goto L_0x00cf
                L_0x00b3:
                    java.lang.String r0 = "Acoustic echo canceler is not available."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x00b9 }
                    goto L_0x00cf
                L_0x00b9:
                    r0 = move-exception
                    java.util.Locale r2 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r3 = "Acoustic echo canceler could not be enabled. %s"
                    java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x01f2 }
                    r4[r1] = r0     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = java.lang.String.format(r2, r3, r4)     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.Log.error(r0)     // Catch:{ Exception -> 0x01f2 }
                L_0x00cf:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    boolean r0 = r0.getUseAutomaticGainControl()     // Catch:{ Exception -> 0x012f }
                    if (r0 == 0) goto L_0x0129
                    boolean r0 = android.media.audiofx.AutomaticGainControl.isAvailable()     // Catch:{ Exception -> 0x012f }
                    if (r0 == 0) goto L_0x0129
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    android.media.AudioRecord r2 = r0.audioRecord     // Catch:{ Exception -> 0x012f }
                    int r2 = r2.getAudioSessionId()     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl r2 = android.media.audiofx.AutomaticGainControl.create(r2)     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl unused = r0.agc = r2     // Catch:{ Exception -> 0x012f }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x012f }
                    if (r0 == 0) goto L_0x0123
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x012f }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x012f }
                    if (r0 != 0) goto L_0x010b
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x012f }
                    r0.setEnabled(r8)     // Catch:{ Exception -> 0x012f }
                L_0x010b:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x012f }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x012f }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x012f }
                    if (r0 == 0) goto L_0x011d
                    java.lang.String r0 = "Automatic gain control is active."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x012f }
                    goto L_0x0145
                L_0x011d:
                    java.lang.String r0 = "Automatic gain control was created, but could not be enabled."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x012f }
                    goto L_0x0145
                L_0x0123:
                    java.lang.String r0 = "Automatic gain control is available, but could not be created."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x012f }
                    goto L_0x0145
                L_0x0129:
                    java.lang.String r0 = "Automatic gain control is not available."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x012f }
                    goto L_0x0145
                L_0x012f:
                    r0 = move-exception
                    java.util.Locale r2 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r3 = "Automatic gain control could not be enabled. %s"
                    java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x01f2 }
                    r4[r1] = r0     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = java.lang.String.format(r2, r3, r4)     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.Log.error(r0)     // Catch:{ Exception -> 0x01f2 }
                L_0x0145:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    boolean r0 = r0.getUseNoiseSuppressor()     // Catch:{ Exception -> 0x01a5 }
                    if (r0 == 0) goto L_0x019f
                    boolean r0 = android.media.audiofx.NoiseSuppressor.isAvailable()     // Catch:{ Exception -> 0x01a5 }
                    if (r0 == 0) goto L_0x019f
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    android.media.AudioRecord r2 = r0.audioRecord     // Catch:{ Exception -> 0x01a5 }
                    int r2 = r2.getAudioSessionId()     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor r2 = android.media.audiofx.NoiseSuppressor.create(r2)     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor unused = r0.ns = r2     // Catch:{ Exception -> 0x01a5 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x01a5 }
                    if (r0 == 0) goto L_0x0199
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x01a5 }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x01a5 }
                    if (r0 != 0) goto L_0x0181
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x01a5 }
                    r0.setEnabled(r8)     // Catch:{ Exception -> 0x01a5 }
                L_0x0181:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01a5 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x01a5 }
                    boolean r0 = r0.getEnabled()     // Catch:{ Exception -> 0x01a5 }
                    if (r0 == 0) goto L_0x0193
                    java.lang.String r0 = "Noise suppressor is active."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x01a5 }
                    goto L_0x01bb
                L_0x0193:
                    java.lang.String r0 = "Noise suppressor was created, but could not be enabled."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x01a5 }
                    goto L_0x01bb
                L_0x0199:
                    java.lang.String r0 = "Noise suppressor is available, but could not be created."
                    fm.liveswitch.Log.warn(r0)     // Catch:{ Exception -> 0x01a5 }
                    goto L_0x01bb
                L_0x019f:
                    java.lang.String r0 = "Noise suppressor is not available."
                    fm.liveswitch.Log.info(r0)     // Catch:{ Exception -> 0x01a5 }
                    goto L_0x01bb
                L_0x01a5:
                    r0 = move-exception
                    java.util.Locale r2 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r3 = "Noise suppressor could not be enabled. %s"
                    java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x01f2 }
                    r4[r1] = r0     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r0 = java.lang.String.format(r2, r3, r4)     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.Log.error(r0)     // Catch:{ Exception -> 0x01f2 }
                L_0x01bb:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x01f2 }
                    r0.startRecording()     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    boolean unused = r0.isCapturing = r8     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x01f2 }
                    boolean unused = r0.isStopped = r1     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.ManagedThread r0 = new fm.liveswitch.ManagedThread     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.android.AudioRecordSource$1$1 r1 = new fm.liveswitch.android.AudioRecordSource$1$1     // Catch:{ Exception -> 0x01f2 }
                    r1.<init>()     // Catch:{ Exception -> 0x01f2 }
                    r0.<init>(r1)     // Catch:{ Exception -> 0x01f2 }
                    r0.start()     // Catch:{ Exception -> 0x01f2 }
                    fm.liveswitch.Promise r0 = r0     // Catch:{ Exception -> 0x01f2 }
                    r1 = 0
                    r0.resolve(r1)     // Catch:{ Exception -> 0x01f2 }
                    goto L_0x01f8
                L_0x01e2:
                    java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r1 = "Could not start audio capture using specified configuration."
                    r0.<init>(r1)     // Catch:{ Exception -> 0x01f2 }
                    throw r0     // Catch:{ Exception -> 0x01f2 }
                L_0x01ea:
                    java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x01f2 }
                    java.lang.String r1 = "Audio capture min buffer size must be greater than 0."
                    r0.<init>(r1)     // Catch:{ Exception -> 0x01f2 }
                    throw r0     // Catch:{ Exception -> 0x01f2 }
                L_0x01f2:
                    r0 = move-exception
                    fm.liveswitch.Promise r1 = r0
                    r1.reject(r0)
                L_0x01f8:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.AudioRecordSource.AnonymousClass1.invoke():void");
            }
        });
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|(2:4|2)|27|5|(1:7)|8|9|(1:11)|12|13|(1:15)|16|17|(1:19)|20|21|(1:23)|24|29) */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0036 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0047 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0058 */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x003e A[Catch:{ Exception -> 0x0047 }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[Catch:{ Exception -> 0x0058 }] */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0060 A[Catch:{ Exception -> 0x007f }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void invoke() {
                /*
                    r2 = this;
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    r1 = 0
                    boolean unused = r0.isCapturing = r1     // Catch:{ Exception -> 0x007f }
                L_0x0006:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    boolean r0 = r0.isStopped     // Catch:{ Exception -> 0x007f }
                    if (r0 != 0) goto L_0x0014
                    r0 = 10
                    fm.liveswitch.ManagedThread.sleep(r0)     // Catch:{ Exception -> 0x007f }
                    goto L_0x0006
                L_0x0014:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x007f }
                    if (r0 == 0) goto L_0x0025
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x007f }
                    r0.stop()     // Catch:{ Exception -> 0x007f }
                L_0x0025:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0036 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x0036 }
                    if (r0 == 0) goto L_0x0036
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0036 }
                    android.media.audiofx.AcousticEchoCanceler r0 = r0.aec     // Catch:{ Exception -> 0x0036 }
                    r0.release()     // Catch:{ Exception -> 0x0036 }
                L_0x0036:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0047 }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x0047 }
                    if (r0 == 0) goto L_0x0047
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0047 }
                    android.media.audiofx.AutomaticGainControl r0 = r0.agc     // Catch:{ Exception -> 0x0047 }
                    r0.release()     // Catch:{ Exception -> 0x0047 }
                L_0x0047:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0058 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x0058 }
                    if (r0 == 0) goto L_0x0058
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x0058 }
                    android.media.audiofx.NoiseSuppressor r0 = r0.ns     // Catch:{ Exception -> 0x0058 }
                    r0.release()     // Catch:{ Exception -> 0x0058 }
                L_0x0058:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x007f }
                    if (r0 == 0) goto L_0x0069
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    android.media.AudioRecord r0 = r0.audioRecord     // Catch:{ Exception -> 0x007f }
                    r0.release()     // Catch:{ Exception -> 0x007f }
                L_0x0069:
                    fm.liveswitch.android.AudioRecordSource r0 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    android.media.AudioManager r0 = r0.audioManager     // Catch:{ Exception -> 0x007f }
                    fm.liveswitch.android.AudioRecordSource r1 = fm.liveswitch.android.AudioRecordSource.this     // Catch:{ Exception -> 0x007f }
                    int r1 = r1.savedAudioManagerMode     // Catch:{ Exception -> 0x007f }
                    r0.setMode(r1)     // Catch:{ Exception -> 0x007f }
                    fm.liveswitch.Promise r0 = r0     // Catch:{ Exception -> 0x007f }
                    r1 = 0
                    r0.resolve(r1)     // Catch:{ Exception -> 0x007f }
                    goto L_0x0085
                L_0x007f:
                    r0 = move-exception
                    fm.liveswitch.Promise r1 = r0
                    r1.reject(r0)
                L_0x0085:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.AudioRecordSource.AnonymousClass2.invoke():void");
            }
        });
        return promise;
    }

    /* access modifiers changed from: private */
    public void captureLoop() {
        if (Process.getThreadPriority(Process.myTid()) != -19) {
            Process.setThreadPriority(-19);
        }
        int i = this.bufferLength;
        byte[] bArr = new byte[i];
        boolean z = Build.VERSION.SDK_INT >= 24;
        while (this.isCapturing) {
            long j = -1;
            if (z) {
                AudioTimestamp audioTimestamp = new AudioTimestamp();
                if (this.audioRecord.getTimestamp(audioTimestamp, 0) == 0) {
                    j = audioTimestamp.nanoTime / ((long) Constants.getNanosecondsPerTick());
                }
            }
            int read = this.audioRecord.read(bArr, 0, i);
            if (read > 0) {
                try {
                    DataBuffer wrap = DataBuffer.wrap(bArr, 0, read);
                    wrap.setLittleEndian(((AudioFormat) getOutputFormat()).getLittleEndian());
                    AudioFrame audioFrame = new AudioFrame(calculateDuration(wrap.getLength()), new AudioBuffer(wrap, (AudioFormat) getOutputFormat()));
                    audioFrame.setSystemTimestamp(j);
                    raiseFrame(audioFrame);
                } catch (Exception e) {
                    Log.error("Could not raise frame from AudioRecordSource.", e);
                }
            }
        }
        this.isStopped = true;
    }
}
