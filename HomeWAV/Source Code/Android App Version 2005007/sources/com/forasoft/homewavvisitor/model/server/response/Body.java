package com.forasoft.homewavvisitor.model.server.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/Body;", "T", "", "add_card_result", "(Ljava/lang/Object;)V", "getAdd_card_result", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/forasoft/homewavvisitor/model/server/response/Body;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardResponse.kt */
public final class Body<T> {
    private final T add_card_result;

    public static /* synthetic */ Body copy$default(Body body, T t, int i, Object obj) {
        if ((i & 1) != 0) {
            t = body.add_card_result;
        }
        return body.copy(t);
    }

    public final T component1() {
        return this.add_card_result;
    }

    public final Body<T> copy(T t) {
        return new Body<>(t);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof Body) && Intrinsics.areEqual((Object) this.add_card_result, (Object) ((Body) obj).add_card_result);
        }
        return true;
    }

    public int hashCode() {
        T t = this.add_card_result;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Body(add_card_result=" + this.add_card_result + ")";
    }

    public Body(T t) {
        this.add_card_result = t;
    }

    public final T getAdd_card_result() {
        return this.add_card_result;
    }
}
