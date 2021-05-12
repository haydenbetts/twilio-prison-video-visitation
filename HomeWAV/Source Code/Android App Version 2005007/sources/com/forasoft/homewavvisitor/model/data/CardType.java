package com.forasoft.homewavvisitor.model.data;

import air.HomeWAV.R;
import com.stripe.android.model.Card;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/CardType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "getIcon", "", "toString", "AmericanExpress", "MasterCard", "Maestro", "Visa", "JCB", "Discover", "DinersClub", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CardType.kt */
public enum CardType {
    AmericanExpress(Card.AMERICAN_EXPRESS),
    MasterCard("Mastercard"),
    Maestro("Maestro"),
    Visa(Card.VISA),
    JCB(Card.JCB),
    Discover(Card.DISCOVER),
    DinersClub(Card.DINERS_CLUB);
    
    private final String value;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[CardType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[CardType.AmericanExpress.ordinal()] = 1;
            iArr[CardType.MasterCard.ordinal()] = 2;
            iArr[CardType.Maestro.ordinal()] = 3;
            iArr[CardType.Visa.ordinal()] = 4;
            iArr[CardType.JCB.ordinal()] = 5;
            iArr[CardType.Discover.ordinal()] = 6;
            iArr[CardType.DinersClub.ordinal()] = 7;
        }
    }

    private CardType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    public final int getIcon() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return R.drawable.icon_payment_american_express;
            case 2:
                return R.drawable.icon_payment_mastercard;
            case 3:
                return R.drawable.icon_payment_maestro;
            case 4:
                return R.drawable.icon_payment_visa;
            case 5:
                return R.drawable.icon_payment_jcb;
            case 6:
                return R.drawable.icon_payment_discover;
            case 7:
                return R.drawable.icon_payment_dci;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
