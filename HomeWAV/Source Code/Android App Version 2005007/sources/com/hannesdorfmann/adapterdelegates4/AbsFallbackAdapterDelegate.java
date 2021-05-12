package com.hannesdorfmann.adapterdelegates4;

public abstract class AbsFallbackAdapterDelegate<T> extends AdapterDelegate<T> {
    /* access modifiers changed from: protected */
    public final boolean isForViewType(Object obj, int i) {
        return true;
    }
}
