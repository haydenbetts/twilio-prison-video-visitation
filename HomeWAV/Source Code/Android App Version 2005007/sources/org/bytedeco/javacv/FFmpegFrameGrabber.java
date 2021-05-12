package org.bytedeco.javacv;

import com.google.android.exoplayer2.C;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int;
import org.bytedeco.ffmpeg.avutil.AVDictionaryEntry;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avdevice;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.ffmpeg.global.swresample;
import org.bytedeco.ffmpeg.global.swscale;
import org.bytedeco.ffmpeg.swresample.SwrContext;
import org.bytedeco.ffmpeg.swscale.SwsContext;
import org.bytedeco.ffmpeg.swscale.SwsFilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

public class FFmpegFrameGrabber extends FrameGrabber {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<Pointer, InputStream> inputStreams = Collections.synchronizedMap(new HashMap());
    private static FrameGrabber.Exception loadingException;
    static ReadCallback readCallback = ((ReadCallback) new ReadCallback().retainReference());
    static SeekCallback seekCallback = ((SeekCallback) new SeekCallback().retainReference());
    private AVCodecContext audio_c;
    private AVStream audio_st;
    private AVIOContext avio;
    private boolean closeInputStream;
    private String filename;
    private Frame frame;
    private boolean frameGrabbed;
    private int[] got_frame;
    private Buffer[] image_buf;
    private BytePointer[] image_ptr;
    private SwsContext img_convert_ctx;
    private InputStream inputStream;
    private int maximumSize;
    private AVFormatContext oc;
    private AVFrame picture;
    private AVFrame picture_rgb;
    private AVPacket pkt;
    private AVPacket pkt2;
    private PointerPointer plane_ptr;
    private PointerPointer plane_ptr2;
    private Buffer[] samples_buf;
    private Buffer[] samples_buf_out;
    private int samples_channels;
    private SwrContext samples_convert_ctx;
    private int samples_format;
    private AVFrame samples_frame;
    private BytePointer[] samples_ptr;
    private BytePointer[] samples_ptr_out;
    private int samples_rate;
    private int sizeof_pkt;
    private volatile boolean started;
    private AVCodecContext video_c;
    private AVStream video_st;

    public static String[] getDeviceDescriptions() throws FrameGrabber.Exception {
        tryLoad();
        throw new UnsupportedOperationException("Device enumeration not support by FFmpeg.");
    }

    public static FFmpegFrameGrabber createDefault(File file) throws FrameGrabber.Exception {
        return new FFmpegFrameGrabber(file);
    }

    public static FFmpegFrameGrabber createDefault(String str) throws FrameGrabber.Exception {
        return new FFmpegFrameGrabber(str);
    }

    public static FFmpegFrameGrabber createDefault(int i) throws FrameGrabber.Exception {
        throw new FrameGrabber.Exception(FFmpegFrameGrabber.class + " does not support device numbers.");
    }

    public static void tryLoad() throws FrameGrabber.Exception {
        FrameGrabber.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(avutil.class);
                Loader.load(swresample.class);
                Loader.load(avcodec.class);
                Loader.load(avformat.class);
                Loader.load(swscale.class);
                avcodec.av_jni_set_java_vm(Loader.getJavaVM(), (Pointer) null);
                avcodec.avcodec_register_all();
                avformat.av_register_all();
                avformat.avformat_network_init();
                Loader.load(avdevice.class);
                avdevice.avdevice_register_all();
            } catch (Throwable th) {
                if (th instanceof FrameGrabber.Exception) {
                    FrameGrabber.Exception exception2 = th;
                    loadingException = exception2;
                    throw exception2;
                }
                FrameGrabber.Exception exception3 = new FrameGrabber.Exception("Failed to load " + FFmpegFrameGrabber.class, th);
                loadingException = exception3;
                throw exception3;
            }
        } else {
            throw exception;
        }
    }

    static {
        try {
            tryLoad();
            FFmpegLockCallback.init();
        } catch (FrameGrabber.Exception unused) {
        }
    }

    public FFmpegFrameGrabber(File file) {
        this(file.getAbsolutePath());
    }

    public FFmpegFrameGrabber(String str) {
        this.started = false;
        this.filename = str;
        this.pixelFormat = -1;
        this.sampleFormat = -1;
    }

    public FFmpegFrameGrabber(InputStream inputStream2) {
        this(inputStream2, 2147483639);
    }

    public FFmpegFrameGrabber(InputStream inputStream2, int i) {
        this.started = false;
        this.inputStream = inputStream2;
        this.closeInputStream = true;
        this.pixelFormat = -1;
        this.sampleFormat = -1;
        this.maximumSize = i;
    }

    public void release() throws FrameGrabber.Exception {
        synchronized (avcodec.class) {
            releaseUnsafe();
        }
    }

    public synchronized void releaseUnsafe() throws FrameGrabber.Exception {
        AVFormatContext aVFormatContext;
        AVPacket aVPacket;
        this.started = false;
        PointerPointer pointerPointer = this.plane_ptr;
        if (!(pointerPointer == null || this.plane_ptr2 == null)) {
            pointerPointer.releaseReference();
            this.plane_ptr2.releaseReference();
            this.plane_ptr2 = null;
            this.plane_ptr = null;
        }
        if (!(this.pkt == null || (aVPacket = this.pkt2) == null)) {
            if (aVPacket.size() > 0) {
                avcodec.av_packet_unref(this.pkt);
            }
            this.pkt.releaseReference();
            this.pkt2.releaseReference();
            this.pkt2 = null;
            this.pkt = null;
        }
        if (this.image_ptr != null) {
            int i = 0;
            while (true) {
                BytePointer[] bytePointerArr = this.image_ptr;
                if (i >= bytePointerArr.length) {
                    break;
                }
                avutil.av_free(bytePointerArr[i]);
                i++;
            }
            this.image_ptr = null;
        }
        AVFrame aVFrame = this.picture_rgb;
        if (aVFrame != null) {
            avutil.av_frame_free(aVFrame);
            this.picture_rgb = null;
        }
        AVFrame aVFrame2 = this.picture;
        if (aVFrame2 != null) {
            avutil.av_frame_free(aVFrame2);
            this.picture = null;
        }
        AVCodecContext aVCodecContext = this.video_c;
        if (aVCodecContext != null) {
            avcodec.avcodec_free_context(aVCodecContext);
            this.video_c = null;
        }
        AVFrame aVFrame3 = this.samples_frame;
        if (aVFrame3 != null) {
            avutil.av_frame_free(aVFrame3);
            this.samples_frame = null;
        }
        AVCodecContext aVCodecContext2 = this.audio_c;
        if (aVCodecContext2 != null) {
            avcodec.avcodec_free_context(aVCodecContext2);
            this.audio_c = null;
        }
        if (this.inputStream == null && (aVFormatContext = this.oc) != null && !aVFormatContext.isNull()) {
            avformat.avformat_close_input(this.oc);
            this.oc = null;
        }
        SwsContext swsContext = this.img_convert_ctx;
        if (swsContext != null) {
            swscale.sws_freeContext(swsContext);
            this.img_convert_ctx = null;
        }
        if (this.samples_ptr_out != null) {
            int i2 = 0;
            while (true) {
                BytePointer[] bytePointerArr2 = this.samples_ptr_out;
                if (i2 >= bytePointerArr2.length) {
                    break;
                }
                avutil.av_free(bytePointerArr2[i2].position(0));
                i2++;
            }
            this.samples_ptr_out = null;
            this.samples_buf_out = null;
        }
        SwrContext swrContext = this.samples_convert_ctx;
        if (swrContext != null) {
            swresample.swr_free(swrContext);
            this.samples_convert_ctx = null;
        }
        this.got_frame = null;
        this.frameGrabbed = false;
        this.frame = null;
        this.timestamp = 0;
        this.frameNumber = 0;
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 != null) {
            try {
                if (this.oc == null) {
                    if (this.closeInputStream) {
                        inputStream2.close();
                    }
                } else if (this.maximumSize > 0) {
                    try {
                        inputStream2.reset();
                    } catch (IOException e) {
                        PrintStream printStream = System.err;
                        printStream.println("Error on InputStream.reset(): " + e);
                    }
                }
                inputStreams.remove(this.oc);
                AVIOContext aVIOContext = this.avio;
                if (aVIOContext != null) {
                    if (aVIOContext.buffer() != null) {
                        avutil.av_free(this.avio.buffer());
                        this.avio.buffer((BytePointer) null);
                    }
                    avutil.av_free(this.avio);
                    this.avio = null;
                }
                AVFormatContext aVFormatContext2 = this.oc;
                if (aVFormatContext2 != null) {
                    avformat.avformat_free_context(aVFormatContext2);
                    this.oc = null;
                }
            } catch (IOException e2) {
                throw new FrameGrabber.Exception("Error on InputStream.close(): ", e2);
            } catch (Throwable th) {
                inputStreams.remove(this.oc);
                AVIOContext aVIOContext2 = this.avio;
                if (aVIOContext2 != null) {
                    if (aVIOContext2.buffer() != null) {
                        avutil.av_free(this.avio.buffer());
                        this.avio.buffer((BytePointer) null);
                    }
                    avutil.av_free(this.avio);
                    this.avio = null;
                }
                AVFormatContext aVFormatContext3 = this.oc;
                if (aVFormatContext3 != null) {
                    avformat.avformat_free_context(aVFormatContext3);
                    this.oc = null;
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    static class ReadCallback extends Read_packet_Pointer_BytePointer_int {
        ReadCallback() {
        }

        public int call(Pointer pointer, BytePointer bytePointer, int i) {
            try {
                byte[] bArr = new byte[i];
                int read = FFmpegFrameGrabber.inputStreams.get(pointer).read(bArr, 0, i);
                if (read < 0) {
                    return 0;
                }
                bytePointer.put(bArr, 0, read);
                return read;
            } catch (Throwable th) {
                PrintStream printStream = System.err;
                printStream.println("Error on InputStream.read(): " + th);
                return -1;
            }
        }
    }

    static class SeekCallback extends Seek_Pointer_long_int {
        SeekCallback() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:28:0x005f A[Catch:{ all -> 0x006e }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x006c  */
        /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long call(org.bytedeco.javacpp.Pointer r17, long r18, int r20) {
            /*
                r16 = this;
                r0 = r20
                r1 = -1
                java.util.Map<org.bytedeco.javacpp.Pointer, java.io.InputStream> r3 = org.bytedeco.javacv.FFmpegFrameGrabber.inputStreams     // Catch:{ all -> 0x006e }
                r4 = r17
                java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x006e }
                java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ all -> 0x006e }
                r4 = 65536(0x10000, float:9.18355E-41)
                r5 = 0
                if (r0 == 0) goto L_0x0055
                r7 = 1
                if (r0 == r7) goto L_0x0058
                r7 = 2
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                if (r0 == r7) goto L_0x0041
                if (r0 == r4) goto L_0x0022
                return r1
            L_0x0022:
                r10 = r5
            L_0x0023:
                long r12 = r3.skip(r8)     // Catch:{ all -> 0x006e }
                int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
                if (r7 != 0) goto L_0x003f
                r3.reset()     // Catch:{ all -> 0x006e }
                r12 = r5
            L_0x002f:
                long r14 = r3.skip(r8)     // Catch:{ all -> 0x006e }
                int r7 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
                if (r7 != 0) goto L_0x003d
                long r7 = r12 - r10
                r3.reset()     // Catch:{ all -> 0x006e }
                goto L_0x005b
            L_0x003d:
                long r12 = r12 + r14
                goto L_0x002f
            L_0x003f:
                long r10 = r10 + r12
                goto L_0x0023
            L_0x0041:
                r3.reset()     // Catch:{ all -> 0x006e }
                r12 = r5
            L_0x0045:
                long r10 = r3.skip(r8)     // Catch:{ all -> 0x006e }
                int r7 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r7 != 0) goto L_0x0053
                long r7 = r18 + r12
                r3.reset()     // Catch:{ all -> 0x006e }
                goto L_0x005b
            L_0x0053:
                long r12 = r12 + r10
                goto L_0x0045
            L_0x0055:
                r3.reset()     // Catch:{ all -> 0x006e }
            L_0x0058:
                r7 = r18
                r12 = r5
            L_0x005b:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 <= 0) goto L_0x006a
                long r9 = r3.skip(r7)     // Catch:{ all -> 0x006e }
                int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r11 != 0) goto L_0x0068
                goto L_0x006a
            L_0x0068:
                long r7 = r7 - r9
                goto L_0x005b
            L_0x006a:
                if (r0 != r4) goto L_0x006d
                r5 = r12
            L_0x006d:
                return r5
            L_0x006e:
                r0 = move-exception
                java.io.PrintStream r3 = java.lang.System.err
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Error on InputStream.reset() or skip(): "
                r4.append(r5)
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r3.println(r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.SeekCallback.call(org.bytedeco.javacpp.Pointer, long, int):long");
        }
    }

    public boolean isCloseInputStream() {
        return this.closeInputStream;
    }

    public void setCloseInputStream(boolean z) {
        this.closeInputStream = z;
    }

    public boolean hasVideo() {
        return this.video_st != null;
    }

    public boolean hasAudio() {
        return this.audio_st != null;
    }

    public double getGamma() {
        if (this.gamma == 0.0d) {
            return 2.2d;
        }
        return this.gamma;
    }

    public String getFormat() {
        AVFormatContext aVFormatContext = this.oc;
        if (aVFormatContext == null) {
            return super.getFormat();
        }
        return aVFormatContext.iformat().name().getString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.video_c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getImageWidth() {
        /*
            r1 = this;
            int r0 = r1.imageWidth
            if (r0 > 0) goto L_0x000e
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.video_c
            if (r0 != 0) goto L_0x0009
            goto L_0x000e
        L_0x0009:
            int r0 = r0.width()
            goto L_0x0012
        L_0x000e:
            int r0 = super.getImageWidth()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.getImageWidth():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.video_c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getImageHeight() {
        /*
            r1 = this;
            int r0 = r1.imageHeight
            if (r0 > 0) goto L_0x000e
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.video_c
            if (r0 != 0) goto L_0x0009
            goto L_0x000e
        L_0x0009:
            int r0 = r0.height()
            goto L_0x0012
        L_0x000e:
            int r0 = super.getImageHeight()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.getImageHeight():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.audio_c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAudioChannels() {
        /*
            r1 = this;
            int r0 = r1.audioChannels
            if (r0 > 0) goto L_0x000e
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.audio_c
            if (r0 != 0) goto L_0x0009
            goto L_0x000e
        L_0x0009:
            int r0 = r0.channels()
            goto L_0x0012
        L_0x000e:
            int r0 = super.getAudioChannels()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.getAudioChannels():int");
    }

    public int getPixelFormat() {
        if (this.imageMode != FrameGrabber.ImageMode.COLOR && this.imageMode != FrameGrabber.ImageMode.GRAY) {
            AVCodecContext aVCodecContext = this.video_c;
            if (aVCodecContext != null) {
                return aVCodecContext.pix_fmt();
            }
            return super.getPixelFormat();
        } else if (this.pixelFormat == -1) {
            return this.imageMode == FrameGrabber.ImageMode.COLOR ? 3 : 8;
        } else {
            return this.pixelFormat;
        }
    }

    public int getVideoCodec() {
        AVCodecContext aVCodecContext = this.video_c;
        return aVCodecContext == null ? super.getVideoCodec() : aVCodecContext.codec_id();
    }

    public int getVideoBitrate() {
        AVCodecContext aVCodecContext = this.video_c;
        return aVCodecContext == null ? super.getVideoBitrate() : (int) aVCodecContext.bit_rate();
    }

    public double getAspectRatio() {
        AVStream aVStream = this.video_st;
        if (aVStream == null) {
            return super.getAspectRatio();
        }
        AVRational av_guess_sample_aspect_ratio = avformat.av_guess_sample_aspect_ratio(this.oc, aVStream, this.picture);
        double num = ((double) av_guess_sample_aspect_ratio.num()) / ((double) av_guess_sample_aspect_ratio.den());
        if (num == 0.0d) {
            return 1.0d;
        }
        return num;
    }

    public double getFrameRate() {
        return getVideoFrameRate();
    }

    public double getAudioFrameRate() {
        if (this.audio_st == null) {
            return 0.0d;
        }
        AVFrame aVFrame = this.samples_frame;
        if (aVFrame == null || aVFrame.nb_samples() == 0) {
            try {
                grabFrame(true, false, false, false);
                this.frameGrabbed = true;
            } catch (FrameGrabber.Exception unused) {
                return 0.0d;
            }
        }
        AVFrame aVFrame2 = this.samples_frame;
        if (aVFrame2 == null && aVFrame2.nb_samples() == 0) {
            return 0.0d;
        }
        return ((double) getSampleRate()) / ((double) this.samples_frame.nb_samples());
    }

    public double getVideoFrameRate() {
        AVStream aVStream = this.video_st;
        if (aVStream == null) {
            return super.getFrameRate();
        }
        AVRational avg_frame_rate = aVStream.avg_frame_rate();
        if (avg_frame_rate.num() == 0 && avg_frame_rate.den() == 0) {
            avg_frame_rate = this.video_st.r_frame_rate();
        }
        return ((double) avg_frame_rate.num()) / ((double) avg_frame_rate.den());
    }

    public int getAudioCodec() {
        AVCodecContext aVCodecContext = this.audio_c;
        return aVCodecContext == null ? super.getAudioCodec() : aVCodecContext.codec_id();
    }

    public int getAudioBitrate() {
        AVCodecContext aVCodecContext = this.audio_c;
        return aVCodecContext == null ? super.getAudioBitrate() : (int) aVCodecContext.bit_rate();
    }

    public int getSampleFormat() {
        if (this.sampleMode != FrameGrabber.SampleMode.SHORT && this.sampleMode != FrameGrabber.SampleMode.FLOAT) {
            AVCodecContext aVCodecContext = this.audio_c;
            if (aVCodecContext != null) {
                return aVCodecContext.sample_fmt();
            }
            return super.getSampleFormat();
        } else if (this.sampleFormat == -1) {
            return this.sampleMode == FrameGrabber.SampleMode.SHORT ? 1 : 3;
        } else {
            return this.sampleFormat;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.audio_c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getSampleRate() {
        /*
            r1 = this;
            int r0 = r1.sampleRate
            if (r0 > 0) goto L_0x000e
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.audio_c
            if (r0 != 0) goto L_0x0009
            goto L_0x000e
        L_0x0009:
            int r0 = r0.sample_rate()
            goto L_0x0012
        L_0x000e:
            int r0 = super.getSampleRate()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.getSampleRate():int");
    }

    public Map<String, String> getMetadata() {
        if (this.oc == null) {
            return super.getMetadata();
        }
        AVDictionaryEntry aVDictionaryEntry = null;
        HashMap hashMap = new HashMap();
        while (true) {
            aVDictionaryEntry = avutil.av_dict_get(this.oc.metadata(), "", aVDictionaryEntry, 2);
            if (aVDictionaryEntry == null) {
                return hashMap;
            }
            hashMap.put(aVDictionaryEntry.key().getString(), aVDictionaryEntry.value().getString());
        }
    }

    public Map<String, String> getVideoMetadata() {
        if (this.video_st == null) {
            return super.getVideoMetadata();
        }
        AVDictionaryEntry aVDictionaryEntry = null;
        HashMap hashMap = new HashMap();
        while (true) {
            aVDictionaryEntry = avutil.av_dict_get(this.video_st.metadata(), "", aVDictionaryEntry, 2);
            if (aVDictionaryEntry == null) {
                return hashMap;
            }
            hashMap.put(aVDictionaryEntry.key().getString(), aVDictionaryEntry.value().getString());
        }
    }

    public Map<String, String> getAudioMetadata() {
        if (this.audio_st == null) {
            return super.getAudioMetadata();
        }
        AVDictionaryEntry aVDictionaryEntry = null;
        HashMap hashMap = new HashMap();
        while (true) {
            aVDictionaryEntry = avutil.av_dict_get(this.audio_st.metadata(), "", aVDictionaryEntry, 2);
            if (aVDictionaryEntry == null) {
                return hashMap;
            }
            hashMap.put(aVDictionaryEntry.key().getString(), aVDictionaryEntry.value().getString());
        }
    }

    public String getMetadata(String str) {
        AVFormatContext aVFormatContext = this.oc;
        if (aVFormatContext == null) {
            return super.getMetadata(str);
        }
        AVDictionaryEntry av_dict_get = avutil.av_dict_get(aVFormatContext.metadata(), str, (AVDictionaryEntry) null, 0);
        if (av_dict_get == null || av_dict_get.value() == null) {
            return null;
        }
        return av_dict_get.value().getString();
    }

    public String getVideoMetadata(String str) {
        AVStream aVStream = this.video_st;
        if (aVStream == null) {
            return super.getVideoMetadata(str);
        }
        AVDictionaryEntry av_dict_get = avutil.av_dict_get(aVStream.metadata(), str, (AVDictionaryEntry) null, 0);
        if (av_dict_get == null || av_dict_get.value() == null) {
            return null;
        }
        return av_dict_get.value().getString();
    }

    public String getAudioMetadata(String str) {
        AVStream aVStream = this.audio_st;
        if (aVStream == null) {
            return super.getAudioMetadata(str);
        }
        AVDictionaryEntry av_dict_get = avutil.av_dict_get(aVStream.metadata(), str, (AVDictionaryEntry) null, 0);
        if (av_dict_get == null || av_dict_get.value() == null) {
            return null;
        }
        return av_dict_get.value().getString();
    }

    public void setFrameNumber(int i) throws FrameGrabber.Exception {
        if (hasVideo()) {
            setTimestamp(Math.round(((double) (((long) i) * C.MICROS_PER_SECOND)) / getFrameRate()));
        } else {
            this.frameNumber = i;
        }
    }

    public void setVideoFrameNumber(int i) throws FrameGrabber.Exception {
        if (hasVideo()) {
            setVideoTimestamp(Math.round(((double) (((long) i) * C.MICROS_PER_SECOND)) / getFrameRate()));
        } else {
            this.frameNumber = i;
        }
    }

    public void setAudioFrameNumber(int i) throws FrameGrabber.Exception {
        if (hasAudio()) {
            setAudioTimestamp(Math.round(((double) (((long) i) * C.MICROS_PER_SECOND)) / getAudioFrameRate()));
        }
    }

    public void setTimestamp(long j) throws FrameGrabber.Exception {
        setTimestamp(j, false);
    }

    public void setTimestamp(long j, boolean z) throws FrameGrabber.Exception {
        setTimestamp(j, (EnumSet<Frame.Type>) z ? EnumSet.of(Frame.Type.VIDEO, Frame.Type.AUDIO) : null);
    }

    public void setVideoTimestamp(long j) throws FrameGrabber.Exception {
        setTimestamp(j, (EnumSet<Frame.Type>) EnumSet.of(Frame.Type.VIDEO));
    }

    public void setAudioTimestamp(long j) throws FrameGrabber.Exception {
        setTimestamp(j, (EnumSet<Frame.Type>) EnumSet.of(Frame.Type.AUDIO));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00cf, code lost:
        if (((double) r10) < (((double) r5) - (r20 / 2.0d))) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x015e, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0106  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void setTimestamp(long r27, java.util.EnumSet<org.bytedeco.javacv.Frame.Type> r29) throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r26 = this;
            r1 = r26
            r0 = r29
            monitor-enter(r26)
            org.bytedeco.ffmpeg.avformat.AVFormatContext r2 = r1.oc     // Catch:{ all -> 0x0183 }
            if (r2 != 0) goto L_0x000e
            super.setTimestamp(r27)     // Catch:{ all -> 0x0183 }
            goto L_0x015d
        L_0x000e:
            r3 = 1000000(0xf4240, double:4.940656E-318)
            long r5 = r27 * r3
            long r5 = r5 / r3
            long r2 = r2.start_time()     // Catch:{ all -> 0x0183 }
            long r7 = org.bytedeco.ffmpeg.global.avutil.AV_NOPTS_VALUE     // Catch:{ all -> 0x0183 }
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0025
            org.bytedeco.ffmpeg.avformat.AVFormatContext r2 = r1.oc     // Catch:{ all -> 0x0183 }
            long r2 = r2.start_time()     // Catch:{ all -> 0x0183 }
            long r5 = r5 + r2
        L_0x0025:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0183 }
            r8 = -1
            r9 = -9223372036854775808
            r13 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r15 = 1
            r11 = r5
            int r2 = org.bytedeco.ffmpeg.global.avformat.avformat_seek_file(r7, r8, r9, r11, r13, r15)     // Catch:{ all -> 0x0183 }
            if (r2 < 0) goto L_0x015f
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x0183 }
            if (r2 == 0) goto L_0x003e
            org.bytedeco.ffmpeg.global.avcodec.avcodec_flush_buffers(r2)     // Catch:{ all -> 0x0183 }
        L_0x003e:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.audio_c     // Catch:{ all -> 0x0183 }
            if (r2 == 0) goto L_0x0045
            org.bytedeco.ffmpeg.global.avcodec.avcodec_flush_buffers(r2)     // Catch:{ all -> 0x0183 }
        L_0x0045:
            org.bytedeco.ffmpeg.avcodec.AVPacket r2 = r1.pkt2     // Catch:{ all -> 0x0183 }
            int r2 = r2.size()     // Catch:{ all -> 0x0183 }
            r3 = 0
            if (r2 <= 0) goto L_0x0058
            org.bytedeco.ffmpeg.avcodec.AVPacket r2 = r1.pkt2     // Catch:{ all -> 0x0183 }
            r2.size(r3)     // Catch:{ all -> 0x0183 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r2 = r1.pkt     // Catch:{ all -> 0x0183 }
            org.bytedeco.ffmpeg.global.avcodec.av_packet_unref(r2)     // Catch:{ all -> 0x0183 }
        L_0x0058:
            r7 = 1
            r2 = 1
            if (r0 == 0) goto L_0x012a
            boolean r4 = r26.hasVideo()     // Catch:{ all -> 0x0183 }
            boolean r9 = r26.hasAudio()     // Catch:{ all -> 0x0183 }
            if (r4 != 0) goto L_0x0069
            if (r9 == 0) goto L_0x015d
        L_0x0069:
            org.bytedeco.javacv.Frame$Type r10 = org.bytedeco.javacv.Frame.Type.VIDEO     // Catch:{ all -> 0x0183 }
            boolean r10 = r0.contains(r10)     // Catch:{ all -> 0x0183 }
            if (r10 == 0) goto L_0x0073
            if (r4 == 0) goto L_0x007d
        L_0x0073:
            org.bytedeco.javacv.Frame$Type r10 = org.bytedeco.javacv.Frame.Type.AUDIO     // Catch:{ all -> 0x0183 }
            boolean r10 = r0.contains(r10)     // Catch:{ all -> 0x0183 }
            if (r10 == 0) goto L_0x0085
            if (r9 != 0) goto L_0x0085
        L_0x007d:
            org.bytedeco.javacv.Frame$Type r0 = org.bytedeco.javacv.Frame.Type.VIDEO     // Catch:{ all -> 0x0183 }
            org.bytedeco.javacv.Frame$Type r10 = org.bytedeco.javacv.Frame.Type.AUDIO     // Catch:{ all -> 0x0183 }
            java.util.EnumSet r0 = java.util.EnumSet.of(r0, r10)     // Catch:{ all -> 0x0183 }
        L_0x0085:
            r10 = -9223372036854775808
            r14 = 0
        L_0x0089:
            long r16 = r14 + r7
            r18 = 1000(0x3e8, double:4.94E-321)
            int r20 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r20 >= 0) goto L_0x00ac
            org.bytedeco.javacv.Frame r14 = r1.grabFrame(r2, r2, r3, r3)     // Catch:{ all -> 0x0183 }
            if (r14 != 0) goto L_0x0099
            monitor-exit(r26)
            return
        L_0x0099:
            java.util.EnumSet r15 = r14.getTypes()     // Catch:{ all -> 0x0183 }
            r15.retainAll(r0)     // Catch:{ all -> 0x0183 }
            boolean r15 = r15.isEmpty()     // Catch:{ all -> 0x0183 }
            if (r15 != 0) goto L_0x00a9
            long r10 = r14.timestamp     // Catch:{ all -> 0x0183 }
            goto L_0x00ac
        L_0x00a9:
            r14 = r16
            goto L_0x0089
        L_0x00ac:
            r14 = 10
            r16 = 4611686018427387904(0x4000000000000000, double:2.0)
            r20 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r22 = 0
            if (r4 == 0) goto L_0x00dc
            double r24 = r26.getFrameRate()     // Catch:{ all -> 0x0183 }
            int r4 = (r24 > r22 ? 1 : (r24 == r22 ? 0 : -1))
            if (r4 <= 0) goto L_0x00dc
            double r18 = r26.getFrameRate()     // Catch:{ all -> 0x0183 }
            double r20 = r20 / r18
            double r12 = (double) r10     // Catch:{ all -> 0x0183 }
            double r2 = (double) r5     // Catch:{ all -> 0x0183 }
            double r16 = r20 / r16
            double r2 = r2 - r16
            int r9 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fe
        L_0x00d1:
            long r2 = r5 - r10
            long r2 = r2 * r14
            double r2 = (double) r2     // Catch:{ all -> 0x0183 }
            double r2 = r2 / r20
            long r2 = (long) r2     // Catch:{ all -> 0x0183 }
            r18 = r2
            goto L_0x0100
        L_0x00dc:
            if (r9 == 0) goto L_0x00f7
            double r2 = r26.getAudioFrameRate()     // Catch:{ all -> 0x0183 }
            int r9 = (r2 > r22 ? 1 : (r2 == r22 ? 0 : -1))
            if (r9 <= 0) goto L_0x00f7
            double r2 = r26.getAudioFrameRate()     // Catch:{ all -> 0x0183 }
            double r20 = r20 / r2
            double r2 = (double) r10     // Catch:{ all -> 0x0183 }
            double r12 = (double) r5     // Catch:{ all -> 0x0183 }
            double r16 = r20 / r16
            double r12 = r12 - r16
            int r9 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fe
            goto L_0x00d1
        L_0x00f7:
            long r2 = r5 - r7
            int r9 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fe
            goto L_0x0100
        L_0x00fe:
            r18 = 0
        L_0x0100:
            r12 = 0
        L_0x0102:
            int r2 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r2 >= 0) goto L_0x0126
            r2 = 0
            r3 = 1
            org.bytedeco.javacv.Frame r9 = r1.grabFrame(r3, r3, r2, r2)     // Catch:{ all -> 0x0183 }
            if (r9 != 0) goto L_0x0110
            monitor-exit(r26)
            return
        L_0x0110:
            java.util.EnumSet r2 = r9.getTypes()     // Catch:{ all -> 0x0183 }
            r2.retainAll(r0)     // Catch:{ all -> 0x0183 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0183 }
            if (r2 != 0) goto L_0x0102
            long r12 = r12 + r7
            long r2 = r1.timestamp     // Catch:{ all -> 0x0183 }
            long r9 = r5 - r7
            int r11 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0102
        L_0x0126:
            r0 = 1
            r1.frameGrabbed = r0     // Catch:{ all -> 0x0183 }
            goto L_0x015d
        L_0x012a:
            r2 = 0
        L_0x012b:
            long r9 = r1.timestamp     // Catch:{ all -> 0x0183 }
            long r11 = r5 + r7
            r0 = 1000(0x3e8, float:1.401E-42)
            int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r3 <= 0) goto L_0x0143
            r3 = 0
            r4 = 1
            org.bytedeco.javacv.Frame r9 = r1.grabFrame(r4, r4, r3, r3)     // Catch:{ all -> 0x0183 }
            if (r9 == 0) goto L_0x0143
            int r3 = r2 + 1
            if (r2 >= r0) goto L_0x0143
            r2 = r3
            goto L_0x012b
        L_0x0143:
            r2 = 0
        L_0x0144:
            long r9 = r1.timestamp     // Catch:{ all -> 0x0183 }
            long r11 = r5 - r7
            int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r3 >= 0) goto L_0x015a
            r3 = 0
            r4 = 1
            org.bytedeco.javacv.Frame r9 = r1.grabFrame(r4, r4, r3, r3)     // Catch:{ all -> 0x0183 }
            if (r9 == 0) goto L_0x015a
            int r4 = r2 + 1
            if (r2 >= r0) goto L_0x015a
            r2 = r4
            goto L_0x0144
        L_0x015a:
            r0 = 1
            r1.frameGrabbed = r0     // Catch:{ all -> 0x0183 }
        L_0x015d:
            monitor-exit(r26)
            return
        L_0x015f:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r3.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r4 = "avformat_seek_file() error "
            r3.append(r4)     // Catch:{ all -> 0x0183 }
            r3.append(r2)     // Catch:{ all -> 0x0183 }
            java.lang.String r2 = ": Could not seek file to timestamp "
            r3.append(r2)     // Catch:{ all -> 0x0183 }
            r3.append(r5)     // Catch:{ all -> 0x0183 }
            java.lang.String r2 = "."
            r3.append(r2)     // Catch:{ all -> 0x0183 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0183 }
            r0.<init>(r2)     // Catch:{ all -> 0x0183 }
            throw r0     // Catch:{ all -> 0x0183 }
        L_0x0183:
            r0 = move-exception
            monitor-exit(r26)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.setTimestamp(long, java.util.EnumSet):void");
    }

    public int getLengthInFrames() {
        return getLengthInVideoFrames();
    }

    public long getLengthInTime() {
        return (this.oc.duration() * C.MICROS_PER_SECOND) / C.MICROS_PER_SECOND;
    }

    public int getLengthInVideoFrames() {
        return (int) Math.round((((double) getLengthInTime()) * getFrameRate()) / 1000000.0d);
    }

    public int getLengthInAudioFrames() {
        double audioFrameRate = getAudioFrameRate();
        if (audioFrameRate > 0.0d) {
            return (int) ((((double) getLengthInTime()) * audioFrameRate) / 1000000.0d);
        }
        return 0;
    }

    public AVFormatContext getFormatContext() {
        return this.oc;
    }

    public void start() throws FrameGrabber.Exception {
        start(true);
    }

    public void start(boolean z) throws FrameGrabber.Exception {
        synchronized (avcodec.class) {
            startUnsafe(z);
        }
    }

    public void startUnsafe() throws FrameGrabber.Exception {
        startUnsafe(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:178:0x04f5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x04fa, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:?, code lost:
        r15.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x04fe, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startUnsafe(boolean r15) throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r14 = this;
            monitor-enter(r14)
            org.bytedeco.javacpp.PointerScope r0 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x04ff }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x04ff }
            r0.<init>(r2)     // Catch:{ all -> 0x04ff }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r2 = r14.oc     // Catch:{ all -> 0x04f3 }
            if (r2 == 0) goto L_0x001c
            boolean r2 = r2.isNull()     // Catch:{ all -> 0x04f3 }
            if (r2 == 0) goto L_0x0014
            goto L_0x001c
        L_0x0014:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "start() has already been called: Call stop() before calling start() again."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x001c:
            r2 = 0
            r14.img_convert_ctx = r2     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r3 = new org.bytedeco.ffmpeg.avformat.AVFormatContext     // Catch:{ all -> 0x04f3 }
            r3.<init>((org.bytedeco.javacpp.Pointer) r2)     // Catch:{ all -> 0x04f3 }
            r14.oc = r3     // Catch:{ all -> 0x04f3 }
            r14.video_c = r2     // Catch:{ all -> 0x04f3 }
            r14.audio_c = r2     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.PointerPointer r3 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x04f3 }
            r4 = 8
            r3.<init>((long) r4)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.Pointer r3 = r3.retainReference()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.PointerPointer r3 = (org.bytedeco.javacpp.PointerPointer) r3     // Catch:{ all -> 0x04f3 }
            r14.plane_ptr = r3     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.PointerPointer r3 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x04f3 }
            r3.<init>((long) r4)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.Pointer r3 = r3.retainReference()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.PointerPointer r3 = (org.bytedeco.javacpp.PointerPointer) r3     // Catch:{ all -> 0x04f3 }
            r14.plane_ptr2 = r3     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r3 = new org.bytedeco.ffmpeg.avcodec.AVPacket     // Catch:{ all -> 0x04f3 }
            r3.<init>()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.Pointer r3 = r3.retainReference()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r3 = (org.bytedeco.ffmpeg.avcodec.AVPacket) r3     // Catch:{ all -> 0x04f3 }
            r14.pkt = r3     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r3 = new org.bytedeco.ffmpeg.avcodec.AVPacket     // Catch:{ all -> 0x04f3 }
            r3.<init>()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.Pointer r3 = r3.retainReference()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r3 = (org.bytedeco.ffmpeg.avcodec.AVPacket) r3     // Catch:{ all -> 0x04f3 }
            r14.pkt2 = r3     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r3 = r14.pkt     // Catch:{ all -> 0x04f3 }
            int r3 = r3.sizeof()     // Catch:{ all -> 0x04f3 }
            r14.sizeof_pkt = r3     // Catch:{ all -> 0x04f3 }
            r3 = 1
            int[] r4 = new int[r3]     // Catch:{ all -> 0x04f3 }
            r14.got_frame = r4     // Catch:{ all -> 0x04f3 }
            r14.frameGrabbed = r1     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.Frame r4 = new org.bytedeco.javacv.Frame     // Catch:{ all -> 0x04f3 }
            r4.<init>()     // Catch:{ all -> 0x04f3 }
            r14.frame = r4     // Catch:{ all -> 0x04f3 }
            r4 = 0
            r14.timestamp = r4     // Catch:{ all -> 0x04f3 }
            r14.frameNumber = r1     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r4 = r14.pkt2     // Catch:{ all -> 0x04f3 }
            r4.size(r1)     // Catch:{ all -> 0x04f3 }
            java.lang.String r4 = r14.format     // Catch:{ all -> 0x04f3 }
            if (r4 == 0) goto L_0x00b4
            java.lang.String r4 = r14.format     // Catch:{ all -> 0x04f3 }
            int r4 = r4.length()     // Catch:{ all -> 0x04f3 }
            if (r4 <= 0) goto L_0x00b4
            java.lang.String r4 = r14.format     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVInputFormat r4 = org.bytedeco.ffmpeg.global.avformat.av_find_input_format((java.lang.String) r4)     // Catch:{ all -> 0x04f3 }
            if (r4 == 0) goto L_0x0096
            goto L_0x00b5
        L_0x0096:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "av_find_input_format() error: Could not find input format \""
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = r14.format     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "\"."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x00b4:
            r4 = r2
        L_0x00b5:
            org.bytedeco.ffmpeg.avutil.AVDictionary r5 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x04f3 }
            r5.<init>(r2)     // Catch:{ all -> 0x04f3 }
            double r6 = r14.frameRate     // Catch:{ all -> 0x04f3 }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x00ec
            double r6 = r14.frameRate     // Catch:{ all -> 0x04f3 }
            r8 = 1001000(0xf4628, float:1.4027E-39)
            org.bytedeco.ffmpeg.avutil.AVRational r6 = org.bytedeco.ffmpeg.global.avutil.av_d2q(r6, r8)     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = "framerate"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r8.<init>()     // Catch:{ all -> 0x04f3 }
            int r9 = r6.num()     // Catch:{ all -> 0x04f3 }
            r8.append(r9)     // Catch:{ all -> 0x04f3 }
            java.lang.String r9 = "/"
            r8.append(r9)     // Catch:{ all -> 0x04f3 }
            int r6 = r6.den()     // Catch:{ all -> 0x04f3 }
            r8.append(r6)     // Catch:{ all -> 0x04f3 }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r7, (java.lang.String) r6, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x00ec:
            int r6 = r14.pixelFormat     // Catch:{ all -> 0x04f3 }
            if (r6 < 0) goto L_0x0100
            java.lang.String r6 = "pixel_format"
            int r7 = r14.pixelFormat     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.BytePointer r7 = org.bytedeco.ffmpeg.global.avutil.av_get_pix_fmt_name(r7)     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r7.getString()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
            goto L_0x0116
        L_0x0100:
            org.bytedeco.javacv.FrameGrabber$ImageMode r6 = r14.imageMode     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.FrameGrabber$ImageMode r7 = org.bytedeco.javacv.FrameGrabber.ImageMode.RAW     // Catch:{ all -> 0x04f3 }
            if (r6 == r7) goto L_0x0116
            java.lang.String r6 = "pixel_format"
            org.bytedeco.javacv.FrameGrabber$ImageMode r7 = r14.imageMode     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.FrameGrabber$ImageMode r8 = org.bytedeco.javacv.FrameGrabber.ImageMode.COLOR     // Catch:{ all -> 0x04f3 }
            if (r7 != r8) goto L_0x0111
            java.lang.String r7 = "bgr24"
            goto L_0x0113
        L_0x0111:
            java.lang.String r7 = "gray8"
        L_0x0113:
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x0116:
            int r6 = r14.imageWidth     // Catch:{ all -> 0x04f3 }
            if (r6 <= 0) goto L_0x013b
            int r6 = r14.imageHeight     // Catch:{ all -> 0x04f3 }
            if (r6 <= 0) goto L_0x013b
            java.lang.String r6 = "video_size"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r7.<init>()     // Catch:{ all -> 0x04f3 }
            int r8 = r14.imageWidth     // Catch:{ all -> 0x04f3 }
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            java.lang.String r8 = "x"
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            int r8 = r14.imageHeight     // Catch:{ all -> 0x04f3 }
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x013b:
            int r6 = r14.sampleRate     // Catch:{ all -> 0x04f3 }
            if (r6 <= 0) goto L_0x0157
            java.lang.String r6 = "sample_rate"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r7.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r8 = ""
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            int r8 = r14.sampleRate     // Catch:{ all -> 0x04f3 }
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x0157:
            int r6 = r14.audioChannels     // Catch:{ all -> 0x04f3 }
            if (r6 <= 0) goto L_0x0173
            java.lang.String r6 = "channels"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r7.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r8 = ""
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            int r8 = r14.audioChannels     // Catch:{ all -> 0x04f3 }
            r7.append(r8)     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x0173:
            java.util.Map r6 = r14.options     // Catch:{ all -> 0x04f3 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x04f3 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x04f3 }
        L_0x017d:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x04f3 }
            if (r7 == 0) goto L_0x0199
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x04f3 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x04f3 }
            java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x04f3 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x04f3 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r8, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
            goto L_0x017d
        L_0x0199:
            java.io.InputStream r6 = r14.inputStream     // Catch:{ all -> 0x04f3 }
            if (r6 == 0) goto L_0x01f1
            boolean r6 = r6.markSupported()     // Catch:{ all -> 0x04f3 }
            if (r6 != 0) goto L_0x01ac
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x04f3 }
            java.io.InputStream r7 = r14.inputStream     // Catch:{ all -> 0x04f3 }
            r6.<init>(r7)     // Catch:{ all -> 0x04f3 }
            r14.inputStream = r6     // Catch:{ all -> 0x04f3 }
        L_0x01ac:
            java.io.InputStream r6 = r14.inputStream     // Catch:{ all -> 0x04f3 }
            int r7 = r14.maximumSize     // Catch:{ all -> 0x04f3 }
            r6.mark(r7)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r6 = org.bytedeco.ffmpeg.global.avformat.avformat_alloc_context()     // Catch:{ all -> 0x04f3 }
            r14.oc = r6     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacpp.BytePointer r7 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x04f3 }
            r8 = 4096(0x1000, double:2.0237E-320)
            org.bytedeco.javacpp.Pointer r6 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r8)     // Catch:{ all -> 0x04f3 }
            r7.<init>((org.bytedeco.javacpp.Pointer) r6)     // Catch:{ all -> 0x04f3 }
            r8 = 4096(0x1000, float:5.74E-42)
            r9 = 0
            org.bytedeco.ffmpeg.avformat.AVFormatContext r10 = r14.oc     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.FFmpegFrameGrabber$ReadCallback r11 = readCallback     // Catch:{ all -> 0x04f3 }
            r12 = 0
            int r6 = r14.maximumSize     // Catch:{ all -> 0x04f3 }
            if (r6 <= 0) goto L_0x01d4
            org.bytedeco.javacv.FFmpegFrameGrabber$SeekCallback r6 = seekCallback     // Catch:{ all -> 0x04f3 }
            r13 = r6
            goto L_0x01d5
        L_0x01d4:
            r13 = r2
        L_0x01d5:
            org.bytedeco.ffmpeg.avformat.AVIOContext r6 = org.bytedeco.ffmpeg.global.avformat.avio_alloc_context((org.bytedeco.javacpp.BytePointer) r7, (int) r8, (int) r9, (org.bytedeco.javacpp.Pointer) r10, (org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_BytePointer_int) r11, (org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_BytePointer_int) r12, (org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int) r13)     // Catch:{ all -> 0x04f3 }
            r14.avio = r6     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r14.oc     // Catch:{ all -> 0x04f3 }
            r7.pb(r6)     // Catch:{ all -> 0x04f3 }
            java.io.InputStream r6 = r14.inputStream     // Catch:{ all -> 0x04f3 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x04f3 }
            r14.filename = r6     // Catch:{ all -> 0x04f3 }
            java.util.Map<org.bytedeco.javacpp.Pointer, java.io.InputStream> r6 = inputStreams     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r14.oc     // Catch:{ all -> 0x04f3 }
            java.io.InputStream r8 = r14.inputStream     // Catch:{ all -> 0x04f3 }
            r6.put(r7, r8)     // Catch:{ all -> 0x04f3 }
        L_0x01f1:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r6 = r14.oc     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r14.filename     // Catch:{ all -> 0x04f3 }
            int r6 = org.bytedeco.ffmpeg.global.avformat.avformat_open_input((org.bytedeco.ffmpeg.avformat.AVFormatContext) r6, (java.lang.String) r7, (org.bytedeco.ffmpeg.avformat.AVInputFormat) r4, (org.bytedeco.ffmpeg.avutil.AVDictionary) r5)     // Catch:{ all -> 0x04f3 }
            if (r6 >= 0) goto L_0x0231
            java.lang.String r6 = "pixel_format"
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r5, (java.lang.String) r6, (java.lang.String) r2, (int) r1)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r6 = r14.oc     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = r14.filename     // Catch:{ all -> 0x04f3 }
            int r4 = org.bytedeco.ffmpeg.global.avformat.avformat_open_input((org.bytedeco.ffmpeg.avformat.AVFormatContext) r6, (java.lang.String) r7, (org.bytedeco.ffmpeg.avformat.AVInputFormat) r4, (org.bytedeco.ffmpeg.avutil.AVDictionary) r5)     // Catch:{ all -> 0x04f3 }
            if (r4 < 0) goto L_0x020b
            goto L_0x0231
        L_0x020b:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "avformat_open_input() error "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            r1.append(r4)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = ": Could not open input \""
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = r14.filename     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "\". (Has setFormat() been called?)"
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x0231:
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r5)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r4 = r14.oc     // Catch:{ all -> 0x04f3 }
            int r5 = r14.maxDelay     // Catch:{ all -> 0x04f3 }
            r4.max_delay(r5)     // Catch:{ all -> 0x04f3 }
            if (r15 == 0) goto L_0x0265
            org.bytedeco.ffmpeg.avformat.AVFormatContext r15 = r14.oc     // Catch:{ all -> 0x04f3 }
            r4 = r2
            org.bytedeco.javacpp.PointerPointer r4 = (org.bytedeco.javacpp.PointerPointer) r4     // Catch:{ all -> 0x04f3 }
            int r15 = org.bytedeco.ffmpeg.global.avformat.avformat_find_stream_info((org.bytedeco.ffmpeg.avformat.AVFormatContext) r15, (org.bytedeco.javacpp.PointerPointer) r4)     // Catch:{ all -> 0x04f3 }
            if (r15 < 0) goto L_0x0249
            goto L_0x0265
        L_0x0249:
            org.bytedeco.javacv.FrameGrabber$Exception r1 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r2.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r3 = "avformat_find_stream_info() error "
            r2.append(r3)     // Catch:{ all -> 0x04f3 }
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = ": Could not find stream information."
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = r2.toString()     // Catch:{ all -> 0x04f3 }
            r1.<init>(r15)     // Catch:{ all -> 0x04f3 }
            throw r1     // Catch:{ all -> 0x04f3 }
        L_0x0265:
            int r15 = org.bytedeco.ffmpeg.global.avutil.av_log_get_level()     // Catch:{ all -> 0x04f3 }
            r4 = 32
            if (r15 < r4) goto L_0x0274
            org.bytedeco.ffmpeg.avformat.AVFormatContext r15 = r14.oc     // Catch:{ all -> 0x04f3 }
            java.lang.String r4 = r14.filename     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avformat.av_dump_format((org.bytedeco.ffmpeg.avformat.AVFormatContext) r15, (int) r1, (java.lang.String) r4, (int) r1)     // Catch:{ all -> 0x04f3 }
        L_0x0274:
            r14.audio_st = r2     // Catch:{ all -> 0x04f3 }
            r14.video_st = r2     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r15 = r14.oc     // Catch:{ all -> 0x04f3 }
            int r15 = r15.nb_streams()     // Catch:{ all -> 0x04f3 }
            r5 = r2
            r6 = r5
            r4 = 0
        L_0x0281:
            if (r4 >= r15) goto L_0x02bf
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r14.oc     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r7.streams((int) r4)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecParameters r8 = r7.codecpar()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avformat.AVStream r9 = r14.video_st     // Catch:{ all -> 0x04f3 }
            if (r9 != 0) goto L_0x02a5
            int r9 = r8.codec_type()     // Catch:{ all -> 0x04f3 }
            if (r9 != 0) goto L_0x02a5
            int r9 = r14.videoStream     // Catch:{ all -> 0x04f3 }
            if (r9 < 0) goto L_0x029f
            int r9 = r14.videoStream     // Catch:{ all -> 0x04f3 }
            if (r9 != r4) goto L_0x02a5
        L_0x029f:
            r14.video_st = r7     // Catch:{ all -> 0x04f3 }
            r14.videoStream = r4     // Catch:{ all -> 0x04f3 }
            r5 = r8
            goto L_0x02bc
        L_0x02a5:
            org.bytedeco.ffmpeg.avformat.AVStream r9 = r14.audio_st     // Catch:{ all -> 0x04f3 }
            if (r9 != 0) goto L_0x02bc
            int r9 = r8.codec_type()     // Catch:{ all -> 0x04f3 }
            if (r9 != r3) goto L_0x02bc
            int r9 = r14.audioStream     // Catch:{ all -> 0x04f3 }
            if (r9 < 0) goto L_0x02b7
            int r9 = r14.audioStream     // Catch:{ all -> 0x04f3 }
            if (r9 != r4) goto L_0x02bc
        L_0x02b7:
            r14.audio_st = r7     // Catch:{ all -> 0x04f3 }
            r14.audioStream = r4     // Catch:{ all -> 0x04f3 }
            r6 = r8
        L_0x02bc:
            int r4 = r4 + 1
            goto L_0x0281
        L_0x02bf:
            org.bytedeco.ffmpeg.avformat.AVStream r15 = r14.video_st     // Catch:{ all -> 0x04f3 }
            if (r15 != 0) goto L_0x02fa
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r14.audio_st     // Catch:{ all -> 0x04f3 }
            if (r4 == 0) goto L_0x02c8
            goto L_0x02fa
        L_0x02c8:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "Did not find a video or audio stream inside \""
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = r14.filename     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "\" for videoStream == "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            int r2 = r14.videoStream     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = " and audioStream == "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            int r2 = r14.audioStream     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x02fa:
            if (r15 == 0) goto L_0x0407
            java.lang.String r15 = r14.videoCodecName     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodec r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_decoder_by_name((java.lang.String) r15)     // Catch:{ all -> 0x04f3 }
            if (r15 != 0) goto L_0x030c
            int r15 = r5.codec_id()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodec r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_decoder(r15)     // Catch:{ all -> 0x04f3 }
        L_0x030c:
            if (r15 == 0) goto L_0x03e7
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = org.bytedeco.ffmpeg.global.avcodec.avcodec_alloc_context3(r15)     // Catch:{ all -> 0x04f3 }
            r14.video_c = r4     // Catch:{ all -> 0x04f3 }
            if (r4 == 0) goto L_0x03df
            org.bytedeco.ffmpeg.avformat.AVStream r5 = r14.video_st     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecParameters r5 = r5.codecpar()     // Catch:{ all -> 0x04f3 }
            int r4 = org.bytedeco.ffmpeg.global.avcodec.avcodec_parameters_to_context(r4, r5)     // Catch:{ all -> 0x04f3 }
            if (r4 < 0) goto L_0x03c0
            org.bytedeco.ffmpeg.avutil.AVDictionary r4 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x04f3 }
            r4.<init>(r2)     // Catch:{ all -> 0x04f3 }
            java.util.Map r5 = r14.videoOptions     // Catch:{ all -> 0x04f3 }
            java.util.Set r5 = r5.entrySet()     // Catch:{ all -> 0x04f3 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x04f3 }
        L_0x0331:
            boolean r7 = r5.hasNext()     // Catch:{ all -> 0x04f3 }
            if (r7 == 0) goto L_0x034d
            java.lang.Object r7 = r5.next()     // Catch:{ all -> 0x04f3 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x04f3 }
            java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x04f3 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x04f3 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r4, (java.lang.String) r8, (java.lang.String) r7, (int) r1)     // Catch:{ all -> 0x04f3 }
            goto L_0x0331
        L_0x034d:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r5 = r14.video_c     // Catch:{ all -> 0x04f3 }
            r5.thread_count(r1)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r5 = r14.video_c     // Catch:{ all -> 0x04f3 }
            int r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_open2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r5, (org.bytedeco.ffmpeg.avcodec.AVCodec) r15, (org.bytedeco.ffmpeg.avutil.AVDictionary) r4)     // Catch:{ all -> 0x04f3 }
            if (r15 < 0) goto L_0x03a4
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r4)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r15 = r14.video_c     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avutil.AVRational r15 = r15.time_base()     // Catch:{ all -> 0x04f3 }
            int r15 = r15.num()     // Catch:{ all -> 0x04f3 }
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r15 <= r4) goto L_0x0380
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r15 = r14.video_c     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avutil.AVRational r15 = r15.time_base()     // Catch:{ all -> 0x04f3 }
            int r15 = r15.den()     // Catch:{ all -> 0x04f3 }
            if (r15 != r3) goto L_0x0380
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r15 = r14.video_c     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avutil.AVRational r15 = r15.time_base()     // Catch:{ all -> 0x04f3 }
            r15.den(r4)     // Catch:{ all -> 0x04f3 }
        L_0x0380:
            org.bytedeco.ffmpeg.avutil.AVFrame r15 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x04f3 }
            r14.picture = r15     // Catch:{ all -> 0x04f3 }
            if (r15 == 0) goto L_0x039c
            org.bytedeco.ffmpeg.avutil.AVFrame r15 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x04f3 }
            r14.picture_rgb = r15     // Catch:{ all -> 0x04f3 }
            if (r15 == 0) goto L_0x0394
            r14.initPictureRGB()     // Catch:{ all -> 0x04f3 }
            goto L_0x0407
        L_0x0394:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "av_frame_alloc() error: Could not allocate RGB picture frame."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x039c:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "av_frame_alloc() error: Could not allocate raw picture frame."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x03a4:
            org.bytedeco.javacv.FrameGrabber$Exception r1 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r2.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r3 = "avcodec_open2() error "
            r2.append(r3)     // Catch:{ all -> 0x04f3 }
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = ": Could not open video codec."
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = r2.toString()     // Catch:{ all -> 0x04f3 }
            r1.<init>(r15)     // Catch:{ all -> 0x04f3 }
            throw r1     // Catch:{ all -> 0x04f3 }
        L_0x03c0:
            r14.releaseUnsafe()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "avcodec_parameters_to_context() error "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            r1.append(r4)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = ": Could not copy the video stream parameters."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x03df:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "avcodec_alloc_context3() error: Could not allocate video decoding context."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x03e7:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "avcodec_find_decoder() error: Unsupported video format or codec not found: "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            int r2 = r5.codec_id()     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x0407:
            org.bytedeco.ffmpeg.avformat.AVStream r15 = r14.audio_st     // Catch:{ all -> 0x04f3 }
            if (r15 == 0) goto L_0x04ec
            java.lang.String r15 = r14.audioCodecName     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodec r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_decoder_by_name((java.lang.String) r15)     // Catch:{ all -> 0x04f3 }
            if (r15 != 0) goto L_0x041b
            int r15 = r6.codec_id()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodec r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_decoder(r15)     // Catch:{ all -> 0x04f3 }
        L_0x041b:
            if (r15 == 0) goto L_0x04cc
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = org.bytedeco.ffmpeg.global.avcodec.avcodec_alloc_context3(r15)     // Catch:{ all -> 0x04f3 }
            r14.audio_c = r4     // Catch:{ all -> 0x04f3 }
            if (r4 == 0) goto L_0x04c4
            org.bytedeco.ffmpeg.avformat.AVStream r5 = r14.audio_st     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecParameters r5 = r5.codecpar()     // Catch:{ all -> 0x04f3 }
            int r4 = org.bytedeco.ffmpeg.global.avcodec.avcodec_parameters_to_context(r4, r5)     // Catch:{ all -> 0x04f3 }
            if (r4 < 0) goto L_0x04a5
            org.bytedeco.ffmpeg.avutil.AVDictionary r4 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x04f3 }
            r4.<init>(r2)     // Catch:{ all -> 0x04f3 }
            java.util.Map r5 = r14.audioOptions     // Catch:{ all -> 0x04f3 }
            java.util.Set r5 = r5.entrySet()     // Catch:{ all -> 0x04f3 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x04f3 }
        L_0x0440:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x04f3 }
            if (r6 == 0) goto L_0x045c
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x04f3 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x04f3 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ all -> 0x04f3 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x04f3 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x04f3 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r4, (java.lang.String) r7, (java.lang.String) r6, (int) r1)     // Catch:{ all -> 0x04f3 }
            goto L_0x0440
        L_0x045c:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r5 = r14.audio_c     // Catch:{ all -> 0x04f3 }
            r5.thread_count(r1)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r5 = r14.audio_c     // Catch:{ all -> 0x04f3 }
            int r15 = org.bytedeco.ffmpeg.global.avcodec.avcodec_open2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r5, (org.bytedeco.ffmpeg.avcodec.AVCodec) r15, (org.bytedeco.ffmpeg.avutil.AVDictionary) r4)     // Catch:{ all -> 0x04f3 }
            if (r15 < 0) goto L_0x0489
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r4)     // Catch:{ all -> 0x04f3 }
            org.bytedeco.ffmpeg.avutil.AVFrame r15 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x04f3 }
            r14.samples_frame = r15     // Catch:{ all -> 0x04f3 }
            if (r15 == 0) goto L_0x0481
            org.bytedeco.javacpp.BytePointer[] r15 = new org.bytedeco.javacpp.BytePointer[r3]     // Catch:{ all -> 0x04f3 }
            r15[r1] = r2     // Catch:{ all -> 0x04f3 }
            r14.samples_ptr = r15     // Catch:{ all -> 0x04f3 }
            java.nio.Buffer[] r15 = new java.nio.Buffer[r3]     // Catch:{ all -> 0x04f3 }
            r15[r1] = r2     // Catch:{ all -> 0x04f3 }
            r14.samples_buf = r15     // Catch:{ all -> 0x04f3 }
            goto L_0x04ec
        L_0x0481:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "av_frame_alloc() error: Could not allocate audio frame."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x0489:
            org.bytedeco.javacv.FrameGrabber$Exception r1 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r2.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r3 = "avcodec_open2() error "
            r2.append(r3)     // Catch:{ all -> 0x04f3 }
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = ": Could not open audio codec."
            r2.append(r15)     // Catch:{ all -> 0x04f3 }
            java.lang.String r15 = r2.toString()     // Catch:{ all -> 0x04f3 }
            r1.<init>(r15)     // Catch:{ all -> 0x04f3 }
            throw r1     // Catch:{ all -> 0x04f3 }
        L_0x04a5:
            r14.releaseUnsafe()     // Catch:{ all -> 0x04f3 }
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "avcodec_parameters_to_context() error "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            r1.append(r4)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = ": Could not copy the audio stream parameters."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x04c4:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = "avcodec_alloc_context3() error: Could not allocate audio decoding context."
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x04cc:
            org.bytedeco.javacv.FrameGrabber$Exception r15 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x04f3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x04f3 }
            r1.<init>()     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "avcodec_find_decoder() error: Unsupported audio format or codec not found: "
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            int r2 = r6.codec_id()     // Catch:{ all -> 0x04f3 }
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r2 = "."
            r1.append(r2)     // Catch:{ all -> 0x04f3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x04f3 }
            r15.<init>(r1)     // Catch:{ all -> 0x04f3 }
            throw r15     // Catch:{ all -> 0x04f3 }
        L_0x04ec:
            r14.started = r3     // Catch:{ all -> 0x04f3 }
            r0.close()     // Catch:{ all -> 0x04ff }
            monitor-exit(r14)
            return
        L_0x04f3:
            r15 = move-exception
            throw r15     // Catch:{ all -> 0x04f5 }
        L_0x04f5:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x04fa }
            goto L_0x04fe
        L_0x04fa:
            r0 = move-exception
            r15.addSuppressed(r0)     // Catch:{ all -> 0x04ff }
        L_0x04fe:
            throw r1     // Catch:{ all -> 0x04ff }
        L_0x04ff:
            r15 = move-exception
            monitor-exit(r14)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.startUnsafe(boolean):void");
    }

    private void initPictureRGB() {
        int width = this.imageWidth > 0 ? this.imageWidth : this.video_c.width();
        int height = this.imageHeight > 0 ? this.imageHeight : this.video_c.height();
        int i = AnonymousClass1.$SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode[this.imageMode.ordinal()];
        if (i == 1 || i == 2) {
            BytePointer[] bytePointerArr = this.image_ptr;
            if (bytePointerArr != null) {
                this.image_buf = null;
                this.image_ptr = null;
                avutil.av_free(bytePointerArr[0]);
            }
            int pixelFormat = getPixelFormat();
            int i2 = width;
            for (int i3 = 1; i3 <= 32; i3 += i3) {
                int i4 = i3 - 1;
                i2 = (~i4) & (width + i4);
                avutil.av_image_fill_linesizes(this.picture_rgb.linesize(), pixelFormat, i2);
                if ((this.picture_rgb.linesize(0) & 31) == 0) {
                    break;
                }
            }
            long av_image_get_buffer_size = (long) avutil.av_image_get_buffer_size(pixelFormat, i2, height, 1);
            BytePointer[] bytePointerArr2 = {new BytePointer(avutil.av_malloc(av_image_get_buffer_size)).capacity(av_image_get_buffer_size)};
            this.image_ptr = bytePointerArr2;
            this.image_buf = new Buffer[]{bytePointerArr2[0].asBuffer()};
            avutil.av_image_fill_arrays(new PointerPointer((Pointer) this.picture_rgb), this.picture_rgb.linesize(), this.image_ptr[0], pixelFormat, i2, height, 1);
            this.picture_rgb.format(pixelFormat);
            this.picture_rgb.width(width);
            this.picture_rgb.height(height);
        } else if (i == 3) {
            this.image_ptr = new BytePointer[]{null};
            this.image_buf = new Buffer[]{null};
        }
    }

    /* renamed from: org.bytedeco.javacv.FFmpegFrameGrabber$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.bytedeco.javacv.FrameGrabber$ImageMode[] r0 = org.bytedeco.javacv.FrameGrabber.ImageMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode = r0
                org.bytedeco.javacv.FrameGrabber$ImageMode r1 = org.bytedeco.javacv.FrameGrabber.ImageMode.COLOR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bytedeco.javacv.FrameGrabber$ImageMode r1 = org.bytedeco.javacv.FrameGrabber.ImageMode.GRAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bytedeco.javacv.FrameGrabber$ImageMode r1 = org.bytedeco.javacv.FrameGrabber.ImageMode.RAW     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.AnonymousClass1.<clinit>():void");
        }
    }

    public void stop() throws FrameGrabber.Exception {
        release();
    }

    public synchronized void trigger() throws FrameGrabber.Exception {
        AVFormatContext aVFormatContext = this.oc;
        if (aVFormatContext == null || aVFormatContext.isNull()) {
            throw new FrameGrabber.Exception("Could not trigger: No AVFormatContext. (Has start() been called?)");
        }
        int i = 0;
        if (this.pkt2.size() > 0) {
            this.pkt2.size(0);
            avcodec.av_packet_unref(this.pkt);
        }
        while (i < this.numBuffers + 1) {
            if (avformat.av_read_frame(this.oc, this.pkt) >= 0) {
                avcodec.av_packet_unref(this.pkt);
                i++;
            } else {
                return;
            }
        }
    }

    private void processImage() throws FrameGrabber.Exception {
        this.frame.imageWidth = this.imageWidth > 0 ? this.imageWidth : this.video_c.width();
        this.frame.imageHeight = this.imageHeight > 0 ? this.imageHeight : this.video_c.height();
        this.frame.imageDepth = 8;
        int i = AnonymousClass1.$SwitchMap$org$bytedeco$javacv$FrameGrabber$ImageMode[this.imageMode.ordinal()];
        if (i == 1 || i == 2) {
            if (!this.deinterlace) {
                if (!(this.frame.imageWidth == this.picture_rgb.width() && this.frame.imageHeight == this.picture_rgb.height())) {
                    initPictureRGB();
                }
                SwsContext sws_getCachedContext = swscale.sws_getCachedContext(this.img_convert_ctx, this.video_c.width(), this.video_c.height(), this.video_c.pix_fmt(), this.frame.imageWidth, this.frame.imageHeight, getPixelFormat(), this.imageScalingFlags != 0 ? this.imageScalingFlags : 2, (SwsFilter) null, (SwsFilter) null, (DoublePointer) null);
                this.img_convert_ctx = sws_getCachedContext;
                if (sws_getCachedContext != null) {
                    swscale.sws_scale(sws_getCachedContext, new PointerPointer((Pointer) this.picture), this.picture.linesize(), 0, this.video_c.height(), new PointerPointer((Pointer) this.picture_rgb), this.picture_rgb.linesize());
                    this.frame.imageStride = this.picture_rgb.linesize(0);
                    this.frame.image = this.image_buf;
                    this.frame.opaque = this.picture_rgb;
                } else {
                    throw new FrameGrabber.Exception("sws_getCachedContext() error: Cannot initialize the conversion context.");
                }
            } else {
                throw new FrameGrabber.Exception("Cannot deinterlace: Functionality moved to FFmpegFrameFilter.");
            }
        } else if (i == 3) {
            this.frame.imageStride = this.picture.linesize(0);
            BytePointer data = this.picture.data(0);
            if (data != null && !data.equals(this.image_ptr[0])) {
                this.image_ptr[0] = data.capacity((long) (this.frame.imageHeight * this.frame.imageStride));
                this.image_buf[0] = data.asBuffer();
            }
            this.frame.image = this.image_buf;
            this.frame.opaque = this.picture;
        }
        this.frame.image[0].limit(this.frame.imageHeight * this.frame.imageStride);
        Frame frame2 = this.frame;
        frame2.imageChannels = frame2.imageStride / this.frame.imageWidth;
    }

    private void processSamples() throws FrameGrabber.Exception {
        int i;
        int format = this.samples_frame.format();
        int channels = avutil.av_sample_fmt_is_planar(format) != 0 ? this.samples_frame.channels() : 1;
        int av_samples_get_buffer_size = avutil.av_samples_get_buffer_size((IntPointer) null, this.audio_c.channels(), this.samples_frame.nb_samples(), this.audio_c.sample_fmt(), 1) / channels;
        Buffer[] bufferArr = this.samples_buf;
        if (bufferArr == null || bufferArr.length != channels) {
            this.samples_ptr = new BytePointer[channels];
            this.samples_buf = new Buffer[channels];
        }
        this.frame.sampleRate = this.audio_c.sample_rate();
        this.frame.audioChannels = this.audio_c.channels();
        this.frame.samples = this.samples_buf;
        this.frame.opaque = this.samples_frame;
        int av_get_bytes_per_sample = av_samples_get_buffer_size / avutil.av_get_bytes_per_sample(format);
        for (int i2 = 0; i2 < channels; i2++) {
            BytePointer data = this.samples_frame.data(i2);
            if (!data.equals(this.samples_ptr[i2]) || this.samples_ptr[i2].capacity() < ((long) av_samples_get_buffer_size)) {
                this.samples_ptr[i2] = data.capacity((long) av_samples_get_buffer_size);
                ByteBuffer asBuffer = data.asBuffer();
                switch (format) {
                    case 0:
                    case 5:
                        this.samples_buf[i2] = asBuffer;
                        break;
                    case 1:
                    case 6:
                        this.samples_buf[i2] = asBuffer.asShortBuffer();
                        break;
                    case 2:
                    case 7:
                        this.samples_buf[i2] = asBuffer.asIntBuffer();
                        break;
                    case 3:
                    case 8:
                        this.samples_buf[i2] = asBuffer.asFloatBuffer();
                        break;
                    case 4:
                    case 9:
                        this.samples_buf[i2] = asBuffer.asDoubleBuffer();
                        break;
                }
            }
            this.samples_buf[i2].position(0).limit(av_get_bytes_per_sample);
        }
        if (this.audio_c.channels() != getAudioChannels() || this.audio_c.sample_fmt() != getSampleFormat() || this.audio_c.sample_rate() != getSampleRate()) {
            if (!(this.samples_convert_ctx != null && this.samples_channels == getAudioChannels() && this.samples_format == getSampleFormat() && this.samples_rate == getSampleRate())) {
                SwrContext swr_alloc_set_opts = swresample.swr_alloc_set_opts(this.samples_convert_ctx, avutil.av_get_default_channel_layout(getAudioChannels()), getSampleFormat(), getSampleRate(), avutil.av_get_default_channel_layout(this.audio_c.channels()), this.audio_c.sample_fmt(), this.audio_c.sample_rate(), 0, (Pointer) null);
                this.samples_convert_ctx = swr_alloc_set_opts;
                if (swr_alloc_set_opts != null) {
                    int swr_init = swresample.swr_init(swr_alloc_set_opts);
                    if (swr_init >= 0) {
                        this.samples_channels = getAudioChannels();
                        this.samples_format = getSampleFormat();
                        this.samples_rate = getSampleRate();
                    } else {
                        throw new FrameGrabber.Exception("swr_init() error " + swr_init + ": Cannot initialize the conversion context.");
                    }
                } else {
                    throw new FrameGrabber.Exception("swr_alloc_set_opts() error: Cannot allocate the conversion context.");
                }
            }
            int nb_samples = this.samples_frame.nb_samples();
            int channels2 = avutil.av_sample_fmt_is_planar(this.samples_format) != 0 ? this.samples_frame.channels() : 1;
            int swr_get_out_samples = swresample.swr_get_out_samples(this.samples_convert_ctx, nb_samples);
            int av_get_bytes_per_sample2 = avutil.av_get_bytes_per_sample(this.samples_format) * swr_get_out_samples;
            if (channels2 > 1) {
                i = 1;
            } else {
                i = this.samples_channels;
            }
            int i3 = av_get_bytes_per_sample2 * i;
            if (this.samples_buf_out == null || this.samples_buf.length != channels2 || this.samples_ptr_out[0].capacity() < ((long) i3)) {
                int i4 = 0;
                while (true) {
                    BytePointer[] bytePointerArr = this.samples_ptr_out;
                    if (bytePointerArr == null || i4 >= bytePointerArr.length) {
                        this.samples_ptr_out = new BytePointer[channels2];
                        this.samples_buf_out = new Buffer[channels2];
                    } else {
                        avutil.av_free(bytePointerArr[i4].position(0));
                        i4++;
                    }
                }
                this.samples_ptr_out = new BytePointer[channels2];
                this.samples_buf_out = new Buffer[channels2];
                for (int i5 = 0; i5 < channels2; i5++) {
                    long j = (long) i3;
                    this.samples_ptr_out[i5] = new BytePointer(avutil.av_malloc(j)).capacity(j);
                    ByteBuffer asBuffer2 = this.samples_ptr_out[i5].asBuffer();
                    switch (this.samples_format) {
                        case 0:
                        case 5:
                            this.samples_buf_out[i5] = asBuffer2;
                            break;
                        case 1:
                        case 6:
                            this.samples_buf_out[i5] = asBuffer2.asShortBuffer();
                            break;
                        case 2:
                        case 7:
                            this.samples_buf_out[i5] = asBuffer2.asIntBuffer();
                            break;
                        case 3:
                        case 8:
                            this.samples_buf_out[i5] = asBuffer2.asFloatBuffer();
                            break;
                        case 4:
                        case 9:
                            this.samples_buf_out[i5] = asBuffer2.asDoubleBuffer();
                            break;
                    }
                }
            }
            this.frame.sampleRate = this.samples_rate;
            this.frame.audioChannels = this.samples_channels;
            this.frame.samples = this.samples_buf_out;
            int swr_convert = swresample.swr_convert(this.samples_convert_ctx, this.plane_ptr.put((P[]) this.samples_ptr_out), swr_get_out_samples, this.plane_ptr2.put((P[]) this.samples_ptr), nb_samples);
            if (swr_convert >= 0) {
                for (int i6 = 0; i6 < channels2; i6++) {
                    this.samples_ptr_out[i6].position(0).limit((long) ((channels2 > 1 ? 1 : this.samples_channels) * swr_convert));
                    this.samples_buf_out[i6].position(0).limit((channels2 > 1 ? 1 : this.samples_channels) * swr_convert);
                }
                return;
            }
            throw new FrameGrabber.Exception("swr_convert() error " + swr_convert + ": Cannot convert audio samples.");
        }
    }

    public Frame grab() throws FrameGrabber.Exception {
        return grabFrame(true, true, true, false, true);
    }

    public Frame grabImage() throws FrameGrabber.Exception {
        return grabFrame(false, true, true, false, false);
    }

    public Frame grabSamples() throws FrameGrabber.Exception {
        return grabFrame(true, false, true, false, false);
    }

    public Frame grabKeyFrame() throws FrameGrabber.Exception {
        return grabFrame(false, true, true, true, false);
    }

    public Frame grabFrame(boolean z, boolean z2, boolean z3, boolean z4) throws FrameGrabber.Exception {
        return grabFrame(z, z2, z3, z4, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:132:0x0260 A[Catch:{ all -> 0x0283, all -> 0x0286 }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x00af A[SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:68:0x00e2=Splitter:B:68:0x00e2, B:141:0x0276=Splitter:B:141:0x0276} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:72:0x00e7=Splitter:B:72:0x00e7, B:145:0x027b=Splitter:B:145:0x027b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.bytedeco.javacv.Frame grabFrame(boolean r14, boolean r15, boolean r16, boolean r17, boolean r18) throws org.bytedeco.javacv.FrameGrabber.Exception {
        /*
            r13 = this;
            r1 = r13
            monitor-enter(r13)
            org.bytedeco.javacpp.PointerScope r2 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x0292 }
            r0 = 0
            java.lang.Class[] r3 = new java.lang.Class[r0]     // Catch:{ all -> 0x0292 }
            r2.<init>(r3)     // Catch:{ all -> 0x0292 }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r3 = r1.oc     // Catch:{ all -> 0x0283 }
            if (r3 == 0) goto L_0x027b
            boolean r3 = r3.isNull()     // Catch:{ all -> 0x0283 }
            if (r3 != 0) goto L_0x027b
            r3 = 0
            if (r15 == 0) goto L_0x001b
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.video_st     // Catch:{ all -> 0x0283 }
            if (r4 != 0) goto L_0x0023
        L_0x001b:
            if (r14 == 0) goto L_0x0276
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.audio_st     // Catch:{ all -> 0x0283 }
            if (r4 != 0) goto L_0x0023
            goto L_0x0276
        L_0x0023:
            boolean r4 = r1.started     // Catch:{ all -> 0x0283 }
            if (r4 == 0) goto L_0x026e
            boolean r4 = r1.frameGrabbed     // Catch:{ all -> 0x0283 }
            r5 = 1
            if (r4 == 0) goto L_0x0034
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            java.nio.Buffer[] r4 = r4.image     // Catch:{ all -> 0x0283 }
            if (r4 == 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            boolean r6 = r1.frameGrabbed     // Catch:{ all -> 0x0283 }
            if (r6 == 0) goto L_0x0041
            org.bytedeco.javacv.Frame r6 = r1.frame     // Catch:{ all -> 0x0283 }
            java.nio.Buffer[] r6 = r6.samples     // Catch:{ all -> 0x0283 }
            if (r6 == 0) goto L_0x0041
            r6 = 1
            goto L_0x0042
        L_0x0041:
            r6 = 0
        L_0x0042:
            r1.frameGrabbed = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.keyFrame = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.imageWidth = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.imageHeight = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.imageDepth = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.imageChannels = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.imageStride = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.image = r3     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.sampleRate = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.audioChannels = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.samples = r3     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.data = r3     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r7 = r1.frame     // Catch:{ all -> 0x0283 }
            r7.opaque = r3     // Catch:{ all -> 0x0283 }
            if (r15 == 0) goto L_0x0091
            if (r4 == 0) goto L_0x0091
            if (r16 == 0) goto L_0x007d
            r13.processImage()     // Catch:{ all -> 0x0283 }
        L_0x007d:
            org.bytedeco.javacv.Frame r3 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r1.picture     // Catch:{ all -> 0x0283 }
            int r4 = r4.key_frame()     // Catch:{ all -> 0x0283 }
            if (r4 == 0) goto L_0x0088
            r0 = 1
        L_0x0088:
            r3.keyFrame = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r0 = r1.frame     // Catch:{ all -> 0x0283 }
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r0
        L_0x0091:
            if (r14 == 0) goto L_0x00ae
            if (r6 == 0) goto L_0x00ae
            if (r16 == 0) goto L_0x009a
            r13.processSamples()     // Catch:{ all -> 0x0283 }
        L_0x009a:
            org.bytedeco.javacv.Frame r3 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r1.samples_frame     // Catch:{ all -> 0x0283 }
            int r4 = r4.key_frame()     // Catch:{ all -> 0x0283 }
            if (r4 == 0) goto L_0x00a5
            r0 = 1
        L_0x00a5:
            r3.keyFrame = r0     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r0 = r1.frame     // Catch:{ all -> 0x0283 }
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r0
        L_0x00ae:
            r4 = 0
        L_0x00af:
            if (r4 != 0) goto L_0x0267
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt2     // Catch:{ all -> 0x0283 }
            int r6 = r6.size()     // Catch:{ all -> 0x0283 }
            if (r6 > 0) goto L_0x00e7
            org.bytedeco.ffmpeg.avformat.AVFormatContext r6 = r1.oc     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r7 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r6 = org.bytedeco.ffmpeg.global.avformat.av_read_frame(r6, r7)     // Catch:{ all -> 0x0283 }
            if (r6 >= 0) goto L_0x00e7
            if (r15 == 0) goto L_0x00e2
            org.bytedeco.ffmpeg.avformat.AVStream r6 = r1.video_st     // Catch:{ all -> 0x0283 }
            if (r6 == 0) goto L_0x00e2
            org.bytedeco.ffmpeg.avcodec.AVPacket r7 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r6 = r6.index()     // Catch:{ all -> 0x0283 }
            r7.stream_index(r6)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            r6.flags(r5)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            r6.data(r3)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            r6.size(r0)     // Catch:{ all -> 0x0283 }
            goto L_0x00e7
        L_0x00e2:
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r3
        L_0x00e7:
            org.bytedeco.javacv.Frame r6 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r7 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r7 = r7.stream_index()     // Catch:{ all -> 0x0283 }
            r6.streamIndex = r7     // Catch:{ all -> 0x0283 }
            r6 = 1000000(0xf4240, double:4.940656E-318)
            if (r15 == 0) goto L_0x019a
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.video_st     // Catch:{ all -> 0x0283 }
            if (r8 == 0) goto L_0x019a
            org.bytedeco.ffmpeg.avcodec.AVPacket r8 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r8 = r8.stream_index()     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avformat.AVStream r9 = r1.video_st     // Catch:{ all -> 0x0283 }
            int r9 = r9.index()     // Catch:{ all -> 0x0283 }
            if (r8 != r9) goto L_0x019a
            if (r17 == 0) goto L_0x0112
            org.bytedeco.ffmpeg.avcodec.AVPacket r8 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r8 = r8.flags()     // Catch:{ all -> 0x0283 }
            if (r8 != r5) goto L_0x019a
        L_0x0112:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r1.video_c     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r9 = r1.picture     // Catch:{ all -> 0x0283 }
            int[] r10 = r1.got_frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r11 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r8 = org.bytedeco.ffmpeg.global.avcodec.avcodec_decode_video2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r8, (org.bytedeco.ffmpeg.avutil.AVFrame) r9, (int[]) r10, (org.bytedeco.ffmpeg.avcodec.AVPacket) r11)     // Catch:{ all -> 0x0283 }
            if (r8 < 0) goto L_0x0185
            int[] r8 = r1.got_frame     // Catch:{ all -> 0x0283 }
            r8 = r8[r0]     // Catch:{ all -> 0x0283 }
            if (r8 == 0) goto L_0x0185
            if (r17 == 0) goto L_0x0130
            org.bytedeco.ffmpeg.avutil.AVFrame r8 = r1.picture     // Catch:{ all -> 0x0283 }
            int r8 = r8.pict_type()     // Catch:{ all -> 0x0283 }
            if (r8 != r5) goto L_0x0185
        L_0x0130:
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r1.picture     // Catch:{ all -> 0x0283 }
            long r8 = org.bytedeco.ffmpeg.global.avutil.av_frame_get_best_effort_timestamp(r4)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.video_st     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVRational r4 = r4.time_base()     // Catch:{ all -> 0x0283 }
            long r8 = r8 * r6
            int r6 = r4.num()     // Catch:{ all -> 0x0283 }
            long r6 = (long) r6     // Catch:{ all -> 0x0283 }
            long r8 = r8 * r6
            int r4 = r4.den()     // Catch:{ all -> 0x0283 }
            long r6 = (long) r4     // Catch:{ all -> 0x0283 }
            long r8 = r8 / r6
            r1.timestamp = r8     // Catch:{ all -> 0x0283 }
            long r6 = r1.timestamp     // Catch:{ all -> 0x0283 }
            double r6 = (double) r6     // Catch:{ all -> 0x0283 }
            double r8 = r13.getFrameRate()     // Catch:{ all -> 0x0283 }
            double r6 = r6 * r8
            r8 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r6 = r6 / r8
            long r6 = java.lang.Math.round(r6)     // Catch:{ all -> 0x0283 }
            int r4 = (int) r6     // Catch:{ all -> 0x0283 }
            r1.frameNumber = r4     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            java.nio.Buffer[] r6 = r1.image_buf     // Catch:{ all -> 0x0283 }
            r4.image = r6     // Catch:{ all -> 0x0283 }
            if (r16 == 0) goto L_0x016e
            r13.processImage()     // Catch:{ all -> 0x0283 }
        L_0x016e:
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            long r6 = r1.timestamp     // Catch:{ all -> 0x0283 }
            r4.timestamp = r6     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r1.picture     // Catch:{ all -> 0x0283 }
            int r6 = r6.key_frame()     // Catch:{ all -> 0x0283 }
            if (r6 == 0) goto L_0x0180
            r6 = 1
            goto L_0x0181
        L_0x0180:
            r6 = 0
        L_0x0181:
            r4.keyFrame = r6     // Catch:{ all -> 0x0283 }
            goto L_0x0257
        L_0x0185:
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer r6 = r6.data()     // Catch:{ all -> 0x0283 }
            if (r6 != 0) goto L_0x0258
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r6 = r6.size()     // Catch:{ all -> 0x0283 }
            if (r6 != 0) goto L_0x0258
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r3
        L_0x019a:
            if (r14 == 0) goto L_0x0236
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.audio_st     // Catch:{ all -> 0x0283 }
            if (r8 == 0) goto L_0x0236
            org.bytedeco.ffmpeg.avcodec.AVPacket r8 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r8 = r8.stream_index()     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avformat.AVStream r9 = r1.audio_st     // Catch:{ all -> 0x0283 }
            int r9 = r9.index()     // Catch:{ all -> 0x0283 }
            if (r8 != r9) goto L_0x0236
            org.bytedeco.ffmpeg.avcodec.AVPacket r8 = r1.pkt2     // Catch:{ all -> 0x0283 }
            int r8 = r8.size()     // Catch:{ all -> 0x0283 }
            if (r8 > 0) goto L_0x01c0
            org.bytedeco.ffmpeg.avcodec.AVPacket r8 = r1.pkt2     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r9 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r10 = r1.sizeof_pkt     // Catch:{ all -> 0x0283 }
            long r10 = (long) r10     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer.memcpy(r8, r9, r10)     // Catch:{ all -> 0x0283 }
        L_0x01c0:
            org.bytedeco.ffmpeg.avutil.AVFrame r8 = r1.samples_frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.global.avutil.av_frame_unref(r8)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r1.audio_c     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r9 = r1.samples_frame     // Catch:{ all -> 0x0283 }
            int[] r10 = r1.got_frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r11 = r1.pkt2     // Catch:{ all -> 0x0283 }
            int r8 = org.bytedeco.ffmpeg.global.avcodec.avcodec_decode_audio4((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r8, (org.bytedeco.ffmpeg.avutil.AVFrame) r9, (int[]) r10, (org.bytedeco.ffmpeg.avcodec.AVPacket) r11)     // Catch:{ all -> 0x0283 }
            if (r8 > 0) goto L_0x01da
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt2     // Catch:{ all -> 0x0283 }
            r6.size(r0)     // Catch:{ all -> 0x0283 }
            goto L_0x0258
        L_0x01da:
            org.bytedeco.ffmpeg.avcodec.AVPacket r9 = r1.pkt2     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer r10 = r9.data()     // Catch:{ all -> 0x0283 }
            long r11 = (long) r8     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer r10 = r10.position((long) r11)     // Catch:{ all -> 0x0283 }
            r9.data(r10)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r9 = r1.pkt2     // Catch:{ all -> 0x0283 }
            int r10 = r9.size()     // Catch:{ all -> 0x0283 }
            int r10 = r10 - r8
            r9.size(r10)     // Catch:{ all -> 0x0283 }
            int[] r8 = r1.got_frame     // Catch:{ all -> 0x0283 }
            r8 = r8[r0]     // Catch:{ all -> 0x0283 }
            if (r8 == 0) goto L_0x0258
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r1.samples_frame     // Catch:{ all -> 0x0283 }
            long r8 = org.bytedeco.ffmpeg.global.avutil.av_frame_get_best_effort_timestamp(r4)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.audio_st     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVRational r4 = r4.time_base()     // Catch:{ all -> 0x0283 }
            long r8 = r8 * r6
            int r6 = r4.num()     // Catch:{ all -> 0x0283 }
            long r6 = (long) r6     // Catch:{ all -> 0x0283 }
            long r8 = r8 * r6
            int r4 = r4.den()     // Catch:{ all -> 0x0283 }
            long r6 = (long) r4     // Catch:{ all -> 0x0283 }
            long r8 = r8 / r6
            r1.timestamp = r8     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            java.nio.Buffer[] r6 = r1.samples_buf     // Catch:{ all -> 0x0283 }
            r4.samples = r6     // Catch:{ all -> 0x0283 }
            if (r16 == 0) goto L_0x0220
            r13.processSamples()     // Catch:{ all -> 0x0283 }
        L_0x0220:
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            long r6 = r1.timestamp     // Catch:{ all -> 0x0283 }
            r4.timestamp = r6     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r1.samples_frame     // Catch:{ all -> 0x0283 }
            int r6 = r6.key_frame()     // Catch:{ all -> 0x0283 }
            if (r6 == 0) goto L_0x0232
            r6 = 1
            goto L_0x0233
        L_0x0232:
            r6 = 0
        L_0x0233:
            r4.keyFrame = r6     // Catch:{ all -> 0x0283 }
            goto L_0x0257
        L_0x0236:
            if (r18 == 0) goto L_0x0258
            org.bytedeco.javacv.Frame r4 = r1.frame     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer r6 = r6.data()     // Catch:{ all -> 0x0283 }
            r7 = 0
            org.bytedeco.javacpp.BytePointer r6 = r6.position((long) r7)     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.avcodec.AVPacket r7 = r1.pkt     // Catch:{ all -> 0x0283 }
            int r7 = r7.size()     // Catch:{ all -> 0x0283 }
            long r7 = (long) r7     // Catch:{ all -> 0x0283 }
            org.bytedeco.javacpp.BytePointer r6 = r6.capacity((long) r7)     // Catch:{ all -> 0x0283 }
            java.nio.ByteBuffer r6 = r6.asByteBuffer()     // Catch:{ all -> 0x0283 }
            r4.data = r6     // Catch:{ all -> 0x0283 }
        L_0x0257:
            r4 = 1
        L_0x0258:
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt2     // Catch:{ all -> 0x0283 }
            int r6 = r6.size()     // Catch:{ all -> 0x0283 }
            if (r6 > 0) goto L_0x00af
            org.bytedeco.ffmpeg.avcodec.AVPacket r6 = r1.pkt     // Catch:{ all -> 0x0283 }
            org.bytedeco.ffmpeg.global.avcodec.av_packet_unref(r6)     // Catch:{ all -> 0x0283 }
            goto L_0x00af
        L_0x0267:
            org.bytedeco.javacv.Frame r0 = r1.frame     // Catch:{ all -> 0x0283 }
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r0
        L_0x026e:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x0283 }
            java.lang.String r3 = "start() was not called successfully!"
            r0.<init>(r3)     // Catch:{ all -> 0x0283 }
            throw r0     // Catch:{ all -> 0x0283 }
        L_0x0276:
            r2.close()     // Catch:{ all -> 0x0292 }
            monitor-exit(r13)
            return r3
        L_0x027b:
            org.bytedeco.javacv.FrameGrabber$Exception r0 = new org.bytedeco.javacv.FrameGrabber$Exception     // Catch:{ all -> 0x0283 }
            java.lang.String r3 = "Could not grab: No AVFormatContext. (Has start() been called?)"
            r0.<init>(r3)     // Catch:{ all -> 0x0283 }
            throw r0     // Catch:{ all -> 0x0283 }
        L_0x0283:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch:{ all -> 0x0286 }
        L_0x0286:
            r0 = move-exception
            r4 = r0
            r2.close()     // Catch:{ all -> 0x028c }
            goto L_0x0291
        L_0x028c:
            r0 = move-exception
            r2 = r0
            r3.addSuppressed(r2)     // Catch:{ all -> 0x0292 }
        L_0x0291:
            throw r4     // Catch:{ all -> 0x0292 }
        L_0x0292:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameGrabber.grabFrame(boolean, boolean, boolean, boolean, boolean):org.bytedeco.javacv.Frame");
    }

    public synchronized AVPacket grabPacket() throws FrameGrabber.Exception {
        AVFormatContext aVFormatContext = this.oc;
        if (aVFormatContext == null || aVFormatContext.isNull()) {
            throw new FrameGrabber.Exception("Could not grab: No AVFormatContext. (Has start() been called?)");
        } else if (!this.started) {
            throw new FrameGrabber.Exception("start() was not called successfully!");
        } else if (avformat.av_read_frame(this.oc, this.pkt) < 0) {
            return null;
        } else {
            return this.pkt;
        }
    }
}
