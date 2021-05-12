package org.bytedeco.javacv;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class JavaFXFrameConverter extends FrameConverter<Image> {
    public Frame convert(Image image) {
        throw new UnsupportedOperationException("conversion from Image to Frame not supported yet.");
    }

    public Image convert(Frame frame) {
        return new WritableImage(new FramePixelReader(frame), frame.imageWidth, frame.imageHeight);
    }

    class FramePixelReader implements PixelReader {
        Frame frame;

        FramePixelReader(Frame frame2) {
            this.frame = frame2;
        }

        public PixelFormat getPixelFormat() {
            throw new UnsupportedOperationException("getPixelFormat not supported yet.");
        }

        public int getArgb(int i, int i2) {
            throw new UnsupportedOperationException("getArgb not supported yet.");
        }

        public Color getColor(int i, int i2) {
            throw new UnsupportedOperationException("getColor not supported yet.");
        }

        public <T extends Buffer> void getPixels(int i, int i2, int i3, int i4, WritablePixelFormat<T> writablePixelFormat, T t, int i5) {
            int i6 = this.frame.imageStride;
            if (this.frame.imageChannels != 3) {
                throw new UnsupportedOperationException("We only support frames with imageChannels = 3 (BGR)");
            } else if (t instanceof ByteBuffer) {
                ByteBuffer byteBuffer = (ByteBuffer) t;
                ByteBuffer byteBuffer2 = (ByteBuffer) this.frame.image[0];
                for (int i7 = i2; i7 < i2 + i4; i7++) {
                    for (int i8 = i; i8 < i + i3; i8++) {
                        int i9 = (i6 * i7) + (i8 * 3);
                        byteBuffer.put(byteBuffer2.get(i9));
                        byteBuffer.put(byteBuffer2.get(i9 + 1));
                        byteBuffer.put(byteBuffer2.get(i9 + 2));
                        byteBuffer.put((byte) -1);
                    }
                }
            } else {
                throw new UnsupportedOperationException("We only support bytebuffers at the moment");
            }
        }

        public void getPixels(int i, int i2, int i3, int i4, WritablePixelFormat<ByteBuffer> writablePixelFormat, byte[] bArr, int i5, int i6) {
            throw new UnsupportedOperationException("getPixels<ByteBuffer> Not supported yet.");
        }

        public void getPixels(int i, int i2, int i3, int i4, WritablePixelFormat<IntBuffer> writablePixelFormat, int[] iArr, int i5, int i6) {
            throw new UnsupportedOperationException("getPixels<IntBuffer>Not supported yet.");
        }
    }
}
