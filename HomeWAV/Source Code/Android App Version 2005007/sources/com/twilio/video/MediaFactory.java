package com.twilio.video;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import java.util.HashSet;
import java.util.Set;
import tvi.webrtc.EglBase;

class MediaFactory {
    private static final String RELEASE_MESSAGE_TEMPLATE = "MediaFactory released %s unavailable";
    private static volatile MediaFactory instance = null;
    private static volatile boolean libraryIsLoaded = false;
    private static final Logger logger = Logger.getLogger(MediaFactory.class);
    private static volatile Set<Object> mediaFactoryOwners = new HashSet();
    private EglBaseProvider eglBaseProvider = EglBaseProvider.instance(this);
    private long nativeMediaFactoryHandle;

    private static native long nativeCreate(Context context, EglBase.Context context2, EglBase.Context context3);

    private native LocalAudioTrack nativeCreateAudioTrack(long j, Context context, boolean z, AudioOptions audioOptions, String str);

    private native LocalDataTrack nativeCreateDataTrack(long j, Context context, boolean z, int i, int i2, String str);

    private native LocalVideoTrack nativeCreateVideoTrack(long j, Context context, boolean z, VideoCapturer videoCapturer, VideoConstraints videoConstraints, String str, EglBase.Context context2);

    private native void nativeRelease(long j);

    private static native long nativeTestCreate(Context context, MediaOptions mediaOptions);

    private native void nativeTestRelease(long j);

    static MediaFactory instance(Object obj, Context context) {
        Preconditions.checkNotNull(obj, "Owner must not be null");
        Preconditions.checkNotNull(context, "Context must not be null");
        synchronized (MediaFactory.class) {
            if (instance == null) {
                if (!libraryIsLoaded) {
                    ReLinker.loadLibrary(context, BuildConfig.TWILIO_VIDEO_ANDROID_LIBRARY);
                    libraryIsLoaded = true;
                }
                Object obj2 = new Object();
                EglBaseProvider instance2 = EglBaseProvider.instance(obj2);
                long nativeCreate = nativeCreate(context, instance2.getLocalEglBase().getEglBaseContext(), instance2.getRemoteEglBase().getEglBaseContext());
                if (nativeCreate == 0) {
                    logger.e("Failed to instance MediaFactory");
                } else {
                    instance = new MediaFactory(nativeCreate);
                }
                instance2.release(obj2);
            }
            mediaFactoryOwners.add(obj);
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    public synchronized LocalAudioTrack createAudioTrack(Context context, boolean z, AudioOptions audioOptions, String str) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkState(this.nativeMediaFactoryHandle != 0, RELEASE_MESSAGE_TEMPLATE, (Object) "createAudioTrack");
        return nativeCreateAudioTrack(this.nativeMediaFactoryHandle, context, z, audioOptions, str);
    }

    /* access modifiers changed from: package-private */
    public synchronized LocalVideoTrack createVideoTrack(Context context, boolean z, VideoCapturer videoCapturer, VideoConstraints videoConstraints, String str) {
        LocalVideoTrack nativeCreateVideoTrack;
        synchronized (this) {
            Context context2 = context;
            Preconditions.checkNotNull(context, "Context must not be null");
            Preconditions.checkState(this.nativeMediaFactoryHandle != 0, RELEASE_MESSAGE_TEMPLATE, (Object) "createVideoTrack");
            nativeCreateVideoTrack = nativeCreateVideoTrack(this.nativeMediaFactoryHandle, context, z, videoCapturer, videoConstraints, str, this.eglBaseProvider.getLocalEglBase().getEglBaseContext());
        }
        return nativeCreateVideoTrack;
    }

    /* access modifiers changed from: package-private */
    public synchronized LocalDataTrack createDataTrack(Context context, boolean z, int i, int i2, String str) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkState(this.nativeMediaFactoryHandle != 0, RELEASE_MESSAGE_TEMPLATE, (Object) "createDataTrack");
        return nativeCreateDataTrack(this.nativeMediaFactoryHandle, context, z, i, i2, str);
    }

    /* access modifiers changed from: package-private */
    public void release(Object obj) {
        if (instance != null) {
            synchronized (MediaFactory.class) {
                mediaFactoryOwners.remove(obj);
                if (instance != null && mediaFactoryOwners.isEmpty()) {
                    this.eglBaseProvider.release(this);
                    this.eglBaseProvider = null;
                    nativeRelease(this.nativeMediaFactoryHandle);
                    this.nativeMediaFactoryHandle = 0;
                    instance = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public long getNativeMediaFactoryHandle() {
        return this.nativeMediaFactoryHandle;
    }

    static MediaFactory testCreate(Context context, MediaOptions mediaOptions) {
        MediaFactory mediaFactory;
        synchronized (MediaFactory.class) {
            if (!libraryIsLoaded) {
                ReLinker.loadLibrary(context, BuildConfig.TWILIO_VIDEO_ANDROID_LIBRARY);
                libraryIsLoaded = true;
            }
            mediaFactory = new MediaFactory(nativeTestCreate(context, mediaOptions));
        }
        return mediaFactory;
    }

    /* access modifiers changed from: package-private */
    public void testRelease() {
        if (this.nativeMediaFactoryHandle != 0) {
            this.eglBaseProvider.release(this);
            this.eglBaseProvider = null;
            nativeTestRelease(this.nativeMediaFactoryHandle);
            this.nativeMediaFactoryHandle = 0;
        }
    }

    static boolean isReleased() {
        boolean z;
        synchronized (MediaFactory.class) {
            z = instance == null;
        }
        return z;
    }

    static void manualRelease() {
        synchronized (MediaFactory.class) {
            if (instance != null) {
                mediaFactoryOwners.clear();
                instance.release(new Object());
            }
        }
    }

    private MediaFactory(long j) {
        this.nativeMediaFactoryHandle = j;
    }
}
