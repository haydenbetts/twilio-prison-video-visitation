package tvi.webrtc;

class NativeLibrary {
    /* access modifiers changed from: private */
    public static String TAG = "NativeLibrary";
    private static boolean libraryLoaded = false;
    private static Object lock = new Object();

    NativeLibrary() {
    }

    static class DefaultLoader implements NativeLibraryLoader {
        DefaultLoader() {
        }

        public boolean load(String str) {
            String access$000 = NativeLibrary.TAG;
            Logging.d(access$000, "Loading library: " + str);
            try {
                System.loadLibrary(str);
                return true;
            } catch (UnsatisfiedLinkError e) {
                String access$0002 = NativeLibrary.TAG;
                Logging.e(access$0002, "Failed to load native library: " + str, e);
                return false;
            }
        }
    }

    static void initialize(NativeLibraryLoader nativeLibraryLoader) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.d(TAG, "Native library has already been loaded.");
                return;
            }
            Logging.d(TAG, "Loading native library.");
            libraryLoaded = nativeLibraryLoader.load("jingle_peerconnection_so");
        }
    }

    static boolean isLoaded() {
        boolean z;
        synchronized (lock) {
            z = libraryLoaded;
        }
        return z;
    }
}
