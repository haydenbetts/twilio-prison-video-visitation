package com.forasoft.homewavvisitor.model.liveswitch;

import fm.liveswitch.AecPipe;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioSink;
import fm.liveswitch.android.AudioRecordSource;
import fm.liveswitch.android.AudioTrackSink;
import fm.liveswitch.audioprocessing.AecProcessor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/liveswitch/AecContext;", "Lfm/liveswitch/AecContext;", "()V", "createOutputMixerSink", "Lfm/liveswitch/AudioSink;", "audioConfig", "Lfm/liveswitch/AudioConfig;", "createProcessor", "Lfm/liveswitch/AecPipe;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AecContext.kt */
public final class AecContext extends fm.liveswitch.AecContext {
    /* access modifiers changed from: protected */
    public AecPipe createProcessor() {
        AudioConfig audioConfig = new AudioConfig(48000, 2);
        return new AecProcessor(audioConfig, AudioTrackSink.getBufferDelay(audioConfig) + AudioRecordSource.getBufferDelay(audioConfig));
    }

    /* access modifiers changed from: protected */
    public AudioSink createOutputMixerSink(AudioConfig audioConfig) {
        Intrinsics.checkParameterIsNotNull(audioConfig, "audioConfig");
        return new AudioTrackSink(audioConfig);
    }
}
