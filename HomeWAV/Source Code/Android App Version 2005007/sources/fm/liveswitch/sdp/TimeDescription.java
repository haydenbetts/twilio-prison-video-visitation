package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

public class TimeDescription {
    private ArrayList<RepeatTime> __repeatTimes = new ArrayList<>();
    private Timing _timing;

    public void addRepeatTime(RepeatTime repeatTime) {
        this.__repeatTimes.add(repeatTime);
    }

    public RepeatTime[] getRepeatTimes() {
        return (RepeatTime[]) this.__repeatTimes.toArray(new RepeatTime[0]);
    }

    public Timing getTiming() {
        return this._timing;
    }

    public static TimeDescription parse(String str) {
        String[] splitAndClean = Utility.splitAndClean(str);
        if (splitAndClean[0].charAt(0) != 't') {
            return null;
        }
        TimeDescription timeDescription = new TimeDescription(Timing.parse(splitAndClean[0]));
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) splitAndClean); i++) {
            String str2 = splitAndClean[i];
            if (str2.charAt(0) == 'r') {
                timeDescription.addRepeatTime(RepeatTime.parse(str2));
            }
        }
        return timeDescription;
    }

    public boolean removeRepeatTime(RepeatTime repeatTime) {
        return this.__repeatTimes.remove(repeatTime);
    }

    private void setTiming(Timing timing) {
        this._timing = timing;
    }

    public TimeDescription(Timing timing) {
        if (timing != null) {
            setTiming(timing);
            return;
        }
        throw new RuntimeException(new Exception("timing cannot be null."));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, StringExtensions.concat(getTiming().toString(), "\r\n"));
        for (RepeatTime repeatTime : getRepeatTimes()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(repeatTime.toString(), "\r\n"));
        }
        return sb.toString();
    }
}
