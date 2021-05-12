package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata;

public class DefaultExtension implements IExtension {
    public boolean acceptProvidedExtensionAsClient(String str) {
        return true;
    }

    public boolean acceptProvidedExtensionAsServer(String str) {
        return true;
    }

    public void decodeFrame(Framedata framedata) throws InvalidDataException {
    }

    public void encodeFrame(Framedata framedata) {
    }

    public String getProvidedExtensionAsClient() {
        return "";
    }

    public String getProvidedExtensionAsServer() {
        return "";
    }

    public void reset() {
    }

    public void isFrameValid(Framedata framedata) throws InvalidDataException {
        if (framedata.isRSV1() || framedata.isRSV2() || framedata.isRSV3()) {
            throw new InvalidFrameException("bad rsv RSV1: " + framedata.isRSV1() + " RSV2: " + framedata.isRSV2() + " RSV3: " + framedata.isRSV3());
        }
    }

    public IExtension copyInstance() {
        return new DefaultExtension();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass());
    }
}
