package fm.liveswitch;

import java.util.ArrayList;
import java.util.Enumeration;

public class ArrayListExtensions {
    public static <T> ArrayList<T> getItem(ArrayList<T> arrayList) {
        return arrayList;
    }

    public static <T> int getCount(ArrayList<T> arrayList) {
        return arrayList.size();
    }

    public static <T> void copyTo(ArrayList<T> arrayList, T[] tArr, int i) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            tArr[i + i2] = arrayList.get(i2);
        }
    }

    public static <T> void insert(ArrayList<T> arrayList, int i, T t) {
        arrayList.add(i, t);
    }

    public static <T> void removeAt(ArrayList<T> arrayList, int i) {
        arrayList.remove(i);
    }

    public static <T> ArrayList<T> createArray(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        for (T add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static <T> ArrayList<T> createArray(Iterable<T> iterable) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T add : iterable) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static <T> void addRange(ArrayList<T> arrayList, Iterable<T> iterable) {
        for (T add : iterable) {
            arrayList.add(add);
        }
    }

    public static <T> void addRange(ArrayList<T> arrayList, T[] tArr) {
        for (T add : tArr) {
            arrayList.add(add);
        }
    }

    public static <T> ArrayList<T> getRange(ArrayList<T> arrayList, int i, int i2) {
        ArrayList<T> arrayList2 = new ArrayList<>(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList2.add(arrayList.get(i + i3));
        }
        return arrayList2;
    }

    public static <T> void insertRange(ArrayList<T> arrayList, int i, Iterable<T> iterable) {
        int i2 = 0;
        for (T add : iterable) {
            arrayList.add(i + i2, add);
            i2++;
        }
    }

    public static <T> void insertRange(ArrayList<T> arrayList, int i, T[] tArr) {
        int i2 = 0;
        for (T add : tArr) {
            arrayList.add(i + i2, add);
            i2++;
        }
    }

    public static <T> void removeRange(ArrayList<T> arrayList, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.remove(i);
        }
    }

    public static <T> Enumeration<T> getEnumerator(ArrayList<T> arrayList) {
        return new Enumeration<T>(arrayList) {
            int cursor;
            int size;
            final /* synthetic */ ArrayList val$array;

            {
                this.val$array = r1;
                this.size = r1.size();
            }

            public boolean hasMoreElements() {
                return this.cursor < this.size;
            }

            public T nextElement() {
                ArrayList arrayList = this.val$array;
                int i = this.cursor;
                this.cursor = i + 1;
                return arrayList.get(i);
            }
        };
    }
}
