package fm.liveswitch.android;

import android.media.AudioTrack;
import android.os.Process;
import com.google.android.exoplayer2.C;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.AudioSink;
import fm.liveswitch.BitAssistant;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IAction1;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.ILog;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.pcm.Format;

public class AudioTrackSink extends AudioSink {
    private static ILog log = Log.getLogger(AudioTrackSink.class);
    private int audioStreamType;
    private AudioTrack audioTrack;
    private int bufferLengthMillis;
    int bufferSize;
    int channelConfig;
    int channelCount;
    private int chunkDivisor;
    private int chunkSize;
    private int chunkTime;
    int clockRate;
    private volatile boolean isRendering;
    private volatile boolean isStopped;
    private int latencyMillis;
    private int sampleLength;
    private byte[] samples;
    private long samplesReadCount;
    private int samplesReadIndex;
    private long samplesWriteCount;
    private int samplesWriteIndex;

    public String getLabel() {
        return "Android AudioTrack Sink";
    }

    public int getAudioStreamType() {
        return this.audioStreamType;
    }

    public void setAudioStreamType(int i) {
        this.audioStreamType = i;
        shutdown();
        startup();
    }

    public static int getBufferDelay(AudioConfig audioConfig) {
        int clockRate2 = audioConfig.getClockRate();
        int channelCount2 = audioConfig.getChannelCount();
        return AudioTrack.getMinBufferSize(clockRate2, channelCount2 == 1 ? 4 : 12, 2) / (((clockRate2 * channelCount2) * 2) / 1000);
    }

    public AudioTrackSink(AudioConfig audioConfig) {
        super(new Format(audioConfig));
        this.audioStreamType = 0;
        this.sampleLength = 2;
        this.latencyMillis = 200;
        this.bufferLengthMillis = 200 * 2;
        this.chunkDivisor = 2;
        this.isRendering = false;
        this.isStopped = true;
        initialize();
        startup();
    }

    public AudioTrackSink(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        addInput(iAudioOutput);
    }

    public AudioTrackSink(IAudioOutput[] iAudioOutputArr) {
        this(iAudioOutputArr[0].getConfig());
        addInputs((TIOutput[]) iAudioOutputArr);
    }

    private void initialize() {
        this.clockRate = ((AudioFormat) getInputFormat()).getClockRate();
        int channelCount2 = ((AudioFormat) getInputFormat()).getChannelCount();
        this.channelCount = channelCount2;
        int i = channelCount2 == 1 ? 4 : 12;
        this.channelConfig = i;
        int minBufferSize = AudioTrack.getMinBufferSize(this.clockRate, i, 2);
        this.bufferSize = minBufferSize;
        int i2 = minBufferSize / this.chunkDivisor;
        this.chunkSize = i2;
        int i3 = this.clockRate;
        int i4 = this.channelCount;
        int i5 = this.sampleLength;
        this.chunkTime = (i2 * 1000) / ((i3 * i4) * i5);
        this.samplesReadIndex = 0;
        this.samplesWriteIndex = 0;
        this.samplesReadCount = 0;
        this.samplesWriteCount = 0;
        this.samples = new byte[((((i3 * i4) * i5) * this.bufferLengthMillis) / 1000)];
    }

    private void startup() {
        AudioTrack audioTrack2 = new AudioTrack(getAudioStreamType(), this.clockRate, this.channelConfig, 2, this.bufferSize, 1);
        this.audioTrack = audioTrack2;
        if (audioTrack2.getState() == 1) {
            this.isRendering = true;
            this.isStopped = false;
            new ManagedThread(new IAction1<ManagedThread>() {
                public void invoke(ManagedThread managedThread) {
                    AudioTrackSink.this.renderLoop();
                }
            }).start();
            return;
        }
        throw new RuntimeException("Device does not support requested audio format.");
    }

    private void shutdown() {
        this.isRendering = false;
        while (!this.isStopped) {
            ManagedThread.sleep(10);
        }
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.release();
            this.audioTrack = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        shutdown();
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        if (this.audioTrack != null) {
            DataBuffer dataBuffer = audioBuffer.getDataBuffer();
            if (((int) (this.samplesWriteCount - this.samplesReadCount)) + dataBuffer.getLength() <= this.samples.length) {
                writeToSamples(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
            } else {
                log.debug("AudioTrackSink buffer overrun.");
            }
        } else {
            throw new RuntimeException("AudioTrackSink must be opened before a frame can be processed!");
        }
    }

    private void writeToSamples(byte[] bArr, int i, int i2) {
        int i3 = this.samplesWriteIndex;
        int i4 = i3 + i2;
        byte[] bArr2 = this.samples;
        if (i4 < bArr2.length) {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.samplesWriteIndex += i2;
        } else {
            int length = bArr2.length - i3;
            System.arraycopy(bArr, i, bArr2, i3, length);
            this.samplesWriteIndex = 0;
            int i5 = i2 - length;
            System.arraycopy(bArr, i + length, this.samples, 0, i5);
            this.samplesWriteIndex += i5;
        }
        if (this.samplesWriteIndex == this.samples.length) {
            this.samplesWriteIndex = 0;
        }
        this.samplesWriteCount += (long) i2;
    }

    private void readFromSamples(byte[] bArr, int i, int i2) {
        int i3 = this.samplesReadIndex;
        int i4 = i3 + i2;
        byte[] bArr2 = this.samples;
        if (i4 < bArr2.length) {
            System.arraycopy(bArr2, i3, bArr, i, i2);
            this.samplesReadIndex += i2;
        } else {
            int length = bArr2.length - i3;
            System.arraycopy(bArr2, i3, bArr, i, length);
            this.samplesReadIndex = 0;
            int i5 = i2 - length;
            System.arraycopy(this.samples, 0, bArr, i + length, i5);
            this.samplesReadIndex += i5;
        }
        if (this.samplesReadIndex == this.samples.length) {
            this.samplesReadIndex = 0;
        }
        this.samplesReadCount += (long) i2;
    }

    /* access modifiers changed from: private */
    public void renderLoop() {
        if (Process.getThreadPriority(Process.myTid()) != -19) {
            Process.setThreadPriority(-19);
        }
        int clockRate2 = ((AudioFormat) getInputFormat()).getClockRate();
        int channelCount2 = ((AudioFormat) getInputFormat()).getChannelCount();
        long nanoTime = System.nanoTime();
        long j = C.MICROS_PER_SECOND;
        byte[] bArr = new byte[this.chunkSize];
        long j2 = 0;
        long j3 = 0;
        while (this.isRendering) {
            long nanoTime2 = (System.nanoTime() - nanoTime) / j;
            while (j3 < nanoTime2) {
                long j4 = this.samplesWriteCount;
                long j5 = nanoTime;
                int i = (int) (j4 - this.samplesReadCount);
                if (((int) ((j4 * 1000) / ((long) ((clockRate2 * channelCount2) * this.sampleLength)))) < this.latencyMillis) {
                    i = 0;
                }
                int i2 = this.chunkSize;
                if (i2 <= i) {
                    readFromSamples(bArr, 0, i2);
                } else if (i == 0) {
                    BitAssistant.set(bArr, 0, i2, 0);
                } else {
                    readFromSamples(bArr, 0, i);
                    BitAssistant.set(bArr, i, this.chunkSize - i, 0);
                    log.debug("AudioTrackSink buffer underrun.");
                }
                this.audioTrack.write(bArr, 0, this.chunkSize);
                j3 += (long) this.chunkTime;
                nanoTime = j5;
            }
            long j6 = nanoTime;
            if (j2 < ((long) this.chunkDivisor)) {
                j2++;
                if (j2 == 2) {
                    this.audioTrack.play();
                }
            }
            try {
                Thread.sleep((long) this.chunkTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            nanoTime = j6;
            j = C.MICROS_PER_SECOND;
        }
        this.audioTrack.stop();
        this.isStopped = true;
    }
}
