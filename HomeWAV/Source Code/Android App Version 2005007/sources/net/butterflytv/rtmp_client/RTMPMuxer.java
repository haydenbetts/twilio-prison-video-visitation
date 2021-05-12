package net.butterflytv.rtmp_client;

public class RTMPMuxer {
    public native int close();

    public native void file_close();

    public native void file_open(String str);

    public native int isConnected();

    public native int open(String str, int i, int i2);

    public native int read(byte[] bArr, int i, int i2);

    public native int writeAudio(byte[] bArr, int i, int i2, int i3);

    public native int writeVideo(byte[] bArr, int i, int i2, int i3);

    public native void write_flv_header(boolean z, boolean z2);

    static {
        System.loadLibrary("rtmp-jni");
    }
}
