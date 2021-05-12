package fm.liveswitch;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

public class HashMapExtensions {
    public static <K, V> HashMap<K, V> getItem(HashMap<K, V> hashMap) {
        return hashMap;
    }

    public static <K, V> int getCount(HashMap<K, V> hashMap) {
        return hashMap.size();
    }

    public static <K, V> Set<K> getKeys(HashMap<K, V> hashMap) {
        return hashMap.keySet();
    }

    public static <K, V> Collection<V> getValues(HashMap<K, V> hashMap) {
        return hashMap.values();
    }

    public static <K, V> V add(HashMap<K, V> hashMap, K k, V v) {
        hashMap.put(k, v);
        return v;
    }

    public static <K, V> V set(HashMap<K, V> hashMap, K k, V v) {
        hashMap.put(k, v);
        return v;
    }

    public static <K, V> boolean remove(HashMap<K, V> hashMap, K k) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        hashMap.remove(k);
        return true;
    }

    public static <K, V> boolean tryGetValue(HashMap<K, V> hashMap, K k, Holder<V> holder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        holder.setValue(hashMap.get(k));
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Boolean> hashMap, K k, BooleanHolder booleanHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        booleanHolder.setValue(hashMap.get(k).booleanValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Byte> hashMap, K k, ByteHolder byteHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        byteHolder.setValue(hashMap.get(k).byteValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Character> hashMap, K k, CharacterHolder characterHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        characterHolder.setValue(hashMap.get(k).charValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Double> hashMap, K k, DoubleHolder doubleHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        doubleHolder.setValue(hashMap.get(k).doubleValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Float> hashMap, K k, FloatHolder floatHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        floatHolder.setValue(hashMap.get(k).floatValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Integer> hashMap, K k, IntegerHolder integerHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        integerHolder.setValue(hashMap.get(k).intValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Long> hashMap, K k, LongHolder longHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        longHolder.setValue(hashMap.get(k).longValue());
        return true;
    }

    public static <K> boolean tryGetValue(HashMap<K, Short> hashMap, K k, ShortHolder shortHolder) {
        if (!hashMap.containsKey(k)) {
            return false;
        }
        shortHolder.setValue(hashMap.get(k).shortValue());
        return true;
    }

    public static <K, V> Enumeration<AbstractMap.SimpleEntry<K, V>> getEnumerator(HashMap<K, V> hashMap) {
        return new Enumeration<AbstractMap.SimpleEntry<K, V>>(hashMap) {
            int cursor;
            K[] keys;
            final /* synthetic */ HashMap val$map;

            {
                this.val$map = r1;
                this.keys = r1.keySet().toArray();
            }

            public boolean hasMoreElements() {
                return this.cursor < this.keys.length;
            }

            public AbstractMap.SimpleEntry<K, V> nextElement() {
                K[] kArr = this.keys;
                int i = this.cursor;
                this.cursor = i + 1;
                K k = kArr[i];
                return new AbstractMap.SimpleEntry<>(k, this.val$map.get(k));
            }
        };
    }

    public static <K, V> Set<K> getAllKeys(HashMap<K, V> hashMap) {
        return hashMap.keySet();
    }
}
