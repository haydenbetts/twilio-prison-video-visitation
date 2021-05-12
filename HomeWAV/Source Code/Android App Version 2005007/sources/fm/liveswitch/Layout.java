package fm.liveswitch;

import java.util.ArrayList;

public class Layout {
    private LayoutFrame __localFrame = new LayoutFrame(0, 0, 0, 0);
    private LayoutFrame[] __remoteFrames = new LayoutFrame[0];
    private int _height;
    private LayoutOrigin _origin;
    private int _width;

    public LayoutFrame[] getAllFrames() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getLocalFrame());
        ArrayListExtensions.addRange(arrayList, (T[]) getRemoteFrames());
        return (LayoutFrame[]) arrayList.toArray(new LayoutFrame[0]);
    }

    public int getHeight() {
        return this._height;
    }

    public LayoutFrame getLocalFrame() {
        return this.__localFrame;
    }

    public LayoutOrigin getOrigin() {
        return this._origin;
    }

    public LayoutFrame[] getRemoteFrames() {
        return this.__remoteFrames;
    }

    public int getWidth() {
        return this._width;
    }

    public void setHeight(int i) {
        this._height = i;
    }

    public void setLocalFrame(LayoutFrame layoutFrame) {
        if (layoutFrame == null) {
            this.__localFrame = new LayoutFrame(0, 0, 0, 0);
        } else {
            this.__localFrame = layoutFrame;
        }
    }

    public void setOrigin(LayoutOrigin layoutOrigin) {
        this._origin = layoutOrigin;
    }

    public void setRemoteFrames(LayoutFrame[] layoutFrameArr) {
        if (layoutFrameArr == null) {
            this.__remoteFrames = new LayoutFrame[0];
        } else {
            this.__remoteFrames = layoutFrameArr;
        }
    }

    public void setWidth(int i) {
        this._width = i;
    }

    public void swapFrames(LayoutFrame layoutFrame, LayoutFrame layoutFrame2) {
        int x = layoutFrame.getX();
        int y = layoutFrame.getY();
        int width = layoutFrame.getWidth();
        int height = layoutFrame.getHeight();
        layoutFrame.setX(layoutFrame2.getX());
        layoutFrame.setY(layoutFrame2.getY());
        layoutFrame.setWidth(layoutFrame2.getWidth());
        layoutFrame.setHeight(layoutFrame2.getHeight());
        layoutFrame2.setX(x);
        layoutFrame2.setY(y);
        layoutFrame2.setWidth(width);
        layoutFrame2.setHeight(height);
    }

    public void swapLocalFrame(int i) {
        LayoutFrame localFrame = getLocalFrame();
        LayoutFrame[] remoteFrames = getRemoteFrames();
        if (localFrame != null && i < ArrayExtensions.getLength((Object[]) remoteFrames)) {
            swapFrames(localFrame, remoteFrames[i]);
        }
    }

    public void swapRemoteFrames(int i, int i2) {
        LayoutFrame[] remoteFrames = getRemoteFrames();
        if (i < ArrayExtensions.getLength((Object[]) remoteFrames) && i2 < ArrayExtensions.getLength((Object[]) remoteFrames)) {
            swapFrames(remoteFrames[i], remoteFrames[i2]);
        }
    }
}
