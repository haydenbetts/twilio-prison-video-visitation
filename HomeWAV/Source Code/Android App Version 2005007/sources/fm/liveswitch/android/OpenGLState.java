package fm.liveswitch.android;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.VideoBuffer;
import java.nio.ByteBuffer;

public class OpenGLState {
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(OpenGLState.class);
    public int height;
    public DataBuffer u = _dataBufferPool.take(this.uLength);
    public ByteBuffer uBuffer = ByteBuffer.wrap(this.u.getData());
    private int uLength;
    public DataBuffer v = _dataBufferPool.take(this.vLength);
    public ByteBuffer vBuffer = ByteBuffer.wrap(this.v.getData());
    private int vLength;
    public int width;
    public DataBuffer y;
    public ByteBuffer yBuffer = ByteBuffer.wrap(this.y.getData());
    private int yLength;

    public OpenGLState(VideoBuffer videoBuffer) {
        this.width = videoBuffer.getWidth();
        int height2 = videoBuffer.getHeight();
        this.height = height2;
        int i = this.width * height2;
        this.yLength = i;
        int i2 = i / 4;
        this.vLength = i2;
        this.uLength = i2;
        this.y = _dataBufferPool.take(i);
        int i3 = videoBuffer.getStrides()[0];
        int i4 = videoBuffer.getStrides()[1];
        int i5 = videoBuffer.getStrides()[2];
        DataBuffer dataBuffer = videoBuffer.getDataBuffers()[0];
        DataBuffer dataBuffer2 = videoBuffer.getDataBuffers()[1];
        DataBuffer dataBuffer3 = videoBuffer.getDataBuffers()[2];
        for (int i6 = 0; i6 < this.height; i6++) {
            this.yBuffer.put(dataBuffer.getData(), dataBuffer.getIndex() + (i6 * i3), this.width);
            if (i6 < this.height / 2) {
                this.uBuffer.put(dataBuffer2.getData(), dataBuffer2.getIndex() + (i6 * i4), this.width / 2);
                this.vBuffer.put(dataBuffer3.getData(), dataBuffer3.getIndex() + (i6 * i5), this.width / 2);
            }
        }
        this.yBuffer.position(0);
        this.uBuffer.position(0);
        this.vBuffer.position(0);
    }

    public void free() {
        this.y.free();
        this.u.free();
        this.v.free();
    }
}
