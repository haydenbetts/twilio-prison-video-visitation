package org.bytedeco.javacv;

import java.io.File;
import java.util.Map;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_videoio.VideoWriter;

public class OpenCVFrameRecorder extends FrameRecorder {
    private static FrameRecorder.Exception loadingException;
    private static final boolean windows = Loader.getPlatform().startsWith("windows");
    private OpenCVFrameConverter.ToMat converter;
    private String filename;
    private VideoWriter writer;

    public void flush() throws FrameRecorder.Exception {
    }

    public static OpenCVFrameRecorder createDefault(File file, int i, int i2) throws FrameRecorder.Exception {
        return new OpenCVFrameRecorder(file, i, i2);
    }

    public static OpenCVFrameRecorder createDefault(String str, int i, int i2) throws FrameRecorder.Exception {
        return new OpenCVFrameRecorder(str, i, i2);
    }

    public static void tryLoad() throws FrameRecorder.Exception {
        FrameRecorder.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(opencv_highgui.class);
            } catch (Throwable th) {
                FrameRecorder.Exception exception2 = new FrameRecorder.Exception("Failed to load " + OpenCVFrameRecorder.class, th);
                loadingException = exception2;
                throw exception2;
            }
        } else {
            throw exception;
        }
    }

    public OpenCVFrameRecorder(File file, int i, int i2) {
        this(file.getAbsolutePath(), i, i2);
    }

    public OpenCVFrameRecorder(String str, int i, int i2) {
        this.writer = null;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.filename = str;
        this.imageWidth = i;
        this.imageHeight = i2;
        this.pixelFormat = 1;
        this.videoCodec = windows ? -1 : VideoWriter.fourcc((byte) 73, (byte) 89, (byte) 85, (byte) 86);
        this.frameRate = 30.0d;
    }

    public void release() throws FrameRecorder.Exception {
        VideoWriter videoWriter = this.writer;
        if (videoWriter != null) {
            videoWriter.release();
            this.writer = null;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public double getOption(int i) {
        VideoWriter videoWriter = this.writer;
        if (videoWriter != null) {
            return videoWriter.get(i);
        }
        return Double.parseDouble((String) this.options.get(Integer.toString(i)));
    }

    public void setOption(int i, double d) {
        this.options.put(Integer.toString(i), Double.toString(d));
        VideoWriter videoWriter = this.writer;
        if (videoWriter != null) {
            videoWriter.set(i, d);
        }
    }

    public void start() throws FrameRecorder.Exception {
        this.writer = new VideoWriter(this.filename, fourCCCodec(), this.frameRate, new Size(this.imageWidth, this.imageHeight), isColour());
        for (Map.Entry entry : this.options.entrySet()) {
            this.writer.set(Integer.parseInt((String) entry.getKey()), Double.parseDouble((String) entry.getValue()));
        }
    }

    private boolean isColour() {
        return this.pixelFormat != 0;
    }

    private int fourCCCodec() {
        return this.videoCodec;
    }

    public void stop() throws FrameRecorder.Exception {
        release();
    }

    public void record(Frame frame) throws FrameRecorder.Exception {
        Mat convert = this.converter.convert(frame);
        VideoWriter videoWriter = this.writer;
        if (videoWriter != null) {
            videoWriter.write(convert);
            frame.keyFrame = true;
            return;
        }
        throw new FrameRecorder.Exception("Cannot record: There is no writer (Has start() been called?)");
    }
}
