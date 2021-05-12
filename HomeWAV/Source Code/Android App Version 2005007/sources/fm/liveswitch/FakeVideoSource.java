package fm.liveswitch;

public class FakeVideoSource extends CameraSourceBase {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(FakeVideoSource.class);
    private double __brightness;
    private double __hue;
    private double __saturation;
    private ManagedTimer __timer;

    public String getLabel() {
        return "Fake Video Source";
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        Promise promise = new Promise();
        try {
            this.__hue = 0.0d;
            this.__saturation = 1.0d;
            this.__brightness = 1.0d;
            setConfig(getTargetConfig());
            ManagedTimer managedTimer = new ManagedTimer((int) (1000.0d / getConfig().getFrameRate()), new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.FakeVideoSource.raiseData";
                }

                public void invoke() {
                    FakeVideoSource.this.raiseData();
                }
            });
            this.__timer = managedTimer;
            managedTimer.start();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        return this.__timer.stopAsync();
    }

    public FakeVideoSource(VideoConfig videoConfig) {
        this(videoConfig, VideoFormat.getBgr());
    }

    public FakeVideoSource(VideoConfig videoConfig, VideoFormat videoFormat) {
        super(videoFormat, videoConfig);
    }

    /* access modifiers changed from: private */
    public void raiseData() {
        VideoConfig config = getConfig();
        VideoFormat videoFormat = (VideoFormat) super.getOutputFormat();
        DataBuffer take = __dataBufferPool.take(VideoBuffer.getMinimumBufferLength(config.getWidth(), config.getHeight(), videoFormat.getName()));
        try {
            Color fromHsb = Color.fromHsb(this.__hue, this.__saturation, this.__brightness);
            double d = this.__hue + 0.005d;
            this.__hue = d;
            if (d >= 1.0d) {
                this.__hue = 0.0d;
            }
            raiseFrame(new VideoFrame(VideoBuffer.createCustom(config.getWidth(), config.getHeight(), fromHsb.getR(), fromHsb.getG(), fromHsb.getB(), videoFormat.getName(), take)));
        } catch (Exception e) {
            Log.error("Could not raise frame.", e);
        } catch (Throwable th) {
            take.free();
            throw th;
        }
        take.free();
    }
}
