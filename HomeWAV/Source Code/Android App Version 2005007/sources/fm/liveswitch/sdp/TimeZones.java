package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.StringAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class TimeZones {
    private ArrayList<TimeZone> __values;

    public void addTimeZone(TimeZone timeZone) {
        this.__values.add(timeZone);
    }

    public TimeZone[] getValues() {
        return (TimeZone[]) this.__values.toArray(new TimeZone[0]);
    }

    public static TimeZones parse(String str) {
        String[] split = StringExtensions.split(str.substring(2), new char[]{' '});
        TimeZones timeZones = new TimeZones(new TimeZone[]{TimeZone.parse(StringExtensions.join(" ", StringAssistant.subArray(split, 0, 2)))});
        for (int i = 2; i < ArrayExtensions.getLength((Object[]) split); i += 2) {
            timeZones.addTimeZone(TimeZone.parse(StringExtensions.join(" ", StringAssistant.subArray(split, i, 2))));
        }
        return timeZones;
    }

    public boolean removeTimeZone(TimeZone timeZone) {
        return this.__values.remove(timeZone);
    }

    public TimeZones(TimeZone[] timeZoneArr) {
        ArrayList<TimeZone> arrayList = new ArrayList<>();
        this.__values = arrayList;
        if (timeZoneArr != null) {
            ArrayListExtensions.addRange(arrayList, (T[]) timeZoneArr);
            return;
        }
        throw new RuntimeException(new Exception("timeZones cannot be null."));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "z=");
        for (TimeZone timeZone : getValues()) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, timeZone.toString());
        }
        return sb.toString().trim();
    }
}
