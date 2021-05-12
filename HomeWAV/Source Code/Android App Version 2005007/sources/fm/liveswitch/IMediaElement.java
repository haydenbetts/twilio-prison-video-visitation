package fm.liveswitch;

public interface IMediaElement extends IElement {
    boolean destroy();

    boolean getDeactivated();

    boolean getDisabled();

    String getLabel();

    boolean getMuted();

    boolean getPaused();

    boolean getPersistent();

    void setDeactivated(boolean z);

    void setMuted(boolean z);
}
