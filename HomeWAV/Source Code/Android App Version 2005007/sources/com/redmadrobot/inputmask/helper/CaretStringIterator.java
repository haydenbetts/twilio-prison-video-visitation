package com.redmadrobot.inputmask.helper;

import com.redmadrobot.inputmask.model.CaretString;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/redmadrobot/inputmask/helper/CaretStringIterator;", "", "caretString", "Lcom/redmadrobot/inputmask/model/CaretString;", "currentIndex", "", "(Lcom/redmadrobot/inputmask/model/CaretString;I)V", "getCaretString", "()Lcom/redmadrobot/inputmask/model/CaretString;", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "deletionAffectsCaret", "", "insertionAffectsCaret", "next", "", "()Ljava/lang/Character;", "input-mask-android_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: CaretStringIterator.kt */
public class CaretStringIterator {
    private final CaretString caretString;
    private int currentIndex;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaretString.CaretGravity.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[CaretString.CaretGravity.BACKWARD.ordinal()] = 1;
            iArr[CaretString.CaretGravity.FORWARD.ordinal()] = 2;
        }
    }

    public CaretStringIterator(CaretString caretString2, int i) {
        Intrinsics.checkParameterIsNotNull(caretString2, "caretString");
        this.caretString = caretString2;
        this.currentIndex = i;
    }

    /* access modifiers changed from: protected */
    public final CaretString getCaretString() {
        return this.caretString;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CaretStringIterator(CaretString caretString2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(caretString2, (i2 & 2) != 0 ? 0 : i);
    }

    /* access modifiers changed from: protected */
    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    /* access modifiers changed from: protected */
    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean insertionAffectsCaret() {
        /*
            r4 = this;
            com.redmadrobot.inputmask.model.CaretString r0 = r4.caretString
            com.redmadrobot.inputmask.model.CaretString$CaretGravity r0 = r0.getCaretGravity()
            int[] r1 = com.redmadrobot.inputmask.helper.CaretStringIterator.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x0032
            r3 = 2
            if (r0 != r3) goto L_0x002c
            int r0 = r4.currentIndex
            com.redmadrobot.inputmask.model.CaretString r3 = r4.caretString
            int r3 = r3.getCaretPosition()
            if (r0 <= r3) goto L_0x003c
            int r0 = r4.currentIndex
            if (r0 != 0) goto L_0x003d
            com.redmadrobot.inputmask.model.CaretString r0 = r4.caretString
            int r0 = r0.getCaretPosition()
            if (r0 != 0) goto L_0x003d
            goto L_0x003c
        L_0x002c:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L_0x0032:
            int r0 = r4.currentIndex
            com.redmadrobot.inputmask.model.CaretString r3 = r4.caretString
            int r3 = r3.getCaretPosition()
            if (r0 >= r3) goto L_0x003d
        L_0x003c:
            r1 = 1
        L_0x003d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redmadrobot.inputmask.helper.CaretStringIterator.insertionAffectsCaret():boolean");
    }

    public boolean deletionAffectsCaret() {
        return this.currentIndex < this.caretString.getCaretPosition();
    }

    public Character next() {
        if (this.currentIndex >= this.caretString.getString().length()) {
            return null;
        }
        String string = this.caretString.getString();
        if (string != null) {
            char[] charArray = string.toCharArray();
            Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
            int i = this.currentIndex;
            char c = charArray[i];
            this.currentIndex = i + 1;
            return Character.valueOf(c);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
