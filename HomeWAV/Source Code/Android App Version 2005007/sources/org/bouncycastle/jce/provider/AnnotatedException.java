package org.bouncycastle.jce.provider;

import org.bouncycastle.jce.exception.ExtException;

public class AnnotatedException extends Exception implements ExtException {
    private Throwable _underlyingException;

    public AnnotatedException(String str) {
        this(str, (Throwable) null);
    }

    public AnnotatedException(String str, Throwable th) {
        super(str);
        this._underlyingException = th;
    }

    public Throwable getCause() {
        return this._underlyingException;
    }

    /* access modifiers changed from: package-private */
    public Throwable getUnderlyingException() {
        return this._underlyingException;
    }
}
