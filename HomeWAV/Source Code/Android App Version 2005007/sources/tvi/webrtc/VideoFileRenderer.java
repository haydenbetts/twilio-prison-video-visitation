package tvi.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.exoplayer2.C;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import tvi.webrtc.EglBase;
import tvi.webrtc.VideoFrame;
import tvi.webrtc.VideoRenderer;

@JNINamespace("webrtc::jni")
public class VideoFileRenderer implements VideoRenderer.Callbacks, VideoSink {
    private static final String TAG = "VideoFileRenderer";
    /* access modifiers changed from: private */
    public EglBase eglBase;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private ArrayList<ByteBuffer> rawFrames = new ArrayList<>();
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    /* access modifiers changed from: private */
    public YuvConverter yuvConverter;

    public VideoFileRenderer(String str, int i, int i2, final EglBase.Context context) throws IOException {
        if (i % 2 == 1 || i2 % 2 == 1) {
            throw new IllegalArgumentException("Does not support uneven width or height");
        }
        this.outputFileName = str;
        this.outputFileWidth = i;
        this.outputFileHeight = i2;
        int i3 = ((i * i2) * 3) / 2;
        this.outputFrameSize = i3;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(i3);
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        this.videoOutFile = fileOutputStream;
        fileOutputStream.write(("YUV4MPEG2 C420 W" + i + " H" + i2 + " Ip F30:1 A1:1\n").getBytes(Charset.forName(C.ASCII_NAME)));
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.renderThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.renderThreadHandler = handler;
        ThreadUtils.invokeAtFrontUninterruptibly(handler, (Runnable) new Runnable() {
            public void run() {
                EglBase unused = VideoFileRenderer.this.eglBase = EglBase.CC.create(context, EglBase.CONFIG_PIXEL_BUFFER);
                VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                VideoFileRenderer.this.eglBase.makeCurrent();
                YuvConverter unused2 = VideoFileRenderer.this.yuvConverter = new YuvConverter();
            }
        });
    }

    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        VideoFrame videoFrame = i420Frame.toVideoFrame();
        onFrame(videoFrame);
        videoFrame.release();
    }

    public void onFrame(VideoFrame videoFrame) {
        videoFrame.retain();
        this.renderThreadHandler.post(new Runnable(videoFrame) {
            public final /* synthetic */ VideoFrame f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VideoFileRenderer.this.lambda$onFrame$0$VideoFileRenderer(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: renderFrameOnRenderThread */
    public void lambda$onFrame$0$VideoFileRenderer(VideoFrame videoFrame) {
        VideoFrame.Buffer buffer = videoFrame.getBuffer();
        int i = videoFrame.getRotation() % 180 == 0 ? this.outputFileWidth : this.outputFileHeight;
        int i2 = videoFrame.getRotation() % 180 == 0 ? this.outputFileHeight : this.outputFileWidth;
        float width = ((float) buffer.getWidth()) / ((float) buffer.getHeight());
        float f = ((float) i) / ((float) i2);
        int width2 = buffer.getWidth();
        int height = buffer.getHeight();
        if (f > width) {
            height = (int) (((float) height) * (width / f));
        } else {
            width2 = (int) (((float) width2) * (f / width));
        }
        VideoFrame.Buffer cropAndScale = buffer.cropAndScale((buffer.getWidth() - width2) / 2, (buffer.getHeight() - height) / 2, width2, height, i, i2);
        videoFrame.release();
        VideoFrame.I420Buffer i420 = cropAndScale.toI420();
        cropAndScale.release();
        ByteBuffer nativeAllocateByteBuffer = JniCommon.nativeAllocateByteBuffer(this.outputFrameSize);
        YuvHelper.I420Rotate(i420.getDataY(), i420.getStrideY(), i420.getDataU(), i420.getStrideU(), i420.getDataV(), i420.getStrideV(), nativeAllocateByteBuffer, i420.getWidth(), i420.getHeight(), videoFrame.getRotation());
        i420.release();
        nativeAllocateByteBuffer.rewind();
        this.rawFrames.add(nativeAllocateByteBuffer);
    }

    public void release() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable(countDownLatch) {
            public final /* synthetic */ CountDownLatch f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VideoFileRenderer.this.lambda$release$1$VideoFileRenderer(this.f$1);
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
        try {
            Iterator<ByteBuffer> it = this.rawFrames.iterator();
            while (it.hasNext()) {
                ByteBuffer next = it.next();
                this.videoOutFile.write("FRAME\n".getBytes(Charset.forName(C.ASCII_NAME)));
                byte[] bArr = new byte[this.outputFrameSize];
                next.get(bArr);
                this.videoOutFile.write(bArr);
                JniCommon.nativeFreeByteBuffer(next);
            }
            this.videoOutFile.close();
            Logging.d(TAG, "Video written to disk as " + this.outputFileName + ". Number frames are " + this.rawFrames.size() + " and the dimension of the frames are " + this.outputFileWidth + "x" + this.outputFileHeight + ".");
        } catch (IOException e) {
            Logging.e(TAG, "Error writing video to disk", e);
        }
    }

    public /* synthetic */ void lambda$release$1$VideoFileRenderer(CountDownLatch countDownLatch) {
        this.yuvConverter.release();
        this.eglBase.release();
        this.renderThread.quit();
        countDownLatch.countDown();
    }
}
