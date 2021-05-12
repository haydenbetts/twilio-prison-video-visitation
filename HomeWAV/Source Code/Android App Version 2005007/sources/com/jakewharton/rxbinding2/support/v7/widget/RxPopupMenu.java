package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.Preconditions;
import io.reactivex.Observable;

public final class RxPopupMenu {
    public static Observable<MenuItem> itemClicks(PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return new PopupMenuItemClickObservable(popupMenu);
    }

    public static Observable<Object> dismisses(PopupMenu popupMenu) {
        Preconditions.checkNotNull(popupMenu, "view == null");
        return new PopupMenuDismissObservable(popupMenu);
    }

    private RxPopupMenu() {
        throw new AssertionError("No instances.");
    }
}
