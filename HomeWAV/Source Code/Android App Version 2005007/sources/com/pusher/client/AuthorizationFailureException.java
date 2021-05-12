package com.pusher.client;

public class AuthorizationFailureException extends RuntimeException {
    private static final long serialVersionUID = -7208133561904200801L;

    public AuthorizationFailureException() {
    }

    public AuthorizationFailureException(String str) {
        super(str);
    }

    public AuthorizationFailureException(Exception exc) {
        super(exc);
    }

    public AuthorizationFailureException(String str, Exception exc) {
        super(str, exc);
    }
}
