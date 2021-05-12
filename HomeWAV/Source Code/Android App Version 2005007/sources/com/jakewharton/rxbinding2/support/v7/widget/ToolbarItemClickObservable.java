package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class ToolbarItemClickObservable extends Observable<MenuItem> {
    private final Toolbar view;

    ToolbarItemClickObservable(Toolbar toolbar) {
        this.view = toolbar;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super MenuItem> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnMenuItemClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements Toolbar.OnMenuItemClickListener {
        private final Observer<? super MenuItem> observer;
        private final Toolbar toolbar;

        Listener(Toolbar toolbar2, Observer<? super MenuItem> observer2) {
            this.toolbar = toolbar2;
            this.observer = observer2;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            if (isDisposed()) {
                return true;
            }
            this.observer.onNext(menuItem);
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.toolbar.setOnMenuItemClickListener((Toolbar.OnMenuItemClickListener) null);
        }
    }
}
