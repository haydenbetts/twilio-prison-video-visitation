package tvi.webrtc.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import tvi.webrtc.Logging;
import tvi.webrtc.NativeClassQualifiedName;
import tvi.webrtc.ThreadUtils;
import tvi.webrtc.audio.JavaAudioDeviceModule;

class WebRtcAudioTrack {
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int DEFAULT_USAGE = getDefaultUsageAttribute();
    private static final String TAG = "WebRtcAudioTrackExternal";
    private final AudioManager audioManager;
    @Nullable
    private AudioTrackThread audioThread;
    /* access modifiers changed from: private */
    @Nullable
    public AudioTrack audioTrack;
    /* access modifiers changed from: private */
    public ByteBuffer byteBuffer;
    private final Context context;
    /* access modifiers changed from: private */
    public byte[] emptyBytes;
    @Nullable
    private final JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback;
    /* access modifiers changed from: private */
    public long nativeAudioTrack;
    /* access modifiers changed from: private */
    public volatile boolean speakerMute;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final VolumeLogger volumeLogger;

    private int channelCountToConfiguration(int i) {
        return i == 1 ? 4 : 12;
    }

    private static int getDefaultUsageAttributeOnLollipopOrHigher() {
        return 2;
    }

    @NativeClassQualifiedName("webrtc::android_adm::AudioTrackJni")
    private static native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer2);

    /* access modifiers changed from: private */
    @NativeClassQualifiedName("webrtc::android_adm::AudioTrackJni")
    public static native void nativeGetPlayoutData(long j, int i);

    private static int getDefaultUsageAttribute() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return getDefaultUsageAttributeOnLollipopOrHigher();
        }
        return 0;
    }

    private class AudioTrackThread extends Thread {
        private volatile boolean keepAlive = true;

        public AudioTrackThread(String str) {
            super(str);
        }

        public void run() {
            int i;
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
            int capacity = WebRtcAudioTrack.this.byteBuffer.capacity();
            while (this.keepAlive) {
                WebRtcAudioTrack.nativeGetPlayoutData(WebRtcAudioTrack.this.nativeAudioTrack, capacity);
                WebRtcAudioTrack.assertTrue(capacity <= WebRtcAudioTrack.this.byteBuffer.remaining());
                if (WebRtcAudioTrack.this.speakerMute) {
                    WebRtcAudioTrack.this.byteBuffer.clear();
                    WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                    WebRtcAudioTrack.this.byteBuffer.position(0);
                }
                if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
                    i = writeOnLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, capacity);
                } else {
                    i = writePreLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, capacity);
                }
                if (i != capacity) {
                    Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.write played invalid number of bytes: " + i);
                    if (i < 0) {
                        this.keepAlive = false;
                        WebRtcAudioTrack webRtcAudioTrack = WebRtcAudioTrack.this;
                        webRtcAudioTrack.reportWebRtcAudioTrackError("AudioTrack.write failed: " + i);
                    }
                }
                WebRtcAudioTrack.this.byteBuffer.rewind();
            }
            if (WebRtcAudioTrack.this.audioTrack != null) {
                Logging.d(WebRtcAudioTrack.TAG, "Calling AudioTrack.stop...");
                try {
                    WebRtcAudioTrack.this.audioTrack.stop();
                    Logging.d(WebRtcAudioTrack.TAG, "AudioTrack.stop is done.");
                } catch (IllegalStateException e) {
                    Logging.e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e.getMessage());
                }
            }
        }

        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer, i, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
        }

        public void stopThread() {
            Logging.d(WebRtcAudioTrack.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    WebRtcAudioTrack(Context context2, AudioManager audioManager2) {
        this(context2, audioManager2, (JavaAudioDeviceModule.AudioTrackErrorCallback) null);
    }

    WebRtcAudioTrack(Context context2, AudioManager audioManager2, @Nullable JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback) {
        ThreadUtils.ThreadChecker threadChecker2 = new ThreadUtils.ThreadChecker();
        this.threadChecker = threadChecker2;
        this.audioTrack = null;
        this.audioThread = null;
        this.speakerMute = false;
        threadChecker2.detachThread();
        this.context = context2;
        this.audioManager = audioManager2;
        this.errorCallback = audioTrackErrorCallback;
        this.volumeLogger = new VolumeLogger(audioManager2);
    }

    public void setNativeAudioTrack(long j) {
        this.nativeAudioTrack = j;
    }

    private boolean initPlayout(int i, int i2) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initPlayout(sampleRate=" + i + ", channels=" + i2 + ")");
        this.byteBuffer = ByteBuffer.allocateDirect(i2 * 2 * (i / 100));
        StringBuilder sb = new StringBuilder();
        sb.append("byteBuffer.capacity: ");
        sb.append(this.byteBuffer.capacity());
        Logging.d(TAG, sb.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioTrack, this.byteBuffer);
        int channelCountToConfiguration = channelCountToConfiguration(i2);
        int minBufferSize = AudioTrack.getMinBufferSize(i, channelCountToConfiguration, 2);
        Logging.d(TAG, "AudioTrack.getMinBufferSize: " + minBufferSize);
        if (minBufferSize < this.byteBuffer.capacity()) {
            reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
            return false;
        } else if (this.audioTrack != null) {
            reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
            return false;
        } else {
            try {
                if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
                    this.audioTrack = createAudioTrackOnLollipopOrHigher(i, channelCountToConfiguration, minBufferSize);
                } else {
                    this.audioTrack = createAudioTrackOnLowerThanLollipop(i, channelCountToConfiguration, minBufferSize);
                }
                AudioTrack audioTrack2 = this.audioTrack;
                if (audioTrack2 == null || audioTrack2.getState() != 1) {
                    reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
                    releaseAudioResources();
                    return false;
                }
                logMainParameters();
                logMainParametersExtended();
                return true;
            } catch (IllegalArgumentException e) {
                reportWebRtcAudioTrackInitError(e.getMessage());
                releaseAudioResources();
                return false;
            }
        }
    }

    private boolean startPlayout() {
        this.threadChecker.checkIsOnValidThread();
        this.volumeLogger.start();
        Logging.d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioTrack.play();
            if (this.audioTrack.getPlayState() != 3) {
                JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH;
                reportWebRtcAudioTrackStartError(audioTrackStartErrorCode, "AudioTrack.play failed - incorrect state :" + this.audioTrack.getPlayState());
                releaseAudioResources();
                return false;
            }
            AudioTrackThread audioTrackThread = new AudioTrackThread("AudioTrackJavaThread");
            this.audioThread = audioTrackThread;
            audioTrackThread.start();
            return true;
        } catch (IllegalStateException e) {
            JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode2 = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION;
            reportWebRtcAudioTrackStartError(audioTrackStartErrorCode2, "AudioTrack.play failed: " + e.getMessage());
            releaseAudioResources();
            return false;
        }
    }

    private boolean stopPlayout() {
        this.threadChecker.checkIsOnValidThread();
        this.volumeLogger.stop();
        Logging.d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.stopThread();
        Logging.d(TAG, "Stopping the AudioTrackThread...");
        this.audioThread.interrupt();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000)) {
            Logging.e(TAG, "Join of AudioTrackThread timed out.");
            WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        }
        Logging.d(TAG, "AudioTrackThread has now been stopped.");
        this.audioThread = null;
        releaseAudioResources();
        return true;
    }

    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamMaxVolume");
        return this.audioManager.getStreamMaxVolume(0);
    }

    private boolean setStreamVolume(int i) {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "setStreamVolume(" + i + ")");
        if (isVolumeFixed()) {
            Logging.e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, i, 0);
        return true;
    }

    private boolean isVolumeFixed() {
        if (!WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return false;
        }
        return this.audioManager.isVolumeFixed();
    }

    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.d(TAG, "getStreamVolume");
        return this.audioManager.getStreamVolume(0);
    }

    private void logMainParameters() {
        Logging.d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    private static AudioTrack createAudioTrackOnLollipopOrHigher(int i, int i2, int i3) {
        Logging.d(TAG, "createAudioTrackOnLollipopOrHigher");
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Logging.d(TAG, "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (i != nativeOutputSampleRate) {
            Logging.w(TAG, "Unable to use fast mode since requested sample rate is not native");
        }
        return new AudioTrack(new AudioAttributes.Builder().setUsage(DEFAULT_USAGE).setContentType(1).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build(), i3, 1, 0);
    }

    private static AudioTrack createAudioTrackOnLowerThanLollipop(int i, int i2, int i3) {
        return new AudioTrack(0, i, i2, 2, i3, 1);
    }

    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    private void logUnderrunCount() {
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
        }
    }

    /* access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public void setSpeakerMute(boolean z) {
        Logging.w(TAG, "setSpeakerMute(" + z + ")");
        this.speakerMute = z;
    }

    private void releaseAudioResources() {
        Logging.d(TAG, "releaseAudioResources");
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.release();
            this.audioTrack = null;
        }
    }

    private void reportWebRtcAudioTrackInitError(String str) {
        Logging.e(TAG, "Init playout error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackInitError(str);
        }
    }

    private void reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode, String str) {
        Logging.e(TAG, "Start playout error: " + audioTrackStartErrorCode + ". " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackStartError(audioTrackStartErrorCode, str);
        }
    }

    /* access modifiers changed from: private */
    public void reportWebRtcAudioTrackError(String str) {
        Logging.e(TAG, "Run-time playback error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackError(str);
        }
    }
}
