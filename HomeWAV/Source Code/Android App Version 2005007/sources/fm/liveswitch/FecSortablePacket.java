package fm.liveswitch;

class FecSortablePacket {
    private int __sequenceNumber;

    private static int toUnsignedShort(int i) {
        while (i < 0) {
            i += 65536;
        }
        while (i > 65535) {
            i -= 65536;
        }
        return i;
    }

    public int getSequenceNumber() {
        return this.__sequenceNumber;
    }

    public void setSequenceNumber(int i) {
        this.__sequenceNumber = toUnsignedShort(i);
    }
}
