package fm.liveswitch;

class SctpCookieReceivedWhileShuttingDown extends SctpErrorCause {
    public static byte[] getBytes(SctpCookieReceivedWhileShuttingDown sctpCookieReceivedWhileShuttingDown) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpCookieReceivedWhileShuttingDown.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(4, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpCookieReceivedWhileShuttingDown parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        Log.debug("SCTP Error: Cookie received while shutting down.");
        integerHolder.setValue(4);
        return new SctpCookieReceivedWhileShuttingDown();
    }

    public SctpCookieReceivedWhileShuttingDown() {
        super.setCauseCode(10);
    }
}
