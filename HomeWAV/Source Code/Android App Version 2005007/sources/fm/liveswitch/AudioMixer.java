package fm.liveswitch;

import fm.liveswitch.pcm.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AudioMixer extends AudioPipe {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(AudioMixer.class);
    private HashMap<Long, CircularBuffer> __buffers;
    private Object __buffersLock;
    private ManagedThread __thread;
    private volatile boolean __threadActive;
    private int _frameDuration;
    private int _latency;

    /* access modifiers changed from: protected */
    public boolean getAllowDurationTimer() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean getAllowInputRateTimer() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean getIsMixer() {
        return true;
    }

    public String getLabel() {
        return "Audio Mixer";
    }

    public AudioMixer(AudioConfig audioConfig) {
        this(audioConfig, 0);
    }

    public AudioMixer(AudioConfig audioConfig, int i) {
        this(audioConfig, i, 0);
    }

    public AudioMixer(AudioConfig audioConfig, int i, int i2) {
        this((AudioFormat) new Format(audioConfig), i, i2);
    }

    public AudioMixer(AudioFormat audioFormat) {
        this(audioFormat, 0);
    }

    public AudioMixer(AudioFormat audioFormat, int i) {
        this(audioFormat, i, 0);
    }

    public AudioMixer(AudioFormat audioFormat, int i, int i2) {
        super(audioFormat.clone(), audioFormat.clone());
        this.__buffers = new HashMap<>();
        this.__buffersLock = new Object();
        this.__threadActive = false;
        byte[] bArr = new byte[4];
        LockedRandomizer.nextBytes(bArr);
        super.setOutputSynchronizationSource(Binary.fromBytes32(bArr, 0, false));
        if (audioFormat.getIsPcm()) {
            i2 = i2 <= 0 ? 20 : i2;
            i = i <= 0 ? 200 : i;
            setFrameDuration(i2);
            setLatency(i);
            this.__threadActive = true;
            ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                public String getId() {
                    return "fm.liveswitch.AudioMixer.loop";
                }

                public void invoke(ManagedThread managedThread) {
                    AudioMixer.this.loop(managedThread);
                }
            });
            this.__thread = managedThread;
            managedThread.start();
            return;
        }
        throw new RuntimeException(new Exception("Cannot mix non-PCM audio."));
    }

    public AudioMixer(IAudioOutput iAudioOutput) {
        this(iAudioOutput, 0);
    }

    public AudioMixer(IAudioOutput iAudioOutput, int i) {
        this(iAudioOutput, i, 0);
    }

    public AudioMixer(IAudioOutput iAudioOutput, int i, int i2) {
        this((AudioFormat) iAudioOutput.getOutputFormat(), i, i2);
        super.addInput(iAudioOutput);
    }

    public AudioMixer(IAudioOutput[] iAudioOutputArr) {
        this(iAudioOutputArr, 0);
    }

    public AudioMixer(IAudioOutput[] iAudioOutputArr, int i) {
        this(iAudioOutputArr, i, 0);
    }

    public AudioMixer(IAudioOutput[] iAudioOutputArr, int i, int i2) {
        this((AudioFormat) iAudioOutputArr[0].getOutputFormat(), i, i2);
        super.addInputs((TIOutput[]) iAudioOutputArr);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        this.__threadActive = false;
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        CircularBuffer circularBuffer;
        synchronized (this.__buffersLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__buffers, Long.valueOf(audioFrame.getSynchronizationSource()), holder);
            circularBuffer = (CircularBuffer) holder.getValue();
            if (!tryGetValue) {
                circularBuffer = new CircularBuffer(getCircularBufferLength(), (((getLatency() * super.getConfig().getClockRate()) * super.getConfig().getChannelCount()) * 2) / 1000, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
                HashMapExtensions.set(HashMapExtensions.getItem(this.__buffers), Long.valueOf(audioFrame.getSynchronizationSource()), circularBuffer);
            }
        }
        circularBuffer.write(audioBuffer.getDataBuffer());
    }

    private int getCircularBufferLength() {
        return ((((super.getConfig().getClockRate() * super.getConfig().getChannelCount()) * 2) * getLatency()) * 2) / 1000;
    }

    public int getFrameDuration() {
        return this._frameDuration;
    }

    public int getLatency() {
        return this._latency;
    }

    /* access modifiers changed from: private */
    public void loop(ManagedThread managedThread) {
        int clockRate = (((super.getConfig().getClockRate() * super.getConfig().getChannelCount()) * 2) * getFrameDuration()) / 1000;
        ArrayList arrayList = new ArrayList();
        ManagedStopwatch managedStopwatch = new ManagedStopwatch();
        managedStopwatch.start();
        double d = 0.0d;
        while (this.__threadActive) {
            managedThread.loopBegin();
            long elapsedMilliseconds = managedStopwatch.getElapsedMilliseconds();
            while (d < ((double) elapsedMilliseconds)) {
                arrayList.clear();
                synchronized (this.__buffersLock) {
                    for (CircularBuffer add : HashMapExtensions.getValues(this.__buffers)) {
                        arrayList.add(add);
                    }
                }
                DataBuffer take = __dataBufferPool.take(clockRate, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    DataBuffer read = ((CircularBuffer) it.next()).read(take.getLength());
                    if (read != null) {
                        arrayList2.add(read);
                    }
                }
                take.set((byte) 0);
                if (ArrayListExtensions.getCount(arrayList2) > 0) {
                    for (int i = 0; i < clockRate; i += 2) {
                        int i2 = 0;
                        for (int i3 = 0; i3 < ArrayListExtensions.getCount(arrayList2); i3++) {
                            i2 += ((DataBuffer) ArrayListExtensions.getItem(arrayList2).get(i3)).read16(i);
                        }
                        take.write16(i2, i);
                    }
                }
                raiseFrame(new AudioFrame(getFrameDuration(), new AudioBuffer(take, (AudioFormat) super.getOutputFormat())));
                take.free();
                d += (double) getFrameDuration();
            }
            int frameDuration = ((int) elapsedMilliseconds) % getFrameDuration();
            if (frameDuration == 0) {
                frameDuration = getFrameDuration();
            }
            ManagedThread.sleep(frameDuration);
            managedThread.loopEnd();
        }
        managedStopwatch.stop();
    }

    private void setFrameDuration(int i) {
        this._frameDuration = i;
    }

    private void setLatency(int i) {
        this._latency = i;
    }
}
