package fm.liveswitch;

public class ScreenConfig extends MediaConfig<ScreenConfig> {
    private double __frameRate;
    private Rectangle __region;

    public double getFrameRate() {
        return this.__frameRate;
    }

    public int getHeight() {
        return getSize().getHeight();
    }

    public Point getOrigin() {
        return getRegion().getOrigin();
    }

    public Rectangle getRegion() {
        return this.__region;
    }

    public Size getSize() {
        return getRegion().getSize();
    }

    public int getWidth() {
        return getSize().getWidth();
    }

    public int getX() {
        return getOrigin().getX();
    }

    public int getY() {
        return getOrigin().getY();
    }

    public boolean isEquivalent(ScreenConfig screenConfig) {
        if (super.isEquivalent(screenConfig) && getX() == screenConfig.getX() && getY() == screenConfig.getY() && getWidth() == screenConfig.getWidth() && getHeight() == screenConfig.getHeight() && getFrameRate() == screenConfig.getFrameRate()) {
            return true;
        }
        return false;
    }

    public ScreenConfig(Point point, Size size, double d) {
        this(new Rectangle(point, size), d);
    }

    public ScreenConfig(Point point, Size size, double d, int i) {
        this(new Rectangle(point, size), d, i);
    }

    public ScreenConfig(Rectangle rectangle, double d) {
        this(rectangle, d, 90000);
    }

    public ScreenConfig(Rectangle rectangle, double d, int i) {
        super(i);
        setRegion(rectangle);
        setFrameRate(d);
    }

    public ScreenConfig(int i, int i2, int i3, int i4, double d) {
        this(new Point(i, i2), new Size(i3, i4), d);
    }

    public ScreenConfig(int i, int i2, int i3, int i4, double d, int i5) {
        this(new Point(i, i2), new Size(i3, i4), d, i5);
    }

    public void setFrameRate(double d) {
        if (d > 0.0d) {
            this.__frameRate = d;
            return;
        }
        throw new RuntimeException(new Exception("Frame-rate must be greater than 0."));
    }

    public void setOrigin(Point point) {
        getRegion().setOrigin(point);
    }

    public void setRegion(Rectangle rectangle) {
        if (rectangle != null) {
            this.__region = rectangle;
            return;
        }
        throw new RuntimeException(new Exception("Region cannot be null."));
    }

    public void setSize(Size size) {
        getRegion().setSize(size);
    }

    public String toString() {
        return StringExtensions.format("Clock Rate: {0}, X: {1}, Y: {2} Width: {3}, Height: {4}, Frame Rate: {5}", new Object[]{IntegerExtensions.toString(Integer.valueOf(super.getClockRate())), IntegerExtensions.toString(Integer.valueOf(getX())), IntegerExtensions.toString(Integer.valueOf(getY())), IntegerExtensions.toString(Integer.valueOf(getWidth())), IntegerExtensions.toString(Integer.valueOf(getHeight())), DoubleExtensions.toString(Double.valueOf(getFrameRate()))});
    }
}
