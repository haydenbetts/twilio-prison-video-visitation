package fm.liveswitch;

public class DataChannel extends DataChannelBase<DataChannel> {
    private ReliableChannel _innerDataChannel;

    /* access modifiers changed from: protected */
    public DataChannel getInstance() {
        return this;
    }

    private void attachInnerEventListeners() {
        getInnerDataChannel().removeOnReceiveBinary(new IActionDelegate1<DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataBytes";
            }

            public void invoke(DataBuffer dataBuffer) {
                DataChannel.this.raiseDataBytes(dataBuffer);
            }
        });
        getInnerDataChannel().addOnReceiveBinary(new IActionDelegate1<DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataBytes";
            }

            public void invoke(DataBuffer dataBuffer) {
                DataChannel.this.raiseDataBytes(dataBuffer);
            }
        });
        getInnerDataChannel().removeOnReceiveString(new IActionDelegate1<String>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataString";
            }

            public void invoke(String str) {
                DataChannel.this.raiseDataString(str);
            }
        });
        getInnerDataChannel().addOnReceiveString(new IActionDelegate1<String>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataString";
            }

            public void invoke(String str) {
                DataChannel.this.raiseDataString(str);
            }
        });
        getInnerDataChannel().removeOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.DataChannel.processInnerChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                DataChannel.this.processInnerChannelStateChange(reliableChannel);
            }
        });
        getInnerDataChannel().addOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.DataChannel.processInnerChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                DataChannel.this.processInnerChannelStateChange(reliableChannel);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void clean() {
        super.setState(DataChannelState.Closed);
        getInnerDataChannel().removeOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.DataChannel.processInnerChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                DataChannel.this.processInnerChannelStateChange(reliableChannel);
            }
        });
        getInnerDataChannel().removeOnReceiveBinary(new IActionDelegate1<DataBuffer>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataBytes";
            }

            public void invoke(DataBuffer dataBuffer) {
                DataChannel.this.raiseDataBytes(dataBuffer);
            }
        });
        getInnerDataChannel().removeOnReceiveString(new IActionDelegate1<String>() {
            public String getId() {
                return "fm.liveswitch.DataChannelBase<fm.liveswitch.DataChannel>.raiseDataString";
            }

            public void invoke(String str) {
                DataChannel.this.raiseDataString(str);
            }
        });
        setInnerDataChannel((ReliableChannel) null);
    }

    DataChannel(ReliableChannel reliableChannel, Object obj) {
        this(reliableChannel.getLabel(), reliableChannel.getOrdered(), reliableChannel.getSubProtocol());
        setInnerDataChannel(reliableChannel);
        super.setStateLock(obj);
    }

    public DataChannel(String str) {
        this(str, true);
    }

    public DataChannel(String str, boolean z) {
        this(str, z, StringExtensions.empty);
    }

    public DataChannel(String str, boolean z, String str2) {
        super(str, z, str2);
    }

    /* access modifiers changed from: package-private */
    public ReliableChannel getInnerDataChannel() {
        return this._innerDataChannel;
    }

    /* access modifiers changed from: package-private */
    public DataChannelStats getStats() {
        DataChannelStats dataChannelStats = new DataChannelStats();
        dataChannelStats.setId(super.getId());
        dataChannelStats.setTimestamp(DateExtensions.getUtcNow());
        dataChannelStats.setLabel(super.getLabel());
        dataChannelStats.setOrdered(super.getOrdered());
        dataChannelStats.setProtocol(super.getSubprotocol());
        dataChannelStats.setState(super.getState());
        dataChannelStats.setMessagesSent(super.getMessagesSent());
        dataChannelStats.setMessagesReceived(super.getMessagesReceived());
        dataChannelStats.setBytesSent(super.getBytesSent());
        dataChannelStats.setBytesReceived(super.getBytesReceived());
        return dataChannelStats;
    }

    /* access modifiers changed from: private */
    public void processInnerChannelStateChange(ReliableChannel reliableChannel) {
        ReliableChannelState state = reliableChannel.getState();
        if (state == ReliableChannelState.Opening) {
            super.setState(DataChannelState.Connecting);
        } else if (state == ReliableChannelState.Open) {
            super.setState(DataChannelState.Connected);
        } else if (state == ReliableChannelState.Closing) {
            super.setState(DataChannelState.Closing);
        } else if (state == ReliableChannelState.Closed) {
            super.setState(DataChannelState.Closed);
        } else if (state == ReliableChannelState.Failed) {
            super.setState(DataChannelState.Failed);
        }
    }

    /* access modifiers changed from: protected */
    public void processStateLockChange() {
        super.processStateLockChange();
        if (getInnerDataChannel() == null) {
            setInnerDataChannel(new ReliableChannel(super.getLabel(), super.getOrdered(), super.getSubprotocol(), super.getStateLock()));
        }
        attachInnerEventListeners();
    }

    /* access modifiers changed from: package-private */
    public void promisedSendDataBytes(final DataBuffer dataBuffer, final int i, final Promise<Object> promise) {
        ReliableChannel innerDataChannel = getInnerDataChannel();
        if (innerDataChannel != null) {
            innerDataChannel.sendBytes(dataBuffer, new IAction0() {
                public void invoke() {
                    DataChannel.this.registerDataSent(i);
                    promise.resolve(dataBuffer);
                }
            }, new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void promisedSendDataString(final String str, final int i, final Promise<Object> promise) {
        ReliableChannel innerDataChannel = getInnerDataChannel();
        if (innerDataChannel != null) {
            innerDataChannel.sendString(str, new IAction0() {
                public void invoke() {
                    DataChannel.this.registerDataSent(i);
                    promise.resolve(str);
                }
            }, new IAction1<Exception>() {
                public void invoke(Exception exc) {
                    promise.reject(exc);
                }
            });
        }
    }

    public Future<Object> sendDataBytes(DataBuffer dataBuffer) {
        Promise promise = new Promise();
        super.prepareAndSendMessage(dataBuffer, (Promise<Object>) promise);
        return promise;
    }

    /* access modifiers changed from: package-private */
    public Future<Object> sendDataMessage(DataMessage dataMessage) {
        int i;
        Promise promise = new Promise();
        DataBuffer bytes = dataMessage.getBytes();
        if (dataMessage.getDataBytes() != null) {
            i = dataMessage.getDataBytes().getLength();
        } else {
            i = ArrayExtensions.getLength(Utf8.encode(dataMessage.getDataString()));
        }
        promisedSendDataBytes(bytes, i, promise);
        return promise;
    }

    public Future<Object> sendDataString(String str) {
        Promise promise = new Promise();
        super.prepareAndSendMessage(str, (Promise<Object>) promise);
        return promise;
    }

    /* access modifiers changed from: package-private */
    public void setInnerDataChannel(ReliableChannel reliableChannel) {
        this._innerDataChannel = reliableChannel;
    }
}
