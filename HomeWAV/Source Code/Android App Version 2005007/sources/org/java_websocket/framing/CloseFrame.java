package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.util.ByteBufferUtils;
import org.java_websocket.util.Charsetfunctions;

public class CloseFrame extends ControlFrame {
    public static final int ABNORMAL_CLOSE = 1006;
    public static final int BAD_GATEWAY = 1014;
    public static final int BUGGYCLOSE = -2;
    public static final int EXTENSION = 1010;
    public static final int FLASHPOLICY = -3;
    public static final int GOING_AWAY = 1001;
    public static final int NEVER_CONNECTED = -1;
    public static final int NOCODE = 1005;
    public static final int NORMAL = 1000;
    public static final int NO_UTF8 = 1007;
    public static final int POLICY_VALIDATION = 1008;
    public static final int PROTOCOL_ERROR = 1002;
    public static final int REFUSE = 1003;
    public static final int SERVICE_RESTART = 1012;
    public static final int TLS_ERROR = 1015;
    public static final int TOOBIG = 1009;
    public static final int TRY_AGAIN_LATER = 1013;
    public static final int UNEXPECTED_CONDITION = 1011;
    private int code;
    private String reason;

    public CloseFrame() {
        super(Opcode.CLOSING);
        setReason("");
        setCode(1000);
    }

    public void setCode(int i) {
        this.code = i;
        if (i == 1015) {
            this.code = 1005;
            this.reason = "";
        }
        updatePayload();
    }

    public void setReason(String str) {
        if (str == null) {
            str = "";
        }
        this.reason = str;
        updatePayload();
    }

    public int getCloseCode() {
        return this.code;
    }

    public String getMessage() {
        return this.reason;
    }

    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    public void isValid() throws InvalidDataException {
        super.isValid();
        if (this.code == 1007 && this.reason.isEmpty()) {
            throw new InvalidDataException(1007, "Received text is no valid utf8 string!");
        } else if (this.code != 1005 || this.reason.length() <= 0) {
            int i = this.code;
            if (i > 1015 && i < 3000) {
                throw new InvalidDataException(1002, "Trying to send an illegal close code!");
            } else if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
            }
        } else {
            throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
        }
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.code = 1005;
        this.reason = "";
        byteBuffer.mark();
        if (byteBuffer.remaining() == 0) {
            this.code = 1000;
        } else if (byteBuffer.remaining() == 1) {
            this.code = 1002;
        } else {
            if (byteBuffer.remaining() >= 2) {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                allocate.position(2);
                allocate.putShort(byteBuffer.getShort());
                allocate.position(0);
                this.code = allocate.getInt();
            }
            byteBuffer.reset();
            try {
                validateUtf8(byteBuffer, byteBuffer.position());
            } catch (InvalidDataException unused) {
                this.code = 1007;
                this.reason = null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        throw new org.java_websocket.exceptions.InvalidDataException(1007);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r3.position(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void validateUtf8(java.nio.ByteBuffer r3, int r4) throws org.java_websocket.exceptions.InvalidDataException {
        /*
            r2 = this;
            int r0 = r3.position()     // Catch:{ IllegalArgumentException -> 0x0015 }
            int r0 = r0 + 2
            r3.position(r0)     // Catch:{ IllegalArgumentException -> 0x0015 }
            java.lang.String r0 = org.java_websocket.util.Charsetfunctions.stringUtf8((java.nio.ByteBuffer) r3)     // Catch:{ IllegalArgumentException -> 0x0015 }
            r2.reason = r0     // Catch:{ IllegalArgumentException -> 0x0015 }
            r3.position(r4)
            return
        L_0x0013:
            r0 = move-exception
            goto L_0x001d
        L_0x0015:
            org.java_websocket.exceptions.InvalidDataException r0 = new org.java_websocket.exceptions.InvalidDataException     // Catch:{ all -> 0x0013 }
            r1 = 1007(0x3ef, float:1.411E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0013 }
            throw r0     // Catch:{ all -> 0x0013 }
        L_0x001d:
            r3.position(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.framing.CloseFrame.validateUtf8(java.nio.ByteBuffer, int):void");
    }

    private void updatePayload() {
        byte[] utf8Bytes = Charsetfunctions.utf8Bytes(this.reason);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(this.code);
        allocate.position(2);
        ByteBuffer allocate2 = ByteBuffer.allocate(utf8Bytes.length + 2);
        allocate2.put(allocate);
        allocate2.put(utf8Bytes);
        allocate2.rewind();
        super.setPayload(allocate2);
    }

    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return ByteBufferUtils.getEmptyByteBuffer();
        }
        return super.getPayloadData();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        CloseFrame closeFrame = (CloseFrame) obj;
        if (this.code != closeFrame.code) {
            return false;
        }
        String str = this.reason;
        String str2 = closeFrame.reason;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + this.code) * 31;
        String str = this.reason;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}
