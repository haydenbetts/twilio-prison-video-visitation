package com.urbanairship.automation;

import android.content.Context;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.automation.tags.TagSelector;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.modules.location.AirshipLocationClient;
import com.urbanairship.push.PushManager;
import com.urbanairship.util.UAStringUtil;
import com.urbanairship.util.VersionUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class AudienceChecks {
    static boolean checkAudienceForScheduling(Context context, Audience audience, boolean z) {
        if (audience == null) {
            return true;
        }
        if (audience.getNewUser() != null && audience.getNewUser().booleanValue() != z) {
            return false;
        }
        if (audience.getTestDevices().isEmpty()) {
            return true;
        }
        byte[] sha256Digest = UAStringUtil.sha256Digest(UAirship.shared().getChannel().getId());
        if (sha256Digest != null && sha256Digest.length >= 16) {
            byte[] copyOf = Arrays.copyOf(sha256Digest, 16);
            for (String base64Decode : audience.getTestDevices()) {
                if (Arrays.equals(copyOf, UAStringUtil.base64Decode(base64Decode))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkAudience(Context context, Audience audience) {
        return checkAudience(context, audience, (Map<String, Set<String>>) null);
    }

    public static boolean checkAudience(Context context, Audience audience, Map<String, Set<String>> map) {
        boolean z = true;
        if (audience == null) {
            return true;
        }
        if (map == null) {
            map = TagSelector.EMPTY_TAG_GROUPS;
        }
        UAirship shared = UAirship.shared();
        AirshipLocationClient locationClient = shared.getLocationClient();
        PushManager pushManager = shared.getPushManager();
        AirshipChannel channel = shared.getChannel();
        boolean isDataCollectionEnabled = shared.isDataCollectionEnabled();
        if (audience.getLocationOptIn() != null) {
            if (!isDataCollectionEnabled) {
                return false;
            }
            if (locationClient == null || !locationClient.isOptIn()) {
                z = false;
            }
            if (audience.getLocationOptIn().booleanValue() != z) {
                return false;
            }
        }
        boolean areNotificationsOptedIn = pushManager.areNotificationsOptedIn();
        if ((audience.getNotificationsOptIn() != null && (!isDataCollectionEnabled || audience.getNotificationsOptIn().booleanValue() != areNotificationsOptedIn)) || !isLocaleConditionMet(context, audience)) {
            return false;
        }
        if (audience.getTagSelector() == null || (isDataCollectionEnabled && audience.getTagSelector().apply(channel.getTags(), map))) {
            return isAppVersionConditionMet(audience);
        }
        return false;
    }

    private static boolean isAppVersionConditionMet(Audience audience) {
        if (audience.getVersionPredicate() == null) {
            return true;
        }
        return audience.getVersionPredicate().apply(VersionUtils.createVersionObject());
    }

    private static Set<String> sanitizeLanguageTags(List<String> list) {
        HashSet hashSet = new HashSet();
        for (String next : list) {
            if (!UAStringUtil.isEmpty(next)) {
                if (next.endsWith("_") || next.endsWith("-")) {
                    Logger.debug("Sanitizing malformed language tag: " + next, new Object[0]);
                    hashSet.add(next.substring(0, next.length() + -1));
                } else {
                    hashSet.add(next);
                }
            }
        }
        return hashSet;
    }

    private static boolean isLocaleConditionMet(Context context, Audience audience) {
        if (audience.getLanguageTags().isEmpty()) {
            return true;
        }
        Locale firstMatch = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).getFirstMatch((String[]) audience.getLanguageTags().toArray(new String[0]));
        if (firstMatch == null) {
            return false;
        }
        try {
            LocaleListCompat forLanguageTags = LocaleListCompat.forLanguageTags(UAStringUtil.join(sanitizeLanguageTags(audience.getLanguageTags()), ","));
            for (int i = 0; i < forLanguageTags.size(); i++) {
                Locale locale = forLanguageTags.get(i);
                if (firstMatch.getLanguage().equals(locale.getLanguage())) {
                    if (UAStringUtil.isEmpty(locale.getCountry()) || locale.getCountry().equals(firstMatch.getCountry())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("Unable to construct locale list: ", e);
        }
        return false;
    }
}
