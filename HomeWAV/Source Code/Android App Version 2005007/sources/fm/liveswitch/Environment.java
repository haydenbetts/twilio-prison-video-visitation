package fm.liveswitch;

import java.util.Date;

public class Environment {
    public static int getTickCount() {
        return (int) DateExtensions.getTicks(new Date());
    }
}
