package com.twilio.video;

import java.nio.ByteBuffer;

public interface AudioSink {
    void renderSample(ByteBuffer byteBuffer, int i, int i2, int i3);
}
