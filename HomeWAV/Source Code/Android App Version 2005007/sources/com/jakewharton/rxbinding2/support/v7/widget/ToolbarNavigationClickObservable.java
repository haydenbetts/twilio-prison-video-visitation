package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class ToolbarNavigationClickObservable extends Observable<Object> {
    private final Toolbar view;

    ToolbarNavigationClickObservable(Toolbar toolbar) {
        this.view = toolbar;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setNavigationOnClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements View.OnClickListener {
        private final Observer<? super Object> observer;
        private final Toolbar toolbar;

        Listener(Toolbar toolbar2, Observer<? super Object> observer2) {
            this.toolbar = toolbar2;
            this.observer = observer2;
        }

        public void onClick(View view) {
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.toolbar.setNavigationOnClickListener((View.OnClickListener) null);
        }
    }
}
