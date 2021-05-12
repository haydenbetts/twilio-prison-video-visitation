package fm.liveswitch.stun;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.stun.ice.RoleConflictError;
import fm.liveswitch.stun.turn.AddressFamilyNotSupportedError;
import fm.liveswitch.stun.turn.AllocationMismatchError;
import fm.liveswitch.stun.turn.AllocationQuotaReachedError;
import fm.liveswitch.stun.turn.ConnectionAlreadyExistsError;
import fm.liveswitch.stun.turn.ConnectionTimeoutOrFailureError;
import fm.liveswitch.stun.turn.ForbiddenError;
import fm.liveswitch.stun.turn.InsufficientCapacityError;
import fm.liveswitch.stun.turn.MobilityForbiddenError;
import fm.liveswitch.stun.turn.PeerAddressFamilyMismatchError;
import fm.liveswitch.stun.turn.UnsupportedTransportProtocolError;
import fm.liveswitch.stun.turn.WrongCredentialsError;
import org.java_websocket.WebSocketImpl;

public abstract class Error extends fm.liveswitch.Error {
    private static Error createAddressFamilyNotSupportedError(Message message) {
        return new AddressFamilyNotSupportedError();
    }

    private static Error createAllocationMismatchError(Message message) {
        return new AllocationMismatchError("Server responded with 437 Allocation Mismatch.");
    }

    private static Error createAllocationQuotaReachedError(Message message) {
        return new AllocationQuotaReachedError("Server responded with 486 Allocation Quota Reached.");
    }

    private static Error createBadRequestError(Message message) {
        return new BadRequestError("Server responded with 400 Bad Request.");
    }

    private static Error createConnectionAlreadyExistsError(Message message) {
        return new ConnectionAlreadyExistsError("Server responded with 446 Connection Already Exists.");
    }

    private static Error createConnectionTimeoutOrFailureError(Message message) {
        return new ConnectionTimeoutOrFailureError("Server responded with 447 Connection Timeout or Failure.");
    }

    private static Error createForbiddenError(Message message) {
        return new ForbiddenError("Server responded with 403 Forbidden.");
    }

    private static Error createInsufficientCapacityError(Message message) {
        return new InsufficientCapacityError("Server responded with 508 Insufficient Capacity.");
    }

    private static fm.liveswitch.Error createIntegrityCheckFailureError(Message message) {
        return new MissingUsernameError("Server responded with 431 Integrity Check Failure.");
    }

    private static fm.liveswitch.Error createMissingUsernameError(Message message) {
        return new MissingUsernameError("Server responded with 432 Missing Username.");
    }

    private static Error createMobilityForbiddenError(Message message) {
        return new MobilityForbiddenError("Server responded with 405 Mobility Forbidden.");
    }

    private static Error createPeerAddressFamilyMismatchError(Message message) {
        return new PeerAddressFamilyMismatchError("Server responded with 443 Peer Address Family Mismatch.");
    }

    private static Error createRoleConflictError(Message message) {
        return new RoleConflictError("Server responded with 487 Role Conflict.", message.getIceControlled(), message.getIceControlling());
    }

    private static Error createServerError(Message message) {
        return new ServerError("Server responded with 500 Server Error.");
    }

    private static fm.liveswitch.Error createStaleCredentialsError(Message message) {
        return new StaleCredentialsError("Server responded with 430 Stale Credentials.");
    }

    private static fm.liveswitch.Error createStaleNonceError(Message message) {
        NonceAttribute nonce = message.getNonce();
        RealmAttribute realm = message.getRealm();
        if (nonce == null) {
            return new fm.liveswitch.Error(ErrorCode.StunInvalidErrorCode, new Exception("Server responded with 438 Stale Nonce, but NONCE is missing."));
        }
        return new StaleNonceError("Server responded with 438 Stale Nonce.", nonce, realm);
    }

    public static fm.liveswitch.Error createStunError(int i, Message message) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(i));
        if (integerExtensions.equals("300")) {
            return createTryAlternateError(message);
        }
        if (integerExtensions.equals("400")) {
            return createBadRequestError(message);
        }
        if (integerExtensions.equals("401")) {
            return createUnauthorizedError(message);
        }
        if (integerExtensions.equals("403")) {
            return createForbiddenError(message);
        }
        if (integerExtensions.equals("405")) {
            return createMobilityForbiddenError(message);
        }
        if (integerExtensions.equals("420")) {
            return createUnknownAttributeError(message);
        }
        if (integerExtensions.equals("430")) {
            return createStaleCredentialsError(message);
        }
        if (integerExtensions.equals("431")) {
            return createIntegrityCheckFailureError(message);
        }
        if (integerExtensions.equals("432")) {
            return createMissingUsernameError(message);
        }
        if (integerExtensions.equals("437")) {
            return createAllocationMismatchError(message);
        }
        if (integerExtensions.equals("438")) {
            return createStaleNonceError(message);
        }
        if (integerExtensions.equals("440")) {
            return createAddressFamilyNotSupportedError(message);
        }
        if (integerExtensions.equals("441")) {
            return createWrongCredentialsError(message);
        }
        if (integerExtensions.equals("442")) {
            return createUnsupportedTransportProtocolError(message);
        }
        if (integerExtensions.equals("443")) {
            return createPeerAddressFamilyMismatchError(message);
        }
        if (integerExtensions.equals("446")) {
            return createConnectionAlreadyExistsError(message);
        }
        if (integerExtensions.equals("447")) {
            return createConnectionTimeoutOrFailureError(message);
        }
        if (integerExtensions.equals("486")) {
            return createAllocationQuotaReachedError(message);
        }
        if (integerExtensions.equals("487")) {
            return createRoleConflictError(message);
        }
        if (integerExtensions.equals("500")) {
            return createServerError(message);
        }
        if (integerExtensions.equals("508")) {
            return createInsufficientCapacityError(message);
        }
        return null;
    }

    private static fm.liveswitch.Error createTryAlternateError(Message message) {
        if (message.getMessageIntegrity() == null) {
            return new fm.liveswitch.Error(ErrorCode.StunInvalidMessageIntegrity, new Exception("Server responded with 300 Try Alternate, but MESSAGE-INTEGRITY check failed."));
        }
        AlternateServerAttribute alternateServer = message.getAlternateServer();
        if (alternateServer == null) {
            return new fm.liveswitch.Error(ErrorCode.StunInvalidErrorCode, new Exception("Server responded with 300 Try Alternate, but ALTERNATE-SERVER is missing."));
        }
        return new TryAlternateStunError("Server responded with 300 Try Alternate.", alternateServer);
    }

    private static Error createUnauthorizedError(Message message) {
        return new UnauthorizedStunError(message.getNonce(), message.getRealm(), "Server responded with 401 Unauthorized.");
    }

    private static fm.liveswitch.Error createUnknownAttributeError(Message message) {
        UnknownAttributesAttribute unknownAttributes = message.getUnknownAttributes();
        if (unknownAttributes == null) {
            return new fm.liveswitch.Error(ErrorCode.StunInvalidErrorCode, new Exception("Server responded with 420 Unknown Attribute, but UNKNOWN-ATTRIBUTES is missing."));
        }
        return new UnknownAttributeError("Server responded with 420 Unknown Attribute.", unknownAttributes);
    }

    private static Error createUnsupportedTransportProtocolError(Message message) {
        return new UnsupportedTransportProtocolError("Server responded with 442 Unsupported Transport Protocol.");
    }

    private static Error createWrongCredentialsError(Message message) {
        return new WrongCredentialsError("Server responded with 441 Wrong Credentials.");
    }

    public Error(ErrorCode errorCode, String str) {
        super(errorCode, new Exception(str));
    }

    public int getStunCode() {
        ErrorCode code = super.getCode();
        if (code == ErrorCode.StunTryAlternate) {
            return 300;
        }
        if (code == ErrorCode.StunBadRequest) {
            return 400;
        }
        if (code == ErrorCode.StunUnauthorized) {
            return 401;
        }
        if (code == ErrorCode.StunTurnForbidden) {
            return 403;
        }
        if (code == ErrorCode.StunTurnMobilityForbidden) {
            return 405;
        }
        if (code == ErrorCode.StunUnknownAttribute) {
            return 420;
        }
        if (code == ErrorCode.StunStaleCredentials) {
            return 430;
        }
        if (code == ErrorCode.StunIntegrityCheckFailure) {
            return 431;
        }
        if (code == ErrorCode.StunMissingUsername) {
            return 432;
        }
        if (code == ErrorCode.StunTurnAllocationMismatch) {
            return 437;
        }
        if (code == ErrorCode.StunStaleNonce) {
            return 438;
        }
        if (code == ErrorCode.StunAddressFamilyNotSupported) {
            return 440;
        }
        if (code == ErrorCode.StunTurnWrongCredentials) {
            return 441;
        }
        if (code == ErrorCode.StunTurnUnsupportedTransportProtocol) {
            return 442;
        }
        if (code == ErrorCode.StunTurnPeerAddressFamilyMismatch) {
            return WebSocketImpl.DEFAULT_WSS_PORT;
        }
        if (code == ErrorCode.StunTurnConnectionAlreadyExists) {
            return 446;
        }
        if (code == ErrorCode.StunTurnConnectionTimeoutOrFailure) {
            return 447;
        }
        if (code == ErrorCode.StunTurnAllocationQuotaReached) {
            return 486;
        }
        if (code == ErrorCode.StunIceRoleConflict) {
            return 487;
        }
        if (code == ErrorCode.StunServerError) {
            return 500;
        }
        return code == ErrorCode.StunTurnInsufficientCapacity ? 508 : -1;
    }

    public int getStunErrorCode() {
        return getStunCode();
    }
}
