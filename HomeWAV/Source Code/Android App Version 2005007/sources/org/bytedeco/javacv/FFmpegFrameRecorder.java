package org.bytedeco.javacv;

import com.google.android.exoplayer2.C;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.Buffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVOutputFormat;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avdevice;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.ffmpeg.global.swresample;
import org.bytedeco.ffmpeg.global.swscale;
import org.bytedeco.ffmpeg.swresample.SwrContext;
import org.bytedeco.ffmpeg.swscale.SwsContext;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacv.FrameRecorder;

public class FFmpegFrameRecorder extends FrameRecorder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static FrameRecorder.Exception loadingException;
    static Map<Pointer, OutputStream> outputStreams = Collections.synchronizedMap(new HashMap());
    static SeekCallback seekCallback = ((SeekCallback) new SeekCallback().retainReference());
    static WriteCallback writeCallback = ((WriteCallback) new WriteCallback().retainReference());
    private AVCodecContext audio_c;
    private AVCodec audio_codec;
    private int audio_input_frame_size;
    private BytePointer audio_outbuf;
    private int audio_outbuf_size;
    private AVPacket audio_pkt;
    private AVStream audio_st;
    private AVIOContext avio;
    private boolean closeOutputStream;
    private String filename;
    private AVFrame frame;
    private int[] got_audio_packet;
    private int[] got_video_packet;
    private AVFormatContext ifmt_ctx;
    private SwsContext img_convert_ctx;
    private AVFormatContext oc;
    private AVOutputFormat oformat;
    private OutputStream outputStream;
    private AVFrame picture;
    private BytePointer picture_buf;
    private PointerPointer plane_ptr;
    private PointerPointer plane_ptr2;
    private int samples_channels;
    private SwrContext samples_convert_ctx;
    private int samples_format;
    private Pointer[] samples_in;
    private BytePointer[] samples_out;
    private int samples_rate;
    private volatile boolean started;
    private AVFrame tmp_picture;
    private AVCodecContext video_c;
    private AVCodec video_codec;
    private BytePointer video_outbuf;
    private int video_outbuf_size;
    private AVPacket video_pkt;
    private AVStream video_st;

    public static FFmpegFrameRecorder createDefault(File file, int i, int i2) throws FrameRecorder.Exception {
        return new FFmpegFrameRecorder(file, i, i2);
    }

    public static FFmpegFrameRecorder createDefault(String str, int i, int i2) throws FrameRecorder.Exception {
        return new FFmpegFrameRecorder(str, i, i2);
    }

    public static void tryLoad() throws FrameRecorder.Exception {
        FrameRecorder.Exception exception = loadingException;
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
                if (th instanceof FrameRecorder.Exception) {
                    FrameRecorder.Exception exception2 = th;
                    loadingException = exception2;
                    throw exception2;
                }
                FrameRecorder.Exception exception3 = new FrameRecorder.Exception("Failed to load " + FFmpegFrameRecorder.class, th);
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
        } catch (FrameRecorder.Exception unused) {
        }
    }

    public FFmpegFrameRecorder(File file, int i) {
        this(file, 0, 0, i);
    }

    public FFmpegFrameRecorder(String str, int i) {
        this(str, 0, 0, i);
    }

    public FFmpegFrameRecorder(File file, int i, int i2) {
        this(file, i, i2, 0);
    }

    public FFmpegFrameRecorder(String str, int i, int i2) {
        this(str, i, i2, 0);
    }

    public FFmpegFrameRecorder(File file, int i, int i2, int i3) {
        this(file.getAbsolutePath(), i, i2, i3);
    }

    public FFmpegFrameRecorder(String str, int i, int i2, int i3) {
        this.started = false;
        this.filename = str;
        this.imageWidth = i;
        this.imageHeight = i2;
        this.audioChannels = i3;
        this.pixelFormat = -1;
        this.videoCodec = 0;
        this.videoBitrate = 400000;
        this.frameRate = 30.0d;
        this.sampleFormat = -1;
        this.audioCodec = 0;
        this.audioBitrate = 64000;
        this.sampleRate = 44100;
        this.interleaved = true;
    }

    public FFmpegFrameRecorder(OutputStream outputStream2, int i) {
        this(outputStream2.toString(), i);
        this.outputStream = outputStream2;
        this.closeOutputStream = true;
    }

    public FFmpegFrameRecorder(OutputStream outputStream2, int i, int i2) {
        this(outputStream2.toString(), i, i2);
        this.outputStream = outputStream2;
        this.closeOutputStream = true;
    }

    public FFmpegFrameRecorder(OutputStream outputStream2, int i, int i2, int i3) {
        this(outputStream2.toString(), i, i2, i3);
        this.outputStream = outputStream2;
        this.closeOutputStream = true;
    }

    public void release() throws FrameRecorder.Exception {
        synchronized (avcodec.class) {
            releaseUnsafe();
        }
    }

    public synchronized void releaseUnsafe() throws FrameRecorder.Exception {
        int i = 0;
        this.started = false;
        PointerPointer pointerPointer = this.plane_ptr;
        if (!(pointerPointer == null || this.plane_ptr2 == null)) {
            pointerPointer.releaseReference();
            this.plane_ptr2.releaseReference();
            this.plane_ptr2 = null;
            this.plane_ptr = null;
        }
        AVPacket aVPacket = this.video_pkt;
        if (!(aVPacket == null || this.audio_pkt == null)) {
            aVPacket.releaseReference();
            this.audio_pkt.releaseReference();
            this.audio_pkt = null;
            this.video_pkt = null;
        }
        AVCodecContext aVCodecContext = this.video_c;
        if (aVCodecContext != null) {
            avcodec.avcodec_free_context(aVCodecContext);
            this.video_c = null;
        }
        AVCodecContext aVCodecContext2 = this.audio_c;
        if (aVCodecContext2 != null) {
            avcodec.avcodec_free_context(aVCodecContext2);
            this.audio_c = null;
        }
        BytePointer bytePointer = this.picture_buf;
        if (bytePointer != null) {
            avutil.av_free(bytePointer);
            this.picture_buf = null;
        }
        AVFrame aVFrame = this.picture;
        if (aVFrame != null) {
            avutil.av_frame_free(aVFrame);
            this.picture = null;
        }
        AVFrame aVFrame2 = this.tmp_picture;
        if (aVFrame2 != null) {
            avutil.av_frame_free(aVFrame2);
            this.tmp_picture = null;
        }
        BytePointer bytePointer2 = this.video_outbuf;
        if (bytePointer2 != null) {
            avutil.av_free(bytePointer2);
            this.video_outbuf = null;
        }
        AVFrame aVFrame3 = this.frame;
        if (aVFrame3 != null) {
            avutil.av_frame_free(aVFrame3);
            this.frame = null;
        }
        if (this.samples_in != null) {
            int i2 = 0;
            while (true) {
                Pointer[] pointerArr = this.samples_in;
                if (i2 >= pointerArr.length) {
                    break;
                }
                if (pointerArr[i2] != null) {
                    pointerArr[i2].releaseReference();
                }
                i2++;
            }
            this.samples_in = null;
        }
        if (this.samples_out != null) {
            while (true) {
                BytePointer[] bytePointerArr = this.samples_out;
                if (i >= bytePointerArr.length) {
                    break;
                }
                avutil.av_free(bytePointerArr[i].position(0));
                i++;
            }
            this.samples_out = null;
        }
        BytePointer bytePointer3 = this.audio_outbuf;
        if (bytePointer3 != null) {
            avutil.av_free(bytePointer3);
            this.audio_outbuf = null;
        }
        AVStream aVStream = this.video_st;
        if (!(aVStream == null || aVStream.metadata() == null)) {
            avutil.av_dict_free(this.video_st.metadata());
            this.video_st.metadata((AVDictionary) null);
        }
        AVStream aVStream2 = this.audio_st;
        if (!(aVStream2 == null || aVStream2.metadata() == null)) {
            avutil.av_dict_free(this.audio_st.metadata());
            this.audio_st.metadata((AVDictionary) null);
        }
        this.video_st = null;
        this.audio_st = null;
        this.filename = null;
        AVFormatContext aVFormatContext = this.oc;
        if (aVFormatContext != null && !aVFormatContext.isNull()) {
            if (this.outputStream == null && (this.oformat.flags() & 1) == 0) {
                avformat.avio_close(this.oc.pb());
            }
            avformat.avformat_free_context(this.oc);
            this.oc = null;
        }
        SwsContext swsContext = this.img_convert_ctx;
        if (swsContext != null) {
            swscale.sws_freeContext(swsContext);
            this.img_convert_ctx = null;
        }
        SwrContext swrContext = this.samples_convert_ctx;
        if (swrContext != null) {
            swresample.swr_free(swrContext);
            this.samples_convert_ctx = null;
        }
        OutputStream outputStream2 = this.outputStream;
        if (outputStream2 != null) {
            try {
                if (this.closeOutputStream) {
                    outputStream2.close();
                }
                this.outputStream = null;
                outputStreams.remove(aVFormatContext);
                AVIOContext aVIOContext = this.avio;
                if (aVIOContext != null) {
                    if (aVIOContext.buffer() != null) {
                        avutil.av_free(this.avio.buffer());
                        this.avio.buffer((BytePointer) null);
                    }
                    avutil.av_free(this.avio);
                    this.avio = null;
                }
            } catch (IOException e) {
                throw new FrameRecorder.Exception("Error on OutputStream.close(): ", e);
            } catch (Throwable th) {
                this.outputStream = null;
                outputStreams.remove(aVFormatContext);
                AVIOContext aVIOContext2 = this.avio;
                if (aVIOContext2 != null) {
                    if (aVIOContext2.buffer() != null) {
                        avutil.av_free(this.avio.buffer());
                        this.avio.buffer((BytePointer) null);
                    }
                    avutil.av_free(this.avio);
                    this.avio = null;
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

    static class WriteCallback extends Write_packet_Pointer_BytePointer_int {
        WriteCallback() {
        }

        public int call(Pointer pointer, BytePointer bytePointer, int i) {
            try {
                byte[] bArr = new byte[i];
                bytePointer.get(bArr, 0, i);
                FFmpegFrameRecorder.outputStreams.get(pointer).write(bArr, 0, i);
                return i;
            } catch (Throwable th) {
                PrintStream printStream = System.err;
                printStream.println("Error on OutputStream.write(): " + th);
                return -1;
            }
        }
    }

    static class SeekCallback extends Seek_Pointer_long_int {
        SeekCallback() {
        }

        public long call(Pointer pointer, long j, int i) {
            try {
                ((Seekable) FFmpegFrameRecorder.outputStreams.get(pointer)).seek(j, i);
                return 0;
            } catch (Throwable th) {
                PrintStream printStream = System.err;
                printStream.println("Error on OutputStream.seek(): " + th);
                return -1;
            }
        }
    }

    public boolean isCloseOutputStream() {
        return this.closeOutputStream;
    }

    public void setCloseOutputStream(boolean z) {
        this.closeOutputStream = z;
    }

    public int getFrameNumber() {
        AVFrame aVFrame = this.picture;
        return aVFrame == null ? super.getFrameNumber() : (int) aVFrame.pts();
    }

    public void setFrameNumber(int i) {
        AVFrame aVFrame = this.picture;
        if (aVFrame == null) {
            super.setFrameNumber(i);
        } else {
            aVFrame.pts((long) i);
        }
    }

    public long getTimestamp() {
        return Math.round(((double) (((long) getFrameNumber()) * C.MICROS_PER_SECOND)) / getFrameRate());
    }

    public void setTimestamp(long j) {
        setFrameNumber((int) Math.round((((double) j) * getFrameRate()) / 1000000.0d));
    }

    public void start(AVFormatContext aVFormatContext) throws FrameRecorder.Exception {
        this.ifmt_ctx = aVFormatContext;
        start();
    }

    public void start() throws FrameRecorder.Exception {
        synchronized (avcodec.class) {
            startUnsafe();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094 A[Catch:{ all -> 0x0adf, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00eb A[Catch:{ all -> 0x0adf, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:359:0x0ad7 A[Catch:{ all -> 0x0adf, all -> 0x0ae2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startUnsafe() throws org.bytedeco.javacv.FrameRecorder.Exception {
        /*
            r22 = this;
            r1 = r22
            monitor-enter(r22)
            org.bytedeco.javacpp.PointerScope r2 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x0aee }
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x0aee }
            r2.<init>(r4)     // Catch:{ all -> 0x0aee }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r4 = r1.oc     // Catch:{ all -> 0x0adf }
            if (r4 == 0) goto L_0x001e
            boolean r4 = r4.isNull()     // Catch:{ all -> 0x0adf }
            if (r4 == 0) goto L_0x0016
            goto L_0x001e
        L_0x0016:
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "start() has already been called: Call stop() before calling start() again."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x001e:
            r4 = 0
            r1.picture = r4     // Catch:{ all -> 0x0adf }
            r1.tmp_picture = r4     // Catch:{ all -> 0x0adf }
            r1.picture_buf = r4     // Catch:{ all -> 0x0adf }
            r1.frame = r4     // Catch:{ all -> 0x0adf }
            r1.video_outbuf = r4     // Catch:{ all -> 0x0adf }
            r1.audio_outbuf = r4     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r5 = new org.bytedeco.ffmpeg.avformat.AVFormatContext     // Catch:{ all -> 0x0adf }
            r5.<init>((org.bytedeco.javacpp.Pointer) r4)     // Catch:{ all -> 0x0adf }
            r1.oc = r5     // Catch:{ all -> 0x0adf }
            r1.video_c = r4     // Catch:{ all -> 0x0adf }
            r1.audio_c = r4     // Catch:{ all -> 0x0adf }
            r1.video_st = r4     // Catch:{ all -> 0x0adf }
            r1.audio_st = r4     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.PointerPointer r5 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x0adf }
            r6 = 8
            r5.<init>((long) r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r5 = r5.retainReference()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.PointerPointer r5 = (org.bytedeco.javacpp.PointerPointer) r5     // Catch:{ all -> 0x0adf }
            r1.plane_ptr = r5     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.PointerPointer r5 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x0adf }
            r5.<init>((long) r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r5 = r5.retainReference()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.PointerPointer r5 = (org.bytedeco.javacpp.PointerPointer) r5     // Catch:{ all -> 0x0adf }
            r1.plane_ptr2 = r5     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVPacket r5 = new org.bytedeco.ffmpeg.avcodec.AVPacket     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r5 = r5.retainReference()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVPacket r5 = (org.bytedeco.ffmpeg.avcodec.AVPacket) r5     // Catch:{ all -> 0x0adf }
            r1.video_pkt = r5     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVPacket r5 = new org.bytedeco.ffmpeg.avcodec.AVPacket     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r5 = r5.retainReference()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVPacket r5 = (org.bytedeco.ffmpeg.avcodec.AVPacket) r5     // Catch:{ all -> 0x0adf }
            r1.audio_pkt = r5     // Catch:{ all -> 0x0adf }
            r5 = 1
            int[] r6 = new int[r5]     // Catch:{ all -> 0x0adf }
            r1.got_video_packet = r6     // Catch:{ all -> 0x0adf }
            int[] r6 = new int[r5]     // Catch:{ all -> 0x0adf }
            r1.got_audio_packet = r6     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = r1.format     // Catch:{ all -> 0x0adf }
            if (r6 == 0) goto L_0x0089
            java.lang.String r6 = r1.format     // Catch:{ all -> 0x0adf }
            int r6 = r6.length()     // Catch:{ all -> 0x0adf }
            if (r6 != 0) goto L_0x0086
            goto L_0x0089
        L_0x0086:
            java.lang.String r6 = r1.format     // Catch:{ all -> 0x0adf }
            goto L_0x008a
        L_0x0089:
            r6 = r4
        L_0x008a:
            java.lang.String r7 = r1.filename     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = org.bytedeco.ffmpeg.global.avformat.av_guess_format((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r4)     // Catch:{ all -> 0x0adf }
            r1.oformat = r7     // Catch:{ all -> 0x0adf }
            if (r7 != 0) goto L_0x00d7
            java.lang.String r7 = r1.filename     // Catch:{ all -> 0x0adf }
            java.lang.String r8 = "://"
            int r7 = r7.indexOf(r8)     // Catch:{ all -> 0x0adf }
            if (r7 <= 0) goto L_0x00a4
            java.lang.String r6 = r1.filename     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = r6.substring(r3, r7)     // Catch:{ all -> 0x0adf }
        L_0x00a4:
            java.lang.String r7 = r1.filename     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = org.bytedeco.ffmpeg.global.avformat.av_guess_format((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r4)     // Catch:{ all -> 0x0adf }
            r1.oformat = r6     // Catch:{ all -> 0x0adf }
            if (r6 == 0) goto L_0x00af
            goto L_0x00d7
        L_0x00af:
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "av_guess_format() error: Could not guess output format for \""
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = r1.filename     // Catch:{ all -> 0x0adf }
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "\" and "
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = r1.format     // Catch:{ all -> 0x0adf }
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = " format."
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x00d7:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.BytePointer r6 = r6.name()     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = r6.getString()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            java.lang.String r8 = r1.filename     // Catch:{ all -> 0x0adf }
            int r7 = org.bytedeco.ffmpeg.global.avformat.avformat_alloc_output_context2((org.bytedeco.ffmpeg.avformat.AVFormatContext) r7, (org.bytedeco.ffmpeg.avformat.AVOutputFormat) r4, (java.lang.String) r6, (java.lang.String) r8)     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x0ad7
            java.io.OutputStream r7 = r1.outputStream     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0129
            org.bytedeco.javacpp.BytePointer r8 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x0adf }
            r9 = 4096(0x1000, double:2.0237E-320)
            org.bytedeco.javacpp.Pointer r7 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r9)     // Catch:{ all -> 0x0adf }
            r8.<init>((org.bytedeco.javacpp.Pointer) r7)     // Catch:{ all -> 0x0adf }
            r9 = 4096(0x1000, float:5.74E-42)
            r10 = 1
            org.bytedeco.ffmpeg.avformat.AVFormatContext r11 = r1.oc     // Catch:{ all -> 0x0adf }
            r12 = 0
            org.bytedeco.javacv.FFmpegFrameRecorder$WriteCallback r13 = writeCallback     // Catch:{ all -> 0x0adf }
            java.io.OutputStream r7 = r1.outputStream     // Catch:{ all -> 0x0adf }
            boolean r7 = r7 instanceof org.bytedeco.javacv.Seekable     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x010c
            org.bytedeco.javacv.FFmpegFrameRecorder$SeekCallback r7 = seekCallback     // Catch:{ all -> 0x0adf }
            r14 = r7
            goto L_0x010d
        L_0x010c:
            r14 = r4
        L_0x010d:
            org.bytedeco.ffmpeg.avformat.AVIOContext r7 = org.bytedeco.ffmpeg.global.avformat.avio_alloc_context((org.bytedeco.javacpp.BytePointer) r8, (int) r9, (int) r10, (org.bytedeco.javacpp.Pointer) r11, (org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_BytePointer_int) r12, (org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_BytePointer_int) r13, (org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int) r14)     // Catch:{ all -> 0x0adf }
            r1.avio = r7     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r8 = r1.oc     // Catch:{ all -> 0x0adf }
            r8.pb(r7)     // Catch:{ all -> 0x0adf }
            java.io.OutputStream r7 = r1.outputStream     // Catch:{ all -> 0x0adf }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0adf }
            r1.filename = r7     // Catch:{ all -> 0x0adf }
            java.util.Map<org.bytedeco.javacpp.Pointer, java.io.OutputStream> r7 = outputStreams     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r8 = r1.oc     // Catch:{ all -> 0x0adf }
            java.io.OutputStream r9 = r1.outputStream     // Catch:{ all -> 0x0adf }
            r7.put(r8, r9)     // Catch:{ all -> 0x0adf }
        L_0x0129:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r8 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7.oformat(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.BytePointer r7 = r7.filename()     // Catch:{ all -> 0x0adf }
            java.lang.String r8 = r1.filename     // Catch:{ all -> 0x0adf }
            r7.putString(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            int r8 = r1.maxDelay     // Catch:{ all -> 0x0adf }
            r7.max_delay(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.ifmt_ctx     // Catch:{ all -> 0x0adf }
            r8 = r4
            r9 = r8
            if (r7 == 0) goto L_0x01b2
            r7 = 0
        L_0x0149:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r10 = r1.ifmt_ctx     // Catch:{ all -> 0x0adf }
            int r10 = r10.nb_streams()     // Catch:{ all -> 0x0adf }
            if (r7 >= r10) goto L_0x01b2
            org.bytedeco.ffmpeg.avformat.AVFormatContext r10 = r1.ifmt_ctx     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r10 = r10.streams((int) r7)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r10.codec()     // Catch:{ all -> 0x0adf }
            int r11 = r11.codec_type()     // Catch:{ all -> 0x0adf }
            if (r11 != 0) goto L_0x019a
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r10.codec()     // Catch:{ all -> 0x0adf }
            int r8 = r8.codec_id()     // Catch:{ all -> 0x0adf }
            r1.videoCodec = r8     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r8 = r10.r_frame_rate()     // Catch:{ all -> 0x0adf }
            int r8 = r8.num()     // Catch:{ all -> 0x0adf }
            long r11 = (long) r8     // Catch:{ all -> 0x0adf }
            long r13 = org.bytedeco.ffmpeg.global.avutil.AV_NOPTS_VALUE     // Catch:{ all -> 0x0adf }
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x0198
            org.bytedeco.ffmpeg.avutil.AVRational r8 = r10.r_frame_rate()     // Catch:{ all -> 0x0adf }
            int r8 = r8.den()     // Catch:{ all -> 0x0adf }
            if (r8 == 0) goto L_0x0198
            org.bytedeco.ffmpeg.avutil.AVRational r8 = r10.r_frame_rate()     // Catch:{ all -> 0x0adf }
            int r8 = r8.num()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r11 = r10.r_frame_rate()     // Catch:{ all -> 0x0adf }
            int r11 = r11.den()     // Catch:{ all -> 0x0adf }
            int r8 = r8 / r11
            double r11 = (double) r8     // Catch:{ all -> 0x0adf }
            r1.frameRate = r11     // Catch:{ all -> 0x0adf }
        L_0x0198:
            r8 = r10
            goto L_0x01af
        L_0x019a:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r10.codec()     // Catch:{ all -> 0x0adf }
            int r11 = r11.codec_type()     // Catch:{ all -> 0x0adf }
            if (r11 != r5) goto L_0x01af
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r9 = r10.codec()     // Catch:{ all -> 0x0adf }
            int r9 = r9.codec_id()     // Catch:{ all -> 0x0adf }
            r1.audioCodec = r9     // Catch:{ all -> 0x0adf }
            r9 = r10
        L_0x01af:
            int r7 = r7 + 1
            goto L_0x0149
        L_0x01b2:
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r14 = 1001000(0xf4628, float:1.4027E-39)
            r16 = 0
            r15 = 2
            if (r7 <= 0) goto L_0x04df
            int r7 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            if (r7 <= 0) goto L_0x04df
            int r7 = r1.videoCodec     // Catch:{ all -> 0x0adf }
            r11 = 25
            r10 = 4
            r12 = 12
            if (r7 == 0) goto L_0x01d1
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r13 = r1.videoCodec     // Catch:{ all -> 0x0adf }
            r7.video_codec(r13)     // Catch:{ all -> 0x0adf }
            goto L_0x020a
        L_0x01d1:
            java.lang.String r7 = "flv"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x01e1
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            r13 = 21
            r7.video_codec(r13)     // Catch:{ all -> 0x0adf }
            goto L_0x020a
        L_0x01e1:
            java.lang.String r7 = "mp4"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x01ef
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7.video_codec(r12)     // Catch:{ all -> 0x0adf }
            goto L_0x020a
        L_0x01ef:
            java.lang.String r7 = "3gp"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x01fd
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7.video_codec(r10)     // Catch:{ all -> 0x0adf }
            goto L_0x020a
        L_0x01fd:
            java.lang.String r7 = "avi"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x020a
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7.video_codec(r11)     // Catch:{ all -> 0x0adf }
        L_0x020a:
            java.lang.String r7 = r1.videoCodecName     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_encoder_by_name((java.lang.String) r7)     // Catch:{ all -> 0x0adf }
            r1.video_codec = r7     // Catch:{ all -> 0x0adf }
            if (r7 != 0) goto L_0x022e
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r7 = r7.video_codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_encoder(r7)     // Catch:{ all -> 0x0adf }
            r1.video_codec = r7     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0223
            goto L_0x022e
        L_0x0223:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avcodec_find_encoder() error: Video codec not found."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x022e:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r13 = r1.video_codec     // Catch:{ all -> 0x0adf }
            int r13 = r13.id()     // Catch:{ all -> 0x0adf }
            r7.video_codec(r13)     // Catch:{ all -> 0x0adf }
            double r12 = r1.frameRate     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r12 = org.bytedeco.ffmpeg.global.avutil.av_d2q(r12, r14)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r13 = r1.video_codec     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r13 = r13.supported_framerates()     // Catch:{ all -> 0x0adf }
            if (r13 == 0) goto L_0x0250
            int r12 = org.bytedeco.ffmpeg.global.avutil.av_find_nearest_q_idx(r12, r13)     // Catch:{ all -> 0x0adf }
            long r10 = (long) r12     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r12 = r13.position((long) r10)     // Catch:{ all -> 0x0adf }
        L_0x0250:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r10 = r1.oc     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r10 = org.bytedeco.ffmpeg.global.avformat.avformat_new_stream(r10, r4)     // Catch:{ all -> 0x0adf }
            r1.video_st = r10     // Catch:{ all -> 0x0adf }
            if (r10 == 0) goto L_0x04d4
            org.bytedeco.ffmpeg.avcodec.AVCodec r10 = r1.video_codec     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = org.bytedeco.ffmpeg.global.avcodec.avcodec_alloc_context3(r10)     // Catch:{ all -> 0x0adf }
            r1.video_c = r10     // Catch:{ all -> 0x0adf }
            if (r10 == 0) goto L_0x04c9
            if (r8 == 0) goto L_0x02c7
            org.bytedeco.ffmpeg.avformat.AVStream r10 = r1.video_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r10.codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r8.codec()     // Catch:{ all -> 0x0adf }
            int r10 = org.bytedeco.ffmpeg.global.avcodec.avcodec_copy_context(r10, r11)     // Catch:{ all -> 0x0adf }
            if (r10 < 0) goto L_0x02bc
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r8.codec()     // Catch:{ all -> 0x0adf }
            long r10 = r10.bit_rate()     // Catch:{ all -> 0x0adf }
            int r11 = (int) r10     // Catch:{ all -> 0x0adf }
            r1.videoBitrate = r11     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r8.codec()     // Catch:{ all -> 0x0adf }
            int r10 = r10.pix_fmt()     // Catch:{ all -> 0x0adf }
            r1.pixelFormat = r10     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r8.codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r10 = r10.sample_aspect_ratio()     // Catch:{ all -> 0x0adf }
            int r10 = r10.den()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r8.codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r11 = r11.sample_aspect_ratio()     // Catch:{ all -> 0x0adf }
            int r11 = r11.den()     // Catch:{ all -> 0x0adf }
            int r10 = r10 / r11
            double r10 = (double) r10     // Catch:{ all -> 0x0adf }
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r10 = r10 * r20
            r1.aspectRatio = r10     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r8.codec()     // Catch:{ all -> 0x0adf }
            int r10 = r10.global_quality()     // Catch:{ all -> 0x0adf }
            double r10 = (double) r10     // Catch:{ all -> 0x0adf }
            r1.videoQuality = r10     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            r10.codec_tag(r3)     // Catch:{ all -> 0x0adf }
            goto L_0x02c7
        L_0x02bc:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avcodec_copy_context() error:\tFailed to copy context from input to output stream codec context"
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x02c7:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r11 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r11 = r11.video_codec()     // Catch:{ all -> 0x0adf }
            r10.codec_id(r11)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            r10.codec_type(r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r11 = r1.videoBitrate     // Catch:{ all -> 0x0adf }
            r13 = r8
            long r7 = (long) r11     // Catch:{ all -> 0x0adf }
            r10.bit_rate(r7)     // Catch:{ all -> 0x0adf }
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            int r7 = r7 % r15
            if (r7 != r5) goto L_0x02f7
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            int r7 = r7 + r5
            int r8 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            int r8 = r8 * r7
            int r10 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            int r10 = r10 / r15
            int r8 = r8 + r10
            int r10 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            int r8 = r8 / r10
            r1.imageHeight = r8     // Catch:{ all -> 0x0adf }
            r1.imageWidth = r7     // Catch:{ all -> 0x0adf }
        L_0x02f7:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r7.width(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            r7.height(r8)     // Catch:{ all -> 0x0adf }
            double r7 = r1.aspectRatio     // Catch:{ all -> 0x0adf }
            int r10 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r10 <= 0) goto L_0x031d
            double r7 = r1.aspectRatio     // Catch:{ all -> 0x0adf }
            r10 = 255(0xff, float:3.57E-43)
            org.bytedeco.ffmpeg.avutil.AVRational r7 = org.bytedeco.ffmpeg.global.avutil.av_d2q(r7, r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8.sample_aspect_ratio(r7)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.video_st     // Catch:{ all -> 0x0adf }
            r8.sample_aspect_ratio(r7)     // Catch:{ all -> 0x0adf }
        L_0x031d:
            org.bytedeco.ffmpeg.avutil.AVRational r7 = org.bytedeco.ffmpeg.global.avutil.av_inv_q(r12)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8.time_base(r7)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.video_st     // Catch:{ all -> 0x0adf }
            r8.time_base(r7)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.video_st     // Catch:{ all -> 0x0adf }
            r8.avg_frame_rate(r12)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r8 = r1.video_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r8 = r8.codec()     // Catch:{ all -> 0x0adf }
            r8.time_base(r7)     // Catch:{ all -> 0x0adf }
            int r7 = r1.gopSize     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x0344
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.gopSize     // Catch:{ all -> 0x0adf }
            r7.gop_size(r8)     // Catch:{ all -> 0x0adf }
        L_0x0344:
            double r7 = r1.videoQuality     // Catch:{ all -> 0x0adf }
            int r10 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r10 < 0) goto L_0x0367
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r7.flags()     // Catch:{ all -> 0x0adf }
            r8 = r8 | r15
            r7.flags(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            double r10 = r1.videoQuality     // Catch:{ all -> 0x0adf }
            r18 = 4638003928749834240(0x405d800000000000, double:118.0)
            double r10 = r10 * r18
            long r10 = java.lang.Math.round(r10)     // Catch:{ all -> 0x0adf }
            int r8 = (int) r10     // Catch:{ all -> 0x0adf }
            r7.global_quality(r8)     // Catch:{ all -> 0x0adf }
        L_0x0367:
            int r7 = r1.pixelFormat     // Catch:{ all -> 0x0adf }
            r8 = -1
            if (r7 == r8) goto L_0x0374
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.pixelFormat     // Catch:{ all -> 0x0adf }
            r7.pix_fmt(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x03d7
        L_0x0374:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 13
            if (r7 == r8) goto L_0x03d0
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 61
            if (r7 == r8) goto L_0x03d0
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 25
            if (r7 == r8) goto L_0x03d0
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 33
            if (r7 != r8) goto L_0x039d
            goto L_0x03d0
        L_0x039d:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 11
            if (r7 != r8) goto L_0x03ae
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 3
            r7.pix_fmt(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x03d7
        L_0x03ae:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 7
            if (r7 == r8) goto L_0x03c8
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 8
            if (r7 != r8) goto L_0x03c2
            goto L_0x03c8
        L_0x03c2:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r7.pix_fmt(r3)     // Catch:{ all -> 0x0adf }
            goto L_0x03d7
        L_0x03c8:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 12
            r7.pix_fmt(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x03d7
        L_0x03d0:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_RGB32     // Catch:{ all -> 0x0adf }
            r7.pix_fmt(r8)     // Catch:{ all -> 0x0adf }
        L_0x03d7:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            if (r7 != r15) goto L_0x03e6
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r7.max_b_frames(r15)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x03e6:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            if (r7 != r5) goto L_0x03f5
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r7.mb_decision(r15)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x03f5:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 4
            if (r7 != r8) goto L_0x046e
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r8 = 128(0x80, float:1.794E-43)
            if (r7 > r8) goto L_0x0414
            int r7 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            r10 = 96
            if (r7 > r10) goto L_0x0414
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.width(r8)     // Catch:{ all -> 0x0adf }
            r7.height(r10)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x0414:
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r8 = 176(0xb0, float:2.47E-43)
            if (r7 > r8) goto L_0x042c
            int r7 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            r10 = 144(0x90, float:2.02E-43)
            if (r7 > r10) goto L_0x042c
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.width(r8)     // Catch:{ all -> 0x0adf }
            r8 = 144(0x90, float:2.02E-43)
            r7.height(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x042c:
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r8 = 352(0x160, float:4.93E-43)
            if (r7 > r8) goto L_0x0446
            int r7 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            r8 = 288(0x120, float:4.04E-43)
            if (r7 > r8) goto L_0x0446
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 352(0x160, float:4.93E-43)
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.width(r8)     // Catch:{ all -> 0x0adf }
            r8 = 288(0x120, float:4.04E-43)
            r7.height(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x0446:
            int r7 = r1.imageWidth     // Catch:{ all -> 0x0adf }
            r8 = 704(0x2c0, float:9.87E-43)
            if (r7 > r8) goto L_0x0460
            int r7 = r1.imageHeight     // Catch:{ all -> 0x0adf }
            r8 = 576(0x240, float:8.07E-43)
            if (r7 > r8) goto L_0x0460
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 704(0x2c0, float:9.87E-43)
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.width(r8)     // Catch:{ all -> 0x0adf }
            r8 = 576(0x240, float:8.07E-43)
            r7.height(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x0460:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 1408(0x580, float:1.973E-42)
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.width(r8)     // Catch:{ all -> 0x0adf }
            r8 = 1152(0x480, float:1.614E-42)
            r7.height(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x047f
        L_0x046e:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r7 = r7.codec_id()     // Catch:{ all -> 0x0adf }
            r8 = 27
            if (r7 != r8) goto L_0x047f
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = 578(0x242, float:8.1E-43)
            r7.profile(r8)     // Catch:{ all -> 0x0adf }
        L_0x047f:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r7 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r7 = r7.flags()     // Catch:{ all -> 0x0adf }
            r8 = 64
            r7 = r7 & r8
            if (r7 == 0) goto L_0x0496
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r7.flags()     // Catch:{ all -> 0x0adf }
            r10 = 4194304(0x400000, float:5.877472E-39)
            r8 = r8 | r10
            r7.flags(r8)     // Catch:{ all -> 0x0adf }
        L_0x0496:
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = r1.video_codec     // Catch:{ all -> 0x0adf }
            int r7 = r7.capabilities()     // Catch:{ all -> 0x0adf }
            r7 = r7 & 512(0x200, float:7.175E-43)
            if (r7 == 0) goto L_0x04a6
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            r8 = -2
            r7.strict_std_compliance(r8)     // Catch:{ all -> 0x0adf }
        L_0x04a6:
            int r7 = r1.maxBFrames     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x04bd
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.maxBFrames     // Catch:{ all -> 0x0adf }
            r7.max_b_frames(r8)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.maxBFrames     // Catch:{ all -> 0x0adf }
            if (r8 != 0) goto L_0x04b9
            r8 = 0
            goto L_0x04ba
        L_0x04b9:
            r8 = 1
        L_0x04ba:
            r7.has_b_frames(r8)     // Catch:{ all -> 0x0adf }
        L_0x04bd:
            int r7 = r1.trellis     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x04e0
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r8 = r1.trellis     // Catch:{ all -> 0x0adf }
            r7.trellis(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x04e0
        L_0x04c9:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avcodec_alloc_context3() error: Could not allocate video encoding context."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x04d4:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avformat_new_stream() error: Could not allocate video stream."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x04df:
            r13 = r8
        L_0x04e0:
            int r7 = r1.audioChannels     // Catch:{ all -> 0x0adf }
            r8 = 32
            if (r7 <= 0) goto L_0x0719
            int r7 = r1.audioBitrate     // Catch:{ all -> 0x0adf }
            if (r7 <= 0) goto L_0x0719
            int r7 = r1.sampleRate     // Catch:{ all -> 0x0adf }
            if (r7 <= 0) goto L_0x0719
            int r7 = r1.audioCodec     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x04fa
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r7 = r1.audioCodec     // Catch:{ all -> 0x0adf }
            r6.audio_codec(r7)     // Catch:{ all -> 0x0adf }
            goto L_0x052b
        L_0x04fa:
            java.lang.String r7 = "flv"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 != 0) goto L_0x0523
            java.lang.String r7 = "mp4"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 != 0) goto L_0x0523
            java.lang.String r7 = "3gp"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0513
            goto L_0x0523
        L_0x0513:
            java.lang.String r7 = "avi"
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x0adf }
            if (r6 == 0) goto L_0x052b
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7 = 65536(0x10000, float:9.18355E-41)
            r6.audio_codec(r7)     // Catch:{ all -> 0x0adf }
            goto L_0x052b
        L_0x0523:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            r7 = 86018(0x15002, float:1.20537E-40)
            r6.audio_codec(r7)     // Catch:{ all -> 0x0adf }
        L_0x052b:
            java.lang.String r6 = r1.audioCodecName     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r6 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_encoder_by_name((java.lang.String) r6)     // Catch:{ all -> 0x0adf }
            r1.audio_codec = r6     // Catch:{ all -> 0x0adf }
            if (r6 != 0) goto L_0x054f
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r6 = r6.audio_codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r6 = org.bytedeco.ffmpeg.global.avcodec.avcodec_find_encoder(r6)     // Catch:{ all -> 0x0adf }
            r1.audio_codec = r6     // Catch:{ all -> 0x0adf }
            if (r6 == 0) goto L_0x0544
            goto L_0x054f
        L_0x0544:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avcodec_find_encoder() error: Audio codec not found."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x054f:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = r1.audio_codec     // Catch:{ all -> 0x0adf }
            int r7 = r7.id()     // Catch:{ all -> 0x0adf }
            r6.audio_codec(r7)     // Catch:{ all -> 0x0adf }
            int r6 = r1.sampleRate     // Catch:{ all -> 0x0adf }
            double r6 = (double) r6     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r6 = org.bytedeco.ffmpeg.global.avutil.av_d2q(r6, r14)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = org.bytedeco.ffmpeg.global.avformat.avformat_new_stream(r7, r4)     // Catch:{ all -> 0x0adf }
            r1.audio_st = r7     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x070e
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = r1.audio_codec     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = org.bytedeco.ffmpeg.global.avcodec.avcodec_alloc_context3(r7)     // Catch:{ all -> 0x0adf }
            r1.audio_c = r7     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0703
            if (r9 == 0) goto L_0x060c
            int r7 = r1.audioChannels     // Catch:{ all -> 0x0adf }
            if (r7 <= 0) goto L_0x060c
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r1.audio_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r9.codec()     // Catch:{ all -> 0x0adf }
            int r7 = org.bytedeco.ffmpeg.global.avcodec.avcodec_copy_context(r7, r10)     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x05f0
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r9.codec()     // Catch:{ all -> 0x0adf }
            long r10 = r7.bit_rate()     // Catch:{ all -> 0x0adf }
            int r7 = (int) r10     // Catch:{ all -> 0x0adf }
            r1.audioBitrate = r7     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r9.codec()     // Catch:{ all -> 0x0adf }
            int r7 = r7.sample_rate()     // Catch:{ all -> 0x0adf }
            r1.sampleRate = r7     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r9.codec()     // Catch:{ all -> 0x0adf }
            int r7 = r7.channels()     // Catch:{ all -> 0x0adf }
            r1.audioChannels = r7     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r9.codec()     // Catch:{ all -> 0x0adf }
            int r7 = r7.sample_fmt()     // Catch:{ all -> 0x0adf }
            r1.sampleFormat = r7     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r9.codec()     // Catch:{ all -> 0x0adf }
            int r7 = r7.global_quality()     // Catch:{ all -> 0x0adf }
            double r10 = (double) r7     // Catch:{ all -> 0x0adf }
            r1.audioQuality = r10     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r7.codec_tag(r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r1.audio_st     // Catch:{ all -> 0x0adf }
            long r10 = r9.duration()     // Catch:{ all -> 0x0adf }
            r7.duration(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r1.audio_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r7 = r7.time_base()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r10 = r9.time_base()     // Catch:{ all -> 0x0adf }
            int r10 = r10.num()     // Catch:{ all -> 0x0adf }
            r7.num(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r1.audio_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r7 = r7.time_base()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVRational r10 = r9.time_base()     // Catch:{ all -> 0x0adf }
            int r10 = r10.den()     // Catch:{ all -> 0x0adf }
            r7.den(r10)     // Catch:{ all -> 0x0adf }
            goto L_0x060c
        L_0x05f0:
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "avcodec_copy_context() error "
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            r4.append(r7)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = ": Failed to copy context from input audio to output audio stream codec context\n"
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x060c:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r10 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r10 = r10.audio_codec()     // Catch:{ all -> 0x0adf }
            r7.codec_id(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r7.codec_type(r5)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r1.audioBitrate     // Catch:{ all -> 0x0adf }
            long r10 = (long) r10     // Catch:{ all -> 0x0adf }
            r7.bit_rate(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r1.sampleRate     // Catch:{ all -> 0x0adf }
            r7.sample_rate(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r1.audioChannels     // Catch:{ all -> 0x0adf }
            r7.channels(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r1.audioChannels     // Catch:{ all -> 0x0adf }
            long r10 = org.bytedeco.ffmpeg.global.avutil.av_get_default_channel_layout(r10)     // Catch:{ all -> 0x0adf }
            r7.channel_layout(r10)     // Catch:{ all -> 0x0adf }
            int r7 = r1.sampleFormat     // Catch:{ all -> 0x0adf }
            r10 = -1
            if (r7 == r10) goto L_0x064a
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r1.sampleFormat     // Catch:{ all -> 0x0adf }
            r7.sample_fmt(r10)     // Catch:{ all -> 0x0adf }
            goto L_0x0674
        L_0x064a:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r10 = 8
            r7.sample_fmt(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r1.audio_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r7 = r7.codec()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.IntPointer r7 = r7.sample_fmts()     // Catch:{ all -> 0x0adf }
            r10 = 0
        L_0x065c:
            long r11 = (long) r10     // Catch:{ all -> 0x0adf }
            int r14 = r7.get((long) r11)     // Catch:{ all -> 0x0adf }
            r3 = -1
            if (r14 == r3) goto L_0x0674
            int r11 = r7.get((long) r11)     // Catch:{ all -> 0x0adf }
            if (r11 != r5) goto L_0x0670
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r3.sample_fmt(r5)     // Catch:{ all -> 0x0adf }
            goto L_0x0674
        L_0x0670:
            int r10 = r10 + 1
            r3 = 0
            goto L_0x065c
        L_0x0674:
            org.bytedeco.ffmpeg.avutil.AVRational r3 = org.bytedeco.ffmpeg.global.avutil.av_inv_q(r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r6 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r6.time_base(r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r6 = r1.audio_st     // Catch:{ all -> 0x0adf }
            r6.time_base(r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r6 = r1.audio_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r6 = r6.codec()     // Catch:{ all -> 0x0adf }
            r6.time_base(r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.sample_fmt()     // Catch:{ all -> 0x0adf }
            switch(r3) {
                case 0: goto L_0x06b1;
                case 1: goto L_0x06a9;
                case 2: goto L_0x06a3;
                case 3: goto L_0x069d;
                case 4: goto L_0x0695;
                case 5: goto L_0x06b1;
                case 6: goto L_0x06a9;
                case 7: goto L_0x06a3;
                case 8: goto L_0x069d;
                case 9: goto L_0x0695;
                default: goto L_0x0694;
            }     // Catch:{ all -> 0x0adf }
        L_0x0694:
            goto L_0x06b8
        L_0x0695:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r6 = 64
            r3.bits_per_raw_sample(r6)     // Catch:{ all -> 0x0adf }
            goto L_0x06b8
        L_0x069d:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r3.bits_per_raw_sample(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x06b8
        L_0x06a3:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r3.bits_per_raw_sample(r8)     // Catch:{ all -> 0x0adf }
            goto L_0x06b8
        L_0x06a9:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r6 = 16
            r3.bits_per_raw_sample(r6)     // Catch:{ all -> 0x0adf }
            goto L_0x06b8
        L_0x06b1:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r6 = 8
            r3.bits_per_raw_sample(r6)     // Catch:{ all -> 0x0adf }
        L_0x06b8:
            double r6 = r1.audioQuality     // Catch:{ all -> 0x0adf }
            int r3 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r3 < 0) goto L_0x06db
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r6 = r3.flags()     // Catch:{ all -> 0x0adf }
            r6 = r6 | r15
            r3.flags(r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            double r6 = r1.audioQuality     // Catch:{ all -> 0x0adf }
            r10 = 4638003928749834240(0x405d800000000000, double:118.0)
            double r6 = r6 * r10
            long r6 = java.lang.Math.round(r6)     // Catch:{ all -> 0x0adf }
            int r7 = (int) r6     // Catch:{ all -> 0x0adf }
            r3.global_quality(r7)     // Catch:{ all -> 0x0adf }
        L_0x06db:
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r3 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r3 = r3.flags()     // Catch:{ all -> 0x0adf }
            r6 = 64
            r3 = r3 & r6
            if (r3 == 0) goto L_0x06f2
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r6 = r3.flags()     // Catch:{ all -> 0x0adf }
            r7 = 4194304(0x400000, float:5.877472E-39)
            r6 = r6 | r7
            r3.flags(r6)     // Catch:{ all -> 0x0adf }
        L_0x06f2:
            org.bytedeco.ffmpeg.avcodec.AVCodec r3 = r1.audio_codec     // Catch:{ all -> 0x0adf }
            int r3 = r3.capabilities()     // Catch:{ all -> 0x0adf }
            r3 = r3 & 512(0x200, float:7.175E-43)
            if (r3 == 0) goto L_0x0719
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r6 = -2
            r3.strict_std_compliance(r6)     // Catch:{ all -> 0x0adf }
            goto L_0x0719
        L_0x0703:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avcodec_alloc_context3() error: Could not allocate audio encoding context."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x070e:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avformat_new_stream() error: Could not allocate audio stream."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0719:
            org.bytedeco.ffmpeg.avformat.AVStream r3 = r1.video_st     // Catch:{ all -> 0x0adf }
            r6 = 0
            if (r3 == 0) goto L_0x085e
            if (r13 != 0) goto L_0x085e
            org.bytedeco.ffmpeg.avutil.AVDictionary r3 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            double r10 = r1.videoQuality     // Catch:{ all -> 0x0adf }
            int r12 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r12 < 0) goto L_0x0745
            java.lang.String r10 = "crf"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r11.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = ""
            r11.append(r12)     // Catch:{ all -> 0x0adf }
            double r12 = r1.videoQuality     // Catch:{ all -> 0x0adf }
            r11.append(r12)     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0adf }
            r12 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r10, (java.lang.String) r11, (int) r12)     // Catch:{ all -> 0x0adf }
        L_0x0745:
            java.util.Map r10 = r1.videoOptions     // Catch:{ all -> 0x0adf }
            java.util.Set r10 = r10.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0adf }
        L_0x074f:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0adf }
            if (r11 == 0) goto L_0x076c
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ all -> 0x0adf }
            java.lang.Object r12 = r11.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0adf }
            java.lang.Object r11 = r11.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0adf }
            r13 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r12, (java.lang.String) r11, (int) r13)     // Catch:{ all -> 0x0adf }
            goto L_0x074f
        L_0x076c:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            r11 = 0
            r10.thread_count(r11)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r11 = r1.video_codec     // Catch:{ all -> 0x0adf }
            int r10 = org.bytedeco.ffmpeg.global.avcodec.avcodec_open2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r10, (org.bytedeco.ffmpeg.avcodec.AVCodec) r11, (org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            if (r10 < 0) goto L_0x083c
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            r1.video_outbuf = r4     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0adf }
            r1.picture = r3     // Catch:{ all -> 0x0adf }
            if (r3 == 0) goto L_0x0831
            r3.pts(r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.pix_fmt()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r10 = r10.width()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r11 = r11.height()     // Catch:{ all -> 0x0adf }
            int r3 = org.bytedeco.ffmpeg.global.avutil.av_image_get_buffer_size(r3, r10, r11, r5)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.BytePointer r10 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x0adf }
            long r11 = (long) r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r3 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r11)     // Catch:{ all -> 0x0adf }
            r10.<init>((org.bytedeco.javacpp.Pointer) r3)     // Catch:{ all -> 0x0adf }
            r1.picture_buf = r10     // Catch:{ all -> 0x0adf }
            boolean r3 = r10.isNull()     // Catch:{ all -> 0x0adf }
            if (r3 != 0) goto L_0x0826
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0adf }
            r1.tmp_picture = r3     // Catch:{ all -> 0x0adf }
            if (r3 == 0) goto L_0x081b
            org.bytedeco.ffmpeg.avformat.AVStream r3 = r1.video_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecParameters r3 = r3.codecpar()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.video_c     // Catch:{ all -> 0x0adf }
            int r3 = org.bytedeco.ffmpeg.global.avcodec.avcodec_parameters_from_context(r3, r10)     // Catch:{ all -> 0x0adf }
            if (r3 < 0) goto L_0x07fc
            org.bytedeco.ffmpeg.avutil.AVDictionary r3 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            java.util.Map r10 = r1.videoMetadata     // Catch:{ all -> 0x0adf }
            java.util.Set r10 = r10.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0adf }
        L_0x07d9:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0adf }
            if (r11 == 0) goto L_0x07f6
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ all -> 0x0adf }
            java.lang.Object r12 = r11.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0adf }
            java.lang.Object r11 = r11.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0adf }
            r13 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r12, (java.lang.String) r11, (int) r13)     // Catch:{ all -> 0x0adf }
            goto L_0x07d9
        L_0x07f6:
            org.bytedeco.ffmpeg.avformat.AVStream r10 = r1.video_st     // Catch:{ all -> 0x0adf }
            r10.metadata(r3)     // Catch:{ all -> 0x0adf }
            goto L_0x085e
        L_0x07fc:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r4 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = "avcodec_parameters_from_context() error "
            r5.append(r6)     // Catch:{ all -> 0x0adf }
            r5.append(r3)     // Catch:{ all -> 0x0adf }
            java.lang.String r3 = ": Could not copy the video stream parameters."
            r5.append(r3)     // Catch:{ all -> 0x0adf }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0adf }
            r4.<init>(r3)     // Catch:{ all -> 0x0adf }
            throw r4     // Catch:{ all -> 0x0adf }
        L_0x081b:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "av_frame_alloc() error: Could not allocate temporary picture."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0826:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "av_malloc() error: Could not allocate picture buffer."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0831:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "av_frame_alloc() error: Could not allocate picture."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x083c:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "avcodec_open2() error "
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            r4.append(r10)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = ": Could not open video codec."
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x085e:
            org.bytedeco.ffmpeg.avformat.AVStream r3 = r1.audio_st     // Catch:{ all -> 0x0adf }
            if (r3 == 0) goto L_0x09e3
            if (r9 != 0) goto L_0x09e3
            org.bytedeco.ffmpeg.avutil.AVDictionary r3 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            double r9 = r1.audioQuality     // Catch:{ all -> 0x0adf }
            int r11 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r11 < 0) goto L_0x0888
            java.lang.String r9 = "crf"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r10.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = ""
            r10.append(r11)     // Catch:{ all -> 0x0adf }
            double r11 = r1.audioQuality     // Catch:{ all -> 0x0adf }
            r10.append(r11)     // Catch:{ all -> 0x0adf }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0adf }
            r11 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r9, (java.lang.String) r10, (int) r11)     // Catch:{ all -> 0x0adf }
        L_0x0888:
            java.util.Map r9 = r1.audioOptions     // Catch:{ all -> 0x0adf }
            java.util.Set r9 = r9.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0adf }
        L_0x0892:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0adf }
            if (r10 == 0) goto L_0x08af
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ all -> 0x0adf }
            java.lang.Object r11 = r10.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0adf }
            java.lang.Object r10 = r10.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0adf }
            r12 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r11, (java.lang.String) r10, (int) r12)     // Catch:{ all -> 0x0adf }
            goto L_0x0892
        L_0x08af:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r9 = r1.audio_c     // Catch:{ all -> 0x0adf }
            r10 = 0
            r9.thread_count(r10)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r9 = r1.audio_c     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodec r10 = r1.audio_codec     // Catch:{ all -> 0x0adf }
            int r9 = org.bytedeco.ffmpeg.global.avcodec.avcodec_open2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r9, (org.bytedeco.ffmpeg.avcodec.AVCodec) r10, (org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            if (r9 < 0) goto L_0x09c1
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            r3 = 262144(0x40000, float:3.67342E-40)
            r1.audio_outbuf_size = r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.BytePointer r9 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x0adf }
            long r10 = (long) r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r3 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r10)     // Catch:{ all -> 0x0adf }
            r9.<init>((org.bytedeco.javacpp.Pointer) r3)     // Catch:{ all -> 0x0adf }
            r1.audio_outbuf = r9     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.frame_size()     // Catch:{ all -> 0x0adf }
            if (r3 > r5) goto L_0x08f7
            r3 = 16384(0x4000, float:2.2959E-41)
            r1.audio_outbuf_size = r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r9 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r9 = r9.channels()     // Catch:{ all -> 0x0adf }
            int r3 = r3 / r9
            r1.audio_input_frame_size = r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.codec_id()     // Catch:{ all -> 0x0adf }
            switch(r3) {
                case 65536: goto L_0x08f1;
                case 65537: goto L_0x08f1;
                case 65538: goto L_0x08f1;
                case 65539: goto L_0x08f1;
                default: goto L_0x08f0;
            }     // Catch:{ all -> 0x0adf }
        L_0x08f0:
            goto L_0x08ff
        L_0x08f1:
            int r3 = r1.audio_input_frame_size     // Catch:{ all -> 0x0adf }
            int r3 = r3 >> r5
            r1.audio_input_frame_size = r3     // Catch:{ all -> 0x0adf }
            goto L_0x08ff
        L_0x08f7:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.frame_size()     // Catch:{ all -> 0x0adf }
            r1.audio_input_frame_size = r3     // Catch:{ all -> 0x0adf }
        L_0x08ff:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.sample_fmt()     // Catch:{ all -> 0x0adf }
            int r3 = org.bytedeco.ffmpeg.global.avutil.av_sample_fmt_is_planar(r3)     // Catch:{ all -> 0x0adf }
            if (r3 == 0) goto L_0x0912
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = r3.channels()     // Catch:{ all -> 0x0adf }
            goto L_0x0913
        L_0x0912:
            r3 = 1
        L_0x0913:
            r9 = r4
            org.bytedeco.javacpp.IntPointer r9 = (org.bytedeco.javacpp.IntPointer) r9     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r10 = r10.channels()     // Catch:{ all -> 0x0adf }
            int r11 = r1.audio_input_frame_size     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r12 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r12 = r12.sample_fmt()     // Catch:{ all -> 0x0adf }
            int r9 = org.bytedeco.ffmpeg.global.avutil.av_samples_get_buffer_size((org.bytedeco.javacpp.IntPointer) r9, (int) r10, (int) r11, (int) r12, (int) r5)     // Catch:{ all -> 0x0adf }
            int r9 = r9 / r3
            org.bytedeco.javacpp.BytePointer[] r3 = new org.bytedeco.javacpp.BytePointer[r3]     // Catch:{ all -> 0x0adf }
            r1.samples_out = r3     // Catch:{ all -> 0x0adf }
            r3 = 0
        L_0x092e:
            org.bytedeco.javacpp.BytePointer[] r10 = r1.samples_out     // Catch:{ all -> 0x0adf }
            int r11 = r10.length     // Catch:{ all -> 0x0adf }
            if (r3 >= r11) goto L_0x0946
            org.bytedeco.javacpp.BytePointer r11 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x0adf }
            long r12 = (long) r9     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.Pointer r14 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r12)     // Catch:{ all -> 0x0adf }
            r11.<init>((org.bytedeco.javacpp.Pointer) r14)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacpp.BytePointer r11 = r11.capacity((long) r12)     // Catch:{ all -> 0x0adf }
            r10[r3] = r11     // Catch:{ all -> 0x0adf }
            int r3 = r3 + 1
            goto L_0x092e
        L_0x0946:
            r3 = 8
            org.bytedeco.javacpp.Pointer[] r3 = new org.bytedeco.javacpp.Pointer[r3]     // Catch:{ all -> 0x0adf }
            r1.samples_in = r3     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0adf }
            r1.frame = r3     // Catch:{ all -> 0x0adf }
            if (r3 == 0) goto L_0x09b6
            r3.pts(r6)     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVStream r3 = r1.audio_st     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecParameters r3 = r3.codecpar()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r6 = r1.audio_c     // Catch:{ all -> 0x0adf }
            int r3 = org.bytedeco.ffmpeg.global.avcodec.avcodec_parameters_from_context(r3, r6)     // Catch:{ all -> 0x0adf }
            if (r3 < 0) goto L_0x0997
            org.bytedeco.ffmpeg.avutil.AVDictionary r3 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            java.util.Map r6 = r1.audioMetadata     // Catch:{ all -> 0x0adf }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0adf }
        L_0x0974:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0991
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x0adf }
            java.lang.Object r9 = r7.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0adf }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0adf }
            r10 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r9, (java.lang.String) r7, (int) r10)     // Catch:{ all -> 0x0adf }
            goto L_0x0974
        L_0x0991:
            org.bytedeco.ffmpeg.avformat.AVStream r6 = r1.audio_st     // Catch:{ all -> 0x0adf }
            r6.metadata(r3)     // Catch:{ all -> 0x0adf }
            goto L_0x09e3
        L_0x0997:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r4 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = "avcodec_parameters_from_context() error "
            r5.append(r6)     // Catch:{ all -> 0x0adf }
            r5.append(r3)     // Catch:{ all -> 0x0adf }
            java.lang.String r3 = ": Could not copy the audio stream parameters."
            r5.append(r3)     // Catch:{ all -> 0x0adf }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0adf }
            r4.<init>(r3)     // Catch:{ all -> 0x0adf }
            throw r4     // Catch:{ all -> 0x0adf }
        L_0x09b6:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "av_frame_alloc() error: Could not allocate audio frame."
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x09c1:
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "avcodec_open2() error "
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            r4.append(r9)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = ": Could not open audio codec."
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x09e3:
            org.bytedeco.ffmpeg.avutil.AVDictionary r3 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            java.util.Map r6 = r1.options     // Catch:{ all -> 0x0adf }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0adf }
        L_0x09f2:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0a0f
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x0adf }
            java.lang.Object r9 = r7.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0adf }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0adf }
            r10 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r3, (java.lang.String) r9, (java.lang.String) r7, (int) r10)     // Catch:{ all -> 0x0adf }
            goto L_0x09f2
        L_0x0a0f:
            java.io.OutputStream r6 = r1.outputStream     // Catch:{ all -> 0x0adf }
            if (r6 != 0) goto L_0x0a5b
            org.bytedeco.ffmpeg.avformat.AVOutputFormat r6 = r1.oformat     // Catch:{ all -> 0x0adf }
            int r6 = r6.flags()     // Catch:{ all -> 0x0adf }
            r6 = r6 & r5
            if (r6 != 0) goto L_0x0a5b
            org.bytedeco.ffmpeg.avformat.AVIOContext r6 = new org.bytedeco.ffmpeg.avformat.AVIOContext     // Catch:{ all -> 0x0adf }
            r6.<init>((org.bytedeco.javacpp.Pointer) r4)     // Catch:{ all -> 0x0adf }
            java.lang.String r7 = r1.filename     // Catch:{ all -> 0x0adf }
            int r7 = org.bytedeco.ffmpeg.global.avformat.avio_open2((org.bytedeco.ffmpeg.avformat.AVIOContext) r6, (java.lang.String) r7, (int) r15, (org.bytedeco.ffmpeg.avformat.AVIOInterruptCB) r4, (org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            if (r7 < 0) goto L_0x0a2f
            org.bytedeco.ffmpeg.avformat.AVFormatContext r7 = r1.oc     // Catch:{ all -> 0x0adf }
            r7.pb(r6)     // Catch:{ all -> 0x0adf }
            goto L_0x0a5b
        L_0x0a2f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "avio_open2 error() error "
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            r4.append(r7)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = ": Could not open '"
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = r1.filename     // Catch:{ all -> 0x0adf }
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "'"
            r4.append(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0adf }
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0a5b:
            org.bytedeco.ffmpeg.avutil.AVDictionary r6 = new org.bytedeco.ffmpeg.avutil.AVDictionary     // Catch:{ all -> 0x0adf }
            r6.<init>(r4)     // Catch:{ all -> 0x0adf }
            java.util.Map r4 = r1.metadata     // Catch:{ all -> 0x0adf }
            java.util.Set r4 = r4.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0adf }
        L_0x0a6a:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x0adf }
            if (r7 == 0) goto L_0x0a87
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x0adf }
            java.lang.Object r9 = r7.getKey()     // Catch:{ all -> 0x0adf }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0adf }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x0adf }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0adf }
            r10 = 0
            org.bytedeco.ffmpeg.global.avutil.av_dict_set((org.bytedeco.ffmpeg.avutil.AVDictionary) r6, (java.lang.String) r9, (java.lang.String) r7, (int) r10)     // Catch:{ all -> 0x0adf }
            goto L_0x0a6a
        L_0x0a87:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r4 = r1.oc     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.avformat.AVFormatContext r4 = r4.metadata(r6)     // Catch:{ all -> 0x0adf }
            int r4 = org.bytedeco.ffmpeg.global.avformat.avformat_write_header((org.bytedeco.ffmpeg.avformat.AVFormatContext) r4, (org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            if (r4 < 0) goto L_0x0aab
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            int r3 = org.bytedeco.ffmpeg.global.avutil.av_log_get_level()     // Catch:{ all -> 0x0adf }
            if (r3 < r8) goto L_0x0aa4
            org.bytedeco.ffmpeg.avformat.AVFormatContext r3 = r1.oc     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r1.filename     // Catch:{ all -> 0x0adf }
            r6 = 0
            org.bytedeco.ffmpeg.global.avformat.av_dump_format((org.bytedeco.ffmpeg.avformat.AVFormatContext) r3, (int) r6, (java.lang.String) r4, (int) r5)     // Catch:{ all -> 0x0adf }
        L_0x0aa4:
            r1.started = r5     // Catch:{ all -> 0x0adf }
            r2.close()     // Catch:{ all -> 0x0aee }
            monitor-exit(r22)
            return
        L_0x0aab:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = "avformat_write_header error() error "
            r5.append(r6)     // Catch:{ all -> 0x0adf }
            r5.append(r4)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = ": Could not write header to '"
            r5.append(r4)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r1.filename     // Catch:{ all -> 0x0adf }
            r5.append(r4)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "'"
            r5.append(r4)     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0adf }
            r22.releaseUnsafe()     // Catch:{ all -> 0x0adf }
            org.bytedeco.ffmpeg.global.avutil.av_dict_free((org.bytedeco.ffmpeg.avutil.AVDictionary) r3)     // Catch:{ all -> 0x0adf }
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0ad7:
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0adf }
            java.lang.String r4 = "avformat_alloc_context2() error:\tCould not allocate format context"
            r3.<init>(r4)     // Catch:{ all -> 0x0adf }
            throw r3     // Catch:{ all -> 0x0adf }
        L_0x0adf:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch:{ all -> 0x0ae2 }
        L_0x0ae2:
            r0 = move-exception
            r4 = r0
            r2.close()     // Catch:{ all -> 0x0ae8 }
            goto L_0x0aed
        L_0x0ae8:
            r0 = move-exception
            r2 = r0
            r3.addSuppressed(r2)     // Catch:{ all -> 0x0aee }
        L_0x0aed:
            throw r4     // Catch:{ all -> 0x0aee }
        L_0x0aee:
            r0 = move-exception
            r2 = r0
            monitor-exit(r22)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameRecorder.startUnsafe():void");
    }

    public synchronized void flush() throws FrameRecorder.Exception {
        synchronized (this.oc) {
            while (this.video_st != null && this.ifmt_ctx == null) {
                if (!recordImage(0, 0, 0, 0, 0, -1, (Buffer[]) null)) {
                    break;
                }
            }
            while (this.audio_st != null && this.ifmt_ctx == null && recordSamples(0, 0, (Buffer[]) null)) {
            }
            if (!this.interleaved || (this.video_st == null && this.audio_st == null)) {
                avformat.av_write_frame(this.oc, (AVPacket) null);
            } else {
                avformat.av_interleaved_write_frame(this.oc, (AVPacket) null);
            }
            avformat.av_write_trailer(this.oc);
        }
    }

    public void stop() throws FrameRecorder.Exception {
        if (this.oc != null) {
            try {
                flush();
            } finally {
                release();
            }
        }
    }

    public void record(Frame frame2) throws FrameRecorder.Exception {
        record(frame2, frame2.opaque instanceof AVFrame ? ((AVFrame) frame2.opaque).format() : -1);
    }

    public synchronized void record(Frame frame2, int i) throws FrameRecorder.Exception {
        if (frame2 != null) {
            if (frame2.image != null || frame2.samples != null) {
                if (frame2.image != null) {
                    frame2.keyFrame = recordImage(frame2.imageWidth, frame2.imageHeight, frame2.imageDepth, frame2.imageChannels, frame2.imageStride, i, frame2.image);
                }
                if (frame2.samples != null) {
                    frame2.keyFrame = recordSamples(frame2.sampleRate, frame2.audioChannels, frame2.samples);
                }
            }
        }
        recordImage(0, 0, 0, 0, 0, i, (Buffer[]) null);
    }

    /* JADX WARNING: type inference failed for: r15v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r15v2 */
    /* JADX WARNING: type inference failed for: r15v3 */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02bc, code lost:
        if (r1.got_video_packet[r15] != 0) goto L_0x02be;
     */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02cb A[SYNTHETIC, Splitter:B:121:0x02cb] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x022e A[Catch:{ all -> 0x02fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x023f A[Catch:{ all -> 0x02fb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean recordImage(int r32, int r33, int r34, int r35, int r36, int r37, java.nio.Buffer... r38) throws org.bytedeco.javacv.FrameRecorder.Exception {
        /*
            r31 = this;
            r1 = r31
            r0 = r32
            r13 = r33
            r2 = r34
            r3 = r35
            r14 = r38
            monitor-enter(r31)
            org.bytedeco.javacpp.PointerScope r15 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x030e }
            r12 = 0
            java.lang.Class[] r4 = new java.lang.Class[r12]     // Catch:{ all -> 0x030e }
            r15.<init>(r4)     // Catch:{ all -> 0x030e }
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.video_st     // Catch:{ all -> 0x02fd }
            if (r4 == 0) goto L_0x02f1
            boolean r4 = r1.started     // Catch:{ all -> 0x02fd }
            if (r4 == 0) goto L_0x02e7
            r16 = 0
            r11 = 1
            if (r14 == 0) goto L_0x0205
            int r4 = r14.length     // Catch:{ all -> 0x02fd }
            if (r4 != 0) goto L_0x0027
            goto L_0x0205
        L_0x0027:
            int r4 = java.lang.Math.abs(r34)     // Catch:{ all -> 0x02fd }
            int r4 = r4 * r36
            r5 = 8
            int r4 = r4 / r5
            r6 = r14[r12]     // Catch:{ all -> 0x02fd }
            boolean r6 = r6 instanceof java.nio.ByteBuffer     // Catch:{ all -> 0x02fd }
            r7 = 0
            if (r6 == 0) goto L_0x004c
            org.bytedeco.javacpp.BytePointer r6 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x0046 }
            r9 = r14[r12]     // Catch:{ all -> 0x0046 }
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9     // Catch:{ all -> 0x0046 }
            r6.<init>((java.nio.ByteBuffer) r9)     // Catch:{ all -> 0x0046 }
            org.bytedeco.javacpp.BytePointer r6 = r6.position((long) r7)     // Catch:{ all -> 0x0046 }
            goto L_0x005c
        L_0x0046:
            r0 = move-exception
            r2 = r0
            r19 = r15
            goto L_0x0301
        L_0x004c:
            org.bytedeco.javacpp.BytePointer r6 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x02fd }
            org.bytedeco.javacpp.Pointer r9 = new org.bytedeco.javacpp.Pointer     // Catch:{ all -> 0x02fd }
            r10 = r14[r12]     // Catch:{ all -> 0x02fd }
            r9.<init>((java.nio.Buffer) r10)     // Catch:{ all -> 0x02fd }
            org.bytedeco.javacpp.Pointer r7 = r9.position(r7)     // Catch:{ all -> 0x02fd }
            r6.<init>((org.bytedeco.javacpp.Pointer) r7)     // Catch:{ all -> 0x02fd }
        L_0x005c:
            r17 = r6
            r6 = -1
            r7 = 2
            r8 = 3
            r9 = 24
            r10 = r37
            if (r10 != r6) goto L_0x00ca
            r6 = -8
            if (r2 == r5) goto L_0x006c
            if (r2 != r6) goto L_0x0070
        L_0x006c:
            if (r3 != r8) goto L_0x0070
            r10 = 3
            goto L_0x00ca
        L_0x0070:
            if (r2 == r5) goto L_0x0074
            if (r2 != r6) goto L_0x0079
        L_0x0074:
            if (r3 != r11) goto L_0x0079
            r10 = 8
            goto L_0x00ca
        L_0x0079:
            r8 = 16
            if (r2 == r8) goto L_0x0081
            r8 = -16
            if (r2 != r8) goto L_0x0096
        L_0x0081:
            if (r3 != r11) goto L_0x0096
            java.nio.ByteOrder r2 = java.nio.ByteOrder.nativeOrder()     // Catch:{ all -> 0x0046 }
            java.nio.ByteOrder r3 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x0046 }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x0046 }
            if (r2 == 0) goto L_0x0092
            r2 = 29
            goto L_0x0094
        L_0x0092:
            r2 = 30
        L_0x0094:
            r10 = r2
            goto L_0x00ca
        L_0x0096:
            if (r2 == r5) goto L_0x009a
            if (r2 != r6) goto L_0x00a2
        L_0x009a:
            r8 = 4
            if (r3 != r8) goto L_0x00a2
            r2 = 26
            r10 = 26
            goto L_0x00ca
        L_0x00a2:
            if (r2 == r5) goto L_0x00a6
            if (r2 != r6) goto L_0x00ab
        L_0x00a6:
            if (r3 != r7) goto L_0x00ab
            r10 = 24
            goto L_0x00ca
        L_0x00ab:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x0046 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r4.<init>()     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "Could not guess pixel format of image: depth="
            r4.append(r5)     // Catch:{ all -> 0x0046 }
            r4.append(r2)     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = ", channels="
            r4.append(r2)     // Catch:{ all -> 0x0046 }
            r4.append(r3)     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0046 }
            r0.<init>(r2)     // Catch:{ all -> 0x0046 }
            throw r0     // Catch:{ all -> 0x0046 }
        L_0x00ca:
            if (r10 != r9) goto L_0x00ce
            r9 = r0
            goto L_0x00cf
        L_0x00ce:
            r9 = r4
        L_0x00cf:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x02fd }
            int r2 = r2.pix_fmt()     // Catch:{ all -> 0x02fd }
            if (r2 != r10) goto L_0x0116
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x0046 }
            int r2 = r2.width()     // Catch:{ all -> 0x0046 }
            if (r2 != r0) goto L_0x0116
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x0046 }
            int r2 = r2.height()     // Catch:{ all -> 0x0046 }
            if (r2 == r13) goto L_0x00e8
            goto L_0x0116
        L_0x00e8:
            org.bytedeco.javacpp.PointerPointer r2 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.picture     // Catch:{ all -> 0x0046 }
            r2.<init>((org.bytedeco.javacpp.Pointer) r3)     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.picture     // Catch:{ all -> 0x0046 }
            org.bytedeco.javacpp.IntPointer r3 = r3.linesize()     // Catch:{ all -> 0x0046 }
            r8 = 1
            r4 = r17
            r5 = r10
            r6 = r32
            r7 = r33
            org.bytedeco.ffmpeg.global.avutil.av_image_fill_arrays((org.bytedeco.javacpp.PointerPointer) r2, (org.bytedeco.javacpp.IntPointer) r3, (org.bytedeco.javacpp.BytePointer) r4, (int) r5, (int) r6, (int) r7, (int) r8)     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.picture     // Catch:{ all -> 0x0046 }
            r2.linesize(r12, r9)     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.picture     // Catch:{ all -> 0x0046 }
            r2.format(r10)     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.picture     // Catch:{ all -> 0x0046 }
            r2.width(r0)     // Catch:{ all -> 0x0046 }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x0046 }
            r0.height(r13)     // Catch:{ all -> 0x0046 }
            goto L_0x0205
        L_0x0116:
            org.bytedeco.ffmpeg.swscale.SwsContext r2 = r1.img_convert_ctx     // Catch:{ all -> 0x02fd }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.video_c     // Catch:{ all -> 0x02fd }
            int r6 = r3.width()     // Catch:{ all -> 0x02fd }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.video_c     // Catch:{ all -> 0x02fd }
            int r8 = r3.height()     // Catch:{ all -> 0x02fd }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r3 = r1.video_c     // Catch:{ all -> 0x02fd }
            int r18 = r3.pix_fmt()     // Catch:{ all -> 0x02fd }
            int r3 = r1.imageScalingFlags     // Catch:{ all -> 0x02fd }
            if (r3 == 0) goto L_0x0133
            int r3 = r1.imageScalingFlags     // Catch:{ all -> 0x0046 }
            r19 = r3
            goto L_0x0135
        L_0x0133:
            r19 = 2
        L_0x0135:
            r20 = 0
            r21 = 0
            r22 = r16
            org.bytedeco.javacpp.DoublePointer r22 = (org.bytedeco.javacpp.DoublePointer) r22     // Catch:{ all -> 0x02fd }
            r3 = r32
            r4 = r33
            r5 = r10
            r7 = r8
            r8 = r18
            r23 = r9
            r9 = r19
            r34 = r10
            r10 = r20
            r18 = 1
            r11 = r21
            r19 = r15
            r15 = 0
            r12 = r22
            org.bytedeco.ffmpeg.swscale.SwsContext r2 = org.bytedeco.ffmpeg.global.swscale.sws_getCachedContext((org.bytedeco.ffmpeg.swscale.SwsContext) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (org.bytedeco.ffmpeg.swscale.SwsFilter) r10, (org.bytedeco.ffmpeg.swscale.SwsFilter) r11, (org.bytedeco.javacpp.DoublePointer) r12)     // Catch:{ all -> 0x02fb }
            r1.img_convert_ctx = r2     // Catch:{ all -> 0x02fb }
            if (r2 == 0) goto L_0x01fd
            org.bytedeco.javacpp.PointerPointer r2 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r2.<init>((org.bytedeco.javacpp.Pointer) r3)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.IntPointer r3 = r3.linesize()     // Catch:{ all -> 0x02fb }
            r8 = 1
            r4 = r17
            r5 = r34
            r6 = r32
            r7 = r33
            org.bytedeco.ffmpeg.global.avutil.av_image_fill_arrays((org.bytedeco.javacpp.PointerPointer) r2, (org.bytedeco.javacpp.IntPointer) r3, (org.bytedeco.javacpp.BytePointer) r4, (int) r5, (int) r6, (int) r7, (int) r8)     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.PointerPointer r2 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.picture     // Catch:{ all -> 0x02fb }
            r2.<init>((org.bytedeco.javacpp.Pointer) r3)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.IntPointer r25 = r3.linesize()     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.BytePointer r3 = r1.picture_buf     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r27 = r4.pix_fmt()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r28 = r4.width()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r29 = r4.height()     // Catch:{ all -> 0x02fb }
            r30 = 1
            r24 = r2
            r26 = r3
            org.bytedeco.ffmpeg.global.avutil.av_image_fill_arrays((org.bytedeco.javacpp.PointerPointer) r24, (org.bytedeco.javacpp.IntPointer) r25, (org.bytedeco.javacpp.BytePointer) r26, (int) r27, (int) r28, (int) r29, (int) r30)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r4 = r23
            r2.linesize(r15, r4)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r8 = r34
            r2.format(r8)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r2.width(r0)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r0.height(r13)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r2 = r2.pix_fmt()     // Catch:{ all -> 0x02fb }
            r0.format(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r2 = r2.width()     // Catch:{ all -> 0x02fb }
            r0.width(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r2 = r2.height()     // Catch:{ all -> 0x02fb }
            r0.height(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.swscale.SwsContext r2 = r1.img_convert_ctx     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.PointerPointer r3 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            r3.<init>((org.bytedeco.javacpp.Pointer) r0)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.tmp_picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.IntPointer r4 = r0.linesize()     // Catch:{ all -> 0x02fb }
            r5 = 0
            org.bytedeco.javacpp.PointerPointer r7 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            r7.<init>((org.bytedeco.javacpp.Pointer) r0)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.IntPointer r8 = r0.linesize()     // Catch:{ all -> 0x02fb }
            r6 = r33
            org.bytedeco.ffmpeg.global.swscale.sws_scale((org.bytedeco.ffmpeg.swscale.SwsContext) r2, (org.bytedeco.javacpp.PointerPointer) r3, (org.bytedeco.javacpp.IntPointer) r4, (int) r5, (int) r6, (org.bytedeco.javacpp.PointerPointer) r7, (org.bytedeco.javacpp.IntPointer) r8)     // Catch:{ all -> 0x02fb }
            goto L_0x020a
        L_0x01fd:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x02fb }
            java.lang.String r2 = "sws_getCachedContext() error: Cannot initialize the conversion context."
            r0.<init>(r2)     // Catch:{ all -> 0x02fb }
            throw r0     // Catch:{ all -> 0x02fb }
        L_0x0205:
            r19 = r15
            r15 = 0
            r18 = 1
        L_0x020a:
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.global.avcodec.av_init_packet(r0)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            org.bytedeco.javacpp.BytePointer r2 = r1.video_outbuf     // Catch:{ all -> 0x02fb }
            r0.data(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            int r2 = r1.video_outbuf_size     // Catch:{ all -> 0x02fb }
            r0.size(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.video_c     // Catch:{ all -> 0x02fb }
            int r2 = r2.global_quality()     // Catch:{ all -> 0x02fb }
            r0.quality(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.video_c     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVPacket r2 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            if (r14 == 0) goto L_0x0235
            int r3 = r14.length     // Catch:{ all -> 0x02fb }
            if (r3 != 0) goto L_0x0232
            goto L_0x0235
        L_0x0232:
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r1.picture     // Catch:{ all -> 0x02fb }
            goto L_0x0237
        L_0x0235:
            r3 = r16
        L_0x0237:
            int[] r4 = r1.got_video_packet     // Catch:{ all -> 0x02fb }
            int r0 = org.bytedeco.ffmpeg.global.avcodec.avcodec_encode_video2((org.bytedeco.ffmpeg.avcodec.AVCodecContext) r0, (org.bytedeco.ffmpeg.avcodec.AVPacket) r2, (org.bytedeco.ffmpeg.avutil.AVFrame) r3, (int[]) r4)     // Catch:{ all -> 0x02fb }
            if (r0 < 0) goto L_0x02cb
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.picture     // Catch:{ all -> 0x02fb }
            long r2 = r0.pts()     // Catch:{ all -> 0x02fb }
            r4 = 1
            long r2 = r2 + r4
            r0.pts(r2)     // Catch:{ all -> 0x02fb }
            int[] r0 = r1.got_video_packet     // Catch:{ all -> 0x02fb }
            r0 = r0[r15]     // Catch:{ all -> 0x02fb }
            if (r0 == 0) goto L_0x02c6
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            long r2 = r0.pts()     // Catch:{ all -> 0x02fb }
            long r4 = org.bytedeco.ffmpeg.global.avutil.AV_NOPTS_VALUE     // Catch:{ all -> 0x02fb }
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0276
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            long r2 = r0.pts()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = r1.video_c     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVRational r4 = r4.time_base()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avformat.AVStream r5 = r1.video_st     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVRational r5 = r5.time_base()     // Catch:{ all -> 0x02fb }
            long r2 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q(r2, r4, r5)     // Catch:{ all -> 0x02fb }
            r0.pts(r2)     // Catch:{ all -> 0x02fb }
        L_0x0276:
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            long r2 = r0.dts()     // Catch:{ all -> 0x02fb }
            long r4 = org.bytedeco.ffmpeg.global.avutil.AV_NOPTS_VALUE     // Catch:{ all -> 0x02fb }
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x029b
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            long r2 = r0.dts()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r4 = r1.video_c     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVRational r4 = r4.time_base()     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avformat.AVStream r5 = r1.video_st     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avutil.AVRational r5 = r5.time_base()     // Catch:{ all -> 0x02fb }
            long r2 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q(r2, r4, r5)     // Catch:{ all -> 0x02fb }
            r0.dts(r2)     // Catch:{ all -> 0x02fb }
        L_0x029b:
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avformat.AVStream r2 = r1.video_st     // Catch:{ all -> 0x02fb }
            int r2 = r2.index()     // Catch:{ all -> 0x02fb }
            r0.stream_index(r2)     // Catch:{ all -> 0x02fb }
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            r1.writePacket(r15, r0)     // Catch:{ all -> 0x02fb }
            if (r14 == 0) goto L_0x02b8
            org.bytedeco.ffmpeg.avcodec.AVPacket r0 = r1.video_pkt     // Catch:{ all -> 0x02fb }
            int r0 = r0.flags()     // Catch:{ all -> 0x02fb }
            r0 = r0 & 1
            if (r0 == 0) goto L_0x02c0
            goto L_0x02be
        L_0x02b8:
            int[] r0 = r1.got_video_packet     // Catch:{ all -> 0x02fb }
            r0 = r0[r15]     // Catch:{ all -> 0x02fb }
            if (r0 == 0) goto L_0x02c0
        L_0x02be:
            r12 = 1
            goto L_0x02c1
        L_0x02c0:
            r12 = 0
        L_0x02c1:
            r19.close()     // Catch:{ all -> 0x030e }
            monitor-exit(r31)
            return r12
        L_0x02c6:
            r19.close()     // Catch:{ all -> 0x030e }
            monitor-exit(r31)
            return r15
        L_0x02cb:
            org.bytedeco.javacv.FrameRecorder$Exception r2 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x02fb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fb }
            r3.<init>()     // Catch:{ all -> 0x02fb }
            java.lang.String r4 = "avcodec_encode_video2() error "
            r3.append(r4)     // Catch:{ all -> 0x02fb }
            r3.append(r0)     // Catch:{ all -> 0x02fb }
            java.lang.String r0 = ": Could not encode video packet."
            r3.append(r0)     // Catch:{ all -> 0x02fb }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x02fb }
            r2.<init>(r0)     // Catch:{ all -> 0x02fb }
            throw r2     // Catch:{ all -> 0x02fb }
        L_0x02e7:
            r19 = r15
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x02fb }
            java.lang.String r2 = "start() was not called successfully!"
            r0.<init>(r2)     // Catch:{ all -> 0x02fb }
            throw r0     // Catch:{ all -> 0x02fb }
        L_0x02f1:
            r19 = r15
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x02fb }
            java.lang.String r2 = "No video output stream (Is imageWidth > 0 && imageHeight > 0 and has start() been called?)"
            r0.<init>(r2)     // Catch:{ all -> 0x02fb }
            throw r0     // Catch:{ all -> 0x02fb }
        L_0x02fb:
            r0 = move-exception
            goto L_0x0300
        L_0x02fd:
            r0 = move-exception
            r19 = r15
        L_0x0300:
            r2 = r0
        L_0x0301:
            throw r2     // Catch:{ all -> 0x0302 }
        L_0x0302:
            r0 = move-exception
            r3 = r0
            r19.close()     // Catch:{ all -> 0x0308 }
            goto L_0x030d
        L_0x0308:
            r0 = move-exception
            r4 = r0
            r2.addSuppressed(r4)     // Catch:{ all -> 0x030e }
        L_0x030d:
            throw r3     // Catch:{ all -> 0x030e }
        L_0x030e:
            r0 = move-exception
            monitor-exit(r31)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameRecorder.recordImage(int, int, int, int, int, int, java.nio.Buffer[]):boolean");
    }

    public boolean recordSamples(Buffer... bufferArr) throws FrameRecorder.Exception {
        return recordSamples(0, 0, bufferArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:192:0x03d1, code lost:
        if (r0 == null) goto L_0x03df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03d9, code lost:
        if (r1.frame.key_frame() == 0) goto L_0x03dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03db, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x03dd, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x03df, code lost:
        r3 = record((org.bytedeco.ffmpeg.avutil.AVFrame) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x03eb, code lost:
        return r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0323 A[Catch:{ all -> 0x04ac, all -> 0x04af }, LOOP:5: B:173:0x031e->B:176:0x0323, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x034a A[Catch:{ all -> 0x04ac, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0362 A[Catch:{ all -> 0x04ac, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x03a5 A[Catch:{ all -> 0x04ac, all -> 0x04af }, LOOP:7: B:181:0x03a0->B:184:0x03a5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x03b8 A[Catch:{ all -> 0x04ac, all -> 0x04af }, LOOP:8: B:186:0x03b3->B:188:0x03b8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x03cf A[Catch:{ all -> 0x04ac, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0445 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x03c3 A[EDGE_INSN: B:270:0x03c3->B:189:0x03c3 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean recordSamples(int r28, int r29, java.nio.Buffer... r30) throws org.bytedeco.javacv.FrameRecorder.Exception {
        /*
            r27 = this;
            r1 = r27
            r0 = r30
            monitor-enter(r27)
            org.bytedeco.javacpp.PointerScope r2 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x04bb }
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x04bb }
            r2.<init>(r4)     // Catch:{ all -> 0x04bb }
            org.bytedeco.ffmpeg.avformat.AVStream r4 = r1.audio_st     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x04a4
            boolean r4 = r1.started     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x049c
            r4 = 0
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r7 = 0
            if (r0 != 0) goto L_0x005c
            org.bytedeco.javacpp.BytePointer[] r9 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r9 = r9[r3]     // Catch:{ all -> 0x04ac }
            long r9 = r9.position()     // Catch:{ all -> 0x04ac }
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 <= 0) goto L_0x005c
            org.bytedeco.javacpp.BytePointer[] r0 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r0 = r0[r3]     // Catch:{ all -> 0x04ac }
            long r7 = r0.limit()     // Catch:{ all -> 0x04ac }
            long r5 = java.lang.Math.min(r7, r5)     // Catch:{ all -> 0x04ac }
            int r0 = (int) r5     // Catch:{ all -> 0x04ac }
            int r5 = r1.audio_input_frame_size     // Catch:{ all -> 0x04ac }
            int r0 = r0 / r5
            double r5 = (double) r0     // Catch:{ all -> 0x04ac }
            double r5 = java.lang.Math.floor(r5)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer[] r0 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r0 = r0[r3]     // Catch:{ all -> 0x04ac }
            long r7 = r0.position()     // Catch:{ all -> 0x04ac }
            int r0 = (int) r7     // Catch:{ all -> 0x04ac }
            double r7 = (double) r0     // Catch:{ all -> 0x04ac }
            double r7 = r7 / r5
            double r5 = java.lang.Math.floor(r7)     // Catch:{ all -> 0x04ac }
            int r0 = (int) r5     // Catch:{ all -> 0x04ac }
            r1.writeSamples(r0)     // Catch:{ all -> 0x04ac }
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = (org.bytedeco.ffmpeg.avutil.AVFrame) r4     // Catch:{ all -> 0x04ac }
            boolean r0 = r1.record((org.bytedeco.ffmpeg.avutil.AVFrame) r4)     // Catch:{ all -> 0x04ac }
            r2.close()     // Catch:{ all -> 0x04bb }
            monitor-exit(r27)
            return r0
        L_0x005c:
            if (r28 > 0) goto L_0x0065
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r9 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r9 = r9.sample_rate()     // Catch:{ all -> 0x04ac }
            goto L_0x0067
        L_0x0065:
            r9 = r28
        L_0x0067:
            if (r29 > 0) goto L_0x0071
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r10 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r10 = r10.channels()     // Catch:{ all -> 0x04ac }
            r15 = r10
            goto L_0x0073
        L_0x0071:
            r15 = r29
        L_0x0073:
            if (r0 == 0) goto L_0x0084
            r10 = r0[r3]     // Catch:{ all -> 0x04ac }
            int r10 = r10.limit()     // Catch:{ all -> 0x04ac }
            r11 = r0[r3]     // Catch:{ all -> 0x04ac }
            int r11 = r11.position()     // Catch:{ all -> 0x04ac }
            int r10 = r10 - r11
            r14 = r10
            goto L_0x0085
        L_0x0084:
            r14 = 0
        L_0x0085:
            int r10 = r1.samples_format     // Catch:{ all -> 0x04ac }
            r13 = 1
            if (r0 == 0) goto L_0x0090
            int r11 = r0.length     // Catch:{ all -> 0x04ac }
            if (r11 <= r13) goto L_0x0090
            r21 = 1
            goto L_0x0092
        L_0x0090:
            r21 = r15
        L_0x0092:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r16 = r11.sample_fmt()     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer[] r11 = r1.samples_out     // Catch:{ all -> 0x04ac }
            int r11 = r11.length     // Catch:{ all -> 0x04ac }
            if (r11 <= r13) goto L_0x00a0
            r22 = 1
            goto L_0x00a8
        L_0x00a0:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r11 = r11.channels()     // Catch:{ all -> 0x04ac }
            r22 = r11
        L_0x00a8:
            int r23 = org.bytedeco.ffmpeg.global.avutil.av_get_bytes_per_sample(r16)     // Catch:{ all -> 0x04ac }
            r17 = 4
            if (r0 == 0) goto L_0x011c
            r11 = r0[r3]     // Catch:{ all -> 0x04ac }
            boolean r11 = r11 instanceof java.nio.ByteBuffer     // Catch:{ all -> 0x04ac }
            if (r11 == 0) goto L_0x011c
            int r10 = r0.length     // Catch:{ all -> 0x04ac }
            if (r10 <= r13) goto L_0x00bb
            r10 = 5
            goto L_0x00bc
        L_0x00bb:
            r10 = 0
        L_0x00bc:
            r11 = 0
        L_0x00bd:
            int r12 = r0.length     // Catch:{ all -> 0x04ac }
            if (r11 >= r12) goto L_0x0116
            r12 = r0[r11]     // Catch:{ all -> 0x04ac }
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r4 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r5 = r4[r11]     // Catch:{ all -> 0x04ac }
            boolean r5 = r5 instanceof org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x04ac }
            if (r5 == 0) goto L_0x00f5
            r4 = r4[r11]     // Catch:{ all -> 0x04ac }
            long r4 = r4.capacity()     // Catch:{ all -> 0x04ac }
            long r7 = (long) r14     // Catch:{ all -> 0x04ac }
            int r6 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x00f5
            boolean r4 = r12.hasArray()     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x00f5
            org.bytedeco.javacpp.Pointer[] r4 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r4 = r4[r11]     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer r4 = (org.bytedeco.javacpp.BytePointer) r4     // Catch:{ all -> 0x04ac }
            r5 = 0
            org.bytedeco.javacpp.BytePointer r4 = r4.position((long) r5)     // Catch:{ all -> 0x04ac }
            byte[] r5 = r12.array()     // Catch:{ all -> 0x04ac }
            int r6 = r12.position()     // Catch:{ all -> 0x04ac }
            r4.put(r5, r6, r14)     // Catch:{ all -> 0x04ac }
            goto L_0x010d
        L_0x00f5:
            org.bytedeco.javacpp.Pointer[] r4 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r5 = r4[r11]     // Catch:{ all -> 0x04ac }
            if (r5 == 0) goto L_0x0100
            r4 = r4[r11]     // Catch:{ all -> 0x04ac }
            r4.releaseReference()     // Catch:{ all -> 0x04ac }
        L_0x0100:
            org.bytedeco.javacpp.Pointer[] r4 = r1.samples_in     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer r5 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x04ac }
            r5.<init>((java.nio.ByteBuffer) r12)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer r5 = r5.retainReference()     // Catch:{ all -> 0x04ac }
            r4[r11] = r5     // Catch:{ all -> 0x04ac }
        L_0x010d:
            int r11 = r11 + 1
            r4 = 0
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r7 = 0
            goto L_0x00bd
        L_0x0116:
            r11 = r7
            r4 = r10
            r5 = 1
            r8 = 1
            goto L_0x02cd
        L_0x011c:
            if (r0 == 0) goto L_0x0189
            r4 = r0[r3]     // Catch:{ all -> 0x04ac }
            boolean r4 = r4 instanceof java.nio.ShortBuffer     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x0189
            int r4 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 <= r13) goto L_0x012a
            r4 = 6
            r10 = 6
            goto L_0x012b
        L_0x012a:
            r10 = 1
        L_0x012b:
            r4 = 0
        L_0x012c:
            int r5 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r5) goto L_0x0182
            r5 = r0[r4]     // Catch:{ all -> 0x04ac }
            java.nio.ShortBuffer r5 = (java.nio.ShortBuffer) r5     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            boolean r7 = r7 instanceof org.bytedeco.javacpp.ShortPointer     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x0166
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            long r6 = r6.capacity()     // Catch:{ all -> 0x04ac }
            long r12 = (long) r14     // Catch:{ all -> 0x04ac }
            int r11 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r11 < 0) goto L_0x0166
            boolean r6 = r5.hasArray()     // Catch:{ all -> 0x04ac }
            if (r6 == 0) goto L_0x0166
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.ShortPointer r6 = (org.bytedeco.javacpp.ShortPointer) r6     // Catch:{ all -> 0x04ac }
            r11 = 0
            org.bytedeco.javacpp.ShortPointer r6 = r6.position((long) r11)     // Catch:{ all -> 0x04ac }
            short[] r5 = r5.array()     // Catch:{ all -> 0x04ac }
            r7 = r0[r4]     // Catch:{ all -> 0x04ac }
            int r7 = r7.position()     // Catch:{ all -> 0x04ac }
            r6.put(r5, r7, r14)     // Catch:{ all -> 0x04ac }
            goto L_0x017e
        L_0x0166:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x0171
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            r6.releaseReference()     // Catch:{ all -> 0x04ac }
        L_0x0171:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.ShortPointer r7 = new org.bytedeco.javacpp.ShortPointer     // Catch:{ all -> 0x04ac }
            r7.<init>((java.nio.ShortBuffer) r5)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer r5 = r7.retainReference()     // Catch:{ all -> 0x04ac }
            r6[r4] = r5     // Catch:{ all -> 0x04ac }
        L_0x017e:
            int r4 = r4 + 1
            r13 = 1
            goto L_0x012c
        L_0x0182:
            r4 = r10
            r5 = 1
            r8 = 2
        L_0x0185:
            r11 = 0
            goto L_0x02cd
        L_0x0189:
            if (r0 == 0) goto L_0x01f3
            r4 = r0[r3]     // Catch:{ all -> 0x04ac }
            boolean r4 = r4 instanceof java.nio.IntBuffer     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x01f3
            int r4 = r0.length     // Catch:{ all -> 0x04ac }
            r5 = 1
            if (r4 <= r5) goto L_0x0198
            r4 = 7
            r10 = 7
            goto L_0x0199
        L_0x0198:
            r10 = 2
        L_0x0199:
            r4 = 0
        L_0x019a:
            int r5 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r5) goto L_0x01ef
            r5 = r0[r4]     // Catch:{ all -> 0x04ac }
            java.nio.IntBuffer r5 = (java.nio.IntBuffer) r5     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            boolean r7 = r7 instanceof org.bytedeco.javacpp.IntPointer     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x01d4
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            long r6 = r6.capacity()     // Catch:{ all -> 0x04ac }
            long r11 = (long) r14     // Catch:{ all -> 0x04ac }
            int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r8 < 0) goto L_0x01d4
            boolean r6 = r5.hasArray()     // Catch:{ all -> 0x04ac }
            if (r6 == 0) goto L_0x01d4
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.IntPointer r6 = (org.bytedeco.javacpp.IntPointer) r6     // Catch:{ all -> 0x04ac }
            r7 = 0
            org.bytedeco.javacpp.IntPointer r6 = r6.position((long) r7)     // Catch:{ all -> 0x04ac }
            int[] r5 = r5.array()     // Catch:{ all -> 0x04ac }
            r7 = r0[r4]     // Catch:{ all -> 0x04ac }
            int r7 = r7.position()     // Catch:{ all -> 0x04ac }
            r6.put(r5, r7, r14)     // Catch:{ all -> 0x04ac }
            goto L_0x01ec
        L_0x01d4:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x01df
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            r6.releaseReference()     // Catch:{ all -> 0x04ac }
        L_0x01df:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.IntPointer r7 = new org.bytedeco.javacpp.IntPointer     // Catch:{ all -> 0x04ac }
            r7.<init>((java.nio.IntBuffer) r5)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer r5 = r7.retainReference()     // Catch:{ all -> 0x04ac }
            r6[r4] = r5     // Catch:{ all -> 0x04ac }
        L_0x01ec:
            int r4 = r4 + 1
            goto L_0x019a
        L_0x01ef:
            r4 = r10
            r5 = 1
            r8 = 4
            goto L_0x0185
        L_0x01f3:
            if (r0 == 0) goto L_0x0258
            r4 = r0[r3]     // Catch:{ all -> 0x04ac }
            boolean r4 = r4 instanceof java.nio.FloatBuffer     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x0258
            int r4 = r0.length     // Catch:{ all -> 0x04ac }
            r5 = 1
            if (r4 <= r5) goto L_0x0202
            r10 = 8
            goto L_0x0204
        L_0x0202:
            r4 = 3
            r10 = 3
        L_0x0204:
            r4 = 0
        L_0x0205:
            int r5 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r5) goto L_0x01ef
            r5 = r0[r4]     // Catch:{ all -> 0x04ac }
            java.nio.FloatBuffer r5 = (java.nio.FloatBuffer) r5     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            boolean r7 = r7 instanceof org.bytedeco.javacpp.FloatPointer     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x023d
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            long r6 = r6.capacity()     // Catch:{ all -> 0x04ac }
            long r11 = (long) r14     // Catch:{ all -> 0x04ac }
            int r8 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r8 < 0) goto L_0x023d
            boolean r6 = r5.hasArray()     // Catch:{ all -> 0x04ac }
            if (r6 == 0) goto L_0x023d
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.FloatPointer r6 = (org.bytedeco.javacpp.FloatPointer) r6     // Catch:{ all -> 0x04ac }
            r7 = 0
            org.bytedeco.javacpp.FloatPointer r6 = r6.position((long) r7)     // Catch:{ all -> 0x04ac }
            float[] r7 = r5.array()     // Catch:{ all -> 0x04ac }
            int r5 = r5.position()     // Catch:{ all -> 0x04ac }
            r6.put(r7, r5, r14)     // Catch:{ all -> 0x04ac }
            goto L_0x0255
        L_0x023d:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r6[r4]     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x0248
            r6 = r6[r4]     // Catch:{ all -> 0x04ac }
            r6.releaseReference()     // Catch:{ all -> 0x04ac }
        L_0x0248:
            org.bytedeco.javacpp.Pointer[] r6 = r1.samples_in     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.FloatPointer r7 = new org.bytedeco.javacpp.FloatPointer     // Catch:{ all -> 0x04ac }
            r7.<init>((java.nio.FloatBuffer) r5)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer r5 = r7.retainReference()     // Catch:{ all -> 0x04ac }
            r6[r4] = r5     // Catch:{ all -> 0x04ac }
        L_0x0255:
            int r4 = r4 + 1
            goto L_0x0205
        L_0x0258:
            if (r0 == 0) goto L_0x02c6
            r4 = r0[r3]     // Catch:{ all -> 0x04ac }
            boolean r4 = r4 instanceof java.nio.DoubleBuffer     // Catch:{ all -> 0x04ac }
            if (r4 == 0) goto L_0x02c6
            int r4 = r0.length     // Catch:{ all -> 0x04ac }
            r5 = 1
            if (r4 <= r5) goto L_0x0269
            r4 = 9
            r10 = 9
            goto L_0x026a
        L_0x0269:
            r10 = 4
        L_0x026a:
            r4 = 0
        L_0x026b:
            int r6 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r6) goto L_0x02c0
            r6 = r0[r4]     // Catch:{ all -> 0x04ac }
            java.nio.DoubleBuffer r6 = (java.nio.DoubleBuffer) r6     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r7 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r8 = r7[r4]     // Catch:{ all -> 0x04ac }
            boolean r8 = r8 instanceof org.bytedeco.javacpp.DoublePointer     // Catch:{ all -> 0x04ac }
            if (r8 == 0) goto L_0x02a3
            r7 = r7[r4]     // Catch:{ all -> 0x04ac }
            long r7 = r7.capacity()     // Catch:{ all -> 0x04ac }
            long r11 = (long) r14     // Catch:{ all -> 0x04ac }
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x02a3
            boolean r7 = r6.hasArray()     // Catch:{ all -> 0x04ac }
            if (r7 == 0) goto L_0x02a3
            org.bytedeco.javacpp.Pointer[] r7 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r7 = r7[r4]     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.DoublePointer r7 = (org.bytedeco.javacpp.DoublePointer) r7     // Catch:{ all -> 0x04ac }
            r11 = 0
            org.bytedeco.javacpp.DoublePointer r7 = r7.position((long) r11)     // Catch:{ all -> 0x04ac }
            double[] r8 = r6.array()     // Catch:{ all -> 0x04ac }
            int r6 = r6.position()     // Catch:{ all -> 0x04ac }
            r7.put(r8, r6, r14)     // Catch:{ all -> 0x04ac }
            goto L_0x02bd
        L_0x02a3:
            r11 = 0
            org.bytedeco.javacpp.Pointer[] r7 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r8 = r7[r4]     // Catch:{ all -> 0x04ac }
            if (r8 == 0) goto L_0x02b0
            r7 = r7[r4]     // Catch:{ all -> 0x04ac }
            r7.releaseReference()     // Catch:{ all -> 0x04ac }
        L_0x02b0:
            org.bytedeco.javacpp.Pointer[] r7 = r1.samples_in     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.DoublePointer r8 = new org.bytedeco.javacpp.DoublePointer     // Catch:{ all -> 0x04ac }
            r8.<init>((java.nio.DoubleBuffer) r6)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer r6 = r8.retainReference()     // Catch:{ all -> 0x04ac }
            r7[r4] = r6     // Catch:{ all -> 0x04ac }
        L_0x02bd:
            int r4 = r4 + 1
            goto L_0x026b
        L_0x02c0:
            r11 = 0
            r4 = r10
            r8 = 8
            goto L_0x02cd
        L_0x02c6:
            r5 = 1
            r11 = 0
            if (r0 != 0) goto L_0x0485
            r4 = r10
            r8 = 0
        L_0x02cd:
            org.bytedeco.ffmpeg.swresample.SwrContext r10 = r1.samples_convert_ctx     // Catch:{ all -> 0x04ac }
            if (r10 == 0) goto L_0x02e2
            int r6 = r1.samples_channels     // Catch:{ all -> 0x04ac }
            if (r6 != r15) goto L_0x02e2
            int r6 = r1.samples_format     // Catch:{ all -> 0x04ac }
            if (r6 != r4) goto L_0x02e2
            int r6 = r1.samples_rate     // Catch:{ all -> 0x04ac }
            if (r6 == r9) goto L_0x02de
            goto L_0x02e2
        L_0x02de:
            r25 = r11
            r6 = r14
            goto L_0x031d
        L_0x02e2:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r6 = r1.audio_c     // Catch:{ all -> 0x04ac }
            long r6 = r6.channel_layout()     // Catch:{ all -> 0x04ac }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r13 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r17 = r13.sample_rate()     // Catch:{ all -> 0x04ac }
            long r18 = org.bytedeco.ffmpeg.global.avutil.av_get_default_channel_layout(r15)     // Catch:{ all -> 0x04ac }
            r20 = 0
            r24 = 0
            r25 = r11
            r11 = r6
            r13 = r16
            r6 = r14
            r14 = r17
            r7 = r15
            r15 = r18
            r17 = r4
            r18 = r9
            r19 = r20
            r20 = r24
            org.bytedeco.ffmpeg.swresample.SwrContext r10 = org.bytedeco.ffmpeg.global.swresample.swr_alloc_set_opts(r10, r11, r13, r14, r15, r17, r18, r19, r20)     // Catch:{ all -> 0x04ac }
            r1.samples_convert_ctx = r10     // Catch:{ all -> 0x04ac }
            if (r10 == 0) goto L_0x047d
            int r10 = org.bytedeco.ffmpeg.global.swresample.swr_init(r10)     // Catch:{ all -> 0x04ac }
            if (r10 < 0) goto L_0x0461
            r1.samples_channels = r7     // Catch:{ all -> 0x04ac }
            r1.samples_format = r4     // Catch:{ all -> 0x04ac }
            r1.samples_rate = r9     // Catch:{ all -> 0x04ac }
        L_0x031d:
            r4 = 0
        L_0x031e:
            if (r0 == 0) goto L_0x0348
            int r7 = r0.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r7) goto L_0x0348
            org.bytedeco.javacpp.Pointer[] r7 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r10 = r7[r4]     // Catch:{ all -> 0x04ac }
            r7 = r7[r4]     // Catch:{ all -> 0x04ac }
            long r11 = r7.position()     // Catch:{ all -> 0x04ac }
            long r13 = (long) r8     // Catch:{ all -> 0x04ac }
            long r11 = r11 * r13
            org.bytedeco.javacpp.Pointer r7 = r10.position(r11)     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r10 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r10 = r10[r4]     // Catch:{ all -> 0x04ac }
            long r10 = r10.position()     // Catch:{ all -> 0x04ac }
            r15 = r4
            long r3 = (long) r6     // Catch:{ all -> 0x04ac }
            long r10 = r10 + r3
            long r10 = r10 * r13
            r7.limit(r10)     // Catch:{ all -> 0x04ac }
            int r4 = r15 + 1
            r3 = 0
            goto L_0x031e
        L_0x0348:
            if (r0 == 0) goto L_0x0362
            org.bytedeco.javacpp.Pointer[] r3 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r4 = 0
            r3 = r3[r4]     // Catch:{ all -> 0x04ac }
            long r6 = r3.limit()     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r3 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r3 = r3[r4]     // Catch:{ all -> 0x04ac }
            long r3 = r3.position()     // Catch:{ all -> 0x04ac }
            long r6 = r6 - r3
            int r3 = r21 * r8
            long r3 = (long) r3     // Catch:{ all -> 0x04ac }
            long r3 = r6 / r3
            goto L_0x0364
        L_0x0362:
            r3 = r25
        L_0x0364:
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r3 = java.lang.Math.min(r3, r6)     // Catch:{ all -> 0x04ac }
            int r4 = (int) r3     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer[] r3 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r6 = 0
            r3 = r3[r6]     // Catch:{ all -> 0x04ac }
            long r10 = r3.limit()     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer[] r3 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r3 = r3[r6]     // Catch:{ all -> 0x04ac }
            long r6 = r3.position()     // Catch:{ all -> 0x04ac }
            long r10 = r10 - r6
            int r3 = r22 * r23
            long r6 = (long) r3     // Catch:{ all -> 0x04ac }
            long r10 = r10 / r6
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r10 = java.lang.Math.min(r10, r6)     // Catch:{ all -> 0x04ac }
            int r3 = (int) r10     // Catch:{ all -> 0x04ac }
            int r10 = r3 * r9
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r11 = r11.sample_rate()     // Catch:{ all -> 0x04ac }
            int r10 = r10 + r11
            int r10 = r10 - r5
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r11 = r1.audio_c     // Catch:{ all -> 0x04ac }
            int r11 = r11.sample_rate()     // Catch:{ all -> 0x04ac }
            int r10 = r10 / r11
            int r4 = java.lang.Math.min(r4, r10)     // Catch:{ all -> 0x04ac }
            r10 = 0
        L_0x03a0:
            if (r0 == 0) goto L_0x03b2
            int r11 = r0.length     // Catch:{ all -> 0x04ac }
            if (r10 >= r11) goto L_0x03b2
            org.bytedeco.javacpp.PointerPointer r11 = r1.plane_ptr     // Catch:{ all -> 0x04ac }
            long r13 = (long) r10     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.Pointer[] r15 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r15 = r15[r10]     // Catch:{ all -> 0x04ac }
            r11.put(r13, r15)     // Catch:{ all -> 0x04ac }
            int r10 = r10 + 1
            goto L_0x03a0
        L_0x03b2:
            r10 = 0
        L_0x03b3:
            org.bytedeco.javacpp.BytePointer[] r11 = r1.samples_out     // Catch:{ all -> 0x04ac }
            int r13 = r11.length     // Catch:{ all -> 0x04ac }
            if (r10 >= r13) goto L_0x03c3
            org.bytedeco.javacpp.PointerPointer r13 = r1.plane_ptr2     // Catch:{ all -> 0x04ac }
            long r14 = (long) r10     // Catch:{ all -> 0x04ac }
            r11 = r11[r10]     // Catch:{ all -> 0x04ac }
            r13.put(r14, r11)     // Catch:{ all -> 0x04ac }
            int r10 = r10 + 1
            goto L_0x03b3
        L_0x03c3:
            org.bytedeco.ffmpeg.swresample.SwrContext r10 = r1.samples_convert_ctx     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.PointerPointer r11 = r1.plane_ptr2     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.PointerPointer r13 = r1.plane_ptr     // Catch:{ all -> 0x04ac }
            int r3 = org.bytedeco.ffmpeg.global.swresample.swr_convert((org.bytedeco.ffmpeg.swresample.SwrContext) r10, (org.bytedeco.javacpp.PointerPointer) r11, (int) r3, (org.bytedeco.javacpp.PointerPointer) r13, (int) r4)     // Catch:{ all -> 0x04ac }
            if (r3 < 0) goto L_0x0445
            if (r3 != 0) goto L_0x03ec
            if (r0 == 0) goto L_0x03df
            org.bytedeco.ffmpeg.avutil.AVFrame r0 = r1.frame     // Catch:{ all -> 0x04ac }
            int r0 = r0.key_frame()     // Catch:{ all -> 0x04ac }
            if (r0 == 0) goto L_0x03dd
            r3 = 1
            goto L_0x03e7
        L_0x03dd:
            r3 = 0
            goto L_0x03e7
        L_0x03df:
            r10 = 0
            r4 = r10
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = (org.bytedeco.ffmpeg.avutil.AVFrame) r4     // Catch:{ all -> 0x04ac }
            boolean r3 = r1.record((org.bytedeco.ffmpeg.avutil.AVFrame) r4)     // Catch:{ all -> 0x04ac }
        L_0x03e7:
            r2.close()     // Catch:{ all -> 0x04bb }
            monitor-exit(r27)
            return r3
        L_0x03ec:
            r10 = 0
            r11 = 0
        L_0x03ee:
            if (r0 == 0) goto L_0x040d
            int r13 = r0.length     // Catch:{ all -> 0x04ac }
            if (r11 >= r13) goto L_0x040d
            org.bytedeco.javacpp.Pointer[] r13 = r1.samples_in     // Catch:{ all -> 0x04ac }
            r14 = r13[r11]     // Catch:{ all -> 0x04ac }
            r13 = r13[r11]     // Catch:{ all -> 0x04ac }
            long r15 = r13.position()     // Catch:{ all -> 0x04ac }
            int r13 = r4 * r21
            int r13 = r13 * r8
            long r5 = (long) r13     // Catch:{ all -> 0x04ac }
            long r5 = r5 + r15
            r14.position(r5)     // Catch:{ all -> 0x04ac }
            int r11 = r11 + 1
            r5 = 1
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            goto L_0x03ee
        L_0x040d:
            r4 = 0
        L_0x040e:
            org.bytedeco.javacpp.BytePointer[] r5 = r1.samples_out     // Catch:{ all -> 0x04ac }
            int r6 = r5.length     // Catch:{ all -> 0x04ac }
            if (r4 >= r6) goto L_0x0428
            r6 = r5[r4]     // Catch:{ all -> 0x04ac }
            r5 = r5[r4]     // Catch:{ all -> 0x04ac }
            long r13 = r5.position()     // Catch:{ all -> 0x04ac }
            int r5 = r3 * r22
            int r5 = r5 * r23
            long r10 = (long) r5     // Catch:{ all -> 0x04ac }
            long r13 = r13 + r10
            r6.position((long) r13)     // Catch:{ all -> 0x04ac }
            int r4 = r4 + 1
            r10 = 0
            goto L_0x040e
        L_0x0428:
            r3 = 0
            if (r0 == 0) goto L_0x043d
            r4 = r5[r3]     // Catch:{ all -> 0x04ac }
            long r4 = r4.position()     // Catch:{ all -> 0x04ac }
            org.bytedeco.javacpp.BytePointer[] r6 = r1.samples_out     // Catch:{ all -> 0x04ac }
            r6 = r6[r3]     // Catch:{ all -> 0x04ac }
            long r6 = r6.limit()     // Catch:{ all -> 0x04ac }
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x0442
        L_0x043d:
            int r4 = r1.audio_input_frame_size     // Catch:{ all -> 0x04ac }
            r1.writeSamples(r4)     // Catch:{ all -> 0x04ac }
        L_0x0442:
            r5 = 1
            goto L_0x0348
        L_0x0445:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ac }
            r4.<init>()     // Catch:{ all -> 0x04ac }
            java.lang.String r5 = "swr_convert() error "
            r4.append(r5)     // Catch:{ all -> 0x04ac }
            r4.append(r3)     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = ": Cannot convert audio samples."
            r4.append(r3)     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x04ac }
            r0.<init>(r3)     // Catch:{ all -> 0x04ac }
            throw r0     // Catch:{ all -> 0x04ac }
        L_0x0461:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ac }
            r3.<init>()     // Catch:{ all -> 0x04ac }
            java.lang.String r4 = "swr_init() error "
            r3.append(r4)     // Catch:{ all -> 0x04ac }
            r3.append(r10)     // Catch:{ all -> 0x04ac }
            java.lang.String r4 = ": Cannot initialize the conversion context."
            r3.append(r4)     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x04ac }
            r0.<init>(r3)     // Catch:{ all -> 0x04ac }
            throw r0     // Catch:{ all -> 0x04ac }
        L_0x047d:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = "swr_alloc_set_opts() error: Cannot allocate the conversion context."
            r0.<init>(r3)     // Catch:{ all -> 0x04ac }
            throw r0     // Catch:{ all -> 0x04ac }
        L_0x0485:
            org.bytedeco.javacv.FrameRecorder$Exception r3 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ac }
            r4.<init>()     // Catch:{ all -> 0x04ac }
            java.lang.String r5 = "Audio samples Buffer has unsupported type: "
            r4.append(r5)     // Catch:{ all -> 0x04ac }
            r4.append(r0)     // Catch:{ all -> 0x04ac }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x04ac }
            r3.<init>(r0)     // Catch:{ all -> 0x04ac }
            throw r3     // Catch:{ all -> 0x04ac }
        L_0x049c:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = "start() was not called successfully!"
            r0.<init>(r3)     // Catch:{ all -> 0x04ac }
            throw r0     // Catch:{ all -> 0x04ac }
        L_0x04a4:
            org.bytedeco.javacv.FrameRecorder$Exception r0 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x04ac }
            java.lang.String r3 = "No audio output stream (Is audioChannels > 0 and has start() been called?)"
            r0.<init>(r3)     // Catch:{ all -> 0x04ac }
            throw r0     // Catch:{ all -> 0x04ac }
        L_0x04ac:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch:{ all -> 0x04af }
        L_0x04af:
            r0 = move-exception
            r4 = r0
            r2.close()     // Catch:{ all -> 0x04b5 }
            goto L_0x04ba
        L_0x04b5:
            r0 = move-exception
            r2 = r0
            r3.addSuppressed(r2)     // Catch:{ all -> 0x04bb }
        L_0x04ba:
            throw r4     // Catch:{ all -> 0x04bb }
        L_0x04bb:
            r0 = move-exception
            monitor-exit(r27)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameRecorder.recordSamples(int, int, java.nio.Buffer[]):boolean");
    }

    private void writeSamples(int i) throws FrameRecorder.Exception {
        int i2;
        BytePointer[] bytePointerArr = this.samples_out;
        if (bytePointerArr != null && bytePointerArr.length != 0) {
            this.frame.nb_samples(i);
            AVFrame aVFrame = this.frame;
            int channels = this.audio_c.channels();
            int sample_fmt = this.audio_c.sample_fmt();
            BytePointer[] bytePointerArr2 = this.samples_out;
            avcodec.avcodec_fill_audio_frame(aVFrame, channels, sample_fmt, bytePointerArr2[0], (int) bytePointerArr2[0].position(), 0);
            int i3 = 0;
            while (true) {
                BytePointer[] bytePointerArr3 = this.samples_out;
                if (i3 < bytePointerArr3.length) {
                    if (bytePointerArr3[0].position() <= 0 || this.samples_out[0].position() >= this.samples_out[0].limit()) {
                        i2 = (int) Math.min(this.samples_out[i3].limit(), 2147483647L);
                    } else {
                        i2 = (((int) this.samples_out[i3].position()) + 31) & -32;
                    }
                    this.frame.data(i3, this.samples_out[i3].position(0));
                    this.frame.linesize(i3, i2);
                    i3++;
                } else {
                    this.frame.quality(this.audio_c.global_quality());
                    record(this.frame);
                    return;
                }
            }
        }
    }

    private boolean record(AVFrame aVFrame) throws FrameRecorder.Exception {
        avcodec.av_init_packet(this.audio_pkt);
        this.audio_pkt.data(this.audio_outbuf);
        this.audio_pkt.size(this.audio_outbuf_size);
        int avcodec_encode_audio2 = avcodec.avcodec_encode_audio2(this.audio_c, this.audio_pkt, aVFrame, this.got_audio_packet);
        if (avcodec_encode_audio2 >= 0) {
            if (aVFrame != null) {
                aVFrame.pts(aVFrame.pts() + ((long) aVFrame.nb_samples()));
            }
            if (this.got_audio_packet[0] == 0) {
                return false;
            }
            if (this.audio_pkt.pts() != avutil.AV_NOPTS_VALUE) {
                AVPacket aVPacket = this.audio_pkt;
                aVPacket.pts(avutil.av_rescale_q(aVPacket.pts(), this.audio_c.time_base(), this.audio_st.time_base()));
            }
            if (this.audio_pkt.dts() != avutil.AV_NOPTS_VALUE) {
                AVPacket aVPacket2 = this.audio_pkt;
                aVPacket2.dts(avutil.av_rescale_q(aVPacket2.dts(), this.audio_c.time_base(), this.audio_st.time_base()));
            }
            AVPacket aVPacket3 = this.audio_pkt;
            aVPacket3.flags(aVPacket3.flags() | 1);
            this.audio_pkt.stream_index(this.audio_st.index());
            writePacket(1, this.audio_pkt);
            return true;
        }
        throw new FrameRecorder.Exception("avcodec_encode_audio2() error " + avcodec_encode_audio2 + ": Could not encode audio packet.");
    }

    private void writePacket(int i, AVPacket aVPacket) throws FrameRecorder.Exception {
        AVStream aVStream = i == 0 ? this.audio_st : i == 1 ? this.video_st : null;
        String str = i == 0 ? "video" : i == 1 ? "audio" : "unsupported media stream type";
        synchronized (this.oc) {
            if (!this.interleaved || aVStream == null) {
                int av_write_frame = avformat.av_write_frame(this.oc, aVPacket);
                if (av_write_frame < 0) {
                    throw new FrameRecorder.Exception("av_write_frame() error " + av_write_frame + " while writing " + str + " packet.");
                }
            } else {
                int av_interleaved_write_frame = avformat.av_interleaved_write_frame(this.oc, aVPacket);
                if (av_interleaved_write_frame < 0) {
                    throw new FrameRecorder.Exception("av_interleaved_write_frame() error " + av_interleaved_write_frame + " while writing interleaved " + str + " packet.");
                }
            }
        }
        avcodec.av_packet_unref(aVPacket);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ea, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean recordPacket(org.bytedeco.ffmpeg.avcodec.AVPacket r9) throws org.bytedeco.javacv.FrameRecorder.Exception {
        /*
            r8 = this;
            monitor-enter(r8)
            org.bytedeco.ffmpeg.avformat.AVFormatContext r0 = r8.ifmt_ctx     // Catch:{ all -> 0x00fb }
            if (r0 == 0) goto L_0x00f3
            boolean r0 = r8.started     // Catch:{ all -> 0x00fb }
            if (r0 == 0) goto L_0x00eb
            r0 = 0
            if (r9 != 0) goto L_0x000e
            monitor-exit(r8)
            return r0
        L_0x000e:
            org.bytedeco.ffmpeg.avformat.AVFormatContext r1 = r8.ifmt_ctx     // Catch:{ all -> 0x00fb }
            int r2 = r9.stream_index()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r1 = r1.streams((int) r2)     // Catch:{ all -> 0x00fb }
            r2 = -1
            r9.pos(r2)     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.codec()     // Catch:{ all -> 0x00fb }
            int r2 = r2.codec_type()     // Catch:{ all -> 0x00fb }
            r3 = 1
            r4 = 8197(0x2005, float:1.1486E-41)
            if (r2 != 0) goto L_0x0082
            org.bytedeco.ffmpeg.avformat.AVStream r2 = r8.video_st     // Catch:{ all -> 0x00fb }
            if (r2 == 0) goto L_0x0082
            int r2 = r2.index()     // Catch:{ all -> 0x00fb }
            r9.stream_index(r2)     // Catch:{ all -> 0x00fb }
            long r5 = r9.duration()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r1.codec()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r2.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r8.video_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r7 = r7.codec()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r7 = r7.time_base()     // Catch:{ all -> 0x00fb }
            long r5 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q(r5, r2, r7)     // Catch:{ all -> 0x00fb }
            int r2 = (int) r5     // Catch:{ all -> 0x00fb }
            long r5 = (long) r2     // Catch:{ all -> 0x00fb }
            r9.duration(r5)     // Catch:{ all -> 0x00fb }
            long r5 = r9.pts()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r1.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r7 = r8.video_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r7 = r7.time_base()     // Catch:{ all -> 0x00fb }
            long r5 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q_rnd(r5, r2, r7, r4)     // Catch:{ all -> 0x00fb }
            r9.pts(r5)     // Catch:{ all -> 0x00fb }
            long r5 = r9.dts()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r1 = r1.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r2 = r8.video_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r2.time_base()     // Catch:{ all -> 0x00fb }
            long r1 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q_rnd(r5, r1, r2, r4)     // Catch:{ all -> 0x00fb }
            r9.dts(r1)     // Catch:{ all -> 0x00fb }
            r8.writePacket(r0, r9)     // Catch:{ all -> 0x00fb }
            goto L_0x00e9
        L_0x0082:
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.codec()     // Catch:{ all -> 0x00fb }
            int r0 = r0.codec_type()     // Catch:{ all -> 0x00fb }
            if (r0 != r3) goto L_0x00e9
            org.bytedeco.ffmpeg.avformat.AVStream r0 = r8.audio_st     // Catch:{ all -> 0x00fb }
            if (r0 == 0) goto L_0x00e9
            int r0 = r8.audioChannels     // Catch:{ all -> 0x00fb }
            if (r0 <= 0) goto L_0x00e9
            org.bytedeco.ffmpeg.avformat.AVStream r0 = r8.audio_st     // Catch:{ all -> 0x00fb }
            int r0 = r0.index()     // Catch:{ all -> 0x00fb }
            r9.stream_index(r0)     // Catch:{ all -> 0x00fb }
            long r5 = r9.duration()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r0 = r1.codec()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r0 = r0.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r2 = r8.audio_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avcodec.AVCodecContext r2 = r2.codec()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r2.time_base()     // Catch:{ all -> 0x00fb }
            long r5 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q(r5, r0, r2)     // Catch:{ all -> 0x00fb }
            int r0 = (int) r5     // Catch:{ all -> 0x00fb }
            long r5 = (long) r0     // Catch:{ all -> 0x00fb }
            r9.duration(r5)     // Catch:{ all -> 0x00fb }
            long r5 = r9.pts()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r0 = r1.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r2 = r8.audio_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r2.time_base()     // Catch:{ all -> 0x00fb }
            long r5 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q_rnd(r5, r0, r2, r4)     // Catch:{ all -> 0x00fb }
            r9.pts(r5)     // Catch:{ all -> 0x00fb }
            long r5 = r9.dts()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r0 = r1.time_base()     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avformat.AVStream r1 = r8.audio_st     // Catch:{ all -> 0x00fb }
            org.bytedeco.ffmpeg.avutil.AVRational r1 = r1.time_base()     // Catch:{ all -> 0x00fb }
            long r0 = org.bytedeco.ffmpeg.global.avutil.av_rescale_q_rnd(r5, r0, r1, r4)     // Catch:{ all -> 0x00fb }
            r9.dts(r0)     // Catch:{ all -> 0x00fb }
            r8.writePacket(r3, r9)     // Catch:{ all -> 0x00fb }
        L_0x00e9:
            monitor-exit(r8)
            return r3
        L_0x00eb:
            org.bytedeco.javacv.FrameRecorder$Exception r9 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x00fb }
            java.lang.String r0 = "start() was not called successfully!"
            r9.<init>(r0)     // Catch:{ all -> 0x00fb }
            throw r9     // Catch:{ all -> 0x00fb }
        L_0x00f3:
            org.bytedeco.javacv.FrameRecorder$Exception r9 = new org.bytedeco.javacv.FrameRecorder$Exception     // Catch:{ all -> 0x00fb }
            java.lang.String r0 = "No input format context (Has start(AVFormatContext) been called?)"
            r9.<init>(r0)     // Catch:{ all -> 0x00fb }
            throw r9     // Catch:{ all -> 0x00fb }
        L_0x00fb:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameRecorder.recordPacket(org.bytedeco.ffmpeg.avcodec.AVPacket):boolean");
    }
}
