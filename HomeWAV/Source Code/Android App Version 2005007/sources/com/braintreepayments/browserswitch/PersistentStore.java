package com.braintreepayments.browserswitch;

import android.content.Context;

class PersistentStore {
    static final String PREFERENCES_KEY = "com.braintreepayament.browserswitch.persistentstore";

    PersistentStore() {
    }

    static void put(String str, String str2, Context context) {
        context.getApplicationContext().getSharedPreferences(PREFERENCES_KEY, 0).edit().putString(str, str2).apply();
    }

    static String get(String str, Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFERENCES_KEY, 0).getString(str, (String) null);
    }

    static void remove(String str, Context context) {
        context.getApplicationContext().getSharedPreferences(PREFERENCES_KEY, 0).edit().remove(str).apply();
    }
}
