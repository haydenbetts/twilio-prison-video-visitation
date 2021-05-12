package com.urbanairship.util;

import com.urbanairship.Predicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Marker;

public class IvyVersionMatcher implements Predicate<String>, JsonSerializable {
    private static final String END_EXCLUSIVE = "[";
    private static final String END_INCLUSIVE = "]";
    private static final String END_INFINITE = ")";
    private static final String END_PATTERN;
    private static final Pattern EXACT_VERSION = Pattern.compile(EXACT_VERSION_PATTERN);
    private static final String EXACT_VERSION_PATTERN = "^([0-9]+)(\\.[0-9]+)?(\\.[0-9]+)?$";
    private static final String RANGE_SEPARATOR = ",";
    private static final String START_EXCLUSIVE = "]";
    private static final String START_INCLUSIVE = "[";
    private static final String START_INFINITE = "(";
    private static final String START_PATTERN;
    private static final Pattern SUB_VERSION = Pattern.compile(SUB_VERSION_PATTERN);
    private static final String SUB_VERSION_PATTERN = "^(.*)\\+$";
    private static final String VERSION_PATTERN = "([0-9]+)(\\.[0-9]+)?(\\.[0-9]+)?";
    private static final Pattern VERSION_RANGE;
    private static final String VERSION_RANGE_PATTERN;
    private static final String WHITESPACE = "\\s";
    private final String constraint;
    private final Predicate<String> predicate;

    static {
        String format = String.format(Locale.US, "([\\%s\\%s\\%s])", new Object[]{"[", "]", START_INFINITE});
        START_PATTERN = format;
        String format2 = String.format(Locale.US, "([\\%s\\%s\\%s])", new Object[]{"]", "[", END_INFINITE});
        END_PATTERN = format2;
        String format3 = String.format(Locale.US, "^(%s(%s)?)%s((%s)?%s)", new Object[]{format, VERSION_PATTERN, RANGE_SEPARATOR, VERSION_PATTERN, format2});
        VERSION_RANGE_PATTERN = format3;
        VERSION_RANGE = Pattern.compile(format3);
    }

    private IvyVersionMatcher(Predicate<String> predicate2, String str) {
        this.predicate = predicate2;
        this.constraint = str;
    }

    public static IvyVersionMatcher newMatcher(String str) {
        String replaceAll = str.replaceAll(WHITESPACE, "");
        Predicate<String> parseExactVersionConstraint = parseExactVersionConstraint(replaceAll);
        if (parseExactVersionConstraint != null) {
            return new IvyVersionMatcher(parseExactVersionConstraint, replaceAll);
        }
        Predicate<String> parseSubVersionConstraint = parseSubVersionConstraint(replaceAll);
        if (parseSubVersionConstraint != null) {
            return new IvyVersionMatcher(parseSubVersionConstraint, replaceAll);
        }
        Predicate<String> parseVersionRangeConstraint = parseVersionRangeConstraint(replaceAll);
        if (parseVersionRangeConstraint != null) {
            return new IvyVersionMatcher(parseVersionRangeConstraint, replaceAll);
        }
        throw new IllegalArgumentException("Invalid constraint: " + replaceAll);
    }

    public boolean apply(String str) {
        if (str == null) {
            return false;
        }
        return this.predicate.apply(str.trim());
    }

    private static Predicate<String> parseSubVersionConstraint(String str) {
        Matcher matcher = SUB_VERSION.matcher(str);
        final String str2 = null;
        if (!matcher.matches()) {
            return null;
        }
        if (Marker.ANY_NON_NULL_MARKER.equals(str)) {
            return new Predicate<String>() {
                public boolean apply(String str) {
                    return true;
                }
            };
        }
        if (matcher.groupCount() >= 1) {
            str2 = matcher.group(1);
        }
        return new Predicate<String>() {
            public boolean apply(String str) {
                String str2 = str2;
                if (str2 == null) {
                    return false;
                }
                return str.startsWith(str2);
            }
        };
    }

    private static Predicate<String> parseVersionRangeConstraint(String str) {
        final Version version;
        final String str2;
        final Version version2;
        final String str3;
        Matcher matcher = VERSION_RANGE.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String group = matcher.groupCount() >= 7 ? matcher.group(7) : null;
        if (!UAStringUtil.isEmpty(group)) {
            str2 = group.substring(group.length() - 1);
            version = group.length() > 1 ? new Version(group.substring(0, group.length() - 1)) : null;
        } else {
            str2 = null;
            version = null;
        }
        String group2 = matcher.groupCount() >= 1 ? matcher.group(1) : null;
        if (!UAStringUtil.isEmpty(group2)) {
            str3 = group2.substring(0, 1);
            version2 = group2.length() > 1 ? new Version(group2.substring(1)) : null;
        } else {
            str3 = null;
            version2 = null;
        }
        if (END_INFINITE.equals(str2) && version != null) {
            return null;
        }
        if (!START_INFINITE.equals(str3) || version2 == null) {
            return new Predicate<String>() {
                public boolean apply(String str) {
                    try {
                        Version version = new Version(str);
                        String str2 = str2;
                        if (!(str2 == null || version == null)) {
                            str2.hashCode();
                            if (!str2.equals("[")) {
                                if (str2.equals("]") && version.compareTo(version) > 0) {
                                    return false;
                                }
                            } else if (version.compareTo(version) >= 0) {
                                return false;
                            }
                        }
                        String str3 = str3;
                        if (str3 == null || version2 == null) {
                            return true;
                        }
                        str3.hashCode();
                        if (!str3.equals("[")) {
                            if (str3.equals("]") && version.compareTo(version2) <= 0) {
                                return false;
                            }
                            return true;
                        } else if (version.compareTo(version2) < 0) {
                            return false;
                        } else {
                            return true;
                        }
                    } catch (NumberFormatException unused) {
                        return false;
                    }
                }
            };
        }
        return null;
    }

    private static Predicate<String> parseExactVersionConstraint(final String str) {
        if (!EXACT_VERSION.matcher(str).matches()) {
            return null;
        }
        return new Predicate<String>() {
            public boolean apply(String str) {
                return str.equals(str);
            }
        };
    }

    public JsonValue toJsonValue() {
        return JsonValue.wrap(this.constraint);
    }

    private static class Version implements Comparable<Version> {
        final String version;
        final int[] versionComponent = {0, 0, 0};

        public Version(String str) {
            this.version = str;
            String[] split = str.split("\\.");
            int i = 0;
            while (i < 3 && split.length > i) {
                this.versionComponent[i] = Integer.parseInt(split[i]);
                i++;
            }
        }

        public int compareTo(Version version2) {
            for (int i = 0; i < 3; i++) {
                int i2 = this.versionComponent[i] - version2.versionComponent[i];
                if (i2 != 0) {
                    return i2 > 0 ? 1 : -1;
                }
            }
            return 0;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.constraint;
        String str2 = ((IvyVersionMatcher) obj).constraint;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.constraint;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
