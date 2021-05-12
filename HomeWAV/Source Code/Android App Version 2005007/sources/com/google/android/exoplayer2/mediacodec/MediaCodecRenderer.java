package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "MediaCodecRenderer";
    private final DecoderInputBuffer buffer;
    private MediaCodec codec;
    private int codecAdaptationWorkaroundMode;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private DrmSession<FrameworkMediaCrypto> drmSession;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final MediaCodecSelector mediaCodecSelector;
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private DrmSession<FrameworkMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean shouldSkipOutputBuffer;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    /* access modifiers changed from: protected */
    public boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, Format format2, Format format3) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format2, MediaCrypto mediaCrypto) throws MediaCodecUtil.DecoderQueryException;

    /* access modifiers changed from: protected */
    public long getDequeueOutputBufferTimeoutUs() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException;

    /* access modifiers changed from: protected */
    public void renderToEndOfStream() throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, Format format2) throws MediaCodecUtil.DecoderQueryException;

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z, int i) {
            super("Decoder init failed: [" + i + "], " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i);
        }

        public DecoderInitializationException(Format format, Throwable th, boolean z, String str) {
            super("Decoder init failed: " + str + ", " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }

        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int i) {
            String str = i < 0 ? "neg_" : "";
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + str + Math.abs(i);
        }
    }

    public MediaCodecRenderer(int i, MediaCodecSelector mediaCodecSelector2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, boolean z) {
        super(i);
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector2);
        this.drmSessionManager = drmSessionManager2;
        this.playClearSamplesWithoutKeys = z;
        this.buffer = new DecoderInputBuffer(0);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
    }

    public final int supportsFormat(Format format2) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, this.drmSessionManager, format2);
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector2, Format format2, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return mediaCodecSelector2.getDecoderInfo(format2.sampleMimeType, z);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0037 A[SYNTHETIC, Splitter:B:15:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void maybeInitCodec() throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r11 = this;
            android.media.MediaCodec r0 = r11.codec
            if (r0 != 0) goto L_0x0152
            com.google.android.exoplayer2.Format r0 = r11.format
            if (r0 != 0) goto L_0x000a
            goto L_0x0152
        L_0x000a:
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.pendingDrmSession
            r11.drmSession = r1
            java.lang.String r0 = r0.sampleMimeType
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.drmSession
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x0031
            com.google.android.exoplayer2.drm.ExoMediaCrypto r1 = r1.getMediaCrypto()
            com.google.android.exoplayer2.drm.FrameworkMediaCrypto r1 = (com.google.android.exoplayer2.drm.FrameworkMediaCrypto) r1
            if (r1 != 0) goto L_0x0028
            com.google.android.exoplayer2.drm.DrmSession<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r1 = r11.drmSession
            com.google.android.exoplayer2.drm.DrmSession$DrmSessionException r1 = r1.getError()
            if (r1 == 0) goto L_0x0027
            goto L_0x0031
        L_0x0027:
            return
        L_0x0028:
            android.media.MediaCrypto r4 = r1.getWrappedMediaCrypto()
            boolean r1 = r1.requiresSecureDecoderComponent(r0)
            goto L_0x0033
        L_0x0031:
            r4 = r3
            r1 = 0
        L_0x0033:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.codecInfo
            if (r5 != 0) goto L_0x0098
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r5 = r11.mediaCodecSelector     // Catch:{ DecoderQueryException -> 0x0079 }
            com.google.android.exoplayer2.Format r6 = r11.format     // Catch:{ DecoderQueryException -> 0x0079 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.getDecoderInfo(r5, r6, r1)     // Catch:{ DecoderQueryException -> 0x0079 }
            r11.codecInfo = r5     // Catch:{ DecoderQueryException -> 0x0079 }
            if (r5 != 0) goto L_0x0087
            if (r1 == 0) goto L_0x0087
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r5 = r11.mediaCodecSelector     // Catch:{ DecoderQueryException -> 0x0079 }
            com.google.android.exoplayer2.Format r6 = r11.format     // Catch:{ DecoderQueryException -> 0x0079 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = r11.getDecoderInfo(r5, r6, r2)     // Catch:{ DecoderQueryException -> 0x0079 }
            r11.codecInfo = r2     // Catch:{ DecoderQueryException -> 0x0079 }
            if (r2 == 0) goto L_0x0087
            java.lang.String r2 = "MediaCodecRenderer"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ DecoderQueryException -> 0x0079 }
            r5.<init>()     // Catch:{ DecoderQueryException -> 0x0079 }
            java.lang.String r6 = "Drm session requires secure decoder for "
            r5.append(r6)     // Catch:{ DecoderQueryException -> 0x0079 }
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0079 }
            java.lang.String r0 = ", but no secure decoder available. Trying to proceed with "
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0079 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo     // Catch:{ DecoderQueryException -> 0x0079 }
            java.lang.String r0 = r0.name     // Catch:{ DecoderQueryException -> 0x0079 }
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0079 }
            java.lang.String r0 = "."
            r5.append(r0)     // Catch:{ DecoderQueryException -> 0x0079 }
            java.lang.String r0 = r5.toString()     // Catch:{ DecoderQueryException -> 0x0079 }
            android.util.Log.w(r2, r0)     // Catch:{ DecoderQueryException -> 0x0079 }
            goto L_0x0087
        L_0x0079:
            r0 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r2 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r5 = r11.format
            r6 = -49998(0xffffffffffff3cb2, float:NaN)
            r2.<init>((com.google.android.exoplayer2.Format) r5, (java.lang.Throwable) r0, (boolean) r1, (int) r6)
            r11.throwDecoderInitError(r2)
        L_0x0087:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            if (r0 != 0) goto L_0x0098
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r2 = r11.format
            r5 = -49999(0xffffffffffff3cb1, float:NaN)
            r0.<init>((com.google.android.exoplayer2.Format) r2, (java.lang.Throwable) r3, (boolean) r1, (int) r5)
            r11.throwDecoderInitError(r0)
        L_0x0098:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            boolean r0 = r11.shouldInitCodec(r0)
            if (r0 != 0) goto L_0x00a1
            return
        L_0x00a1:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r11.codecInfo
            java.lang.String r0 = r0.name
            int r2 = r11.codecAdaptationWorkaroundMode(r0)
            r11.codecAdaptationWorkaroundMode = r2
            com.google.android.exoplayer2.Format r2 = r11.format
            boolean r2 = codecNeedsDiscardToSpsWorkaround(r0, r2)
            r11.codecNeedsDiscardToSpsWorkaround = r2
            boolean r2 = codecNeedsFlushWorkaround(r0)
            r11.codecNeedsFlushWorkaround = r2
            boolean r2 = codecNeedsEosPropagationWorkaround(r0)
            r11.codecNeedsEosPropagationWorkaround = r2
            boolean r2 = codecNeedsEosFlushWorkaround(r0)
            r11.codecNeedsEosFlushWorkaround = r2
            boolean r2 = codecNeedsEosOutputExceptionWorkaround(r0)
            r11.codecNeedsEosOutputExceptionWorkaround = r2
            com.google.android.exoplayer2.Format r2 = r11.format
            boolean r2 = codecNeedsMonoChannelCountWorkaround(r0, r2)
            r11.codecNeedsMonoChannelCountWorkaround = r2
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x0121 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0121 }
            r5.<init>()     // Catch:{ Exception -> 0x0121 }
            java.lang.String r6 = "createCodec:"
            r5.append(r6)     // Catch:{ Exception -> 0x0121 }
            r5.append(r0)     // Catch:{ Exception -> 0x0121 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r5)     // Catch:{ Exception -> 0x0121 }
            android.media.MediaCodec r5 = android.media.MediaCodec.createByCodecName(r0)     // Catch:{ Exception -> 0x0121 }
            r11.codec = r5     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x0121 }
            java.lang.String r5 = "configureCodec"
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r5)     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5 = r11.codecInfo     // Catch:{ Exception -> 0x0121 }
            android.media.MediaCodec r6 = r11.codec     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.Format r7 = r11.format     // Catch:{ Exception -> 0x0121 }
            r11.configureCodec(r5, r6, r7, r4)     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x0121 }
            java.lang.String r4 = "startCodec"
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r4)     // Catch:{ Exception -> 0x0121 }
            android.media.MediaCodec r4 = r11.codec     // Catch:{ Exception -> 0x0121 }
            r4.start()     // Catch:{ Exception -> 0x0121 }
            com.google.android.exoplayer2.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x0121 }
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x0121 }
            long r9 = r7 - r2
            r5 = r11
            r6 = r0
            r5.onCodecInitialized(r6, r7, r9)     // Catch:{ Exception -> 0x0121 }
            r11.getCodecBuffers()     // Catch:{ Exception -> 0x0121 }
            goto L_0x012c
        L_0x0121:
            r2 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r3 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r4 = r11.format
            r3.<init>((com.google.android.exoplayer2.Format) r4, (java.lang.Throwable) r2, (boolean) r1, (java.lang.String) r0)
            r11.throwDecoderInitError(r3)
        L_0x012c:
            int r0 = r11.getState()
            r1 = 2
            if (r0 != r1) goto L_0x013b
            long r0 = android.os.SystemClock.elapsedRealtime()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 + r2
            goto L_0x0140
        L_0x013b:
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0140:
            r11.codecHotswapDeadlineMs = r0
            r11.resetInputBuffer()
            r11.resetOutputBuffer()
            r0 = 1
            r11.waitingForFirstSyncFrame = r0
            com.google.android.exoplayer2.decoder.DecoderCounters r1 = r11.decoderCounters
            int r2 = r1.decoderInitCount
            int r2 = r2 + r0
            r1.decoderInitCount = r2
        L_0x0152:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.maybeInitCodec():void");
    }

    private void throwDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        throw ExoPlaybackException.createForRenderer(decoderInitializationException, getIndex());
    }

    /* access modifiers changed from: protected */
    public final MediaCodec getCodec() {
        return this.codec;
    }

    /* access modifiers changed from: protected */
    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    /* access modifiers changed from: protected */
    public final MediaFormat getMediaFormatForPlayback(Format format2) {
        MediaFormat frameworkMediaFormatV16 = format2.getFrameworkMediaFormatV16();
        if (Util.SDK_INT >= 23) {
            configureMediaFormatForPlaybackV23(frameworkMediaFormatV16);
        }
        return frameworkMediaFormatV16;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.codec != null) {
            flushCodec();
        }
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.format = null;
        try {
            releaseCodec();
            try {
                DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
                if (drmSession2 != null) {
                    this.drmSessionManager.releaseSession(drmSession2);
                }
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.pendingDrmSession;
                    if (!(drmSession3 == null || drmSession3 == this.drmSession)) {
                        this.drmSessionManager.releaseSession(drmSession3);
                    }
                } finally {
                    this.drmSession = null;
                    this.pendingDrmSession = null;
                }
            } catch (Throwable th) {
                DrmSession<FrameworkMediaCrypto> drmSession4 = this.pendingDrmSession;
                if (!(drmSession4 == null || drmSession4 == this.drmSession)) {
                    this.drmSessionManager.releaseSession(drmSession4);
                }
                throw th;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
            }
        } catch (Throwable th2) {
            try {
                DrmSession<FrameworkMediaCrypto> drmSession5 = this.pendingDrmSession;
                if (!(drmSession5 == null || drmSession5 == this.drmSession)) {
                    this.drmSessionManager.releaseSession(drmSession5);
                }
                throw th2;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
            }
        } finally {
        }
    }

    /* access modifiers changed from: protected */
    public void releaseCodec() {
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        resetCodecBuffers();
        this.codecInfo = null;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        if (this.codec != null) {
            this.decoderCounters.decoderReleaseCount++;
            try {
                this.codec.stop();
                try {
                    this.codec.release();
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
                    if (drmSession2 != null && this.pendingDrmSession != drmSession2) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession2);
                        } finally {
                            this.drmSession = null;
                        }
                    }
                } catch (Throwable th) {
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.drmSession;
                    if (!(drmSession3 == null || this.pendingDrmSession == drmSession3)) {
                        this.drmSessionManager.releaseSession(drmSession3);
                    }
                    throw th;
                } finally {
                    this.drmSession = null;
                }
            } catch (Throwable th2) {
                this.codec = null;
                DrmSession<FrameworkMediaCrypto> drmSession4 = this.drmSession;
                if (!(drmSession4 == null || this.pendingDrmSession == drmSession4)) {
                    try {
                        this.drmSessionManager.releaseSession(drmSession4);
                    } finally {
                        this.drmSession = null;
                    }
                }
                throw th2;
            } finally {
            }
        }
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            renderToEndOfStream();
            return;
        }
        if (this.format == null) {
            this.flagsOnlyBuffer.clear();
            int readSource = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if (readSource == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (readSource == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
                return;
            } else {
                return;
            }
        }
        maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            do {
            } while (drainOutputBuffer(j, j2));
            do {
            } while (feedInputBuffer());
            TraceUtil.endSection();
        } else {
            this.decoderCounters.skippedInputBufferCount += skipSource(j);
            this.flagsOnlyBuffer.clear();
            int readSource2 = readSource(this.formatHolder, this.flagsOnlyBuffer, false);
            if (readSource2 == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (readSource2 == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
            }
        }
        this.decoderCounters.ensureUpdated();
    }

    /* access modifiers changed from: protected */
    public void flushCodec() throws ExoPlaybackException {
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos)) {
            releaseCodec();
            maybeInitCodec();
        } else if (this.codecReinitializationState != 0) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        if (this.codecReconfigured && this.format != null) {
            this.codecReconfigurationState = 1;
        }
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        int i;
        int i2;
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec == null || this.codecReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputIndex < 0) {
            int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(0);
            this.inputIndex = dequeueInputBuffer;
            if (dequeueInputBuffer < 0) {
                return false;
            }
            this.buffer.data = getInputBuffer(dequeueInputBuffer);
            this.buffer.clear();
        }
        if (this.codecReinitializationState == 1) {
            if (!this.codecNeedsEosPropagationWorkaround) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                resetInputBuffer();
            }
            this.codecReinitializationState = 2;
            return false;
        } else if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            ByteBuffer byteBuffer = this.buffer.data;
            byte[] bArr = ADAPTATION_WORKAROUND_BUFFER;
            byteBuffer.put(bArr);
            this.codec.queueInputBuffer(this.inputIndex, 0, bArr.length, 0, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        } else {
            if (this.waitingForKeys) {
                i2 = -4;
                i = 0;
            } else {
                if (this.codecReconfigurationState == 1) {
                    for (int i3 = 0; i3 < this.format.initializationData.size(); i3++) {
                        this.buffer.data.put(this.format.initializationData.get(i3));
                    }
                    this.codecReconfigurationState = 2;
                }
                i = this.buffer.data.position();
                i2 = readSource(this.formatHolder, this.buffer, false);
            }
            if (i2 == -3) {
                return false;
            }
            if (i2 == -5) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                onInputFormatChanged(this.formatHolder.format);
                return true;
            } else if (this.buffer.isEndOfStream()) {
                if (this.codecReconfigurationState == 2) {
                    this.buffer.clear();
                    this.codecReconfigurationState = 1;
                }
                this.inputStreamEnded = true;
                if (!this.codecReceivedBuffers) {
                    processEndOfStream();
                    return false;
                }
                try {
                    if (!this.codecNeedsEosPropagationWorkaround) {
                        this.codecReceivedEos = true;
                        this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                        resetInputBuffer();
                    }
                    return false;
                } catch (MediaCodec.CryptoException e) {
                    throw ExoPlaybackException.createForRenderer(e, getIndex());
                }
            } else if (!this.waitingForFirstSyncFrame || this.buffer.isKeyFrame()) {
                this.waitingForFirstSyncFrame = false;
                boolean isEncrypted = this.buffer.isEncrypted();
                boolean shouldWaitForKeys = shouldWaitForKeys(isEncrypted);
                this.waitingForKeys = shouldWaitForKeys;
                if (shouldWaitForKeys) {
                    return false;
                }
                if (this.codecNeedsDiscardToSpsWorkaround && !isEncrypted) {
                    NalUnitUtil.discardToSps(this.buffer.data);
                    if (this.buffer.data.position() == 0) {
                        return true;
                    }
                    this.codecNeedsDiscardToSpsWorkaround = false;
                }
                try {
                    long j = this.buffer.timeUs;
                    if (this.buffer.isDecodeOnly()) {
                        this.decodeOnlyPresentationTimestamps.add(Long.valueOf(j));
                    }
                    this.buffer.flip();
                    onQueueInputBuffer(this.buffer);
                    if (isEncrypted) {
                        this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.buffer, i), j, 0);
                    } else {
                        this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), j, 0);
                    }
                    resetInputBuffer();
                    this.codecReceivedBuffers = true;
                    this.codecReconfigurationState = 0;
                    this.decoderCounters.inputBufferCount++;
                    return true;
                } catch (MediaCodec.CryptoException e2) {
                    throw ExoPlaybackException.createForRenderer(e2, getIndex());
                }
            } else {
                this.buffer.clear();
                if (this.codecReconfigurationState == 2) {
                    this.codecReconfigurationState = 1;
                }
                return true;
            }
        }
    }

    private void getCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = this.codec.getInputBuffers();
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void resetCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = null;
            this.outputBuffers = null;
        }
    }

    private ByteBuffer getInputBuffer(int i) {
        if (Util.SDK_INT >= 21) {
            return this.codec.getInputBuffer(i);
        }
        return this.inputBuffers[i];
    }

    private ByteBuffer getOutputBuffer(int i) {
        if (Util.SDK_INT >= 21) {
            return this.codec.getOutputBuffer(i);
        }
        return this.outputBuffers[i];
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private static MediaCodec.CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer decoderInputBuffer, int i) {
        MediaCodec.CryptoInfo frameworkCryptoInfoV16 = decoderInputBuffer.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return frameworkCryptoInfoV16;
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
        if (drmSession2 == null || (!z && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = drmSession2.getState();
        if (state == 1) {
            throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
        } else if (state != 4) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(Format format2) throws ExoPlaybackException {
        MediaCodec mediaCodec;
        Format format3 = this.format;
        this.format = format2;
        boolean z = true;
        if (!Util.areEqual(format2.drmInitData, format3 == null ? null : format3.drmInitData)) {
            if (this.format.drmInitData != null) {
                DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2 = this.drmSessionManager;
                if (drmSessionManager2 != null) {
                    DrmSession<FrameworkMediaCrypto> acquireSession = drmSessionManager2.acquireSession(Looper.myLooper(), this.format.drmInitData);
                    this.pendingDrmSession = acquireSession;
                    if (acquireSession == this.drmSession) {
                        this.drmSessionManager.releaseSession(acquireSession);
                    }
                } else {
                    throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
            } else {
                this.pendingDrmSession = null;
            }
        }
        if (this.pendingDrmSession == this.drmSession && (mediaCodec = this.codec) != null && canReconfigureCodec(mediaCodec, this.codecInfo.adaptive, format3, this.format)) {
            this.codecReconfigured = true;
            this.codecReconfigurationState = 1;
            int i = this.codecAdaptationWorkaroundMode;
            if (!(i == 2 || (i == 1 && this.format.width == format3.width && this.format.height == format3.height))) {
                z = false;
            }
            this.codecNeedsAdaptationWorkaroundBuffer = z;
        } else if (this.codecReceivedBuffers) {
            this.codecReinitializationState = 1;
        } else {
            releaseCodec();
            maybeInitCodec();
        }
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        return this.format != null && !this.waitingForKeys && (isSourceReady() || hasOutputBuffer() || (this.codecHotswapDeadlineMs != C.TIME_UNSET && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs));
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException {
        boolean z;
        int i;
        if (!hasOutputBuffer()) {
            if (!this.codecNeedsEosOutputExceptionWorkaround || !this.codecReceivedEos) {
                i = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
            } else {
                try {
                    i = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            }
            if (i >= 0) {
                if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                    this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                    this.codec.releaseOutputBuffer(i, false);
                    return true;
                } else if ((this.outputBufferInfo.flags & 4) != 0) {
                    processEndOfStream();
                    return false;
                } else {
                    this.outputIndex = i;
                    ByteBuffer outputBuffer2 = getOutputBuffer(i);
                    this.outputBuffer = outputBuffer2;
                    if (outputBuffer2 != null) {
                        outputBuffer2.position(this.outputBufferInfo.offset);
                        this.outputBuffer.limit(this.outputBufferInfo.offset + this.outputBufferInfo.size);
                    }
                    this.shouldSkipOutputBuffer = shouldSkipOutputBuffer(this.outputBufferInfo.presentationTimeUs);
                }
            } else if (i == -2) {
                processOutputFormat();
                return true;
            } else if (i == -3) {
                processOutputBuffersChanged();
                return true;
            } else {
                if (this.codecNeedsEosPropagationWorkaround && (this.inputStreamEnded || this.codecReinitializationState == 2)) {
                    processEndOfStream();
                }
                return false;
            }
        }
        if (!this.codecNeedsEosOutputExceptionWorkaround || !this.codecReceivedEos) {
            z = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, this.outputBufferInfo.presentationTimeUs, this.shouldSkipOutputBuffer);
        } else {
            try {
                z = processOutputBuffer(j, j2, this.codec, this.outputBuffer, this.outputIndex, this.outputBufferInfo.flags, this.outputBufferInfo.presentationTimeUs, this.shouldSkipOutputBuffer);
            } catch (IllegalStateException unused2) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return false;
            }
        }
        if (!z) {
            return false;
        }
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        resetOutputBuffer();
        return true;
    }

    private void processOutputFormat() throws ExoPlaybackException {
        MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        onOutputFormatChanged(this.codec, outputFormat);
    }

    private void processOutputBuffersChanged() {
        if (Util.SDK_INT < 21) {
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
            return;
        }
        this.outputStreamEnded = true;
        renderToEndOfStream();
    }

    private boolean shouldSkipOutputBuffer(long j) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (this.decodeOnlyPresentationTimestamps.get(i).longValue() == j) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }

    private static void configureMediaFormatForPlaybackV23(MediaFormat mediaFormat) {
        mediaFormat.setInteger("priority", 0);
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        return Util.SDK_INT < 18 || (Util.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private int codecAdaptationWorkaroundMode(String str) {
        if (Util.SDK_INT <= 25 && "OMX.Exynos.avc.dec.secure".equals(str) && (Util.MODEL.startsWith("SM-T585") || Util.MODEL.startsWith("SM-A510") || Util.MODEL.startsWith("SM-A520") || Util.MODEL.startsWith("SM-J700"))) {
            return 2;
        }
        if (Util.SDK_INT >= 24) {
            return 0;
        }
        if ("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) {
            return ("flounder".equals(Util.DEVICE) || "flounder_lte".equals(Util.DEVICE) || "grouper".equals(Util.DEVICE) || "tilapia".equals(Util.DEVICE)) ? 1 : 0;
        }
        return 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format2) {
        return Util.SDK_INT < 21 && format2.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(String str) {
        return Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        return (Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (Util.SDK_INT <= 19 && "hb2000".equals(Util.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format2) {
        if (Util.SDK_INT > 18 || format2.channelCount != 1 || !"OMX.MTK.AUDIO.DECODER.MP3".equals(str)) {
            return false;
        }
        return true;
    }
}
