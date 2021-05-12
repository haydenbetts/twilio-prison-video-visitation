package com.twilio.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

final class Util {
    Util() {
    }

    static Handler createCallbackHandler() {
        Handler handler;
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            handler = new Handler(myLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            handler = mainLooper != null ? new Handler(mainLooper) : null;
        }
        if (handler != null) {
            return handler;
        }
        throw new IllegalThreadStateException("This thread must be able to obtain a Looper");
    }

    static boolean permissionGranted(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }
}
