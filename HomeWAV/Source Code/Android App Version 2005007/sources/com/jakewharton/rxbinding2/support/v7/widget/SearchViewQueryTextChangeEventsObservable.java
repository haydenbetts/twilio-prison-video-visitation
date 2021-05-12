package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.appcompat.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

final class SearchViewQueryTextChangeEventsObservable extends InitialValueObservable<SearchViewQueryTextEvent> {
    /* access modifiers changed from: private */
    public final SearchView view;

    SearchViewQueryTextChangeEventsObservable(SearchView searchView) {
        this.view = searchView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super SearchViewQueryTextEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnQueryTextListener(listener);
        }
    }

    /* access modifiers changed from: protected */
    public SearchViewQueryTextEvent getInitialValue() {
        SearchView searchView = this.view;
        return SearchViewQueryTextEvent.create(searchView, searchView.getQuery(), false);
    }

    final class Listener extends MainThreadDisposable implements SearchView.OnQueryTextListener {
        private final Observer<? super SearchViewQueryTextEvent> observer;
        private final SearchView searchView;

        Listener(SearchView searchView2, Observer<? super SearchViewQueryTextEvent> observer2) {
            this.searchView = searchView2;
            this.observer = observer2;
        }

        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(SearchViewQueryTextEvent.create(SearchViewQueryTextChangeEventsObservable.this.view, str, false));
            return true;
        }

        public boolean onQueryTextSubmit(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(SearchViewQueryTextEvent.create(SearchViewQueryTextChangeEventsObservable.this.view, SearchViewQueryTextChangeEventsObservable.this.view.getQuery(), true));
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        }
    }
}
