package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import java.util.Objects;

final class AutoValue_AdapterViewItemSelectionEvent extends AdapterViewItemSelectionEvent {
    private final long id;
    private final int position;
    private final View selectedView;
    private final AdapterView<?> view;

    AutoValue_AdapterViewItemSelectionEvent(AdapterView<?> adapterView, View view2, int i, long j) {
        Objects.requireNonNull(adapterView, "Null view");
        this.view = adapterView;
        Objects.requireNonNull(view2, "Null selectedView");
        this.selectedView = view2;
        this.position = i;
        this.id = j;
    }

    public AdapterView<?> view() {
        return this.view;
    }

    public View selectedView() {
        return this.selectedView;
    }

    public int position() {
        return this.position;
    }

    public long id() {
        return this.id;
    }

    public String toString() {
        return "AdapterViewItemSelectionEvent{view=" + this.view + ", selectedView=" + this.selectedView + ", position=" + this.position + ", id=" + this.id + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemSelectionEvent)) {
            return false;
        }
        AdapterViewItemSelectionEvent adapterViewItemSelectionEvent = (AdapterViewItemSelectionEvent) obj;
        if (!this.view.equals(adapterViewItemSelectionEvent.view()) || !this.selectedView.equals(adapterViewItemSelectionEvent.selectedView()) || this.position != adapterViewItemSelectionEvent.position() || this.id != adapterViewItemSelectionEvent.id()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.id;
        return (int) (((long) ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.selectedView.hashCode()) * 1000003) ^ this.position) * 1000003)) ^ (j ^ (j >>> 32)));
    }
}
