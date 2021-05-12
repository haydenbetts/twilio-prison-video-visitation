package com.forasoft.homewavvisitor.model.data;

import air.HomeWAV.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Card;", "", "token", "", "cardType", "last4", "imageUrl", "isDefault", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getCardType", "()Ljava/lang/String;", "getImageUrl", "()Z", "getLast4", "getToken", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "getImageResources", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Card.kt */
public final class Card {
    private final String cardType;
    private final String imageUrl;
    private final boolean isDefault;
    private final String last4;
    private final String token;

    public static /* synthetic */ Card copy$default(Card card, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = card.token;
        }
        if ((i & 2) != 0) {
            str2 = card.cardType;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = card.last4;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = card.imageUrl;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            z = card.isDefault;
        }
        return card.copy(str, str5, str6, str7, z);
    }

    public final String component1() {
        return this.token;
    }

    public final String component2() {
        return this.cardType;
    }

    public final String component3() {
        return this.last4;
    }

    public final String component4() {
        return this.imageUrl;
    }

    public final boolean component5() {
        return this.isDefault;
    }

    public final Card copy(String str, String str2, String str3, String str4, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        Intrinsics.checkParameterIsNotNull(str2, "cardType");
        Intrinsics.checkParameterIsNotNull(str3, "last4");
        Intrinsics.checkParameterIsNotNull(str4, "imageUrl");
        return new Card(str, str2, str3, str4, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card card = (Card) obj;
        return Intrinsics.areEqual((Object) this.token, (Object) card.token) && Intrinsics.areEqual((Object) this.cardType, (Object) card.cardType) && Intrinsics.areEqual((Object) this.last4, (Object) card.last4) && Intrinsics.areEqual((Object) this.imageUrl, (Object) card.imageUrl) && this.isDefault == card.isDefault;
    }

    public int hashCode() {
        String str = this.token;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.cardType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.last4;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.imageUrl;
        if (str4 != null) {
            i = str4.hashCode();
        }
        int i2 = (hashCode3 + i) * 31;
        boolean z = this.isDefault;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public String toString() {
        return "Card(token=" + this.token + ", cardType=" + this.cardType + ", last4=" + this.last4 + ", imageUrl=" + this.imageUrl + ", isDefault=" + this.isDefault + ")";
    }

    public Card(String str, String str2, String str3, String str4, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        Intrinsics.checkParameterIsNotNull(str2, "cardType");
        Intrinsics.checkParameterIsNotNull(str3, "last4");
        Intrinsics.checkParameterIsNotNull(str4, "imageUrl");
        this.token = str;
        this.cardType = str2;
        this.last4 = str3;
        this.imageUrl = str4;
        this.isDefault = z;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getCardType() {
        return this.cardType;
    }

    public final String getLast4() {
        return this.last4;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final boolean isDefault() {
        return this.isDefault;
    }

    public final int getImageResources() {
        String str = this.cardType;
        switch (str.hashCode()) {
            case -1802816241:
                if (str.equals("Maestro")) {
                    return R.drawable.icon_payment_maestro;
                }
                break;
            case -298759312:
                if (str.equals(com.stripe.android.model.Card.AMERICAN_EXPRESS)) {
                    return R.drawable.icon_payment_american_express;
                }
                break;
            case -46205774:
                if (str.equals(com.stripe.android.model.Card.MASTERCARD)) {
                    return R.drawable.icon_payment_mastercard;
                }
                break;
            case 73257:
                if (str.equals(com.stripe.android.model.Card.JCB)) {
                    return R.drawable.icon_payment_jcb;
                }
                break;
            case 2666593:
                if (str.equals(com.stripe.android.model.Card.VISA)) {
                    return R.drawable.icon_payment_visa;
                }
                break;
            case 337828873:
                if (str.equals(com.stripe.android.model.Card.DISCOVER)) {
                    return R.drawable.icon_payment_discover;
                }
                break;
        }
        return R.drawable.icon_payment_dci;
    }
}
