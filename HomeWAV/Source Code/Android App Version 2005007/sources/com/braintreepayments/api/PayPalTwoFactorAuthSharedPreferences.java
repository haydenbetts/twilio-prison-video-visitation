package com.braintreepayments.api;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.util.Base64;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.models.PayPalAccountNonce;

class PayPalTwoFactorAuthSharedPreferences {
    private static final String PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY = "com.braintreepayments.api.PayPalTwoFactorAuth.PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY";

    PayPalTwoFactorAuthSharedPreferences() {
    }

    static void persistPayPalAccountNonce(BraintreeFragment braintreeFragment, PayPalAccountNonce payPalAccountNonce) {
        Parcel obtain = Parcel.obtain();
        payPalAccountNonce.writeToParcel(obtain, 0);
        BraintreeSharedPreferences.getSharedPreferences(braintreeFragment.getApplicationContext()).edit().putString(PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY, Base64.encodeToString(obtain.marshall(), 0)).apply();
    }

    /* JADX INFO: finally extract failed */
    static PayPalAccountNonce getPersistedPayPalAccountNonce(BraintreeFragment braintreeFragment) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(braintreeFragment.getApplicationContext());
        try {
            byte[] decode = Base64.decode(sharedPreferences.getString(PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY, ""), 0);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            PayPalAccountNonce createFromParcel = PayPalAccountNonce.CREATOR.createFromParcel(obtain);
            sharedPreferences.edit().remove(PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY).apply();
            return createFromParcel;
        } catch (Exception unused) {
            sharedPreferences.edit().remove(PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY).apply();
            return null;
        } catch (Throwable th) {
            sharedPreferences.edit().remove(PAYPAL_TWO_FACTOR_AUTH_REQUEST_KEY).apply();
            throw th;
        }
    }
}
