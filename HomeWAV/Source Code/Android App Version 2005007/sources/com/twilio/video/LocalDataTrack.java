package com.twilio.video;

import android.content.Context;
import java.nio.ByteBuffer;

public class LocalDataTrack extends DataTrack {
    private static final Logger logger = Logger.getLogger(LocalDataTrack.class);
    private final MediaFactory mediaFactory;
    private long nativeLocalDataTrackHandle;
    private final String nativeTrackHash;

    private native void nativeBufferSend(long j, byte[] bArr);

    private native void nativeRelease(long j);

    private native void nativeStringSend(long j, String str);

    public static LocalDataTrack create(Context context) {
        return create(context, DataTrackOptions.DEFAULT_DATA_TRACK_OPTIONS);
    }

    public static LocalDataTrack create(Context context, DataTrackOptions dataTrackOptions) {
        Preconditions.checkNotNull(context, "Context must not be null");
        if (dataTrackOptions == null) {
            dataTrackOptions = DataTrackOptions.DEFAULT_DATA_TRACK_OPTIONS;
        }
        Object obj = new Object();
        MediaFactory instance = MediaFactory.instance(obj, context);
        LocalDataTrack createDataTrack = instance.createDataTrack(context, dataTrackOptions.ordered, dataTrackOptions.maxPacketLifeTime, dataTrackOptions.maxRetransmits, dataTrackOptions.name);
        instance.release(obj);
        return createDataTrack;
    }

    public synchronized void send(ByteBuffer byteBuffer) {
        byte[] bArr;
        Preconditions.checkState(!isReleased(), "Cannot send message after data track is released");
        Preconditions.checkNotNull(byteBuffer, "Message buffer must not be null");
        long j = this.nativeLocalDataTrackHandle;
        if (byteBuffer.hasArray()) {
            bArr = byteBuffer.array();
        } else {
            bArr = getMessageByteArray(byteBuffer);
        }
        nativeBufferSend(j, bArr);
    }

    public synchronized void send(String str) {
        Preconditions.checkState(!isReleased(), "Cannot send message after data track is released");
        Preconditions.checkNotNull(str, "Message buffer must not be null");
        nativeStringSend(this.nativeLocalDataTrackHandle, str);
    }

    public synchronized boolean isEnabled() {
        if (!isReleased()) {
            return super.isEnabled();
        }
        logger.e("Local data track is not enabled because it has been released");
        return false;
    }

    public String getName() {
        return super.getName();
    }

    public synchronized void release() {
        if (!isReleased()) {
            nativeRelease(this.nativeLocalDataTrackHandle);
            this.nativeLocalDataTrackHandle = 0;
            this.mediaFactory.release(this);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LocalDataTrack(long j, boolean z, boolean z2, boolean z3, int i, int i2, String str, String str2, Context context) {
        super(z, z2, z3, i, i2, str2);
        this.nativeLocalDataTrackHandle = j;
        this.nativeTrackHash = str;
        this.mediaFactory = MediaFactory.instance(this, context);
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeLocalDataTrackHandle == 0;
    }

    /* access modifiers changed from: package-private */
    public String getNativeTrackHash() {
        return this.nativeTrackHash;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNativeHandle() {
        return this.nativeLocalDataTrackHandle;
    }

    private byte[] getMessageByteArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.capacity()];
        byteBuffer.get(bArr);
        return bArr;
    }
}
