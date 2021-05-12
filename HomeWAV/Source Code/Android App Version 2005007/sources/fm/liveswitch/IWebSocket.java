package fm.liveswitch;

public interface IWebSocket {
    void close();

    void close(WebSocketCloseArgs webSocketCloseArgs);

    int getBufferedAmount();

    boolean getIsOpen();

    boolean getSecure();

    void open(WebSocketOpenArgs webSocketOpenArgs);

    void send(WebSocketSendArgs webSocketSendArgs);
}
