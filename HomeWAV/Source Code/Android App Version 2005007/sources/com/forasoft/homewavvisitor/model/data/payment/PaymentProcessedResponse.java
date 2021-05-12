package com.forasoft.homewavvisitor.model.data.payment;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "", "braintree_transaction", "Lcom/forasoft/homewavvisitor/model/data/payment/Transaction;", "stripe_transaction", "credit", "Lcom/forasoft/homewavvisitor/model/data/payment/Credit;", "balance", "", "(Lcom/forasoft/homewavvisitor/model/data/payment/Transaction;Lcom/forasoft/homewavvisitor/model/data/payment/Transaction;Lcom/forasoft/homewavvisitor/model/data/payment/Credit;Ljava/lang/String;)V", "getBalance", "()Ljava/lang/String;", "getBraintree_transaction", "()Lcom/forasoft/homewavvisitor/model/data/payment/Transaction;", "getCredit", "()Lcom/forasoft/homewavvisitor/model/data/payment/Credit;", "getStripe_transaction", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentResponce.kt */
public final class PaymentProcessedResponse {
    @SerializedName("credit_balance")
    private final String balance;
    private final Transaction braintree_transaction;
    private final Credit credit;
    private final Transaction stripe_transaction;

    public static /* synthetic */ PaymentProcessedResponse copy$default(PaymentProcessedResponse paymentProcessedResponse, Transaction transaction, Transaction transaction2, Credit credit2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            transaction = paymentProcessedResponse.braintree_transaction;
        }
        if ((i & 2) != 0) {
            transaction2 = paymentProcessedResponse.stripe_transaction;
        }
        if ((i & 4) != 0) {
            credit2 = paymentProcessedResponse.credit;
        }
        if ((i & 8) != 0) {
            str = paymentProcessedResponse.balance;
        }
        return paymentProcessedResponse.copy(transaction, transaction2, credit2, str);
    }

    public final Transaction component1() {
        return this.braintree_transaction;
    }

    public final Transaction component2() {
        return this.stripe_transaction;
    }

    public final Credit component3() {
        return this.credit;
    }

    public final String component4() {
        return this.balance;
    }

    public final PaymentProcessedResponse copy(Transaction transaction, Transaction transaction2, Credit credit2, String str) {
        Intrinsics.checkParameterIsNotNull(credit2, "credit");
        Intrinsics.checkParameterIsNotNull(str, "balance");
        return new PaymentProcessedResponse(transaction, transaction2, credit2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaymentProcessedResponse)) {
            return false;
        }
        PaymentProcessedResponse paymentProcessedResponse = (PaymentProcessedResponse) obj;
        return Intrinsics.areEqual((Object) this.braintree_transaction, (Object) paymentProcessedResponse.braintree_transaction) && Intrinsics.areEqual((Object) this.stripe_transaction, (Object) paymentProcessedResponse.stripe_transaction) && Intrinsics.areEqual((Object) this.credit, (Object) paymentProcessedResponse.credit) && Intrinsics.areEqual((Object) this.balance, (Object) paymentProcessedResponse.balance);
    }

    public int hashCode() {
        Transaction transaction = this.braintree_transaction;
        int i = 0;
        int hashCode = (transaction != null ? transaction.hashCode() : 0) * 31;
        Transaction transaction2 = this.stripe_transaction;
        int hashCode2 = (hashCode + (transaction2 != null ? transaction2.hashCode() : 0)) * 31;
        Credit credit2 = this.credit;
        int hashCode3 = (hashCode2 + (credit2 != null ? credit2.hashCode() : 0)) * 31;
        String str = this.balance;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "PaymentProcessedResponse(braintree_transaction=" + this.braintree_transaction + ", stripe_transaction=" + this.stripe_transaction + ", credit=" + this.credit + ", balance=" + this.balance + ")";
    }

    public PaymentProcessedResponse(Transaction transaction, Transaction transaction2, Credit credit2, String str) {
        Intrinsics.checkParameterIsNotNull(credit2, "credit");
        Intrinsics.checkParameterIsNotNull(str, "balance");
        this.braintree_transaction = transaction;
        this.stripe_transaction = transaction2;
        this.credit = credit2;
        this.balance = str;
    }

    public final Transaction getBraintree_transaction() {
        return this.braintree_transaction;
    }

    public final Transaction getStripe_transaction() {
        return this.stripe_transaction;
    }

    public final Credit getCredit() {
        return this.credit;
    }

    public final String getBalance() {
        return this.balance;
    }
}
