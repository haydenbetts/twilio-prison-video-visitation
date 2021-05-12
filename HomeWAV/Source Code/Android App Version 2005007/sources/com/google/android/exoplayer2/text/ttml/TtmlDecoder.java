package com.google.android.exoplayer2.text.ttml;

import android.util.Log;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    public TtmlDecoder() {
        super(TAG);
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* access modifiers changed from: protected */
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            TtmlSubtitle ttmlSubtitle = null;
            hashMap2.put("", new TtmlRegion((String) null));
            int i2 = 0;
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), (String) null);
            LinkedList linkedList = new LinkedList();
            FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) linkedList.peekLast();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRate = parseFrameAndTickRates(newPullParser);
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + newPullParser.getName());
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            parseHeader(newPullParser, hashMap, hashMap2);
                        } else {
                            try {
                                TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                linkedList.addLast(parseNode);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(parseNode);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                            }
                        }
                    } else if (eventType == 4) {
                        ttmlNode.addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                    } else if (eventType == 3) {
                        if (newPullParser.getName().equals(TtmlNode.TAG_TT)) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) linkedList.getLast(), hashMap, hashMap2);
                        }
                        linkedList.removeLast();
                    }
                    newPullParser.next();
                } else if (eventType != 2) {
                    if (eventType == 3) {
                        i2--;
                    }
                    newPullParser.next();
                }
                i2++;
                newPullParser.next();
            }
            return ttmlSubtitle;
        } catch (XmlPullParserException e2) {
            throw new SubtitleDecoderException("Unable to decode source", e2);
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split(" ");
            if (split.length == 2) {
                f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f, i, i2);
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) throws IOException, XmlPullParserException {
        TtmlRegion parseRegionAttributes;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                if (parseStyleAttributes.getId() != null) {
                    map.put(parseStyleAttributes.getId(), parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region") && (parseRegionAttributes = parseRegionAttributes(xmlPullParser)) != null) {
                map2.put(parseRegionAttributes.id, parseRegionAttributes);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser) {
        float f;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_ORIGIN);
        if (attributeValue2 != null) {
            Pattern pattern = PERCENTAGE_COORDINATES;
            Matcher matcher = pattern.matcher(attributeValue2);
            if (matcher.matches()) {
                int i = 1;
                try {
                    float parseFloat = Float.parseFloat(matcher.group(1)) / 100.0f;
                    float parseFloat2 = Float.parseFloat(matcher.group(2)) / 100.0f;
                    String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
                    if (attributeValue3 != null) {
                        Matcher matcher2 = pattern.matcher(attributeValue3);
                        if (matcher2.matches()) {
                            try {
                                float parseFloat3 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                                float parseFloat4 = Float.parseFloat(matcher2.group(2)) / 100.0f;
                                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                                if (attributeValue4 != null) {
                                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                                    lowerInvariant.hashCode();
                                    if (lowerInvariant.equals("center")) {
                                        f = parseFloat2 + (parseFloat4 / 2.0f);
                                    } else if (lowerInvariant.equals("after")) {
                                        f = parseFloat2 + parseFloat4;
                                        i = 2;
                                    }
                                    return new TtmlRegion(attributeValue, parseFloat, f, 0, i, parseFloat3);
                                }
                                f = parseFloat2;
                                i = 0;
                                return new TtmlRegion(attributeValue, parseFloat, f, 0, i, parseFloat3);
                            } catch (NumberFormatException unused) {
                                Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                                return null;
                            }
                        } else {
                            Log.w(TAG, "Ignoring region with unsupported extent: " + attributeValue2);
                            return null;
                        }
                    } else {
                        Log.w(TAG, "Ignoring region without an extent");
                        return null;
                    }
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            } else {
                Log.w(TAG, "Ignoring region with unsupported origin: " + attributeValue2);
                return null;
            }
        } else {
            Log.w(TAG, "Ignoring region without an origin");
            return null;
        }
    }

    private String[] parseStyleIds(String str) {
        return str.split("\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x012e, code lost:
        if (r3.equals(com.google.android.exoplayer2.text.ttml.TtmlNode.LINETHROUGH) == false) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0195, code lost:
        if (r3.equals(com.google.android.exoplayer2.text.ttml.TtmlNode.START) == false) goto L_0x018d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, com.google.android.exoplayer2.text.ttml.TtmlStyle r13) {
        /*
            r11 = this;
            int r0 = r12.getAttributeCount()
            r1 = 0
            r2 = 0
        L_0x0006:
            if (r2 >= r0) goto L_0x0219
            java.lang.String r3 = r12.getAttributeValue(r2)
            java.lang.String r4 = r12.getAttributeName(r2)
            r4.hashCode()
            int r5 = r4.hashCode()
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = -1
            r10 = 1
            switch(r5) {
                case -1550943582: goto L_0x007c;
                case -1224696685: goto L_0x0071;
                case -1065511464: goto L_0x0066;
                case -879295043: goto L_0x005b;
                case -734428249: goto L_0x0050;
                case 3355: goto L_0x0045;
                case 94842723: goto L_0x003a;
                case 365601008: goto L_0x002f;
                case 1287124693: goto L_0x0022;
                default: goto L_0x001f;
            }
        L_0x001f:
            r4 = -1
            goto L_0x0086
        L_0x0022:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x002b
            goto L_0x001f
        L_0x002b:
            r4 = 8
            goto L_0x0086
        L_0x002f:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0038
            goto L_0x001f
        L_0x0038:
            r4 = 7
            goto L_0x0086
        L_0x003a:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0043
            goto L_0x001f
        L_0x0043:
            r4 = 6
            goto L_0x0086
        L_0x0045:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x004e
            goto L_0x001f
        L_0x004e:
            r4 = 5
            goto L_0x0086
        L_0x0050:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0059
            goto L_0x001f
        L_0x0059:
            r4 = 4
            goto L_0x0086
        L_0x005b:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0064
            goto L_0x001f
        L_0x0064:
            r4 = 3
            goto L_0x0086
        L_0x0066:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x006f
            goto L_0x001f
        L_0x006f:
            r4 = 2
            goto L_0x0086
        L_0x0071:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x007a
            goto L_0x001f
        L_0x007a:
            r4 = 1
            goto L_0x0086
        L_0x007c:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0085
            goto L_0x001f
        L_0x0085:
            r4 = 0
        L_0x0086:
            java.lang.String r5 = "TtmlDecoder"
            switch(r4) {
                case 0: goto L_0x0207;
                case 1: goto L_0x01fe;
                case 2: goto L_0x017f;
                case 3: goto L_0x0118;
                case 4: goto L_0x0108;
                case 5: goto L_0x00f2;
                case 6: goto L_0x00cf;
                case 7: goto L_0x00b0;
                case 8: goto L_0x008d;
                default: goto L_0x008b;
            }
        L_0x008b:
            goto L_0x0215
        L_0x008d:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            int r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x009a }
            r13.setBackgroundColor(r4)     // Catch:{ IllegalArgumentException -> 0x009a }
            goto L_0x0215
        L_0x009a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Failed parsing background value: "
            r4.append(r6)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            android.util.Log.w(r5, r3)
            goto L_0x0215
        L_0x00b0:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)     // Catch:{ SubtitleDecoderException -> 0x00b9 }
            parseFontSize(r3, r13)     // Catch:{ SubtitleDecoderException -> 0x00b9 }
            goto L_0x0215
        L_0x00b9:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Failed parsing fontSize value: "
            r4.append(r6)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            android.util.Log.w(r5, r3)
            goto L_0x0215
        L_0x00cf:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            int r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x00dc }
            r13.setFontColor(r4)     // Catch:{ IllegalArgumentException -> 0x00dc }
            goto L_0x0215
        L_0x00dc:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Failed parsing color value: "
            r4.append(r6)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            android.util.Log.w(r5, r3)
            goto L_0x0215
        L_0x00f2:
            java.lang.String r4 = r12.getName()
            java.lang.String r5 = "style"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0215
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setId(r3)
            goto L_0x0215
        L_0x0108:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setBold(r3)
            goto L_0x0215
        L_0x0118:
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            r3.hashCode()
            int r4 = r3.hashCode()
            switch(r4) {
                case -1461280213: goto L_0x0148;
                case -1026963764: goto L_0x013c;
                case 913457136: goto L_0x0131;
                case 1679736913: goto L_0x0128;
                default: goto L_0x0126;
            }
        L_0x0126:
            r7 = -1
            goto L_0x0152
        L_0x0128:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0152
            goto L_0x0126
        L_0x0131:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x013a
            goto L_0x0126
        L_0x013a:
            r7 = 2
            goto L_0x0152
        L_0x013c:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0146
            goto L_0x0126
        L_0x0146:
            r7 = 1
            goto L_0x0152
        L_0x0148:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0151
            goto L_0x0126
        L_0x0151:
            r7 = 0
        L_0x0152:
            switch(r7) {
                case 0: goto L_0x0175;
                case 1: goto L_0x016b;
                case 2: goto L_0x0161;
                case 3: goto L_0x0157;
                default: goto L_0x0155;
            }
        L_0x0155:
            goto L_0x0215
        L_0x0157:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setLinethrough(r10)
            goto L_0x0215
        L_0x0161:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setLinethrough(r1)
            goto L_0x0215
        L_0x016b:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setUnderline(r10)
            goto L_0x0215
        L_0x0175:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setUnderline(r1)
            goto L_0x0215
        L_0x017f:
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3)
            r3.hashCode()
            int r4 = r3.hashCode()
            switch(r4) {
                case -1364013995: goto L_0x01b9;
                case 100571: goto L_0x01ae;
                case 3317767: goto L_0x01a3;
                case 108511772: goto L_0x0198;
                case 109757538: goto L_0x018f;
                default: goto L_0x018d;
            }
        L_0x018d:
            r6 = -1
            goto L_0x01c3
        L_0x018f:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01c3
            goto L_0x018d
        L_0x0198:
            java.lang.String r4 = "right"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01a1
            goto L_0x018d
        L_0x01a1:
            r6 = 3
            goto L_0x01c3
        L_0x01a3:
            java.lang.String r4 = "left"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01ac
            goto L_0x018d
        L_0x01ac:
            r6 = 2
            goto L_0x01c3
        L_0x01ae:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01b7
            goto L_0x018d
        L_0x01b7:
            r6 = 1
            goto L_0x01c3
        L_0x01b9:
            java.lang.String r4 = "center"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01c2
            goto L_0x018d
        L_0x01c2:
            r6 = 0
        L_0x01c3:
            switch(r6) {
                case 0: goto L_0x01f3;
                case 1: goto L_0x01e8;
                case 2: goto L_0x01dd;
                case 3: goto L_0x01d2;
                case 4: goto L_0x01c7;
                default: goto L_0x01c6;
            }
        L_0x01c6:
            goto L_0x0215
        L_0x01c7:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0215
        L_0x01d2:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0215
        L_0x01dd:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0215
        L_0x01e8:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0215
        L_0x01f3:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0215
        L_0x01fe:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setFontFamily(r3)
            goto L_0x0215
        L_0x0207:
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            com.google.android.exoplayer2.text.ttml.TtmlStyle r13 = r13.setItalic(r3)
        L_0x0215:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x0219:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlStyle):com.google.android.exoplayer2.text.ttml.TtmlStyle");
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r20, com.google.android.exoplayer2.text.ttml.TtmlNode r21, java.util.Map<java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlRegion> r22, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r23) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r23
            int r4 = r20.getAttributeCount()
            r5 = 0
            com.google.android.exoplayer2.text.ttml.TtmlStyle r11 = r0.parseStyleAttributes(r1, r5)
            java.lang.String r9 = ""
            r12 = r5
            r13 = r9
            r5 = 0
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0025:
            if (r5 >= r4) goto L_0x009b
            java.lang.String r6 = r1.getAttributeName(r5)
            java.lang.String r7 = r1.getAttributeValue(r5)
            r6.hashCode()
            int r18 = r6.hashCode()
            switch(r18) {
                case -934795532: goto L_0x0067;
                case 99841: goto L_0x005c;
                case 100571: goto L_0x0051;
                case 93616297: goto L_0x0046;
                case 109780401: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            r8 = -1
            goto L_0x0071
        L_0x003b:
            java.lang.String r8 = "style"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0044
            goto L_0x0039
        L_0x0044:
            r8 = 4
            goto L_0x0071
        L_0x0046:
            java.lang.String r8 = "begin"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x004f
            goto L_0x0039
        L_0x004f:
            r8 = 3
            goto L_0x0071
        L_0x0051:
            java.lang.String r8 = "end"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x005a
            goto L_0x0039
        L_0x005a:
            r8 = 2
            goto L_0x0071
        L_0x005c:
            java.lang.String r8 = "dur"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0065
            goto L_0x0039
        L_0x0065:
            r8 = 1
            goto L_0x0071
        L_0x0067:
            java.lang.String r8 = "region"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0070
            goto L_0x0039
        L_0x0070:
            r8 = 0
        L_0x0071:
            switch(r8) {
                case 0: goto L_0x008f;
                case 1: goto L_0x008a;
                case 2: goto L_0x0085;
                case 3: goto L_0x007e;
                case 4: goto L_0x0075;
                default: goto L_0x0074;
            }
        L_0x0074:
            goto L_0x0082
        L_0x0075:
            java.lang.String[] r6 = r0.parseStyleIds(r7)
            int r7 = r6.length
            if (r7 <= 0) goto L_0x0082
            r12 = r6
            goto L_0x0082
        L_0x007e:
            long r9 = parseTimeExpression(r7, r3)
        L_0x0082:
            r6 = r22
            goto L_0x0098
        L_0x0085:
            long r14 = parseTimeExpression(r7, r3)
            goto L_0x0082
        L_0x008a:
            long r16 = parseTimeExpression(r7, r3)
            goto L_0x0082
        L_0x008f:
            r6 = r22
            boolean r8 = r6.containsKey(r7)
            if (r8 == 0) goto L_0x0098
            r13 = r7
        L_0x0098:
            int r5 = r5 + 1
            goto L_0x0025
        L_0x009b:
            if (r2 == 0) goto L_0x00b7
            long r3 = r2.startTimeUs
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00bc
            int r3 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00af
            long r3 = r2.startTimeUs
            long r9 = r9 + r3
        L_0x00af:
            int r3 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00bc
            long r3 = r2.startTimeUs
            long r14 = r14 + r3
            goto L_0x00bc
        L_0x00b7:
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x00bc:
            r7 = r9
            int r3 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x00d6
            int r3 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00ca
            long r16 = r7 + r16
            r9 = r16
            goto L_0x00d7
        L_0x00ca:
            if (r2 == 0) goto L_0x00d6
            long r3 = r2.endTimeUs
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 == 0) goto L_0x00d6
            long r2 = r2.endTimeUs
            r9 = r2
            goto L_0x00d7
        L_0x00d6:
            r9 = r14
        L_0x00d7:
            java.lang.String r6 = r20.getName()
            com.google.android.exoplayer2.text.ttml.TtmlNode r1 = com.google.android.exoplayer2.text.ttml.TtmlNode.buildNode(r6, r7, r9, r11, r12, r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseNode(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlNode, java.util.Map, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):com.google.android.exoplayer2.text.ttml.TtmlNode");
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals("body") || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals(TtmlNode.TAG_SMPTE_IMAGE) || str.equals(TtmlNode.TAG_SMPTE_DATA) || str.equals(TtmlNode.TAG_SMPTE_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = str.split("\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + split.length + ".");
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            group.hashCode();
            char c = 65535;
            switch (group.hashCode()) {
                case 37:
                    if (group.equals("%")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3240:
                    if (group.equals("em")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3592:
                    if (group.equals("px")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                default:
                    throw new SubtitleDecoderException("Invalid unit for fontSize: '" + group + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a1, code lost:
        if (r13.equals("ms") == false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d8, code lost:
        r8 = r8 / r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e6, code lost:
        r8 = r8 * r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r13, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r14) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
            java.util.regex.Pattern r0 = CLOCK_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L_0x0076
            java.lang.String r13 = r0.group(r7)
            long r7 = java.lang.Long.parseLong(r13)
            r9 = 3600(0xe10, double:1.7786E-320)
            long r7 = r7 * r9
            double r7 = (double) r7
            java.lang.String r13 = r0.group(r6)
            long r9 = java.lang.Long.parseLong(r13)
            r11 = 60
            long r9 = r9 * r11
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r0.group(r5)
            long r5 = java.lang.Long.parseLong(r13)
            double r5 = (double) r5
            double r7 = r7 + r5
            java.lang.String r13 = r0.group(r4)
            r4 = 0
            if (r13 == 0) goto L_0x0047
            double r9 = java.lang.Double.parseDouble(r13)
            goto L_0x0048
        L_0x0047:
            r9 = r4
        L_0x0048:
            double r7 = r7 + r9
            r13 = 5
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x005a
            long r9 = java.lang.Long.parseLong(r13)
            float r13 = (float) r9
            float r1 = r14.effectiveFrameRate
            float r13 = r13 / r1
            double r9 = (double) r13
            goto L_0x005b
        L_0x005a:
            r9 = r4
        L_0x005b:
            double r7 = r7 + r9
            r13 = 6
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x0071
            long r0 = java.lang.Long.parseLong(r13)
            double r0 = (double) r0
            int r13 = r14.subFrameRate
            double r4 = (double) r13
            double r0 = r0 / r4
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            double r4 = r0 / r13
        L_0x0071:
            double r7 = r7 + r4
            double r7 = r7 * r2
            long r13 = (long) r7
            return r13
        L_0x0076:
            java.util.regex.Pattern r0 = OFFSET_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x00f1
            java.lang.String r13 = r0.group(r7)
            double r8 = java.lang.Double.parseDouble(r13)
            java.lang.String r13 = r0.group(r6)
            r13.hashCode()
            r0 = -1
            int r1 = r13.hashCode()
            switch(r1) {
                case 102: goto L_0x00c5;
                case 104: goto L_0x00ba;
                case 109: goto L_0x00af;
                case 116: goto L_0x00a4;
                case 3494: goto L_0x009b;
                default: goto L_0x0099;
            }
        L_0x0099:
            r4 = -1
            goto L_0x00cf
        L_0x009b:
            java.lang.String r1 = "ms"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00cf
            goto L_0x0099
        L_0x00a4:
            java.lang.String r1 = "t"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00ad
            goto L_0x0099
        L_0x00ad:
            r4 = 3
            goto L_0x00cf
        L_0x00af:
            java.lang.String r1 = "m"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00b8
            goto L_0x0099
        L_0x00b8:
            r4 = 2
            goto L_0x00cf
        L_0x00ba:
            java.lang.String r1 = "h"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00c3
            goto L_0x0099
        L_0x00c3:
            r4 = 1
            goto L_0x00cf
        L_0x00c5:
            java.lang.String r1 = "f"
            boolean r13 = r13.equals(r1)
            if (r13 != 0) goto L_0x00ce
            goto L_0x0099
        L_0x00ce:
            r4 = 0
        L_0x00cf:
            switch(r4) {
                case 0: goto L_0x00e9;
                case 1: goto L_0x00e1;
                case 2: goto L_0x00de;
                case 3: goto L_0x00da;
                case 4: goto L_0x00d3;
                default: goto L_0x00d2;
            }
        L_0x00d2:
            goto L_0x00ed
        L_0x00d3:
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x00d8:
            double r8 = r8 / r13
            goto L_0x00ed
        L_0x00da:
            int r13 = r14.tickRate
            double r13 = (double) r13
            goto L_0x00d8
        L_0x00de:
            r13 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x00e6
        L_0x00e1:
            r13 = 4660134898793709568(0x40ac200000000000, double:3600.0)
        L_0x00e6:
            double r8 = r8 * r13
            goto L_0x00ed
        L_0x00e9:
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            goto L_0x00d8
        L_0x00ed:
            double r8 = r8 * r2
            long r13 = (long) r8
            return r13
        L_0x00f1:
            com.google.android.exoplayer2.text.SubtitleDecoderException r14 = new com.google.android.exoplayer2.text.SubtitleDecoderException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Malformed time expression: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseTimeExpression(java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }
}
