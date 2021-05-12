package com.twilio.video;

import android.content.Context;

public class LocalAudioTrack extends AudioTrack {
    private static final Logger logger = Logger.getLogger(LocalAudioTrack.class);
    private final MediaFactory mediaFactory;
    private long nativeLocalAudioTrackHandle;
    private final String nativeTrackHash;

    private native void nativeEnable(long j, boolean z);

    private native boolean nativeIsEnabled(long j);

    private native void nativeRelease(long j);

    public static LocalAudioTrack create(Context context, boolean z) {
        return create(context, z, (AudioOptions) null, (String) null);
    }

    public static LocalAudioTrack create(Context context, boolean z, AudioOptions audioOptions) {
        return create(context, z, audioOptions, (String) null);
    }

    public static LocalAudioTrack create(Context context, boolean z, String str) {
        return create(context, z, (AudioOptions) null, str);
    }

    public static LocalAudioTrack create(Context context, boolean z, AudioOptions audioOptions, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkState(Util.permissionGranted(context, "android.permission.RECORD_AUDIO"), "RECORD_AUDIO permission must be granted to create audio track");
        Object obj = new Object();
        MediaFactory instance = MediaFactory.instance(obj, context);
        LocalAudioTrack createAudioTrack = instance.createAudioTrack(context, z, audioOptions, str);
        if (createAudioTrack == null) {
            logger.e("Failed to create local audio track");
        }
        instance.release(obj);
        return createAudioTrack;
    }

    public synchronized boolean isEnabled() {
        if (!isReleased()) {
            return nativeIsEnabled(this.nativeLocalAudioTrackHandle);
        }
        logger.w("Local audio track is not enabled because it has been released");
        return false;
    }

    public String getName() {
        return super.getName();
    }

    public synchronized void enable(boolean z) {
        if (!isReleased()) {
            nativeEnable(this.nativeLocalAudioTrackHandle, z);
        } else {
            logger.e("Cannot enable a local audio track that has been removed");
        }
    }

    public synchronized void release() {
        if (!isReleased()) {
            nativeRelease(this.nativeLocalAudioTrackHandle);
            this.nativeLocalAudioTrackHandle = 0;
            this.mediaFactory.release(this);
        }
    }

    LocalAudioTrack(long j, String str, String str2, boolean z, Context context) {
        super(j, z, str2);
        this.nativeTrackHash = str;
        this.nativeLocalAudioTrackHandle = j;
        this.mediaFactory = MediaFactory.instance(this, context);
    }

    public synchronized void addSink(AudioSink audioSink) {
        Preconditions.checkState(!isReleased(), "Cannot add AudioSink to audio track that has been released");
        super.addSink(audioSink);
    }

    public synchronized void removeSink(AudioSink audioSink) {
        Preconditions.checkState(!isReleased(), "Cannot remove AudioSink from audio track that has been released");
        super.removeSink(audioSink);
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeLocalAudioTrackHandle == 0;
    }

    /* access modifiers changed from: package-private */
    public String getNativeTrackHash() {
        return this.nativeTrackHash;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNativeHandle() {
        return this.nativeLocalAudioTrackHandle;
    }
}
