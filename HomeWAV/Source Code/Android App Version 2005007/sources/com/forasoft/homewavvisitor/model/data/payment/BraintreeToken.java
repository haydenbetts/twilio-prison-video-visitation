package com.forasoft.homewavvisitor.model.data.payment;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "", "token", "", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BraintreeToken.kt */
public final class BraintreeToken {
    @SerializedName("braintree_token")
    private final String token;

    public static /* synthetic */ BraintreeToken copy$default(BraintreeToken braintreeToken, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = braintreeToken.token;
        }
        return braintreeToken.copy(str);
    }

    public final String component1() {
        return this.token;
    }

    public final BraintreeToken copy(String str) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        return new BraintreeToken(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof BraintreeToken) && Intrinsics.areEqual((Object) this.token, (Object) ((BraintreeToken) obj).token);
        }
        return true;
    }

    public int hashCode() {
        String str = this.token;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "BraintreeToken(token=" + this.token + ")";
    }

    public BraintreeToken(String str) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        this.token = str;
    }

    public final String getToken() {
        return this.token;
    }
}
