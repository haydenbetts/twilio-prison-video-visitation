package com.crypho.plugins;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesHandler {
    private SharedPreferences prefs;

    public SharedPreferencesHandler(String str, Context context) {
        this.prefs = context.getSharedPreferences(str + "_SS", 0);
    }

    /* access modifiers changed from: package-private */
    public void store(String str, String str2) {
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.putString("_SS_" + str, str2);
        edit.commit();
    }

    /* access modifiers changed from: package-private */
    public String fetch(String str) {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences.getString("_SS_" + str, (String) null);
    }

    /* access modifiers changed from: package-private */
    public void remove(String str) {
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.remove("_SS_" + str);
        edit.commit();
    }

    /* access modifiers changed from: package-private */
    public Set keys() {
        HashSet hashSet = new HashSet();
        for (String next : this.prefs.getAll().keySet()) {
            if (next.startsWith("_SS_") && !next.startsWith("_SS_MIGRATED_")) {
                hashSet.add(next.replaceFirst("^_SS_", ""));
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        SharedPreferences.Editor edit = this.prefs.edit();
        edit.clear();
        edit.commit();
    }
}
