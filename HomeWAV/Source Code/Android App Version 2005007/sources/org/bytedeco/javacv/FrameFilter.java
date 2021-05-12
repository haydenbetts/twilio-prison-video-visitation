package org.bytedeco.javacv;

import java.io.Closeable;
import java.io.IOException;

public abstract class FrameFilter implements Closeable {
    protected String afilters;
    protected double aspectRatio;
    protected int audioChannels;
    protected int audioInputs;
    protected String filters;
    protected double frameRate;
    protected int imageHeight;
    protected int imageWidth;
    protected int pixelFormat;
    protected int sampleFormat;
    protected int sampleRate;
    protected int videoInputs;

    public abstract Frame pull() throws Exception;

    public abstract void push(Frame frame) throws Exception;

    public abstract void release() throws Exception;

    public abstract void start() throws Exception;

    public abstract void stop() throws Exception;

    public static FrameFilter createDefault(String str, int i, int i2) throws Exception {
        return new FFmpegFrameFilter(str, i, i2);
    }

    public String getFilters() {
        return this.filters;
    }

    public void setFilters(String str) {
        this.filters = str;
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

    public int getPixelFormat() {
        return this.pixelFormat;
    }

    public void setPixelFormat(int i) {
        this.pixelFormat = i;
    }

    public double getFrameRate() {
        return this.frameRate;
    }

    public void setFrameRate(double d) {
        this.frameRate = d;
    }

    public double getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(double d) {
        this.aspectRatio = d;
    }

    public int getVideoInputs() {
        return this.videoInputs;
    }

    public void setVideoInputs(int i) {
        this.videoInputs = i;
    }

    public int getAudioChannels() {
        return this.audioChannels;
    }

    public void setAudioChannels(int i) {
        this.audioChannels = i;
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

    public int getAudioInputs() {
        return this.audioInputs;
    }

    public void setAudioInputs(int i) {
        this.audioInputs = i;
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

    public void restart() throws Exception {
        stop();
        start();
    }

    public void flush() throws Exception {
        do {
        } while (pull() != null);
    }
}
