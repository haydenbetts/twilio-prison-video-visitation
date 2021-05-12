package com.urbanairship.json;

public class JsonException extends Exception {
    public JsonException(String str) {
        super(str);
    }

    public JsonException(String str, Throwable th) {
        super(str, th);
    }
}
