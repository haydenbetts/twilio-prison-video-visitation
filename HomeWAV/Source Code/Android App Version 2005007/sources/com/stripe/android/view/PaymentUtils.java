package com.stripe.android.view;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class PaymentUtils {
    static String formatPriceStringUsingFree(long j, Currency currency, String str) {
        if (j == 0) {
            return str;
        }
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol(currency.getSymbol(Locale.getDefault()));
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return formatPriceString((double) j, currency);
    }

    static String formatPriceString(double d, Currency currency) {
        double pow = d / Math.pow(10.0d, (double) currency.getDefaultFractionDigits());
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        try {
            DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) currencyInstance).getDecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol(currency.getSymbol(Locale.getDefault()));
            ((DecimalFormat) currencyInstance).setDecimalFormatSymbols(decimalFormatSymbols);
            return currencyInstance.format(pow);
        } catch (ClassCastException unused) {
            return currencyInstance.format(pow);
        }
    }
}
