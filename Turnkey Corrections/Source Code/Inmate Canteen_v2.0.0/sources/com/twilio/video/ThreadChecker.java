package com.twilio.video;

import android.os.Handler;
import android.os.Looper;

class ThreadChecker {
    ThreadChecker() {
    }

    static void checkIsValidThread(Handler handler) {
        Preconditions.checkState(Looper.myLooper() == null || handler.getLooper().getThread().getId() == Looper.myLooper().getThread().getId());
    }
}
