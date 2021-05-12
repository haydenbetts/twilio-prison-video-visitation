package fm.liveswitch;

public abstract class LockedRandomizer {
    private static Object _randomLock = new Object();
    private static Randomizer _randomizer = new Randomizer();

    public static int next() {
        int next;
        synchronized (_randomLock) {
            next = _randomizer.next();
        }
        return next;
    }

    public static int next(int i) {
        int next;
        synchronized (_randomLock) {
            next = _randomizer.next(i);
        }
        return next;
    }

    public static int next(int i, int i2) {
        int next;
        synchronized (_randomLock) {
            next = _randomizer.next(i, i2);
        }
        return next;
    }

    public static void nextBytes(byte[] bArr) {
        synchronized (_randomLock) {
            _randomizer.nextBytes(bArr);
        }
    }

    public static double nextDouble() {
        double nextDouble;
        synchronized (_randomLock) {
            nextDouble = _randomizer.nextDouble();
        }
        return nextDouble;
    }

    public static long nextLong() {
        long nextDouble;
        synchronized (_randomLock) {
            nextDouble = (long) (_randomizer.nextDouble() * 9.22337203685478E18d);
        }
        return nextDouble;
    }

    public static String randomString(int i) {
        String randomString;
        synchronized (_randomLock) {
            randomString = _randomizer.randomString(i);
        }
        return randomString;
    }
}
