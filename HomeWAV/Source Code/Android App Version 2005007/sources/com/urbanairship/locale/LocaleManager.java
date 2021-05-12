package com.urbanairship.locale;

import android.content.Context;
import androidx.core.os.ConfigurationCompat;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocaleManager {
    public static final String LOCALE_OVERRIDE_COUNTRY_KEY = "com.urbanairship.LOCALE_OVERRIDE_COUNTRY";
    public static final String LOCALE_OVERRIDE_LANGUAGE_KEY = "com.urbanairship.LOCALE_OVERRIDE_LANGUAGE";
    public static final String LOCALE_OVERRIDE_VARIANT_KEY = "com.urbanairship.LOCALE_OVERRIDE_VARIANT";
    private final Context context;
    private volatile Locale deviceLocale;
    private List<LocaleChangedListener> localeChangedListeners = new CopyOnWriteArrayList();
    private PreferenceDataStore preferenceDataStore;

    public LocaleManager(Context context2, PreferenceDataStore preferenceDataStore2) {
        this.preferenceDataStore = preferenceDataStore2;
        this.context = context2.getApplicationContext();
    }

    public void addListener(LocaleChangedListener localeChangedListener) {
        this.localeChangedListeners.add(localeChangedListener);
    }

    public void removeListener(LocaleChangedListener localeChangedListener) {
        this.localeChangedListeners.remove(localeChangedListener);
    }

    /* access modifiers changed from: package-private */
    public void onDeviceLocaleChanged() {
        synchronized (this) {
            this.deviceLocale = ConfigurationCompat.getLocales(this.context.getResources().getConfiguration()).get(0);
            Logger.debug("Device Locale changed. Locale: %s.", this.deviceLocale);
            if (getLocaleOverride() == null) {
                notifyLocaleChanged(this.deviceLocale);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyLocaleChanged(Locale locale) {
        for (LocaleChangedListener onLocaleChanged : this.localeChangedListeners) {
            onLocaleChanged.onLocaleChanged(locale);
        }
    }

    public void setLocaleOverride(Locale locale) {
        synchronized (this) {
            Locale locale2 = getLocale();
            if (locale != null) {
                this.preferenceDataStore.put(LOCALE_OVERRIDE_COUNTRY_KEY, locale.getCountry());
                this.preferenceDataStore.put(LOCALE_OVERRIDE_LANGUAGE_KEY, locale.getLanguage());
                this.preferenceDataStore.put(LOCALE_OVERRIDE_VARIANT_KEY, locale.getVariant());
            } else {
                this.preferenceDataStore.remove(LOCALE_OVERRIDE_COUNTRY_KEY);
                this.preferenceDataStore.remove(LOCALE_OVERRIDE_LANGUAGE_KEY);
                this.preferenceDataStore.remove(LOCALE_OVERRIDE_VARIANT_KEY);
            }
            if (locale2 != getLocale()) {
                notifyLocaleChanged(getLocale());
            }
        }
    }

    private Locale getLocaleOverride() {
        String string = this.preferenceDataStore.getString(LOCALE_OVERRIDE_LANGUAGE_KEY, (String) null);
        String string2 = this.preferenceDataStore.getString(LOCALE_OVERRIDE_COUNTRY_KEY, (String) null);
        String string3 = this.preferenceDataStore.getString(LOCALE_OVERRIDE_VARIANT_KEY, (String) null);
        if (string == null || string2 == null || string3 == null) {
            return null;
        }
        return new Locale(string, string2, string3);
    }

    public Locale getLocale() {
        if (getLocaleOverride() != null) {
            return getLocaleOverride();
        }
        if (this.deviceLocale == null) {
            this.deviceLocale = ConfigurationCompat.getLocales(this.context.getResources().getConfiguration()).get(0);
        }
        return this.deviceLocale;
    }
}
