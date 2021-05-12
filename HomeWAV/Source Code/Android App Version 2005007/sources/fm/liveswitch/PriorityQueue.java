package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

public class PriorityQueue<T> {
    private IFunction2<T, T, CompareResult> _comparer;
    private ArrayList<T> _data = new ArrayList<>();

    public T dequeue() {
        int count = ArrayListExtensions.getCount(this._data) - 1;
        int i = 0;
        T t = ArrayListExtensions.getItem(this._data).get(0);
        ArrayListExtensions.getItem(this._data).set(0, ArrayListExtensions.getItem(this._data).get(count));
        ArrayListExtensions.removeAt(this._data, count);
        int i2 = count - 1;
        while (true) {
            int i3 = (i * 2) + 1;
            if (i3 > i2) {
                return t;
            }
            int i4 = i3 + 1;
            if (i4 <= i2 && Global.equals(this._comparer.invoke(ArrayListExtensions.getItem(this._data).get(i4), ArrayListExtensions.getItem(this._data).get(i3)), CompareResult.Negative)) {
                i3 = i4;
            }
            if (!Global.equals(this._comparer.invoke(ArrayListExtensions.getItem(this._data).get(i), ArrayListExtensions.getItem(this._data).get(i3)), CompareResult.Positive)) {
                return t;
            }
            T t2 = ArrayListExtensions.getItem(this._data).get(i);
            ArrayListExtensions.getItem(this._data).set(i, ArrayListExtensions.getItem(this._data).get(i3));
            ArrayListExtensions.getItem(this._data).set(i3, t2);
            i = i3;
        }
    }

    public void enqueue(T t) {
        this._data.add(t);
        int count = ArrayListExtensions.getCount(this._data) - 1;
        while (count > 0) {
            int i = (count - 1) / 2;
            if (Global.equals(this._comparer.invoke(ArrayListExtensions.getItem(this._data).get(count), ArrayListExtensions.getItem(this._data).get(i)), CompareResult.Negative)) {
                T t2 = ArrayListExtensions.getItem(this._data).get(count);
                ArrayListExtensions.getItem(this._data).set(count, ArrayListExtensions.getItem(this._data).get(i));
                ArrayListExtensions.getItem(this._data).set(i, t2);
                count = i;
            } else {
                return;
            }
        }
    }

    public boolean exists(T t) {
        Iterator<T> it = this._data.iterator();
        while (it.hasNext()) {
            if (Global.equals(this._comparer.invoke(it.next(), t), CompareResult.Equal)) {
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return ArrayListExtensions.getCount(this._data);
    }

    public boolean isConsistent() {
        if (ArrayListExtensions.getCount(this._data) != 0) {
            int count = ArrayListExtensions.getCount(this._data) - 1;
            for (int i = 0; i < ArrayListExtensions.getCount(this._data); i++) {
                int i2 = i * 2;
                int i3 = i2 + 1;
                int i4 = i2 + 2;
                if (i3 <= count && Global.equals(this._comparer.invoke(ArrayListExtensions.getItem(this._data).get(i), ArrayListExtensions.getItem(this._data).get(i3)), CompareResult.Positive)) {
                    return false;
                }
                if (i4 <= count && Global.equals(this._comparer.invoke(ArrayListExtensions.getItem(this._data).get(i), ArrayListExtensions.getItem(this._data).get(i4)), CompareResult.Positive)) {
                    return false;
                }
            }
        }
        return true;
    }

    public T peek() {
        if (ArrayListExtensions.getCount(this._data) == 0) {
            return null;
        }
        return ArrayListExtensions.getItem(this._data).get(0);
    }

    public PriorityQueue(IFunction2<T, T, CompareResult> iFunction2) {
        this._comparer = iFunction2;
    }
}
