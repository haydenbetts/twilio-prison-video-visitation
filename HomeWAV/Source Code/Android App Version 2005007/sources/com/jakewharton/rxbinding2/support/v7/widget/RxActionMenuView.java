package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;

public final class RxActionMenuView {
    public static Observable<MenuItem> itemClicks(ActionMenuView actionMenuView) {
        Preconditions.checkNotNull(actionMenuView, "view == null");
        return new ActionMenuViewItemClickObservable(actionMenuView);
    }

    private RxActionMenuView() {
        throw new AssertionError("No instances.");
    }
}
