package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003JD\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u001aHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001J\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001aHÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "Landroid/os/Parcelable;", "amount", "", "inmateId", "", "occupantId", "paymentScope", "visitorEmail", "(Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getInmateId", "()Ljava/lang/String;", "getOccupantId", "getPaymentScope", "getVisitorEmail", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentRequestData.kt */
public final class PaymentRequestData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final Float amount;
    private final String inmateId;
    private final String occupantId;
    private final String paymentScope;
    private final String visitorEmail;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new PaymentRequestData(parcel.readInt() != 0 ? Float.valueOf(parcel.readFloat()) : null, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new PaymentRequestData[i];
        }
    }

    public static /* synthetic */ PaymentRequestData copy$default(PaymentRequestData paymentRequestData, Float f, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = paymentRequestData.amount;
        }
        if ((i & 2) != 0) {
            str = paymentRequestData.inmateId;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = paymentRequestData.occupantId;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = paymentRequestData.paymentScope;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = paymentRequestData.visitorEmail;
        }
        return paymentRequestData.copy(f, str5, str6, str7, str4);
    }

    public final Float component1() {
        return this.amount;
    }

    public final String component2() {
        return this.inmateId;
    }

    public final String component3() {
        return this.occupantId;
    }

    public final String component4() {
        return this.paymentScope;
    }

    public final String component5() {
        return this.visitorEmail;
    }

    public final PaymentRequestData copy(Float f, String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(str2, "occupantId");
        Intrinsics.checkParameterIsNotNull(str3, "paymentScope");
        return new PaymentRequestData(f, str, str2, str3, str4);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaymentRequestData)) {
            return false;
        }
        PaymentRequestData paymentRequestData = (PaymentRequestData) obj;
        return Intrinsics.areEqual((Object) this.amount, (Object) paymentRequestData.amount) && Intrinsics.areEqual((Object) this.inmateId, (Object) paymentRequestData.inmateId) && Intrinsics.areEqual((Object) this.occupantId, (Object) paymentRequestData.occupantId) && Intrinsics.areEqual((Object) this.paymentScope, (Object) paymentRequestData.paymentScope) && Intrinsics.areEqual((Object) this.visitorEmail, (Object) paymentRequestData.visitorEmail);
    }

    public int hashCode() {
        Float f = this.amount;
        int i = 0;
        int hashCode = (f != null ? f.hashCode() : 0) * 31;
        String str = this.inmateId;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.occupantId;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.paymentScope;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.visitorEmail;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "PaymentRequestData(amount=" + this.amount + ", inmateId=" + this.inmateId + ", occupantId=" + this.occupantId + ", paymentScope=" + this.paymentScope + ", visitorEmail=" + this.visitorEmail + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        Float f = this.amount;
        if (f != null) {
            parcel.writeInt(1);
            parcel.writeFloat(f.floatValue());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.inmateId);
        parcel.writeString(this.occupantId);
        parcel.writeString(this.paymentScope);
        parcel.writeString(this.visitorEmail);
    }

    public PaymentRequestData(Float f, String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        Intrinsics.checkParameterIsNotNull(str2, "occupantId");
        Intrinsics.checkParameterIsNotNull(str3, "paymentScope");
        this.amount = f;
        this.inmateId = str;
        this.occupantId = str2;
        this.paymentScope = str3;
        this.visitorEmail = str4;
    }

    public final Float getAmount() {
        return this.amount;
    }

    public final String getInmateId() {
        return this.inmateId;
    }

    public final String getOccupantId() {
        return this.occupantId;
    }

    public final String getPaymentScope() {
        return this.paymentScope;
    }

    public final String getVisitorEmail() {
        return this.visitorEmail;
    }
}
