package fm.liveswitch;

public interface IViewSink<TView> {
    TView getView();

    boolean getViewMirror();

    LayoutScale getViewScale();

    void setViewMirror(boolean z);

    void setViewScale(LayoutScale layoutScale);
}
