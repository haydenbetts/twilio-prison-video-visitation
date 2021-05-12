package fm.liveswitch;

public interface IViewableMedia<TView> {
    String getId();

    TView getView();
}
