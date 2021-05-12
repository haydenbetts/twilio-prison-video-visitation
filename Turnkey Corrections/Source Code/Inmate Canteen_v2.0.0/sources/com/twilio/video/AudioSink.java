package com.twilio.video;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

public interface AudioSink {
    void renderSample(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3);
}
