package com.urbanairship.http;

import java.net.URL;

public class RequestFactory {
    public static final RequestFactory DEFAULT_REQUEST_FACTORY = new RequestFactory();

    public Request createRequest(String str, URL url) {
        return new Request(str, url);
    }

    public Request createRequest() {
        return new Request();
    }
}
