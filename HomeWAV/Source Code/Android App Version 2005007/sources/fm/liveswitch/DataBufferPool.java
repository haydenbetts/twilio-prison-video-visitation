package fm.liveswitch;

import java.util.Collection;

public class DataBufferPool implements IDataBufferPool {
    private static DataBufferPool __singleton;
    private static Object __singletonLock = new Object();
    private int __blockSize;
    private ManagedConcurrentDictionary<Integer, ManagedConcurrentStack<byte[]>> __pools;
    private ManagedConcurrentDictionary<String, PoolStatistics> __traceStatistics;
    private boolean _disabled;
    private boolean _enableStatistics;
    private ILog _log;
    private PoolStatistics _statistics;

    public static boolean getIsSupported() {
        return true;
    }

    /* access modifiers changed from: private */
    public ManagedConcurrentStack<byte[]> createStack(PoolStatistics poolStatistics) {
        if (getEnableStatistics()) {
            getStatistics().__creates.increment();
            if (poolStatistics != null) {
                poolStatistics.__creates.increment();
            }
        }
        return new ManagedConcurrentStack<>();
    }

    /* access modifiers changed from: private */
    public PoolStatistics createTraceStatistics(String str) {
        return new PoolStatistics(str);
    }

    public DataBufferPool() {
        this(ClassExtensions.getFullName(DataBufferPool.class));
    }

    public DataBufferPool(String str) {
        this._log = Log.getLogger("DataBufferPool", LogLevel.Info);
        this.__blockSize = 128;
        this.__traceStatistics = new ManagedConcurrentDictionary<>();
        this.__pools = new ManagedConcurrentDictionary<>();
        setEnableStatistics(false);
        setStatistics(new PoolStatistics(str));
    }

    private DataBuffer doTraceTake(int i, boolean z, boolean z2, String str, final PoolStatistics poolStatistics) {
        int padToBlock = padToBlock(i);
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__pools.tryGetValue(Integer.valueOf(padToBlock), holder);
        ManagedConcurrentStack managedConcurrentStack = (ManagedConcurrentStack) holder.getValue();
        if (!tryGetValue) {
            managedConcurrentStack = this.__pools.getOrAdd(Integer.valueOf(padToBlock), new IFunction1<Integer, ManagedConcurrentStack<byte[]>>() {
                public ManagedConcurrentStack<byte[]> invoke(Integer num) {
                    return DataBufferPool.this.createStack(poolStatistics);
                }
            });
        }
        if (getEnableStatistics()) {
            long j = (long) (padToBlock - i);
            getStatistics().__paddingWasted.add(j);
            if (poolStatistics != null) {
                poolStatistics.__paddingWasted.add(j);
            }
        }
        Holder holder2 = new Holder(null);
        boolean tryPop = managedConcurrentStack.tryPop(holder2);
        byte[] bArr = (byte[]) holder2.getValue();
        if (tryPop) {
            if (z2) {
                BitAssistant.set(bArr, 0, ArrayExtensions.getLength(bArr), 0);
            }
            if (getEnableStatistics()) {
                getStatistics().__hits.increment();
                getStatistics().__takenSize.add((long) ArrayExtensions.getLength(bArr));
                getStatistics().__pendingPoolSize.subtract((long) ArrayExtensions.getLength(bArr));
                if (poolStatistics != null) {
                    poolStatistics.__hits.increment();
                    poolStatistics.__takenSize.add((long) ArrayExtensions.getLength(bArr));
                    poolStatistics.__pendingPoolSize.subtract((long) ArrayExtensions.getLength(bArr));
                }
            }
            return new DataBufferPooled(this, bArr, i, z, str);
        }
        if (getEnableStatistics()) {
            getStatistics().__misses.increment();
            long j2 = (long) padToBlock;
            getStatistics().__takenSize.add(j2);
            getStatistics().__totalPoolSize.add(j2);
            if (poolStatistics != null) {
                poolStatistics.__misses.increment();
                poolStatistics.__takenSize.add(j2);
                poolStatistics.__totalPoolSize.add(j2);
            }
        }
        return new DataBufferPooled(this, new byte[padToBlock], i, z, str);
    }

    public int getBlockSize() {
        return this.__blockSize;
    }

    public boolean getDisabled() {
        return this._disabled;
    }

    public boolean getEnableStatistics() {
        return this._enableStatistics;
    }

    public static DataBufferPool getInstance() {
        if (__singleton == null) {
            synchronized (__singletonLock) {
                __singleton = new DataBufferPool();
            }
        }
        return __singleton;
    }

    /* access modifiers changed from: package-private */
    public PoolStatistics getOrAddTraceStatistics(String str) {
        Holder holder = new Holder(null);
        return !this.__traceStatistics.tryGetValue(str, holder) ? this.__traceStatistics.getOrAdd(str, new IFunctionDelegate1<String, PoolStatistics>() {
            public String getId() {
                return "fm.liveswitch.DataBufferPool.createTraceStatistics";
            }

            public PoolStatistics invoke(String str) {
                return DataBufferPool.this.createTraceStatistics(str);
            }
        }) : (PoolStatistics) holder.getValue();
    }

    public PoolStatistics getStatistics() {
        return this._statistics;
    }

    public static DataBufferPoolTracer getTracer(String str) {
        return getTracer(str, getInstance());
    }

    public static DataBufferPoolTracer getTracer(String str, DataBufferPool dataBufferPool) {
        return new DataBufferPoolTracer(str, dataBufferPool);
    }

    public static DataBufferPoolTracer getTracer(Class cls) {
        return getTracer(ClassExtensions.getFullName(cls));
    }

    public static DataBufferPoolTracer getTracer(Class cls, DataBufferPool dataBufferPool) {
        return getTracer(ClassExtensions.getFullName(cls), dataBufferPool);
    }

    public PoolStatistics[] getTraceStatistics() {
        Collection<PoolStatistics> values = this.__traceStatistics.getValues();
        PoolStatistics[] poolStatisticsArr = new PoolStatistics[CollectionExtensions.getCount(values)];
        int i = 0;
        for (PoolStatistics poolStatistics : values) {
            poolStatisticsArr[i] = poolStatistics;
            i++;
        }
        return poolStatisticsArr;
    }

    public PoolStatistics getTraceStatistics(String str) {
        if (str == null) {
            return getStatistics();
        }
        Holder holder = new Holder(null);
        boolean tryGetValue = this.__traceStatistics.tryGetValue(str, holder);
        PoolStatistics poolStatistics = (PoolStatistics) holder.getValue();
        if (tryGetValue) {
            return poolStatistics;
        }
        return null;
    }

    public PoolStatistics getTraceStatistics(Class cls) {
        return getTraceStatistics(ClassExtensions.getFullName(cls));
    }

    private int padToBlock(int i) {
        int i2 = this.__blockSize;
        int i3 = (i + i2) - 1;
        return i3 - ((i2 - 1) & i3);
    }

    /* access modifiers changed from: package-private */
    public void returnBuffer(DataBufferPooled dataBufferPooled, String str) {
        if (!getDisabled()) {
            PoolStatistics orAddTraceStatistics = (!getEnableStatistics() || str == null) ? null : getOrAddTraceStatistics(str);
            int decrementRetain = dataBufferPooled.decrementRetain();
            if (decrementRetain == 0) {
                byte[] invalidate = dataBufferPooled.invalidate();
                Holder holder = new Holder(null);
                boolean tryGetValue = this.__pools.tryGetValue(Integer.valueOf(ArrayExtensions.getLength(invalidate)), holder);
                ManagedConcurrentStack managedConcurrentStack = (ManagedConcurrentStack) holder.getValue();
                if (tryGetValue) {
                    managedConcurrentStack.push(invalidate);
                    if (getEnableStatistics()) {
                        getStatistics().__returnedSize.add((long) ArrayExtensions.getLength(invalidate));
                        getStatistics().__pendingPoolSize.add((long) ArrayExtensions.getLength(invalidate));
                        if (orAddTraceStatistics != null) {
                            orAddTraceStatistics.__returnedSize.add((long) ArrayExtensions.getLength(invalidate));
                            orAddTraceStatistics.__pendingPoolSize.add((long) ArrayExtensions.getLength(invalidate));
                            return;
                        }
                        return;
                    }
                    return;
                }
                throw new RuntimeException(new Exception("Cannot return a buffer to the pool that was not taken from the pool."));
            } else if (decrementRetain < 0) {
                this._log.warn("Cannot return a buffer to the pool with a negative retain count.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setBlockSize(int i) {
        this.__blockSize = i;
    }

    public void setDisabled(boolean z) {
        this._disabled = z;
    }

    public void setEnableStatistics(boolean z) {
        this._enableStatistics = z;
    }

    private void setStatistics(PoolStatistics poolStatistics) {
        this._statistics = poolStatistics;
    }

    public DataBuffer take(int i) {
        return traceTake(i, (String) null);
    }

    public DataBuffer take(int i, boolean z) {
        return traceTake(i, z, (String) null);
    }

    public DataBuffer take(int i, boolean z, boolean z2) {
        return traceTake(i, z, z2, (String) null);
    }

    /* access modifiers changed from: package-private */
    public DataBuffer traceTake(int i, boolean z, boolean z2, String str) {
        if (getDisabled()) {
            return DataBuffer.allocate(i, z);
        }
        PoolStatistics poolStatistics = null;
        if (getEnableStatistics() && str != null) {
            poolStatistics = getOrAddTraceStatistics(str);
        }
        return doTraceTake(i, z, z2, str, poolStatistics);
    }

    /* access modifiers changed from: package-private */
    public DataBuffer traceTake(int i, boolean z, String str) {
        return traceTake(i, z, false, str);
    }

    /* access modifiers changed from: package-private */
    public DataBuffer traceTake(int i, String str) {
        return traceTake(i, false, str);
    }
}
