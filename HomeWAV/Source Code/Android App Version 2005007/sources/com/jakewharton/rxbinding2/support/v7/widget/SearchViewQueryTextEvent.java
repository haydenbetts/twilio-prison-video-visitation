package com.jakewharton.rxbinding2.support.v7.widget;

import androidx.appcompat.widget.SearchView;

public abstract class SearchViewQueryTextEvent {
    public abstract boolean isSubmitted();

    public abstract CharSequence queryText();

    public abstract SearchView view();

    public static SearchViewQueryTextEvent create(SearchView searchView, CharSequence charSequence, boolean z) {
        return new AutoValue_SearchViewQueryTextEvent(searchView, charSequence, z);
    }

    SearchViewQueryTextEvent() {
    }
}
