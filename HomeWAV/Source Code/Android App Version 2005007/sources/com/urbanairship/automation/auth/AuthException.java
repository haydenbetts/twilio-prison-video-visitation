package com.urbanairship.automation.auth;

public class AuthException extends Exception {
    public AuthException(String str, Throwable th) {
        super(str, th);
    }

    public AuthException(String str) {
        super(str);
    }
}
