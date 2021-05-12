package fm.liveswitch.stun.ice;

import fm.liveswitch.ErrorCode;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Error;

public class RoleConflictError extends Error {
    private ControlledAttribute _iceControlled;
    private ControllingAttribute _iceControlling;

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 487 Role Conflict.");
        }
        String concat = StringExtensions.concat(errorCode, " ", super.getMessage().trim());
        if (getIceControlling() != null) {
            concat = StringExtensions.concat(concat, StringExtensions.format(" Ice Controlling attribute: {0}", (Object) LongExtensions.toString(Long.valueOf(getIceControlling().getValue()))));
        }
        return getIceControlled() != null ? StringExtensions.concat(concat, StringExtensions.format(" Ice Controlled attribute: {0}", (Object) LongExtensions.toString(Long.valueOf(getIceControlled().getValue())))) : concat;
    }

    public ControlledAttribute getIceControlled() {
        return this._iceControlled;
    }

    public ControllingAttribute getIceControlling() {
        return this._iceControlling;
    }

    public RoleConflictError(ControlledAttribute controlledAttribute, ControllingAttribute controllingAttribute) {
        this((String) null, controlledAttribute, controllingAttribute);
    }

    public RoleConflictError(String str, ControlledAttribute controlledAttribute, ControllingAttribute controllingAttribute) {
        super(ErrorCode.StunIceRoleConflict, str);
        setIceControlled(controlledAttribute);
        setIceControlling(controllingAttribute);
    }

    private void setIceControlled(ControlledAttribute controlledAttribute) {
        this._iceControlled = controlledAttribute;
    }

    private void setIceControlling(ControllingAttribute controllingAttribute) {
        this._iceControlling = controllingAttribute;
    }
}
