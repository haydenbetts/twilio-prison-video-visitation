package com.twilio.video;

import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
import tvi.webrtc.EglBase;

class EglBaseProvider {
    private static final String RELEASE_MESSAGE_TEMPLATE = "EglBaseProvider released %s unavailable";
    private static volatile Set<Object> eglBaseProviderOwners = new HashSet();
    private static volatile EglBaseProvider instance;
    private EglBase localEglBase = EglBase.CC.create(this.rootEglBase.getEglBaseContext());
    private EglBase remoteEglBase = EglBase.CC.create(this.rootEglBase.getEglBaseContext());
    private EglBase rootEglBase = EglBase.CC.create();

    static EglBaseProvider instance(Object obj) {
        EglBaseProvider eglBaseProvider;
        synchronized (EglBaseProvider.class) {
            if (instance == null) {
                instance = new EglBaseProvider();
            }
            eglBaseProviderOwners.add(obj);
            eglBaseProvider = instance;
        }
        return eglBaseProvider;
    }

    /* access modifiers changed from: package-private */
    public EglBase getRootEglBase() {
        EglBase eglBase;
        synchronized (EglBaseProvider.class) {
            checkReleased("getRootEglBase");
            eglBase = instance.rootEglBase;
        }
        return eglBase;
    }

    /* access modifiers changed from: package-private */
    public EglBase getLocalEglBase() {
        EglBase eglBase;
        synchronized (EglBaseProvider.class) {
            checkReleased("getLocalEglBase");
            eglBase = instance.localEglBase;
        }
        return eglBase;
    }

    /* access modifiers changed from: package-private */
    public EglBase getRemoteEglBase() {
        EglBase eglBase;
        synchronized (EglBaseProvider.class) {
            checkReleased("getRemoteEglBase");
            eglBase = instance.remoteEglBase;
        }
        return eglBase;
    }

    /* access modifiers changed from: package-private */
    public void release(Object obj) {
        synchronized (EglBaseProvider.class) {
            eglBaseProviderOwners.remove(obj);
            if (instance != null && eglBaseProviderOwners.isEmpty()) {
                instance.remoteEglBase.release();
                instance.remoteEglBase = null;
                instance.localEglBase.release();
                instance.localEglBase = null;
                instance.rootEglBase.release();
                instance.rootEglBase = null;
                instance = null;
            }
        }
    }

    @VisibleForTesting(otherwise = 5)
    static void waitForNoOwners() {
        while (true) {
            synchronized (EglBaseProvider.class) {
                if (eglBaseProviderOwners.isEmpty()) {
                    return;
                }
            }
        }
    }

    private EglBaseProvider() {
    }

    private void checkReleased(String str) {
        if (instance == null) {
            throw new IllegalStateException(String.format(RELEASE_MESSAGE_TEMPLATE, new Object[]{str}));
        }
    }
}
