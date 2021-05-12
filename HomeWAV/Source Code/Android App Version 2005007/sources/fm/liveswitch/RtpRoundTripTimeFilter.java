package fm.liveswitch;

class RtpRoundTripTimeFilter {
    private static ILog __log = Log.getLogger(RtpRoundTripTimeFilter.class);
    private double __avgRoundTripTime;
    private int[] __driftBuffer = new int[5];
    private int __driftCount;
    private int __filtFactCount;
    private boolean __gotNonZeroUpdate;
    private int[] __jumpBuffer = new int[5];
    private int __jumpCount;
    private String __logScope;
    private int __maxRoundTripTime;
    private double __varRoundTripTime;

    private boolean driftDetection(int i) {
        if (((double) this.__maxRoundTripTime) - this.__avgRoundTripTime > MathAssistant.sqrt(this.__varRoundTripTime) * 3.5d) {
            int i2 = this.__driftCount;
            if (i2 < 5) {
                this.__driftBuffer[i2] = i;
                this.__driftCount = i2 + 1;
            }
            int i3 = this.__driftCount;
            if (i3 >= 5) {
                shortRoundTripTimeFilter(this.__driftBuffer, i3);
                this.__filtFactCount = 6;
                this.__driftCount = 0;
                if (__log.getIsVerboseEnabled()) {
                    __log.verbose(this.__logScope, "Detected a round-trip-time drift.");
                }
            }
        } else {
            this.__driftCount = 0;
        }
        return true;
    }

    public int getRoundTripTime() {
        return this.__maxRoundTripTime;
    }

    private boolean jumpDetection(int i) {
        double d = this.__avgRoundTripTime - ((double) i);
        if (MathAssistant.abs(d) > MathAssistant.sqrt(this.__varRoundTripTime) * 2.5d) {
            int i2 = -1;
            int i3 = d >= 0.0d ? 1 : -1;
            if (this.__jumpCount >= 0) {
                i2 = 1;
            }
            if (i3 != i2) {
                this.__jumpCount = 0;
            }
            if (MathAssistant.abs(this.__jumpCount) < 5) {
                this.__jumpBuffer[MathAssistant.abs(this.__jumpCount)] = i;
                this.__jumpCount += i3;
            }
            if (MathAssistant.abs(this.__jumpCount) < 5) {
                return false;
            }
            shortRoundTripTimeFilter(this.__jumpBuffer, MathAssistant.abs(this.__jumpCount));
            this.__filtFactCount = 6;
            this.__jumpCount = 0;
            if (__log.getIsVerboseEnabled()) {
                __log.verbose(this.__logScope, "Detected a round-trip-time jump.");
            }
        } else {
            this.__jumpCount = 0;
        }
        return true;
    }

    public void reset() {
        this.__gotNonZeroUpdate = false;
        this.__avgRoundTripTime = 0.0d;
        this.__varRoundTripTime = 0.0d;
        this.__maxRoundTripTime = 0;
        this.__filtFactCount = 1;
        this.__jumpCount = 0;
        this.__driftCount = 0;
        for (int i = 0; i < ArrayExtensions.getLength(this.__jumpBuffer); i++) {
            this.__jumpBuffer[i] = 0;
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength(this.__driftBuffer); i2++) {
            this.__driftBuffer[i2] = 0;
        }
    }

    public RtpRoundTripTimeFilter(String str) {
        this.__logScope = str;
        reset();
    }

    private void shortRoundTripTimeFilter(int[] iArr, int i) {
        if (i != 0) {
            this.__maxRoundTripTime = 0;
            this.__avgRoundTripTime = 0.0d;
            for (int i2 = 0; i2 < i; i2++) {
                if (iArr[i2] > this.__maxRoundTripTime) {
                    this.__maxRoundTripTime = iArr[i2];
                }
                this.__avgRoundTripTime += (double) iArr[i2];
            }
            this.__avgRoundTripTime /= (double) i;
        }
    }

    public void update(int i) {
        if (!this.__gotNonZeroUpdate) {
            if (i != 0) {
                this.__gotNonZeroUpdate = true;
            } else {
                return;
            }
        }
        if (i > 3000) {
            i = 3000;
        }
        double d = 0.0d;
        int i2 = this.__filtFactCount;
        if (i2 > 1) {
            d = ((double) (i2 - 1)) / ((double) i2);
        }
        int i3 = i2 + 1;
        this.__filtFactCount = i3;
        if (i3 > 35) {
            this.__filtFactCount = 35;
        }
        double d2 = this.__avgRoundTripTime;
        double d3 = this.__varRoundTripTime;
        double d4 = 1.0d - d;
        double d5 = (double) i;
        double d6 = (d * d2) + (d4 * d5);
        this.__avgRoundTripTime = d6;
        this.__varRoundTripTime = (d * d3) + (d4 * (d5 - d6) * (d5 - d6));
        this.__maxRoundTripTime = MathAssistant.max(i, this.__maxRoundTripTime);
        if (!jumpDetection(i) || !driftDetection(i)) {
            this.__avgRoundTripTime = d2;
            this.__varRoundTripTime = d3;
        }
    }
}
