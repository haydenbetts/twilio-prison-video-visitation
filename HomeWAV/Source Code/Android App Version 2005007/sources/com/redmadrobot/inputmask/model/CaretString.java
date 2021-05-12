package com.redmadrobot.inputmask.model;

import com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u00002\u00020\u0001:\u0001\u0019B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0000J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/redmadrobot/inputmask/model/CaretString;", "", "string", "", "caretPosition", "", "caretGravity", "Lcom/redmadrobot/inputmask/model/CaretString$CaretGravity;", "(Ljava/lang/String;ILcom/redmadrobot/inputmask/model/CaretString$CaretGravity;)V", "getCaretGravity", "()Lcom/redmadrobot/inputmask/model/CaretString$CaretGravity;", "getCaretPosition", "()I", "getString", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "reversed", "toString", "CaretGravity", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: CaretString.kt */
public final class CaretString {
    private final CaretGravity caretGravity;
    private final int caretPosition;
    private final String string;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/redmadrobot/inputmask/model/CaretString$CaretGravity;", "", "(Ljava/lang/String;I)V", "FORWARD", "BACKWARD", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
    /* compiled from: CaretString.kt */
    public enum CaretGravity {
        FORWARD,
        BACKWARD
    }

    public static /* synthetic */ CaretString copy$default(CaretString caretString, String str, int i, CaretGravity caretGravity2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = caretString.string;
        }
        if ((i2 & 2) != 0) {
            i = caretString.caretPosition;
        }
        if ((i2 & 4) != 0) {
            caretGravity2 = caretString.caretGravity;
        }
        return caretString.copy(str, i, caretGravity2);
    }

    public final String component1() {
        return this.string;
    }

    public final int component2() {
        return this.caretPosition;
    }

    public final CaretGravity component3() {
        return this.caretGravity;
    }

    public final CaretString copy(String str, int i, CaretGravity caretGravity2) {
        Intrinsics.checkParameterIsNotNull(str, StringTypedProperty.TYPE);
        Intrinsics.checkParameterIsNotNull(caretGravity2, "caretGravity");
        return new CaretString(str, i, caretGravity2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof CaretString) {
                CaretString caretString = (CaretString) obj;
                if (Intrinsics.areEqual((Object) this.string, (Object) caretString.string)) {
                    if (!(this.caretPosition == caretString.caretPosition) || !Intrinsics.areEqual((Object) this.caretGravity, (Object) caretString.caretGravity)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.string;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.caretPosition) * 31;
        CaretGravity caretGravity2 = this.caretGravity;
        if (caretGravity2 != null) {
            i = caretGravity2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "CaretString(string=" + this.string + ", caretPosition=" + this.caretPosition + ", caretGravity=" + this.caretGravity + ")";
    }

    public CaretString(String str, int i, CaretGravity caretGravity2) {
        Intrinsics.checkParameterIsNotNull(str, StringTypedProperty.TYPE);
        Intrinsics.checkParameterIsNotNull(caretGravity2, "caretGravity");
        this.string = str;
        this.caretPosition = i;
        this.caretGravity = caretGravity2;
    }

    public final String getString() {
        return this.string;
    }

    public final int getCaretPosition() {
        return this.caretPosition;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CaretString(String str, int i, CaretGravity caretGravity2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? CaretGravity.FORWARD : caretGravity2);
    }

    public final CaretGravity getCaretGravity() {
        return this.caretGravity;
    }

    public final CaretString reversed() {
        String str = this.string;
        if (str != null) {
            return new CaretString(StringsKt.reversed((CharSequence) str).toString(), this.string.length() - this.caretPosition, this.caretGravity);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
