package fm.liveswitch;

public interface IRtpHeaderExtension {
    void fillBuffer(DataBuffer dataBuffer, int i);

    byte[] getId();

    int getLength();
}
