package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataStream extends DataStreamBase<DataChannel> {
    private DataChannelCollection __channels;
    private DataStreamMediaDescriptionManager __dataStreamMediaDescriptionManager;
    private long __maxMessageSize;
    /* access modifiers changed from: private */
    public List<IAction1<DataChannel>> __onChannel;
    private ReliableTransport __reliableDataTransport;
    private IAction1<DataChannel> _onChannel;
    private SctpTransport _sctpTransport;

    public boolean getUseSdes() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void addOnChannel(IAction1<DataChannel> iAction1) {
        if (iAction1 != null) {
            if (this._onChannel == null) {
                this._onChannel = new IAction1<DataChannel>() {
                    public void invoke(DataChannel dataChannel) {
                        Iterator it = new ArrayList(DataStream.this.__onChannel).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dataChannel);
                        }
                    }
                };
            }
            this.__onChannel.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        super.close();
        setSctpTransport((SctpTransport) null);
        setReliableDataTransport((ReliableTransport) null);
    }

    /* access modifiers changed from: package-private */
    public MediaDescription createSdpMediaDescription(Message message, boolean z, boolean z2, boolean z3, BundlePolicy bundlePolicy) {
        DataStreamMediaDescriptionRequirements dataStreamMediaDescriptionRequirements = new DataStreamMediaDescriptionRequirements();
        dataStreamMediaDescriptionRequirements.setSctpPort(getSctpPort());
        dataStreamMediaDescriptionRequirements.setMaxMessageSize(getMaxMessageSize());
        super.populateProperties(dataStreamMediaDescriptionRequirements);
        return this.__dataStreamMediaDescriptionManager.createSdpMediaDescription(dataStreamMediaDescriptionRequirements, message, z2, z3);
    }

    DataStream() {
        this.__onChannel = new ArrayList();
        this._onChannel = null;
        this.__maxMessageSize = 2147483647L;
        this.__channels = new DataChannelCollection();
        this.__dataStreamMediaDescriptionManager = new DataStreamMediaDescriptionManager();
    }

    public DataStream(DataChannel dataChannel) {
        this(new DataChannel[]{dataChannel});
    }

    public DataStream(DataChannel[] dataChannelArr) {
        this.__onChannel = new ArrayList();
        this._onChannel = null;
        this.__maxMessageSize = 2147483647L;
        this.__channels = new DataChannelCollection();
        this.__dataStreamMediaDescriptionManager = new DataStreamMediaDescriptionManager();
        int length = dataChannelArr.length;
        int i = 0;
        while (i < length) {
            DataChannel dataChannel = dataChannelArr[i];
            if (dataChannel.getStreamId() == null) {
                super.attachToChannel(dataChannel);
                i++;
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Data channel ({0}) is already associated with another data stream.", (Object) dataChannel.getLabel())));
            }
        }
        this.__channels.addMany(dataChannelArr);
    }

    public DataChannel[] getChannels() {
        return (DataChannel[]) this.__channels.getValues();
    }

    public long getMaxMessageSize() {
        return this.__maxMessageSize;
    }

    /* access modifiers changed from: package-private */
    public MediaDescriptionManagerBase getMediaDescriptionManager() {
        return this.__dataStreamMediaDescriptionManager;
    }

    /* access modifiers changed from: package-private */
    public ReliableTransport getReliableDataTransport() {
        return this.__reliableDataTransport;
    }

    public int getSctpPort() {
        if (getSctpTransport() != null) {
            return getSctpTransport().getPort();
        }
        return 5000;
    }

    /* access modifiers changed from: package-private */
    public SctpTransport getSctpTransport() {
        return this._sctpTransport;
    }

    /* access modifiers changed from: package-private */
    public DataStreamStats getStats() {
        ArrayList arrayList = new ArrayList();
        for (DataChannel stats : getChannels()) {
            arrayList.add(stats.getStats());
        }
        DataStreamStats dataStreamStats = new DataStreamStats();
        dataStreamStats.setId(super.getId());
        dataStreamStats.setTimestamp(DateExtensions.getUtcNow());
        dataStreamStats.setType(super.getType());
        dataStreamStats.setChannels((DataChannelStats[]) arrayList.toArray(new DataChannelStats[0]));
        dataStreamStats.setMessagesSent(super.getMessagesSent());
        dataStreamStats.setMessagesReceived(super.getMessagesReceived());
        dataStreamStats.setBytesSent(super.getBytesSent());
        dataStreamStats.setBytesReceived(super.getBytesReceived());
        CoreTransport coreTransportRtp = super.getCoreTransportRtp();
        if (coreTransportRtp != null) {
            dataStreamStats.setTransport(coreTransportRtp.getStats());
        }
        return dataStreamStats;
    }

    public TransportInfo getTransportInfo() {
        if (super.getCoreTransportRtp() == null) {
            return null;
        }
        return super.getCoreTransportRtp().getInfo();
    }

    /* access modifiers changed from: protected */
    public void processBundledStateChanged(boolean z) {
        super.processBundledStateChanged(z);
        SctpTransport sctpTransport = getSctpTransport();
        if (sctpTransport != null) {
            sctpTransport.switchToAlternativeInternalTransport();
        }
    }

    /* access modifiers changed from: protected */
    public void processCachedSettings() {
        super.processCachedSettings();
    }

    /* access modifiers changed from: package-private */
    public Error processSdpMediaDescription(Message message, MediaDescription mediaDescription, int i, boolean z, boolean z2, boolean z3) {
        DataStreamMediaDescriptionRequirements dataStreamMediaDescriptionRequirements = new DataStreamMediaDescriptionRequirements();
        dataStreamMediaDescriptionRequirements.setSctpPort(getSctpPort());
        dataStreamMediaDescriptionRequirements.setMaxMessageSize(getMaxMessageSize());
        super.populateProperties(dataStreamMediaDescriptionRequirements);
        Error processSdpMediaDescription = this.__dataStreamMediaDescriptionManager.processSdpMediaDescription(dataStreamMediaDescriptionRequirements, message, i, z, z3, z2);
        if (processSdpMediaDescription != null) {
            return processSdpMediaDescription;
        }
        setSctpPort(this.__dataStreamMediaDescriptionManager.getSctpPort());
        long maxMessageSize = this.__dataStreamMediaDescriptionManager.getMaxMessageSize();
        SctpTransport sctpTransport = getSctpTransport();
        if ((maxMessageSize == ((long) SctpTransport.getUnset()) || maxMessageSize > 0) && sctpTransport != null) {
            sctpTransport.setMaxMessageSize(maxMessageSize);
        }
        super.updateProperties(z3, z2, z);
        return null;
    }

    /* access modifiers changed from: private */
    public void raiseNewDataChannel(ReliableChannel reliableChannel) {
        DataChannel dataChannel = new DataChannel(reliableChannel, super.getStateLock());
        synchronized (super.getStateLock()) {
            this.__channels.add(dataChannel);
            super.attachToChannel(dataChannel);
            dataChannel.setConnectionId(super.getConnectionId());
            dataChannel.setGetRemoteConnectionInfo(super.getGetRemoteConnectionInfo());
        }
        IAction1<DataChannel> iAction1 = this._onChannel;
        if (iAction1 != null) {
            iAction1.invoke(dataChannel);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnChannel(IAction1<DataChannel> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onChannel, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onChannel.remove(iAction1);
        if (this.__onChannel.size() == 0) {
            this._onChannel = null;
        }
    }

    public void setMaxMessageSize(long j) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i >= 0 || j == ((long) SctpTransport.getUnset())) {
            if (i == 0) {
                Log.warn("Setting maximum message size of 0 bytes for Data Streams will signal rejection of SDPs containing data streams.");
            }
            this.__maxMessageSize = j;
            return;
        }
        throw new RuntimeException(new Exception("Negative values for maximum message size on data streams are invalid."));
    }

    /* access modifiers changed from: package-private */
    public void setReliableDataTransport(ReliableTransport reliableTransport) {
        if (reliableTransport != null) {
            this.__reliableDataTransport = reliableTransport;
            reliableTransport.addOnDataChannel(new IActionDelegate1<ReliableChannel>() {
                public String getId() {
                    return "fm.liveswitch.DataStream.raiseNewDataChannel";
                }

                public void invoke(ReliableChannel reliableChannel) {
                    DataStream.this.raiseNewDataChannel(reliableChannel);
                }
            });
            return;
        }
        ReliableTransport reliableTransport2 = this.__reliableDataTransport;
        if (reliableTransport2 != null) {
            reliableTransport2.removeOnDataChannel(new IActionDelegate1<ReliableChannel>() {
                public String getId() {
                    return "fm.liveswitch.DataStream.raiseNewDataChannel";
                }

                public void invoke(ReliableChannel reliableChannel) {
                    DataStream.this.raiseNewDataChannel(reliableChannel);
                }
            });
            this.__reliableDataTransport = null;
        }
    }

    public void setSctpPort(int i) {
        if (getSctpTransport() != null) {
            getSctpTransport().setPort(i);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Attempting to set SCTP port, but the SCTP Transport for Data Stream {0} is not set.", (Object) super.getId())));
    }

    /* access modifiers changed from: package-private */
    public void setSctpTransport(SctpTransport sctpTransport) {
        this._sctpTransport = sctpTransport;
    }

    public void setUseSdes(boolean z) {
        if (z) {
            throw new RuntimeException(new Exception("SDES encryption is not supported on Data Streams."));
        }
    }
}
