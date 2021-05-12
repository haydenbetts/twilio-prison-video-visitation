package fm.liveswitch;

public interface IDataBufferPool {
    DataBuffer take(int i);

    DataBuffer take(int i, boolean z);

    DataBuffer take(int i, boolean z, boolean z2);
}
