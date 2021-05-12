package fm.liveswitch;

import java.util.ArrayList;

public class Sort {
    private static <T> void doQuickSort(ArrayList<T> arrayList, int i, int i2, IFunction2<T, T, CompareResult> iFunction2) {
        if (i < i2) {
            int partition = partition(arrayList, i, i2, ((i2 - i) / 2) + i, iFunction2);
            doQuickSort(arrayList, i, partition - 1, iFunction2);
            doQuickSort(arrayList, partition + 1, i2, iFunction2);
        }
    }

    private static <T> int partition(ArrayList<T> arrayList, int i, int i2, int i3, IFunction2<T, T, CompareResult> iFunction2) {
        T t = ArrayListExtensions.getItem(arrayList).get(i3);
        swap(arrayList, i3, i2);
        int i4 = i;
        while (i < i2) {
            if (Global.equals(iFunction2.invoke(ArrayListExtensions.getItem(arrayList).get(i), t), CompareResult.Negative)) {
                swap(arrayList, i, i4);
                i4++;
            }
            i++;
        }
        swap(arrayList, i4, i2);
        return i4;
    }

    public static <T> void quickSort(ArrayList<T> arrayList, IFunction2<T, T, CompareResult> iFunction2) {
        doQuickSort(arrayList, 0, ArrayListExtensions.getCount(arrayList) - 1, iFunction2);
    }

    private static <T> void swap(ArrayList<T> arrayList, int i, int i2) {
        T t = ArrayListExtensions.getItem(arrayList).get(i);
        ArrayListExtensions.getItem(arrayList).set(i, ArrayListExtensions.getItem(arrayList).get(i2));
        ArrayListExtensions.getItem(arrayList).set(i2, t);
    }
}
