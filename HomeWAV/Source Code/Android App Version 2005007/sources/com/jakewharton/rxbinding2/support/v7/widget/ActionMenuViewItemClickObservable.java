package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class ActionMenuViewItemClickObservable extends Observable<MenuItem> {
    private final ActionMenuView view;

    ActionMenuViewItemClickObservable(ActionMenuView actionMenuView) {
        this.view = actionMenuView;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super MenuItem> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnMenuItemClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements ActionMenuView.OnMenuItemClickListener {
        private final ActionMenuView actionMenuView;
        private final Observer<? super MenuItem> observer;

        Listener(ActionMenuView actionMenuView2, Observer<? super MenuItem> observer2) {
            this.actionMenuView = actionMenuView2;
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
            this.actionMenuView.setOnMenuItemClickListener((ActionMenuView.OnMenuItemClickListener) null);
        }
    }
}
