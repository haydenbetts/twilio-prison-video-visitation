package com.forasoft.homewavvisitor.model.data;

import com.google.gson.annotations.SerializedName;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\b&\u0018\u00002\u00020\u0001:\u000e\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "", "()V", "inmateId", "", "getInmateId", "()Ljava/lang/String;", "inmateName", "getInmateName", "Airship", "BalanceBelowTwo", "BalanceBelowZero", "FundsAdded", "IncomingCall", "InmateOnline", "MessageGif", "MessageImage", "MessageS3Video", "MessageText", "MessageVideo", "RequestFunds", "VisitCancel", "VisitConfirm", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationBody.kt */
public abstract class NotificationBody {
    public abstract String getInmateId();

    public abstract String getInmateName();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$BalanceBelowTwo;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class BalanceBelowTwo extends NotificationBody {
        private final String inmateId;
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public BalanceBelowTwo(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$BalanceBelowZero;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class BalanceBelowZero extends NotificationBody {
        private final String inmateId;
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public BalanceBelowZero(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$VisitConfirm;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "timeSlot", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getTimeSlot", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class VisitConfirm extends NotificationBody {
        @SerializedName("inmate_id")
        private final String inmateId;
        @SerializedName("inmate_name")
        private final String inmateName;
        @SerializedName("time_slot")
        private final String timeSlot;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getTimeSlot() {
            return this.timeSlot;
        }

        public VisitConfirm(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, "timeSlot");
            this.inmateId = str;
            this.inmateName = str2;
            this.timeSlot = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$VisitCancel;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "timeSlot", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getTimeSlot", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class VisitCancel extends NotificationBody {
        @SerializedName("inmate_id")
        private final String inmateId;
        @SerializedName("inmate_name")
        private final String inmateName;
        @SerializedName("time_slot")
        private final String timeSlot;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getTimeSlot() {
            return this.timeSlot;
        }

        public VisitCancel(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, "timeSlot");
            this.inmateId = str;
            this.inmateName = str2;
            this.timeSlot = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$MessageText;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "body", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getInmateId", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class MessageText extends NotificationBody {
        private final String body;
        @SerializedName("inmateId")
        private final String inmateId;
        @SerializedName("inmateName")
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getBody() {
            return this.body;
        }

        public MessageText(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, "body");
            this.inmateId = str;
            this.inmateName = str2;
            this.body = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$MessageVideo;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class MessageVideo extends NotificationBody {
        @SerializedName("inmateId")
        private final String inmateId;
        @SerializedName("inmateName")
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public MessageVideo(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$MessageS3Video;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class MessageS3Video extends NotificationBody {
        @SerializedName("inmateId")
        private final String inmateId;
        @SerializedName("inmateName")
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public MessageS3Video(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$MessageImage;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "previewUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getPreviewUrl", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class MessageImage extends NotificationBody {
        @SerializedName("inmateId")
        private final String inmateId;
        @SerializedName("inmateName")
        private final String inmateName;
        @SerializedName("imageUrl")
        private final String previewUrl;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getPreviewUrl() {
            return this.previewUrl;
        }

        public MessageImage(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
            this.previewUrl = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$MessageGif;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "previewUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getPreviewUrl", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class MessageGif extends NotificationBody {
        @SerializedName("inmateId")
        private final String inmateId;
        @SerializedName("inmateName")
        private final String inmateName;
        @SerializedName("imageUrl")
        private final String previewUrl;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getPreviewUrl() {
            return this.previewUrl;
        }

        public MessageGif(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
            this.previewUrl = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$FundsAdded;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "value", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getValue", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class FundsAdded extends NotificationBody {
        @SerializedName("inmate_id")
        private final String inmateId;
        @SerializedName("inmate_name")
        private final String inmateName;
        private final String value;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getValue() {
            return this.value;
        }

        public FundsAdded(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, CommonProperties.VALUE);
            this.inmateId = str;
            this.inmateName = str2;
            this.value = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$RequestFunds;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "value", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getValue", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class RequestFunds extends NotificationBody {
        @SerializedName("inmate_id")
        private final String inmateId;
        @SerializedName("inmate_name")
        private final String inmateName;
        private final String value;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getValue() {
            return this.value;
        }

        public RequestFunds(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, CommonProperties.VALUE);
            this.inmateId = str;
            this.inmateName = str2;
            this.value = str3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$IncomingCall;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class IncomingCall extends NotificationBody {
        @SerializedName("inmate_id")
        private final String inmateId;
        @SerializedName("inmate_name")
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public IncomingCall(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$InmateOnline;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class InmateOnline extends NotificationBody {
        private final String inmateId;
        private final String inmateName;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public InmateOnline(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            this.inmateId = str;
            this.inmateName = str2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/NotificationBody$Airship;", "Lcom/forasoft/homewavvisitor/model/data/NotificationBody;", "inmateId", "", "inmateName", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getInmateName", "getMessage", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationBody.kt */
    public static final class Airship extends NotificationBody {
        private final String inmateId;
        private final String inmateName;
        private final String message;

        public String getInmateId() {
            return this.inmateId;
        }

        public String getInmateName() {
            return this.inmateName;
        }

        public final String getMessage() {
            return this.message;
        }

        public Airship(String str, String str2, String str3) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "inmateName");
            Intrinsics.checkParameterIsNotNull(str3, "message");
            this.inmateId = str;
            this.inmateName = str2;
            this.message = str3;
        }
    }
}
