package fm.liveswitch;

import java.net.URI;
import java.net.URLEncoder;

public class UriExtensions {
    public static boolean tryCreate(String str, UriKind uriKind, Holder<URI> holder) {
        try {
            holder.setValue(URI.create(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryCreate(URI uri, String str, Holder<URI> holder) {
        if (uri == null) {
            return false;
        }
        try {
            holder.setValue(URI.create(uri.toString()).resolve(str));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String escapeDataString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getAbsolutePath(URI uri) {
        return uri.getPath();
    }

    public static String getDnsSafeHost(URI uri) {
        return uri.getHost();
    }

    public static String getQuery(URI uri) {
        StringBuilder sb = new StringBuilder();
        String query = uri.getQuery();
        if (query != null && query.length() > 0) {
            sb.append("?");
            sb.append(query);
        }
        return sb.toString();
    }
}
