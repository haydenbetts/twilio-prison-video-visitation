package org.bytedeco.javacv;

import org.bytedeco.ffmpeg.avutil.LogCallback;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.tools.Logger;

public class FFmpegLogCallback extends LogCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final FFmpegLogCallback instance = ((FFmpegLogCallback) new FFmpegLogCallback().retainReference());
    private static final Logger logger = Logger.create(FFmpegLogCallback.class);

    public static FFmpegLogCallback getInstance() {
        return instance;
    }

    public static void set() {
        avutil.setLogCallback(getInstance());
    }

    public void call(int i, BytePointer bytePointer) {
        if (i == 0 || i == 8 || i == 16) {
            logger.error(bytePointer.getString());
        } else if (i == 24) {
            logger.warn(bytePointer.getString());
        } else if (i == 32) {
            logger.info(bytePointer.getString());
        } else if (i == 40 || i == 48 || i == 56) {
            logger.debug(bytePointer.getString());
        }
    }
}
