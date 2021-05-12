package com.forasoft.homewavvisitor.model.data.payment;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/NonceResponse;", "", "creditCards", "", "Lcom/forasoft/homewavvisitor/model/data/payment/CreditCard;", "(Ljava/util/List;)V", "getCreditCards", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NonceResponse.kt */
public final class NonceResponse {
    private final List<CreditCard> creditCards;

    public static /* synthetic */ NonceResponse copy$default(NonceResponse nonceResponse, List<CreditCard> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = nonceResponse.creditCards;
        }
        return nonceResponse.copy(list);
    }

    public final List<CreditCard> component1() {
        return this.creditCards;
    }

    public final NonceResponse copy(List<CreditCard> list) {
        Intrinsics.checkParameterIsNotNull(list, "creditCards");
        return new NonceResponse(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof NonceResponse) && Intrinsics.areEqual((Object) this.creditCards, (Object) ((NonceResponse) obj).creditCards);
        }
        return true;
    }

    public int hashCode() {
        List<CreditCard> list = this.creditCards;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "NonceResponse(creditCards=" + this.creditCards + ")";
    }

    public NonceResponse(List<CreditCard> list) {
        Intrinsics.checkParameterIsNotNull(list, "creditCards");
        this.creditCards = list;
    }

    public final List<CreditCard> getCreditCards() {
        return this.creditCards;
    }
}
