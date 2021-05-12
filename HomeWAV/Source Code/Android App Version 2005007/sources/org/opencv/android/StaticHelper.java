package org.opencv.android;

import android.util.Log;
import java.util.StringTokenizer;
import org.opencv.core.Core;

class StaticHelper {
    private static final String TAG = "OpenCV/StaticHelper";

    private static native String getLibraryList();

    StaticHelper() {
    }

    public static boolean initOpenCV(boolean z) {
        String str;
        if (z) {
            loadLibrary("cudart");
            loadLibrary("nppc");
            loadLibrary("nppi");
            loadLibrary("npps");
            loadLibrary("cufft");
            loadLibrary("cublas");
        }
        Log.d(TAG, "Trying to get library list");
        try {
            System.loadLibrary("opencv_info");
            str = getLibraryList();
        } catch (UnsatisfiedLinkError unused) {
            Log.e(TAG, "OpenCV error: Cannot load info library for OpenCV");
            str = "";
        }
        Log.d(TAG, "Library list: \"" + str + "\"");
        Log.d(TAG, "First attempt to load libs");
        if (initOpenCVLibs(str)) {
            Log.d(TAG, "First attempt to load libs is OK");
            for (String i : Core.getBuildInformation().split(System.getProperty("line.separator"))) {
                Log.i(TAG, i);
            }
            return true;
        }
        Log.d(TAG, "First attempt to load libs fails");
        return false;
    }

    private static boolean loadLibrary(String str) {
        Log.d(TAG, "Trying to load library " + str);
        try {
            System.loadLibrary(str);
            Log.d(TAG, "Library " + str + " loaded");
            return true;
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "Cannot load library \"" + str + "\"");
            e.printStackTrace();
            return false;
        }
    }

    private static boolean initOpenCVLibs(String str) {
        Log.d(TAG, "Trying to init OpenCV libs");
        if (str == null || str.length() == 0) {
            return loadLibrary("opencv_java4");
        }
        Log.d(TAG, "Trying to load libs by dependency list");
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        boolean z = true;
        while (stringTokenizer.hasMoreTokens()) {
            z &= loadLibrary(stringTokenizer.nextToken());
        }
        return z;
    }
}
