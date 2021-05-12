package tvi.webrtc;

import java.util.Locale;

public class SessionDescription {
    public final String description;
    public final Type type;

    public enum Type {
        OFFER,
        PRANSWER,
        ANSWER;

        public String canonicalForm() {
            return name().toLowerCase(Locale.US);
        }

        public static Type fromCanonicalForm(String str) {
            return (Type) valueOf(Type.class, str.toUpperCase(Locale.US));
        }
    }

    public SessionDescription(Type type2, String str) {
        this.type = type2;
        this.description = str;
    }

    /* access modifiers changed from: package-private */
    public String getDescription() {
        return this.description;
    }

    /* access modifiers changed from: package-private */
    public String getTypeInCanonicalForm() {
        return this.type.canonicalForm();
    }
}
