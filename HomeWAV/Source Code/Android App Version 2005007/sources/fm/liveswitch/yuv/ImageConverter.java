package fm.liveswitch.yuv;

import fm.liveswitch.IVideoOutput;
import fm.liveswitch.ImageScalePipe;
import fm.liveswitch.StringComparison;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;

public class ImageConverter extends ImageScalePipe {
    private int __alignment;
    private int __filterMode;
    private Native __yuvFM;

    public String getLabel() {
        return "Image Converter";
    }

    private VideoBuffer doConvertToI420(VideoBuffer videoBuffer, int i) {
        if (videoBuffer.getIsI420()) {
            return videoBuffer;
        }
        int orientation = videoBuffer.getOrientation();
        videoBuffer.setOrientation(0);
        VideoBuffer convertToI420 = this.__yuvFM.convertToI420(videoBuffer, i);
        convertToI420.setOrientation(orientation);
        videoBuffer.free();
        return convertToI420;
    }

    private VideoBuffer doConvertToOutputFormat(VideoBuffer videoBuffer) {
        if (StringExtensions.isEqual(((VideoFormat) videoBuffer.getFormat()).getName(), ((VideoFormat) super.getOutputFormat()).getName(), StringComparison.OrdinalIgnoreCase)) {
            return videoBuffer;
        }
        VideoBuffer convertFromI420 = this.__yuvFM.convertFromI420(videoBuffer.toPlanar(), (VideoFormat) super.getOutputFormat());
        convertFromI420.setOrientation(videoBuffer.getOrientation());
        videoBuffer.free();
        return convertFromI420;
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
        VideoBuffer doConvertToI420 = doConvertToI420(videoBuffer, getAlignment());
        if (scale < 1.0d) {
            doConvertToI420 = doScale(doConvertToI420, scale);
        }
        VideoBuffer doRotate = doRotate(doConvertToI420);
        if (i > 0) {
            doRotate = doScale(doRotate, scale);
        }
        VideoBuffer planar = doConvertToOutputFormat(doRotate).toPlanar();
        if (planar != null) {
            videoFrame.addBuffer(planar);
            raiseFrame(videoFrame);
            planar.free();
        }
    }

    private VideoBuffer doRotate(VideoBuffer videoBuffer) {
        if (videoBuffer.getOrientation() == 0) {
            return videoBuffer;
        }
        VideoBuffer planar = videoBuffer.toPlanar();
        VideoBuffer rotate = this.__yuvFM.rotate(planar);
        planar.free();
        return rotate;
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

    public int getAlignment() {
        return this.__alignment;
    }

    public int getFilterMode() {
        return this.__filterMode;
    }

    public ImageConverter(IVideoOutput iVideoOutput, VideoFormat videoFormat) {
        this((VideoFormat) iVideoOutput.getOutputFormat(), videoFormat);
        super.addInput(iVideoOutput);
    }

    public ImageConverter(VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
        this.__filterMode = 0;
        this.__alignment = 16;
        setTargetScale(1.0d);
    }

    public ImageConverter(IVideoOutput[] iVideoOutputArr, VideoFormat videoFormat) {
        this((VideoFormat) iVideoOutputArr[0].getOutputFormat(), videoFormat);
        super.addInputs((TIOutput[]) iVideoOutputArr);
    }

    public ImageConverter(VideoFormat videoFormat) {
        super(videoFormat);
        this.__filterMode = 0;
        this.__alignment = 16;
        setTargetScale(1.0d);
    }

    public void setAlignment(int i) {
        this.__alignment = i;
    }

    public void setFilterMode(int i) {
        this.__filterMode = i;
    }
}
