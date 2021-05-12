package com.google.android.datatransport.cct.a;

import android.util.SparseArray;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public enum zzaa {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    
    private static final SparseArray<zzaa> zzg = null;

    static {
        zzaa zzaa = new zzaa("DEFAULT", 0, 0);
        DEFAULT = zzaa;
        zzaa zzaa2 = new zzaa("UNMETERED_ONLY", 1, 1);
        UNMETERED_ONLY = zzaa2;
        zzaa zzaa3 = new zzaa("UNMETERED_OR_DAILY", 2, 2);
        UNMETERED_OR_DAILY = zzaa3;
        zzaa zzaa4 = new zzaa("FAST_IF_RADIO_AWAKE", 3, 3);
        FAST_IF_RADIO_AWAKE = zzaa4;
        zzaa zzaa5 = new zzaa("NEVER", 4, 4);
        NEVER = zzaa5;
        zzaa zzaa6 = new zzaa("UNRECOGNIZED", 5, -1);
        UNRECOGNIZED = zzaa6;
        SparseArray<zzaa> sparseArray = new SparseArray<>();
        zzg = sparseArray;
        sparseArray.put(0, zzaa);
        sparseArray.put(1, zzaa2);
        sparseArray.put(2, zzaa3);
        sparseArray.put(3, zzaa4);
        sparseArray.put(4, zzaa5);
        sparseArray.put(-1, zzaa6);
    }

    private zzaa(int i) {
    }
}
