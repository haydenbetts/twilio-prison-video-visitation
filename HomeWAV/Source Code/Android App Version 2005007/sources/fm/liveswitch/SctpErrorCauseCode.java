package fm.liveswitch;

class SctpErrorCauseCode {
    public static final int CookieReceivedWhileShuttingDown = 10;
    public static final int InvalidMandatoryParameter = 7;
    public static final int InvalidStreamIdentifier = 1;
    public static final int MissingMandatoryParameter = 2;
    public static final int NoUserData = 9;
    public static final int OutOfResource = 4;
    public static final int ProtocolViolation = 13;
    public static final int RestartOfAnAssociationWithNewAddresses = 11;
    public static final int StaleCookieError = 3;
    public static final int UnrecognizedChunkType = 6;
    public static final int UnrecognizedParameters = 8;
    public static final int UnresolvableAddress = 5;
    public static final int UserInitiatedAbort = 12;
}
