package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.appcompat.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007*\u00020\u0003H\b¨\u0006\n"}, d2 = {"query", "Lio/reactivex/functions/Consumer;", "", "Landroidx/appcompat/widget/SearchView;", "submit", "", "queryTextChangeEvents", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "Lcom/jakewharton/rxbinding2/support/v7/widget/SearchViewQueryTextEvent;", "queryTextChanges", "rxbinding2-appcompat-v7-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxSearchView.kt */
public final class RxSearchViewKt {
    public static final InitialValueObservable<SearchViewQueryTextEvent> queryTextChangeEvents(SearchView searchView) {
        Intrinsics.checkParameterIsNotNull(searchView, "$receiver");
        InitialValueObservable<SearchViewQueryTextEvent> queryTextChangeEvents = RxSearchView.queryTextChangeEvents(searchView);
        Intrinsics.checkExpressionValueIsNotNull(queryTextChangeEvents, "RxSearchView.queryTextChangeEvents(this)");
        return queryTextChangeEvents;
    }

    public static final InitialValueObservable<CharSequence> queryTextChanges(SearchView searchView) {
        Intrinsics.checkParameterIsNotNull(searchView, "$receiver");
        InitialValueObservable<CharSequence> queryTextChanges = RxSearchView.queryTextChanges(searchView);
        Intrinsics.checkExpressionValueIsNotNull(queryTextChanges, "RxSearchView.queryTextChanges(this)");
        return queryTextChanges;
    }

    public static final Consumer<? super CharSequence> query(SearchView searchView, boolean z) {
        Intrinsics.checkParameterIsNotNull(searchView, "$receiver");
        Consumer<? super CharSequence> query = RxSearchView.query(searchView, z);
        Intrinsics.checkExpressionValueIsNotNull(query, "RxSearchView.query(this, submit)");
        return query;
    }
}
