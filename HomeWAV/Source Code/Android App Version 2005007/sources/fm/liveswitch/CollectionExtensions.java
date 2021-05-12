package fm.liveswitch;

import java.util.Collection;

public class CollectionExtensions {
    public static <T> int getCount(Collection<T> collection) {
        return collection.size();
    }
}
