package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SourceLanguage {
    CSharp(1),
    Java(2),
    ObjectiveC(3),
    TypeScript(4);
    
    private static final Map<Integer, SourceLanguage> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(SourceLanguage.class).iterator();
        while (it.hasNext()) {
            SourceLanguage sourceLanguage = (SourceLanguage) it.next();
            lookup.put(Integer.valueOf(sourceLanguage.getAssignedValue()), sourceLanguage);
        }
    }

    private SourceLanguage(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static SourceLanguage getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
