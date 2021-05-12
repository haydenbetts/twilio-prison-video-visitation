package fm.liveswitch.sdp;

import fm.liveswitch.DoubleExtensions;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TimeSpan;

public class TimeZone {
    private double _adjustmentTime;
    private TimeSpan _offset;

    public double getAdjustmentTime() {
        return this._adjustmentTime;
    }

    public TimeSpan getOffset() {
        return this._offset;
    }

    public static TimeZone parse(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        return new TimeZone(ParseAssistant.parseDoubleValue(split[0]), new TimeSpan(0, 0, ParseAssistant.parseIntegerValue(split[1])));
    }

    private void setAdjustmentTime(double d) {
        this._adjustmentTime = d;
    }

    private void setOffset(TimeSpan timeSpan) {
        this._offset = timeSpan;
    }

    public TimeZone(double d, TimeSpan timeSpan) {
        setAdjustmentTime(d);
        setOffset(timeSpan);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, DoubleExtensions.toString(Double.valueOf(getAdjustmentTime())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf((int) getOffset().getTotalSeconds())));
        return sb.toString();
    }
}
