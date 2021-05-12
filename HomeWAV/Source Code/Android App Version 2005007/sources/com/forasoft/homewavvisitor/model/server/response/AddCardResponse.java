package com.forasoft.homewavvisitor.model.server.response;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006HÆ\u0003J+\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/AddCardResponse;", "T", "", "ok", "", "body", "Lcom/forasoft/homewavvisitor/model/server/response/Body;", "(ZLcom/forasoft/homewavvisitor/model/server/response/Body;)V", "getBody", "()Lcom/forasoft/homewavvisitor/model/server/response/Body;", "getOk", "()Z", "result", "getResult", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardResponse.kt */
public final class AddCardResponse<T> {
    private final Body<T> body;
    private final boolean ok;

    public static /* synthetic */ AddCardResponse copy$default(AddCardResponse addCardResponse, boolean z, Body<T> body2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = addCardResponse.ok;
        }
        if ((i & 2) != 0) {
            body2 = addCardResponse.body;
        }
        return addCardResponse.copy(z, body2);
    }

    public final boolean component1() {
        return this.ok;
    }

    public final Body<T> component2() {
        return this.body;
    }

    public final AddCardResponse<T> copy(boolean z, Body<T> body2) {
        return new AddCardResponse<>(z, body2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AddCardResponse)) {
            return false;
        }
        AddCardResponse addCardResponse = (AddCardResponse) obj;
        return this.ok == addCardResponse.ok && Intrinsics.areEqual((Object) this.body, (Object) addCardResponse.body);
    }

    public int hashCode() {
        boolean z = this.ok;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        Body<T> body2 = this.body;
        return i + (body2 != null ? body2.hashCode() : 0);
    }

    public String toString() {
        return "AddCardResponse(ok=" + this.ok + ", body=" + this.body + ")";
    }

    public AddCardResponse(boolean z, Body<T> body2) {
        this.ok = z;
        this.body = body2;
    }

    public final Body<T> getBody() {
        return this.body;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final boolean getResult() {
        Body<T> body2 = this.body;
        T add_card_result = body2 != null ? body2.getAdd_card_result() : null;
        if (add_card_result instanceof Boolean) {
            T add_card_result2 = this.body.getAdd_card_result();
            if (add_card_result2 != null) {
                return ((Boolean) add_card_result2).booleanValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        } else if (!(add_card_result instanceof BraintreeResult)) {
            return false;
        } else {
            T add_card_result3 = this.body.getAdd_card_result();
            if (add_card_result3 != null) {
                return ((BraintreeResult) add_card_result3).getSuccess();
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.model.server.response.BraintreeResult");
        }
    }
}
