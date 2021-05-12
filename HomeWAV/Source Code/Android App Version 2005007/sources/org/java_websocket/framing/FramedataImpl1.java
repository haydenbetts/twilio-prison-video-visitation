package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.util.ByteBufferUtils;

public abstract class FramedataImpl1 implements Framedata {
    private boolean fin = true;
    private Opcode optcode;
    private boolean rsv1 = false;
    private boolean rsv2 = false;
    private boolean rsv3 = false;
    private boolean transferemasked = false;
    private ByteBuffer unmaskedpayload = ByteBufferUtils.getEmptyByteBuffer();

    public abstract void isValid() throws InvalidDataException;

    public FramedataImpl1(Opcode opcode) {
        this.optcode = opcode;
    }

    public boolean isRSV1() {
        return this.rsv1;
    }

    public boolean isRSV2() {
        return this.rsv2;
    }

    public boolean isRSV3() {
        return this.rsv3;
    }

    public boolean isFin() {
        return this.fin;
    }

    public Opcode getOpcode() {
        return this.optcode;
    }

    public boolean getTransfereMasked() {
        return this.transferemasked;
    }

    public ByteBuffer getPayloadData() {
        return this.unmaskedpayload;
    }

    public void append(Framedata framedata) {
        ByteBuffer payloadData = framedata.getPayloadData();
        if (this.unmaskedpayload == null) {
            this.unmaskedpayload = ByteBuffer.allocate(payloadData.remaining());
            payloadData.mark();
            this.unmaskedpayload.put(payloadData);
            payloadData.reset();
        } else {
            payloadData.mark();
            ByteBuffer byteBuffer = this.unmaskedpayload;
            byteBuffer.position(byteBuffer.limit());
            ByteBuffer byteBuffer2 = this.unmaskedpayload;
            byteBuffer2.limit(byteBuffer2.capacity());
            if (payloadData.remaining() > this.unmaskedpayload.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(payloadData.remaining() + this.unmaskedpayload.capacity());
                this.unmaskedpayload.flip();
                allocate.put(this.unmaskedpayload);
                allocate.put(payloadData);
                this.unmaskedpayload = allocate;
            } else {
                this.unmaskedpayload.put(payloadData);
            }
            this.unmaskedpayload.rewind();
            payloadData.reset();
        }
        this.fin = framedata.isFin();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Framedata{ optcode:");
        sb.append(getOpcode());
        sb.append(", fin:");
        sb.append(isFin());
        sb.append(", rsv1:");
        sb.append(isRSV1());
        sb.append(", rsv2:");
        sb.append(isRSV2());
        sb.append(", rsv3:");
        sb.append(isRSV3());
        sb.append(", payloadlength:[pos:");
        sb.append(this.unmaskedpayload.position());
        sb.append(", len:");
        sb.append(this.unmaskedpayload.remaining());
        sb.append("], payload:");
        sb.append(this.unmaskedpayload.remaining() > 1000 ? "(too big to display)" : new String(this.unmaskedpayload.array()));
        sb.append('}');
        return sb.toString();
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.unmaskedpayload = byteBuffer;
    }

    public void setFin(boolean z) {
        this.fin = z;
    }

    public void setRSV1(boolean z) {
        this.rsv1 = z;
    }

    public void setRSV2(boolean z) {
        this.rsv2 = z;
    }

    public void setRSV3(boolean z) {
        this.rsv3 = z;
    }

    public void setTransferemasked(boolean z) {
        this.transferemasked = z;
    }

    /* renamed from: org.java_websocket.framing.FramedataImpl1$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$java_websocket$enums$Opcode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.java_websocket.enums.Opcode[] r0 = org.java_websocket.enums.Opcode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$java_websocket$enums$Opcode = r0
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.PING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$java_websocket$enums$Opcode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.PONG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$java_websocket$enums$Opcode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.TEXT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$java_websocket$enums$Opcode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.BINARY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$java_websocket$enums$Opcode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.CLOSING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$java_websocket$enums$Opcode     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.java_websocket.enums.Opcode r1 = org.java_websocket.enums.Opcode.CONTINUOUS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.framing.FramedataImpl1.AnonymousClass1.<clinit>():void");
        }
    }

    public static FramedataImpl1 get(Opcode opcode) {
        if (opcode != null) {
            switch (AnonymousClass1.$SwitchMap$org$java_websocket$enums$Opcode[opcode.ordinal()]) {
                case 1:
                    return new PingFrame();
                case 2:
                    return new PongFrame();
                case 3:
                    return new TextFrame();
                case 4:
                    return new BinaryFrame();
                case 5:
                    return new CloseFrame();
                case 6:
                    return new ContinuousFrame();
                default:
                    throw new IllegalArgumentException("Supplied opcode is invalid");
            }
        } else {
            throw new IllegalArgumentException("Supplied opcode cannot be null");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FramedataImpl1 framedataImpl1 = (FramedataImpl1) obj;
        if (this.fin != framedataImpl1.fin || this.transferemasked != framedataImpl1.transferemasked || this.rsv1 != framedataImpl1.rsv1 || this.rsv2 != framedataImpl1.rsv2 || this.rsv3 != framedataImpl1.rsv3 || this.optcode != framedataImpl1.optcode) {
            return false;
        }
        ByteBuffer byteBuffer = this.unmaskedpayload;
        ByteBuffer byteBuffer2 = framedataImpl1.unmaskedpayload;
        if (byteBuffer != null) {
            return byteBuffer.equals(byteBuffer2);
        }
        if (byteBuffer2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.fin ? 1 : 0) * true) + this.optcode.hashCode()) * 31;
        ByteBuffer byteBuffer = this.unmaskedpayload;
        return ((((((((hashCode + (byteBuffer != null ? byteBuffer.hashCode() : 0)) * 31) + (this.transferemasked ? 1 : 0)) * 31) + (this.rsv1 ? 1 : 0)) * 31) + (this.rsv2 ? 1 : 0)) * 31) + (this.rsv3 ? 1 : 0);
    }
}
