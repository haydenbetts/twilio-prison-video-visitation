package fm.liveswitch;

import fm.liveswitch.dtmf.Tone;

public interface IAudioStream extends IMediaStream, IStream {
    void addOnReceiveDtmfTone(IAction1<Tone> iAction1);

    void addOnReceiveDtmfToneChange(IAction1<Tone> iAction1);

    void addOnSendDtmfTone(IAction1<Tone> iAction1);

    void addOnSendDtmfToneChange(IAction1<Tone> iAction1);

    boolean getG722Disabled();

    boolean getOpusDisabled();

    boolean getPcmaDisabled();

    boolean getPcmuDisabled();

    boolean insertDtmfTone(Tone tone);

    boolean insertDtmfTones(Tone[] toneArr);

    void removeOnReceiveDtmfTone(IAction1<Tone> iAction1);

    void removeOnReceiveDtmfToneChange(IAction1<Tone> iAction1);

    void removeOnSendDtmfTone(IAction1<Tone> iAction1);

    void removeOnSendDtmfToneChange(IAction1<Tone> iAction1);

    void setG722Disabled(boolean z);

    void setOpusDisabled(boolean z);

    void setPcmaDisabled(boolean z);

    void setPcmuDisabled(boolean z);
}
