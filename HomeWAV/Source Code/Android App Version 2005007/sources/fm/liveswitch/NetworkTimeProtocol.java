package fm.liveswitch;

import java.util.Date;

public class NetworkTimeProtocol {
    private static NetworkTimeProtocol __absolute = new NetworkTimeProtocol();
    private static NetworkTimeProtocol __relative = new NetworkTimeProtocol(0);
    private long __baseTicks;

    public Date compactNtpToDateTime(long j) {
        return DateExtensions.createDate(compactNtpToTicks(j));
    }

    public long compactNtpToTicks(long j) {
        byte[] bytes32 = Binary.toBytes32(j, false);
        return (long) MathAssistant.round(((double) (this.__baseTicks + (((long) Binary.fromBytes16(bytes32, 0, false)) * ((long) Constants.getTicksPerSecond())))) + fractionToTicks(((long) Binary.fromBytes16(bytes32, 2, false)) * 65536));
    }

    public long dateTimeToCompactNtp(Date date) {
        return ticksToCompactNtp(DateExtensions.getTicks(date));
    }

    public long dateTimeToNtp(Date date) {
        return ticksToNtp(DateExtensions.getTicks(date));
    }

    public double dateTimeToNtpSeconds(Date date) {
        return ticksToNtpSeconds(DateExtensions.getTicks(date));
    }

    private static double fractionToTicks(long j) {
        return ((double) (j * ((long) Constants.getTicksPerSecond()))) / 4.294967296E9d;
    }

    public static NetworkTimeProtocol getAbsolute() {
        return __absolute;
    }

    public static NetworkTimeProtocol getRelative() {
        return __relative;
    }

    public long getUtcNow() {
        return dateTimeToNtp(DateExtensions.getUtcNow());
    }

    public NetworkTimeProtocol() {
        this(599266080000000000L);
    }

    public NetworkTimeProtocol(long j) {
        this.__baseTicks = 0;
        if (j >= 0) {
            this.__baseTicks = j;
            return;
        }
        throw new RuntimeException(new Exception("Network time protocol cannot be initialized with base ticks less than zero."));
    }

    public Date ntpSecondsToDateTime(double d) {
        return DateExtensions.createDate(ntpSecondsToTicks(d));
    }

    public long ntpSecondsToTicks(double d) {
        return this.__baseTicks + ((long) (d * ((double) Constants.getTicksPerSecond())));
    }

    public Date ntpToDateTime(long j) {
        return DateExtensions.createDate(ntpToTicks(j));
    }

    public long ntpToTicks(long j) {
        byte[] bytes64 = Binary.toBytes64(j, false);
        long fromBytes32 = Binary.fromBytes32(bytes64, 0, false);
        return (long) MathAssistant.round(((double) (this.__baseTicks + (fromBytes32 * ((long) Constants.getTicksPerSecond())))) + fractionToTicks(Binary.fromBytes32(bytes64, 4, false)));
    }

    public long ticksToCompactNtp(long j) {
        long j2 = j - this.__baseTicks;
        long ticksPerSecond = j2 / ((long) Constants.getTicksPerSecond());
        double ticksToFraction = ticksToFraction(j2 % ((long) Constants.getTicksPerSecond()));
        byte[] bArr = new byte[4];
        Binary.toBytes16((int) (ticksPerSecond % 65536), false, bArr, 0);
        Binary.toBytes16((int) MathAssistant.round(ticksToFraction / 65536.0d), false, bArr, 2);
        return Binary.fromBytes32(bArr, 0, false);
    }

    private static double ticksToFraction(long j) {
        return (((double) j) * 4.294967296E9d) / ((double) Constants.getTicksPerSecond());
    }

    public long ticksToNtp(long j) {
        long j2 = j - this.__baseTicks;
        long ticksPerSecond = j2 / ((long) Constants.getTicksPerSecond());
        double ticksToFraction = ticksToFraction(j2 % ((long) Constants.getTicksPerSecond()));
        byte[] bArr = new byte[8];
        Binary.toBytes32(ticksPerSecond, false, bArr, 0);
        Binary.toBytes32((long) MathAssistant.round(ticksToFraction), false, bArr, 4);
        return Binary.fromBytes64(bArr, 0, false);
    }

    public double ticksToNtpSeconds(long j) {
        return new TimeSpan(j - this.__baseTicks).getTotalSeconds();
    }
}
