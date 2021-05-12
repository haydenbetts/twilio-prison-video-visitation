package fm.liveswitch;

import fm.liveswitch.DataChannelBase;
import java.util.ArrayList;

public abstract class DataStreamBase<TDataChannel extends DataChannelBase<TDataChannel>> extends Stream implements IDataStream<TDataChannel>, IStream {
    private AtomicLong __bytesReceived = new AtomicLong();
    private AtomicLong __bytesSent = new AtomicLong();
    private IFunction1<String, Object> __getRemoteConnectionInfo;
    private AtomicLong __messagesReceived = new AtomicLong();
    private AtomicLong __messagesSent = new AtomicLong();
    private String __obsoleteCanonicalName;

    public abstract TDataChannel[] getChannels();

    public String getLabel() {
        return "Data Stream";
    }

    /* access modifiers changed from: protected */
    public void attachToChannel(TDataChannel tdatachannel) {
        if (tdatachannel.getStreamId() == null) {
            tdatachannel.setStreamId(super.getId());
            tdatachannel.removeOnDataSent(new IActionDelegate1<Integer>() {
                public String getId() {
                    return "fm.liveswitch.DataStreamBase<TDataChannel>.registerDataSent";
                }

                public void invoke(Integer num) {
                    DataStreamBase.this.registerDataSent(num.intValue());
                }
            });
            tdatachannel.addOnDataSent(new IActionDelegate1<Integer>() {
                public String getId() {
                    return "fm.liveswitch.DataStreamBase<TDataChannel>.registerDataSent";
                }

                public void invoke(Integer num) {
                    DataStreamBase.this.registerDataSent(num.intValue());
                }
            });
            tdatachannel.removeOnDataReceived(new IActionDelegate1<Integer>() {
                public String getId() {
                    return "fm.liveswitch.DataStreamBase<TDataChannel>.registerDataReceived";
                }

                public void invoke(Integer num) {
                    DataStreamBase.this.registerDataReceived(num.intValue());
                }
            });
            tdatachannel.addOnDataReceived(new IActionDelegate1<Integer>() {
                public String getId() {
                    return "fm.liveswitch.DataStreamBase<TDataChannel>.registerDataReceived";
                }

                public void invoke(Integer num) {
                    DataStreamBase.this.registerDataReceived(num.intValue());
                }
            });
        }
    }

    public Error changeDirection(StreamDirection streamDirection) {
        return new Error(ErrorCode.DataStreamDirectionCannotBeChanged, new Exception("Changes to Stream Direction of Data Streams are not permitted."));
    }

    public DataStreamBase() {
        super(StreamType.Application);
    }

    public long getBytesReceived() {
        return this.__bytesReceived.getValue();
    }

    public long getBytesSent() {
        return this.__bytesSent.getValue();
    }

    public String getCanonicalName() {
        Log.warn("Getting the value of DataStream.CanonicalName is deprecated and will be removed in a future release.");
        return this.__obsoleteCanonicalName;
    }

    public StreamDirection getDirection() {
        return StreamDirection.SendReceive;
    }

    /* access modifiers changed from: package-private */
    public StreamDirection getDirectionCapabilities() {
        return StreamDirection.SendReceive;
    }

    /* access modifiers changed from: package-private */
    public IFunction1<String, Object> getGetRemoteConnectionInfo() {
        return this.__getRemoteConnectionInfo;
    }

    public DataStreamInfo getInfo() {
        ArrayList arrayList = new ArrayList();
        for (DataChannelBase info : getChannels()) {
            arrayList.add(info.getInfo());
        }
        DataStreamInfo dataStreamInfo = new DataStreamInfo();
        dataStreamInfo.setId(super.getId());
        dataStreamInfo.setTag(super.getTag());
        dataStreamInfo.setChannels((DataChannelInfo[]) arrayList.toArray(new DataChannelInfo[0]));
        DataStreamReport dataStreamReport = new DataStreamReport();
        dataStreamReport.setBytesReceived(new NullableLong(getBytesReceived()));
        dataStreamReport.setBytesSent(new NullableLong(getBytesSent()));
        dataStreamReport.setMessagesReceived(new NullableLong(getMessagesReceived()));
        dataStreamReport.setMessagesSent(new NullableLong(getMessagesSent()));
        dataStreamInfo.setReport(dataStreamReport);
        return dataStreamInfo;
    }

    public StreamDirection getLocalDirection() {
        return StreamDirection.SendReceive;
    }

    public long getMessagesReceived() {
        return this.__messagesReceived.getValue();
    }

    public long getMessagesSent() {
        return this.__messagesSent.getValue();
    }

    public StreamDirection getRemoteDirection() {
        return StreamDirection.SendReceive;
    }

    /* access modifiers changed from: protected */
    public void processStateChange() {
        super.processStateChange();
        DataChannelBase[] channels = getChannels();
        if (channels != null) {
            for (DataChannelBase dataChannelBase : channels) {
                if (Global.equals(super.getState(), StreamState.Closing)) {
                    dataChannelBase.setState(DataChannelState.Closing);
                } else if (Global.equals(super.getState(), StreamState.Closed)) {
                    dataChannelBase.setState(DataChannelState.Closed);
                } else if (Global.equals(super.getState(), StreamState.Failed)) {
                    dataChannelBase.setState(DataChannelState.Failed);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processStateLockChange() {
        super.processStateLockChange();
        DataChannelBase[] channels = getChannels();
        if (channels != null) {
            for (DataChannelBase dataChannelBase : channels) {
                attachToChannel(dataChannelBase);
                dataChannelBase.setConnectionId(super.getConnectionId());
                dataChannelBase.setStateLock(super.getStateLock());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerDataReceived(int i) {
        this.__messagesReceived.increment();
        this.__bytesReceived.add((long) i);
    }

    /* access modifiers changed from: protected */
    public void registerDataSent(int i) {
        this.__messagesSent.increment();
        this.__bytesSent.add((long) i);
    }

    /* access modifiers changed from: package-private */
    public void setCanonicalName(String str) {
        this.__obsoleteCanonicalName = str;
    }

    /* access modifiers changed from: package-private */
    public void setGetRemoteConnectionInfo(IFunction1<String, Object> iFunction1) {
        synchronized (super.getStateLock()) {
            if (!Global.equals(this.__getRemoteConnectionInfo, iFunction1)) {
                this.__getRemoteConnectionInfo = iFunction1;
                for (DataChannelBase getRemoteConnectionInfo : getChannels()) {
                    getRemoteConnectionInfo.setGetRemoteConnectionInfo(iFunction1);
                }
            }
        }
    }

    public void setLocalDirection(StreamDirection streamDirection) {
        if (!Global.equals(streamDirection, StreamDirection.SendReceive)) {
            throw new RuntimeException(new Exception("Local direction other than SendReceive for DataStreams is not currently supported."));
        }
    }

    /* access modifiers changed from: package-private */
    public void setRemoteDirection(StreamDirection streamDirection) {
        if (!Global.equals(streamDirection, StreamDirection.SendReceive)) {
            throw new RuntimeException(new Exception("Remote direction other than SendReceive for DataStreams is not currently supported."));
        }
    }
}
