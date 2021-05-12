package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum HttpMethod {
    Get(1),
    Head(2),
    Post(3),
    Put(4),
    Patch(5),
    Delete(6);
    
    private static final Map<Integer, HttpMethod> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(HttpMethod.class).iterator();
        while (it.hasNext()) {
            HttpMethod httpMethod = (HttpMethod) it.next();
            lookup.put(Integer.valueOf(httpMethod.getAssignedValue()), httpMethod);
        }
    }

    private HttpMethod(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static HttpMethod getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
