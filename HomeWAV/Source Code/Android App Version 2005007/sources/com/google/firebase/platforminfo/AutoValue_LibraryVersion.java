package com.google.firebase.platforminfo;

import java.util.Objects;
import javax.annotation.Nonnull;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
final class AutoValue_LibraryVersion extends LibraryVersion {
    private final String libraryName;
    private final String version;

    AutoValue_LibraryVersion(String str, String str2) {
        Objects.requireNonNull(str, "Null libraryName");
        this.libraryName = str;
        Objects.requireNonNull(str2, "Null version");
        this.version = str2;
    }

    @Nonnull
    public String getLibraryName() {
        return this.libraryName;
    }

    @Nonnull
    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return "LibraryVersion{libraryName=" + this.libraryName + ", version=" + this.version + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LibraryVersion)) {
            return false;
        }
        LibraryVersion libraryVersion = (LibraryVersion) obj;
        if (!this.libraryName.equals(libraryVersion.getLibraryName()) || !this.version.equals(libraryVersion.getVersion())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.libraryName.hashCode() ^ 1000003) * 1000003) ^ this.version.hashCode();
    }
}
