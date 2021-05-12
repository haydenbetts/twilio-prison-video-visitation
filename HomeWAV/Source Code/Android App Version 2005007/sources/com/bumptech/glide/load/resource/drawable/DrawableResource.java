package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.Resource;
import java.util.Objects;

public abstract class DrawableResource<T extends Drawable> implements Resource<T> {
    protected final T drawable;

    public DrawableResource(T t) {
        Objects.requireNonNull(t, "Drawable must not be null!");
        this.drawable = t;
    }

    public final T get() {
        return this.drawable.getConstantState().newDrawable();
    }
}
