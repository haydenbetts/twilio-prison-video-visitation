package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TimeSpan;
import java.util.ArrayList;

public class RepeatTime {
    private ArrayList<TimeSpan> __offsets;
    private TimeSpan _activeDuration;
    private TimeSpan _repeatInterval;

    public void addOffset(TimeSpan timeSpan) {
        this.__offsets.add(timeSpan);
    }

    public TimeSpan getActiveDuration() {
        return this._activeDuration;
    }

    public TimeSpan[] getOffsets() {
        return (TimeSpan[]) this.__offsets.toArray(new TimeSpan[0]);
    }

    public TimeSpan getRepeatInterval() {
        return this._repeatInterval;
    }

    public static RepeatTime parse(String str) {
        String[] split = StringExtensions.split(str.substring(2), new char[]{' '});
        RepeatTime repeatTime = new RepeatTime(new TimeSpan(0, 0, ParseAssistant.parseIntegerValue(split[0])), new TimeSpan(0, 0, ParseAssistant.parseIntegerValue(split[1])));
        for (int i = 2; i < ArrayExtensions.getLength((Object[]) split); i++) {
            repeatTime.addOffset(new TimeSpan(0, 0, ParseAssistant.parseIntegerValue(split[2])));
        }
        return repeatTime;
    }

    public boolean removeOffset(TimeSpan timeSpan) {
        return this.__offsets.remove(timeSpan);
    }

    public RepeatTime(TimeSpan timeSpan, TimeSpan timeSpan2) {
        this(timeSpan, timeSpan2, (TimeSpan[]) null);
    }

    public RepeatTime(TimeSpan timeSpan, TimeSpan timeSpan2, TimeSpan[] timeSpanArr) {
        this.__offsets = new ArrayList<>();
        setRepeatInterval(timeSpan);
        setActiveDuration(timeSpan2);
        if (timeSpanArr != null) {
            ArrayListExtensions.addRange(this.__offsets, (T[]) timeSpanArr);
        }
    }

    private void setActiveDuration(TimeSpan timeSpan) {
        this._activeDuration = timeSpan;
    }

    private void setRepeatInterval(TimeSpan timeSpan) {
        this._repeatInterval = timeSpan;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "r=");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf((int) getRepeatInterval().getTotalSeconds())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf((int) getActiveDuration().getTotalSeconds())));
        for (TimeSpan totalSeconds : getOffsets()) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf((int) totalSeconds.getTotalSeconds())));
        }
        return sb.toString();
    }
}
