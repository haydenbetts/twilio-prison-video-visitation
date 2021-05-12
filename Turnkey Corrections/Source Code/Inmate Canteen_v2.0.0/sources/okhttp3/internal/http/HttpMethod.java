package okhttp3.internal.http;

import com.github.kevinsawicki.http.HttpRequest;

public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals(HttpRequest.METHOD_POST) || str.equals(HttpRequest.METHOD_PATCH) || str.equals(HttpRequest.METHOD_PUT) || str.equals(HttpRequest.METHOD_DELETE) || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(HttpRequest.METHOD_POST) || str.equals(HttpRequest.METHOD_PUT) || str.equals(HttpRequest.METHOD_PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return !str.equals(HttpRequest.METHOD_GET) && !str.equals(HttpRequest.METHOD_HEAD);
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
