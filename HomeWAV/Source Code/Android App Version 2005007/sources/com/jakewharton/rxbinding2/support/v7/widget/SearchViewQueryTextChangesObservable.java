package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.appcompat.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class SearchViewQueryTextChangesObservable extends InitialValueObservable<CharSequence> {
    private final SearchView view;

    SearchViewQueryTextChangesObservable(SearchView searchView) {
        this.view = searchView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super CharSequence> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnQueryTextListener(listener);
        }
    }

    /* access modifiers changed from: protected */
    public CharSequence getInitialValue() {
        return this.view.getQuery();
    }

    final class Listener extends MainThreadDisposable implements SearchView.OnQueryTextListener {
        private final Observer<? super CharSequence> observer;
        private final SearchView searchView;

        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        Listener(SearchView searchView2, Observer<? super CharSequence> observer2) {
            this.searchView = searchView2;
            this.observer = observer2;
        }

        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(str);
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        }
    }
}
