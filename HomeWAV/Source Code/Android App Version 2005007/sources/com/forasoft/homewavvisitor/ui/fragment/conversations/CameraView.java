package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.AudioRecord;
import android.os.Process;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.renderscript.RenderScript;
import com.forasoft.homewavvisitor.ui.fragment.conversations.CameraView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import kotlin.UByte;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;
import timber.log.Timber;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private int audioCodec;
    /* access modifiers changed from: private */
    public AudioRecord audioRecord;
    private AudioRecordRunnable audioRecordRunnable;
    private Thread audioThread;
    private Integer bitrate;
    private Camera camera;
    private int cameraId;
    private int facing;
    private String filename;
    private String format;
    private int fps;
    private int imageHeight;
    private int imageWidth;
    public boolean isPreviewOn;
    private SurfaceHolder mHolder;
    /* access modifiers changed from: private */
    public FFmpegFrameRecorder recorder;
    public boolean recording;
    private RenderScript renderScript;
    private int rotation;
    volatile boolean runAudioThread;
    /* access modifiers changed from: private */
    public int sampleAudioRateInHz;
    ShortBuffer[] samples;
    int samplesIndex;
    long startTime;
    private int videoCodec;
    private Frame yuvImage;

    public interface PictureCallback {
        void onPictureAvailable(File file);
    }

    private CameraView(Builder builder) {
        super(builder.context);
        this.cameraId = 0;
        this.runAudioThread = true;
        this.sampleAudioRateInHz = 44100;
        this.startTime = 0;
        this.recording = false;
        this.renderScript = RenderScript.create(builder.context);
        this.filename = builder.filename;
        this.format = builder.format;
        this.rotation = builder.rotation;
        this.facing = builder.facing;
        this.imageWidth = builder.imageWidth;
        this.imageHeight = builder.imageHeight;
        this.fps = builder.fps;
        this.videoCodec = builder.videoCodec;
        this.audioCodec = builder.audioCodec;
        this.bitrate = builder.bitrate;
        try {
            this.camera = getCamera(builder.facing);
            setCameraOrientation(this.rotation);
            updateImageDims();
            SurfaceHolder holder = getHolder();
            this.mHolder = holder;
            holder.addCallback(this);
            this.mHolder.setType(3);
            initRecorder();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Camera camera2 = this.camera;
        if (camera2 != null) {
            camera2.release();
        }
        if (this.recording) {
            stopRecording();
        }
    }

    private Camera getCamera(int i) throws RuntimeException {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i2 = 0;
        while (i2 < numberOfCameras) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                try {
                    this.cameraId = i2;
                    return Camera.open(i2);
                } catch (RuntimeException unused) {
                    throw new RuntimeException("cannot open a camera");
                }
            } else {
                i2++;
            }
        }
        throw new RuntimeException("cannot find camera");
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            Camera.Parameters parameters = camera2.getParameters();
            parameters.setPreviewSize(this.imageWidth, this.imageHeight);
            parameters.setPictureSize(this.imageWidth, this.imageHeight);
            this.camera.setParameters(parameters);
            this.camera.startPreview();
            startPreview();
        }
    }

    public void startPreview() {
        Camera camera2;
        if (!this.isPreviewOn && (camera2 = this.camera) != null) {
            this.isPreviewOn = true;
            try {
                camera2.setPreviewDisplay(this.mHolder);
                this.camera.setPreviewCallback(this);
                this.mHolder.addCallback(this);
                this.camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPreview() {
        Camera camera2;
        if (this.isPreviewOn && (camera2 = this.camera) != null) {
            this.isPreviewOn = false;
            camera2.stopPreview();
            this.camera.setPreviewCallback((Camera.PreviewCallback) null);
            this.mHolder.removeCallback(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCameraOrientation(int r6) {
        /*
            r5 = this;
            android.hardware.Camera$CameraInfo r0 = new android.hardware.Camera$CameraInfo
            r0.<init>()
            int r1 = r5.cameraId
            android.hardware.Camera.getCameraInfo(r1, r0)
            r1 = 1
            r2 = 0
            if (r6 == 0) goto L_0x0016
            if (r6 == r1) goto L_0x001e
            r3 = 2
            if (r6 == r3) goto L_0x001b
            r3 = 3
            if (r6 == r3) goto L_0x0018
        L_0x0016:
            r6 = 0
            goto L_0x0020
        L_0x0018:
            r6 = 270(0x10e, float:3.78E-43)
            goto L_0x0020
        L_0x001b:
            r6 = 180(0xb4, float:2.52E-43)
            goto L_0x0020
        L_0x001e:
            r6 = 90
        L_0x0020:
            java.lang.Object[] r3 = new java.lang.Object[r1]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            r3[r2] = r4
            java.lang.String r2 = "Rotation %s"
            timber.log.Timber.d(r2, r3)
            int r2 = r0.facing
            if (r2 != r1) goto L_0x003b
            int r0 = r0.orientation
            int r0 = r0 + r6
            int r0 = r0 % 360
            int r6 = 360 - r0
            int r6 = r6 % 360
            goto L_0x0042
        L_0x003b:
            int r0 = r0.orientation
            int r0 = r0 - r6
            int r0 = r0 + 360
            int r6 = r0 % 360
        L_0x0042:
            android.hardware.Camera r0 = r5.camera
            if (r0 == 0) goto L_0x0049
            r0.setDisplayOrientation(r6)
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.conversations.CameraView.setCameraOrientation(int):void");
    }

    public void updateImageDims() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            Camera.Parameters parameters = camera2.getParameters();
            parameters.setPreviewSize(this.imageWidth, this.imageHeight);
            this.camera.setParameters(parameters);
        }
    }

    private void initRecorder() {
        if (this.format.equals("mp4")) {
            this.recorder = new FFmpegFrameRecorder(this.filename, this.imageWidth, this.imageHeight, 1);
            this.yuvImage = new Frame(this.imageWidth, this.imageHeight, 8, 2);
        } else {
            this.recorder = new FFmpegFrameRecorder(this.filename, this.imageHeight, this.imageWidth, 1);
            this.yuvImage = new Frame(this.imageHeight, this.imageWidth, 8, 2);
        }
        this.recorder.setFormat(this.format);
        this.recorder.setVideoCodec(this.videoCodec);
        this.recorder.setAudioCodec(this.audioCodec);
        this.recorder.setSampleRate(this.sampleAudioRateInHz);
        this.recorder.setFrameRate((double) this.fps);
        Integer num = this.bitrate;
        if (num != null) {
            this.recorder.setVideoBitrate(num.intValue());
        }
        this.recorder.setVideoOption("preset", "ultrafast");
        if (this.facing == 0) {
            this.recorder.setVideoMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION, "90");
        } else {
            this.recorder.setVideoMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION, "270");
        }
        this.recorder.setOption("type", "record");
        this.audioRecordRunnable = new AudioRecordRunnable();
        this.audioThread = new Thread(this.audioRecordRunnable);
        this.runAudioThread = true;
    }

    public void takePicture(PictureCallback pictureCallback) {
        this.camera.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, (Camera.PictureCallback) null, new Camera.PictureCallback(pictureCallback) {
            public final /* synthetic */ CameraView.PictureCallback f$1;

            {
                this.f$1 = r2;
            }

            public final void onPictureTaken(byte[] bArr, Camera camera) {
                CameraView.this.lambda$takePicture$0$CameraView(this.f$1, bArr, camera);
            }
        });
    }

    public /* synthetic */ void lambda$takePicture$0$CameraView(PictureCallback pictureCallback, byte[] bArr, Camera camera2) {
        try {
            File filesDir = getContext().getFilesDir();
            File file = new File(filesDir, System.currentTimeMillis() + ".jpeg");
            Matrix matrix = new Matrix();
            if (this.facing == 0) {
                matrix.postRotate(90.0f);
            } else {
                matrix.postRotate(270.0f);
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
            decodeByteArray.recycle();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            pictureCallback.onPictureAvailable(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean startRecording() {
        try {
            if (this.recorder == null) {
                initRecorder();
            }
            this.recorder.start();
            this.startTime = System.currentTimeMillis();
            this.recording = true;
            this.audioThread.start();
            return true;
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stopRecording() {
        this.runAudioThread = false;
        try {
            Thread thread = this.audioThread;
            if (thread != null) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.audioRecordRunnable = null;
        this.audioThread = null;
        FFmpegFrameRecorder fFmpegFrameRecorder = this.recorder;
        if (fFmpegFrameRecorder != null && this.recording) {
            this.recording = false;
            try {
                fFmpegFrameRecorder.stop();
                this.recorder.release();
            } catch (FrameRecorder.Exception e2) {
                e2.printStackTrace();
            }
            this.recorder = null;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.camera.setPreviewDisplay(surfaceHolder);
            this.camera.setPreviewCallback(this);
        } catch (Exception unused) {
            this.camera.release();
            this.camera = null;
        }
    }

    public void onPreviewFrame(byte[] bArr, Camera camera2) {
        if (this.recording && this.yuvImage != null) {
            if (this.format.equals("mp4")) {
                ((ByteBuffer) this.yuvImage.image[0].position(0)).put(bArr);
            } else if (this.facing == 0) {
                ((ByteBuffer) this.yuvImage.image[0].position(0)).put(rotateNV21(bArr, this.imageWidth, this.imageHeight, 90));
            } else {
                ((ByteBuffer) this.yuvImage.image[0].position(0)).put(rotateNV21(bArr, this.imageWidth, this.imageHeight, 270));
            }
            try {
                long currentTimeMillis = (System.currentTimeMillis() - this.startTime) * 1000;
                if (currentTimeMillis > this.recorder.getTimestamp()) {
                    this.recorder.setTimestamp(currentTimeMillis);
                    this.recorder.record(this.yuvImage);
                }
            } catch (FrameRecorder.Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] rotateNV21(byte[] bArr, int i, int i2, int i3) {
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i6 == 0) {
            return bArr2;
        }
        if (i6 % 90 != 0 || i6 < 0 || i6 > 270) {
            throw new IllegalArgumentException("0 <= rotation < 360, rotation % 90 == 0");
        }
        byte[] bArr3 = new byte[bArr2.length];
        int i7 = i4 * i5;
        boolean z = i6 % 180 != 0;
        boolean z2 = i6 % 270 != 0;
        boolean z3 = i6 >= 180;
        for (int i8 = 0; i8 < i5; i8++) {
            for (int i9 = 0; i9 < i4; i9++) {
                int i10 = (i8 * i4) + i9;
                int i11 = ((i8 >> 1) * i4) + i7 + (i9 & -2);
                int i12 = i11 + 1;
                int i13 = z ? i5 : i4;
                int i14 = z ? i4 : i5;
                int i15 = z ? i8 : i9;
                int i16 = z ? i9 : i8;
                if (z2) {
                    i15 = (i13 - i15) - 1;
                }
                if (z3) {
                    i16 = (i14 - i16) - 1;
                }
                int i17 = i7 + ((i16 >> 1) * i13) + (i15 & -2);
                bArr3[(i16 * i13) + i15] = (byte) (bArr2[i10] & UByte.MAX_VALUE);
                bArr3[i17] = (byte) (bArr2[i11] & UByte.MAX_VALUE);
                bArr3[i17 + 1] = (byte) (bArr2[i12] & UByte.MAX_VALUE);
            }
        }
        return bArr3;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            this.mHolder.addCallback((SurfaceHolder.Callback) null);
            this.camera.setPreviewCallback((Camera.PreviewCallback) null);
        } catch (RuntimeException unused) {
        }
    }

    class AudioRecordRunnable implements Runnable {
        AudioRecordRunnable() {
        }

        public void run() {
            Process.setThreadPriority(-19);
            int minBufferSize = AudioRecord.getMinBufferSize(CameraView.this.sampleAudioRateInHz, 16, 2);
            AudioRecord unused = CameraView.this.audioRecord = new AudioRecord(1, CameraView.this.sampleAudioRateInHz, 16, 2, minBufferSize);
            CameraView.this.samplesIndex = 0;
            CameraView cameraView = CameraView.this;
            cameraView.samples = new ShortBuffer[((((cameraView.sampleAudioRateInHz * 10) * 2) / minBufferSize) + 1)];
            for (int i = 0; i < CameraView.this.samples.length; i++) {
                CameraView.this.samples[i] = ShortBuffer.allocate(minBufferSize);
            }
            Timber.d("audioRecord.startRecording()", new Object[0]);
            CameraView.this.audioRecord.startRecording();
            while (CameraView.this.runAudioThread) {
                try {
                    ShortBuffer[] shortBufferArr = CameraView.this.samples;
                    CameraView cameraView2 = CameraView.this;
                    int i2 = cameraView2.samplesIndex;
                    cameraView2.samplesIndex = i2 + 1;
                    ShortBuffer shortBuffer = shortBufferArr[i2 % CameraView.this.samples.length];
                    shortBuffer.position(0).limit(0);
                    int read = CameraView.this.audioRecord.read(shortBuffer.array(), 0, shortBuffer.capacity());
                    shortBuffer.limit(read);
                    if (read > 0 && CameraView.this.recording) {
                        try {
                            CameraView.this.recorder.recordSamples(shortBuffer);
                        } catch (FrameRecorder.Exception e) {
                            Timber.d(e.getMessage(), new Object[0]);
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Timber.d("AudioThread Finished, release audioRecord", new Object[0]);
            if (CameraView.this.audioRecord != null) {
                CameraView.this.audioRecord.stop();
                CameraView.this.audioRecord.release();
                AudioRecord unused2 = CameraView.this.audioRecord = null;
                Timber.d("audioRecord released", new Object[0]);
            }
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int audioCodec;
        /* access modifiers changed from: private */
        public Integer bitrate;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public int facing;
        /* access modifiers changed from: private */
        public String filename;
        /* access modifiers changed from: private */
        public String format;
        /* access modifiers changed from: private */
        public int fps;
        /* access modifiers changed from: private */
        public int imageHeight;
        /* access modifiers changed from: private */
        public int imageWidth;
        /* access modifiers changed from: private */
        public int rotation;
        /* access modifiers changed from: private */
        public int videoCodec;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder filename(String str) {
            this.filename = str;
            return this;
        }

        public Builder format(String str) {
            this.format = str;
            return this;
        }

        public Builder rotation(int i) {
            this.rotation = i;
            return this;
        }

        public Builder facing(int i) {
            this.facing = i;
            return this;
        }

        public Builder imageWidth(int i) {
            this.imageWidth = i;
            return this;
        }

        public Builder imageHeight(int i) {
            this.imageHeight = i;
            return this;
        }

        public Builder fps(int i) {
            this.fps = i;
            return this;
        }

        public Builder videoCodec(int i) {
            this.videoCodec = i;
            return this;
        }

        public Builder audioCodec(int i) {
            this.audioCodec = i;
            return this;
        }

        public Builder videoBitrate(int i) {
            this.bitrate = Integer.valueOf(i);
            return this;
        }

        public CameraView create() {
            return new CameraView(this);
        }
    }
}
