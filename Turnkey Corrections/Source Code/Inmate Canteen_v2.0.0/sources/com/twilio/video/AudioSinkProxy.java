package com.twilio.video;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

class AudioSinkProxy implements AudioSink {
    private final AudioSink audioSink;
    private final Handler handler;
    private final HandlerThread handlerThread;
    private boolean isReleased = false;

    AudioSinkProxy(AudioSink audioSink2) {
        this.audioSink = audioSink2;
        this.handlerThread = new HandlerThread(audioSink2.toString());
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    public void renderSample(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (!this.isReleased) {
            this.handler.post(new Runnable(byteBuffer, i, i2, i3) {
                private final /* synthetic */ ByteBuffer f$1;
                private final /* synthetic */ int f$2;
                private final /* synthetic */ int f$3;
                private final /* synthetic */ int f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void run() {
                    AudioSinkProxy.this.audioSink.renderSample(this.f$1, this.f$2, this.f$3, this.f$4);
                }
            });
        }
    }

    public void release() {
        this.isReleased = true;
        this.handlerThread.quit();
    }
}
