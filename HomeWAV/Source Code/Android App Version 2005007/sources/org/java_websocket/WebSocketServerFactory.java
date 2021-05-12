package org.java_websocket;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.drafts.Draft;

public interface WebSocketServerFactory extends WebSocketFactory {
    void close();

    WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list);

    WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft);

    ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException;
}
