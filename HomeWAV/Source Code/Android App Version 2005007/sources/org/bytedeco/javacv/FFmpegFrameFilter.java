package org.bytedeco.javacv;

import com.google.android.exoplayer2.C;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Locale;
import org.bytedeco.ffmpeg.avfilter.AVFilter;
import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraph;
import org.bytedeco.ffmpeg.avfilter.AVFilterInOut;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avfilter;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.ffmpeg.global.postproc;
import org.bytedeco.ffmpeg.global.swresample;
import org.bytedeco.ffmpeg.global.swscale;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacv.FrameFilter;

public class FFmpegFrameFilter extends FrameFilter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static FrameFilter.Exception loadingException;
    AVFilterContext abuffersink_ctx;
    AVFilterContext[] abuffersrc_ctx;
    AVFilterGraph afilter_graph;
    AVFilterContext[] asetpts_ctx;
    AVRational atime_base;
    AVFilterContext buffersink_ctx;
    AVFilterContext[] buffersrc_ctx;
    AVFrame filt_frame;
    AVFilterGraph filter_graph;
    Frame frame;
    Buffer[] image_buf;
    Buffer[] image_buf2;
    AVFrame image_frame;
    BytePointer[] image_ptr;
    BytePointer[] image_ptr2;
    Frame inframe;
    Buffer[] samples_buf;
    AVFrame samples_frame;
    BytePointer[] samples_ptr;
    AVFilterContext[] setpts_ctx;
    private volatile boolean started;
    AVRational time_base;

    public static void tryLoad() throws FrameFilter.Exception {
        FrameFilter.Exception exception = loadingException;
        if (exception == null) {
            try {
                Loader.load(avutil.class);
                Loader.load(avcodec.class);
                Loader.load(avformat.class);
                Loader.load(postproc.class);
                Loader.load(swresample.class);
                Loader.load(swscale.class);
                Loader.load(avfilter.class);
                avformat.av_register_all();
                avfilter.avfilter_register_all();
            } catch (Throwable th) {
                if (th instanceof FrameFilter.Exception) {
                    FrameFilter.Exception exception2 = th;
                    loadingException = exception2;
                    throw exception2;
                }
                FrameFilter.Exception exception3 = new FrameFilter.Exception("Failed to load " + FFmpegFrameRecorder.class, th);
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
        } catch (FrameFilter.Exception unused) {
        }
    }

    public FFmpegFrameFilter(String str, String str2, int i, int i2, int i3) {
        this.started = false;
        this.filters = str;
        this.imageWidth = i;
        this.imageHeight = i2;
        this.pixelFormat = 3;
        this.frameRate = 30.0d;
        this.aspectRatio = 0.0d;
        this.videoInputs = 1;
        this.afilters = str2;
        this.audioChannels = i3;
        this.sampleFormat = 1;
        this.sampleRate = 44100;
        this.audioInputs = 1;
    }

    public FFmpegFrameFilter(String str, int i, int i2) {
        this(str, (String) null, i, i2, 0);
    }

    public FFmpegFrameFilter(String str, int i) {
        this((String) null, str, 0, 0, i);
    }

    public void release() throws FrameFilter.Exception {
        synchronized (avfilter.class) {
            releaseUnsafe();
        }
    }

    public synchronized void releaseUnsafe() throws FrameFilter.Exception {
        int i = 0;
        this.started = false;
        if (this.image_ptr2 != null) {
            int i2 = 0;
            while (true) {
                BytePointer[] bytePointerArr = this.image_ptr2;
                if (i2 >= bytePointerArr.length) {
                    break;
                }
                avutil.av_free(bytePointerArr[i2]);
                i2++;
            }
            this.image_ptr2 = null;
        }
        AVFilterGraph aVFilterGraph = this.filter_graph;
        if (aVFilterGraph != null) {
            avfilter.avfilter_graph_free(aVFilterGraph);
            this.buffersink_ctx.releaseReference();
            int i3 = 0;
            while (true) {
                AVFilterContext[] aVFilterContextArr = this.buffersrc_ctx;
                if (i3 >= aVFilterContextArr.length) {
                    break;
                }
                aVFilterContextArr[i3].releaseReference();
                this.setpts_ctx[i3].releaseReference();
                i3++;
            }
            this.time_base.releaseReference();
            this.buffersink_ctx = null;
            this.buffersrc_ctx = null;
            this.setpts_ctx = null;
            this.filter_graph = null;
            this.time_base = null;
        }
        AVFilterGraph aVFilterGraph2 = this.afilter_graph;
        if (aVFilterGraph2 != null) {
            avfilter.avfilter_graph_free(aVFilterGraph2);
            this.abuffersink_ctx.releaseReference();
            while (true) {
                AVFilterContext[] aVFilterContextArr2 = this.abuffersrc_ctx;
                if (i >= aVFilterContextArr2.length) {
                    break;
                }
                aVFilterContextArr2[i].releaseReference();
                this.asetpts_ctx[i].releaseReference();
                i++;
            }
            this.atime_base.releaseReference();
            this.abuffersink_ctx = null;
            this.abuffersrc_ctx = null;
            this.asetpts_ctx = null;
            this.afilter_graph = null;
            this.atime_base = null;
        }
        AVFrame aVFrame = this.image_frame;
        if (aVFrame != null) {
            avutil.av_frame_free(aVFrame);
            this.image_frame = null;
        }
        AVFrame aVFrame2 = this.samples_frame;
        if (aVFrame2 != null) {
            avutil.av_frame_free(aVFrame2);
            this.samples_frame = null;
        }
        AVFrame aVFrame3 = this.filt_frame;
        if (aVFrame3 != null) {
            avutil.av_frame_free(aVFrame3);
            this.filt_frame = null;
        }
        this.frame = null;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }

    public int getImageWidth() {
        AVFilterContext aVFilterContext = this.buffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_w(aVFilterContext) : super.getImageWidth();
    }

    public int getImageHeight() {
        AVFilterContext aVFilterContext = this.buffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_h(aVFilterContext) : super.getImageHeight();
    }

    public int getPixelFormat() {
        AVFilterContext aVFilterContext = this.buffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_format(aVFilterContext) : super.getPixelFormat();
    }

    public double getFrameRate() {
        double num;
        int den;
        AVFilterContext aVFilterContext = this.buffersink_ctx;
        if (aVFilterContext == null) {
            return super.getFrameRate();
        }
        AVRational av_buffersink_get_frame_rate = avfilter.av_buffersink_get_frame_rate(aVFilterContext);
        if (av_buffersink_get_frame_rate.num() == 0 && av_buffersink_get_frame_rate.den() == 0) {
            AVRational av_buffersink_get_time_base = avfilter.av_buffersink_get_time_base(this.buffersink_ctx);
            num = (double) av_buffersink_get_time_base.den();
            den = av_buffersink_get_time_base.num();
        } else {
            num = (double) av_buffersink_get_frame_rate.num();
            den = av_buffersink_get_frame_rate.den();
        }
        return num / ((double) den);
    }

    public double getAspectRatio() {
        AVFilterContext aVFilterContext = this.buffersink_ctx;
        if (aVFilterContext == null) {
            return super.getAspectRatio();
        }
        AVRational av_buffersink_get_sample_aspect_ratio = avfilter.av_buffersink_get_sample_aspect_ratio(aVFilterContext);
        double num = ((double) av_buffersink_get_sample_aspect_ratio.num()) / ((double) av_buffersink_get_sample_aspect_ratio.den());
        if (num == 0.0d) {
            return 1.0d;
        }
        return num;
    }

    public int getAudioChannels() {
        AVFilterContext aVFilterContext = this.abuffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_channels(aVFilterContext) : super.getAudioChannels();
    }

    public int getSampleFormat() {
        AVFilterContext aVFilterContext = this.abuffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_format(aVFilterContext) : super.getSampleFormat();
    }

    public int getSampleRate() {
        AVFilterContext aVFilterContext = this.abuffersink_ctx;
        return aVFilterContext != null ? avfilter.av_buffersink_get_sample_rate(aVFilterContext) : super.getSampleRate();
    }

    public void start() throws FrameFilter.Exception {
        synchronized (avfilter.class) {
            startUnsafe();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0093, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0098, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startUnsafe() throws org.bytedeco.javacv.FrameFilter.Exception {
        /*
            r5 = this;
            monitor-enter(r5)
            org.bytedeco.javacpp.PointerScope r0 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x009d }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x009d }
            r0.<init>(r2)     // Catch:{ all -> 0x009d }
            org.bytedeco.javacv.Frame r2 = r5.frame     // Catch:{ all -> 0x0091 }
            if (r2 != 0) goto L_0x0089
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0091 }
            r5.image_frame = r2     // Catch:{ all -> 0x0091 }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0091 }
            r5.samples_frame = r2     // Catch:{ all -> 0x0091 }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = org.bytedeco.ffmpeg.global.avutil.av_frame_alloc()     // Catch:{ all -> 0x0091 }
            r5.filt_frame = r2     // Catch:{ all -> 0x0091 }
            r2 = 1
            org.bytedeco.javacpp.BytePointer[] r3 = new org.bytedeco.javacpp.BytePointer[r2]     // Catch:{ all -> 0x0091 }
            r4 = 0
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.image_ptr = r3     // Catch:{ all -> 0x0091 }
            org.bytedeco.javacpp.BytePointer[] r3 = new org.bytedeco.javacpp.BytePointer[r2]     // Catch:{ all -> 0x0091 }
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.image_ptr2 = r3     // Catch:{ all -> 0x0091 }
            java.nio.Buffer[] r3 = new java.nio.Buffer[r2]     // Catch:{ all -> 0x0091 }
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.image_buf = r3     // Catch:{ all -> 0x0091 }
            java.nio.Buffer[] r3 = new java.nio.Buffer[r2]     // Catch:{ all -> 0x0091 }
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.image_buf2 = r3     // Catch:{ all -> 0x0091 }
            org.bytedeco.javacpp.BytePointer[] r3 = new org.bytedeco.javacpp.BytePointer[r2]     // Catch:{ all -> 0x0091 }
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.samples_ptr = r3     // Catch:{ all -> 0x0091 }
            java.nio.Buffer[] r3 = new java.nio.Buffer[r2]     // Catch:{ all -> 0x0091 }
            r3[r1] = r4     // Catch:{ all -> 0x0091 }
            r5.samples_buf = r3     // Catch:{ all -> 0x0091 }
            org.bytedeco.javacv.Frame r1 = new org.bytedeco.javacv.Frame     // Catch:{ all -> 0x0091 }
            r1.<init>()     // Catch:{ all -> 0x0091 }
            r5.frame = r1     // Catch:{ all -> 0x0091 }
            org.bytedeco.ffmpeg.avutil.AVFrame r1 = r5.image_frame     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0081
            org.bytedeco.ffmpeg.avutil.AVFrame r1 = r5.samples_frame     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0081
            org.bytedeco.ffmpeg.avutil.AVFrame r1 = r5.filt_frame     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0081
            java.lang.String r1 = r5.filters     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x006b
            int r1 = r5.imageWidth     // Catch:{ all -> 0x0091 }
            if (r1 <= 0) goto L_0x006b
            int r1 = r5.imageHeight     // Catch:{ all -> 0x0091 }
            if (r1 <= 0) goto L_0x006b
            int r1 = r5.videoInputs     // Catch:{ all -> 0x0091 }
            if (r1 <= 0) goto L_0x006b
            r5.startVideoUnsafe()     // Catch:{ all -> 0x0091 }
        L_0x006b:
            java.lang.String r1 = r5.afilters     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x007a
            int r1 = r5.audioChannels     // Catch:{ all -> 0x0091 }
            if (r1 <= 0) goto L_0x007a
            int r1 = r5.audioInputs     // Catch:{ all -> 0x0091 }
            if (r1 <= 0) goto L_0x007a
            r5.startAudioUnsafe()     // Catch:{ all -> 0x0091 }
        L_0x007a:
            r5.started = r2     // Catch:{ all -> 0x0091 }
            r0.close()     // Catch:{ all -> 0x009d }
            monitor-exit(r5)
            return
        L_0x0081:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "Could not allocate frames"
            r1.<init>(r2)     // Catch:{ all -> 0x0091 }
            throw r1     // Catch:{ all -> 0x0091 }
        L_0x0089:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "start() has already been called: Call stop() before calling start() again."
            r1.<init>(r2)     // Catch:{ all -> 0x0091 }
            throw r1     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x009d }
        L_0x009c:
            throw r2     // Catch:{ all -> 0x009d }
        L_0x009d:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameFilter.startUnsafe():void");
    }

    private void startVideoUnsafe() throws FrameFilter.Exception {
        String str;
        String str2;
        AVFilter avfilter_get_by_name = avfilter.avfilter_get_by_name("buffer");
        AVFilter avfilter_get_by_name2 = avfilter.avfilter_get_by_name("buffersink");
        AVFilter avfilter_get_by_name3 = avfilter.avfilter_get_by_name("setpts");
        AVFilterInOut[] aVFilterInOutArr = new AVFilterInOut[this.videoInputs];
        AVFilterInOut avfilter_inout_alloc = avfilter.avfilter_inout_alloc();
        AVRational av_inv_q = avutil.av_inv_q(avutil.av_d2q(this.frameRate, 1001000));
        int i = this.pixelFormat;
        try {
            AVFilterGraph avfilter_graph_alloc = avfilter.avfilter_graph_alloc();
            this.filter_graph = avfilter_graph_alloc;
            if (avfilter_inout_alloc == null || avfilter_graph_alloc == null) {
                throw new FrameFilter.Exception("Could not allocate video filter graph: Out of memory?");
            }
            AVRational av_d2q = avutil.av_d2q(this.aspectRatio > 0.0d ? this.aspectRatio : 1.0d, 255);
            String format = String.format(Locale.ROOT, "video_size=%dx%d:pix_fmt=%d:time_base=%d/%d:pixel_aspect=%d/%d", new Object[]{Integer.valueOf(this.imageWidth), Integer.valueOf(this.imageHeight), Integer.valueOf(this.pixelFormat), Integer.valueOf(av_inv_q.num()), Integer.valueOf(av_inv_q.den()), Integer.valueOf(av_d2q.num()), Integer.valueOf(av_d2q.den())});
            this.buffersrc_ctx = new AVFilterContext[this.videoInputs];
            this.setpts_ctx = new AVFilterContext[this.videoInputs];
            int i2 = 0;
            while (i2 < this.videoInputs) {
                if (this.videoInputs > 1) {
                    str = i2 + ":v";
                } else {
                    str = "in";
                }
                String str3 = str;
                aVFilterInOutArr[i2] = avfilter.avfilter_inout_alloc();
                AVFilterContext[] aVFilterContextArr = this.buffersrc_ctx;
                AVFilterContext aVFilterContext = (AVFilterContext) new AVFilterContext().retainReference();
                aVFilterContextArr[i2] = aVFilterContext;
                String str4 = str3;
                String str5 = "avfilter_graph_create_filter() error ";
                int i3 = i2;
                int avfilter_graph_create_filter = avfilter.avfilter_graph_create_filter(aVFilterContext, avfilter_get_by_name, str3, format, (Pointer) null, this.filter_graph);
                if (avfilter_graph_create_filter >= 0) {
                    AVFilterContext[] aVFilterContextArr2 = this.setpts_ctx;
                    AVFilterContext aVFilterContext2 = (AVFilterContext) new AVFilterContext().retainReference();
                    aVFilterContextArr2[i3] = aVFilterContext2;
                    if (this.videoInputs > 1) {
                        str2 = "setpts" + i3;
                    } else {
                        str2 = "setpts";
                    }
                    int avfilter_graph_create_filter2 = avfilter.avfilter_graph_create_filter(aVFilterContext2, avfilter_get_by_name3, str2, "N", (Pointer) null, this.filter_graph);
                    if (avfilter_graph_create_filter2 >= 0) {
                        int avfilter_link = avfilter.avfilter_link(this.buffersrc_ctx[i3], 0, this.setpts_ctx[i3], 0);
                        if (avfilter_link >= 0) {
                            aVFilterInOutArr[i3].name(avutil.av_strdup(new BytePointer(str4)));
                            aVFilterInOutArr[i3].filter_ctx(this.setpts_ctx[i3]);
                            aVFilterInOutArr[i3].pad_idx(0);
                            aVFilterInOutArr[i3].next((AVFilterInOut) null);
                            if (i3 > 0) {
                                aVFilterInOutArr[i3 - 1].next(aVFilterInOutArr[i3]);
                            }
                            i2 = i3 + 1;
                        } else {
                            throw new FrameFilter.Exception(str5 + avfilter_link + ": Cannot link setpts filter.");
                        }
                    } else {
                        throw new FrameFilter.Exception(str5 + avfilter_graph_create_filter2 + ": Cannot create setpts filter.");
                    }
                } else {
                    throw new FrameFilter.Exception(str5 + avfilter_graph_create_filter + ": Cannot create video buffer source.");
                }
            }
            String str6 = "avfilter_graph_create_filter() error ";
            String str7 = this.videoInputs > 1 ? "v" : "out";
            AVFilterContext aVFilterContext3 = (AVFilterContext) new AVFilterContext().retainReference();
            this.buffersink_ctx = aVFilterContext3;
            int avfilter_graph_create_filter3 = avfilter.avfilter_graph_create_filter(aVFilterContext3, avfilter_get_by_name2, str7, (String) null, (Pointer) null, this.filter_graph);
            if (avfilter_graph_create_filter3 >= 0) {
                avfilter_inout_alloc.name(avutil.av_strdup(new BytePointer(str7)));
                avfilter_inout_alloc.filter_ctx(this.buffersink_ctx);
                avfilter_inout_alloc.pad_idx(0);
                avfilter_inout_alloc.next((AVFilterInOut) null);
                int avfilter_graph_parse_ptr = avfilter.avfilter_graph_parse_ptr(this.filter_graph, this.filters, avfilter_inout_alloc, aVFilterInOutArr[0], (Pointer) null);
                if (avfilter_graph_parse_ptr >= 0) {
                    int avfilter_graph_config = avfilter.avfilter_graph_config(this.filter_graph, (Pointer) null);
                    if (avfilter_graph_config >= 0) {
                        this.time_base = (AVRational) avfilter.av_buffersink_get_time_base(this.buffersink_ctx).retainReference();
                        return;
                    }
                    throw new FrameFilter.Exception("avfilter_graph_config() error " + avfilter_graph_config);
                }
                throw new FrameFilter.Exception("avfilter_graph_parse_ptr() error " + avfilter_graph_parse_ptr);
            }
            throw new FrameFilter.Exception(str6 + avfilter_graph_create_filter3 + ": Cannot create video buffer sink.");
        } finally {
            avfilter.avfilter_inout_free(avfilter_inout_alloc);
            avfilter.avfilter_inout_free(aVFilterInOutArr[0]);
        }
    }

    private void startAudioUnsafe() throws FrameFilter.Exception {
        String str;
        String str2;
        AVFilter avfilter_get_by_name = avfilter.avfilter_get_by_name("abuffer");
        AVFilter avfilter_get_by_name2 = avfilter.avfilter_get_by_name("abuffersink");
        AVFilter avfilter_get_by_name3 = avfilter.avfilter_get_by_name("asetpts");
        AVFilterInOut[] aVFilterInOutArr = new AVFilterInOut[this.audioInputs];
        AVFilterInOut avfilter_inout_alloc = avfilter.avfilter_inout_alloc();
        int i = this.sampleFormat;
        char c = 0;
        try {
            AVFilterGraph avfilter_graph_alloc = avfilter.avfilter_graph_alloc();
            this.afilter_graph = avfilter_graph_alloc;
            if (avfilter_inout_alloc == null || avfilter_graph_alloc == null) {
                throw new FrameFilter.Exception("Could not allocate audio filter graph: Out of memory?");
            }
            this.abuffersrc_ctx = new AVFilterContext[this.audioInputs];
            this.asetpts_ctx = new AVFilterContext[this.audioInputs];
            int i2 = 0;
            while (i2 < this.audioInputs) {
                if (this.audioInputs > 1) {
                    str = i2 + ":a";
                } else {
                    str = "in";
                }
                String str3 = str;
                aVFilterInOutArr[i2] = avfilter.avfilter_inout_alloc();
                Locale locale = Locale.ROOT;
                Object[] objArr = new Object[4];
                objArr[c] = Integer.valueOf(this.audioChannels);
                objArr[1] = Integer.valueOf(this.sampleFormat);
                objArr[2] = Integer.valueOf(this.sampleRate);
                objArr[3] = Long.valueOf(avutil.av_get_default_channel_layout(this.audioChannels));
                String format = String.format(locale, "channels=%d:sample_fmt=%d:sample_rate=%d:channel_layout=%d", objArr);
                AVFilterContext[] aVFilterContextArr = this.abuffersrc_ctx;
                AVFilterContext aVFilterContext = (AVFilterContext) new AVFilterContext().retainReference();
                aVFilterContextArr[i2] = aVFilterContext;
                String str4 = str3;
                String str5 = "avfilter_graph_create_filter() error ";
                int avfilter_graph_create_filter = avfilter.avfilter_graph_create_filter(aVFilterContext, avfilter_get_by_name, str3, format, (Pointer) null, this.afilter_graph);
                if (avfilter_graph_create_filter >= 0) {
                    AVFilterContext[] aVFilterContextArr2 = this.asetpts_ctx;
                    AVFilterContext aVFilterContext2 = (AVFilterContext) new AVFilterContext().retainReference();
                    aVFilterContextArr2[i2] = aVFilterContext2;
                    if (this.audioInputs > 1) {
                        str2 = "asetpts" + i2;
                    } else {
                        str2 = "asetpts";
                    }
                    int avfilter_graph_create_filter2 = avfilter.avfilter_graph_create_filter(aVFilterContext2, avfilter_get_by_name3, str2, "N", (Pointer) null, this.afilter_graph);
                    if (avfilter_graph_create_filter2 >= 0) {
                        int avfilter_link = avfilter.avfilter_link(this.abuffersrc_ctx[i2], 0, this.asetpts_ctx[i2], 0);
                        if (avfilter_link >= 0) {
                            aVFilterInOutArr[i2].name(avutil.av_strdup(new BytePointer(str4)));
                            aVFilterInOutArr[i2].filter_ctx(this.asetpts_ctx[i2]);
                            aVFilterInOutArr[i2].pad_idx(0);
                            aVFilterInOutArr[i2].next((AVFilterInOut) null);
                            if (i2 > 0) {
                                aVFilterInOutArr[i2 - 1].next(aVFilterInOutArr[i2]);
                            }
                            i2++;
                            c = 0;
                        } else {
                            throw new FrameFilter.Exception(str5 + avfilter_link + ": Cannot link asetpts filter.");
                        }
                    } else {
                        throw new FrameFilter.Exception(str5 + avfilter_graph_create_filter2 + ": Cannot create asetpts filter.");
                    }
                } else {
                    throw new FrameFilter.Exception(str5 + avfilter_graph_create_filter + ": Cannot create audio buffer source.");
                }
            }
            String str6 = "avfilter_graph_create_filter() error ";
            String str7 = this.audioInputs > 1 ? "a" : "out";
            AVFilterContext aVFilterContext3 = (AVFilterContext) new AVFilterContext().retainReference();
            this.abuffersink_ctx = aVFilterContext3;
            int avfilter_graph_create_filter3 = avfilter.avfilter_graph_create_filter(aVFilterContext3, avfilter_get_by_name2, str7, (String) null, (Pointer) null, this.afilter_graph);
            if (avfilter_graph_create_filter3 >= 0) {
                avfilter_inout_alloc.name(avutil.av_strdup(new BytePointer(str7)));
                avfilter_inout_alloc.filter_ctx(this.abuffersink_ctx);
                avfilter_inout_alloc.pad_idx(0);
                avfilter_inout_alloc.next((AVFilterInOut) null);
                int avfilter_graph_parse_ptr = avfilter.avfilter_graph_parse_ptr(this.afilter_graph, this.afilters, avfilter_inout_alloc, aVFilterInOutArr[0], (Pointer) null);
                if (avfilter_graph_parse_ptr >= 0) {
                    int avfilter_graph_config = avfilter.avfilter_graph_config(this.afilter_graph, (Pointer) null);
                    if (avfilter_graph_config >= 0) {
                        this.atime_base = (AVRational) avfilter.av_buffersink_get_time_base(this.abuffersink_ctx).retainReference();
                        return;
                    }
                    throw new FrameFilter.Exception("avfilter_graph_config() error " + avfilter_graph_config);
                }
                throw new FrameFilter.Exception("avfilter_graph_parse_ptr() error " + avfilter_graph_parse_ptr);
            }
            throw new FrameFilter.Exception(str6 + avfilter_graph_create_filter3 + ": Cannot create audio buffer sink.");
        } finally {
            avfilter.avfilter_inout_free(avfilter_inout_alloc);
            avfilter.avfilter_inout_free(aVFilterInOutArr[0]);
        }
    }

    public void stop() throws FrameFilter.Exception {
        release();
    }

    public void push(Frame frame2) throws FrameFilter.Exception {
        push(frame2, (frame2 == null || !(frame2.opaque instanceof AVFrame)) ? -1 : ((AVFrame) frame2.opaque).format());
    }

    public void push(Frame frame2, int i) throws FrameFilter.Exception {
        push(0, frame2, i);
    }

    public void push(int i, Frame frame2) throws FrameFilter.Exception {
        push(i, frame2, (frame2 == null || !(frame2.opaque instanceof AVFrame)) ? -1 : ((AVFrame) frame2.opaque).format());
    }

    public synchronized void push(int i, Frame frame2, int i2) throws FrameFilter.Exception {
        int i3 = i;
        Frame frame3 = frame2;
        synchronized (this) {
            if (this.started) {
                this.inframe = frame3;
                if (!(frame3 == null || frame3.image == null || this.buffersrc_ctx == null)) {
                    this.image_frame.pts((frame3.timestamp * ((long) this.time_base.den())) / (((long) this.time_base.num()) * C.MICROS_PER_SECOND));
                    pushImage(i, frame3.imageWidth, frame3.imageHeight, frame3.imageDepth, frame3.imageChannels, frame3.imageStride, i2, frame3.image);
                }
                if (!(frame3 == null || frame3.samples == null || this.abuffersrc_ctx == null)) {
                    this.samples_frame.pts((frame3.timestamp * ((long) this.atime_base.den())) / (((long) this.atime_base.num()) * C.MICROS_PER_SECOND));
                    pushSamples(i, frame3.audioChannels, this.sampleRate, this.sampleFormat, frame3.samples);
                }
                if (frame3 == null || (frame3.image == null && frame3.samples == null)) {
                    AVFilterContext[] aVFilterContextArr = this.buffersrc_ctx;
                    if (aVFilterContextArr != null && i3 < aVFilterContextArr.length) {
                        avfilter.av_buffersrc_add_frame_flags(aVFilterContextArr[i3], (AVFrame) null, 4);
                    }
                    AVFilterContext[] aVFilterContextArr2 = this.abuffersrc_ctx;
                    if (aVFilterContextArr2 != null && i3 < aVFilterContextArr2.length) {
                        avfilter.av_buffersrc_add_frame_flags(aVFilterContextArr2[i3], (AVFrame) null, 4);
                    }
                }
            } else {
                throw new FrameFilter.Exception("start() was not called successfully!");
            }
        }
    }

    public synchronized void pushImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer... bufferArr) throws FrameFilter.Exception {
        Throwable th;
        BytePointer bytePointer;
        int i8;
        int i9 = i4;
        int i10 = i5;
        synchronized (this) {
            PointerScope pointerScope = new PointerScope(new Class[0]);
            try {
                if (this.started) {
                    int abs = (Math.abs(i4) * i6) / 8;
                    if (bufferArr[0] instanceof ByteBuffer) {
                        bytePointer = new BytePointer(bufferArr[0]).position(0);
                    } else {
                        bytePointer = new BytePointer(new Pointer(bufferArr[0]).position(0));
                    }
                    BytePointer bytePointer2 = bytePointer;
                    int i11 = i7;
                    if (i11 != -1) {
                        i8 = i11;
                    } else if ((i9 == 8 || i9 == -8) && i10 == 3) {
                        i8 = 3;
                    } else if ((i9 == 8 || i9 == -8) && i10 == 1) {
                        i8 = 8;
                    } else if ((i9 == 16 || i9 == -16) && i10 == 1) {
                        i8 = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 29 : 30;
                    } else if ((i9 == 8 || i9 == -8) && i10 == 4) {
                        i8 = 26;
                    } else if ((i9 == 8 || i9 == -8) && i10 == 2) {
                        i8 = 24;
                    } else {
                        throw new FrameFilter.Exception("Could not guess pixel format of image: depth=" + i9 + ", channels=" + i10);
                    }
                    if (i8 == 24) {
                        abs = i2;
                    }
                    avutil.av_image_fill_arrays(new PointerPointer((Pointer) this.image_frame), this.image_frame.linesize(), bytePointer2, i8, i2, i3, 1);
                    this.image_frame.linesize(0, abs);
                    this.image_frame.format(i8);
                    this.image_frame.width(i2);
                    this.image_frame.height(i3);
                    int av_buffersrc_add_frame_flags = avfilter.av_buffersrc_add_frame_flags(this.buffersrc_ctx[i], this.image_frame, 12);
                    if (av_buffersrc_add_frame_flags >= 0) {
                        pointerScope.close();
                    } else {
                        throw new FrameFilter.Exception("av_buffersrc_add_frame_flags() error " + av_buffersrc_add_frame_flags + ": Error while feeding the filtergraph.");
                    }
                } else {
                    throw new FrameFilter.Exception("start() was not called successfully!");
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                try {
                    pointerScope.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
                throw th3;
            }
        }
    }

    public synchronized void pushSamples(int i, int i2, int i3, int i4, Buffer... bufferArr) throws FrameFilter.Exception {
        Throwable th;
        int i5;
        int i6;
        Buffer[] bufferArr2 = bufferArr;
        synchronized (this) {
            PointerScope pointerScope = new PointerScope(new Class[0]);
            try {
                if (this.started) {
                    int length = bufferArr2.length;
                    Pointer[] pointerArr = new Pointer[length];
                    int i7 = 1;
                    if (bufferArr2 != null) {
                        i5 = (bufferArr2[0].limit() - bufferArr2[0].position()) / (bufferArr2.length > 1 ? 1 : i2);
                    } else {
                        i5 = 0;
                    }
                    if (bufferArr2 != null && (bufferArr2[0] instanceof ByteBuffer)) {
                        i6 = length > 1 ? 5 : 0;
                        for (int i8 = 0; i8 < length; i8++) {
                            pointerArr[i8] = new BytePointer((ByteBuffer) bufferArr2[i8]);
                        }
                    } else if (bufferArr2 != null && (bufferArr2[0] instanceof ShortBuffer)) {
                        if (length > 1) {
                            i7 = 6;
                        }
                        for (int i9 = 0; i9 < length; i9++) {
                            pointerArr[i9] = new ShortPointer((ShortBuffer) bufferArr2[i9]);
                        }
                    } else if (bufferArr2 != null && (bufferArr2[0] instanceof IntBuffer)) {
                        i6 = length > 1 ? 7 : 2;
                        for (int i10 = 0; i10 < length; i10++) {
                            pointerArr[i10] = new IntPointer((IntBuffer) bufferArr2[i10]);
                        }
                    } else if (bufferArr2 != null && (bufferArr2[0] instanceof FloatBuffer)) {
                        i6 = length > 1 ? 8 : 3;
                        for (int i11 = 0; i11 < length; i11++) {
                            pointerArr[i11] = new FloatPointer((FloatBuffer) bufferArr2[i11]);
                        }
                    } else if (bufferArr2 == null || !(bufferArr2[0] instanceof DoubleBuffer)) {
                        if (bufferArr2 != null) {
                            for (int i12 = 0; i12 < length; i12++) {
                                pointerArr[i12] = new Pointer(bufferArr2[i12]);
                            }
                        }
                        i6 = i4;
                    } else {
                        i6 = length > 1 ? 9 : 4;
                        for (int i13 = 0; i13 < length; i13++) {
                            pointerArr[i13] = new DoublePointer((DoubleBuffer) bufferArr2[i13]);
                        }
                    }
                    avutil.av_samples_fill_arrays(new PointerPointer((Pointer) this.samples_frame), this.samples_frame.linesize(), new BytePointer(pointerArr[0]), i2, i5, i6, 1);
                    for (int i14 = 0; i14 < bufferArr2.length; i14++) {
                        this.samples_frame.data(i14, new BytePointer(pointerArr[i14]));
                    }
                    this.samples_frame.channels(i2);
                    this.samples_frame.channel_layout(avutil.av_get_default_channel_layout(i2));
                    this.samples_frame.nb_samples(i5);
                    this.samples_frame.format(i6);
                    this.samples_frame.sample_rate(i3);
                    int av_buffersrc_add_frame_flags = avfilter.av_buffersrc_add_frame_flags(this.abuffersrc_ctx[i], this.samples_frame, 12);
                    if (av_buffersrc_add_frame_flags >= 0) {
                        pointerScope.close();
                    } else {
                        throw new FrameFilter.Exception("av_buffersrc_add_frame_flags() error " + av_buffersrc_add_frame_flags + ": Error while feeding the filtergraph.");
                    }
                } else {
                    throw new FrameFilter.Exception("start() was not called successfully!");
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                try {
                    pointerScope.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
                throw th3;
            }
        }
    }

    public synchronized Frame pull() throws FrameFilter.Exception {
        Frame pullImage;
        Frame frame2;
        if (this.started) {
            this.frame.keyFrame = false;
            this.frame.imageWidth = 0;
            this.frame.imageHeight = 0;
            this.frame.imageDepth = 0;
            this.frame.imageChannels = 0;
            this.frame.imageStride = 0;
            this.frame.image = null;
            this.frame.sampleRate = 0;
            this.frame.audioChannels = 0;
            this.frame.samples = null;
            this.frame.opaque = null;
            pullImage = this.buffersrc_ctx != null ? pullImage() : null;
            if (pullImage == null && this.abuffersrc_ctx != null) {
                pullImage = pullSamples();
            }
            if (pullImage == null && (frame2 = this.inframe) != null && ((frame2.image != null && this.buffersrc_ctx == null) || (this.inframe.samples != null && this.abuffersrc_ctx == null))) {
                pullImage = this.inframe;
            }
            this.inframe = null;
        } else {
            throw new FrameFilter.Exception("start() was not called successfully!");
        }
        return pullImage;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01f0, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01f5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01f9, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.bytedeco.javacv.Frame pullImage() throws org.bytedeco.javacv.FrameFilter.Exception {
        /*
            r13 = this;
            monitor-enter(r13)
            org.bytedeco.javacpp.PointerScope r0 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x01fa }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x01fa }
            r0.<init>(r2)     // Catch:{ all -> 0x01fa }
            boolean r2 = r13.started     // Catch:{ all -> 0x01ee }
            if (r2 == 0) goto L_0x01e6
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.global.avutil.av_frame_unref(r2)     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avfilter.AVFilterContext r2 = r13.buffersink_ctx     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r2 = org.bytedeco.ffmpeg.global.avfilter.av_buffersink_get_frame(r2, r3)     // Catch:{ all -> 0x01ee }
            int r3 = org.bytedeco.ffmpeg.global.avutil.AVERROR_EAGAIN()     // Catch:{ all -> 0x01ee }
            if (r2 == r3) goto L_0x01e0
            int r3 = org.bytedeco.ffmpeg.global.avutil.AVERROR_EOF()     // Catch:{ all -> 0x01ee }
            if (r2 != r3) goto L_0x0028
            goto L_0x01e0
        L_0x0028:
            if (r2 < 0) goto L_0x01ba
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.width()     // Catch:{ all -> 0x01ee }
            r2.imageWidth = r3     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.height()     // Catch:{ all -> 0x01ee }
            r2.imageHeight = r3     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            r3 = 8
            r2.imageDepth = r3     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            r3 = 1
            org.bytedeco.javacpp.BytePointer r2 = r2.data(r3)     // Catch:{ all -> 0x01ee }
            if (r2 != 0) goto L_0x00c0
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.linesize(r1)     // Catch:{ all -> 0x01ee }
            r2.imageStride = r3     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r2 = r2.data(r1)     // Catch:{ all -> 0x01ee }
            if (r2 == 0) goto L_0x0088
            org.bytedeco.javacpp.BytePointer[] r3 = r13.image_ptr     // Catch:{ all -> 0x01ee }
            r3 = r3[r1]     // Catch:{ all -> 0x01ee }
            boolean r3 = r2.equals(r3)     // Catch:{ all -> 0x01ee }
            if (r3 != 0) goto L_0x0088
            org.bytedeco.javacpp.BytePointer[] r3 = r13.image_ptr     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r4 = r13.frame     // Catch:{ all -> 0x01ee }
            int r4 = r4.imageHeight     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x01ee }
            int r5 = r5.imageStride     // Catch:{ all -> 0x01ee }
            int r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x01ee }
            int r4 = r4 * r5
            long r4 = (long) r4     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r4 = r2.capacity((long) r4)     // Catch:{ all -> 0x01ee }
            r3[r1] = r4     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r3 = r13.image_buf     // Catch:{ all -> 0x01ee }
            java.nio.ByteBuffer r2 = r2.asBuffer()     // Catch:{ all -> 0x01ee }
            r3[r1] = r2     // Catch:{ all -> 0x01ee }
        L_0x0088:
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r3 = r13.image_buf     // Catch:{ all -> 0x01ee }
            r2.image = r3     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r2 = r2.image     // Catch:{ all -> 0x01ee }
            r2 = r2[r1]     // Catch:{ all -> 0x01ee }
            java.nio.Buffer r1 = r2.position(r1)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            int r2 = r2.imageHeight     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r3 = r13.frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.imageStride     // Catch:{ all -> 0x01ee }
            int r3 = java.lang.Math.abs(r3)     // Catch:{ all -> 0x01ee }
            int r2 = r2 * r3
            r1.limit(r2)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x01ee }
            int r2 = r1.imageStride     // Catch:{ all -> 0x01ee }
            int r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r3 = r13.frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.imageWidth     // Catch:{ all -> 0x01ee }
            int r2 = r2 / r3
            r1.imageChannels = r2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            r1.opaque = r2     // Catch:{ all -> 0x01ee }
            goto L_0x0177
        L_0x00c0:
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            int r4 = r2.imageWidth     // Catch:{ all -> 0x01ee }
            r2.imageStride = r4     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r2 = r2.format()     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r4 = r13.frame     // Catch:{ all -> 0x01ee }
            int r4 = r4.imageWidth     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x01ee }
            int r5 = r5.imageHeight     // Catch:{ all -> 0x01ee }
            int r2 = org.bytedeco.ffmpeg.global.avutil.av_image_get_buffer_size(r2, r4, r5, r3)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r4 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r5 = r4[r1]     // Catch:{ all -> 0x01ee }
            if (r5 == 0) goto L_0x00e9
            r4 = r4[r1]     // Catch:{ all -> 0x01ee }
            long r4 = r4.capacity()     // Catch:{ all -> 0x01ee }
            long r6 = (long) r2     // Catch:{ all -> 0x01ee }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x010e
        L_0x00e9:
            org.bytedeco.javacpp.BytePointer[] r4 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r4 = r4[r1]     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.global.avutil.av_free(r4)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r4 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r5 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x01ee }
            long r6 = (long) r2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.Pointer r8 = org.bytedeco.ffmpeg.global.avutil.av_malloc(r6)     // Catch:{ all -> 0x01ee }
            r5.<init>((org.bytedeco.javacpp.Pointer) r8)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r5 = r5.capacity((long) r6)     // Catch:{ all -> 0x01ee }
            r4[r1] = r5     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r4 = r13.image_buf2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r5 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r5 = r5[r1]     // Catch:{ all -> 0x01ee }
            java.nio.ByteBuffer r5 = r5.asBuffer()     // Catch:{ all -> 0x01ee }
            r4[r1] = r5     // Catch:{ all -> 0x01ee }
        L_0x010e:
            org.bytedeco.javacv.Frame r4 = r13.frame     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r5 = r13.image_buf2     // Catch:{ all -> 0x01ee }
            r4.image = r5     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r4 = r13.frame     // Catch:{ all -> 0x01ee }
            java.nio.Buffer[] r4 = r4.image     // Catch:{ all -> 0x01ee }
            r4 = r4[r1]     // Catch:{ all -> 0x01ee }
            java.nio.Buffer r4 = r4.position(r1)     // Catch:{ all -> 0x01ee }
            r4.limit(r2)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r4 = r13.frame     // Catch:{ all -> 0x01ee }
            int r5 = r4.imageWidth     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r6 = r13.frame     // Catch:{ all -> 0x01ee }
            int r6 = r6.imageHeight     // Catch:{ all -> 0x01ee }
            int r5 = r5 * r6
            int r2 = r2 + r5
            int r2 = r2 - r3
            org.bytedeco.javacv.Frame r3 = r13.frame     // Catch:{ all -> 0x01ee }
            int r3 = r3.imageWidth     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x01ee }
            int r5 = r5.imageHeight     // Catch:{ all -> 0x01ee }
            int r3 = r3 * r5
            int r2 = r2 / r3
            r4.imageChannels = r2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r2 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r2 = r2[r1]     // Catch:{ all -> 0x01ee }
            r3 = 0
            org.bytedeco.javacpp.BytePointer r5 = r2.position((long) r3)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r2 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r2 = r2[r1]     // Catch:{ all -> 0x01ee }
            long r2 = r2.capacity()     // Catch:{ all -> 0x01ee }
            int r6 = (int) r2     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.PointerPointer r7 = new org.bytedeco.javacpp.PointerPointer     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            r7.<init>((org.bytedeco.javacpp.Pointer) r2)     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.IntPointer r8 = r2.linesize()     // Catch:{ all -> 0x01ee }
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            int r9 = r2.format()     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            int r10 = r2.imageWidth     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            int r11 = r2.imageHeight     // Catch:{ all -> 0x01ee }
            r12 = 1
            int r2 = org.bytedeco.ffmpeg.global.avutil.av_image_copy_to_buffer((org.bytedeco.javacpp.BytePointer) r5, (int) r6, (org.bytedeco.javacpp.PointerPointer) r7, (org.bytedeco.javacpp.IntPointer) r8, (int) r9, (int) r10, (int) r11, (int) r12)     // Catch:{ all -> 0x01ee }
            if (r2 < 0) goto L_0x019e
            org.bytedeco.javacv.Frame r2 = r13.frame     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer[] r3 = r13.image_ptr2     // Catch:{ all -> 0x01ee }
            r1 = r3[r1]     // Catch:{ all -> 0x01ee }
            r2.opaque = r1     // Catch:{ all -> 0x01ee }
        L_0x0177:
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x01ee }
            r2 = 1000000(0xf4240, double:4.940656E-318)
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r13.filt_frame     // Catch:{ all -> 0x01ee }
            long r4 = r4.pts()     // Catch:{ all -> 0x01ee }
            long r4 = r4 * r2
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r13.time_base     // Catch:{ all -> 0x01ee }
            int r2 = r2.num()     // Catch:{ all -> 0x01ee }
            long r2 = (long) r2     // Catch:{ all -> 0x01ee }
            long r4 = r4 * r2
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r13.time_base     // Catch:{ all -> 0x01ee }
            int r2 = r2.den()     // Catch:{ all -> 0x01ee }
            long r2 = (long) r2     // Catch:{ all -> 0x01ee }
            long r4 = r4 / r2
            r1.timestamp = r4     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x01ee }
            r0.close()     // Catch:{ all -> 0x01fa }
            monitor-exit(r13)
            return r1
        L_0x019e:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x01ee }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ee }
            r3.<init>()     // Catch:{ all -> 0x01ee }
            java.lang.String r4 = "av_image_copy_to_buffer() error "
            r3.append(r4)     // Catch:{ all -> 0x01ee }
            r3.append(r2)     // Catch:{ all -> 0x01ee }
            java.lang.String r2 = ": Cannot pull image."
            r3.append(r2)     // Catch:{ all -> 0x01ee }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x01ee }
            r1.<init>(r2)     // Catch:{ all -> 0x01ee }
            throw r1     // Catch:{ all -> 0x01ee }
        L_0x01ba:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x01ee }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ee }
            r3.<init>()     // Catch:{ all -> 0x01ee }
            java.lang.String r4 = "av_buffersink_get_frame(): Error occurred: "
            r3.append(r4)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r4 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x01ee }
            r5 = 256(0x100, double:1.265E-321)
            r4.<init>((long) r5)     // Catch:{ all -> 0x01ee }
            org.bytedeco.javacpp.BytePointer r2 = org.bytedeco.ffmpeg.global.avutil.av_make_error_string((org.bytedeco.javacpp.BytePointer) r4, (long) r5, (int) r2)     // Catch:{ all -> 0x01ee }
            java.lang.String r2 = r2.getString()     // Catch:{ all -> 0x01ee }
            r3.append(r2)     // Catch:{ all -> 0x01ee }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x01ee }
            r1.<init>(r2)     // Catch:{ all -> 0x01ee }
            throw r1     // Catch:{ all -> 0x01ee }
        L_0x01e0:
            r1 = 0
            r0.close()     // Catch:{ all -> 0x01fa }
            monitor-exit(r13)
            return r1
        L_0x01e6:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x01ee }
            java.lang.String r2 = "start() was not called successfully!"
            r1.<init>(r2)     // Catch:{ all -> 0x01ee }
            throw r1     // Catch:{ all -> 0x01ee }
        L_0x01ee:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x01f0 }
        L_0x01f0:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x01f5 }
            goto L_0x01f9
        L_0x01f5:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x01fa }
        L_0x01f9:
            throw r2     // Catch:{ all -> 0x01fa }
        L_0x01fa:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameFilter.pullImage():org.bytedeco.javacv.Frame");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0151, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0156, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x015a, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.bytedeco.javacv.Frame pullSamples() throws org.bytedeco.javacv.FrameFilter.Exception {
        /*
            r13 = this;
            monitor-enter(r13)
            org.bytedeco.javacpp.PointerScope r0 = new org.bytedeco.javacpp.PointerScope     // Catch:{ all -> 0x015b }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ all -> 0x015b }
            r0.<init>(r2)     // Catch:{ all -> 0x015b }
            boolean r2 = r13.started     // Catch:{ all -> 0x014f }
            if (r2 == 0) goto L_0x0147
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.global.avutil.av_frame_unref(r2)     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avfilter.AVFilterContext r2 = r13.abuffersink_ctx     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r2 = org.bytedeco.ffmpeg.global.avfilter.av_buffersink_get_frame(r2, r3)     // Catch:{ all -> 0x014f }
            int r3 = org.bytedeco.ffmpeg.global.avutil.AVERROR_EAGAIN()     // Catch:{ all -> 0x014f }
            r4 = 0
            if (r2 == r3) goto L_0x0142
            int r3 = org.bytedeco.ffmpeg.global.avutil.AVERROR_EOF()     // Catch:{ all -> 0x014f }
            if (r2 != r3) goto L_0x0029
            goto L_0x0142
        L_0x0029:
            if (r2 < 0) goto L_0x011c
            org.bytedeco.ffmpeg.avutil.AVFrame r2 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r2 = r2.format()     // Catch:{ all -> 0x014f }
            int r3 = org.bytedeco.ffmpeg.global.avutil.av_sample_fmt_is_planar(r2)     // Catch:{ all -> 0x014f }
            r5 = 1
            if (r3 == 0) goto L_0x003f
            org.bytedeco.ffmpeg.avutil.AVFrame r3 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r3 = r3.channels()     // Catch:{ all -> 0x014f }
            goto L_0x0040
        L_0x003f:
            r3 = 1
        L_0x0040:
            org.bytedeco.javacpp.IntPointer r4 = (org.bytedeco.javacpp.IntPointer) r4     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r6 = r6.channels()     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r7 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r7 = r7.nb_samples()     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r8 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r8 = r8.format()     // Catch:{ all -> 0x014f }
            int r4 = org.bytedeco.ffmpeg.global.avutil.av_samples_get_buffer_size((org.bytedeco.javacpp.IntPointer) r4, (int) r6, (int) r7, (int) r8, (int) r5)     // Catch:{ all -> 0x014f }
            int r4 = r4 / r3
            java.nio.Buffer[] r5 = r13.samples_buf     // Catch:{ all -> 0x014f }
            if (r5 == 0) goto L_0x0060
            int r5 = r5.length     // Catch:{ all -> 0x014f }
            if (r5 == r3) goto L_0x0068
        L_0x0060:
            org.bytedeco.javacpp.BytePointer[] r5 = new org.bytedeco.javacpp.BytePointer[r3]     // Catch:{ all -> 0x014f }
            r13.samples_ptr = r5     // Catch:{ all -> 0x014f }
            java.nio.Buffer[] r5 = new java.nio.Buffer[r3]     // Catch:{ all -> 0x014f }
            r13.samples_buf = r5     // Catch:{ all -> 0x014f }
        L_0x0068:
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r6 = r6.channels()     // Catch:{ all -> 0x014f }
            r5.audioChannels = r6     // Catch:{ all -> 0x014f }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r13.filt_frame     // Catch:{ all -> 0x014f }
            int r6 = r6.sample_rate()     // Catch:{ all -> 0x014f }
            r5.sampleRate = r6     // Catch:{ all -> 0x014f }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x014f }
            java.nio.Buffer[] r6 = r13.samples_buf     // Catch:{ all -> 0x014f }
            r5.samples = r6     // Catch:{ all -> 0x014f }
            org.bytedeco.javacv.Frame r5 = r13.frame     // Catch:{ all -> 0x014f }
            org.bytedeco.ffmpeg.avutil.AVFrame r6 = r13.filt_frame     // Catch:{ all -> 0x014f }
            r5.opaque = r6     // Catch:{ all -> 0x014f }
            int r5 = org.bytedeco.ffmpeg.global.avutil.av_get_bytes_per_sample(r2)     // Catch:{ all -> 0x014f }
            int r5 = r4 / r5
            r6 = 0
        L_0x008f:
            if (r6 >= r3) goto L_0x00f5
            org.bytedeco.ffmpeg.avutil.AVFrame r7 = r13.filt_frame     // Catch:{ all -> 0x014f }
            org.bytedeco.javacpp.BytePointer r7 = r7.data(r6)     // Catch:{ all -> 0x014f }
            org.bytedeco.javacpp.BytePointer[] r8 = r13.samples_ptr     // Catch:{ all -> 0x014f }
            r8 = r8[r6]     // Catch:{ all -> 0x014f }
            boolean r8 = r7.equals(r8)     // Catch:{ all -> 0x014f }
            if (r8 == 0) goto L_0x00ae
            org.bytedeco.javacpp.BytePointer[] r8 = r13.samples_ptr     // Catch:{ all -> 0x014f }
            r8 = r8[r6]     // Catch:{ all -> 0x014f }
            long r8 = r8.capacity()     // Catch:{ all -> 0x014f }
            long r10 = (long) r4     // Catch:{ all -> 0x014f }
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 >= 0) goto L_0x00e7
        L_0x00ae:
            org.bytedeco.javacpp.BytePointer[] r8 = r13.samples_ptr     // Catch:{ all -> 0x014f }
            long r9 = (long) r4     // Catch:{ all -> 0x014f }
            org.bytedeco.javacpp.BytePointer r9 = r7.capacity((long) r9)     // Catch:{ all -> 0x014f }
            r8[r6] = r9     // Catch:{ all -> 0x014f }
            java.nio.ByteBuffer r7 = r7.asBuffer()     // Catch:{ all -> 0x014f }
            switch(r2) {
                case 0: goto L_0x00e3;
                case 1: goto L_0x00da;
                case 2: goto L_0x00d1;
                case 3: goto L_0x00c8;
                case 4: goto L_0x00bf;
                case 5: goto L_0x00e3;
                case 6: goto L_0x00da;
                case 7: goto L_0x00d1;
                case 8: goto L_0x00c8;
                case 9: goto L_0x00bf;
                default: goto L_0x00be;
            }     // Catch:{ all -> 0x014f }
        L_0x00be:
            goto L_0x00e7
        L_0x00bf:
            java.nio.Buffer[] r8 = r13.samples_buf     // Catch:{ all -> 0x014f }
            java.nio.DoubleBuffer r7 = r7.asDoubleBuffer()     // Catch:{ all -> 0x014f }
            r8[r6] = r7     // Catch:{ all -> 0x014f }
            goto L_0x00e7
        L_0x00c8:
            java.nio.Buffer[] r8 = r13.samples_buf     // Catch:{ all -> 0x014f }
            java.nio.FloatBuffer r7 = r7.asFloatBuffer()     // Catch:{ all -> 0x014f }
            r8[r6] = r7     // Catch:{ all -> 0x014f }
            goto L_0x00e7
        L_0x00d1:
            java.nio.Buffer[] r8 = r13.samples_buf     // Catch:{ all -> 0x014f }
            java.nio.IntBuffer r7 = r7.asIntBuffer()     // Catch:{ all -> 0x014f }
            r8[r6] = r7     // Catch:{ all -> 0x014f }
            goto L_0x00e7
        L_0x00da:
            java.nio.Buffer[] r8 = r13.samples_buf     // Catch:{ all -> 0x014f }
            java.nio.ShortBuffer r7 = r7.asShortBuffer()     // Catch:{ all -> 0x014f }
            r8[r6] = r7     // Catch:{ all -> 0x014f }
            goto L_0x00e7
        L_0x00e3:
            java.nio.Buffer[] r8 = r13.samples_buf     // Catch:{ all -> 0x014f }
            r8[r6] = r7     // Catch:{ all -> 0x014f }
        L_0x00e7:
            java.nio.Buffer[] r7 = r13.samples_buf     // Catch:{ all -> 0x014f }
            r7 = r7[r6]     // Catch:{ all -> 0x014f }
            java.nio.Buffer r7 = r7.position(r1)     // Catch:{ all -> 0x014f }
            r7.limit(r5)     // Catch:{ all -> 0x014f }
            int r6 = r6 + 1
            goto L_0x008f
        L_0x00f5:
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x014f }
            r2 = 1000000(0xf4240, double:4.940656E-318)
            org.bytedeco.ffmpeg.avutil.AVFrame r4 = r13.filt_frame     // Catch:{ all -> 0x014f }
            long r4 = r4.pts()     // Catch:{ all -> 0x014f }
            long r4 = r4 * r2
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r13.atime_base     // Catch:{ all -> 0x014f }
            int r2 = r2.num()     // Catch:{ all -> 0x014f }
            long r2 = (long) r2     // Catch:{ all -> 0x014f }
            long r4 = r4 * r2
            org.bytedeco.ffmpeg.avutil.AVRational r2 = r13.atime_base     // Catch:{ all -> 0x014f }
            int r2 = r2.den()     // Catch:{ all -> 0x014f }
            long r2 = (long) r2     // Catch:{ all -> 0x014f }
            long r4 = r4 / r2
            r1.timestamp = r4     // Catch:{ all -> 0x014f }
            org.bytedeco.javacv.Frame r1 = r13.frame     // Catch:{ all -> 0x014f }
            r0.close()     // Catch:{ all -> 0x015b }
            monitor-exit(r13)
            return r1
        L_0x011c:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x014f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r3.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "av_buffersink_get_frame(): Error occurred: "
            r3.append(r4)     // Catch:{ all -> 0x014f }
            org.bytedeco.javacpp.BytePointer r4 = new org.bytedeco.javacpp.BytePointer     // Catch:{ all -> 0x014f }
            r5 = 256(0x100, double:1.265E-321)
            r4.<init>((long) r5)     // Catch:{ all -> 0x014f }
            org.bytedeco.javacpp.BytePointer r2 = org.bytedeco.ffmpeg.global.avutil.av_make_error_string((org.bytedeco.javacpp.BytePointer) r4, (long) r5, (int) r2)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r2.getString()     // Catch:{ all -> 0x014f }
            r3.append(r2)     // Catch:{ all -> 0x014f }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x014f }
            r1.<init>(r2)     // Catch:{ all -> 0x014f }
            throw r1     // Catch:{ all -> 0x014f }
        L_0x0142:
            r0.close()     // Catch:{ all -> 0x015b }
            monitor-exit(r13)
            return r4
        L_0x0147:
            org.bytedeco.javacv.FrameFilter$Exception r1 = new org.bytedeco.javacv.FrameFilter$Exception     // Catch:{ all -> 0x014f }
            java.lang.String r2 = "start() was not called successfully!"
            r1.<init>(r2)     // Catch:{ all -> 0x014f }
            throw r1     // Catch:{ all -> 0x014f }
        L_0x014f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0151 }
        L_0x0151:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0156 }
            goto L_0x015a
        L_0x0156:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x015b }
        L_0x015a:
            throw r2     // Catch:{ all -> 0x015b }
        L_0x015b:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.FFmpegFrameFilter.pullSamples():org.bytedeco.javacv.Frame");
    }
}
