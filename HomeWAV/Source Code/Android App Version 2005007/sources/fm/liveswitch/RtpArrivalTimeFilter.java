package fm.liveswitch;

class RtpArrivalTimeFilter {
    private static double __chi = 0.05d;
    private static int __k = 5;
    private static double __q11 = MathAssistant.pow(10.0d, -13.0d);
    private static double __q12 = 0.0d;
    private static double __q21 = 0.0d;
    private static double __q22 = MathAssistant.pow(10.0d, -3.0d);
    private double __bitrate;
    private long __e11;
    private long __e12;
    private long __e21;
    private long __e22;
    private long[] __interdepartureTimes;
    private long __observation;
    private int __packetGroupSize;
    private double __timeToSendOffset;
    private double __variance;

    public RtpArrivalTimeFilter() {
        this.__bitrate = 1.0d;
        this.__timeToSendOffset = 0.0d;
        this.__packetGroupSize = 0;
        this.__e11 = 0;
        this.__e12 = 0;
        this.__e21 = 0;
        this.__e22 = 0;
        this.__interdepartureTimes = null;
        this.__observation = 0;
        this.__variance = 0.0d;
        this.__interdepartureTimes = new long[__k];
    }

    public Pair<Long, Long> smooth(int i, int i2, int i3) {
        int i4 = i;
        this.__interdepartureTimes[(int) (this.__observation % ((long) __k))] = (long) i4;
        double d = (double) (i3 - this.__packetGroupSize);
        double d2 = (((double) (i2 - i4)) - (d / this.__bitrate)) - this.__timeToSendOffset;
        long j = 0;
        for (int i5 = 0; ((long) i5) <= this.__observation; i5++) {
            j = MathAssistant.max(this.__interdepartureTimes[i5], j);
        }
        double pow = MathAssistant.pow(1.0d - __chi, (double) (30 / (j * 1000)));
        double sqrt = (MathAssistant.sqrt(this.__variance) * 3.0d >= d2 || this.__observation <= 2) ? d2 : MathAssistant.sqrt(this.__variance) * 3.0d;
        this.__variance = MathAssistant.max((this.__variance * pow) + ((1.0d - pow) * sqrt * sqrt), 1.0d);
        long j2 = this.__e11;
        double d3 = __q11;
        long j3 = this.__e12;
        double d4 = __q12;
        double d5 = d2;
        long j4 = this.__e21;
        double d6 = ((((double) j2) + d3) * d) + ((double) j3) + d4;
        double d7 = __q21;
        double d8 = d4;
        long j5 = this.__e22;
        long j6 = j3;
        double d9 = __q22;
        double d10 = ((((((double) j2) + d3) * d) + ((double) j4) + d7) * d) + (d * (((double) j6) + d8)) + ((double) j5) + d9;
        double d11 = ((((((double) j4) + d7) * d) + ((double) j5)) + d9) / d10;
        double d12 = d5 * (d6 / d10);
        if (d12 == 0.0d) {
            d12 = 0.001d;
        }
        double d13 = this.__bitrate + (1.0d / d12);
        if (d13 > 0.0d) {
            this.__bitrate = d13;
        }
        double d14 = this.__timeToSendOffset + (d5 * d11);
        if (d14 > 0.0d) {
            this.__timeToSendOffset = d14;
        }
        this.__packetGroupSize = i3;
        this.__observation++;
        return new Pair<>(Long.valueOf((long) this.__bitrate), Long.valueOf((long) this.__timeToSendOffset));
    }
}
