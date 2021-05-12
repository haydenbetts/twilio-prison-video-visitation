package fm.liveswitch.opus;

import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioDecoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.Error;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.SoundUtility;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.pcm.Format;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.PacketTimeAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import java.util.Iterator;

public class Decoder extends AudioDecoder {
    private ArrayList<AudioBuffer> __decodedBuffers;
    private Native __decoder;
    private double __packetTime;

    public String getLabel() {
        return "Opus Decoder";
    }

    private AudioBuffer decode(AudioBuffer audioBuffer, boolean z) {
        AudioBuffer decode = this.__decoder.decode(audioBuffer, (AudioFormat) super.getOutputFormat(), z);
        if (decode == null) {
            return null;
        }
        decode.getDataBuffer().setLittleEndian(((AudioFormat) super.getOutputFormat()).getLittleEndian());
        return decode;
    }

    private ArrayList<AudioBuffer> decode(AudioFrame audioFrame, AudioBuffer audioBuffer, AudioFormat audioFormat) {
        AudioBuffer decodeFec;
        if (audioBuffer == null) {
            return null;
        }
        if (this.__decoder == null) {
            if (this.__packetTime == -1.0d) {
                if (audioFrame.getDuration() > 0) {
                    this.__packetTime = (double) audioFrame.getDuration();
                } else {
                    this.__packetTime = 20.0d;
                }
            }
            this.__decoder = new Native(false, super.getConfig().getClockRate(), super.getConfig().getChannelCount(), this.__packetTime);
        }
        if (this.__decodedBuffers == null) {
            this.__decodedBuffers = new ArrayList<>();
        }
        this.__decodedBuffers.clear();
        if (super.getMissingDuration() > 0 && (decodeFec = decodeFec(audioBuffer)) != null) {
            int calculateDuration = SoundUtility.calculateDuration(decodeFec.getDataBuffer().getLength(), super.getConfig());
            if (Log.getIsVerboseEnabled()) {
                Log.verbose(StringExtensions.format("Opus decoder recovered {0}ms of audio using forward error correction (FEC).", (Object) IntegerExtensions.toString(Integer.valueOf(calculateDuration))));
            }
            super.setMissingDuration(super.getMissingDuration() - calculateDuration);
            this.__decodedBuffers.add(decodeFec);
        }
        while (super.getMissingDuration() > 0) {
            AudioBuffer decodePlc = decodePlc();
            if (decodePlc == null) {
                decodePlc = super.generatePlc(super.getMissingDuration());
            }
            int calculateDuration2 = SoundUtility.calculateDuration(decodePlc.getDataBuffer().getLength(), super.getConfig());
            if (Log.getIsVerboseEnabled()) {
                Log.verbose(StringExtensions.format("Opus decoder generated {0}ms of audio using packet loss concealment (PLC).", (Object) IntegerExtensions.toString(Integer.valueOf(calculateDuration2))));
            }
            super.setMissingDuration(super.getMissingDuration() - calculateDuration2);
            ArrayListExtensions.insert(this.__decodedBuffers, 0, decodePlc);
        }
        this.__decodedBuffers.add(decodeNormal(audioBuffer));
        return this.__decodedBuffers;
    }

    private AudioBuffer decodeFec(AudioBuffer audioBuffer) {
        AudioBuffer decode = decode(audioBuffer, true);
        if (decode != null) {
            decode.setRecoveredByFec(true);
        }
        return decode;
    }

    private AudioBuffer decodeNormal(AudioBuffer audioBuffer) {
        return decode(audioBuffer, false);
    }

    private AudioBuffer decodePlc() {
        AudioBuffer decode = decode((AudioBuffer) null, false);
        if (decode != null) {
            decode.setGeneratedByPlc(true);
        }
        return decode;
    }

    public Decoder() {
        this(new Format().getConfig());
    }

    public Decoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
        this.__packetTime = -1.0d;
        super.setDisablePlc(true);
    }

    public Decoder(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        Native nativeR = this.__decoder;
        if (nativeR != null) {
            nativeR.destroy();
            this.__decoder = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioFrame audioFrame2;
        ArrayList<AudioBuffer> decode = decode(audioFrame, audioBuffer, (AudioFormat) super.getOutputFormat());
        if (decode != null) {
            Iterator<AudioBuffer> it = decode.iterator();
            while (it.hasNext()) {
                AudioBuffer next = it.next();
                if (next != null) {
                    if (ArrayListExtensions.getCount(decode) == 1) {
                        audioFrame2 = audioFrame;
                    } else {
                        audioFrame2 = audioFrame.clone();
                    }
                    audioFrame2.setDuration(SoundUtility.calculateDuration(next.getDataBuffer().getLength(), super.getOutputConfig()));
                    audioFrame2.addBuffer(next);
                    raiseFrame(audioFrame2);
                }
            }
            Iterator<AudioBuffer> it2 = decode.iterator();
            while (it2.hasNext()) {
                AudioBuffer next2 = it2.next();
                if (next2 != null) {
                    next2.getDataBuffer().free();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Error doProcessSdpMediaDescription(MediaDescription mediaDescription, boolean z, boolean z2) {
        Error doProcessSdpMediaDescription = super.doProcessSdpMediaDescription(mediaDescription, z, z2);
        if (doProcessSdpMediaDescription != null) {
            return doProcessSdpMediaDescription;
        }
        if (z2) {
            MapAttribute rtpMapAttribute = mediaDescription.getRtpMapAttribute(((AudioFormat) super.getInputFormat()).getName(), ((AudioFormat) super.getInputFormat()).getClockRate(), ((AudioFormat) super.getInputFormat()).getParameters());
            if (rtpMapAttribute == null) {
                return null;
            }
            mediaDescription.setFormatParameterValue(rtpMapAttribute.getPayloadType(), "useinbandfec", "1");
            if (super.getOutputConfig().getChannelCount() <= 1) {
                return null;
            }
            mediaDescription.setFormatParameterValue(rtpMapAttribute.getPayloadType(), "stereo", "1");
            return null;
        }
        int i = 20;
        PacketTimeAttribute packetTimeAttribute = mediaDescription.getPacketTimeAttribute();
        if (packetTimeAttribute != null) {
            i = packetTimeAttribute.getPacketTime();
        }
        this.__packetTime = (double) i;
        return null;
    }
}
