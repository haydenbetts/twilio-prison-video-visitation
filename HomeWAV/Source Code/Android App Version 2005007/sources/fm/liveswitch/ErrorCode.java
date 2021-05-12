package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ErrorCode {
    SocketSendError(100000),
    SocketReceiveError(100001),
    SocketClosed(100002),
    SocketSendBufferFull(100003),
    IPProtocolMismatch(100004),
    PacketTooLarge(100005),
    StunTryAlternate(114000),
    StunBadRequest(114001),
    StunUnauthorized(114002),
    StunTurnForbidden(114003),
    StunTurnMobilityForbidden(114004),
    StunUnknownAttribute(114005),
    StunStaleCredentials(114006),
    StunIntegrityCheckFailure(114007),
    StunMissingUsername(114008),
    StunTurnAllocationMismatch(114009),
    StunStaleNonce(114010),
    StunAddressFamilyNotSupported(114011),
    StunTurnWrongCredentials(114012),
    StunTurnUnsupportedTransportProtocol(114013),
    StunTurnPeerAddressFamilyMismatch(114014),
    StunTurnConnectionAlreadyExists(114015),
    StunTurnConnectionTimeoutOrFailure(114016),
    StunTurnAllocationQuotaReached(114017),
    StunIceRoleConflict(114018),
    StunServerError(114019),
    StunTurnInsufficientCapacity(114020),
    StunInvalidResponseType(114021),
    StunInvalidErrorCode(114022),
    StunInvalidTransactionId(114023),
    StunUnknownStunErrorCode(114024),
    StunInvalidMessageIntegrity(114025),
    SocketIPError(115001),
    IceLocalRelayedDatagramCandidateError(101000),
    IceLocalServerReflexiveCandidateError(101001),
    SocketManagerInvalidState(101002),
    IceStartError(102000),
    IceUnsuitableSocketAssignment(102001),
    IceAllPortsInUse(102002),
    IceLocalAddressUnavailable(102003),
    IceGenericGathererError(102004),
    IceRefreshTimeout(103000),
    IceRefreshError(103001),
    IceCreatePermissionTimeout(104000),
    IceCreatePermissionError(104001),
    IceConnectivityCheckFailed(104002),
    IceSendError(105000),
    IcePeerReflexiveError(105001),
    IncompatibleIceSetup(105002),
    DtlsInternalError(106000),
    DtlsKeyExchangeFailed(106001),
    DtlsNotReady(106002),
    SctpNoPayloadData(107000),
    SctpUnsupportedStream(107001),
    SctpInvalidState(107002),
    SctpInternalError(107003),
    MediaTransportFailed(108000),
    ReliableDataChannelOpenError(109000),
    ReliableDataChannelSendError(109001),
    ConnectionInvalidArchitecture(110000),
    ConnectionInternalError(110001),
    ConnectionTransportStartError(110002),
    ConnectionTransportClosed(110003),
    ConnectionDeadStream(110004),
    ConnectionNotEstablished(110005),
    ConnectionRemoteFailure(110006),
    ConnectionSimulcastNotSupported(110007),
    StreamDisabled(111000),
    StreamEncryptionMismatch(111001),
    StreamDirectionMismatch(111002),
    DataStreamDirectionCannotBeChanged(111003),
    InvalidStreamDirectionChange(111004),
    LocalDescriptionError(112000),
    RemoteDescriptionError(112001),
    IceLocalRelayedStreamCandidateError(113000),
    IceInvalidServerAssignmentError(113001);
    
    private static final Map<Integer, ErrorCode> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(ErrorCode.class).iterator();
        while (it.hasNext()) {
            ErrorCode errorCode = (ErrorCode) it.next();
            lookup.put(Integer.valueOf(errorCode.getAssignedValue()), errorCode);
        }
    }

    private ErrorCode(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static ErrorCode getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
