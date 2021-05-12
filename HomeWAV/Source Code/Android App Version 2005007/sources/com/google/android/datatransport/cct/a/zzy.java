package com.google.android.datatransport.cct.a;

import android.util.SparseArray;
import com.google.android.datatransport.cct.a.zzn;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public abstract class zzy {

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static abstract class zza {
        public abstract zza zza(zzb zzb);

        public abstract zza zza(zzc zzc);

        public abstract zzy zza();
    }

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzb {
        UNKNOWN_MOBILE_SUBTYPE(0),
        GPRS(1),
        EDGE(2),
        UMTS(3),
        CDMA(4),
        EVDO_0(5),
        EVDO_A(6),
        RTT(7),
        HSDPA(8),
        HSUPA(9),
        HSPA(10),
        IDEN(11),
        EVDO_B(12),
        LTE(13),
        EHRPD(14),
        HSPAP(15),
        GSM(16),
        TD_SCDMA(17),
        IWLAN(18),
        LTE_CA(19),
        COMBINED(100);
        
        private static final SparseArray<zzb> zzv = null;
        private final int zzw;

        static {
            zzb zzb = new zzb("UNKNOWN_MOBILE_SUBTYPE", 0, 0);
            UNKNOWN_MOBILE_SUBTYPE = zzb;
            zzb zzb2 = new zzb("GPRS", 1, 1);
            GPRS = zzb2;
            zzb zzb3 = new zzb("EDGE", 2, 2);
            EDGE = zzb3;
            zzb zzb4 = new zzb("UMTS", 3, 3);
            UMTS = zzb4;
            zzb zzb5 = new zzb("CDMA", 4, 4);
            CDMA = zzb5;
            zzb zzb6 = new zzb("EVDO_0", 5, 5);
            EVDO_0 = zzb6;
            zzb zzb7 = new zzb("EVDO_A", 6, 6);
            EVDO_A = zzb7;
            zzb zzb8 = new zzb("RTT", 7, 7);
            RTT = zzb8;
            zzb zzb9 = new zzb("HSDPA", 8, 8);
            HSDPA = zzb9;
            zzb zzb10 = new zzb("HSUPA", 9, 9);
            HSUPA = zzb10;
            zzb zzb11 = new zzb("HSPA", 10, 10);
            HSPA = zzb11;
            zzb zzb12 = new zzb("IDEN", 11, 11);
            IDEN = zzb12;
            zzb zzb13 = new zzb("EVDO_B", 12, 12);
            EVDO_B = zzb13;
            zzb zzb14 = new zzb("LTE", 13, 13);
            LTE = zzb14;
            zzb zzb15 = zzb14;
            zzb zzb16 = new zzb("EHRPD", 14, 14);
            EHRPD = zzb16;
            zzb zzb17 = zzb16;
            zzb zzb18 = new zzb("HSPAP", 15, 15);
            HSPAP = zzb18;
            zzb zzb19 = zzb18;
            zzb zzb20 = new zzb("GSM", 16, 16);
            GSM = zzb20;
            zzb zzb21 = zzb20;
            zzb zzb22 = new zzb("TD_SCDMA", 17, 17);
            TD_SCDMA = zzb22;
            zzb zzb23 = new zzb("IWLAN", 18, 18);
            IWLAN = zzb23;
            zzb zzb24 = zzb23;
            zzb zzb25 = new zzb("LTE_CA", 19, 19);
            LTE_CA = zzb25;
            COMBINED = new zzb("COMBINED", 20, 100);
            SparseArray<zzb> sparseArray = new SparseArray<>();
            zzv = sparseArray;
            sparseArray.put(0, zzb);
            sparseArray.put(1, zzb2);
            sparseArray.put(2, zzb3);
            sparseArray.put(3, zzb4);
            sparseArray.put(4, zzb5);
            sparseArray.put(5, zzb6);
            sparseArray.put(6, zzb7);
            sparseArray.put(7, zzb8);
            sparseArray.put(8, zzb9);
            sparseArray.put(9, zzb10);
            sparseArray.put(10, zzb11);
            sparseArray.put(11, zzb12);
            sparseArray.put(12, zzb13);
            sparseArray.put(13, zzb15);
            sparseArray.put(14, zzb17);
            sparseArray.put(15, zzb19);
            sparseArray.put(16, zzb21);
            sparseArray.put(17, zzb22);
            sparseArray.put(18, zzb24);
            sparseArray.put(19, zzb25);
        }

        private zzb(int i) {
            this.zzw = i;
        }

        public int zza() {
            return this.zzw;
        }

        public static zzb zza(int i) {
            return zzv.get(i);
        }
    }

    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzc {
        MOBILE(0),
        WIFI(1),
        MOBILE_MMS(2),
        MOBILE_SUPL(3),
        MOBILE_DUN(4),
        MOBILE_HIPRI(5),
        WIMAX(6),
        BLUETOOTH(7),
        DUMMY(8),
        ETHERNET(9),
        MOBILE_FOTA(10),
        MOBILE_IMS(11),
        MOBILE_CBS(12),
        WIFI_P2P(13),
        MOBILE_IA(14),
        MOBILE_EMERGENCY(15),
        PROXY(16),
        VPN(17),
        NONE(-1);
        
        private static final SparseArray<zzc> zzt = null;
        private final int zzu;

        static {
            zzc zzc = new zzc("MOBILE", 0, 0);
            MOBILE = zzc;
            zzc zzc2 = new zzc("WIFI", 1, 1);
            WIFI = zzc2;
            zzc zzc3 = new zzc("MOBILE_MMS", 2, 2);
            MOBILE_MMS = zzc3;
            zzc zzc4 = new zzc("MOBILE_SUPL", 3, 3);
            MOBILE_SUPL = zzc4;
            zzc zzc5 = new zzc("MOBILE_DUN", 4, 4);
            MOBILE_DUN = zzc5;
            zzc zzc6 = new zzc("MOBILE_HIPRI", 5, 5);
            MOBILE_HIPRI = zzc6;
            zzc zzc7 = new zzc("WIMAX", 6, 6);
            WIMAX = zzc7;
            zzc zzc8 = new zzc("BLUETOOTH", 7, 7);
            BLUETOOTH = zzc8;
            zzc zzc9 = new zzc("DUMMY", 8, 8);
            DUMMY = zzc9;
            zzc zzc10 = new zzc("ETHERNET", 9, 9);
            ETHERNET = zzc10;
            zzc zzc11 = new zzc("MOBILE_FOTA", 10, 10);
            MOBILE_FOTA = zzc11;
            zzc zzc12 = new zzc("MOBILE_IMS", 11, 11);
            MOBILE_IMS = zzc12;
            zzc zzc13 = new zzc("MOBILE_CBS", 12, 12);
            MOBILE_CBS = zzc13;
            zzc zzc14 = new zzc("WIFI_P2P", 13, 13);
            WIFI_P2P = zzc14;
            zzc zzc15 = zzc14;
            zzc zzc16 = new zzc("MOBILE_IA", 14, 14);
            MOBILE_IA = zzc16;
            zzc zzc17 = zzc16;
            zzc zzc18 = new zzc("MOBILE_EMERGENCY", 15, 15);
            MOBILE_EMERGENCY = zzc18;
            zzc zzc19 = zzc18;
            zzc zzc20 = new zzc("PROXY", 16, 16);
            PROXY = zzc20;
            zzc zzc21 = new zzc("VPN", 17, 17);
            VPN = zzc21;
            zzc zzc22 = zzc21;
            zzc zzc23 = new zzc("NONE", 18, -1);
            NONE = zzc23;
            SparseArray<zzc> sparseArray = new SparseArray<>();
            zzt = sparseArray;
            sparseArray.put(0, zzc);
            sparseArray.put(1, zzc2);
            sparseArray.put(2, zzc3);
            sparseArray.put(3, zzc4);
            sparseArray.put(4, zzc5);
            sparseArray.put(5, zzc6);
            sparseArray.put(6, zzc7);
            sparseArray.put(7, zzc8);
            sparseArray.put(8, zzc9);
            sparseArray.put(9, zzc10);
            sparseArray.put(10, zzc11);
            sparseArray.put(11, zzc12);
            sparseArray.put(12, zzc13);
            sparseArray.put(13, zzc15);
            sparseArray.put(14, zzc17);
            sparseArray.put(15, zzc19);
            sparseArray.put(16, zzc20);
            sparseArray.put(17, zzc22);
            sparseArray.put(-1, zzc23);
        }

        private zzc(int i) {
            this.zzu = i;
        }

        public int zza() {
            return this.zzu;
        }

        public static zzc zza(int i) {
            return zzt.get(i);
        }
    }

    public static zza zza() {
        return new zzn.zza();
    }
}
