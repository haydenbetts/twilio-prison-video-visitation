package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractIplImage extends CvArr {
    protected BytePointer pointer;

    public abstract int depth();

    public abstract int height();

    public abstract BytePointer imageData();

    public abstract int imageSize();

    public abstract int nChannels();

    public abstract int origin();

    public abstract IplImage origin(int i);

    public abstract IplROI roi();

    public abstract int width();

    public abstract int widthStep();

    public AbstractIplImage(Pointer pointer2) {
        super(pointer2);
    }

    public static IplImage create(CvSize cvSize, int i, int i2) {
        IplImage cvCreateImage = org.bytedeco.opencv.global.opencv_core.cvCreateImage(cvSize, i, i2);
        if (cvCreateImage != null) {
            cvCreateImage.deallocator(new ReleaseDeallocator(cvCreateImage));
        }
        return cvCreateImage;
    }

    public static IplImage create(int i, int i2, int i3, int i4) {
        return create(org.bytedeco.opencv.global.opencv_core.cvSize(i, i2), i3, i4);
    }

    public static IplImage create(CvSize cvSize, int i, int i2, int i3) {
        IplImage create = create(cvSize, i, i2);
        if (create != null) {
            create.origin(i3);
        }
        return create;
    }

    public static IplImage create(int i, int i2, int i3, int i4, int i5) {
        IplImage create = create(i, i2, i3, i4);
        if (create != null) {
            create.origin(i5);
        }
        return create;
    }

    public static IplImage createHeader(CvSize cvSize, int i, int i2) {
        IplImage cvCreateImageHeader = org.bytedeco.opencv.global.opencv_core.cvCreateImageHeader(cvSize, i, i2);
        if (cvCreateImageHeader != null) {
            cvCreateImageHeader.deallocator(new HeaderReleaseDeallocator(cvCreateImageHeader));
        }
        return cvCreateImageHeader;
    }

    public static IplImage createHeader(int i, int i2, int i3, int i4) {
        return createHeader(org.bytedeco.opencv.global.opencv_core.cvSize(i, i2), i3, i4);
    }

    public static IplImage createHeader(CvSize cvSize, int i, int i2, int i3) {
        IplImage createHeader = createHeader(cvSize, i, i2);
        if (createHeader != null) {
            createHeader.origin(i3);
        }
        return createHeader;
    }

    public static IplImage createHeader(int i, int i2, int i3, int i4, int i5) {
        IplImage createHeader = createHeader(i, i2, i3, i4);
        if (createHeader != null) {
            createHeader.origin(i5);
        }
        return createHeader;
    }

    public static IplImage create(int i, int i2, int i3, int i4, Pointer pointer2) {
        IplImage createHeader = createHeader(i, i2, i3, i4);
        BytePointer bytePointer = new BytePointer(pointer2);
        createHeader.pointer = bytePointer;
        createHeader.imageData(bytePointer);
        return createHeader;
    }

    public static IplImage createCompatible(IplImage iplImage) {
        return createIfNotCompatible((IplImage) null, iplImage);
    }

    public static IplImage createIfNotCompatible(IplImage iplImage, IplImage iplImage2) {
        if (!(iplImage != null && iplImage.width() == iplImage2.width() && iplImage.height() == iplImage2.height() && iplImage.depth() == iplImage2.depth() && iplImage.nChannels() == iplImage2.nChannels())) {
            iplImage = create(iplImage2.width(), iplImage2.height(), iplImage2.depth(), iplImage2.nChannels(), iplImage2.origin());
        }
        iplImage.origin(iplImage2.origin());
        return iplImage;
    }

    public IplImage clone() {
        IplImage cvCloneImage = org.bytedeco.opencv.global.opencv_core.cvCloneImage((IplImage) this);
        if (cvCloneImage != null) {
            cvCloneImage.deallocator(new ReleaseDeallocator(cvCloneImage));
        }
        return cvCloneImage;
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends IplImage implements Pointer.Deallocator {
        ReleaseDeallocator(IplImage iplImage) {
            super((Pointer) iplImage);
        }

        public void deallocate() {
            if (!isNull()) {
                org.bytedeco.opencv.global.opencv_core.cvReleaseImage((IplImage) this);
                setNull();
            }
        }
    }

    protected static class HeaderReleaseDeallocator extends IplImage implements Pointer.Deallocator {
        HeaderReleaseDeallocator(IplImage iplImage) {
            super((Pointer) iplImage);
        }

        public void deallocate() {
            if (!isNull()) {
                org.bytedeco.opencv.global.opencv_core.cvReleaseImageHeader((IplImage) this);
                setNull();
            }
        }
    }

    public int arrayChannels() {
        return nChannels();
    }

    public int arrayDepth() {
        return depth();
    }

    public int arrayOrigin() {
        return origin();
    }

    public void arrayOrigin(int i) {
        origin(i);
    }

    public int arrayWidth() {
        return width();
    }

    public int arrayHeight() {
        return height();
    }

    public IplROI arrayROI() {
        return roi();
    }

    public long arraySize() {
        return (long) imageSize();
    }

    public BytePointer arrayData() {
        return imageData();
    }

    public long arrayStep() {
        return (long) widthStep();
    }

    public CvMat asCvMat() {
        CvMat cvMat = new CvMat();
        org.bytedeco.opencv.global.opencv_core.cvGetMat((CvArr) this, cvMat, (IntPointer) null, 0);
        return cvMat;
    }
}
