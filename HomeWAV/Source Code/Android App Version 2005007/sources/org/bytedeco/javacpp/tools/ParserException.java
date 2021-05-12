package org.bytedeco.javacpp.tools;

public class ParserException extends Exception {
    public ParserException(String str) {
        super(str);
    }

    public ParserException(String str, Throwable th) {
        super(str, th);
    }

    public ParserException(Throwable th) {
        super(th);
    }
}
