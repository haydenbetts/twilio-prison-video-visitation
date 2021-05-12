package com.google.android.exoplayer2.text.ssa;

import android.util.Log;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue: ";
    private static final String FORMAT_LINE_PREFIX = "Format: ";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private static final String TAG = "SsaDecoder";
    private int formatEndIndex;
    private int formatKeyCount;
    private int formatStartIndex;
    private int formatTextIndex;
    private final boolean haveInitializationData;

    public SsaDecoder() {
        this((List<byte[]>) null);
    }

    public SsaDecoder(List<byte[]> list) {
        super(TAG);
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            return;
        }
        this.haveInitializationData = true;
        String str = new String(list.get(0));
        Assertions.checkArgument(str.startsWith(FORMAT_LINE_PREFIX));
        parseFormatLine(str);
        parseHeader(new ParsableByteArray(list.get(1)));
    }

    /* access modifiers changed from: protected */
    public SsaSubtitle decode(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        if (!this.haveInitializationData) {
            parseHeader(parsableByteArray);
        }
        parseEventBody(parsableByteArray, arrayList, longArray);
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SsaSubtitle(cueArr, longArray.toArray());
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private void parseHeader(com.google.android.exoplayer2.util.ParsableByteArray r3) {
        /*
            r2 = this;
        L_0x0000:
            java.lang.String r0 = r3.readLine()
            if (r0 == 0) goto L_0x000e
            java.lang.String r1 = "[Events]"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L_0x0000
        L_0x000e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.parseHeader(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    private void parseEventBody(ParsableByteArray parsableByteArray, List<Cue> list, LongArray longArray) {
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if (!this.haveInitializationData && readLine.startsWith(FORMAT_LINE_PREFIX)) {
                parseFormatLine(readLine);
            } else if (readLine.startsWith(DIALOGUE_LINE_PREFIX)) {
                parseDialogueLine(readLine, list, longArray);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseFormatLine(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 8
            java.lang.String r6 = r6.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r6 = android.text.TextUtils.split(r6, r0)
            int r0 = r6.length
            r5.formatKeyCount = r0
            r0 = -1
            r5.formatStartIndex = r0
            r5.formatEndIndex = r0
            r5.formatTextIndex = r0
            r1 = 0
            r2 = 0
        L_0x0018:
            int r3 = r5.formatKeyCount
            if (r2 >= r3) goto L_0x0061
            r3 = r6[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            r3.hashCode()
            int r4 = r3.hashCode()
            switch(r4) {
                case 100571: goto L_0x0048;
                case 3556653: goto L_0x003d;
                case 109757538: goto L_0x0032;
                default: goto L_0x0030;
            }
        L_0x0030:
            r3 = -1
            goto L_0x0052
        L_0x0032:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x003b
            goto L_0x0030
        L_0x003b:
            r3 = 2
            goto L_0x0052
        L_0x003d:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0046
            goto L_0x0030
        L_0x0046:
            r3 = 1
            goto L_0x0052
        L_0x0048:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0051
            goto L_0x0030
        L_0x0051:
            r3 = 0
        L_0x0052:
            switch(r3) {
                case 0: goto L_0x005c;
                case 1: goto L_0x0059;
                case 2: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x005e
        L_0x0056:
            r5.formatStartIndex = r2
            goto L_0x005e
        L_0x0059:
            r5.formatTextIndex = r2
            goto L_0x005e
        L_0x005c:
            r5.formatEndIndex = r2
        L_0x005e:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0061:
            int r6 = r5.formatStartIndex
            if (r6 == r0) goto L_0x006d
            int r6 = r5.formatEndIndex
            if (r6 == r0) goto L_0x006d
            int r6 = r5.formatTextIndex
            if (r6 != r0) goto L_0x006f
        L_0x006d:
            r5.formatKeyCount = r1
        L_0x006f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.parseFormatLine(java.lang.String):void");
    }

    private void parseDialogueLine(String str, List<Cue> list, LongArray longArray) {
        long j;
        if (this.formatKeyCount == 0) {
            Log.w(TAG, "Skipping dialogue line before complete format: " + str);
            return;
        }
        String[] split = str.substring(10).split(",", this.formatKeyCount);
        if (split.length != this.formatKeyCount) {
            Log.w(TAG, "Skipping dialogue line with fewer columns than format: " + str);
            return;
        }
        long parseTimecodeUs = parseTimecodeUs(split[this.formatStartIndex]);
        if (parseTimecodeUs == C.TIME_UNSET) {
            Log.w(TAG, "Skipping invalid timing: " + str);
            return;
        }
        String str2 = split[this.formatEndIndex];
        if (!str2.trim().isEmpty()) {
            j = parseTimecodeUs(str2);
            if (j == C.TIME_UNSET) {
                Log.w(TAG, "Skipping invalid timing: " + str);
                return;
            }
        } else {
            j = -9223372036854775807L;
        }
        list.add(new Cue(split[this.formatTextIndex].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        longArray.add(parseTimecodeUs);
        if (j != C.TIME_UNSET) {
            list.add((Object) null);
            longArray.add(j);
        }
    }

    public static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            return C.TIME_UNSET;
        }
        return (Long.parseLong(matcher.group(1)) * 60 * 60 * C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(2)) * 60 * C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(3)) * C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(4)) * WorkRequest.MIN_BACKOFF_MILLIS);
    }
}
