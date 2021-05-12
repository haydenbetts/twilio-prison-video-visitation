package fm.liveswitch;

public class Color {
    private int _b;
    private int _g;
    private int _r;

    public Color(int i, int i2, int i3) {
        setR(MathAssistant.max(0, MathAssistant.min(255, i)));
        setG(MathAssistant.max(0, MathAssistant.min(255, i2)));
        setB(MathAssistant.max(0, MathAssistant.min(255, i3)));
    }

    public static Color fromHsb(double d, double d2, double d3) {
        double max = MathAssistant.max(0.0d, MathAssistant.min(359.0d, d));
        double max2 = MathAssistant.max(0.0d, MathAssistant.min(1.0d, d2));
        double max3 = MathAssistant.max(0.0d, MathAssistant.min(1.0d, d3));
        if (max2 == 0.0d) {
            int i = (int) ((max3 * 255.0d) + 0.5d);
            return new Color(i, i, i);
        }
        double floor = (max - MathAssistant.floor(max)) * 6.0d;
        double floor2 = floor - MathAssistant.floor(floor);
        double d4 = (1.0d - max2) * max3;
        double d5 = (1.0d - (max2 * floor2)) * max3;
        double d6 = (1.0d - (max2 * (1.0d - floor2))) * max3;
        int i2 = (int) floor;
        if (i2 == 0) {
            return new Color((int) ((max3 * 255.0d) + 0.5d), (int) ((d6 * 255.0d) + 0.5d), (int) ((d4 * 255.0d) + 0.5d));
        }
        if (i2 == 1) {
            return new Color((int) ((d5 * 255.0d) + 0.5d), (int) ((max3 * 255.0d) + 0.5d), (int) ((d4 * 255.0d) + 0.5d));
        }
        if (i2 == 2) {
            return new Color((int) ((d4 * 255.0d) + 0.5d), (int) ((max3 * 255.0d) + 0.5d), (int) ((d6 * 255.0d) + 0.5d));
        }
        if (i2 == 3) {
            return new Color((int) ((d4 * 255.0d) + 0.5d), (int) ((d5 * 255.0d) + 0.5d), (int) ((max3 * 255.0d) + 0.5d));
        }
        if (i2 == 4) {
            return new Color((int) ((d6 * 255.0d) + 0.5d), (int) ((d4 * 255.0d) + 0.5d), (int) ((max3 * 255.0d) + 0.5d));
        }
        return new Color((int) ((max3 * 255.0d) + 0.5d), (int) ((d4 * 255.0d) + 0.5d), (int) ((d5 * 255.0d) + 0.5d));
    }

    public int getB() {
        return this._b;
    }

    public static Color getBlack() {
        return new Color(0, 0, 0);
    }

    public static Color getBlue() {
        return new Color(0, 0, 255);
    }

    public static Color getCyan() {
        return new Color(0, 255, 255);
    }

    public static Color getDarkBlue() {
        return new Color(0, 0, 127);
    }

    public static Color getDarkGreen() {
        return new Color(0, 127, 0);
    }

    public static Color getDarkRed() {
        return new Color(127, 0, 0);
    }

    public int getG() {
        return this._g;
    }

    public static Color getGray() {
        return new Color(127, 127, 127);
    }

    public static Color getGreen() {
        return new Color(0, 255, 0);
    }

    public static Color getMagenta() {
        return new Color(255, 0, 255);
    }

    public static Color getOlive() {
        return new Color(127, 127, 0);
    }

    public static Color getPurple() {
        return new Color(127, 0, 127);
    }

    public int getR() {
        return this._r;
    }

    public static Color getRed() {
        return new Color(255, 0, 0);
    }

    public static Color getTeal() {
        return new Color(0, 127, 127);
    }

    public static Color getWhite() {
        return new Color(255, 255, 255);
    }

    public static Color getYellow() {
        return new Color(255, 255, 0);
    }

    private void setB(int i) {
        this._b = i;
    }

    private void setG(int i) {
        this._g = i;
    }

    private void setR(int i) {
        this._r = i;
    }
}
