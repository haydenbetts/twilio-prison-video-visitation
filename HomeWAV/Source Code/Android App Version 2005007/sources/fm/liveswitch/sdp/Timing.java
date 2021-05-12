package fm.liveswitch.sdp;

import fm.liveswitch.DoubleExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;

public class Timing {
    private double _startTime;
    private double _stopTime;

    public double getStartTime() {
        return this._startTime;
    }

    public double getStopTime() {
        return this._stopTime;
    }

    public static Timing parse(String str) {
        String[] split = StringExtensions.split(str.substring(2), new char[]{' '});
        double d = 0.0d;
        double parseDoubleValue = !Global.equals(split[0], "0") ? ParseAssistant.parseDoubleValue(split[0]) : 0.0d;
        if (!Global.equals(split[1], "0")) {
            d = ParseAssistant.parseDoubleValue(split[1]);
        }
        Timing timing = new Timing();
        timing.setStartTime(parseDoubleValue);
        timing.setStopTime(d);
        return timing;
    }

    public void setStartTime(double d) {
        this._startTime = d;
    }

    public void setStopTime(double d) {
        this._stopTime = d;
    }

    public Timing() {
        setStartTime(0.0d);
        setStopTime(0.0d);
    }

    public Timing(double d, double d2) {
        setStartTime(d);
        setStopTime(d2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "t=");
        if (getStartTime() != 0.0d) {
            StringBuilderExtensions.append(sb, DoubleExtensions.toString(Double.valueOf(getStartTime())));
        } else {
            StringBuilderExtensions.append(sb, "0");
        }
        StringBuilderExtensions.append(sb, " ");
        if (getStopTime() != 0.0d) {
            StringBuilderExtensions.append(sb, DoubleExtensions.toString(Double.valueOf(getStopTime())));
        } else {
            StringBuilderExtensions.append(sb, "0");
        }
        return sb.toString();
    }
}
