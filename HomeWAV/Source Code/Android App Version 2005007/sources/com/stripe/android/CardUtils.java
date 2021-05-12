package com.stripe.android;

import com.stripe.android.model.Card;

public class CardUtils {
    private static final int LENGTH_AMERICAN_EXPRESS = 15;
    private static final int LENGTH_COMMON_CARD = 16;
    private static final int LENGTH_DINERS_CLUB = 14;

    public static String getPossibleCardType(String str) {
        return getPossibleCardType(str, true);
    }

    public static boolean isValidCardNumber(String str) {
        String removeSpacesAndHyphens = StripeTextUtils.removeSpacesAndHyphens(str);
        return isValidLuhnNumber(removeSpacesAndHyphens) && isValidCardLength(removeSpacesAndHyphens);
    }

    static boolean isValidLuhnNumber(String str) {
        if (str == null) {
            return false;
        }
        int i = 0;
        boolean z = true;
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (!Character.isDigit(charAt)) {
                return false;
            }
            int numericValue = Character.getNumericValue(charAt);
            z = !z;
            if (z) {
                numericValue *= 2;
            }
            if (numericValue > 9) {
                numericValue -= 9;
            }
            i += numericValue;
        }
        if (i % 10 == 0) {
            return true;
        }
        return false;
    }

    static boolean isValidCardLength(String str) {
        if (str == null || !isValidCardLength(str, getPossibleCardType(str, false))) {
            return false;
        }
        return true;
    }

    static boolean isValidCardLength(String str, String str2) {
        if (str == null || "Unknown".equals(str2)) {
            return false;
        }
        int length = str.length();
        str2.hashCode();
        if (!str2.equals(Card.DINERS_CLUB)) {
            if (!str2.equals(Card.AMERICAN_EXPRESS)) {
                if (length == 16) {
                    return true;
                }
                return false;
            } else if (length == 15) {
                return true;
            } else {
                return false;
            }
        } else if (length == 14) {
            return true;
        } else {
            return false;
        }
    }

    private static String getPossibleCardType(String str, boolean z) {
        if (StripeTextUtils.isBlank(str)) {
            return "Unknown";
        }
        if (z) {
            str = StripeTextUtils.removeSpacesAndHyphens(str);
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_AMERICAN_EXPRESS)) {
            return Card.AMERICAN_EXPRESS;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_DISCOVER)) {
            return Card.DISCOVER;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_JCB)) {
            return Card.JCB;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_DINERS_CLUB)) {
            return Card.DINERS_CLUB;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_VISA)) {
            return Card.VISA;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_MASTERCARD)) {
            return Card.MASTERCARD;
        }
        if (StripeTextUtils.hasAnyPrefix(str, Card.PREFIXES_UNIONPAY)) {
            return Card.UNIONPAY;
        }
        return "Unknown";
    }
}
