package com.bumptech.glide.load.engine;

import java.util.Objects;

public class ErrorWrappingGlideException extends Exception {
    public ErrorWrappingGlideException(Error error) {
        super(error);
        Objects.requireNonNull(error, "The causing error must not be null");
    }

    public Error getCause() {
        return (Error) super.getCause();
    }
}
