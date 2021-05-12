package com.urbanairship.messagecenter;

import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.util.UAStringUtil;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class User {
    private static final String KEY_PREFIX = "com.urbanairship.user";
    private static final String USER_ID_KEY = "com.urbanairship.user.ID";
    private static final String USER_PASSWORD_KEY = "com.urbanairship.user.PASSWORD";
    private static final String USER_REGISTERED_CHANNEL_ID_KEY = "com.urbanairship.user.REGISTERED_CHANNEL_ID";
    private static final String USER_TOKEN_KEY = "com.urbanairship.user.USER_TOKEN";
    private final AirshipChannel channel;
    private final List<Listener> listeners = new CopyOnWriteArrayList();
    private final PreferenceDataStore preferences;

    public interface Listener {
        void onUserUpdated(boolean z);
    }

    User(PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel) {
        this.preferences = preferenceDataStore;
        this.channel = airshipChannel;
        String string = preferenceDataStore.getString(USER_PASSWORD_KEY, (String) null);
        if (!UAStringUtil.isEmpty(string) && preferenceDataStore.putSync(USER_TOKEN_KEY, encode(string, preferenceDataStore.getString(USER_ID_KEY, (String) null)))) {
            preferenceDataStore.remove(USER_PASSWORD_KEY);
        }
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /* access modifiers changed from: package-private */
    public void onUserUpdated(boolean z) {
        for (Listener onUserUpdated : this.listeners) {
            onUserUpdated.onUserUpdated(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void onUpdated(String str) {
        if (!str.equals(getRegisteredChannelId())) {
            this.preferences.put(USER_REGISTERED_CHANNEL_ID_KEY, str);
        }
    }

    /* access modifiers changed from: package-private */
    public void onCreated(String str, String str2, String str3) {
        setRegisteredChannelId(str3);
        setUser(str, str2);
    }

    public static boolean isCreated() {
        return MessageCenter.shared().getUser().isUserCreated();
    }

    public boolean isUserCreated() {
        return !UAStringUtil.isEmpty(getId()) && !UAStringUtil.isEmpty(getPassword());
    }

    /* access modifiers changed from: package-private */
    public void setUser(String str, String str2) {
        Logger.debug("RichPushUser - Setting Rich Push user: %s", str);
        this.preferences.put(USER_ID_KEY, str);
        this.preferences.put(USER_TOKEN_KEY, encode(str2, str));
    }

    public String getId() {
        if (this.preferences.getString(USER_TOKEN_KEY, (String) null) != null) {
            return this.preferences.getString(USER_ID_KEY, (String) null);
        }
        return null;
    }

    public String getPassword() {
        if (this.preferences.getString(USER_ID_KEY, (String) null) != null) {
            return decode(this.preferences.getString(USER_TOKEN_KEY, (String) null), getId());
        }
        return null;
    }

    private static String encode(String str, String str2) {
        if (UAStringUtil.isEmpty(str) || UAStringUtil.isEmpty(str2)) {
            return null;
        }
        byte[] xor = xor(str.getBytes(), str2.getBytes());
        StringBuilder sb = new StringBuilder();
        int length = xor.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(xor[i])}));
        }
        return sb.toString();
    }

    private static String decode(String str, String str2) {
        if (!UAStringUtil.isEmpty(str) && !UAStringUtil.isEmpty(str2)) {
            int length = str.length();
            if (length % 2 != 0) {
                return null;
            }
            try {
                byte[] bArr = new byte[(length / 2)];
                int i = 0;
                while (i < length) {
                    int i2 = i + 2;
                    bArr[i / 2] = Byte.parseByte(str.substring(i, i2), 16);
                    i = i2;
                }
                return new String(xor(bArr, str2.getBytes()), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Logger.error(e, "RichPushUser - Unable to decode string.", new Object[0]);
            } catch (NumberFormatException e2) {
                Logger.error(e2, "RichPushUser - String contains invalid hex numbers.", new Object[0]);
            }
        }
        return null;
    }

    private static byte[] xor(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    private String getRegisteredChannelId() {
        return this.preferences.getString(USER_REGISTERED_CHANNEL_ID_KEY, "");
    }

    private void setRegisteredChannelId(String str) {
        this.preferences.put(USER_REGISTERED_CHANNEL_ID_KEY, str);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldUpdate() {
        return this.channel.getId() != null && !getRegisteredChannelId().equals(this.channel.getId());
    }
}
