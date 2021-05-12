package com.urbanairship.http;

public class RequestException extends Exception {
    public RequestException(String str) {
        super(str);
    }

    public RequestException(String str, Throwable th) {
        super(str, th);
    }
}
