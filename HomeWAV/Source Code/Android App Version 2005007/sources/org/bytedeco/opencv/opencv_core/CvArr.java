package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Opaque
public class CvArr extends AbstractArray {
    public CvArr(Pointer pointer) {
        super(pointer);
    }

    public int arrayChannels() {
        throw new UnsupportedOperationException();
    }

    public int arrayDepth() {
        throw new UnsupportedOperationException();
    }

    public int arrayOrigin() {
        throw new UnsupportedOperationException();
    }

    public void arrayOrigin(int i) {
        throw new UnsupportedOperationException();
    }

    public int arrayWidth() {
        throw new UnsupportedOperationException();
    }

    public int arrayHeight() {
        throw new UnsupportedOperationException();
    }

    public IplROI arrayROI() {
        throw new UnsupportedOperationException();
    }

    public long arraySize() {
        throw new UnsupportedOperationException();
    }

    public BytePointer arrayData() {
        throw new UnsupportedOperationException();
    }

    public long arrayStep() {
        throw new UnsupportedOperationException();
    }
}
