package com.github.karczews.rxbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;

class RxBroadcastReceiver extends Observable<Intent> {
    private final Context context;
    private final IntentFilter intentFilter;

    public RxBroadcastReceiver(Context context2, IntentFilter intentFilter2) {
        this.context = context2.getApplicationContext();
        this.intentFilter = intentFilter2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Intent> observer) {
        if (Preconditions.checkLooperThread(observer)) {
            ReceiverDisposable receiverDisposable = new ReceiverDisposable(this.context, observer);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.context.registerReceiver(receiverDisposable, this.intentFilter);
            } else {
                this.context.registerReceiver(receiverDisposable, this.intentFilter, (String) null, new Handler(Looper.myLooper()));
            }
            observer.onSubscribe(receiverDisposable);
        }
    }

    private static class ReceiverDisposable extends BroadcastReceiver implements Disposable {
        private final Context context;
        private final AtomicBoolean disposed = new AtomicBoolean(false);
        private final Observer<? super Intent> observer;

        ReceiverDisposable(Context context2, Observer<? super Intent> observer2) {
            this.observer = observer2;
            this.context = context2;
        }

        public void dispose() {
            if (this.disposed.compareAndSet(false, true)) {
                this.context.unregisterReceiver(this);
            }
        }

        public boolean isDisposed() {
            return this.disposed.get();
        }

        public void onReceive(Context context2, Intent intent) {
            if (!isDisposed()) {
                this.observer.onNext(intent);
            }
        }
    }
}
