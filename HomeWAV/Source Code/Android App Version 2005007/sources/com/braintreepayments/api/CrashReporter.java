package com.braintreepayments.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

class CrashReporter implements Thread.UncaughtExceptionHandler {
    private BraintreeFragment mBraintreeFragment;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    static CrashReporter setup(BraintreeFragment braintreeFragment) {
        return new CrashReporter(braintreeFragment);
    }

    private CrashReporter(BraintreeFragment braintreeFragment) {
        this.mBraintreeFragment = braintreeFragment;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* access modifiers changed from: package-private */
    public void tearDown() {
        Thread.setDefaultUncaughtExceptionHandler(this.mDefaultExceptionHandler);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        if (stringWriter.toString().contains("com.braintreepayments") || stringWriter.toString().contains("com.paypal")) {
            this.mBraintreeFragment.sendAnalyticsEvent("crash");
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
