package fm.liveswitch;

import fm.liveswitch.pcm.Format;
import java.util.ArrayList;
import java.util.HashMap;

public class AudioBuffer extends MediaBuffer<AudioFormat, AudioBuffer> {
    private double __gain = 1.0d;
    private boolean __isMuted;
    private boolean _generatedByPlc;

    public void applyGain(double d) {
        if (!getIsPcm()) {
            throw new RuntimeException(new Exception("Only PCM audio buffers can have their gain adjusted."));
        } else if (d != 1.0d) {
            for (DataBuffer dataBuffer : super.getDataBuffers()) {
                for (int i = 0; i < dataBuffer.getLength(); i += 2) {
                    double read16Signed = ((double) dataBuffer.read16Signed(i)) * d;
                    if (read16Signed > 32767.0d) {
                        read16Signed = 32767.0d;
                    }
                    if (read16Signed < -32768.0d) {
                        read16Signed = -32768.0d;
                    }
                    dataBuffer.write16((short) ((int) read16Signed), i);
                }
            }
            this.__gain *= d;
        }
    }

    protected AudioBuffer() {
    }

    public AudioBuffer(DataBuffer dataBuffer, AudioFormat audioFormat) {
        super(dataBuffer, audioFormat);
        initBuffer();
    }

    public AudioBuffer(DataBuffer[] dataBufferArr, AudioFormat audioFormat) {
        super(dataBufferArr, audioFormat);
        initBuffer();
    }

    public double calculateLevel() {
        if (((AudioFormat) super.getFormat()).getIsPcm()) {
            double d = 0.0d;
            int i = 0;
            for (DataBuffer dataBuffer : super.getDataBuffers()) {
                i += dataBuffer.getLength() / 2;
                for (int i2 = 0; i2 < dataBuffer.getLength(); i2 += 2) {
                    d += MathAssistant.pow((double) SoundUtility.floatFromShort((short) dataBuffer.read16Signed(i2)), 2.0d);
                }
            }
            return MathAssistant.sqrt(d / ((double) i));
        }
        throw new RuntimeException(new Exception("Only PCM audio buffers can have their level calculated."));
    }

    public AudioBuffer clone() {
        AudioBuffer audioBuffer = (AudioBuffer) super.clone();
        audioBuffer.__gain = this.__gain;
        audioBuffer.__isMuted = this.__isMuted;
        return audioBuffer;
    }

    /* access modifiers changed from: protected */
    public AudioBuffer createInstance() {
        return new AudioBuffer();
    }

    public static AudioBuffer createSilence(int i, int i2, int i3) {
        return new AudioBuffer(DataBuffer.allocate(getMinimumBufferLength(i, i2, i3), true), (AudioFormat) new Format(i2, i3));
    }

    public static AudioBuffer createSilence(int i, AudioConfig audioConfig) {
        return createSilence(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static AudioBuffer createSound(long j, int i, int i2, int i3) {
        return createSound(j, i, i2, i3, 440.0f);
    }

    public static AudioBuffer createSound(long j, int i, int i2, int i3, float f) {
        return createSound(j, i, i2, i3, f, 16384);
    }

    public static AudioBuffer createSound(long j, int i, int i2, int i3, float f, int i4) {
        double pi = ((MathAssistant.getPi() * 2.0d) * ((double) f)) / ((double) i2);
        int i5 = (i2 * i) / 1000;
        DataBuffer allocate = DataBuffer.allocate(getMinimumBufferLength(i, i2, i3), true);
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            short sin = (short) ((int) (((double) i4) * MathAssistant.sin(((double) j) * pi)));
            j++;
            if (j >= Long.MAX_VALUE) {
                j = 0;
            }
            for (int i8 = 0; i8 < i3; i8++) {
                allocate.write16(sin, i6);
                i6 += 2;
            }
        }
        return new AudioBuffer(allocate, (AudioFormat) new Format(i2, i3));
    }

    public static AudioBuffer createSound(long j, int i, AudioConfig audioConfig) {
        return createSound(j, i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    public static AudioBuffer createSound(long j, int i, AudioConfig audioConfig, float f) {
        return createSound(j, i, audioConfig.getClockRate(), audioConfig.getChannelCount(), f);
    }

    public static AudioBuffer createSound(long j, int i, AudioConfig audioConfig, float f, int i2) {
        return createSound(j, i, audioConfig.getClockRate(), audioConfig.getChannelCount(), f, i2);
    }

    public static AudioBuffer fromJson(String str) {
        return (AudioBuffer) JsonSerializer.deserializeObject(str, new IFunction0<AudioBuffer>() {
            public AudioBuffer invoke() {
                return new AudioBuffer();
            }
        }, new IAction3<AudioBuffer, String, String>() {
            public void invoke(AudioBuffer audioBuffer, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "gain")) {
                    audioBuffer.setGain(JsonSerializer.deserializeDouble(str2).getValue());
                } else if (Global.equals(str, "isMuted")) {
                    audioBuffer.setIsMuted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (Global.equals(str, "dataBuffers")) {
                    audioBuffer.setDataBuffers(DataBuffer.fromJsonArray(str2));
                } else if (Global.equals(str, "format")) {
                    audioBuffer.setFormat(AudioFormat.fromJson(str2));
                }
            }
        });
    }

    public static AudioBuffer[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, AudioBuffer>() {
            public String getId() {
                return "fm.liveswitch.AudioBuffer.fromJson";
            }

            public AudioBuffer invoke(String str) {
                return AudioBuffer.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (AudioBuffer[]) deserializeObjectArray.toArray(new AudioBuffer[0]);
    }

    public double getGain() {
        return this.__gain;
    }

    public boolean getGeneratedByPlc() {
        return this._generatedByPlc;
    }

    public boolean getIsDtmf() {
        AudioFormat audioFormat = (AudioFormat) super.getFormat();
        return audioFormat != null && audioFormat.getIsDtmf();
    }

    public boolean getIsMuted() {
        return this.__isMuted;
    }

    public boolean getIsOpus() {
        AudioFormat audioFormat = (AudioFormat) super.getFormat();
        return audioFormat != null && audioFormat.getIsOpus();
    }

    public boolean getIsPcm() {
        AudioFormat audioFormat = (AudioFormat) super.getFormat();
        return audioFormat != null && audioFormat.getIsPcm();
    }

    public boolean getIsPcma() {
        AudioFormat audioFormat = (AudioFormat) super.getFormat();
        return audioFormat != null && audioFormat.getIsPcma();
    }

    public boolean getIsPcmu() {
        AudioFormat audioFormat = (AudioFormat) super.getFormat();
        return audioFormat != null && audioFormat.getIsPcmu();
    }

    public static int getMinimumBufferLength(int i, int i2, int i3) {
        return SoundUtility.calculateDataLength(i, i2, i3);
    }

    public static int getMinimumBufferLength(int i, AudioConfig audioConfig) {
        return getMinimumBufferLength(i, audioConfig.getClockRate(), audioConfig.getChannelCount());
    }

    private void initBuffer() {
        for (DataBuffer dataBuffer : super.getDataBuffers()) {
            if (((AudioFormat) super.getFormat()).getLittleEndian() && !dataBuffer.getLittleEndian()) {
                Log.warn(StringExtensions.format("Format ({0}) requires little endian, but data buffer is big endian. Will switch data buffer to little endian.", (Object) ((AudioFormat) super.getFormat()).getFullName()));
            }
            if (!((AudioFormat) super.getFormat()).getLittleEndian() && dataBuffer.getLittleEndian()) {
                Log.warn(StringExtensions.format("Format ({0}) requires big endian, but data buffer is little endian. Will switch data buffer to big endian.", (Object) ((AudioFormat) super.getFormat()).getFullName()));
            }
            dataBuffer.setLittleEndian(((AudioFormat) super.getFormat()).getLittleEndian());
        }
    }

    public boolean mute() {
        if (!this.__isMuted) {
            if (!((AudioFormat) super.getFormat()).getIsPcm()) {
                return false;
            }
            for (DataBuffer dataBuffer : super.getDataBuffers()) {
                dataBuffer.set((byte) 0);
            }
            this.__isMuted = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setGain(double d) {
        this.__gain = d;
    }

    public void setGeneratedByPlc(boolean z) {
        this._generatedByPlc = z;
    }

    /* access modifiers changed from: package-private */
    public void setIsMuted(boolean z) {
        this.__isMuted = z;
    }

    public static String toJson(AudioBuffer audioBuffer) {
        return JsonSerializer.serializeObject(audioBuffer, new IAction2<AudioBuffer, HashMap<String, String>>(audioBuffer) {
            final /* synthetic */ AudioBuffer val$audioBuffer;

            {
                this.val$audioBuffer = r1;
            }

            public void invoke(AudioBuffer audioBuffer, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "gain", JsonSerializer.serializeDouble(new NullableDouble(this.val$audioBuffer.getGain())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isMuted", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$audioBuffer.getIsMuted())));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataBuffers", DataBuffer.toJsonArray(this.val$audioBuffer.getDataBuffers()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "format", AudioFormat.toJson((AudioFormat) this.val$audioBuffer.getFormat()));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(AudioBuffer[] audioBufferArr) {
        return JsonSerializer.serializeObjectArray(audioBufferArr, new IFunctionDelegate1<AudioBuffer, String>() {
            public String getId() {
                return "fm.liveswitch.AudioBuffer.toJson";
            }

            public String invoke(AudioBuffer audioBuffer) {
                return AudioBuffer.toJson(audioBuffer);
            }
        });
    }
}
