package com.forasoft.homewavvisitor.model.data.payment;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0001HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003Jq\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000f¨\u0006+"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/Transaction;", "", "amount", "", "created", "credit_id", "handling_fee", "id", "occupant_credit_id", "payment_description", "payment_type", "transaction_type", "tx_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/String;", "getCreated", "getCredit_id", "()Ljava/lang/Object;", "getHandling_fee", "getId", "getOccupant_credit_id", "getPayment_description", "getPayment_type", "getTransaction_type", "getTx_id", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentResponce.kt */
public final class Transaction {
    private final String amount;
    private final String created;
    private final Object credit_id;
    private final String handling_fee;
    private final String id;
    private final Object occupant_credit_id;
    private final String payment_description;
    private final String payment_type;
    private final String transaction_type;
    private final String tx_id;

    public static /* synthetic */ Transaction copy$default(Transaction transaction, String str, String str2, Object obj, String str3, String str4, Object obj2, String str5, String str6, String str7, String str8, int i, Object obj3) {
        Transaction transaction2 = transaction;
        int i2 = i;
        return transaction.copy((i2 & 1) != 0 ? transaction2.amount : str, (i2 & 2) != 0 ? transaction2.created : str2, (i2 & 4) != 0 ? transaction2.credit_id : obj, (i2 & 8) != 0 ? transaction2.handling_fee : str3, (i2 & 16) != 0 ? transaction2.id : str4, (i2 & 32) != 0 ? transaction2.occupant_credit_id : obj2, (i2 & 64) != 0 ? transaction2.payment_description : str5, (i2 & 128) != 0 ? transaction2.payment_type : str6, (i2 & 256) != 0 ? transaction2.transaction_type : str7, (i2 & 512) != 0 ? transaction2.tx_id : str8);
    }

    public final String component1() {
        return this.amount;
    }

    public final String component10() {
        return this.tx_id;
    }

    public final String component2() {
        return this.created;
    }

    public final Object component3() {
        return this.credit_id;
    }

    public final String component4() {
        return this.handling_fee;
    }

    public final String component5() {
        return this.id;
    }

    public final Object component6() {
        return this.occupant_credit_id;
    }

    public final String component7() {
        return this.payment_description;
    }

    public final String component8() {
        return this.payment_type;
    }

    public final String component9() {
        return this.transaction_type;
    }

    public final Transaction copy(String str, String str2, Object obj, String str3, String str4, Object obj2, String str5, String str6, String str7, String str8) {
        Intrinsics.checkParameterIsNotNull(str, "amount");
        Intrinsics.checkParameterIsNotNull(str2, "created");
        String str9 = str3;
        Intrinsics.checkParameterIsNotNull(str9, "handling_fee");
        String str10 = str4;
        Intrinsics.checkParameterIsNotNull(str10, "id");
        String str11 = str5;
        Intrinsics.checkParameterIsNotNull(str11, "payment_description");
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, "payment_type");
        String str13 = str7;
        Intrinsics.checkParameterIsNotNull(str13, "transaction_type");
        String str14 = str8;
        Intrinsics.checkParameterIsNotNull(str14, "tx_id");
        return new Transaction(str, str2, obj, str9, str10, obj2, str11, str12, str13, str14);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) obj;
        return Intrinsics.areEqual((Object) this.amount, (Object) transaction.amount) && Intrinsics.areEqual((Object) this.created, (Object) transaction.created) && Intrinsics.areEqual(this.credit_id, transaction.credit_id) && Intrinsics.areEqual((Object) this.handling_fee, (Object) transaction.handling_fee) && Intrinsics.areEqual((Object) this.id, (Object) transaction.id) && Intrinsics.areEqual(this.occupant_credit_id, transaction.occupant_credit_id) && Intrinsics.areEqual((Object) this.payment_description, (Object) transaction.payment_description) && Intrinsics.areEqual((Object) this.payment_type, (Object) transaction.payment_type) && Intrinsics.areEqual((Object) this.transaction_type, (Object) transaction.transaction_type) && Intrinsics.areEqual((Object) this.tx_id, (Object) transaction.tx_id);
    }

    public int hashCode() {
        String str = this.amount;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.created;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Object obj = this.credit_id;
        int hashCode3 = (hashCode2 + (obj != null ? obj.hashCode() : 0)) * 31;
        String str3 = this.handling_fee;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.id;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Object obj2 = this.occupant_credit_id;
        int hashCode6 = (hashCode5 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        String str5 = this.payment_description;
        int hashCode7 = (hashCode6 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.payment_type;
        int hashCode8 = (hashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.transaction_type;
        int hashCode9 = (hashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.tx_id;
        if (str8 != null) {
            i = str8.hashCode();
        }
        return hashCode9 + i;
    }

    public String toString() {
        return "Transaction(amount=" + this.amount + ", created=" + this.created + ", credit_id=" + this.credit_id + ", handling_fee=" + this.handling_fee + ", id=" + this.id + ", occupant_credit_id=" + this.occupant_credit_id + ", payment_description=" + this.payment_description + ", payment_type=" + this.payment_type + ", transaction_type=" + this.transaction_type + ", tx_id=" + this.tx_id + ")";
    }

    public Transaction(String str, String str2, Object obj, String str3, String str4, Object obj2, String str5, String str6, String str7, String str8) {
        Intrinsics.checkParameterIsNotNull(str, "amount");
        Intrinsics.checkParameterIsNotNull(str2, "created");
        Intrinsics.checkParameterIsNotNull(str3, "handling_fee");
        Intrinsics.checkParameterIsNotNull(str4, "id");
        Intrinsics.checkParameterIsNotNull(str5, "payment_description");
        Intrinsics.checkParameterIsNotNull(str6, "payment_type");
        Intrinsics.checkParameterIsNotNull(str7, "transaction_type");
        Intrinsics.checkParameterIsNotNull(str8, "tx_id");
        this.amount = str;
        this.created = str2;
        this.credit_id = obj;
        this.handling_fee = str3;
        this.id = str4;
        this.occupant_credit_id = obj2;
        this.payment_description = str5;
        this.payment_type = str6;
        this.transaction_type = str7;
        this.tx_id = str8;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getCreated() {
        return this.created;
    }

    public final Object getCredit_id() {
        return this.credit_id;
    }

    public final String getHandling_fee() {
        return this.handling_fee;
    }

    public final String getId() {
        return this.id;
    }

    public final Object getOccupant_credit_id() {
        return this.occupant_credit_id;
    }

    public final String getPayment_description() {
        return this.payment_description;
    }

    public final String getPayment_type() {
        return this.payment_type;
    }

    public final String getTransaction_type() {
        return this.transaction_type;
    }

    public final String getTx_id() {
        return this.tx_id;
    }
}
