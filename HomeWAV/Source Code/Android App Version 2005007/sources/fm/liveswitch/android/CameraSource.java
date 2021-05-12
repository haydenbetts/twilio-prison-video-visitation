package fm.liveswitch.android;

import android.content.Context;
import android.hardware.Camera;
import android.os.SystemClock;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import fm.liveswitch.CameraSourceBase;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Future;
import fm.liveswitch.IAction0;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.Promise;
import fm.liveswitch.SourceInput;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoConfig;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraSource extends CameraSourceBase {
    /* access modifiers changed from: private */
    public int bufferOrientation = 0;
    /* access modifiers changed from: private */
    public Camera camera = null;
    /* access modifiers changed from: private */
    public int cameraId = 0;
    /* access modifiers changed from: private */
    public Display defaultDisplay;
    /* access modifiers changed from: private */
    public float desiredFrameDuration = 0.0f;
    /* access modifiers changed from: private */
    public ExecutorService exec = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public volatile boolean isCapturing = false;
    /* access modifiers changed from: private */
    public volatile boolean isRaising = false;
    /* access modifiers changed from: private */
    public int lastRotation = -1;
    /* access modifiers changed from: private */
    public long lastTimestamp = -1;
    /* access modifiers changed from: private */
    public OrientationEventListener orientationEventListener;
    /* access modifiers changed from: private */
    public CameraPreview preview = null;
    /* access modifiers changed from: private */
    public int selectedHeight;
    /* access modifiers changed from: private */
    public int selectedWidth;
    private byte[] threadData = null;
    /* access modifiers changed from: private */
    public Object threadLock = new Object();

    public String getLabel() {
        return "Android Camera Source";
    }

    public Camera getCamera() {
        return this.camera;
    }

    public CameraPreview getPreview() {
        return this.preview;
    }

    public Future<SourceInput[]> getInputs() {
        Promise promise = new Promise();
        ArrayList arrayList = new ArrayList();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            arrayList.add(sourceInputFromCameraInfo(cameraInfo, i));
        }
        promise.resolve(arrayList.toArray(new SourceInput[arrayList.size()]));
        return promise;
    }

    public SourceInput getFrontInput() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                return sourceInputFromCameraInfo(cameraInfo, i);
            }
        }
        return null;
    }

    public SourceInput getBackInput() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                return sourceInputFromCameraInfo(cameraInfo, i);
            }
        }
        return null;
    }

    private static SourceInput sourceInputFromCameraInfo(Camera.CameraInfo cameraInfo, int i) {
        return new SourceInput(Integer.toString(i), cameraInfo.facing == 1 ? "Front-Facing Camera" : "Back-Facing Camera");
    }

    public CameraSource(CameraPreview cameraPreview, VideoConfig videoConfig) {
        super(VideoFormat.getNv21(), videoConfig);
        if (cameraPreview != null) {
            Context context = cameraPreview.getContext();
            if (context.checkCallingOrSelfPermission("android.permission.CAMERA") == 0) {
                this.preview = cameraPreview;
                Display defaultDisplay2 = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
                this.defaultDisplay = defaultDisplay2;
                setRotation(defaultDisplay2);
                this.orientationEventListener = new OrientationEventListener(context.getApplicationContext(), 3) {
                    public void onOrientationChanged(int i) {
                        CameraSource cameraSource = CameraSource.this;
                        cameraSource.setRotation(cameraSource.defaultDisplay);
                    }
                };
                return;
            }
            throw new RuntimeException("Video capture permission has not been granted. Please add android.permission.CAMERA to your application manifest.");
        }
        throw new RuntimeException("Preview cannot be null.");
    }

    public boolean setRotation(Display display) {
        int i;
        int rotation = display.getRotation();
        int i2 = 0;
        if (rotation == this.lastRotation) {
            return false;
        }
        this.lastRotation = rotation;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.cameraId, cameraInfo);
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = 180;
            } else if (rotation == 3) {
                i2 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            int i3 = (cameraInfo.orientation + i2) % 360;
            this.bufferOrientation = i3;
            i = (360 - i3) % 360;
        } else {
            i = ((cameraInfo.orientation - i2) + 360) % 360;
            this.bufferOrientation = i;
        }
        synchronized (this.threadLock) {
            Camera camera2 = this.camera;
            if (camera2 != null) {
                camera2.setDisplayOrientation(i);
            }
        }
        this.preview.setCameraRotation(i);
        return true;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    CameraSource cameraSource = CameraSource.this;
                    float unused = cameraSource.desiredFrameDuration = 1000.0f / ((float) cameraSource.getTargetFrameRate());
                    if (CameraSource.this.orientationEventListener.canDetectOrientation()) {
                        CameraSource.this.orientationEventListener.enable();
                    } else {
                        Log.error("Orientation event listener cannot detect orientation changes!");
                    }
                    SourceInput input = CameraSource.this.getInput();
                    if (input == null) {
                        input = CameraSource.this.getFrontInput();
                    }
                    if (input == null) {
                        input = CameraSource.this.getBackInput();
                    }
                    if (input != null) {
                        int unused2 = CameraSource.this.cameraId = ParseAssistant.parseIntegerValue(input.getId());
                        CameraSource cameraSource2 = CameraSource.this;
                        Camera unused3 = cameraSource2.camera = Camera.open(cameraSource2.cameraId);
                        Camera.Parameters parameters = CameraSource.this.camera.getParameters();
                        parameters.setPreviewFormat(17);
                        Camera.Size size = null;
                        int i = -1;
                        for (Camera.Size next : parameters.getSupportedPreviewSizes()) {
                            int abs = Math.abs(next.width - CameraSource.this.getTargetSize().getWidth()) + Math.abs(next.height - CameraSource.this.getTargetSize().getHeight());
                            if (i == -1 || abs < i) {
                                size = next;
                                i = abs;
                            }
                        }
                        if (size != null) {
                            int unused4 = CameraSource.this.selectedWidth = size.width;
                            int unused5 = CameraSource.this.selectedHeight = size.height;
                            parameters.setPreviewSize(CameraSource.this.selectedWidth, CameraSource.this.selectedHeight);
                            int targetFrameRate = (int) CameraSource.this.getTargetFrameRate();
                            int[] iArr = null;
                            int i2 = -1;
                            for (int[] next2 : parameters.getSupportedPreviewFpsRange()) {
                                int abs2 = ((int) Math.abs(((double) next2[0]) - (CameraSource.this.getTargetFrameRate() * 1000.0d))) + ((int) Math.abs(((double) next2[1]) - (CameraSource.this.getTargetFrameRate() * 1000.0d)));
                                if (i2 == -1 || abs2 < i2) {
                                    targetFrameRate = next2[0] + ((next2[1] - next2[0]) / 2);
                                    iArr = next2;
                                    i2 = abs2;
                                }
                            }
                            if (iArr != null) {
                                parameters.setPreviewFpsRange(iArr[0], iArr[1]);
                            }
                            String str = null;
                            for (String next3 : parameters.getSupportedFocusModes()) {
                                if (next3.equals("continuous-video")) {
                                    str = next3;
                                }
                            }
                            if (str != null) {
                                parameters.setFocusMode(str);
                            }
                            CameraSource cameraSource3 = CameraSource.this;
                            cameraSource3.setConfig(new VideoConfig(cameraSource3.selectedWidth, CameraSource.this.selectedHeight, (double) targetFrameRate));
                            CameraSource.this.camera.setParameters(parameters);
                            CameraSource.this.camera.setPreviewCallback(new Camera.PreviewCallback() {
                                public void onPreviewFrame(final byte[] bArr, Camera camera) {
                                    if (!CameraSource.this.isRaising) {
                                        boolean unused = CameraSource.this.isRaising = true;
                                        if (!CameraSource.this.isCapturing) {
                                            boolean unused2 = CameraSource.this.isRaising = false;
                                            return;
                                        }
                                        long elapsedRealtime = SystemClock.elapsedRealtime();
                                        if (CameraSource.this.lastTimestamp == -1 || ((float) (elapsedRealtime - CameraSource.this.lastTimestamp)) >= CameraSource.this.desiredFrameDuration) {
                                            long unused3 = CameraSource.this.lastTimestamp = elapsedRealtime;
                                            CameraSource.this.exec.submit(new Runnable() {
                                                public void run() {
                                                    try {
                                                        byte[] bArr = bArr;
                                                        VideoBuffer videoBuffer = new VideoBuffer(CameraSource.this.selectedWidth, CameraSource.this.selectedHeight, DataBuffer.wrap(bArr, 0, bArr.length), VideoFormat.getNv21());
                                                        videoBuffer.setOrientation(CameraSource.this.bufferOrientation);
                                                        CameraSource.this.raiseFrame(new VideoFrame(videoBuffer));
                                                    } catch (Exception e) {
                                                        Log.error("Could not raise camera image.", e);
                                                    } catch (Throwable th) {
                                                        boolean unused = CameraSource.this.isRaising = false;
                                                        throw th;
                                                    }
                                                    boolean unused2 = CameraSource.this.isRaising = false;
                                                }
                                            });
                                            return;
                                        }
                                        boolean unused4 = CameraSource.this.isRaising = false;
                                    }
                                }
                            });
                            CameraSource.this.setInput(input);
                            CameraSource.this.preview.setCamera(CameraSource.this.camera);
                            int unused6 = CameraSource.this.lastRotation = -1;
                            CameraSource cameraSource4 = CameraSource.this;
                            cameraSource4.setRotation(cameraSource4.defaultDisplay);
                            boolean unused7 = CameraSource.this.isCapturing = true;
                            promise.resolve(null);
                            return;
                        }
                        throw new Exception("No supported preview size.");
                    }
                    throw new Exception("Device has no available cameras.");
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    boolean unused = CameraSource.this.isCapturing = false;
                    while (CameraSource.this.isRaising) {
                        ManagedThread.sleep(10);
                    }
                    CameraSource.this.preview.setCamera((Camera) null);
                    if (CameraSource.this.camera != null) {
                        CameraSource.this.camera.setPreviewCallback((Camera.PreviewCallback) null);
                        synchronized (CameraSource.this.threadLock) {
                            CameraSource.this.camera.release();
                            Camera unused2 = CameraSource.this.camera = null;
                        }
                    }
                    if (CameraSource.this.orientationEventListener != null) {
                        CameraSource.this.orientationEventListener.disable();
                    }
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }
}
