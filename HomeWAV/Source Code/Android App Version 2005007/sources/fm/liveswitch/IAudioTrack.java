package fm.liveswitch;

public interface IAudioTrack extends IMediaTrack {
    void addOnLevel(IAction1<Double> iAction1);

    double getGain();

    double getVolume();

    void removeOnLevel(IAction1<Double> iAction1);

    void setGain(double d);

    void setVolume(double d);
}
