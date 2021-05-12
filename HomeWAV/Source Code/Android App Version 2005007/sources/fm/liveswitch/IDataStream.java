package fm.liveswitch;

public interface IDataStream<TDataChannel> extends IStream {
    TDataChannel[] getChannels();

    DataStreamInfo getInfo();
}
