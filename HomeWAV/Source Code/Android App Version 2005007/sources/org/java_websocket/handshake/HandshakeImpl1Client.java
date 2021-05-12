package org.java_websocket.handshake;

import org.slf4j.Marker;

public class HandshakeImpl1Client extends HandshakedataImpl1 implements ClientHandshakeBuilder {
    private String resourceDescriptor = Marker.ANY_MARKER;

    public void setResourceDescriptor(String str) {
        if (str != null) {
            this.resourceDescriptor = str;
            return;
        }
        throw new IllegalArgumentException("http resource descriptor must not be null");
    }

    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }
}
