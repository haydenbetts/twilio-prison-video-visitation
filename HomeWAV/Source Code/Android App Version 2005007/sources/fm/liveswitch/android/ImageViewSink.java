package fm.liveswitch.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import fm.liveswitch.IAction0;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.LayoutScale;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.ViewSink;

public class ImageViewSink extends ViewSink<ImageView> {
    /* access modifiers changed from: private */
    public boolean _lastViewMirror;
    /* access modifiers changed from: private */
    public LayoutScale _lastViewScale;
    /* access modifiers changed from: private */
    public ImageView _view;
    /* access modifiers changed from: private */
    public boolean _viewMirror = false;
    /* access modifiers changed from: private */
    public LayoutScale _viewScale = LayoutScale.Contain;

    public String getLabel() {
        return "Android ImageView Sink";
    }

    public ImageView getView() {
        return this._view;
    }

    public LayoutScale getViewScale() {
        return this._viewScale;
    }

    public void setViewScale(LayoutScale layoutScale) {
        this._viewScale = layoutScale;
    }

    public boolean getViewMirror() {
        return this._viewMirror;
    }

    public void setViewMirror(boolean z) {
        this._viewMirror = z;
    }

    public ImageViewSink(Context context) {
        super(VideoFormat.getRgba());
        initialize(context);
    }

    public ImageViewSink(Context context, IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        initialize(context);
    }

    public ImageViewSink(Context context, IVideoOutput[] iVideoOutputArr) {
        super(iVideoOutputArr);
        initialize(context);
    }

    public ImageViewSink(ImageView imageView) {
        super(VideoFormat.getRgba());
        initialize(imageView);
    }

    public ImageViewSink(ImageView imageView, IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        initialize(imageView);
    }

    public ImageViewSink(ImageView imageView, IVideoOutput[] iVideoOutputArr) {
        super(iVideoOutputArr);
        initialize(imageView);
    }

    private void initialize(final Context context) {
        Utility.dispatchToMainThread(new IAction0() {
            public void invoke() {
                ImageViewSink.this.initialize(new ImageView(context));
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void initialize(ImageView imageView) {
        if (!((VideoFormat) getInputFormat()).getName().equals(VideoFormat.getRgbaName())) {
            throw new RuntimeException("Input format must be RGBA.");
        } else if (imageView != null) {
            this._view = imageView;
        } else {
            throw new RuntimeException("View cannot be null.");
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        super.doProcessFrame(videoFrame, videoBuffer);
        renderBuffer(videoBuffer);
    }

    public void renderBuffer(VideoBuffer videoBuffer) {
        renderBitmap(ImageUtility.bufferToBitmap(videoBuffer));
    }

    public void renderBitmap(final Bitmap bitmap) {
        if (bitmap != null) {
            Utility.dispatchToMainThread(new IAction0() {
                public void invoke() {
                    LayoutScale access$100 = ImageViewSink.this._viewScale;
                    if (access$100 != ImageViewSink.this._lastViewScale) {
                        if (access$100 == LayoutScale.Contain) {
                            ImageViewSink.this._view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        } else if (access$100 == LayoutScale.Cover) {
                            ImageViewSink.this._view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else if (access$100 == LayoutScale.Stretch) {
                            ImageViewSink.this._view.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        LayoutScale unused = ImageViewSink.this._lastViewScale = access$100;
                    }
                    boolean access$400 = ImageViewSink.this._viewMirror;
                    if (access$400 != ImageViewSink.this._lastViewMirror) {
                        if (access$400) {
                            ImageViewSink.this._view.setScaleX(-1.0f);
                        } else {
                            ImageViewSink.this._view.setScaleX(1.0f);
                        }
                        boolean unused2 = ImageViewSink.this._lastViewMirror = access$400;
                    }
                    ImageViewSink.this._view.setImageBitmap(bitmap);
                }
            });
        }
    }
}
