package com.jakewharton.rxbinding2.internal;

import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lcom/jakewharton/rxbinding2/internal/VoidToUnit;", "Lio/reactivex/functions/Function;", "", "", "()V", "apply", "ignored", "rxbinding2-kotlin"}, k = 1, mv = {1, 1, 1})
/* compiled from: VoidToUnit.kt */
public final class VoidToUnit implements Function<Object, Unit> {
    public static final VoidToUnit INSTANCE = null;

    public void apply(Object obj) {
    }

    static {
        new VoidToUnit();
    }

    private VoidToUnit() {
        INSTANCE = this;
    }
}
