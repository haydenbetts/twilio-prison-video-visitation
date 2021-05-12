package fm.liveswitch.yuv;

import fm.liveswitch.IVideoOutput;
import fm.liveswitch.ImageScalePipe;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public class ImageScaler extends ImageScalePipe {
    private int __filterMode;
    private Native __yuvFM;

    public String getLabel() {
        return "Image Scaler";
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        Native nativeR = this.__yuvFM;
        if (nativeR != null) {
            nativeR.destroy();
            this.__yuvFM = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        if (this.__yuvFM == null) {
            this.__yuvFM = new Native();
        }
        double scale = getScale();
        if (scale == -1.0d) {
            scale = 1.0d;
        }
        int i = (scale > 1.0d ? 1 : (scale == 1.0d ? 0 : -1));
        if (i == 0 && videoBuffer.getOrientation() == 0 && ((VideoFormat) super.getInputFormat()).isEquivalent(super.getOutputFormat())) {
            raiseFrame(videoFrame);
            return;
        }
        videoBuffer.keep();
        if (i != 0) {
            videoBuffer = doScale(videoBuffer, scale);
        }
        VideoBuffer planar = videoBuffer.toPlanar();
        if (planar != null) {
            videoFrame.addBuffer(planar);
            raiseFrame(videoFrame);
            planar.free();
        }
    }

    private VideoBuffer doScale(VideoBuffer videoBuffer, double d) {
        if (d == 1.0d) {
            return videoBuffer;
        }
        int width = (int) (((double) videoBuffer.getWidth()) * d);
        int height = (int) (((double) videoBuffer.getHeight()) * d);
        VideoBuffer planar = videoBuffer.toPlanar();
        VideoBuffer scale = this.__yuvFM.scale(planar, width - (width % 2), height - (height % 2), getFilterMode());
        scale.setOrientation(planar.getOrientation());
        planar.free();
        return scale;
    }

    public int getFilterMode() {
        return this.__filterMode;
    }

    public ImageScaler(double d) {
        super(VideoFormat.getI420(), VideoFormat.getI420());
        this.__filterMode = 0;
        setTargetScale(d);
    }

    public ImageScaler(double d, IVideoOutput iVideoOutput) {
        this(d);
        super.addInput(iVideoOutput);
    }

    public ImageScaler(double d, IVideoOutput[] iVideoOutputArr) {
        this(d);
        super.addInputs((TIOutput[]) iVideoOutputArr);
    }

    public void setFilterMode(int i) {
        this.__filterMode = i;
    }
}
