package com.twilio.video;

import android.os.Handler;
import com.twilio.video.RemoteDataTrack;
import java.nio.ByteBuffer;

public class RemoteDataTrack extends DataTrack {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(RemoteDataTrack.class);
    private final Listener dataTrackListenerProxy = new Listener() {
        public void onMessage(RemoteDataTrack remoteDataTrack, ByteBuffer byteBuffer) {
            checkCallback(byteBuffer, "onMessage(ByteBuffer)");
            synchronized (RemoteDataTrack.this) {
                if (RemoteDataTrack.this.handler != null) {
                    RemoteDataTrack.this.handler.post(new Runnable(remoteDataTrack, byteBuffer) {
                        public final /* synthetic */ RemoteDataTrack f$1;
                        public final /* synthetic */ ByteBuffer f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            RemoteDataTrack.AnonymousClass1.this.lambda$onMessage$0$RemoteDataTrack$1(this.f$1, this.f$2);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onMessage$0$RemoteDataTrack$1(RemoteDataTrack remoteDataTrack, ByteBuffer byteBuffer) {
            synchronized (RemoteDataTrack.this) {
                RemoteDataTrack.logger.d("onMessage(ByteBuffer)");
                if (RemoteDataTrack.this.listener != null) {
                    RemoteDataTrack.this.listener.onMessage(remoteDataTrack, byteBuffer);
                }
            }
        }

        public void onMessage(RemoteDataTrack remoteDataTrack, String str) {
            checkCallback(str, "onMessage(String)");
            synchronized (RemoteDataTrack.this) {
                if (RemoteDataTrack.this.handler != null) {
                    RemoteDataTrack.this.handler.post(new Runnable(remoteDataTrack, str) {
                        public final /* synthetic */ RemoteDataTrack f$1;
                        public final /* synthetic */ String f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            RemoteDataTrack.AnonymousClass1.this.lambda$onMessage$1$RemoteDataTrack$1(this.f$1, this.f$2);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onMessage$1$RemoteDataTrack$1(RemoteDataTrack remoteDataTrack, String str) {
            synchronized (RemoteDataTrack.this) {
                RemoteDataTrack.logger.d("onMessage(String)");
                if (RemoteDataTrack.this.listener != null) {
                    RemoteDataTrack.this.listener.onMessage(remoteDataTrack, str);
                }
            }
        }

        private void checkCallback(Object obj, String str) {
            Preconditions.checkNotNull(obj, "Received null message in %s", (Object) str);
        }
    };
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public Listener listener;
    private long nativeRemoteDataTrackContext;
    private final String sid;

    public interface Listener {
        void onMessage(RemoteDataTrack remoteDataTrack, String str);

        void onMessage(RemoteDataTrack remoteDataTrack, ByteBuffer byteBuffer);
    }

    private native void nativeRelease(long j);

    public String getSid() {
        return this.sid;
    }

    public synchronized void setListener(Listener listener2) {
        Handler handler2;
        if (listener2 != null) {
            try {
                handler2 = Util.createCallbackHandler();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            handler2 = null;
        }
        this.handler = handler2;
        this.listener = listener2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteDataTrack(boolean z, boolean z2, boolean z3, int i, int i2, String str, String str2, long j) {
        super(z, z2, z3, i, i2, str2);
        this.sid = str;
        this.nativeRemoteDataTrackContext = j;
    }

    /* access modifiers changed from: package-private */
    public synchronized void release() {
        if (!isReleased()) {
            nativeRelease(this.nativeRemoteDataTrackContext);
            this.nativeRemoteDataTrackContext = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.nativeRemoteDataTrackContext == 0;
    }
}
