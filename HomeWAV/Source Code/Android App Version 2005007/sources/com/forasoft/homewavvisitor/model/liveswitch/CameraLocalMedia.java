package com.forasoft.homewavvisitor.model.liveswitch;

import android.content.Context;
import android.view.View;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioEncoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioSink;
import fm.liveswitch.AudioSource;
import fm.liveswitch.LayoutScale;
import fm.liveswitch.RtcLocalMedia;
import fm.liveswitch.VideoConfig;
import fm.liveswitch.VideoEncoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoPipe;
import fm.liveswitch.VideoSink;
import fm.liveswitch.VideoSource;
import fm.liveswitch.ViewSink;
import fm.liveswitch.android.AudioRecordSource;
import fm.liveswitch.android.CameraPreview;
import fm.liveswitch.android.CameraSource;
import fm.liveswitch.opus.Encoder;
import fm.liveswitch.yuv.ImageConverter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010 \u001a\u00020!H\u0014J\u0010\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u0017H\u0014J\n\u0010%\u001a\u0004\u0018\u00010\u0017H\u0014J\n\u0010&\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/model/liveswitch/CameraLocalMedia;", "Lfm/liveswitch/RtcLocalMedia;", "Landroid/view/View;", "context", "Landroid/content/Context;", "disableAudio", "", "disableVideo", "aecContext", "Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;", "withH264", "(Landroid/content/Context;ZZLcom/forasoft/homewavvisitor/model/liveswitch/AecContext;Z)V", "preview", "Lfm/liveswitch/android/CameraPreview;", "createAudioRecorder", "Lfm/liveswitch/AudioSink;", "p0", "Lfm/liveswitch/AudioFormat;", "createAudioSource", "Lfm/liveswitch/AudioSource;", "config", "Lfm/liveswitch/AudioConfig;", "createH264Encoder", "Lfm/liveswitch/VideoEncoder;", "createImageConverter", "Lfm/liveswitch/VideoPipe;", "outputFormat", "Lfm/liveswitch/VideoFormat;", "createOpusEncoder", "Lfm/liveswitch/AudioEncoder;", "createVideoRecorder", "Lfm/liveswitch/VideoSink;", "createVideoSource", "Lfm/liveswitch/VideoSource;", "createViewSink", "Lfm/liveswitch/ViewSink;", "createVp8Encoder", "createVp9Encoder", "getView", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CameraLocalMedia.kt */
public final class CameraLocalMedia extends RtcLocalMedia<View> {
    private final Context context;
    private CameraPreview preview;
    private final boolean withH264;

    /* access modifiers changed from: protected */
    public AudioSink createAudioRecorder(AudioFormat audioFormat) {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoSink createVideoRecorder(VideoFormat videoFormat) {
        return null;
    }

    /* access modifiers changed from: protected */
    public ViewSink<View> createViewSink() {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoEncoder createVp9Encoder() {
        return null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CameraLocalMedia(Context context2, boolean z, boolean z2, AecContext aecContext, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, z, z2, aecContext, (i & 16) != 0 ? false : z3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraLocalMedia(Context context2, boolean z, boolean z2, AecContext aecContext, boolean z3) {
        super(z, z2, aecContext);
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        this.withH264 = z3;
        initialize();
    }

    /* access modifiers changed from: protected */
    public AudioSource createAudioSource(AudioConfig audioConfig) {
        return new AudioRecordSource(this.context, audioConfig);
    }

    /* access modifiers changed from: protected */
    public VideoSource createVideoSource() {
        CameraPreview cameraPreview = new CameraPreview(this.context, LayoutScale.Cover);
        this.preview = cameraPreview;
        if (cameraPreview == null) {
            Intrinsics.throwNpe();
        }
        return new CameraSource(cameraPreview, new VideoConfig(640, 480, 30.0d));
    }

    /* access modifiers changed from: protected */
    public AudioEncoder createOpusEncoder(AudioConfig audioConfig) {
        Encoder encoder = new Encoder(audioConfig);
        encoder.setMaxBitrate(24);
        return encoder;
    }

    /* access modifiers changed from: protected */
    public VideoEncoder createH264Encoder() {
        if (this.withH264) {
            return new fm.liveswitch.openh264.Encoder();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoPipe createImageConverter(VideoFormat videoFormat) {
        return new ImageConverter(videoFormat);
    }

    /* access modifiers changed from: protected */
    public VideoEncoder createVp8Encoder() {
        return new fm.liveswitch.vp8.Encoder();
    }

    public View getView() {
        CameraPreview cameraPreview = this.preview;
        if (cameraPreview != null) {
            return cameraPreview.getView();
        }
        return null;
    }
}
