package com.google.android.datatransport.cct;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedDestination;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public final class CCTDestination implements EncodedDestination {
    public static final CCTDestination INSTANCE;
    public static final CCTDestination LEGACY_INSTANCE;
    static final String zza;
    static final String zzb;
    private static final String zzc;
    private static final Set<Encoding> zzd = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Encoding[]{Encoding.of("proto"), Encoding.of("json")})));
    private final String zze;
    private final String zzf;

    static {
        String zza2 = zzd.zza("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        zza = zza2;
        String zza3 = zzd.zza("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        zzb = zza3;
        String zza4 = zzd.zza("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        zzc = zza4;
        INSTANCE = new CCTDestination(zza2, (String) null);
        LEGACY_INSTANCE = new CCTDestination(zza3, zza4);
    }

    public CCTDestination(String str, String str2) {
        this.zze = str;
        this.zzf = str2;
    }

    public static CCTDestination fromByteArray(byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (str.startsWith("1$")) {
            String[] split = str.substring(2).split(Pattern.quote("\\"), 2);
            if (split.length == 2) {
                String str2 = split[0];
                if (!str2.isEmpty()) {
                    String str3 = split[1];
                    if (str3.isEmpty()) {
                        str3 = null;
                    }
                    return new CCTDestination(str2, str3);
                }
                throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
            }
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        throw new IllegalArgumentException("Version marker missing from extras");
    }

    public byte[] asByteArray() {
        String str = this.zzf;
        if (str == null && this.zze == null) {
            return null;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "1$";
        objArr[1] = this.zze;
        objArr[2] = "\\";
        if (str == null) {
            str = "";
        }
        objArr[3] = str;
        return String.format("%s%s%s%s", objArr).getBytes(Charset.forName("UTF-8"));
    }

    public String getAPIKey() {
        return this.zzf;
    }

    public String getEndPoint() {
        return this.zze;
    }

    public byte[] getExtras() {
        return asByteArray();
    }

    public String getName() {
        return "cct";
    }

    public Set<Encoding> getSupportedEncodings() {
        return zzd;
    }
}
