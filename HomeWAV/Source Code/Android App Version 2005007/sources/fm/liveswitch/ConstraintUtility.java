package fm.liveswitch;

class ConstraintUtility {
    public static double coalesce(double d, double d2) {
        return d != -1.0d ? d : d2;
    }

    public static int coalesce(int i, int i2) {
        return i != -1 ? i : i2;
    }

    public static long coalesce(long j, long j2) {
        return j != -1 ? j : j2;
    }

    public static boolean overConstrained(double d, double d2) {
        return d >= 0.0d && d2 >= 0.0d && d > d2;
    }

    public static boolean overConstrained(int i, int i2) {
        return i >= 0 && i2 >= 0 && i > i2;
    }

    public static boolean overConstrained(long j, long j2) {
        return j >= 0 && j2 >= 0 && j > j2;
    }

    private static double clamp(double d, double d2, double d3, boolean z) {
        int i = (d2 > -1.0d ? 1 : (d2 == -1.0d ? 0 : -1));
        if (i == 0 && d3 == -1.0d) {
            return d;
        }
        if (d3 == -1.0d) {
            return d == -1.0d ? d : MathAssistant.max(d, d2);
        }
        if (i == 0) {
            if (d == -1.0d) {
                return d3;
            }
            return MathAssistant.min(d, d3);
        } else if (d == -1.0d) {
            return z ? d2 : d3;
        } else {
            return MathAssistant.min(MathAssistant.max(d, d2), d3);
        }
    }

    private static int clamp(int i, int i2, int i3, boolean z) {
        if (i2 == -1 && i3 == -1) {
            return i;
        }
        if (i3 == -1) {
            return i == -1 ? i : MathAssistant.max(i, i2);
        }
        if (i2 == -1) {
            if (i == -1) {
                return i3;
            }
            return MathAssistant.min(i, i3);
        } else if (i == -1) {
            return z ? i2 : i3;
        } else {
            return MathAssistant.min(MathAssistant.max(i, i2), i3);
        }
    }

    private static long clamp(long j, long j2, long j3, boolean z) {
        int i = (j2 > -1 ? 1 : (j2 == -1 ? 0 : -1));
        if (i == 0 && j3 == -1) {
            return j;
        }
        if (j3 == -1) {
            return j == -1 ? j : MathAssistant.max(j, j2);
        }
        if (i == 0) {
            if (j == -1) {
                return j3;
            }
            return MathAssistant.min(j, j3);
        } else if (j == -1) {
            return z ? j2 : j3;
        } else {
            return MathAssistant.min(MathAssistant.max(j, j2), j3);
        }
    }

    private static Size clamp(Size size, Size size2, Size size3, boolean z) {
        if (size2 == null && size3 == null) {
            return size;
        }
        if (size3 == null) {
            return size == null ? size : new Size(max(size.getWidth(), size2.getWidth()), max(size.getHeight(), size2.getHeight()));
        }
        if (size2 == null) {
            if (size == null) {
                return size3;
            }
            return new Size(min(size.getWidth(), size3.getWidth()), min(size.getHeight(), size3.getHeight()));
        } else if (size == null) {
            return z ? size2 : size3;
        } else {
            return new Size(min(max(size.getWidth(), size2.getWidth()), size3.getWidth()), min(max(size.getHeight(), size2.getHeight()), size3.getHeight()));
        }
    }

    public static double clampMax(double d, double d2, double d3) {
        return clamp(d, d2, d3, false);
    }

    public static int clampMax(int i, int i2, int i3) {
        return clamp(i, i2, i3, false);
    }

    public static long clampMax(long j, long j2, long j3) {
        return clamp(j, j2, j3, false);
    }

    public static Size clampMax(Size size, Size size2, Size size3) {
        return clamp(size, size2, size3, false);
    }

    public static double clampMin(double d, double d2, double d3) {
        return clamp(d, d2, d3, true);
    }

    public static int clampMin(int i, int i2, int i3) {
        return clamp(i, i2, i3, true);
    }

    public static long clampMin(long j, long j2, long j3) {
        return clamp(j, j2, j3, true);
    }

    public static Size clampMin(Size size, Size size2, Size size3) {
        return clamp(size, size2, size3, true);
    }

    public static int getHeight(Size size) {
        if (size == null) {
            return -1;
        }
        return size.getHeight();
    }

    public static int getWidth(Size size) {
        if (size == null) {
            return -1;
        }
        return size.getWidth();
    }

    public static double max(double d, double d2) {
        if (d < 0.0d) {
            return d2;
        }
        return d2 < 0.0d ? d : MathAssistant.max(d, d2);
    }

    public static int max(int i, int i2) {
        if (i < 0) {
            return i2;
        }
        return i2 < 0 ? i : MathAssistant.max(i, i2);
    }

    public static long max(long j, long j2) {
        if (j < 0) {
            return j2;
        }
        return j2 < 0 ? j : MathAssistant.max(j, j2);
    }

    public static Size max(Size size, Size size2) {
        if (size == null) {
            return size2;
        }
        return size2 == null ? size : new Size(max(size.getWidth(), size2.getWidth()), max(size.getHeight(), size2.getHeight()));
    }

    public static double min(double d, double d2) {
        if (d < 0.0d) {
            return d2;
        }
        return d2 < 0.0d ? d : MathAssistant.min(d, d2);
    }

    public static int min(int i, int i2) {
        if (i < 0) {
            return i2;
        }
        return i2 < 0 ? i : MathAssistant.min(i, i2);
    }

    public static long min(long j, long j2) {
        if (j < 0) {
            return j2;
        }
        return j2 < 0 ? j : MathAssistant.min(j, j2);
    }

    public static Size min(Size size, Size size2) {
        if (size == null) {
            return size2;
        }
        return size2 == null ? size : new Size(min(size.getWidth(), size2.getWidth()), min(size.getHeight(), size2.getHeight()));
    }

    public static boolean overConstrained(Size size, Size size2) {
        if (size == null || size2 == null) {
            return false;
        }
        return (size.getWidth() >= 0 && size2.getWidth() >= 0 && size.getWidth() > size2.getWidth()) || (size.getHeight() >= 0 && size2.getHeight() >= 0 && size.getHeight() > size2.getHeight());
    }
}
