package fm.liveswitch;

import java.util.ArrayList;

class InternalConcurrentQueue<T> {
    private ArrayList<T> _backingData = new ArrayList<>();

    public void enqueue(T t) {
        synchronized (this._backingData) {
            this._backingData.add(t);
        }
    }

    public int getCount() {
        return ArrayListExtensions.getCount(this._backingData);
    }

    public boolean getIsEmpty() {
        return getCount() == 0;
    }

    public boolean tryDequeue(Holder<T> holder) {
        synchronized (this._backingData) {
            if (getIsEmpty()) {
                holder.setValue(null);
                return false;
            }
            holder.setValue(ArrayListExtensions.getItem(this._backingData).get(0));
            ArrayListExtensions.removeAt(this._backingData, 0);
            return true;
        }
    }

    public boolean tryPeek(Holder<T> holder) {
        synchronized (this._backingData) {
            if (getIsEmpty()) {
                holder.setValue(null);
                return false;
            }
            holder.setValue(ArrayListExtensions.getItem(this._backingData).get(0));
            return true;
        }
    }
}
