package fm.liveswitch;

import java.util.ArrayList;

class InternalConcurrentStack<T> {
    private ArrayList<T> _backingData = new ArrayList<>();

    public void clear() {
        synchronized (this._backingData) {
            this._backingData.clear();
        }
    }

    public int getCount() {
        return ArrayListExtensions.getCount(this._backingData);
    }

    public boolean getIsEmpty() {
        return getCount() == 0;
    }

    public void push(T t) {
        synchronized (this._backingData) {
            this._backingData.add(t);
        }
    }

    public boolean tryPeek(Holder<T> holder) {
        synchronized (this._backingData) {
            if (getIsEmpty()) {
                holder.setValue(null);
                return false;
            }
            holder.setValue(ArrayListExtensions.getItem(this._backingData).get(ArrayListExtensions.getCount(this._backingData) - 1));
            return true;
        }
    }

    public boolean tryPop(Holder<T> holder) {
        synchronized (this._backingData) {
            if (getIsEmpty()) {
                holder.setValue(null);
                return false;
            }
            holder.setValue(ArrayListExtensions.getItem(this._backingData).get(ArrayListExtensions.getCount(this._backingData) - 1));
            ArrayList<T> arrayList = this._backingData;
            ArrayListExtensions.removeAt(arrayList, ArrayListExtensions.getCount(arrayList) - 1);
            return true;
        }
    }
}
