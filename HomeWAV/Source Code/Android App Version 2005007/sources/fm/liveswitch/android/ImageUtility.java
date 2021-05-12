package fm.liveswitch.android;

import android.graphics.Bitmap;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import java.nio.ByteBuffer;

public class ImageUtility {
    public static VideoBuffer bitmapToBuffer(Bitmap bitmap) throws Exception {
        if (bitmap.getConfig().equals(Bitmap.Config.ARGB_8888)) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * height);
            bitmap.copyPixelsToBuffer(allocate);
            allocate.rewind();
            int remaining = allocate.remaining();
            byte[] bArr = new byte[remaining];
            allocate.get(bArr);
            return new VideoBuffer(width, height, DataBuffer.wrap(bArr, 0, remaining), VideoFormat.getArgb());
        }
        throw new RuntimeException("Bitmap config must be ARGB_8888.");
    }

    public static Bitmap bufferToBitmap(VideoBuffer videoBuffer) {
        if (((VideoFormat) videoBuffer.getFormat()).getName().equals(VideoFormat.getRgbaName())) {
            int width = videoBuffer.getWidth();
            int height = videoBuffer.getHeight();
            DataBuffer dataBuffer = videoBuffer.getDataBuffer();
            int stride = videoBuffer.getStride();
            byte[] data = dataBuffer.getData();
            int index = dataBuffer.getIndex();
            int length = dataBuffer.getLength();
            ByteBuffer allocate = ByteBuffer.allocate(width * 4 * height);
            allocate.put(data, index, length);
            allocate.rewind();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(allocate);
            return createBitmap;
        }
        throw new RuntimeException("Video buffer format must be RGBA.");
    }
}
