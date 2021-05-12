package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import java.util.ArrayList;
import java.util.HashMap;

class NackBuffer<TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> {
    private TFrame[] __frameBuffer;
    private long __highestPacketIndex = 0;
    private HashMap<Long, Long> __recentNackedPacketIndexes;
    private ArrayList<Long> __recentTimestampsUsedToNack;
    private int _length;
    private String _name;
    private int _retransmissionTimeout;

    private TFrame getFrame(long j) {
        TFrame tframe = this.__frameBuffer[(int) (j % ((long) ArrayExtensions.getLength((Object[]) this.__frameBuffer)))];
        if (tframe == null || tframe.getBuffer().getSequenceNumber() != j) {
            return null;
        }
        return tframe;
    }

    public int getLength() {
        return this._length;
    }

    public String getName() {
        return this._name;
    }

    public int getRetransmissionTimeout() {
        return this._retransmissionTimeout;
    }

    public NackBuffer(String str, int i, IFunction1<Integer, TFrame[]> iFunction1) {
        setRetransmissionTimeout(50);
        setName(str);
        setLength(i);
        this.__frameBuffer = (MediaFrame[]) iFunction1.invoke(Integer.valueOf(i));
        this.__recentTimestampsUsedToNack = new ArrayList<>();
        this.__recentNackedPacketIndexes = new HashMap<>();
    }

    public boolean read(long j, int i, long j2, long j3, IAction1<TFrame> iAction1, IAction1<GenericNack> iAction12) {
        IAction1<GenericNack> iAction13 = iAction12;
        if (j <= this.__highestPacketIndex) {
            MediaFrame frame = getFrame(j);
            if (frame != null) {
                this.__recentTimestampsUsedToNack.remove(Long.valueOf(frame.getTimestamp()));
                HashMapExtensions.remove(this.__recentNackedPacketIndexes, Long.valueOf(j));
                MediaBuffer buffer = frame.getBuffer();
                iAction1.invoke(frame);
                buffer.getDataBuffer().free();
                return true;
            }
            if (this.__recentNackedPacketIndexes.containsKey(Long.valueOf(j))) {
                long longValue = HashMapExtensions.getItem(this.__recentNackedPacketIndexes).get(Long.valueOf(j)).longValue();
                if (longValue > 0 && (j3 - longValue) / ((long) Constants.getTicksPerMillisecond()) < ((long) getRetransmissionTimeout())) {
                    return false;
                }
            }
            if (j2 != -1) {
                if (this.__recentTimestampsUsedToNack.contains(Long.valueOf(j2))) {
                    return false;
                }
                this.__recentTimestampsUsedToNack.add(Long.valueOf(j2));
            }
            long j4 = this.__highestPacketIndex - j;
            GenericNack genericNack = new GenericNack();
            int i2 = i;
            genericNack.setPacketId(i);
            for (int i3 = 1; i3 <= 16; i3++) {
                long j5 = (long) i3;
                if (j4 > j5) {
                    genericNack.setLostPacketIdPlus(i3, getFrame(j5 + j) == null);
                }
            }
            if (!this.__recentNackedPacketIndexes.containsKey(Long.valueOf(j))) {
                HashMapExtensions.add(this.__recentNackedPacketIndexes, Long.valueOf(j), Long.valueOf(j3));
            }
            if (iAction13 != null) {
                iAction13.invoke(genericNack);
            }
        }
        return false;
    }

    private void setLength(int i) {
        this._length = i;
    }

    private void setName(String str) {
        this._name = str;
    }

    public void setRetransmissionTimeout(int i) {
        this._retransmissionTimeout = i;
    }

    public int write(TFrame tframe) {
        if (!tframe.getBuffer().getFormat().getIsPacketized()) {
            throw new RuntimeException(new Exception("The first buffer of a frame stored to be stored in a Nack.Buffer must have a packetized format."));
        } else if (ArrayExtensions.getLength((Object[]) tframe.getBuffer().getDataBuffers()) == 1) {
            int sequenceNumber = (int) (tframe.getBuffer().getSequenceNumber() % ((long) ArrayExtensions.getLength((Object[]) this.__frameBuffer)));
            TFrame tframe2 = this.__frameBuffer[sequenceNumber];
            if (tframe2 != null && tframe2.getTimestamp() > tframe.getTimestamp()) {
                return -2;
            }
            if (tframe2 != null && tframe2.getTimestamp() == tframe.getTimestamp()) {
                return -1;
            }
            tframe.getBuffer().getDataBuffer().keep();
            this.__frameBuffer[sequenceNumber] = tframe;
            this.__highestPacketIndex = MathAssistant.max(this.__highestPacketIndex, tframe.getBuffer().getSequenceNumber());
            return 0;
        } else {
            throw new RuntimeException(new Exception("Nack.Buffer only supports a Frame.Buffer.DataBuffers.Length of one."));
        }
    }
}
