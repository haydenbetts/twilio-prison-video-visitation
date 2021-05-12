package org.bytedeco.javacv;

import java.beans.PropertyEditorSupport;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class FrameGrabber implements Closeable {
    public static final long SENSOR_PATTERN_BGGR = 4294967297L;
    public static final long SENSOR_PATTERN_GBRG = 4294967296L;
    public static final long SENSOR_PATTERN_GRBG = 1;
    public static final long SENSOR_PATTERN_RGGB = 0;
    public static final List<String> list = new LinkedList(Arrays.asList(new String[]{"DC1394", "FlyCapture", "FlyCapture2", "OpenKinect", "OpenKinect2", "RealSense", "RealSense2", "PS3Eye", "VideoInput", "OpenCV", "FFmpeg", "IPCamera"}));
    protected double aspectRatio = 0.0d;
    protected int audioBitrate = 0;
    protected int audioChannels = 0;
    protected int audioCodec;
    protected String audioCodecName = null;
    protected Map<String, String> audioMetadata = new HashMap();
    protected Map<String, String> audioOptions = new HashMap();
    protected int audioStream = -1;
    protected int bpp = 0;
    protected boolean deinterlace = false;
    /* access modifiers changed from: private */
    public Frame delayedFrame = null;
    /* access modifiers changed from: private */
    public long delayedTime = 0;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    protected String format = null;
    protected int frameNumber = 0;
    protected double frameRate = 0.0d;
    private Future<Void> future = null;
    protected double gamma = 0.0d;
    protected int imageHeight = 0;
    protected ImageMode imageMode = ImageMode.COLOR;
    protected int imageScalingFlags = 0;
    protected int imageWidth = 0;
    protected int maxDelay = -1;
    protected Map<String, String> metadata = new HashMap();
    protected int numBuffers = 4;
    protected Map<String, String> options = new HashMap();
    protected int pixelFormat = -1;
    protected int sampleFormat = -1;
    protected SampleMode sampleMode = SampleMode.SHORT;
    protected int sampleRate = 0;
    protected long sensorPattern = -1;
    protected int timeout = 10000;
    protected long timestamp = 0;
    protected boolean triggerMode = false;
    protected int videoBitrate = 0;
    protected int videoCodec;
    protected String videoCodecName = null;
    protected Map<String, String> videoMetadata = new HashMap();
    protected Map<String, String> videoOptions = new HashMap();
    protected int videoStream = -1;

    public enum ImageMode {
        COLOR,
        GRAY,
        RAW
    }

    public enum SampleMode {
        SHORT,
        FLOAT,
        RAW
    }

    public int getLengthInFrames() {
        return 0;
    }

    public long getLengthInTime() {
        return 0;
    }

    public abstract Frame grab() throws Exception;

    public abstract void release() throws Exception;

    public abstract void start() throws Exception;

    public abstract void stop() throws Exception;

    public abstract void trigger() throws Exception;

    public static void init() {
        for (String str : list) {
            try {
                get(str).getMethod("tryLoad", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        if ((r2.getCause() instanceof java.lang.UnsupportedOperationException) == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r4 == false) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        if (((java.lang.String[]) r1.getMethod("getDeviceDescriptions", new java.lang.Class[0]).invoke((java.lang.Object) null, new java.lang.Object[0])).length > 0) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<? extends org.bytedeco.javacv.FrameGrabber> getDefault() {
        /*
            java.util.List<java.lang.String> r0 = list
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Class r1 = get(r1)     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = "tryLoad"
            r4 = 0
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ all -> 0x0049 }
            java.lang.reflect.Method r3 = r1.getMethod(r3, r5)     // Catch:{ all -> 0x0049 }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0049 }
            r3.invoke(r2, r5)     // Catch:{ all -> 0x0049 }
            r3 = 1
            java.lang.String r5 = "getDeviceDescriptions"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x003c }
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch:{ all -> 0x003c }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x003c }
            java.lang.Object r2 = r5.invoke(r2, r6)     // Catch:{ all -> 0x003c }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ all -> 0x003c }
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch:{ all -> 0x003c }
            int r2 = r2.length     // Catch:{ all -> 0x003c }
            if (r2 <= 0) goto L_0x0046
            goto L_0x0045
        L_0x003c:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()     // Catch:{ all -> 0x0049 }
            boolean r2 = r2 instanceof java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0046
        L_0x0045:
            r4 = 1
        L_0x0046:
            if (r4 == 0) goto L_0x0006
            return r1
        L_0x0049:
            goto L_0x0006
        L_0x004b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FrameGrabber.getDefault():java.lang.Class");
    }

    public static Class<? extends FrameGrabber> get(String str) throws Exception {
        Class<FrameGrabber> cls = FrameGrabber.class;
        String str2 = cls.getPackage().getName() + "." + str;
        try {
            return Class.forName(str2).asSubclass(cls);
        } catch (ClassNotFoundException e) {
            String str3 = str2 + "FrameGrabber";
            try {
                return Class.forName(str3).asSubclass(cls);
            } catch (ClassNotFoundException unused) {
                throw new Exception("Could not get FrameGrabber class for " + str2 + " or " + str3, e);
            }
        }
    }

    public static FrameGrabber create(Class<? extends FrameGrabber> cls, Class cls2, Object obj) throws Exception {
        try {
            return (FrameGrabber) cls.getConstructor(new Class[]{cls2}).newInstance(new Object[]{obj});
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException e) {
            e = e;
            throw new Exception("Could not create new " + cls.getSimpleName() + "(" + obj + ")", e);
        } catch (InvocationTargetException e2) {
            e = e2.getCause();
            throw new Exception("Could not create new " + cls.getSimpleName() + "(" + obj + ")", e);
        }
    }

    public static FrameGrabber createDefault(File file) throws Exception {
        return create(getDefault(), File.class, file);
    }

    public static FrameGrabber createDefault(String str) throws Exception {
        return create(getDefault(), String.class, str);
    }

    public static FrameGrabber createDefault(int i) throws Exception {
        try {
            return create(getDefault(), Integer.TYPE, Integer.valueOf(i));
        } catch (Exception unused) {
            return create(getDefault(), Integer.class, Integer.valueOf(i));
        }
    }

    public static FrameGrabber create(String str, File file) throws Exception {
        return create(get(str), File.class, file);
    }

    public static FrameGrabber create(String str, String str2) throws Exception {
        return create(get(str), String.class, str2);
    }

    public static FrameGrabber create(String str, int i) throws Exception {
        try {
            return create(get(str), Integer.TYPE, Integer.valueOf(i));
        } catch (Exception unused) {
            return create(get(str), Integer.class, Integer.valueOf(i));
        }
    }

    public static class PropertyEditor extends PropertyEditorSupport {
        public String getAsText() {
            Class cls = (Class) getValue();
            if (cls == null) {
                return "null";
            }
            return cls.getSimpleName().split("FrameGrabber")[0];
        }

        public void setAsText(String str) {
            if (str == null) {
                setValue((Object) null);
            }
            try {
                setValue(FrameGrabber.get(str));
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        public String[] getTags() {
            return (String[]) FrameGrabber.list.toArray(new String[FrameGrabber.list.size()]);
        }
    }

    public int getVideoStream() {
        return this.videoStream;
    }

    public void setVideoStream(int i) {
        this.videoStream = i;
    }

    public int getAudioStream() {
        return this.audioStream;
    }

    public void setAudioStream(int i) {
        this.audioStream = i;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getVideoCodecName() {
        return this.videoCodecName;
    }

    public void setVideoCodecName(String str) {
        this.videoCodecName = str;
    }

    public String getAudioCodecName() {
        return this.audioCodecName;
    }

    public void setAudioCodecName(String str) {
        this.audioCodecName = str;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public void setImageWidth(int i) {
        this.imageWidth = i;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public void setImageHeight(int i) {
        this.imageHeight = i;
    }

    public int getAudioChannels() {
        return this.audioChannels;
    }

    public void setAudioChannels(int i) {
        this.audioChannels = i;
    }

    public ImageMode getImageMode() {
        return this.imageMode;
    }

    public void setImageMode(ImageMode imageMode2) {
        this.imageMode = imageMode2;
    }

    public long getSensorPattern() {
        return this.sensorPattern;
    }

    public void setSensorPattern(long j) {
        this.sensorPattern = j;
    }

    public int getPixelFormat() {
        return this.pixelFormat;
    }

    public void setPixelFormat(int i) {
        this.pixelFormat = i;
    }

    public int getVideoCodec() {
        return this.videoCodec;
    }

    public void setVideoCodec(int i) {
        this.videoCodec = i;
    }

    public int getVideoBitrate() {
        return this.videoBitrate;
    }

    public void setVideoBitrate(int i) {
        this.videoBitrate = i;
    }

    public int getImageScalingFlags() {
        return this.imageScalingFlags;
    }

    public void setImageScalingFlags(int i) {
        this.imageScalingFlags = i;
    }

    public double getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(double d) {
        this.aspectRatio = d;
    }

    public double getFrameRate() {
        return this.frameRate;
    }

    public void setFrameRate(double d) {
        this.frameRate = d;
    }

    public int getAudioCodec() {
        return this.audioCodec;
    }

    public void setAudioCodec(int i) {
        this.audioCodec = i;
    }

    public int getAudioBitrate() {
        return this.audioBitrate;
    }

    public void setAudioBitrate(int i) {
        this.audioBitrate = i;
    }

    public SampleMode getSampleMode() {
        return this.sampleMode;
    }

    public void setSampleMode(SampleMode sampleMode2) {
        this.sampleMode = sampleMode2;
    }

    public int getSampleFormat() {
        return this.sampleFormat;
    }

    public void setSampleFormat(int i) {
        this.sampleFormat = i;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public boolean isTriggerMode() {
        return this.triggerMode;
    }

    public void setTriggerMode(boolean z) {
        this.triggerMode = z;
    }

    public int getBitsPerPixel() {
        return this.bpp;
    }

    public void setBitsPerPixel(int i) {
        this.bpp = i;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public int getNumBuffers() {
        return this.numBuffers;
    }

    public void setNumBuffers(int i) {
        this.numBuffers = i;
    }

    public double getGamma() {
        return this.gamma;
    }

    public void setGamma(double d) {
        this.gamma = d;
    }

    public boolean isDeinterlace() {
        return this.deinterlace;
    }

    public void setDeinterlace(boolean z) {
        this.deinterlace = z;
    }

    public Map<String, String> getOptions() {
        return this.options;
    }

    public void setOptions(Map<String, String> map) {
        this.options = map;
    }

    public Map<String, String> getVideoOptions() {
        return this.videoOptions;
    }

    public void setVideoOptions(Map<String, String> map) {
        this.videoOptions = map;
    }

    public Map<String, String> getAudioOptions() {
        return this.audioOptions;
    }

    public void setAudioOptions(Map<String, String> map) {
        this.audioOptions = map;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, String> map) {
        this.metadata = map;
    }

    public Map<String, String> getVideoMetadata() {
        return this.videoMetadata;
    }

    public void setVideoMetadata(Map<String, String> map) {
        this.videoMetadata = map;
    }

    public Map<String, String> getAudioMetadata() {
        return this.audioMetadata;
    }

    public void setAudioMetadata(Map<String, String> map) {
        this.audioMetadata = map;
    }

    public String getOption(String str) {
        return this.options.get(str);
    }

    public void setOption(String str, String str2) {
        this.options.put(str, str2);
    }

    public String getVideoOption(String str) {
        return this.videoOptions.get(str);
    }

    public void setVideoOption(String str, String str2) {
        this.videoOptions.put(str, str2);
    }

    public String getAudioOption(String str) {
        return this.audioOptions.get(str);
    }

    public void setAudioOption(String str, String str2) {
        this.audioOptions.put(str, str2);
    }

    public String getMetadata(String str) {
        return this.metadata.get(str);
    }

    public void setMetadata(String str, String str2) {
        this.metadata.put(str, str2);
    }

    public String getVideoMetadata(String str) {
        return this.videoMetadata.get(str);
    }

    public void setVideoMetadata(String str, String str2) {
        this.videoMetadata.put(str, str2);
    }

    public String getAudioMetadata(String str) {
        return this.audioMetadata.get(str);
    }

    public void setAudioMetadata(String str, String str2) {
        this.audioMetadata.put(str, str2);
    }

    public int getFrameNumber() {
        return this.frameNumber;
    }

    public void setFrameNumber(int i) throws Exception {
        this.frameNumber = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) throws Exception {
        this.timestamp = j;
    }

    public int getMaxDelay() {
        return this.maxDelay;
    }

    public void setMaxDelay(int i) {
        this.maxDelay = i;
    }

    public static class Exception extends IOException {
        public Exception(String str) {
            super(str);
        }

        public Exception(String str, Throwable th) {
            super(str, th);
        }
    }

    public void close() throws Exception {
        stop();
        release();
    }

    public Frame grabFrame() throws Exception {
        return grab();
    }

    public void restart() throws Exception {
        stop();
        start();
    }

    public void flush() throws Exception {
        for (int i = 0; i < this.numBuffers + 1; i++) {
            grab();
        }
    }

    public void delayedGrab(long j) {
        this.delayedFrame = null;
        this.delayedTime = 0;
        final long nanoTime = System.nanoTime() / 1000;
        Future<Void> future2 = this.future;
        if (future2 == null || future2.isDone()) {
            final long j2 = j;
            this.future = this.executor.submit(new Callable<Void>() {
                public Void call() throws Exception {
                    do {
                        FrameGrabber frameGrabber = FrameGrabber.this;
                        Frame unused = frameGrabber.delayedFrame = frameGrabber.grab();
                        long unused2 = FrameGrabber.this.delayedTime = (System.nanoTime() / 1000) - nanoTime;
                    } while (FrameGrabber.this.delayedTime < j2);
                    return null;
                }
            });
        }
    }

    public long getDelayedTime() throws InterruptedException, ExecutionException {
        Future<Void> future2 = this.future;
        if (future2 == null) {
            return 0;
        }
        future2.get();
        return this.delayedTime;
    }

    public Frame getDelayedFrame() throws InterruptedException, ExecutionException {
        Future<Void> future2 = this.future;
        if (future2 == null) {
            return null;
        }
        future2.get();
        return this.delayedFrame;
    }

    public static class Array {
        private long bestInterval = Long.MAX_VALUE;
        private long[] bestLatencies = null;
        protected FrameGrabber[] frameGrabbers = null;
        private Frame[] grabbedFrames = null;
        private long lastNewestTimestamp = 0;
        private long[] latencies = null;

        protected Array(FrameGrabber[] frameGrabberArr) {
            setFrameGrabbers(frameGrabberArr);
        }

        public FrameGrabber[] getFrameGrabbers() {
            return this.frameGrabbers;
        }

        public void setFrameGrabbers(FrameGrabber[] frameGrabberArr) {
            this.frameGrabbers = frameGrabberArr;
            this.grabbedFrames = new Frame[frameGrabberArr.length];
            this.latencies = new long[frameGrabberArr.length];
            this.bestLatencies = null;
            this.lastNewestTimestamp = 0;
        }

        public int size() {
            return this.frameGrabbers.length;
        }

        public void start() throws Exception {
            for (FrameGrabber start : this.frameGrabbers) {
                start.start();
            }
        }

        public void stop() throws Exception {
            for (FrameGrabber stop : this.frameGrabbers) {
                stop.stop();
            }
        }

        public void trigger() throws Exception {
            for (FrameGrabber frameGrabber : this.frameGrabbers) {
                if (frameGrabber.isTriggerMode()) {
                    frameGrabber.trigger();
                }
            }
        }

        public Frame[] grab() throws Exception {
            FrameGrabber[] frameGrabberArr = this.frameGrabbers;
            if (frameGrabberArr.length == 1) {
                this.grabbedFrames[0] = frameGrabberArr[0].grab();
                return this.grabbedFrames;
            }
            long j = 0;
            int i = 0;
            boolean z = false;
            while (true) {
                FrameGrabber[] frameGrabberArr2 = this.frameGrabbers;
                if (i >= frameGrabberArr2.length) {
                    break;
                }
                this.grabbedFrames[i] = frameGrabberArr2[i].grab();
                if (this.grabbedFrames[i] != null) {
                    j = Math.max(j, this.frameGrabbers[i].getTimestamp());
                }
                Class<?> cls = this.frameGrabbers[i].getClass();
                FrameGrabber[] frameGrabberArr3 = this.frameGrabbers;
                i++;
                if (cls != frameGrabberArr3[i % frameGrabberArr3.length].getClass()) {
                    z = true;
                }
            }
            if (z) {
                return this.grabbedFrames;
            }
            int i2 = 0;
            while (true) {
                FrameGrabber[] frameGrabberArr4 = this.frameGrabbers;
                if (i2 >= frameGrabberArr4.length) {
                    break;
                }
                if (this.grabbedFrames[i2] != null) {
                    this.latencies[i2] = j - Math.max(0, frameGrabberArr4[i2].getTimestamp());
                }
                i2++;
            }
            if (this.bestLatencies == null) {
                long[] jArr = this.latencies;
                this.bestLatencies = Arrays.copyOf(jArr, jArr.length);
            } else {
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < this.frameGrabbers.length; i5++) {
                    i3 = (int) (((long) i3) + this.latencies[i5]);
                    i4 = (int) (((long) i4) + this.bestLatencies[i5]);
                }
                if (i3 < i4) {
                    long[] jArr2 = this.latencies;
                    this.bestLatencies = Arrays.copyOf(jArr2, jArr2.length);
                }
            }
            this.bestInterval = Math.min(this.bestInterval, j - this.lastNewestTimestamp);
            int i6 = 0;
            while (true) {
                long[] jArr3 = this.bestLatencies;
                if (i6 >= jArr3.length) {
                    break;
                }
                jArr3[i6] = Math.min(jArr3[i6], (this.bestInterval * 9) / 10);
                i6++;
            }
            for (int i7 = 0; i7 < 2; i7++) {
                int i8 = 0;
                while (true) {
                    FrameGrabber[] frameGrabberArr5 = this.frameGrabbers;
                    if (i8 >= frameGrabberArr5.length) {
                        break;
                    }
                    if (!frameGrabberArr5[i8].isTriggerMode() && this.grabbedFrames[i8] != null) {
                        int max = (int) (j - Math.max(0, this.frameGrabbers[i8].getTimestamp()));
                        while (true) {
                            long j2 = (long) max;
                            long[] jArr4 = this.bestLatencies;
                            if (((double) (j2 - jArr4[i8])) <= ((double) jArr4[i8]) * 0.1d) {
                                break;
                            }
                            this.grabbedFrames[i8] = this.frameGrabbers[i8].grab();
                            if (this.grabbedFrames[i8] == null) {
                                break;
                            }
                            max = (int) (j - Math.max(0, this.frameGrabbers[i8].getTimestamp()));
                            if (max < 0) {
                                j = Math.max(0, this.frameGrabbers[i8].getTimestamp());
                                break;
                            }
                        }
                    }
                    i8++;
                }
            }
            this.lastNewestTimestamp = j;
            return this.grabbedFrames;
        }

        public void release() throws Exception {
            for (FrameGrabber release : this.frameGrabbers) {
                release.release();
            }
        }
    }

    public Array createArray(FrameGrabber[] frameGrabberArr) {
        return new Array(frameGrabberArr);
    }
}
