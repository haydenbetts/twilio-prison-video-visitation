package fm.liveswitch;

import fm.liveswitch.IViewSink;

public interface IViewSinkableMedia<TView, TViewSink extends IViewSink<TView>> extends IViewableMedia<TView> {
    TViewSink getViewSink();
}
