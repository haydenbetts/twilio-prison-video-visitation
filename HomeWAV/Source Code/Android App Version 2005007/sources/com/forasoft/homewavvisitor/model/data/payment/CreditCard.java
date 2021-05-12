package com.forasoft.homewavvisitor.model.data.payment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/CreditCard;", "", "type", "", "nonce", "(Ljava/lang/String;Ljava/lang/String;)V", "getNonce", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CreditCard.kt */
public final class CreditCard {
    private final String nonce;
    private final String type;

    public static /* synthetic */ CreditCard copy$default(CreditCard creditCard, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = creditCard.type;
        }
        if ((i & 2) != 0) {
            str2 = creditCard.nonce;
        }
        return creditCard.copy(str, str2);
    }

    public final String component1() {
        return this.type;
    }

    public final String component2() {
        return this.nonce;
    }

    public final CreditCard copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        Intrinsics.checkParameterIsNotNull(str2, "nonce");
        return new CreditCard(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CreditCard)) {
            return false;
        }
        CreditCard creditCard = (CreditCard) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) creditCard.type) && Intrinsics.areEqual((Object) this.nonce, (Object) creditCard.nonce);
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.nonce;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "CreditCard(type=" + this.type + ", nonce=" + this.nonce + ")";
    }

    public CreditCard(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        Intrinsics.checkParameterIsNotNull(str2, "nonce");
        this.type = str;
        this.nonce = str2;
    }

    public final String getType() {
        return this.type;
    }

    public final String getNonce() {
        return this.nonce;
    }
}
