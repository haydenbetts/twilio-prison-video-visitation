package com.stripe.android;

class PaymentSessionUtils {
    PaymentSessionUtils() {
    }

    public static String paymentResultFromString(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1867169789:
                if (str.equals("success")) {
                    c = 0;
                    break;
                }
                break;
            case -1010022050:
                if (str.equals(PaymentResultListener.INCOMPLETE)) {
                    c = 1;
                    break;
                }
                break;
            case 2043678173:
                if (str.equals(PaymentResultListener.USER_CANCELLED)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "success";
            case 1:
                return PaymentResultListener.INCOMPLETE;
            case 2:
                return PaymentResultListener.USER_CANCELLED;
            default:
                return "error";
        }
    }
}
