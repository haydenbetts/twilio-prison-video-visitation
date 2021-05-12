package fm.liveswitch;

public class VideoConfig extends MediaConfig<VideoConfig> {
    private double __frameRate;
    private Size __size;

    public double getFrameDuration() {
        double frameRate = getFrameRate();
        if (frameRate <= 0.0d) {
            return -1.0d;
        }
        return 1000.0d / frameRate;
    }

    public double getFrameRate() {
        return this.__frameRate;
    }

    public int getHeight() {
        return getSize().getHeight();
    }

    public Size getSize() {
        return this.__size;
    }

    public int getWidth() {
        return getSize().getWidth();
    }

    public boolean isEquivalent(VideoConfig videoConfig) {
        if (super.isEquivalent(videoConfig) && getWidth() == videoConfig.getWidth() && getHeight() == videoConfig.getHeight() && getFrameRate() == videoConfig.getFrameRate()) {
            return true;
        }
        return false;
    }

    public void setFrameRate(double d) {
        if (d > 0.0d) {
            this.__frameRate = d;
            return;
        }
        throw new RuntimeException(new Exception("Frame-rate must be greater than 0."));
    }

    public void setSize(Size size) {
        if (size != null) {
            this.__size = size;
            return;
        }
        throw new RuntimeException(new Exception("Size cannot be null."));
    }

    public String toString() {
        return StringExtensions.format("Clock Rate: {0}, Width: {1}, Height: {2}, Frame Rate: {3}", new Object[]{IntegerExtensions.toString(Integer.valueOf(super.getClockRate())), IntegerExtensions.toString(Integer.valueOf(getWidth())), IntegerExtensions.toString(Integer.valueOf(getHeight())), DoubleExtensions.toString(Double.valueOf(getFrameRate()))});
    }

    public VideoConfig(Size size, double d) {
        this(size, d, 90000);
    }

    public VideoConfig(Size size, double d, int i) {
        super(i);
        setSize(size);
        setFrameRate(d);
    }

    public VideoConfig(int i, int i2, double d) {
        this(new Size(i, i2), d);
    }

    public VideoConfig(int i, int i2, double d, int i3) {
        this(new Size(i, i2), d, i3);
    }
}
