package fm.liveswitch;

import fm.liveswitch.stun.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class IceTransactionManager {
    private static ILog __log = Log.getLogger(IceTransactionManager.class);
    private static float __rttSmoothingFactor = 0.8f;
    private Object __lock;
    private HashMap<String, ArrayList<ScheduledItem>> __scheduledItemsByCallerHash = new HashMap<>();
    private HashMap<String, ScheduledItem> __scheduledItemsByTransactionKey = new HashMap<>();
    private Scheduler __scheduler;
    private HashMap<String, ArrayList<IceSendMessageArgs>> __sendMessageArgsByCallerHash = new HashMap<>();
    private HashMap<String, IceSendMessageArgs> __sendMessageArgsByTransactionKey = new HashMap<>();

    /* access modifiers changed from: package-private */
    public String addTransaction(IceSendMessageArgs iceSendMessageArgs, Object obj, String str) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(obj.hashCode()));
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__sendMessageArgsByCallerHash, integerExtensions, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                arrayList.add(iceSendMessageArgs);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(iceSendMessageArgs);
                HashMapExtensions.set(HashMapExtensions.getItem(this.__sendMessageArgsByCallerHash), integerExtensions, arrayList2);
            }
            HashMapExtensions.set(HashMapExtensions.getItem(this.__sendMessageArgsByTransactionKey), str, iceSendMessageArgs);
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public String addTransaction(ScheduledItem scheduledItem, Object obj) {
        String encodeBuffer = Base64.encodeBuffer(((IceSendMessageArgs) scheduledItem.getState()).getMessage().getTransactionId());
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(obj.hashCode()));
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__scheduledItemsByCallerHash, integerExtensions, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                arrayList.add(scheduledItem);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(scheduledItem);
                HashMapExtensions.set(HashMapExtensions.getItem(this.__scheduledItemsByCallerHash), integerExtensions, arrayList2);
            }
            HashMapExtensions.set(HashMapExtensions.getItem(this.__scheduledItemsByTransactionKey), encodeBuffer, scheduledItem);
        }
        this.__scheduler.add(scheduledItem);
        return encodeBuffer;
    }

    static int calculateInstantaneousRtt(boolean z, IceSendRequestSuccessArgs iceSendRequestSuccessArgs, boolean z2, long j) {
        if (z) {
            return calculateInstantaneousRttFireAndForgetTransactions(iceSendRequestSuccessArgs, j);
        }
        return calculateInstantaneousRttReliableTransactions(iceSendRequestSuccessArgs, z2, j);
    }

    private static int calculateInstantaneousRttFireAndForgetTransactions(IceSendRequestSuccessArgs iceSendRequestSuccessArgs, long j) {
        long requestDispatchTimestamp = iceSendRequestSuccessArgs.getRequestDispatchTimestamp();
        if (requestDispatchTimestamp > 0) {
            long j2 = j - requestDispatchTimestamp;
            if (j2 >= 0) {
                return (int) j2;
            }
            __log.error("ICE Transaction Manager: invalid round trip time. Cannot calculate round trip time for a candidate pair.");
            return -1;
        }
        __log.error("ICE Transaction Manager: departure time is not set for SendMessageArgs. Cannot calculate round trip time for a candidate pair.");
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int calculateInstantaneousRttReliableTransactions(fm.liveswitch.IceSendRequestSuccessArgs r5, boolean r6, long r7) {
        /*
            fm.liveswitch.ScheduledItem r0 = r5.getItem()
            fm.liveswitch.stun.Message r5 = r5.getResponse()
            fm.liveswitch.stun.TransactionTransmitCounterAttribute r5 = r5.getTransactionTransmitCounter()
            int r1 = fm.liveswitch.ScheduledItem.getUnset()
            long r1 = (long) r1
            r3 = 1
            r4 = 0
            if (r5 == 0) goto L_0x0054
            if (r6 == 0) goto L_0x0054
            int r5 = r5.getNumRequests()
            boolean r6 = r0.getRecordDetailedInvocationTimes()
            if (r6 == 0) goto L_0x005b
            long r5 = r0.getInvocationTime(r5)     // Catch:{ Exception -> 0x0026 }
            goto L_0x0047
        L_0x0026:
            r6 = move-exception
            fm.liveswitch.ILog r1 = __log
            java.lang.String r0 = r0.getId()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r2 = "Could not obtain transaction execution time for scheduled item {0} attempt {1}:  {2}."
            java.lang.String r5 = fm.liveswitch.StringExtensions.format(r2, r0, r5, r6)
            r1.error(r5)
            int r5 = fm.liveswitch.ScheduledItem.getUnset()
            long r5 = (long) r5
        L_0x0047:
            int r0 = fm.liveswitch.ScheduledItem.getUnset()
            long r0 = (long) r0
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r3 = 0
        L_0x0052:
            r1 = r5
            goto L_0x005c
        L_0x0054:
            if (r6 != 0) goto L_0x005b
            long r1 = r0.getLastInvocationTime()
            goto L_0x005c
        L_0x005b:
            r3 = 0
        L_0x005c:
            if (r3 == 0) goto L_0x0077
            int r5 = fm.liveswitch.ScheduledItem.getUnset()
            long r5 = (long) r5
            int r0 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0070
            long r7 = r7 - r1
            r5 = 0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0077
            int r5 = (int) r7
            return r5
        L_0x0070:
            fm.liveswitch.ILog r5 = __log
            java.lang.String r6 = "ICE Transaction Manager: departure time is not set for a ScheduledItem. Cannot calculate round trip time for a candidate pair."
            r5.error(r6)
        L_0x0077:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransactionManager.calculateInstantaneousRttReliableTransactions(fm.liveswitch.IceSendRequestSuccessArgs, boolean, long):int");
    }

    static int calculateSmoothedRtt(int i, int i2) {
        if (i2 == -1) {
            i2 = i;
        }
        float f = __rttSmoothingFactor;
        return (int) ((((float) i2) * f) + (((float) i) * (1.0f - f)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasActiveTransactions(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__lock
            monitor-enter(r0)
            int r6 = r6.hashCode()     // Catch:{ all -> 0x0049 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0049 }
            java.lang.String r6 = fm.liveswitch.IntegerExtensions.toString(r6)     // Catch:{ all -> 0x0049 }
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0049 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0049 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.ScheduledItem>> r3 = r5.__scheduledItemsByCallerHash     // Catch:{ all -> 0x0049 }
            boolean r3 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r6, r1)     // Catch:{ all -> 0x0049 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0049 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x0049 }
            r4 = 1
            if (r3 == 0) goto L_0x002c
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x0049 }
            if (r1 <= 0) goto L_0x002c
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r4
        L_0x002c:
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0049 }
            r1.<init>(r2)     // Catch:{ all -> 0x0049 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.IceSendMessageArgs>> r2 = r5.__sendMessageArgsByCallerHash     // Catch:{ all -> 0x0049 }
            boolean r6 = fm.liveswitch.HashMapExtensions.tryGetValue(r2, r6, r1)     // Catch:{ all -> 0x0049 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0049 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x0049 }
            if (r6 == 0) goto L_0x0046
            int r6 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x0049 }
            if (r6 <= 0) goto L_0x0046
            goto L_0x0047
        L_0x0046:
            r4 = 0
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            return r4
        L_0x0049:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransactionManager.hasActiveTransactions(java.lang.Object):boolean");
    }

    public IceTransactionManager(Object obj, Scheduler scheduler) {
        this.__lock = obj;
        this.__scheduler = scheduler;
    }

    public void remove(Object obj) {
        synchronized (this.__lock) {
            String integerExtensions = IntegerExtensions.toString(Integer.valueOf(obj.hashCode()));
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__scheduledItemsByCallerHash, integerExtensions, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ScheduledItem scheduledItem = (ScheduledItem) it.next();
                        this.__scheduler.remove(scheduledItem);
                        HashMapExtensions.remove(this.__scheduledItemsByTransactionKey, Base64.encodeBuffer(((IceSendMessageArgs) scheduledItem.getState()).getMessage().getTransactionId()));
                    }
                }
                arrayList.clear();
            }
            Holder holder2 = new Holder(null);
            boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__sendMessageArgsByCallerHash, integerExtensions, holder2);
            ArrayList arrayList2 = (ArrayList) holder2.getValue();
            if (tryGetValue2) {
                if (arrayList2 != null) {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        HashMapExtensions.remove(this.__sendMessageArgsByTransactionKey, Base64.encodeBuffer(((IceSendMessageArgs) it2.next()).getMessage().getTransactionId()));
                    }
                }
                arrayList2.clear();
            }
            HashMapExtensions.remove(this.__scheduledItemsByCallerHash, integerExtensions);
            HashMapExtensions.remove(this.__sendMessageArgsByCallerHash, integerExtensions);
        }
    }

    public void remove(ScheduledItem scheduledItem, Object obj) {
        Message message;
        if (scheduledItem != null && obj != null) {
            IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
            DataBuffer dataBuffer = null;
            if (iceSendMessageArgs == null) {
                message = null;
            } else {
                message = iceSendMessageArgs.getMessage();
            }
            if (message != null) {
                dataBuffer = message.getTransactionId();
            }
            if (dataBuffer != null) {
                remove(Base64.encodeBuffer(dataBuffer), obj);
            }
        }
    }

    public void remove(String str, Object obj) {
        synchronized (this.__lock) {
            String integerExtensions = IntegerExtensions.toString(Integer.valueOf(obj.hashCode()));
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__scheduledItemsByCallerHash, integerExtensions, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (tryGetValue) {
                if (arrayList != null) {
                    Holder holder2 = new Holder(null);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(this.__scheduledItemsByTransactionKey, str, holder2);
                    ScheduledItem scheduledItem = (ScheduledItem) holder2.getValue();
                    if (tryGetValue2) {
                        arrayList.remove(scheduledItem);
                        this.__scheduler.remove(scheduledItem);
                    }
                    if (ArrayListExtensions.getCount(arrayList) < 1) {
                        HashMapExtensions.remove(this.__scheduledItemsByCallerHash, integerExtensions);
                    }
                } else {
                    HashMapExtensions.remove(this.__scheduledItemsByCallerHash, integerExtensions);
                }
            }
            Holder holder3 = new Holder(null);
            boolean tryGetValue3 = HashMapExtensions.tryGetValue(this.__sendMessageArgsByCallerHash, integerExtensions, holder3);
            ArrayList arrayList2 = (ArrayList) holder3.getValue();
            if (tryGetValue3) {
                if (arrayList2 != null) {
                    Holder holder4 = new Holder(null);
                    boolean tryGetValue4 = HashMapExtensions.tryGetValue(this.__sendMessageArgsByTransactionKey, str, holder4);
                    IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) holder4.getValue();
                    if (tryGetValue4) {
                        arrayList2.remove(iceSendMessageArgs);
                    }
                    if (ArrayListExtensions.getCount(arrayList2) < 1) {
                        HashMapExtensions.remove(this.__sendMessageArgsByCallerHash, integerExtensions);
                    }
                } else {
                    HashMapExtensions.remove(this.__sendMessageArgsByCallerHash, integerExtensions);
                }
            }
            HashMapExtensions.remove(this.__scheduledItemsByTransactionKey, str);
            HashMapExtensions.remove(this.__sendMessageArgsByTransactionKey, str);
        }
    }

    public ScheduledItem[] scheduledItemsForCaller(Object obj) {
        synchronized (this.__lock) {
            ArrayList arrayList = HashMapExtensions.getItem(this.__scheduledItemsByCallerHash).get(IntegerExtensions.toString(Integer.valueOf(obj.hashCode())));
            if (arrayList == null || ArrayListExtensions.getCount(arrayList) <= 0) {
                return null;
            }
            ScheduledItem[] scheduledItemArr = (ScheduledItem[]) arrayList.toArray(new ScheduledItem[0]);
            return scheduledItemArr;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.IceSendMessageArgs[] sendMessageArgsForCaller(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            int r5 = r5.hashCode()     // Catch:{ all -> 0x0032 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0032 }
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)     // Catch:{ all -> 0x0032 }
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0032 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0032 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<fm.liveswitch.IceSendMessageArgs>> r3 = r4.__sendMessageArgsByCallerHash     // Catch:{ all -> 0x0032 }
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r5, r1)     // Catch:{ all -> 0x0032 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0032 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x0032 }
            if (r5 == 0) goto L_0x0030
            if (r1 == 0) goto L_0x0030
            r5 = 0
            fm.liveswitch.IceSendMessageArgs[] r5 = new fm.liveswitch.IceSendMessageArgs[r5]     // Catch:{ all -> 0x0032 }
            java.lang.Object[] r5 = r1.toArray(r5)     // Catch:{ all -> 0x0032 }
            fm.liveswitch.IceSendMessageArgs[] r5 = (fm.liveswitch.IceSendMessageArgs[]) r5     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r5
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r2
        L_0x0032:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransactionManager.sendMessageArgsForCaller(java.lang.Object):fm.liveswitch.IceSendMessageArgs[]");
    }

    public void start() {
        this.__scheduler.start();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: fm.liveswitch.IceSendMessageArgs} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean tryTriggerOnResponse(java.lang.String r5, fm.liveswitch.IceSendRequestSuccessArgs r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x004b }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x004b }
            java.util.HashMap<java.lang.String, fm.liveswitch.ScheduledItem> r3 = r4.__scheduledItemsByTransactionKey     // Catch:{ all -> 0x004b }
            fm.liveswitch.HashMapExtensions.tryGetValue(r3, r5, r1)     // Catch:{ all -> 0x004b }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x004b }
            fm.liveswitch.ScheduledItem r1 = (fm.liveswitch.ScheduledItem) r1     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x001e
            java.lang.Object r2 = r1.getState()     // Catch:{ all -> 0x004b }
            fm.liveswitch.IceSendMessageArgs r2 = (fm.liveswitch.IceSendMessageArgs) r2     // Catch:{ all -> 0x004b }
            fm.liveswitch.IceSendMessageArgs r2 = (fm.liveswitch.IceSendMessageArgs) r2     // Catch:{ all -> 0x004b }
        L_0x001e:
            if (r2 != 0) goto L_0x0031
            fm.liveswitch.Holder r3 = new fm.liveswitch.Holder     // Catch:{ all -> 0x004b }
            r3.<init>(r2)     // Catch:{ all -> 0x004b }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceSendMessageArgs> r2 = r4.__sendMessageArgsByTransactionKey     // Catch:{ all -> 0x004b }
            fm.liveswitch.HashMapExtensions.tryGetValue(r2, r5, r3)     // Catch:{ all -> 0x004b }
            java.lang.Object r5 = r3.getValue()     // Catch:{ all -> 0x004b }
            r2 = r5
            fm.liveswitch.IceSendMessageArgs r2 = (fm.liveswitch.IceSendMessageArgs) r2     // Catch:{ all -> 0x004b }
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            if (r2 != 0) goto L_0x0036
            r5 = 0
            return r5
        L_0x0036:
            fm.liveswitch.IAction1 r5 = r2.getOnResponse()
            if (r5 == 0) goto L_0x0049
            r6.setItem(r1)
            long r0 = r2.getDispatchTimestamp()
            r6.setRequestDispatchTimestamp(r0)
            r5.invoke(r6)
        L_0x0049:
            r5 = 1
            return r5
        L_0x004b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransactionManager.tryTriggerOnResponse(java.lang.String, fm.liveswitch.IceSendRequestSuccessArgs):boolean");
    }
}
