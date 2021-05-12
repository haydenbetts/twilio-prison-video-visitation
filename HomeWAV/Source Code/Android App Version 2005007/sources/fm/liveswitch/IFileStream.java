package fm.liveswitch;

public interface IFileStream {
    void close();

    boolean exists();

    boolean flush();

    long getLength();

    String getPath();

    long getPosition();

    void open(FileStreamAccess fileStreamAccess);

    int read(byte[] bArr, int i, int i2);

    void setPosition(long j);

    boolean write(byte[] bArr, int i, int i2);

    boolean writeTo(int i, byte[] bArr, int i2, int i3);
}
