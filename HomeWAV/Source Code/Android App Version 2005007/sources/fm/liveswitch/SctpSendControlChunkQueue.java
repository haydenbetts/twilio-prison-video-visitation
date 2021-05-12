package fm.liveswitch;

import java.util.ArrayList;

class SctpSendControlChunkQueue {
    private ArrayList<SctpControlChunk> _controlChunks;

    public SctpControlChunk dequeue() {
        ArrayList<SctpControlChunk> arrayList = this._controlChunks;
        if (arrayList == null || ArrayListExtensions.getCount(arrayList) <= 0) {
            return null;
        }
        SctpControlChunk sctpControlChunk = (SctpControlChunk) ArrayListExtensions.getItem(this._controlChunks).get(0);
        ArrayListExtensions.removeAt(this._controlChunks, 0);
        return sctpControlChunk;
    }

    public void enqueue(SctpControlChunk sctpControlChunk) {
        this._controlChunks.add(sctpControlChunk);
    }

    public int getCount() {
        return ArrayListExtensions.getCount(this._controlChunks);
    }

    public SctpControlChunk peek() {
        ArrayList<SctpControlChunk> arrayList = this._controlChunks;
        if (arrayList == null || ArrayListExtensions.getCount(arrayList) <= 0) {
            return null;
        }
        return (SctpControlChunk) ArrayListExtensions.getItem(this._controlChunks).get(0);
    }

    public void removeAll() {
        this._controlChunks.clear();
    }

    public SctpSendControlChunkQueue() {
        this._controlChunks = new ArrayList<>();
        this._controlChunks = new ArrayList<>();
    }
}
