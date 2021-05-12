package fm.liveswitch;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Guid {
    public static final Guid empty = new Guid("00000000-0000-0000-0000-000000000000");
    public UUID uuid;

    public Guid(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.uuid = new UUID(wrap.getLong(), wrap.getLong());
    }

    public Guid(String str) {
        this.uuid = UUID.fromString(str);
    }

    public String toString() {
        return this.uuid.toString();
    }

    public byte[] toByteArray() {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(this.uuid.getMostSignificantBits());
        wrap.putLong(this.uuid.getLeastSignificantBits());
        return wrap.array();
    }

    public static String toString(Guid guid) {
        return guid.toString();
    }

    public static Guid newGuid() {
        return new Guid(UUID.randomUUID().toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this instanceof Guid)) {
            return false;
        }
        return ((Guid) obj).toString().equals(toString());
    }

    public int hashCode() {
        return this.uuid.hashCode();
    }

    public int compareTo(Guid guid) {
        return guid.uuid.compareTo(this.uuid);
    }
}
