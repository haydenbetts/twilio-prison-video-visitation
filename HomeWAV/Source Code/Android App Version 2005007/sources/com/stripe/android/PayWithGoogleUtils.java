package com.stripe.android;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;

public class PayWithGoogleUtils {
    public static String getPriceString(long j, Currency currency) {
        int defaultFractionDigits = currency.getDefaultFractionDigits();
        int length = String.valueOf(j).length();
        StringBuilder sb = new StringBuilder();
        if (defaultFractionDigits == 0) {
            for (int i = 0; i < length; i++) {
                sb.append('#');
            }
            DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
            decimalFormat.setCurrency(currency);
            decimalFormat.setGroupingUsed(false);
            return decimalFormat.format(j);
        }
        int i2 = length - defaultFractionDigits;
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append('#');
        }
        if (length <= defaultFractionDigits) {
            sb.append('0');
        }
        sb.append('.');
        for (int i4 = 0; i4 < defaultFractionDigits; i4++) {
            sb.append('0');
        }
        double d = (double) j;
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat2 = new DecimalFormat(sb.toString(), decimalFormatSymbols);
        decimalFormat2.setCurrency(currency);
        decimalFormat2.setGroupingUsed(false);
        return decimalFormat2.format(d / Math.pow(10.0d, (double) defaultFractionDigits));
    }
}
