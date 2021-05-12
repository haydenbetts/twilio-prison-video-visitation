package fm.liveswitch.android;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Size;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.WindowManager;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import fm.liveswitch.CameraSourceBase;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Future;
import fm.liveswitch.IAction0;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.Promise;
import fm.liveswitch.SourceInput;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoConfig;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Camera2Source extends CameraSourceBase {
    /* access modifiers changed from: private */
    public Handler backgroundCameraHandler;
    private HandlerThread backgroundCameraThread;
    /* access modifiers changed from: private */
    public int bufferOrientation = 0;
    Camera2SourceListener camera2EventListener;
    /* access modifiers changed from: private */
    public CameraCaptureSession cameraCaptureSession = null;
    /* access modifiers changed from: private */
    public CameraDevice cameraDevice = null;
    /* access modifiers changed from: private */
    public String cameraId = "";
    /* access modifiers changed from: private */
    public CameraManager cameraManager = null;
    /* access modifiers changed from: private */
    public Display defaultDisplay;
    /* access modifiers changed from: private */
    public float desiredFrameDuration = 0.0f;
    /* access modifiers changed from: private */
    public Size[] imageSizes = null;
    /* access modifiers changed from: private */
    public volatile boolean isCapturing = false;
    /* access modifiers changed from: private */
    public volatile boolean isRaising = false;
    /* access modifiers changed from: private */
    public int lastRotation = -1;
    /* access modifiers changed from: private */
    public long lastTimestamp = 0;
    /* access modifiers changed from: private */

    /* renamed from: me  reason: collision with root package name */
    public Camera2Source f5me = this;
    /* access modifiers changed from: private */
    public OrientationEventListener orientationEventListener;
    /* access modifiers changed from: private */
    public CameraPreview preview = null;
    private SurfaceTexture previewTexture;
    ImageReader.OnImageAvailableListener raiseFrame = new ImageReader.OnImageAvailableListener() {
        public void onImageAvailable(ImageReader imageReader) {
            Image acquireNextImage;
            int i;
            int i2;
            int i3;
            DataBuffer take;
            DataBuffer take2;
            DataBuffer take3;
            Camera2Source camera2Source;
            boolean z;
            try {
                acquireNextImage = imageReader.acquireNextImage();
                if (Camera2Source.this.isRaising) {
                    acquireNextImage.close();
                    return;
                }
                boolean unused = Camera2Source.this.isRaising = true;
                if (!Camera2Source.this.isCapturing) {
                    acquireNextImage.close();
                    boolean unused2 = Camera2Source.this.isRaising = false;
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (Camera2Source.this.lastTimestamp == -1 || ((float) (elapsedRealtime - Camera2Source.this.lastTimestamp)) >= Camera2Source.this.desiredFrameDuration) {
                    long unused3 = Camera2Source.this.lastTimestamp = elapsedRealtime;
                    if (acquireNextImage != null) {
                        Image.Plane[] planes = acquireNextImage.getPlanes();
                        Image.Plane plane = planes[0];
                        Image.Plane plane2 = planes[1];
                        Image.Plane plane3 = planes[2];
                        ByteBuffer buffer = plane.getBuffer();
                        ByteBuffer buffer2 = plane2.getBuffer();
                        ByteBuffer buffer3 = plane3.getBuffer();
                        if (planes[0].getPixelStride() == 1 && planes[1].getPixelStride() == 1 && planes[2].getPixelStride() == 1) {
                            i3 = buffer.remaining();
                            i2 = buffer2.remaining();
                            i = buffer3.remaining();
                        } else {
                            i3 = buffer.remaining();
                            i2 = i3 / 4;
                            i = i3 / 4;
                        }
                        take = DataBufferPool.getInstance().take(i3);
                        take2 = DataBufferPool.getInstance().take(i2);
                        take3 = DataBufferPool.getInstance().take(i);
                        try {
                            int rowStride = planes[0].getRowStride() / planes[0].getPixelStride();
                            int rowStride2 = planes[1].getRowStride() / planes[1].getPixelStride();
                            int rowStride3 = planes[2].getRowStride() / planes[2].getPixelStride();
                            buffer.get(take.getData(), take.getIndex(), i3);
                            int pixelStride = plane2.getPixelStride();
                            if (pixelStride == 1) {
                                buffer2.get(take2.getData(), take2.getIndex(), i2);
                            } else {
                                for (int i4 = 0; i4 < i2; i4++) {
                                    take2.write8(buffer2.get(i4 * pixelStride), i4);
                                }
                            }
                            int pixelStride2 = plane3.getPixelStride();
                            if (pixelStride2 == 1) {
                                buffer3.get(take3.getData(), take3.getIndex(), i);
                            } else {
                                for (int i5 = 0; i5 < i; i5++) {
                                    take3.write8(buffer3.get(i5 * pixelStride2), i5);
                                }
                            }
                            VideoBuffer videoBuffer = new VideoBuffer(acquireNextImage.getWidth(), acquireNextImage.getHeight(), new DataBuffer[]{take, take2, take3}, VideoFormat.getI420());
                            videoBuffer.setStrides(new int[]{rowStride, rowStride2, rowStride3});
                            videoBuffer.setOrientation(Camera2Source.this.bufferOrientation);
                            Camera2Source.this.raiseFrame(new VideoFrame(videoBuffer));
                            acquireNextImage.close();
                            take.free();
                            take2.free();
                            take3.free();
                            camera2Source = Camera2Source.this;
                            z = false;
                        } catch (Exception e) {
                            Log.error("Could not raise camera image.", e);
                            acquireNextImage.close();
                            take.free();
                            take2.free();
                            take3.free();
                            camera2Source = Camera2Source.this;
                            z = false;
                        }
                        boolean unused4 = camera2Source.isRaising = z;
                        return;
                    }
                    boolean unused5 = Camera2Source.this.isRaising = false;
                    return;
                }
                acquireNextImage.close();
                boolean unused6 = Camera2Source.this.isRaising = false;
            } catch (Exception e2) {
                Log.error("Error creating video frame.", e2);
            } catch (Throwable th) {
                acquireNextImage.close();
                take.free();
                take2.free();
                take3.free();
                boolean unused7 = Camera2Source.this.isRaising = false;
                throw th;
            }
        }
    };
    /* access modifiers changed from: private */
    public Size selectedSize = new Size(0, 0);
    /* access modifiers changed from: private */
    public CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        public void onOpened(CameraDevice cameraDevice) {
            CameraDevice unused = Camera2Source.this.cameraDevice = cameraDevice;
            Camera2Source.this.preview.updateSurfaceSize();
            Camera2Source.this.preview.setCamera(Camera2Source.this.f5me);
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2Source.this.f5me.doStop();
            Log.error("Camera 2 device disconnected.");
        }

        public void onError(CameraDevice cameraDevice, int i) {
            Camera2Source.this.f5me.doStop();
            Log.error("Camera 2 device errored.");
        }
    };
    /* access modifiers changed from: private */
    public ImageReader videoBufferImageReader = null;
    private Size[] videoSizes = null;

    public String getLabel() {
        return "Android Camera2 Source";
    }

    public void setListener(Camera2SourceListener camera2SourceListener) {
        this.camera2EventListener = camera2SourceListener;
    }

    public CameraDevice getCamera() {
        return this.cameraDevice;
    }

    public String getCameraId() {
        return this.cameraId;
    }

    public CameraPreview getPreview() {
        return this.preview;
    }

    public Size[] getVideoSizes() {
        return this.videoSizes;
    }

    public Size[] getImageSizes() {
        return this.imageSizes;
    }

    public Size getSelectedSize() {
        return this.selectedSize;
    }

    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        this.previewTexture = surfaceTexture;
    }

    public Future<SourceInput[]> getInputs() {
        Promise promise = new Promise();
        ArrayList arrayList = new ArrayList();
        CameraManager cameraManager2 = this.cameraManager;
        if (cameraManager2 != null) {
            try {
                String[] cameraIdList = cameraManager2.getCameraIdList();
                for (int i = 0; i < cameraIdList.length; i++) {
                    arrayList.add(sourceInputFromCameraInfo(cameraIdList[i], i));
                }
            } catch (CameraAccessException unused) {
                Log.error("Error getting camera access in getInputs().");
            }
        }
        promise.resolve(arrayList.toArray(new SourceInput[arrayList.size()]));
        return promise;
    }

    public SourceInput getFrontInput() {
        try {
            String[] cameraIdList = this.cameraManager.getCameraIdList();
            for (int i = 0; i < cameraIdList.length; i++) {
                if (((Integer) this.cameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0) {
                    return sourceInputFromCameraInfo(cameraIdList[i], i);
                }
            }
            return null;
        } catch (CameraAccessException unused) {
            Log.error("Error getting camera access in getFrontInput().");
            return null;
        }
    }

    public SourceInput getBackInput() {
        try {
            String[] cameraIdList = this.cameraManager.getCameraIdList();
            for (int i = 0; i < cameraIdList.length; i++) {
                if (((Integer) this.cameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 1) {
                    return sourceInputFromCameraInfo(cameraIdList[i], i);
                }
            }
            return null;
        } catch (CameraAccessException unused) {
            Log.error("Error getting camera access in getBackInput().");
            return null;
        }
    }

    public SourceInput getExternalInput() {
        try {
            String[] cameraIdList = this.cameraManager.getCameraIdList();
            for (int i = 0; i < cameraIdList.length; i++) {
                if (((Integer) this.cameraManager.getCameraCharacteristics(cameraIdList[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 2) {
                    return sourceInputFromCameraInfo(cameraIdList[i], i);
                }
            }
            return null;
        } catch (CameraAccessException unused) {
            Log.error("Error getting camera access in getExternalInput().");
            return null;
        }
    }

    private SourceInput sourceInputFromCameraInfo(String str, int i) throws CameraAccessException {
        int intValue = ((Integer) this.cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING)).intValue();
        return new SourceInput(str, intValue == 0 ? "Front-Facing Camera" : intValue == 1 ? "Back-Facing Camera" : intValue == 2 ? "External Camera" : "Camera");
    }

    public void transformImage(int i, int i2) {
        if (this.preview != null && this.selectedSize != null) {
            int rotation = this.defaultDisplay.getRotation();
            final Matrix matrix = new Matrix();
            float f = (float) i;
            float f2 = (float) i2;
            RectF rectF = new RectF(0.0f, 0.0f, f, f2);
            RectF rectF2 = new RectF(0.0f, 0.0f, (float) this.selectedSize.getHeight(), (float) this.selectedSize.getWidth());
            float centerX = rectF.centerX();
            float centerY = rectF.centerY();
            if (1 == rotation || 3 == rotation) {
                rectF2.offset(centerX - rectF2.centerX(), centerY - rectF2.centerY());
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                float max = Math.max(f2 / ((float) this.selectedSize.getHeight()), f / ((float) this.selectedSize.getWidth()));
                matrix.postScale(max, max, centerX, centerY);
                matrix.postRotate((float) ((rotation - 2) * 90), centerX, centerY);
            } else if (2 == rotation) {
                matrix.postRotate(180.0f, centerX, centerY);
            }
            this.preview.getCameraView().post(new Runnable() {
                public void run() {
                    Camera2Source.this.preview.getCameraView().setTransform(matrix);
                }
            });
        }
    }

    public Camera2Source(CameraPreview cameraPreview, VideoConfig videoConfig) {
        super(VideoFormat.getI420(), videoConfig);
        if (cameraPreview != null) {
            Context context = cameraPreview.getContext();
            if (context.checkCallingOrSelfPermission("android.permission.CAMERA") == 0) {
                this.preview = cameraPreview;
                this.cameraManager = (CameraManager) context.getSystemService(AvatarUpdaterDialog.CAMERA);
                this.defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
                this.orientationEventListener = new OrientationEventListener(context.getApplicationContext(), 3) {
                    public void onOrientationChanged(int i) {
                        Camera2Source camera2Source = Camera2Source.this;
                        camera2Source.setRotation(camera2Source.defaultDisplay);
                    }
                };
                return;
            }
            throw new RuntimeException("Video capture permission has not been granted. Please add android.permission.CAMERA to your application manifest.");
        }
        throw new RuntimeException("Preview cannot be null.");
    }

    /* access modifiers changed from: private */
    public void setPreviewSize() {
        try {
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.cameraManager.getCameraCharacteristics(this.cameraId).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap != null) {
                this.imageSizes = streamConfigurationMap.getOutputSizes(35);
                this.videoSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
                return;
            }
            throw new IllegalStateException("Failed to get configuration map: " + this.cameraId);
        } catch (Exception e) {
            Log.error("Error setting up camera2.", e);
        }
    }

    /* access modifiers changed from: private */
    public void startBackgroundThreads() {
        HandlerThread handlerThread = new HandlerThread("camera_background_thread");
        this.backgroundCameraThread = handlerThread;
        handlerThread.start();
        this.backgroundCameraHandler = new Handler(this.backgroundCameraThread.getLooper());
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043 A[Catch:{ CameraAccessException -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0046 A[Catch:{ CameraAccessException -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c A[Catch:{ CameraAccessException -> 0x005b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setRotation(android.view.Display r6) {
        /*
            r5 = this;
            int r6 = r6.getRotation()
            int r0 = r5.lastRotation
            r1 = 0
            if (r6 != r0) goto L_0x000a
            return r1
        L_0x000a:
            r5.lastRotation = r6
            r0 = 1
            android.hardware.camera2.CameraManager r2 = r5.cameraManager     // Catch:{ CameraAccessException -> 0x005b }
            java.lang.String r3 = r5.cameraId     // Catch:{ CameraAccessException -> 0x005b }
            android.hardware.camera2.CameraCharacteristics r2 = r2.getCameraCharacteristics(r3)     // Catch:{ CameraAccessException -> 0x005b }
            android.hardware.camera2.CameraCharacteristics$Key r3 = android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION     // Catch:{ CameraAccessException -> 0x005b }
            java.lang.Object r3 = r2.get(r3)     // Catch:{ CameraAccessException -> 0x005b }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ CameraAccessException -> 0x005b }
            int r3 = r3.intValue()     // Catch:{ CameraAccessException -> 0x005b }
            if (r6 == 0) goto L_0x002b
            if (r6 == r0) goto L_0x0033
            r4 = 2
            if (r6 == r4) goto L_0x0030
            r4 = 3
            if (r6 == r4) goto L_0x002d
        L_0x002b:
            r6 = 0
            goto L_0x0035
        L_0x002d:
            r6 = 270(0x10e, float:3.78E-43)
            goto L_0x0035
        L_0x0030:
            r6 = 180(0xb4, float:2.52E-43)
            goto L_0x0035
        L_0x0033:
            r6 = 90
        L_0x0035:
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.LENS_FACING     // Catch:{ CameraAccessException -> 0x005b }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ CameraAccessException -> 0x005b }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ CameraAccessException -> 0x005b }
            int r2 = r2.intValue()     // Catch:{ CameraAccessException -> 0x005b }
            if (r2 != 0) goto L_0x0044
            r1 = 1
        L_0x0044:
            if (r1 == 0) goto L_0x004c
            int r3 = r3 + r6
            int r3 = r3 % 360
            r5.bufferOrientation = r3     // Catch:{ CameraAccessException -> 0x005b }
            goto L_0x0053
        L_0x004c:
            int r3 = r3 - r6
            int r3 = r3 + 360
            int r3 = r3 % 360
            r5.bufferOrientation = r3     // Catch:{ CameraAccessException -> 0x005b }
        L_0x0053:
            fm.liveswitch.android.CameraPreview r6 = r5.preview     // Catch:{ CameraAccessException -> 0x005b }
            int r1 = r5.bufferOrientation     // Catch:{ CameraAccessException -> 0x005b }
            r6.setCameraRotation(r1)     // Catch:{ CameraAccessException -> 0x005b }
            goto L_0x0061
        L_0x005b:
            r6 = move-exception
            java.lang.String r1 = "Camera access exception in Rotation."
            fm.liveswitch.Log.error(r1, r6)
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.Camera2Source.setRotation(android.view.Display):boolean");
    }

    /* access modifiers changed from: private */
    public void stopCamera() {
        CameraCaptureSession cameraCaptureSession2 = this.cameraCaptureSession;
        if (cameraCaptureSession2 != null) {
            cameraCaptureSession2.close();
            this.cameraCaptureSession = null;
        }
        try {
            ImageReader imageReader = this.videoBufferImageReader;
            if (imageReader != null) {
                imageReader.close();
                this.videoBufferImageReader = null;
            }
        } catch (Exception e) {
            Log.debug("Video Image Buffer failed to close gracefully. Not an issue.", e);
        }
        CameraDevice cameraDevice2 = this.cameraDevice;
        if (cameraDevice2 != null) {
            cameraDevice2.close();
            this.cameraDevice = null;
        }
    }

    /* access modifiers changed from: private */
    public void stopBackgroundThread() {
        HandlerThread handlerThread = this.backgroundCameraThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.backgroundCameraThread = null;
            this.backgroundCameraHandler = null;
        }
    }

    public void startCaptureSession() {
        SurfaceTexture surfaceTexture;
        try {
            setConfig(new VideoConfig(this.selectedSize.getWidth(), this.selectedSize.getHeight(), (double) this.desiredFrameDuration));
            ImageReader newInstance = ImageReader.newInstance(this.selectedSize.getWidth(), this.selectedSize.getHeight(), 35, 3);
            this.videoBufferImageReader = newInstance;
            newInstance.setOnImageAvailableListener(this.raiseFrame, this.backgroundCameraHandler);
            if (this.cameraDevice != null && (surfaceTexture = this.previewTexture) != null) {
                surfaceTexture.setDefaultBufferSize(this.selectedSize.getWidth(), this.selectedSize.getHeight());
                final Surface surface = new Surface(this.previewTexture);
                this.cameraDevice.createCaptureSession(Arrays.asList(new Surface[]{surface, this.videoBufferImageReader.getSurface()}), new CameraCaptureSession.StateCallback() {
                    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        if (Camera2Source.this.cameraDevice != null) {
                            if (Camera2Source.this.camera2EventListener != null) {
                                Camera2Source.this.camera2EventListener.onCameraCaptureSession(cameraCaptureSession);
                            }
                            CameraCaptureSession unused = Camera2Source.this.cameraCaptureSession = cameraCaptureSession;
                            try {
                                CaptureRequest.Builder createCaptureRequest = Camera2Source.this.cameraDevice.createCaptureRequest(1);
                                createCaptureRequest.addTarget(Camera2Source.this.videoBufferImageReader.getSurface());
                                createCaptureRequest.addTarget(surface);
                                createCaptureRequest.set(CaptureRequest.CONTROL_MODE, 1);
                                createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 3);
                                if (Camera2Source.this.camera2EventListener != null) {
                                    Camera2Source.this.camera2EventListener.onCameraRequestBuilder(createCaptureRequest);
                                }
                                cameraCaptureSession.setRepeatingRequest(createCaptureRequest.build(), (CameraCaptureSession.CaptureCallback) null, Camera2Source.this.backgroundCameraHandler);
                            } catch (CameraAccessException e) {
                                Log.error("Error configuring capture session.", e);
                            }
                        }
                    }

                    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        Log.error("Error configuring the Capture Session.");
                    }
                }, this.backgroundCameraHandler);
            }
        } catch (CameraAccessException e) {
            Log.error("Camera Access Exception in Create Capture Session", e);
        }
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        final Promise promise = new Promise();
        ManagedThread.dispatch(new IAction0() {
            public void invoke() {
                try {
                    Camera2Source camera2Source = Camera2Source.this;
                    float unused = camera2Source.desiredFrameDuration = 1000.0f / ((float) camera2Source.getTargetFrameRate());
                    if (Camera2Source.this.orientationEventListener.canDetectOrientation()) {
                        Camera2Source.this.orientationEventListener.enable();
                    } else {
                        Log.error("Orientation event listener cannot detect orientation changes!");
                    }
                    SourceInput input = Camera2Source.this.getInput();
                    if (input == null) {
                        input = Camera2Source.this.getFrontInput();
                    }
                    if (input == null) {
                        input = Camera2Source.this.getBackInput();
                    }
                    if (input == null) {
                        input = Camera2Source.this.getExternalInput();
                    }
                    if (input != null) {
                        String unused2 = Camera2Source.this.cameraId = input.getId();
                        Camera2Source.this.setPreviewSize();
                        int i = -1;
                        for (Size size : Camera2Source.this.imageSizes) {
                            int abs = Math.abs(size.getWidth() - Camera2Source.this.getTargetSize().getWidth()) + Math.abs(size.getHeight() - Camera2Source.this.getTargetSize().getHeight());
                            if (i == -1 || abs < i) {
                                Size unused3 = Camera2Source.this.selectedSize = size;
                                i = abs;
                            }
                        }
                        if (Camera2Source.this.selectedSize != null) {
                            int unused4 = Camera2Source.this.lastRotation = -1;
                            Camera2Source camera2Source2 = Camera2Source.this;
                            camera2Source2.setRotation(camera2Source2.defaultDisplay);
                            Camera2Source.this.startBackgroundThreads();
                            boolean unused5 = Camera2Source.this.isCapturing = true;
                            Camera2Source.this.cameraManager.openCamera(Camera2Source.this.cameraId, Camera2Source.this.stateCallback, Camera2Source.this.backgroundCameraHandler);
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
                    boolean unused = Camera2Source.this.isCapturing = false;
                    while (Camera2Source.this.isRaising) {
                        ManagedThread.sleep(10);
                    }
                    Camera2Source.this.preview.setCamera((Camera2Source) null);
                    if (Camera2Source.this.orientationEventListener != null) {
                        Camera2Source.this.orientationEventListener.disable();
                    }
                    if (Camera2Source.this.cameraDevice != null) {
                        Camera2Source.this.stopCamera();
                        Camera2Source.this.stopBackgroundThread();
                    }
                    Camera2Source.this.setPreviewTexture((SurfaceTexture) null);
                    promise.resolve(null);
                } catch (Exception e) {
                    promise.reject(e);
                }
            }
        });
        return promise;
    }
}
