package org.java_websocket.protocols;

import java.util.regex.Pattern;

public class Protocol implements IProtocol {
    private static final Pattern patternComma = Pattern.compile(",");
    private static final Pattern patternSpace = Pattern.compile(" ");
    private final String providedProtocol;

    public Protocol(String str) {
        if (str != null) {
            this.providedProtocol = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean acceptProvidedProtocol(String str) {
        for (String equals : patternComma.split(patternSpace.matcher(str).replaceAll(""))) {
            if (this.providedProtocol.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public String getProvidedProtocol() {
        return this.providedProtocol;
    }

    public IProtocol copyInstance() {
        return new Protocol(getProvidedProtocol());
    }

    public String toString() {
        return getProvidedProtocol();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.providedProtocol.equals(((Protocol) obj).providedProtocol);
    }

    public int hashCode() {
        return this.providedProtocol.hashCode();
    }
}
