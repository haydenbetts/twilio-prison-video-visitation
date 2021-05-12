package com.twilio.video;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TwilioException extends Exception {
    public static final int ACCESS_TOKEN_EXPIRED_EXCEPTION = 20104;
    public static final int ACCESS_TOKEN_GRANTS_INVALID_EXCEPTION = 20106;
    public static final int ACCESS_TOKEN_HEADER_INVALID_EXCEPTION = 20102;
    public static final int ACCESS_TOKEN_INVALID_EXCEPTION = 20101;
    public static final int ACCESS_TOKEN_ISSUER_INVALID_EXCEPTION = 20103;
    public static final int ACCESS_TOKEN_NOT_YET_VALID_EXCEPTION = 20105;
    public static final int ACCESS_TOKEN_SIGNATURE_INVALID_EXCEPTION = 20107;
    public static final int CONFIGURATION_ACQUIRE_FAILED_EXCEPTION = 53500;
    public static final int CONFIGURATION_ACQUIRE_TURN_FAILED_EXCEPTION = 53501;
    public static final int MEDIA_CLIENT_LOCAL_DESC_FAILED_EXCEPTION = 53400;
    public static final int MEDIA_CLIENT_REMOTE_DESC_FAILED_EXCEPTION = 53402;
    public static final int MEDIA_CONNECTION_ERROR_EXCEPTION = 53405;
    public static final int MEDIA_NO_SUPPORTED_CODEC_EXCEPTION = 53404;
    public static final int MEDIA_SERVER_LOCAL_DESC_FAILED_EXCEPTION = 53401;
    public static final int MEDIA_SERVER_REMOTE_DESC_FAILED_EXCEPTION = 53403;
    public static final int PARTICIPANT_DUPLICATE_IDENTITY_EXCEPTION = 53205;
    public static final int PARTICIPANT_IDENTITY_CHARS_INVALID_EXCEPTION = 53202;
    public static final int PARTICIPANT_IDENTITY_INVALID_EXCEPTION = 53200;
    public static final int PARTICIPANT_IDENTITY_TOO_LONG_EXCEPTION = 53201;
    public static final int PARTICIPANT_MAX_TRACKS_EXCEEDED_EXCEPTION = 53203;
    public static final int PARTICIPANT_NOT_FOUND_EXCEPTION = 53204;
    public static final int ROOM_CONNECT_FAILED_EXCEPTION = 53104;
    public static final int ROOM_CREATE_FAILED_EXCEPTION = 53103;
    public static final int ROOM_INVALID_PARAMETERS_EXCEPTION = 53114;
    public static final int ROOM_MAX_PARTICIPANTS_EXCEEDED_EXCEPTION = 53105;
    public static final int ROOM_MAX_PARTICIPANTS_OUT_OF_RANGE_EXCEPTION = 53107;
    public static final int ROOM_MEDIA_REGION_INVALID_EXCEPTION = 53115;
    public static final int ROOM_MEDIA_REGION_UNAVAILABLE_EXCEPTION = 53116;
    public static final int ROOM_NAME_CHARS_INVALID_EXCEPTION = 53102;
    public static final int ROOM_NAME_INVALID_EXCEPTION = 53100;
    public static final int ROOM_NAME_TOO_LONG_EXCEPTION = 53101;
    public static final int ROOM_NOT_FOUND_EXCEPTION = 53106;
    public static final int ROOM_ROOM_COMPLETED_EXCEPTION = 53118;
    public static final int ROOM_ROOM_EXISTS_EXCEPTION = 53113;
    public static final int ROOM_STATUS_CALLBACK_INVALID_EXCEPTION = 53111;
    public static final int ROOM_STATUS_CALLBACK_METHOD_INVALID_EXCEPTION = 53110;
    public static final int ROOM_STATUS_INVALID_EXCEPTION = 53112;
    public static final int ROOM_SUBSCRIPTION_OPERATION_NOT_SUPPORTED_EXCEPTION = 53117;
    public static final int ROOM_TIMEOUT_OUT_OF_RANGE_EXCEPTION = 53109;
    public static final int ROOM_TYPE_INVALID_EXCEPTION = 53108;
    public static final int SIGNALING_CONNECTION_DISCONNECTED_EXCEPTION = 53001;
    public static final int SIGNALING_CONNECTION_ERROR_EXCEPTION = 53000;
    public static final int SIGNALING_CONNECTION_TIMEOUT_EXCEPTION = 53002;
    public static final int SIGNALING_DNS_RESOLUTION_ERROR_EXCEPTION = 53005;
    public static final int SIGNALING_INCOMING_MESSAGE_INVALID_EXCEPTION = 53003;
    public static final int SIGNALING_OUTGOING_MESSAGE_INVALID_EXCEPTION = 53004;
    public static final int TRACK_INVALID_EXCEPTION = 53300;
    public static final int TRACK_NAME_CHARS_INVALID_EXCEPTION = 53303;
    public static final int TRACK_NAME_INVALID_EXCEPTION = 53301;
    public static final int TRACK_NAME_IS_DUPLICATED_EXCEPTION = 53304;
    public static final int TRACK_NAME_TOO_LONG_EXCEPTION = 53302;
    public static final int TRACK_SERVER_TRACK_CAPACITY_REACHED_EXCEPTION = 53305;
    private final int code;
    private final String explanation;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    TwilioException(int i, String str, String str2) {
        super(str);
        this.code = i;
        this.explanation = str2;
    }

    public int getCode() {
        return this.code;
    }

    public String getExplanation() {
        return this.explanation;
    }
}
