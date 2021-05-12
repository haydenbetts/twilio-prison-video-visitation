package fm.liveswitch;

import fm.liveswitch.pcm.Format;

public class FakeAudioSource extends AudioSource {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(FakeAudioSource.class);
    private AudioClock __clock;
    private long __signalCounter;
    private int _amplitude;
    private float _frequency;
    private int _latency;

    public String getLabel() {
        return "Fake Audio Source";
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStart() {
        Promise promise = new Promise();
        try {
            this.__signalCounter = 0;
            AudioClock audioClock = new AudioClock(super.getConfig().getClockRate(), super.getConfig().getChannelCount(), new IActionDelegate2<Integer, Integer>() {
                public String getId() {
                    return "fm.liveswitch.FakeAudioSource.raiseData";
                }

                public void invoke(Integer num, Integer num2) {
                    FakeAudioSource.this.raiseData(num.intValue(), num2.intValue());
                }
            });
            this.__clock = audioClock;
            audioClock.start();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    /* access modifiers changed from: protected */
    public Future<Object> doStop() {
        Promise promise = new Promise();
        try {
            this.__clock.stop();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
        return promise;
    }

    public FakeAudioSource(AudioConfig audioConfig) {
        this(audioConfig, 440.0f);
    }

    public FakeAudioSource(AudioConfig audioConfig, float f) {
        this(audioConfig, f, 16384);
    }

    public FakeAudioSource(AudioConfig audioConfig, float f, int i) {
        this(audioConfig, f, i, 100);
    }

    public FakeAudioSource(AudioConfig audioConfig, float f, int i, int i2) {
        super(new Format(audioConfig));
        setFrequency(f);
        setAmplitude(i);
        setLatency(i2);
        super.setOutputSynchronizable(true);
        super.setDisableTimestampReset(true);
    }

    private void fillDataBuffer(int i, DataBuffer dataBuffer) {
        int clockRate = (((AudioFormat) super.getOutputFormat()).getClockRate() * i) / 1000;
        double pi = ((MathAssistant.getPi() * 2.0d) * ((double) getFrequency())) / ((double) ((AudioFormat) super.getOutputFormat()).getClockRate());
        int i2 = 0;
        for (int i3 = 0; i3 < clockRate; i3++) {
            short amplitude = (short) ((int) (((double) getAmplitude()) * MathAssistant.sin(((double) this.__signalCounter) * pi)));
            long j = this.__signalCounter + 1;
            this.__signalCounter = j;
            if (j >= Long.MAX_VALUE) {
                this.__signalCounter = 0;
            }
            for (int i4 = 0; i4 < ((AudioFormat) super.getOutputFormat()).getChannelCount(); i4++) {
                dataBuffer.write16(amplitude, i2);
                i2 += 2;
            }
        }
    }

    public int getAmplitude() {
        return this._amplitude;
    }

    public float getFrequency() {
        return this._frequency;
    }

    public int getLatency() {
        return this._latency;
    }

    /* access modifiers changed from: private */
    public void raiseData(int i, int i2) {
        DataBuffer take = __dataBufferPool.take(i, true);
        try {
            fillDataBuffer(i2, take);
            raiseFrame(new AudioFrame(i2, new AudioBuffer(take, (AudioFormat) super.getOutputFormat())));
        } catch (Exception e) {
            Log.error("Could not raise frame.", e);
        } catch (Throwable th) {
            take.free();
            throw th;
        }
        take.free();
    }

    private void setAmplitude(int i) {
        this._amplitude = i;
    }

    private void setFrequency(float f) {
        this._frequency = f;
    }

    private void setLatency(int i) {
        this._latency = i;
    }
}
