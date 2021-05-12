package org.bytedeco.javacv;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class FrameRecorder implements Closeable {
    public static final List<String> list = new LinkedList(Arrays.asList(new String[]{"FFmpeg", "OpenCV"}));
    protected double aspectRatio;
    protected int audioBitrate;
    protected int audioChannels;
    protected int audioCodec;
    protected String audioCodecName;
    protected Map<String, String> audioMetadata = new HashMap();
    protected Map<String, String> audioOptions = new HashMap();
    protected double audioQuality = -1.0d;
    protected String format;
    protected int frameNumber = 0;
    protected double frameRate;
    protected int gopSize = -1;
    protected int imageHeight;
    protected int imageScalingFlags;
    protected int imageWidth;
    protected boolean interleaved;
    protected int maxBFrames = -1;
    protected int maxDelay = -1;
    protected Map<String, String> metadata = new HashMap();
    protected Map<String, String> options = new HashMap();
    protected int pixelFormat;
    protected int sampleFormat;
    protected int sampleRate;
    protected long timestamp = 0;
    protected int trellis = -1;
    protected int videoBitrate;
    protected int videoCodec;
    protected String videoCodecName;
    protected Map<String, String> videoMetadata = new HashMap();
    protected Map<String, String> videoOptions = new HashMap();
    protected double videoQuality = -1.0d;

    public abstract void flush() throws Exception;

    public abstract void record(Frame frame) throws Exception;

    public abstract void release() throws Exception;

    public abstract void start() throws Exception;

    public abstract void stop() throws Exception;

    public static void init() {
        for (String str : list) {
            try {
                get(str).getMethod("tryLoad", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }

    public static Class<? extends FrameRecorder> getDefault() {
        for (String str : list) {
            try {
                Class<? extends FrameRecorder> cls = get(str);
                cls.getMethod("tryLoad", new Class[0]).invoke((Object) null, new Object[0]);
                return cls;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static Class<? extends FrameRecorder> get(String str) throws Exception {
        Class<FrameRecorder> cls = FrameRecorder.class;
        String str2 = cls.getPackage().getName() + "." + str;
        try {
            return Class.forName(str2).asSubclass(cls);
        } catch (ClassNotFoundException e) {
            String str3 = str2 + "FrameRecorder";
            try {
                return Class.forName(str3).asSubclass(cls);
            } catch (ClassNotFoundException unused) {
                throw new Exception("Could not get FrameRecorder class for " + str2 + " or " + str3, e);
            }
        }
    }

    public static FrameRecorder create(Class<? extends FrameRecorder> cls, Class cls2, Object obj, int i, int i2) throws Exception {
        try {
            return (FrameRecorder) cls.getConstructor(new Class[]{cls2, Integer.TYPE, Integer.TYPE}).newInstance(new Object[]{obj, Integer.valueOf(i), Integer.valueOf(i2)});
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException e) {
            e = e;
            throw new Exception("Could not create new " + cls.getSimpleName() + "(" + obj + ", " + i + ", " + i2 + ")", e);
        } catch (InvocationTargetException e2) {
            e = e2.getCause();
            throw new Exception("Could not create new " + cls.getSimpleName() + "(" + obj + ", " + i + ", " + i2 + ")", e);
        }
    }

    public static FrameRecorder createDefault(File file, int i, int i2) throws Exception {
        return create(getDefault(), File.class, file, i, i2);
    }

    public static FrameRecorder createDefault(String str, int i, int i2) throws Exception {
        return create(getDefault(), String.class, str, i, i2);
    }

    public static FrameRecorder create(String str, File file, int i, int i2) throws Exception {
        return create(get(str), File.class, file, i, i2);
    }

    public static FrameRecorder create(String str, String str2, int i, int i2) throws Exception {
        return create(get(str), String.class, str2, i, i2);
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

    public int getGopSize() {
        return this.gopSize;
    }

    public void setGopSize(int i) {
        this.gopSize = i;
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

    public double getVideoQuality() {
        return this.videoQuality;
    }

    public void setVideoQuality(double d) {
        this.videoQuality = d;
    }

    public int getSampleFormat() {
        return this.sampleFormat;
    }

    public void setSampleFormat(int i) {
        this.sampleFormat = i;
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

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public double getAudioQuality() {
        return this.audioQuality;
    }

    public void setAudioQuality(double d) {
        this.audioQuality = d;
    }

    public boolean isInterleaved() {
        return this.interleaved;
    }

    public void setInterleaved(boolean z) {
        this.interleaved = z;
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

    public void setFrameNumber(int i) {
        this.frameNumber = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getMaxBFrames() {
        return this.maxBFrames;
    }

    public void setMaxBFrames(int i) {
        this.maxBFrames = i;
    }

    public int getTrellis() {
        return this.trellis;
    }

    public void setTrellis(int i) {
        this.trellis = i;
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
}
