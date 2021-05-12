package com.urbanairship.js;

import android.net.Uri;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.slf4j.Marker;

public class UrlAllowList {
    private static final String HOST_REGEX = "((\\*)|(\\*\\.[^/\\*]+)|([^/\\*]+))";
    private static final String PATH_REGEX = "(.*)";
    private static final String PATTERN_REGEX;
    private static final String REGEX_SPECIAL_CHARACTERS = "\\.[]{}()^$?+|*";
    private static final String SCHEME_REGEX = "([^\\s]+)";
    public static final int SCOPE_ALL = 3;
    public static final int SCOPE_JAVASCRIPT_INTERFACE = 1;
    public static final int SCOPE_OPEN_URL = 2;
    private static final Pattern VALID_PATTERN;
    private final List<Entry> entries = new ArrayList();
    private OnUrlAllowListCallback urlAllowListCallback;

    public interface OnUrlAllowListCallback {
        boolean allowUrl(String str, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Scope {
    }

    static {
        String format = String.format(Locale.US, "^((\\*)|((%s://%s/%s)|(%s://%s)|(%s:/[^/]%s)|(%s:/)|(%s:///%s)))", new Object[]{SCHEME_REGEX, HOST_REGEX, PATH_REGEX, SCHEME_REGEX, HOST_REGEX, SCHEME_REGEX, PATH_REGEX, SCHEME_REGEX, SCHEME_REGEX, PATH_REGEX});
        PATTERN_REGEX = format;
        VALID_PATTERN = Pattern.compile(format, 2);
    }

    public boolean addEntry(String str) {
        return addEntry(str, 3);
    }

    public boolean addEntry(String str, int i) {
        Pattern pattern;
        if (str == null || !VALID_PATTERN.matcher(str).matches()) {
            Logger.error("Invalid URL allow list pattern %s", str);
            return false;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        String encodedAuthority = parse.getEncodedAuthority();
        String path = parse.getPath();
        Pattern pattern2 = null;
        Pattern compile = (UAStringUtil.isEmpty(scheme) || scheme.equals(Marker.ANY_MARKER)) ? null : Pattern.compile(escapeRegEx(scheme, false));
        if (UAStringUtil.isEmpty(encodedAuthority) || encodedAuthority.equals(Marker.ANY_MARKER)) {
            pattern = null;
        } else if (encodedAuthority.startsWith("*.")) {
            pattern = Pattern.compile("(.*\\.)?" + escapeRegEx(encodedAuthority.substring(2), true));
        } else {
            pattern = Pattern.compile(escapeRegEx(encodedAuthority, true));
        }
        if (!UAStringUtil.isEmpty(path) && !path.equals("/*")) {
            pattern2 = Pattern.compile(escapeRegEx(path, false));
        }
        addEntry(new UriPattern(compile, pattern, pattern2), i);
        return true;
    }

    private void addEntry(UriPattern uriPattern, int i) {
        synchronized (this.entries) {
            this.entries.add(new Entry(uriPattern, i));
        }
    }

    public boolean isAllowed(String str) {
        return isAllowed(str, 3);
    }

    public boolean isAllowed(String str, int i) {
        int i2;
        OnUrlAllowListCallback onUrlAllowListCallback;
        boolean z = false;
        if (str == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        synchronized (this.entries) {
            i2 = 0;
            for (Entry next : this.entries) {
                if (next.pattern.matches(parse)) {
                    i2 |= next.scope;
                }
            }
        }
        if ((i2 & i) == i) {
            z = true;
        }
        if (!z || (onUrlAllowListCallback = this.urlAllowListCallback) == null) {
            return z;
        }
        return onUrlAllowListCallback.allowUrl(str, i);
    }

    private String escapeRegEx(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (char valueOf : str.toCharArray()) {
            String valueOf2 = String.valueOf(valueOf);
            if (!z && valueOf2.equals(Marker.ANY_MARKER)) {
                sb.append(".");
            } else if (REGEX_SPECIAL_CHARACTERS.contains(valueOf2)) {
                sb.append("\\");
            }
            sb.append(valueOf2);
        }
        return sb.toString();
    }

    public static UrlAllowList createDefaultUrlAllowList(AirshipConfigOptions airshipConfigOptions) {
        UrlAllowList urlAllowList = new UrlAllowList();
        urlAllowList.addEntry("https://*.urbanairship.com");
        urlAllowList.addEntry("https://*.youtube.com", 2);
        urlAllowList.addEntry("https://*.asnapieu.com");
        for (String addEntry : airshipConfigOptions.urlAllowList) {
            urlAllowList.addEntry(addEntry, 3);
        }
        for (String addEntry2 : airshipConfigOptions.urlAllowListScopeJavaScriptInterface) {
            urlAllowList.addEntry(addEntry2, 1);
        }
        for (String addEntry3 : airshipConfigOptions.urlAllowListScopeOpenUrl) {
            urlAllowList.addEntry(addEntry3, 2);
        }
        return urlAllowList;
    }

    public void setUrlAllowListCallback(OnUrlAllowListCallback onUrlAllowListCallback) {
        this.urlAllowListCallback = onUrlAllowListCallback;
    }

    private static class UriPattern {
        private final Pattern host;
        private final Pattern path;
        private final Pattern scheme;

        UriPattern(Pattern pattern, Pattern pattern2, Pattern pattern3) {
            this.scheme = pattern;
            this.host = pattern2;
            this.path = pattern3;
        }

        /* access modifiers changed from: package-private */
        public boolean matches(Uri uri) {
            if (this.scheme != null && (uri.getScheme() == null || !this.scheme.matcher(uri.getScheme()).matches())) {
                return false;
            }
            if (this.host != null && (uri.getHost() == null || !this.host.matcher(uri.getHost()).matches())) {
                return false;
            }
            if (this.path == null || (uri.getPath() != null && this.path.matcher(uri.getPath()).matches())) {
                return true;
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UriPattern uriPattern = (UriPattern) obj;
            Pattern pattern = this.scheme;
            if (pattern == null ? uriPattern.scheme != null : !pattern.equals(uriPattern.scheme)) {
                return false;
            }
            Pattern pattern2 = this.host;
            if (pattern2 == null ? uriPattern.host != null : !pattern2.equals(uriPattern.host)) {
                return false;
            }
            Pattern pattern3 = this.path;
            Pattern pattern4 = uriPattern.path;
            if (pattern3 != null) {
                return pattern3.equals(pattern4);
            }
            if (pattern4 == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            Pattern pattern = this.scheme;
            int i = 0;
            int hashCode = (pattern != null ? pattern.hashCode() : 0) * 31;
            Pattern pattern2 = this.host;
            int hashCode2 = (hashCode + (pattern2 != null ? pattern2.hashCode() : 0)) * 31;
            Pattern pattern3 = this.path;
            if (pattern3 != null) {
                i = pattern3.hashCode();
            }
            return hashCode2 + i;
        }
    }

    private static class Entry {
        /* access modifiers changed from: private */
        public final UriPattern pattern;
        /* access modifiers changed from: private */
        public final int scope;

        private Entry(UriPattern uriPattern, int i) {
            this.scope = i;
            this.pattern = uriPattern;
        }
    }
}
