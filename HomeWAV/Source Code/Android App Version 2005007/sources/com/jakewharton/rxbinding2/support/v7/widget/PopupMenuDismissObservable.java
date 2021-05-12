package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.appcompat.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class PopupMenuDismissObservable extends Observable<Object> {
    private final PopupMenu view;

    PopupMenuDismissObservable(PopupMenu popupMenu) {
        this.view = popupMenu;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnDismissListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements PopupMenu.OnDismissListener {
        private final Observer<? super Object> observer;
        private final PopupMenu popupMenu;

        Listener(PopupMenu popupMenu2, Observer<? super Object> observer2) {
            this.popupMenu = popupMenu2;
            this.observer = observer2;
        }

        public void onDismiss(PopupMenu popupMenu2) {
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.popupMenu.setOnDismissListener((PopupMenu.OnDismissListener) null);
        }
    }
}
