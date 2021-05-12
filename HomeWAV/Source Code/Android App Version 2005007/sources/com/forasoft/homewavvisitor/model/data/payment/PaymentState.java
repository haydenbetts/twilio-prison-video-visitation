package com.forasoft.homewavvisitor.model.data.payment;

import android.os.Parcel;
import android.os.Parcelable;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u000eJ<\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001bHÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\u0019\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/PaymentState;", "Landroid/os/Parcelable;", "gatewayType", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "token", "", "selectedConnection", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "paymentAmount", "", "(Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;Ljava/lang/Float;)V", "getGatewayType", "()Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "getPaymentAmount", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getSelectedConnection", "()Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "getToken", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;Ljava/lang/Float;)Lcom/forasoft/homewavvisitor/model/data/payment/PaymentState;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentState.kt */
public final class PaymentState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final PaymentGatewayType gatewayType;
    private final Float paymentAmount;
    private final InmateByVisitor selectedConnection;
    private final String token;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            PaymentGatewayType paymentGatewayType = (PaymentGatewayType) Enum.valueOf(PaymentGatewayType.class, parcel.readString());
            String readString = parcel.readString();
            Float f = null;
            InmateByVisitor inmateByVisitor = parcel.readInt() != 0 ? (InmateByVisitor) InmateByVisitor.CREATOR.createFromParcel(parcel) : null;
            if (parcel.readInt() != 0) {
                f = Float.valueOf(parcel.readFloat());
            }
            return new PaymentState(paymentGatewayType, readString, inmateByVisitor, f);
        }

        public final Object[] newArray(int i) {
            return new PaymentState[i];
        }
    }

    public static /* synthetic */ PaymentState copy$default(PaymentState paymentState, PaymentGatewayType paymentGatewayType, String str, InmateByVisitor inmateByVisitor, Float f, int i, Object obj) {
        if ((i & 1) != 0) {
            paymentGatewayType = paymentState.gatewayType;
        }
        if ((i & 2) != 0) {
            str = paymentState.token;
        }
        if ((i & 4) != 0) {
            inmateByVisitor = paymentState.selectedConnection;
        }
        if ((i & 8) != 0) {
            f = paymentState.paymentAmount;
        }
        return paymentState.copy(paymentGatewayType, str, inmateByVisitor, f);
    }

    public final PaymentGatewayType component1() {
        return this.gatewayType;
    }

    public final String component2() {
        return this.token;
    }

    public final InmateByVisitor component3() {
        return this.selectedConnection;
    }

    public final Float component4() {
        return this.paymentAmount;
    }

    public final PaymentState copy(PaymentGatewayType paymentGatewayType, String str, InmateByVisitor inmateByVisitor, Float f) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "gatewayType");
        return new PaymentState(paymentGatewayType, str, inmateByVisitor, f);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaymentState)) {
            return false;
        }
        PaymentState paymentState = (PaymentState) obj;
        return Intrinsics.areEqual((Object) this.gatewayType, (Object) paymentState.gatewayType) && Intrinsics.areEqual((Object) this.token, (Object) paymentState.token) && Intrinsics.areEqual((Object) this.selectedConnection, (Object) paymentState.selectedConnection) && Intrinsics.areEqual((Object) this.paymentAmount, (Object) paymentState.paymentAmount);
    }

    public int hashCode() {
        PaymentGatewayType paymentGatewayType = this.gatewayType;
        int i = 0;
        int hashCode = (paymentGatewayType != null ? paymentGatewayType.hashCode() : 0) * 31;
        String str = this.token;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        InmateByVisitor inmateByVisitor = this.selectedConnection;
        int hashCode3 = (hashCode2 + (inmateByVisitor != null ? inmateByVisitor.hashCode() : 0)) * 31;
        Float f = this.paymentAmount;
        if (f != null) {
            i = f.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "PaymentState(gatewayType=" + this.gatewayType + ", token=" + this.token + ", selectedConnection=" + this.selectedConnection + ", paymentAmount=" + this.paymentAmount + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.gatewayType.name());
        parcel.writeString(this.token);
        InmateByVisitor inmateByVisitor = this.selectedConnection;
        if (inmateByVisitor != null) {
            parcel.writeInt(1);
            inmateByVisitor.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        Float f = this.paymentAmount;
        if (f != null) {
            parcel.writeInt(1);
            parcel.writeFloat(f.floatValue());
            return;
        }
        parcel.writeInt(0);
    }

    public PaymentState(PaymentGatewayType paymentGatewayType, String str, InmateByVisitor inmateByVisitor, Float f) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "gatewayType");
        this.gatewayType = paymentGatewayType;
        this.token = str;
        this.selectedConnection = inmateByVisitor;
        this.paymentAmount = f;
    }

    public final PaymentGatewayType getGatewayType() {
        return this.gatewayType;
    }

    public final String getToken() {
        return this.token;
    }

    public final InmateByVisitor getSelectedConnection() {
        return this.selectedConnection;
    }

    public final Float getPaymentAmount() {
        return this.paymentAmount;
    }
}
