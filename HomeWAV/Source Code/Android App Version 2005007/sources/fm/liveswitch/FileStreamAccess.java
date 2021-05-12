package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum FileStreamAccess {
    Read(1),
    Write(2);
    
    private static final Map<Integer, FileStreamAccess> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(FileStreamAccess.class).iterator();
        while (it.hasNext()) {
            FileStreamAccess fileStreamAccess = (FileStreamAccess) it.next();
            lookup.put(Integer.valueOf(fileStreamAccess.getAssignedValue()), fileStreamAccess);
        }
    }

    private FileStreamAccess(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static FileStreamAccess getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
