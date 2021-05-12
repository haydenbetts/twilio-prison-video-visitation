package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MpdParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    /* access modifiers changed from: protected */
    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public DashManifestParser() {
        this((String) null);
    }

    public DashManifestParser(String str) {
        this.contentId = str;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            if (newPullParser.next() == 2 && "MPD".equals(newPullParser.getName())) {
                return parseMediaPresentationDescription(newPullParser, uri.toString());
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0164 A[LOOP:0: B:20:0x006d->B:63:0x0164, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r32, java.lang.String r33) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r31 = this;
            r0 = r32
            java.lang.String r1 = "availabilityStartTime"
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r5 = parseDateTime(r0, r1, r2)
            java.lang.String r1 = "mediaPresentationDuration"
            long r7 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "minBufferTime"
            long r9 = parseDuration(r0, r1, r2)
            r1 = 0
            java.lang.String r4 = "type"
            java.lang.String r4 = r0.getAttributeValue(r1, r4)
            r12 = 0
            if (r4 == 0) goto L_0x002e
            java.lang.String r13 = "dynamic"
            boolean r4 = r4.equals(r13)
            if (r4 == 0) goto L_0x002e
            r13 = 1
            goto L_0x002f
        L_0x002e:
            r13 = 0
        L_0x002f:
            if (r13 == 0) goto L_0x0038
            java.lang.String r4 = "minimumUpdatePeriod"
            long r14 = parseDuration(r0, r4, r2)
            goto L_0x0039
        L_0x0038:
            r14 = r2
        L_0x0039:
            if (r13 == 0) goto L_0x0042
            java.lang.String r4 = "timeShiftBufferDepth"
            long r16 = parseDuration(r0, r4, r2)
            goto L_0x0044
        L_0x0042:
            r16 = r2
        L_0x0044:
            if (r13 == 0) goto L_0x004d
            java.lang.String r4 = "suggestedPresentationDelay"
            long r18 = parseDuration(r0, r4, r2)
            goto L_0x004f
        L_0x004d:
            r18 = r2
        L_0x004f:
            java.lang.String r4 = "publishTime"
            long r20 = parseDateTime(r0, r4, r2)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r13 == 0) goto L_0x005f
            r22 = r2
            goto L_0x0061
        L_0x005f:
            r22 = 0
        L_0x0061:
            r11 = r22
            r23 = 0
            r24 = 0
            r22 = r1
            r1 = r33
            r33 = r22
        L_0x006d:
            r32.next()
            java.lang.String r2 = "BaseURL"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x008a
            if (r23 != 0) goto L_0x0084
            java.lang.String r1 = parseBaseUrl(r0, r1)
            r2 = r33
            r23 = 1
            goto L_0x011e
        L_0x0084:
            r27 = r1
            r28 = r11
            goto L_0x0118
        L_0x008a:
            java.lang.String r2 = "UTCTiming"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x0098
            com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r2 = r31.parseUtcTiming(r32)
            goto L_0x011e
        L_0x0098:
            java.lang.String r2 = "Location"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x00ae
            java.lang.String r2 = r32.nextText()
            android.net.Uri r2 = android.net.Uri.parse(r2)
            r22 = r2
        L_0x00aa:
            r2 = r33
            goto L_0x011e
        L_0x00ae:
            java.lang.String r2 = "Period"
            boolean r2 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r2)
            if (r2 == 0) goto L_0x0084
            if (r24 != 0) goto L_0x0084
            r2 = r31
            android.util.Pair r3 = r2.parsePeriod(r0, r1, r11)
            r27 = r1
            java.lang.Object r1 = r3.first
            com.google.android.exoplayer2.source.dash.manifest.Period r1 = (com.google.android.exoplayer2.source.dash.manifest.Period) r1
            r28 = r11
            long r11 = r1.startMs
            r25 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r30 = (r11 > r25 ? 1 : (r11 == r25 ? 0 : -1))
            if (r30 != 0) goto L_0x00f7
            if (r13 == 0) goto L_0x00dc
            r2 = r33
            r1 = r27
            r11 = r28
            r24 = 1
            goto L_0x011e
        L_0x00dc:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Unable to determine start of period "
            r1.append(r3)
            int r3 = r4.size()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x00f7:
            java.lang.Object r3 = r3.second
            java.lang.Long r3 = (java.lang.Long) r3
            long r11 = r3.longValue()
            r25 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r11 > r25 ? 1 : (r11 == r25 ? 0 : -1))
            if (r3 != 0) goto L_0x010e
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0111
        L_0x010e:
            long r2 = r1.startMs
            long r2 = r2 + r11
        L_0x0111:
            r4.add(r1)
            r11 = r2
            r1 = r27
            goto L_0x00aa
        L_0x0118:
            r2 = r33
            r1 = r27
            r11 = r28
        L_0x011e:
            java.lang.String r3 = "MPD"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r3)
            r25 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r3 == 0) goto L_0x0164
            int r0 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1))
            if (r0 != 0) goto L_0x0140
            int r0 = (r11 > r25 ? 1 : (r11 == r25 ? 0 : -1))
            if (r0 == 0) goto L_0x0135
            r7 = r11
            goto L_0x0140
        L_0x0135:
            if (r13 == 0) goto L_0x0138
            goto L_0x0140
        L_0x0138:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Unable to determine duration of static manifest."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0140:
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x015c
            r3 = r4
            r4 = r31
            r11 = r13
            r12 = r14
            r14 = r16
            r16 = r18
            r18 = r20
            r20 = r2
            r21 = r22
            r22 = r3
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.buildMediaPresentationDescription(r5, r7, r9, r11, r12, r14, r16, r18, r20, r21, r22)
            return r0
        L_0x015c:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "No periods found."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0164:
            r33 = r2
            r2 = r25
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    /* access modifiers changed from: protected */
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, UtcTimingElement utcTimingElement, Uri uri, List<Period> list) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, j7, utcTimingElement, uri, list);
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue((String) null, "schemeIdUri"), xmlPullParser.getAttributeValue((String) null, CommonProperties.VALUE));
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* access modifiers changed from: protected */
    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "id");
        long parseDuration = parseDuration(xmlPullParser, TtmlNode.START, j);
        long parseDuration2 = parseDuration(xmlPullParser, "duration", C.TIME_UNSET);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        SegmentBase segmentBase = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentBase));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "EventStream")) {
                arrayList2.add(parseEventStream(xmlPullParser));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentBase = parseSegmentBase(xmlPullParser, (SegmentBase.SingleSegmentBase) null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentBase = parseSegmentList(xmlPullParser, (SegmentBase.SegmentList) null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentBase = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) null);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, parseDuration, arrayList, arrayList2), Long.valueOf(parseDuration2));
    }

    /* access modifiers changed from: protected */
    public Period buildPeriod(String str, long j, List<AdaptationSet> list, List<EventStream> list2) {
        return new Period(str, j, list, list2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v5, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x022c A[LOOP:0: B:1:0x0064->B:59:0x022c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01f3 A[EDGE_INSN: B:60:0x01f3->B:53:0x01f3 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r39, java.lang.String r40, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r41) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r38 = this;
            r14 = r38
            r15 = r39
            java.lang.String r0 = "id"
            r1 = -1
            int r16 = parseInt(r15, r0, r1)
            int r0 = r38.parseContentType(r39)
            r13 = 0
            java.lang.String r2 = "mimeType"
            java.lang.String r17 = r15.getAttributeValue(r13, r2)
            java.lang.String r2 = "codecs"
            java.lang.String r18 = r15.getAttributeValue(r13, r2)
            java.lang.String r2 = "width"
            int r19 = parseInt(r15, r2, r1)
            java.lang.String r2 = "height"
            int r20 = parseInt(r15, r2, r1)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r21 = parseFrameRate(r15, r2)
            java.lang.String r2 = "audioSamplingRate"
            int r22 = parseInt(r15, r2, r1)
            java.lang.String r12 = "lang"
            java.lang.String r2 = r15.getAttributeValue(r13, r12)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r23 = 0
            r6 = r40
            r24 = r41
            r5 = r0
            r4 = r2
            r28 = r13
            r25 = -1
            r26 = 0
            r27 = 0
        L_0x0064:
            r39.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x009d
            if (r26 != 0) goto L_0x0089
            java.lang.String r0 = parseBaseUrl(r15, r6)
            r26 = 1
            r30 = r0
        L_0x0079:
            r6 = r7
            r32 = r8
            r33 = r9
            r7 = r10
            r35 = r11
            r36 = r12
            r37 = r13
            r1 = r15
            r8 = r5
            goto L_0x01eb
        L_0x0089:
            r29 = r4
            r2 = r5
            r30 = r6
            r6 = r7
            r32 = r8
            r33 = r9
            r7 = r10
            r35 = r11
            r36 = r12
            r37 = r13
            r1 = r15
            goto L_0x01e8
        L_0x009d:
            java.lang.String r0 = "ContentProtection"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00bd
            android.util.Pair r0 = r38.parseContentProtection(r39)
            java.lang.Object r1 = r0.first
            if (r1 == 0) goto L_0x00b3
            java.lang.Object r1 = r0.first
            r28 = r1
            java.lang.String r28 = (java.lang.String) r28
        L_0x00b3:
            java.lang.Object r1 = r0.second
            if (r1 == 0) goto L_0x00f6
            java.lang.Object r0 = r0.second
            r11.add(r0)
            goto L_0x00f6
        L_0x00bd:
            java.lang.String r0 = "ContentComponent"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00e8
            java.lang.String r0 = r15.getAttributeValue(r13, r12)
            java.lang.String r0 = checkLanguageConsistency(r4, r0)
            int r1 = r38.parseContentType(r39)
            int r1 = checkContentTypeConsistency(r5, r1)
            r4 = r0
            r30 = r6
            r6 = r7
            r32 = r8
            r33 = r9
            r7 = r10
            r35 = r11
            r36 = r12
            r37 = r13
            r8 = r1
            r1 = r15
            goto L_0x01eb
        L_0x00e8:
            java.lang.String r0 = "Role"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00f9
            int r0 = r38.parseRole(r39)
            r27 = r27 | r0
        L_0x00f6:
            r30 = r6
            goto L_0x0079
        L_0x00f9:
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x0108
            int r0 = r38.parseAudioChannelConfiguration(r39)
            r25 = r0
            goto L_0x00f6
        L_0x0108:
            java.lang.String r0 = "Accessibility"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r1 == 0) goto L_0x0119
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r15, r0)
            r9.add(r0)
            goto L_0x0089
        L_0x0119:
            java.lang.String r0 = "SupplementalProperty"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r1 == 0) goto L_0x012a
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r15, r0)
            r8.add(r0)
            goto L_0x0089
        L_0x012a:
            java.lang.String r0 = "Representation"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x017c
            r0 = r38
            r1 = r39
            r2 = r6
            r3 = r17
            r29 = r4
            r4 = r18
            r15 = r5
            r5 = r19
            r30 = r6
            r6 = r20
            r31 = r7
            r7 = r21
            r32 = r8
            r8 = r25
            r33 = r9
            r9 = r22
            r34 = r10
            r10 = r29
            r35 = r11
            r11 = r27
            r36 = r12
            r12 = r33
            r37 = r13
            r13 = r24
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.parseRepresentation(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            com.google.android.exoplayer2.Format r1 = r0.format
            int r1 = r14.getContentType(r1)
            int r1 = checkContentTypeConsistency(r15, r1)
            r6 = r31
            r6.add(r0)
            r8 = r1
            r4 = r29
            r7 = r34
            r1 = r39
            goto L_0x01eb
        L_0x017c:
            r29 = r4
            r15 = r5
            r30 = r6
            r6 = r7
            r32 = r8
            r33 = r9
            r34 = r10
            r35 = r11
            r36 = r12
            r37 = r13
            java.lang.String r0 = "SegmentBase"
            r1 = r39
            r2 = r15
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x01a9
            r0 = r24
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = r14.parseSegmentBase(r1, r0)
        L_0x01a1:
            r24 = r0
            r8 = r2
            r4 = r29
            r7 = r34
            goto L_0x01eb
        L_0x01a9:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x01ba
            r0 = r24
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = r14.parseSegmentList(r1, r0)
            goto L_0x01a1
        L_0x01ba:
            java.lang.String r0 = "SegmentTemplate"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x01cb
            r0 = r24
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = r14.parseSegmentTemplate(r1, r0)
            goto L_0x01a1
        L_0x01cb:
            java.lang.String r0 = "InbandEventStream"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r3 == 0) goto L_0x01dd
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r1, r0)
            r7 = r34
            r7.add(r0)
            goto L_0x01e8
        L_0x01dd:
            r7 = r34
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r39)
            if (r0 == 0) goto L_0x01e8
            r38.parseAdaptationSetChild(r39)
        L_0x01e8:
            r8 = r2
            r4 = r29
        L_0x01eb:
            java.lang.String r0 = "AdaptationSet"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r1, r0)
            if (r0 == 0) goto L_0x022c
            java.util.ArrayList r9 = new java.util.ArrayList
            int r0 = r6.size()
            r9.<init>(r0)
            r10 = 0
        L_0x01fd:
            int r0 = r6.size()
            if (r10 >= r0) goto L_0x021d
            java.lang.Object r0 = r6.get(r10)
            r1 = r0
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r1 = (com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo) r1
            java.lang.String r2 = r14.contentId
            r0 = r38
            r3 = r28
            r4 = r35
            r5 = r7
            com.google.android.exoplayer2.source.dash.manifest.Representation r0 = r0.buildRepresentation(r1, r2, r3, r4, r5)
            r9.add(r0)
            int r10 = r10 + 1
            goto L_0x01fd
        L_0x021d:
            r0 = r38
            r1 = r16
            r2 = r8
            r3 = r9
            r4 = r33
            r5 = r32
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r0 = r0.buildAdaptationSet(r1, r2, r3, r4, r5)
            return r0
        L_0x022c:
            r15 = r1
            r10 = r7
            r5 = r8
            r8 = r32
            r9 = r33
            r11 = r35
            r12 = r36
            r13 = r37
            r7 = r6
            r6 = r30
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* access modifiers changed from: protected */
    public AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3) {
        return new AdaptationSet(i, i2, list, list2, list3);
    }

    /* access modifiers changed from: protected */
    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getContentType(Format format) {
        String str = format.sampleMimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (mimeTypeIsRawText(str)) {
            return 3;
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v33, resolved type: java.util.UUID} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v34, resolved type: java.util.UUID} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0085, code lost:
        r1 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ac, code lost:
        if (r4 != null) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b5, code lost:
        if (com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, "cenc:pssh") == false) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bb, code lost:
        if (r10.next() != 4) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        r4 = android.util.Base64.decode(r10.getText(), 0);
        r5 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.parseUuid(r4);
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c9, code lost:
        if (r5 != null) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00cb, code lost:
        android.util.Log.w(TAG, "Skipping malformed cenc:pssh data");
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d4, code lost:
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00da, code lost:
        if (com.google.android.exoplayer2.C.PLAYREADY_UUID.equals(r5) == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00dc, code lost:
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        if (com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, "mspr:pro") == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e4, code lost:
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e8, code lost:
        if (r10.next() != 4) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ea, code lost:
        r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(com.google.android.exoplayer2.C.PLAYREADY_UUID, android.util.Base64.decode(r10.getText(), 0));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.lang.String, com.google.android.exoplayer2.drm.DrmInitData.SchemeData> parseContentProtection(org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r1 = "schemeIdUri"
            java.lang.String r1 = r10.getAttributeValue(r0, r1)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0088
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r1)
            r1.hashCode()
            r4 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case 489446379: goto L_0x0033;
                case 755418770: goto L_0x0027;
                case 1812765994: goto L_0x001b;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x003e
        L_0x001b:
            java.lang.String r5 = "urn:mpeg:dash:mp4protection:2011"
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x0025
            goto L_0x003e
        L_0x0025:
            r4 = 2
            goto L_0x003e
        L_0x0027:
            java.lang.String r5 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x0031
            goto L_0x003e
        L_0x0031:
            r4 = 1
            goto L_0x003e
        L_0x0033:
            java.lang.String r5 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            switch(r4) {
                case 0: goto L_0x0083;
                case 1: goto L_0x0080;
                case 2: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x0088
        L_0x0042:
            java.lang.String r1 = "value"
            java.lang.String r1 = r10.getAttributeValue(r0, r1)
            java.lang.String r4 = "cenc:default_KID"
            java.lang.String r4 = r10.getAttributeValue(r0, r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x007e
            java.lang.String r5 = "00000000-0000-0000-0000-000000000000"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x007e
            java.lang.String r5 = "\\s+"
            java.lang.String[] r4 = r4.split(r5)
            int r5 = r4.length
            java.util.UUID[] r5 = new java.util.UUID[r5]
            r6 = 0
        L_0x0067:
            int r7 = r4.length
            if (r6 >= r7) goto L_0x0075
            r7 = r4[r6]
            java.util.UUID r7 = java.util.UUID.fromString(r7)
            r5[r6] = r7
            int r6 = r6 + 1
            goto L_0x0067
        L_0x0075:
            java.util.UUID r4 = com.google.android.exoplayer2.C.COMMON_PSSH_UUID
            byte[] r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r4, r5, r0)
            java.util.UUID r5 = com.google.android.exoplayer2.C.COMMON_PSSH_UUID
            goto L_0x008b
        L_0x007e:
            r4 = r0
            goto L_0x008a
        L_0x0080:
            java.util.UUID r5 = com.google.android.exoplayer2.C.WIDEVINE_UUID
            goto L_0x0085
        L_0x0083:
            java.util.UUID r5 = com.google.android.exoplayer2.C.PLAYREADY_UUID
        L_0x0085:
            r1 = r0
            r4 = r1
            goto L_0x008b
        L_0x0088:
            r1 = r0
            r4 = r1
        L_0x008a:
            r5 = r4
        L_0x008b:
            r6 = 0
        L_0x008c:
            r10.next()
            java.lang.String r7 = "widevine:license"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x00ac
            java.lang.String r6 = "robustness_level"
            java.lang.String r6 = r10.getAttributeValue(r0, r6)
            if (r6 == 0) goto L_0x00aa
            java.lang.String r7 = "HW"
            boolean r6 = r6.startsWith(r7)
            if (r6 == 0) goto L_0x00aa
            r6 = 1
            goto L_0x00f8
        L_0x00aa:
            r6 = 0
            goto L_0x00f8
        L_0x00ac:
            if (r4 != 0) goto L_0x00f8
            java.lang.String r7 = "cenc:pssh"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r7)
            r8 = 4
            if (r7 == 0) goto L_0x00d4
            int r7 = r10.next()
            if (r7 != r8) goto L_0x00d4
            java.lang.String r4 = r10.getText()
            byte[] r4 = android.util.Base64.decode(r4, r3)
            java.util.UUID r5 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.parseUuid(r4)
            if (r5 != 0) goto L_0x00f8
            java.lang.String r4 = "MpdParser"
            java.lang.String r7 = "Skipping malformed cenc:pssh data"
            android.util.Log.w(r4, r7)
            r4 = r0
            goto L_0x00f8
        L_0x00d4:
            java.util.UUID r7 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            boolean r7 = r7.equals(r5)
            if (r7 == 0) goto L_0x00f8
            java.lang.String r7 = "mspr:pro"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x00f8
            int r7 = r10.next()
            if (r7 != r8) goto L_0x00f8
            java.util.UUID r4 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            java.lang.String r7 = r10.getText()
            byte[] r7 = android.util.Base64.decode(r7, r3)
            byte[] r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r4, r7)
        L_0x00f8:
            java.lang.String r7 = "ContentProtection"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r10, r7)
            if (r7 == 0) goto L_0x008c
            if (r5 == 0) goto L_0x010a
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
            java.lang.String r10 = "video/mp4"
            r0.<init>(r5, r10, r4, r6)
        L_0x010a:
            android.util.Pair r10 = android.util.Pair.create(r1, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public int parseRole(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", (String) null);
        String parseString2 = parseString(xmlPullParser, CommonProperties.VALUE, (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Role"));
        return (!"urn:mpeg:dash:role:2011".equals(parseString) || !"main".equals(parseString2)) ? 0 : 1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v8, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0159 A[LOOP:0: B:1:0x005b->B:43:0x0159, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011f A[EDGE_INSN: B:44:0x011f->B:37:0x011f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26, int r27, float r28, int r29, int r30, java.lang.String r31, int r32, java.util.List<com.google.android.exoplayer2.source.dash.manifest.Descriptor> r33, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r21 = this;
            r14 = r21
            r0 = r22
            r1 = 0
            java.lang.String r2 = "id"
            java.lang.String r2 = r0.getAttributeValue(r1, r2)
            java.lang.String r3 = "bandwidth"
            r4 = -1
            int r8 = parseInt(r0, r3, r4)
            java.lang.String r3 = "mimeType"
            r4 = r24
            java.lang.String r3 = parseString(r0, r3, r4)
            java.lang.String r4 = "codecs"
            r5 = r25
            java.lang.String r12 = parseString(r0, r4, r5)
            java.lang.String r4 = "width"
            r5 = r26
            int r4 = parseInt(r0, r4, r5)
            java.lang.String r5 = "height"
            r6 = r27
            int r5 = parseInt(r0, r5, r6)
            r6 = r28
            float r6 = parseFrameRate(r0, r6)
            java.lang.String r7 = "audioSamplingRate"
            r9 = r30
            int r7 = parseInt(r0, r7, r9)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r9 = 0
            r9 = r29
            r10 = r34
            r16 = r1
            r17 = 0
            r1 = r23
        L_0x005b:
            r22.next()
            r23 = r9
            java.lang.String r9 = "BaseURL"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x007e
            if (r17 != 0) goto L_0x007a
            java.lang.String r1 = parseBaseUrl(r0, r1)
            r9 = 1
            r9 = r23
            r17 = r10
            r18 = r16
            r16 = r1
            r1 = 1
            goto L_0x0117
        L_0x007a:
            r24 = r1
            goto L_0x010d
        L_0x007e:
            java.lang.String r9 = "AudioChannelConfiguration"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x0092
            int r9 = r21.parseAudioChannelConfiguration(r22)
            r18 = r16
            r16 = r1
            r1 = r17
            goto L_0x0115
        L_0x0092:
            java.lang.String r9 = "SegmentBase"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00ac
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r9 = r14.parseSegmentBase(r0, r10)
        L_0x00a0:
            r18 = r16
            r16 = r1
            r1 = r17
            r17 = r9
            r9 = r23
            goto L_0x0117
        L_0x00ac:
            java.lang.String r9 = "SegmentList"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00bb
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r9 = r14.parseSegmentList(r0, r10)
            goto L_0x00a0
        L_0x00bb:
            java.lang.String r9 = "SegmentTemplate"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00ca
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r9 = r14.parseSegmentTemplate(r0, r10)
            goto L_0x00a0
        L_0x00ca:
            java.lang.String r9 = "ContentProtection"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00ec
            android.util.Pair r9 = r21.parseContentProtection(r22)
            r24 = r1
            java.lang.Object r1 = r9.first
            if (r1 == 0) goto L_0x00e2
            java.lang.Object r1 = r9.first
            r16 = r1
            java.lang.String r16 = (java.lang.String) r16
        L_0x00e2:
            java.lang.Object r1 = r9.second
            if (r1 == 0) goto L_0x010d
            java.lang.Object r1 = r9.second
            r15.add(r1)
            goto L_0x010d
        L_0x00ec:
            r24 = r1
            java.lang.String r1 = "InbandEventStream"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r9 == 0) goto L_0x00fe
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = parseDescriptor(r0, r1)
            r13.add(r1)
            goto L_0x010d
        L_0x00fe:
            java.lang.String r1 = "SupplementalProperty"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r9 == 0) goto L_0x010d
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = parseDescriptor(r0, r1)
            r11.add(r1)
        L_0x010d:
            r9 = r23
            r18 = r16
            r1 = r17
            r16 = r24
        L_0x0115:
            r17 = r10
        L_0x0117:
            java.lang.String r10 = "Representation"
            boolean r10 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r10)
            if (r10 == 0) goto L_0x0159
            r0 = r21
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r9
            r9 = r31
            r10 = r32
            r19 = r11
            r11 = r33
            r20 = r13
            r13 = r19
            com.google.android.exoplayer2.Format r0 = r0.buildFormat(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            if (r17 == 0) goto L_0x013c
            r1 = r17
            goto L_0x0141
        L_0x013c:
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r1 = new com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
        L_0x0141:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r2 = new com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo
            r3 = -1
            r22 = r2
            r23 = r0
            r24 = r16
            r25 = r1
            r26 = r18
            r27 = r15
            r28 = r20
            r29 = r3
            r22.<init>(r23, r24, r25, r26, r27, r28, r29)
            return r2
        L_0x0159:
            r10 = r17
            r17 = r1
            r1 = r16
            r16 = r18
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, int i6, List<Descriptor> list, String str4, List<Descriptor> list2) {
        String str5;
        int i7;
        int parseCea708AccessibilityChannel;
        String str6 = str2;
        String sampleMimeType = getSampleMimeType(str2, str4);
        if (sampleMimeType != null) {
            if (MimeTypes.AUDIO_E_AC3.equals(sampleMimeType)) {
                sampleMimeType = parseEac3SupplementalProperties(list2);
            }
            str5 = sampleMimeType;
            if (MimeTypes.isVideo(str5)) {
                return Format.createVideoContainerFormat(str, str2, str5, str4, i5, i, i2, f, (List<byte[]>) null, i6);
            }
            if (MimeTypes.isAudio(str5)) {
                return Format.createAudioContainerFormat(str, str2, str5, str4, i5, i3, i4, (List<byte[]>) null, i6, str3);
            }
            if (mimeTypeIsRawText(str5)) {
                if (MimeTypes.APPLICATION_CEA608.equals(str5)) {
                    parseCea708AccessibilityChannel = parseCea608AccessibilityChannel(list);
                } else if (MimeTypes.APPLICATION_CEA708.equals(str5)) {
                    parseCea708AccessibilityChannel = parseCea708AccessibilityChannel(list);
                } else {
                    i7 = -1;
                    return Format.createTextContainerFormat(str, str2, str5, str4, i5, i6, str3, i7);
                }
                i7 = parseCea708AccessibilityChannel;
                return Format.createTextContainerFormat(str, str2, str5, str4, i5, i6, str3, i7);
            }
        } else {
            str5 = sampleMimeType;
        }
        return Format.createContainerFormat(str, str2, str5, str4, i5, i6, str3);
    }

    /* access modifiers changed from: protected */
    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format format = representationInfo.format;
        if (representationInfo.drmSchemeType != null) {
            str2 = representationInfo.drmSchemeType;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            filterRedundantIncompleteSchemeDatas(arrayList3);
            format = format.copyWithDrmInitData(new DrmInitData(str2, (List<DrmInitData.SchemeData>) arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(str, representationInfo.revisionId, format, representationInfo.baseUrl, representationInfo.segmentBase, arrayList4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long parseLong = parseLong(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.timescale : 1);
        long j3 = 0;
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.presentationTimeOffset : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.indexStart : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.indexLength;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            long parseLong3 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - parseLong3) + 1;
            j2 = parseLong3;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.initialization;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentBase"));
        return buildSingleSegmentBase(rangedUri, parseLong, parseLong2, j2, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentList segmentList2 = segmentList;
        long j = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentList2 != null ? segmentList2.duration : C.TIME_UNSET);
        if (segmentList2 != null) {
            j = segmentList2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j);
        List list = null;
        RangedUri rangedUri = null;
        List<SegmentBase.SegmentTimelineElement> list2 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list2 = parseSegmentTimeline(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentURL")) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(parseSegmentUrl(xmlPullParser));
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentList2.initialization;
            }
            if (list2 == null) {
                list2 = segmentList2.segmentTimeline;
            }
            if (list == null) {
                list = segmentList2.mediaSegments;
            }
        }
        return buildSegmentList(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list2, list);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, j3, j4, list, list2);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentTemplate segmentTemplate2 = segmentTemplate;
        long j = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentTemplate2 != null ? segmentTemplate2.duration : C.TIME_UNSET);
        if (segmentTemplate2 != null) {
            j = segmentTemplate2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j);
        RangedUri rangedUri = null;
        UrlTemplate parseUrlTemplate = parseUrlTemplate(xmlPullParser2, "media", segmentTemplate2 != null ? segmentTemplate2.mediaTemplate : null);
        UrlTemplate parseUrlTemplate2 = parseUrlTemplate(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.initializationTemplate : null);
        List<SegmentBase.SegmentTimelineElement> list = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list = parseSegmentTimeline(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTemplate"));
        if (segmentTemplate2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentTemplate2.initialization;
            }
            if (list == null) {
                list = segmentTemplate2.segmentTimeline;
            }
        }
        return buildSegmentTemplate(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list, parseUrlTemplate2, parseUrlTemplate);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, j3, j4, list, urlTemplate, urlTemplate2);
    }

    /* access modifiers changed from: protected */
    public EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, CommonProperties.VALUE, "");
        long parseLong = parseLong(xmlPullParser, "timescale", 1);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Event")) {
                arrayList.add(parseEvent(xmlPullParser, parseString, parseString2, parseLong, byteArrayOutputStream));
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "EventStream"));
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            EventMessage eventMessage = (EventMessage) arrayList.get(i);
            jArr[i] = eventMessage.presentationTimeUs;
            eventMessageArr[i] = eventMessage;
        }
        return buildEventStream(parseString, parseString2, parseLong, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public EventStream buildEventStream(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        return new EventStream(str, str2, j, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public EventMessage parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j, ByteArrayOutputStream byteArrayOutputStream) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long parseLong = parseLong(xmlPullParser2, "id", 0);
        long parseLong2 = parseLong(xmlPullParser2, "duration", C.TIME_UNSET);
        long parseLong3 = parseLong(xmlPullParser2, "presentationTime", 0);
        return buildEvent(str, str2, parseLong, Util.scaleLargeTimestamp(parseLong2, 1000, j), parseEventObject(xmlPullParser2, byteArrayOutputStream), Util.scaleLargeTimestamp(parseLong3, C.MICROS_PER_SECOND, j));
    }

    /* access modifiers changed from: protected */
    public byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, (String) null);
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    newSerializer.startDocument((String) null, false);
                    break;
                case 1:
                    newSerializer.endDocument();
                    break;
                case 2:
                    newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    newSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    newSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    newSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    newSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    newSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    newSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    newSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        newSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public EventMessage buildEvent(String str, String str2, long j, long j2, byte[] bArr, long j3) {
        return new EventMessage(str, str2, j2, j, bArr, j3);
    }

    /* access modifiers changed from: protected */
    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, ExifInterface.LATITUDE_SOUTH)) {
                j = parseLong(xmlPullParser, "t", j);
                long parseLong = parseLong(xmlPullParser, "d", C.TIME_UNSET);
                int parseInt = parseInt(xmlPullParser, "r", 0) + 1;
                for (int i = 0; i < parseInt; i++) {
                    arrayList.add(buildSegmentTimelineElement(j, parseLong));
                    j += parseLong;
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    /* access modifiers changed from: protected */
    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    /* access modifiers changed from: protected */
    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j2 = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - j2) + 1;
                return buildRangedUri(attributeValue, j2, j);
            }
        } else {
            j2 = 0;
        }
        j = -1;
        return buildRangedUri(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", (String) null);
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString)) {
            i = parseInt(xmlPullParser, CommonProperties.VALUE, -1);
        } else if ("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(parseString)) {
            i = parseDolbyChannelConfiguration(xmlPullParser);
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.hasData()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (arrayList.get(i).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if (MimeTypes.APPLICATION_MP4.equals(str)) {
            if ("stpp".equals(str2)) {
                return MimeTypes.APPLICATION_TTML;
            }
            if ("wvtt".equals(str2)) {
                return MimeTypes.APPLICATION_MP4VTT;
            }
        } else if (MimeTypes.APPLICATION_RAWCC.equals(str) && str2 != null) {
            if (str2.contains("cea708")) {
                return MimeTypes.APPLICATION_CEA708;
            }
            if (str2.contains("eia608") || str2.contains("cea608")) {
                return MimeTypes.APPLICATION_CEA608;
            }
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String str) {
        return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, CommonProperties.VALUE, (String) null);
        String parseString3 = parseString(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(parseString, parseString2, parseString3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(descriptor.schemeIdUri) && "ec+3".equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        return MimeTypes.AUDIO_E_AC3;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser r4) {
        /*
            r0 = 0
            java.lang.String r1 = "value"
            java.lang.String r4 = r4.getAttributeValue(r0, r1)
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r4)
            r0 = -1
            if (r4 != 0) goto L_0x0010
            return r0
        L_0x0010:
            r4.hashCode()
            int r1 = r4.hashCode()
            r2 = 2
            r3 = 1
            switch(r1) {
                case 1596796: goto L_0x003f;
                case 2937391: goto L_0x0034;
                case 3094035: goto L_0x0029;
                case 3133436: goto L_0x001e;
                default: goto L_0x001c;
            }
        L_0x001c:
            r4 = -1
            goto L_0x0049
        L_0x001e:
            java.lang.String r1 = "fa01"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0027
            goto L_0x001c
        L_0x0027:
            r4 = 3
            goto L_0x0049
        L_0x0029:
            java.lang.String r1 = "f801"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0032
            goto L_0x001c
        L_0x0032:
            r4 = 2
            goto L_0x0049
        L_0x0034:
            java.lang.String r1 = "a000"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x003d
            goto L_0x001c
        L_0x003d:
            r4 = 1
            goto L_0x0049
        L_0x003f:
            java.lang.String r1 = "4000"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0048
            goto L_0x001c
        L_0x0048:
            r4 = 0
        L_0x0049:
            switch(r4) {
                case 0: goto L_0x0053;
                case 1: goto L_0x0052;
                case 2: goto L_0x0050;
                case 3: goto L_0x004d;
                default: goto L_0x004c;
            }
        L_0x004c:
            return r0
        L_0x004d:
            r4 = 8
            return r4
        L_0x0050:
            r4 = 6
            return r4
        L_0x0052:
            return r2
        L_0x0053:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser):int");
    }

    protected static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format2, String str, SegmentBase segmentBase2, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2, long j) {
            this.format = format2;
            this.baseUrl = str;
            this.segmentBase = segmentBase2;
            this.drmSchemeType = str2;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.revisionId = j;
        }
    }
}
