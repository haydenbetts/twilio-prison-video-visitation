package fm.liveswitch.android;

import android.content.Context;
import android.widget.FrameLayout;
import fm.liveswitch.IAction0;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.LayoutFrame;
import fm.liveswitch.LayoutScale;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import fm.liveswitch.ViewSink;

public class OpenGLSink extends ViewSink<FrameLayout> {
    /* access modifiers changed from: private */
    public OpenGLView _view;
    /* access modifiers changed from: private */
    public FrameLayout _viewContainer;
    int lastContainerHeight;
    int lastContainerWidth;
    int lastFrameHeight;
    int lastFrameWidth;

    public String getLabel() {
        return "Android OpenGL Sink";
    }

    public boolean getViewMirror() {
        return false;
    }

    public FrameLayout getView() {
        return this._viewContainer;
    }

    public LayoutScale getViewScale() {
        return this._view.getScale();
    }

    public void setViewScale(LayoutScale layoutScale) {
        if (layoutScale != this._view.getScale()) {
            throw new RuntimeException("View scale can not be changed.");
        }
    }

    public void setViewMirror(boolean z) {
        throw new RuntimeException("View mirroring is not currently supported.");
    }

    public OpenGLSink(Context context) {
        this(context, LayoutScale.Contain);
    }

    public OpenGLSink(Context context, IVideoOutput iVideoOutput) {
        this(context, LayoutScale.Contain, iVideoOutput);
    }

    public OpenGLSink(Context context, IVideoOutput[] iVideoOutputArr) {
        this(context, LayoutScale.Contain, iVideoOutputArr);
    }

    public OpenGLSink(Context context, LayoutScale layoutScale) {
        super(VideoFormat.getI420());
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(context, layoutScale);
    }

    public OpenGLSink(Context context, LayoutScale layoutScale, IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(context, layoutScale);
    }

    public OpenGLSink(Context context, LayoutScale layoutScale, IVideoOutput[] iVideoOutputArr) {
        super(iVideoOutputArr);
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(context, layoutScale);
    }

    public OpenGLSink(OpenGLView openGLView) {
        super(VideoFormat.getI420());
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(openGLView);
    }

    public OpenGLSink(OpenGLView openGLView, IVideoOutput iVideoOutput) {
        super(iVideoOutput);
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(openGLView);
    }

    public OpenGLSink(OpenGLView openGLView, IVideoOutput[] iVideoOutputArr) {
        super(iVideoOutputArr);
        this.lastContainerWidth = 0;
        this.lastContainerHeight = 0;
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        initialize(openGLView);
    }

    private void initialize(final Context context, final LayoutScale layoutScale) {
        Utility.dispatchToMainThread(new IAction0() {
            public void invoke() {
                OpenGLSink.this.initialize(new OpenGLView(context.getApplicationContext(), layoutScale));
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void initialize(final OpenGLView openGLView) {
        if (!((VideoFormat) getInputFormat()).getName().equals(VideoFormat.getI420Name())) {
            throw new RuntimeException("Input format must be I420.");
        } else if (openGLView != null) {
            Utility.dispatchToMainThread(new IAction0() {
                public void invoke() {
                    OpenGLView unused = OpenGLSink.this._view = openGLView;
                    FrameLayout unused2 = OpenGLSink.this._viewContainer = new FrameLayout(openGLView.getContext());
                    OpenGLSink.this._viewContainer.addView(OpenGLSink.this._view, OpenGLSink.this._view.getScale() == LayoutScale.Contain ? new FrameLayout.LayoutParams(0, 0) : new FrameLayout.LayoutParams(-1, -1));
                }
            }, true);
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
        LayoutScale viewScale = getViewScale();
        if (viewScale == LayoutScale.Contain) {
            int width = this._viewContainer.getWidth();
            int height = this._viewContainer.getHeight();
            int width2 = videoBuffer.getWidth();
            int height2 = videoBuffer.getHeight();
            if (width > 0 && height > 0 && width2 > 0 && height2 > 0 && !(width == this.lastContainerWidth && height == this.lastContainerHeight && width2 == this.lastFrameWidth && height2 == this.lastFrameHeight)) {
                this.lastContainerWidth = width;
                this.lastContainerHeight = height;
                this.lastFrameWidth = width2;
                this.lastFrameHeight = height2;
                LayoutFrame scaledFrame = LayoutFrame.getScaledFrame(viewScale, width, height, width2, height2);
                final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this._view.getLayoutParams();
                layoutParams.width = scaledFrame.getWidth();
                layoutParams.height = scaledFrame.getHeight();
                layoutParams.leftMargin = scaledFrame.getX();
                layoutParams.topMargin = scaledFrame.getY();
                Utility.dispatchToMainThread(new IAction0() {
                    public void invoke() {
                        OpenGLSink.this._view.setLayoutParams(layoutParams);
                    }
                }, true);
            }
        }
        this._view.render(videoBuffer);
    }
}
