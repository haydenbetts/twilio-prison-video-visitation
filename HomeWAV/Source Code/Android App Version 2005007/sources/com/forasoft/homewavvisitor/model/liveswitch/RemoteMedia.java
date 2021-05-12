package com.forasoft.homewavvisitor.model.liveswitch;

import android.content.Context;
import android.view.View;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioDecoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioSink;
import fm.liveswitch.RtcRemoteMedia;
import fm.liveswitch.VideoDecoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoPipe;
import fm.liveswitch.VideoSink;
import fm.liveswitch.ViewSink;
import fm.liveswitch.android.AudioTrackSink;
import fm.liveswitch.android.OpenGLSink;
import fm.liveswitch.opus.Decoder;
import fm.liveswitch.yuv.ImageConverter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0012\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\n\u001a\u0004\u0018\u00010\u0014H\u0014J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0010H\u0014J\b\u0010\u001c\u001a\u00020\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/forasoft/homewavvisitor/model/liveswitch/RemoteMedia;", "Lfm/liveswitch/RtcRemoteMedia;", "Landroid/view/View;", "context", "Landroid/content/Context;", "aecContext", "Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;)V", "createAudioRecorder", "Lfm/liveswitch/AudioSink;", "p0", "Lfm/liveswitch/AudioFormat;", "createAudioSink", "config", "Lfm/liveswitch/AudioConfig;", "createH264Decoder", "Lfm/liveswitch/VideoDecoder;", "createImageConverter", "Lfm/liveswitch/VideoPipe;", "outputFormat", "Lfm/liveswitch/VideoFormat;", "createOpusDecoder", "Lfm/liveswitch/AudioDecoder;", "createVideoRecorder", "Lfm/liveswitch/VideoSink;", "createViewSink", "Lfm/liveswitch/ViewSink;", "createVp8Decoder", "createVp9Decoder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RemoteMedia.kt */
public final class RemoteMedia extends RtcRemoteMedia<View> {
    private final Context context;

    /* access modifiers changed from: protected */
    public AudioSink createAudioRecorder(AudioFormat audioFormat) {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoDecoder createH264Decoder() {
        return null;
    }

    /* access modifiers changed from: protected */
    public VideoSink createVideoRecorder(VideoFormat videoFormat) {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteMedia(Context context2, AecContext aecContext) {
        super(false, false, aecContext);
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        initialize();
    }

    /* access modifiers changed from: protected */
    public AudioDecoder createOpusDecoder(AudioConfig audioConfig) {
        return new Decoder(audioConfig);
    }

    /* access modifiers changed from: protected */
    public ViewSink<View> createViewSink() {
        return new OpenGLSink(this.context);
    }

    /* access modifiers changed from: protected */
    public VideoDecoder createVp9Decoder() {
        return new fm.liveswitch.vp9.Decoder();
    }

    /* access modifiers changed from: protected */
    public VideoPipe createImageConverter(VideoFormat videoFormat) {
        return new ImageConverter(videoFormat);
    }

    /* access modifiers changed from: protected */
    public AudioSink createAudioSink(AudioConfig audioConfig) {
        return new AudioTrackSink(audioConfig);
    }

    /* access modifiers changed from: protected */
    public VideoDecoder createVp8Decoder() {
        return new fm.liveswitch.vp8.Decoder();
    }
}
