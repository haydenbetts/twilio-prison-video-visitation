package com.stripe.android.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

class CountryUtils {
    private static final String[] NO_POSTAL_CODE_COUNTRIES;
    private static final Set<String> NO_POSTAL_CODE_COUNTRIES_SET;

    CountryUtils() {
    }

    static {
        String[] strArr = {"AE", "AG", "AN", "AO", "AW", "BF", "BI", "BJ", "BO", "BS", "BW", "BZ", "CD", "CF", "CG", "CI", "CK", "CM", "DJ", "DM", "ER", "FJ", "GD", "GH", "GM", "GN", "GQ", "GY", "HK", "IE", "JM", "KE", "KI", "KM", "KN", "KP", "LC", "ML", "MO", "MR", "MS", "MU", "MW", "NR", "NU", "PA", "QA", "RW", "SB", "SC", "SL", "SO", "SR", "ST", "SY", "TF", "TK", "TL", "TO", "TT", "TV", "TZ", "UG", "VU", "YE", "ZA", "ZW"};
        NO_POSTAL_CODE_COUNTRIES = strArr;
        NO_POSTAL_CODE_COUNTRIES_SET = new HashSet(Arrays.asList(strArr));
    }

    static boolean doesCountryUsePostalCode(String str) {
        return !NO_POSTAL_CODE_COUNTRIES_SET.contains(str);
    }

    static boolean isUSZipCodeValid(String str) {
        return Pattern.matches("^[0-9]{5}(?:-[0-9]{4})?$", str);
    }

    static boolean isCanadianPostalCodeValid(String str) {
        return Pattern.matches("^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$", str);
    }

    static boolean isUKPostcodeValid(String str) {
        return Pattern.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", str);
    }

    static Map<String, String> getCountryNameToCodeMap() {
        HashMap hashMap = new HashMap();
        for (String str : Locale.getISOCountries()) {
            hashMap.put(new Locale("", str).getDisplayCountry(), str);
        }
        return hashMap;
    }
}
