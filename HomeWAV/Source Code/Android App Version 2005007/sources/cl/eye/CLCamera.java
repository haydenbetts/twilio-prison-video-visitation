package cl.eye;

public class CLCamera {
    public static int CLEYE_AUTO_EXPOSURE = 2;
    public static int CLEYE_AUTO_GAIN = 0;
    public static int CLEYE_AUTO_WHITEBALANCE = 4;
    public static int CLEYE_BAYER_RAW = 4;
    public static int CLEYE_COLOR_PROCESSED = 1;
    public static int CLEYE_COLOR_RAW = 3;
    public static int CLEYE_EXPOSURE = 3;
    public static int CLEYE_GAIN = 1;
    public static int CLEYE_HFLIP = 8;
    public static int CLEYE_HKEYSTONE = 10;
    public static int CLEYE_LENSBRIGHTNESS = 19;
    public static int CLEYE_LENSCORRECTION1 = 16;
    public static int CLEYE_LENSCORRECTION2 = 17;
    public static int CLEYE_LENSCORRECTION3 = 18;
    public static int CLEYE_MONO_PROCESSED = 0;
    public static int CLEYE_MONO_RAW = 2;
    public static int CLEYE_QVGA = 0;
    public static int CLEYE_ROTATION = 14;
    public static int CLEYE_VFLIP = 9;
    public static int CLEYE_VGA = 1;
    public static int CLEYE_VKEYSTONE = 11;
    public static int CLEYE_WHITEBALANCE_BLUE = 7;
    public static int CLEYE_WHITEBALANCE_GREEN = 6;
    public static int CLEYE_WHITEBALANCE_RED = 5;
    public static int CLEYE_XOFFSET = 12;
    public static int CLEYE_YOFFSET = 13;
    public static int CLEYE_ZOOM = 15;
    private static String dllpathx32 = "C://Program Files//Code Laboratories//CL-Eye Platform SDK//Bin//CLEyeMulticam.dll";
    private static String dllpathx64 = "C://Program Files (x86)//Code Laboratories//CL-Eye Platform SDK//Bin//CLEyeMulticam.dll";
    private static boolean libraryLoaded = false;
    private int cameraInstance = 0;

    static native boolean CLEyeCameraGetFrame(int i, int[] iArr, int i2);

    static native boolean CLEyeCameraStart(int i);

    static native boolean CLEyeCameraStop(int i);

    static native int CLEyeCreateCamera(int i, int i2, int i3, int i4);

    static native boolean CLEyeDestroyCamera(int i);

    static native int CLEyeGetCameraCount();

    static native int CLEyeGetCameraParameter(int i, int i2);

    static native String CLEyeGetCameraUUID(int i);

    static native boolean CLEyeSetCameraParameter(int i, int i2, int i3);

    static {
        try {
            System.load("C://Program Files//Code Laboratories//CL-Eye Platform SDK//Bin//CLEyeMulticam.dll");
            libraryLoaded = true;
            System.out.println("CLEyeMulticam.dll loaded");
        } catch (UnsatisfiedLinkError unused) {
            System.out.println("(1) Could not find the CLEyeMulticam.dll");
            try {
                System.load(dllpathx64);
                libraryLoaded = true;
                System.out.println("CLEyeMulticam.dll loaded");
            } catch (UnsatisfiedLinkError unused2) {
                System.out.println("(2) Could not find the CLEyeMulticam.dll");
            }
        }
    }

    public static boolean IsLibraryLoaded() {
        return libraryLoaded;
    }

    public static void loadLibrary(String str) {
        if (!libraryLoaded) {
            try {
                System.load(str);
                System.out.println("CLEyeMulticam.dll loaded");
            } catch (UnsatisfiedLinkError unused) {
                System.out.println("(3) Could not find the CLEyeMulticam.dll (Custom Path)");
            }
        }
    }

    public static int cameraCount() {
        return CLEyeGetCameraCount();
    }

    public static String cameraUUID(int i) {
        return CLEyeGetCameraUUID(i);
    }

    public void dispose() {
        stopCamera();
        destroyCamera();
    }

    public boolean createCamera(int i, int i2, int i3, int i4) {
        int CLEyeCreateCamera = CLEyeCreateCamera(i, i2, i3, i4);
        this.cameraInstance = CLEyeCreateCamera;
        return CLEyeCreateCamera != 0;
    }

    public boolean destroyCamera() {
        return CLEyeDestroyCamera(this.cameraInstance);
    }

    public boolean startCamera() {
        return CLEyeCameraStart(this.cameraInstance);
    }

    public boolean stopCamera() {
        return CLEyeCameraStop(this.cameraInstance);
    }

    public boolean getCameraFrame(int[] iArr, int i) {
        return CLEyeCameraGetFrame(this.cameraInstance, iArr, i);
    }

    public boolean setCameraParam(int i, int i2) {
        return CLEyeSetCameraParameter(this.cameraInstance, i, i2);
    }

    public int getCameraParam(int i) {
        return CLEyeGetCameraParameter(this.cameraInstance, i);
    }
}
