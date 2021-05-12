package tvi.webrtc;

import android.content.Context;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase;
import tvi.webrtc.NativeLibrary;
import tvi.webrtc.PeerConnection;
import tvi.webrtc.audio.AudioDeviceModule;
import tvi.webrtc.audio.LegacyAudioDeviceModule;

@JNINamespace("webrtc::jni")
public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    public static final String TRIAL_ENABLED = "Enabled";
    private static final String VIDEO_CAPTURER_THREAD_NAME = "VideoCapturerThread";
    @Deprecated
    public static final String VIDEO_FRAME_EMIT_TRIAL = "VideoFrameEmit";
    private static volatile boolean internalTracerInitialized = false;
    @Nullable
    private static Thread networkThread;
    @Nullable
    private static Thread signalingThread;
    @Nullable
    private static Thread workerThread;
    private EglBase localEglbase;
    private final long nativeFactory;
    private EglBase remoteEglbase;

    private static native long nativeCreateAudioSource(long j, MediaConstraints mediaConstraints);

    private static native long nativeCreateAudioTrack(long j, String str, long j2);

    private static native long nativeCreateLocalMediaStream(long j, String str);

    private static native long nativeCreatePeerConnection(long j, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j2);

    private static native long nativeCreatePeerConnectionFactory(Context context, Options options, long j, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory, long j2, long j3);

    private static native long nativeCreateVideoSource(long j, SurfaceTextureHelper surfaceTextureHelper, boolean z);

    private static native long nativeCreateVideoTrack(long j, String str, long j2);

    private static native String nativeFindFieldTrialsFullName(String str);

    private static native void nativeFreeFactory(long j);

    private static native long nativeGetNativePeerConnectionFactory(long j);

    private static native void nativeInitializeAndroidGlobals(boolean z);

    private static native void nativeInitializeFieldTrials(String str);

    private static native void nativeInitializeInternalTracer();

    private static native void nativeInvokeThreadsCallbacks(long j);

    private static native void nativeSetVideoHwAccelerationOptions(long j, Object obj, Object obj2);

    private static native void nativeShutdownInternalTracer();

    private static native boolean nativeStartAecDump(long j, int i, int i2);

    private static native boolean nativeStartInternalTracingCapture(String str);

    private static native void nativeStopAecDump(long j);

    private static native void nativeStopInternalTracingCapture();

    @Deprecated
    public native void nativeSetOptions(long j, Options options);

    public static class InitializationOptions {
        final Context applicationContext;
        final boolean enableInternalTracer;
        final boolean enableVideoHwAcceleration;
        final String fieldTrials;
        final NativeLibraryLoader nativeLibraryLoader;

        private InitializationOptions(Context context, String str, boolean z, boolean z2, NativeLibraryLoader nativeLibraryLoader2) {
            this.applicationContext = context;
            this.fieldTrials = str;
            this.enableInternalTracer = z;
            this.enableVideoHwAcceleration = z2;
            this.nativeLibraryLoader = nativeLibraryLoader2;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        public static class Builder {
            private final Context applicationContext;
            private boolean enableInternalTracer = false;
            private boolean enableVideoHwAcceleration = true;
            private String fieldTrials = "";
            private NativeLibraryLoader nativeLibraryLoader = new NativeLibrary.DefaultLoader();

            Builder(Context context) {
                this.applicationContext = context;
            }

            public Builder setFieldTrials(String str) {
                this.fieldTrials = str;
                return this;
            }

            public Builder setEnableInternalTracer(boolean z) {
                this.enableInternalTracer = z;
                return this;
            }

            public Builder setEnableVideoHwAcceleration(boolean z) {
                this.enableVideoHwAcceleration = z;
                return this;
            }

            public Builder setNativeLibraryLoader(NativeLibraryLoader nativeLibraryLoader2) {
                this.nativeLibraryLoader = nativeLibraryLoader2;
                return this;
            }

            public InitializationOptions createInitializationOptions() {
                return new InitializationOptions(this.applicationContext, this.fieldTrials, this.enableInternalTracer, this.enableVideoHwAcceleration, this.nativeLibraryLoader);
            }
        }
    }

    public static class Options {
        static final int ADAPTER_TYPE_CELLULAR = 4;
        static final int ADAPTER_TYPE_ETHERNET = 1;
        static final int ADAPTER_TYPE_LOOPBACK = 16;
        static final int ADAPTER_TYPE_UNKNOWN = 0;
        static final int ADAPTER_TYPE_VPN = 8;
        static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public boolean enableAes128Sha1_32CryptoCipher;
        public int networkIgnoreMask;

        /* access modifiers changed from: package-private */
        public int getNetworkIgnoreMask() {
            return this.networkIgnoreMask;
        }

        /* access modifiers changed from: package-private */
        public boolean getDisableEncryption() {
            return this.disableEncryption;
        }

        /* access modifiers changed from: package-private */
        public boolean getDisableNetworkMonitor() {
            return this.disableNetworkMonitor;
        }

        /* access modifiers changed from: package-private */
        public boolean getEnableAes128Sha1_32CryptoCipher() {
            return this.enableAes128Sha1_32CryptoCipher;
        }
    }

    public static class Builder {
        @Nullable
        private AudioDeviceModule audioDeviceModule;
        @Nullable
        private AudioProcessingFactory audioProcessingFactory;
        @Nullable
        private VideoDecoderFactory decoderFactory;
        @Nullable
        private VideoEncoderFactory encoderFactory;
        @Nullable
        private FecControllerFactoryFactoryInterface fecControllerFactoryFactory;
        @Nullable
        private Options options;

        private Builder() {
            this.audioDeviceModule = new LegacyAudioDeviceModule();
        }

        public Builder setOptions(Options options2) {
            this.options = options2;
            return this;
        }

        public Builder setAudioDeviceModule(AudioDeviceModule audioDeviceModule2) {
            this.audioDeviceModule = audioDeviceModule2;
            return this;
        }

        public Builder setVideoEncoderFactory(VideoEncoderFactory videoEncoderFactory) {
            this.encoderFactory = videoEncoderFactory;
            return this;
        }

        public Builder setVideoDecoderFactory(VideoDecoderFactory videoDecoderFactory) {
            this.decoderFactory = videoDecoderFactory;
            return this;
        }

        public Builder setAudioProcessingFactory(AudioProcessingFactory audioProcessingFactory2) {
            Objects.requireNonNull(audioProcessingFactory2, "PeerConnectionFactory builder does not accept a null AudioProcessingFactory.");
            this.audioProcessingFactory = audioProcessingFactory2;
            return this;
        }

        public Builder setFecControllerFactoryFactoryInterface(FecControllerFactoryFactoryInterface fecControllerFactoryFactoryInterface) {
            this.fecControllerFactoryFactory = fecControllerFactoryFactoryInterface;
            return this;
        }

        public PeerConnectionFactory createPeerConnectionFactory() {
            return new PeerConnectionFactory(this.options, this.audioDeviceModule, this.encoderFactory, this.decoderFactory, this.audioProcessingFactory, this.fecControllerFactoryFactory);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void initialize(InitializationOptions initializationOptions) {
        ContextUtils.initialize(initializationOptions.applicationContext);
        NativeLibrary.initialize(initializationOptions.nativeLibraryLoader);
        nativeInitializeAndroidGlobals(initializationOptions.enableVideoHwAcceleration);
        initializeFieldTrials(initializationOptions.fieldTrials);
        if (initializationOptions.enableInternalTracer && !internalTracerInitialized) {
            initializeInternalTracer();
        }
    }

    private void checkInitializeHasBeenCalled() {
        if (!NativeLibrary.isLoaded() || ContextUtils.getApplicationContext() == null) {
            throw new IllegalStateException("PeerConnectionFactory.initialize was not called before creating a PeerConnectionFactory.");
        }
    }

    private static void initializeInternalTracer() {
        internalTracerInitialized = true;
        nativeInitializeInternalTracer();
    }

    public static void shutdownInternalTracer() {
        internalTracerInitialized = false;
        nativeShutdownInternalTracer();
    }

    @Deprecated
    public static void initializeFieldTrials(String str) {
        nativeInitializeFieldTrials(str);
    }

    public static String fieldTrialsFindFullName(String str) {
        return NativeLibrary.isLoaded() ? nativeFindFieldTrialsFullName(str) : "";
    }

    public static boolean startInternalTracingCapture(String str) {
        return nativeStartInternalTracingCapture(str);
    }

    public static void stopInternalTracingCapture() {
        nativeStopInternalTracingCapture();
    }

    @Deprecated
    public PeerConnectionFactory() {
        this((Options) null);
    }

    @Deprecated
    public PeerConnectionFactory(Options options) {
        this(options, (VideoEncoderFactory) null, (VideoDecoderFactory) null);
    }

    @Deprecated
    public PeerConnectionFactory(Options options, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory) {
        checkInitializeHasBeenCalled();
        long nativeCreatePeerConnectionFactory = nativeCreatePeerConnectionFactory(ContextUtils.getApplicationContext(), options, 0, videoEncoderFactory, videoDecoderFactory, 0, 0);
        this.nativeFactory = nativeCreatePeerConnectionFactory;
        if (nativeCreatePeerConnectionFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    @Deprecated
    public PeerConnectionFactory(Options options, VideoEncoderFactory videoEncoderFactory, VideoDecoderFactory videoDecoderFactory, AudioProcessingFactory audioProcessingFactory) {
        this(options, new LegacyAudioDeviceModule(), videoEncoderFactory, videoDecoderFactory, audioProcessingFactory, (FecControllerFactoryFactoryInterface) null);
    }

    private PeerConnectionFactory(Options options, @Nullable AudioDeviceModule audioDeviceModule, @Nullable VideoEncoderFactory videoEncoderFactory, @Nullable VideoDecoderFactory videoDecoderFactory, @Nullable AudioProcessingFactory audioProcessingFactory, @Nullable FecControllerFactoryFactoryInterface fecControllerFactoryFactoryInterface) {
        long j;
        long j2;
        long j3;
        checkInitializeHasBeenCalled();
        Context applicationContext = ContextUtils.getApplicationContext();
        if (audioDeviceModule == null) {
            j = 0;
        } else {
            j = audioDeviceModule.getNativeAudioDeviceModulePointer();
        }
        if (audioProcessingFactory == null) {
            j2 = 0;
        } else {
            j2 = audioProcessingFactory.createNative();
        }
        if (fecControllerFactoryFactoryInterface == null) {
            j3 = 0;
        } else {
            j3 = fecControllerFactoryFactoryInterface.createNative();
        }
        long nativeCreatePeerConnectionFactory = nativeCreatePeerConnectionFactory(applicationContext, options, j, videoEncoderFactory, videoDecoderFactory, j2, j3);
        this.nativeFactory = nativeCreatePeerConnectionFactory;
        if (nativeCreatePeerConnectionFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    PeerConnectionFactory(long j) {
        checkInitializeHasBeenCalled();
        if (j != 0) {
            this.nativeFactory = j;
            return;
        }
        throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
    }

    @Deprecated
    @Nullable
    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        long createNativePeerConnectionObserver = PeerConnection.createNativePeerConnectionObserver(observer);
        if (createNativePeerConnectionObserver == 0) {
            return null;
        }
        long nativeCreatePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rTCConfiguration, mediaConstraints, createNativePeerConnectionObserver);
        if (nativeCreatePeerConnection == 0) {
            return null;
        }
        return new PeerConnection(nativeCreatePeerConnection);
    }

    @Deprecated
    @Nullable
    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), mediaConstraints, observer);
    }

    @Nullable
    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), observer);
    }

    @Nullable
    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, PeerConnection.Observer observer) {
        return createPeerConnection(rTCConfiguration, (MediaConstraints) null, observer);
    }

    public MediaStream createLocalMediaStream(String str) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, str));
    }

    public VideoSource createVideoSource(VideoCapturer videoCapturer) {
        EglBase eglBase = this.localEglbase;
        SurfaceTextureHelper create = SurfaceTextureHelper.create(VIDEO_CAPTURER_THREAD_NAME, eglBase == null ? null : eglBase.getEglBaseContext());
        long nativeCreateVideoSource = nativeCreateVideoSource(this.nativeFactory, create, videoCapturer.isScreencast());
        videoCapturer.initialize(create, ContextUtils.getApplicationContext(), new AndroidVideoTrackSourceObserver(nativeCreateVideoSource));
        return new VideoSource(nativeCreateVideoSource);
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, str, videoSource.nativeSource));
    }

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, mediaConstraints));
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, str, audioSource.nativeSource));
    }

    public boolean startAecDump(int i, int i2) {
        return nativeStartAecDump(this.nativeFactory, i, i2);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    @Deprecated
    public void setOptions(Options options) {
        nativeSetOptions(this.nativeFactory, options);
    }

    public void setVideoHwAccelerationOptions(EglBase.Context context, EglBase.Context context2) {
        if (this.localEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.remoteEglbase.release();
        }
        this.localEglbase = EglBase.CC.create(context);
        this.remoteEglbase = EglBase.CC.create(context2);
        nativeSetVideoHwAccelerationOptions(this.nativeFactory, this.localEglbase.getEglBaseContext(), this.remoteEglbase.getEglBaseContext());
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        networkThread = null;
        workerThread = null;
        signalingThread = null;
        EglBase eglBase = this.localEglbase;
        if (eglBase != null) {
            eglBase.release();
        }
        EglBase eglBase2 = this.remoteEglbase;
        if (eglBase2 != null) {
            eglBase2.release();
        }
    }

    public void threadsCallbacks() {
        nativeInvokeThreadsCallbacks(this.nativeFactory);
    }

    public long getNativePeerConnectionFactory() {
        return nativeGetNativePeerConnectionFactory(this.nativeFactory);
    }

    public long getNativeOwnedFactoryAndThreads() {
        return this.nativeFactory;
    }

    private static void printStackTrace(@Nullable Thread thread, String str) {
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.d(TAG, str + " stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(networkThread, "Network thread");
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    private static void onNetworkThreadReady() {
        networkThread = Thread.currentThread();
        Logging.d(TAG, "onNetworkThreadReady");
    }

    private static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.d(TAG, "onWorkerThreadReady");
    }

    private static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.d(TAG, "onSignalingThreadReady");
    }
}
