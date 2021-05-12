package fm.liveswitch;

import java.util.HashMap;

abstract class SctpDataQueue {
    public static final long Unset = -1;
    Object __lock;
    HashMap<String, LinkedListNode<SctpDataChunk>> __tsnDataDictionary = new HashMap<>();
    LinkedList<SctpDataChunk> __tsnDataLinkedList = new LinkedList<>();

    public void add(SctpDataChunk sctpDataChunk) {
        LinkedListNode<SctpDataChunk> linkedListNode;
        synchronized (this.__lock) {
            boolean z = false;
            LinkedListNode<SctpDataChunk> last = this.__tsnDataLinkedList.getLast();
            LinkedListNode<SctpDataChunk> linkedListNode2 = null;
            if (last == null) {
                linkedListNode = this.__tsnDataLinkedList.addFirst(sctpDataChunk);
            } else {
                while (!z) {
                    if (SctpDataChunk.compareTsns(last.getValue().getTsn(), sctpDataChunk.getTsn()) > 1) {
                        linkedListNode2 = this.__tsnDataLinkedList.addAfterNode(last, sctpDataChunk);
                    } else if (last.getPrevious() != null) {
                        last = last.getPrevious();
                    } else {
                        linkedListNode2 = this.__tsnDataLinkedList.addFirst(sctpDataChunk);
                    }
                    z = true;
                }
                linkedListNode = linkedListNode2;
            }
            HashMapExtensions.add(this.__tsnDataDictionary, LongExtensions.toString(Long.valueOf(sctpDataChunk.getTsn())), linkedListNode);
        }
    }

    public boolean chunkExists(long j) {
        boolean containsKey;
        synchronized (this.__lock) {
            containsKey = this.__tsnDataDictionary.containsKey(LongExtensions.toString(Long.valueOf(j)));
        }
        return containsKey;
    }

    public SctpDataChunk getChunk(long j) {
        synchronized (this.__lock) {
            if (!chunkExists(j)) {
                return null;
            }
            SctpDataChunk sctpDataChunk = (SctpDataChunk) HashMapExtensions.getItem(this.__tsnDataDictionary).get(LongExtensions.toString(Long.valueOf(j))).getValue();
            return sctpDataChunk;
        }
    }

    public int getCount() {
        int count;
        synchronized (this.__lock) {
            count = HashMapExtensions.getCount(this.__tsnDataDictionary);
        }
        return count;
    }

    public long getEarliestTSN() {
        long tsn;
        synchronized (this.__lock) {
            tsn = this.__tsnDataLinkedList.getFirst() == null ? -1 : this.__tsnDataLinkedList.getFirst().getValue().getTsn();
        }
        return tsn;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: fm.liveswitch.SctpDataChunk} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0072, code lost:
        return r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.SctpDataChunk getNextChunk(long r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__lock
            monitor-enter(r0)
            boolean r1 = r5.chunkExists(r6)     // Catch:{ all -> 0x007a }
            r2 = 0
            if (r1 == 0) goto L_0x002e
            java.util.HashMap<java.lang.String, fm.liveswitch.LinkedListNode<fm.liveswitch.SctpDataChunk>> r1 = r5.__tsnDataDictionary     // Catch:{ all -> 0x007a }
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)     // Catch:{ all -> 0x007a }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x007a }
            java.lang.String r6 = fm.liveswitch.LongExtensions.toString(r6)     // Catch:{ all -> 0x007a }
            java.lang.Object r6 = r1.get(r6)     // Catch:{ all -> 0x007a }
            fm.liveswitch.LinkedListNode r6 = (fm.liveswitch.LinkedListNode) r6     // Catch:{ all -> 0x007a }
            fm.liveswitch.LinkedListNode r6 = r6.getNext()     // Catch:{ all -> 0x007a }
            if (r6 != 0) goto L_0x0025
            goto L_0x002c
        L_0x0025:
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x007a }
            r2 = r6
            fm.liveswitch.SctpDataChunk r2 = (fm.liveswitch.SctpDataChunk) r2     // Catch:{ all -> 0x007a }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return r2
        L_0x002e:
            fm.liveswitch.LinkedList<fm.liveswitch.SctpDataChunk> r1 = r5.__tsnDataLinkedList     // Catch:{ all -> 0x007a }
            fm.liveswitch.LinkedListNode r1 = r1.getFirst()     // Catch:{ all -> 0x007a }
        L_0x0034:
            if (r1 == 0) goto L_0x0078
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x007a }
            fm.liveswitch.SctpDataChunk r3 = (fm.liveswitch.SctpDataChunk) r3     // Catch:{ all -> 0x007a }
            long r3 = r3.getTsn()     // Catch:{ all -> 0x007a }
            int r3 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r6)     // Catch:{ all -> 0x007a }
            r4 = 1
            if (r3 != r4) goto L_0x004f
            java.lang.Object r6 = r1.getValue()     // Catch:{ all -> 0x007a }
            fm.liveswitch.SctpDataChunk r6 = (fm.liveswitch.SctpDataChunk) r6     // Catch:{ all -> 0x007a }
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return r6
        L_0x004f:
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x007a }
            fm.liveswitch.SctpDataChunk r3 = (fm.liveswitch.SctpDataChunk) r3     // Catch:{ all -> 0x007a }
            long r3 = r3.getTsn()     // Catch:{ all -> 0x007a }
            int r3 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r6)     // Catch:{ all -> 0x007a }
            if (r3 != 0) goto L_0x0073
            fm.liveswitch.LinkedListNode r6 = r1.getNext()     // Catch:{ all -> 0x007a }
            if (r6 != 0) goto L_0x0066
            goto L_0x0071
        L_0x0066:
            fm.liveswitch.LinkedListNode r6 = r1.getNext()     // Catch:{ all -> 0x007a }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x007a }
            r2 = r6
            fm.liveswitch.SctpDataChunk r2 = (fm.liveswitch.SctpDataChunk) r2     // Catch:{ all -> 0x007a }
        L_0x0071:
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return r2
        L_0x0073:
            fm.liveswitch.LinkedListNode r1 = r1.getNext()     // Catch:{ all -> 0x007a }
            goto L_0x0034
        L_0x0078:
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            return r2
        L_0x007a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpDataQueue.getNextChunk(long):fm.liveswitch.SctpDataChunk");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: fm.liveswitch.SctpDataChunk} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0082, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
        return null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.SctpDataChunk getPreviousChunk(long r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__lock
            monitor-enter(r0)
            boolean r1 = r5.chunkExists(r6)     // Catch:{ all -> 0x008a }
            r2 = 0
            if (r1 == 0) goto L_0x002e
            java.util.HashMap<java.lang.String, fm.liveswitch.LinkedListNode<fm.liveswitch.SctpDataChunk>> r1 = r5.__tsnDataDictionary     // Catch:{ all -> 0x008a }
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)     // Catch:{ all -> 0x008a }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x008a }
            java.lang.String r6 = fm.liveswitch.LongExtensions.toString(r6)     // Catch:{ all -> 0x008a }
            java.lang.Object r6 = r1.get(r6)     // Catch:{ all -> 0x008a }
            fm.liveswitch.LinkedListNode r6 = (fm.liveswitch.LinkedListNode) r6     // Catch:{ all -> 0x008a }
            fm.liveswitch.LinkedListNode r6 = r6.getPrevious()     // Catch:{ all -> 0x008a }
            if (r6 != 0) goto L_0x0025
            goto L_0x002c
        L_0x0025:
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x008a }
            r2 = r6
            fm.liveswitch.SctpDataChunk r2 = (fm.liveswitch.SctpDataChunk) r2     // Catch:{ all -> 0x008a }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return r2
        L_0x002e:
            fm.liveswitch.LinkedList<fm.liveswitch.SctpDataChunk> r1 = r5.__tsnDataLinkedList     // Catch:{ all -> 0x008a }
            fm.liveswitch.LinkedListNode r1 = r1.getLast()     // Catch:{ all -> 0x008a }
        L_0x0034:
            if (r1 == 0) goto L_0x0088
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x008a }
            if (r3 == 0) goto L_0x0088
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x008a }
            fm.liveswitch.SctpDataChunk r3 = (fm.liveswitch.SctpDataChunk) r3     // Catch:{ all -> 0x008a }
            long r3 = r3.getTsn()     // Catch:{ all -> 0x008a }
            int r3 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r6)     // Catch:{ all -> 0x008a }
            r4 = 2
            if (r3 != r4) goto L_0x0055
            java.lang.Object r6 = r1.getValue()     // Catch:{ all -> 0x008a }
            fm.liveswitch.SctpDataChunk r6 = (fm.liveswitch.SctpDataChunk) r6     // Catch:{ all -> 0x008a }
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return r6
        L_0x0055:
            java.lang.Object r3 = r1.getValue()     // Catch:{ all -> 0x008a }
            fm.liveswitch.SctpDataChunk r3 = (fm.liveswitch.SctpDataChunk) r3     // Catch:{ all -> 0x008a }
            long r3 = r3.getTsn()     // Catch:{ all -> 0x008a }
            int r3 = fm.liveswitch.SctpDataChunk.compareTsns(r3, r6)     // Catch:{ all -> 0x008a }
            if (r3 != 0) goto L_0x0083
            fm.liveswitch.LinkedListNode r6 = r1.getPrevious()     // Catch:{ all -> 0x008a }
            if (r6 == 0) goto L_0x0081
            fm.liveswitch.LinkedListNode r6 = r1.getPrevious()     // Catch:{ all -> 0x008a }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x008a }
            if (r6 != 0) goto L_0x0076
            goto L_0x0081
        L_0x0076:
            fm.liveswitch.LinkedListNode r6 = r1.getPrevious()     // Catch:{ all -> 0x008a }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x008a }
            r2 = r6
            fm.liveswitch.SctpDataChunk r2 = (fm.liveswitch.SctpDataChunk) r2     // Catch:{ all -> 0x008a }
        L_0x0081:
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return r2
        L_0x0083:
            fm.liveswitch.LinkedListNode r1 = r1.getPrevious()     // Catch:{ all -> 0x008a }
            goto L_0x0034
        L_0x0088:
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            return r2
        L_0x008a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SctpDataQueue.getPreviousChunk(long):fm.liveswitch.SctpDataChunk");
    }

    public long[] getTsns() {
        long[] jArr;
        synchronized (this.__lock) {
            jArr = new long[HashMapExtensions.getCount(this.__tsnDataDictionary)];
            int i = 0;
            LinkedListNode<SctpDataChunk> first = this.__tsnDataLinkedList.getFirst();
            while (first != null) {
                jArr[i] = first.getValue().getTsn();
                first = first.getNext();
                i++;
            }
        }
        return jArr;
    }

    public void purge(long j) {
        synchronized (this.__lock) {
            LinkedListNode<SctpDataChunk> first = this.__tsnDataLinkedList.getFirst();
            while (first != null && SctpDataChunk.compareTsns(j, first.getValue().getTsn()) != 2) {
                LinkedListNode<SctpDataChunk> next = first.getNext();
                HashMapExtensions.remove(this.__tsnDataDictionary, LongExtensions.toString(Long.valueOf(first.getValue().getTsn())));
                this.__tsnDataLinkedList.remove(first.getValue());
                first = next;
            }
        }
    }

    public boolean remove(long j) {
        synchronized (this.__lock) {
            if (getChunk(j) == null) {
                return false;
            }
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__tsnDataDictionary, LongExtensions.toString(Long.valueOf(j)), holder);
            LinkedListNode linkedListNode = (LinkedListNode) holder.getValue();
            if (tryGetValue) {
                this.__tsnDataLinkedList.removeNode(linkedListNode);
            }
            HashMapExtensions.remove(this.__tsnDataDictionary, LongExtensions.toString(Long.valueOf(j)));
            return true;
        }
    }

    public void removeAll() {
        synchronized (this.__lock) {
            this.__tsnDataDictionary.clear();
            this.__tsnDataLinkedList.clear();
        }
    }

    protected SctpDataQueue() {
    }
}
