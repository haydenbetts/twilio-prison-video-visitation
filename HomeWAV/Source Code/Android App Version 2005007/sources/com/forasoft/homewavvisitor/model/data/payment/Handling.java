package com.forasoft.homewavvisitor.model.data.payment;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/Handling;", "", "handling", "", "(F)V", "getHandling", "()F", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Handling.kt */
public final class Handling {
    private final float handling;

    public static /* synthetic */ Handling copy$default(Handling handling2, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = handling2.handling;
        }
        return handling2.copy(f);
    }

    public final float component1() {
        return this.handling;
    }

    public final Handling copy(float f) {
        return new Handling(f);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof Handling) && Float.compare(this.handling, ((Handling) obj).handling) == 0;
        }
        return true;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.handling);
    }

    public String toString() {
        return "Handling(handling=" + this.handling + ")";
    }

    public Handling(float f) {
        this.handling = f;
    }

    public final float getHandling() {
        return this.handling;
    }
}
