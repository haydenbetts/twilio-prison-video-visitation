package fm.liveswitch.stun;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ErrorCode;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.StringExtensions;

public class UnknownAttributeError extends Error {
    private UnknownAttributesAttribute _unknownAttributes;

    public String getDescription() {
        String errorCode = super.getCode().toString();
        if (super.getMessage() == null) {
            super.setMessage("Server responded with 420 Unknown Attribute.");
        }
        String concat = StringExtensions.concat(errorCode, " ", super.getMessage().trim());
        if (getUnknownAttributes() != null) {
            concat = StringExtensions.concat(concat, " . Unknow types:");
            if (ArrayExtensions.getLength(getUnknownAttributes().getTypes()) == 0) {
                concat = StringExtensions.concat(concat, " None.");
            }
            for (int valueOf : getUnknownAttributes().getTypes()) {
                concat = StringExtensions.concat(concat, " ", IntegerExtensions.toString(Integer.valueOf(valueOf)));
            }
        }
        return concat;
    }

    public UnknownAttributesAttribute getUnknownAttributes() {
        return this._unknownAttributes;
    }

    private void setUnknownAttributes(UnknownAttributesAttribute unknownAttributesAttribute) {
        this._unknownAttributes = unknownAttributesAttribute;
    }

    public UnknownAttributeError(String str, UnknownAttributesAttribute unknownAttributesAttribute) {
        super(ErrorCode.StunUnknownAttribute, str);
        setUnknownAttributes(unknownAttributesAttribute);
    }

    public UnknownAttributeError(UnknownAttributesAttribute unknownAttributesAttribute) {
        this((String) null, unknownAttributesAttribute);
    }
}
