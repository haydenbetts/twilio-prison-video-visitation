package fm.liveswitch.dtmf;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import fm.liveswitch.Global;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.IAction2;
import fm.liveswitch.IAction3;
import fm.liveswitch.IFunction0;
import fm.liveswitch.IFunctionDelegate1;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.JsonSerializer;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.NullableBoolean;
import fm.liveswitch.NullableInteger;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Marker;

public class Tone {
    private int _duration;
    private boolean _end;
    private int _sentDuration;
    private String _value;

    public static String getAValue() {
        return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
    }

    public static String getBValue() {
        return "B";
    }

    public static String getCValue() {
        return "C";
    }

    public static String getDValue() {
        return "D";
    }

    public static String getEightValue() {
        return "8";
    }

    public static String getEmptyValue() {
        return "";
    }

    public static String getFiveValue() {
        return "5";
    }

    public static String getFourValue() {
        return "4";
    }

    public static String getHashValue() {
        return "#";
    }

    public static String getNineValue() {
        return "9";
    }

    public static String getOneValue() {
        return "1";
    }

    public static int getPauseDuration() {
        return 2000;
    }

    public static String getPauseValue() {
        return ",";
    }

    public static String getSevenValue() {
        return "7";
    }

    public static String getSixValue() {
        return "6";
    }

    public static String getStarValue() {
        return Marker.ANY_MARKER;
    }

    public static String getThreeValue() {
        return ExifInterface.GPS_MEASUREMENT_3D;
    }

    public static String getTwoValue() {
        return "2";
    }

    public static String getZeroValue() {
        return "0";
    }

    private static String valueFromEventCode(int i) {
        return i == 0 ? "0" : i == 1 ? "1" : i == 2 ? "2" : i == 3 ? ExifInterface.GPS_MEASUREMENT_3D : i == 4 ? "4" : i == 5 ? "5" : i == 6 ? "6" : i == 7 ? "7" : i == 8 ? "8" : i == 9 ? "9" : i == 10 ? Marker.ANY_MARKER : i == 11 ? "#" : i == 12 ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : i == 13 ? "B" : i == 14 ? "C" : i == 15 ? "D" : i == 99 ? "," : "";
    }

    public Tone clone() {
        return clone(getDuration());
    }

    public Tone clone(int i) {
        return new Tone(getValue(), i);
    }

    private static int eventCodeFromValue(String str) {
        if (str.equals("0")) {
            return 0;
        }
        if (str.equals("1")) {
            return 1;
        }
        if (str.equals("2")) {
            return 2;
        }
        if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            return 3;
        }
        if (str.equals("4")) {
            return 4;
        }
        if (str.equals("5")) {
            return 5;
        }
        if (str.equals("6")) {
            return 6;
        }
        if (str.equals("7")) {
            return 7;
        }
        if (str.equals("8")) {
            return 8;
        }
        if (str.equals("9")) {
            return 9;
        }
        if (str.equals(Marker.ANY_MARKER)) {
            return 10;
        }
        if (str.equals("#")) {
            return 11;
        }
        if (str.equals(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
            return 12;
        }
        if (str.equals("B")) {
            return 13;
        }
        if (str.equals("C")) {
            return 14;
        }
        if (str.equals("D")) {
            return 15;
        }
        return str.equals(",") ? 99 : -1;
    }

    public static Tone fromJson(String str) {
        return (Tone) JsonSerializer.deserializeObject(str, new IFunction0<Tone>() {
            public Tone invoke() {
                return new Tone();
            }
        }, new IAction3<Tone, String, String>() {
            public void invoke(Tone tone, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, CommonProperties.VALUE)) {
                    tone.setValue(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "duration")) {
                    tone.setDuration(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, TtmlNode.END)) {
                    tone.setEnd(JsonSerializer.deserializeBoolean(str2).getValue());
                }
            }
        });
    }

    public static Tone[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, Tone>() {
            public String getId() {
                return "fm.liveswitch.dtmf.Tone.fromJson";
            }

            public Tone invoke(String str) {
                return Tone.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (Tone[]) deserializeObjectArray.toArray(new Tone[0]);
    }

    public static Tone fromPacket(Packet packet, int i) {
        Tone tone = new Tone(valueFromEventCode(packet.getEventCode()), (packet.getDuration() * 1000) / i);
        tone.setEnd(packet.getEnd());
        return tone;
    }

    public static Tone[] fromToneString(String str) {
        return fromToneString(str, 100, 100);
    }

    public static Tone[] fromToneString(String str, int i) {
        return fromToneString(str, i, 100);
    }

    public static Tone[] fromToneString(String str, int i, int i2) {
        int max = MathAssistant.max(40, MathAssistant.min(2000, i));
        int max2 = MathAssistant.max(40, i2);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < StringExtensions.getLength(str); i3++) {
            if (i3 > 0) {
                arrayList.add(new Tone(getEmptyValue(), max2));
            }
            String substring = StringExtensions.substring(str, i3, 1);
            if (Global.equals(substring, getPauseValue())) {
                arrayList.add(new Tone(substring, 2000));
            } else {
                arrayList.add(new Tone(substring, max));
            }
        }
        return (Tone[]) arrayList.toArray(new Tone[0]);
    }

    public static Tone getA() {
        return new Tone(getAValue(), 100);
    }

    public static Tone getB() {
        return new Tone(getBValue(), 100);
    }

    public static Tone getC() {
        return new Tone(getCValue(), 100);
    }

    public static Tone getD() {
        return new Tone(getDValue(), 100);
    }

    public int getDuration() {
        return this._duration;
    }

    public static Tone getEight() {
        return new Tone(getEightValue(), 100);
    }

    public static Tone getEmpty() {
        return new Tone(getEmptyValue(), 0);
    }

    public boolean getEnd() {
        return this._end;
    }

    public static Tone getFive() {
        return new Tone(getFiveValue(), 100);
    }

    public static Tone getFour() {
        return new Tone(getFourValue(), 100);
    }

    public static Tone getHash() {
        return new Tone(getHashValue(), 100);
    }

    public static Tone getNine() {
        return new Tone(getNineValue(), 100);
    }

    public static Tone getOne() {
        return new Tone(getOneValue(), 100);
    }

    public static Tone getPause() {
        return new Tone(getPauseValue(), 2000);
    }

    /* access modifiers changed from: package-private */
    public int getSentDuration() {
        return this._sentDuration;
    }

    public static Tone getSeven() {
        return new Tone(getSevenValue(), 100);
    }

    public static Tone getSix() {
        return new Tone(getSixValue(), 100);
    }

    public static Tone getStar() {
        return new Tone(getStarValue(), 100);
    }

    public static Tone getThree() {
        return new Tone(getThreeValue(), 100);
    }

    public static Tone getTwo() {
        return new Tone(getTwoValue(), 100);
    }

    public String getValue() {
        return this._value;
    }

    public static Tone getZero() {
        return new Tone(getZeroValue(), 100);
    }

    /* access modifiers changed from: package-private */
    public void setDuration(int i) {
        this._duration = i;
    }

    /* access modifiers changed from: private */
    public void setEnd(boolean z) {
        this._end = z;
    }

    /* access modifiers changed from: package-private */
    public void setSentDuration(int i) {
        this._sentDuration = i;
    }

    /* access modifiers changed from: private */
    public void setValue(String str) {
        this._value = str;
    }

    public static String toJson(Tone tone) {
        return JsonSerializer.serializeObject(tone, new IAction2<Tone, HashMap<String, String>>() {
            public void invoke(Tone tone, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), CommonProperties.VALUE, JsonSerializer.serializeString(tone.getValue()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "duration", JsonSerializer.serializeInteger(new NullableInteger(tone.getDuration())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), TtmlNode.END, JsonSerializer.serializeBoolean(new NullableBoolean(tone.getEnd())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(Tone[] toneArr) {
        return JsonSerializer.serializeObjectArray(toneArr, new IFunctionDelegate1<Tone, String>() {
            public String getId() {
                return "fm.liveswitch.dtmf.Tone.toJson";
            }

            public String invoke(Tone tone) {
                return Tone.toJson(tone);
            }
        });
    }

    private Tone() {
    }

    public Tone(String str) {
        this(str, 100);
    }

    public Tone(String str, int i) {
        setValue(str);
        setDuration(i);
    }

    public Packet toPacket(int i) {
        Packet packet = new Packet();
        packet.setEventCode(eventCodeFromValue(getValue()));
        packet.setDuration((getDuration() * i) / 1000);
        packet.setEnd(getEnd());
        return packet;
    }

    public String toString() {
        if (Global.equals(getValue(), getEmptyValue())) {
            return StringExtensions.format("[empty] ({0}ms)", (Object) IntegerExtensions.toString(Integer.valueOf(getDuration())));
        }
        if (Global.equals(getValue(), getPauseValue())) {
            return StringExtensions.format("[pause] ({0}ms)", (Object) IntegerExtensions.toString(Integer.valueOf(getDuration())));
        }
        if (getEnd()) {
            return StringExtensions.format("{0} ({1}ms) [end]", getValue(), IntegerExtensions.toString(Integer.valueOf(getDuration())));
        }
        return StringExtensions.format("{0} ({1}ms)", getValue(), IntegerExtensions.toString(Integer.valueOf(getDuration())));
    }

    public static String toToneString(Tone[] toneArr) {
        StringBuilder sb = new StringBuilder();
        for (Tone value : toneArr) {
            StringBuilderExtensions.append(sb, value.getValue());
        }
        return sb.toString();
    }
}
