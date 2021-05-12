package com.forasoft.homewavvisitor.model.data;

import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\rHÆ\u0003J\t\u0010<\u001a\u00020\u000fHÆ\u0003J±\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002J\t\u0010B\u001a\u00020CHÖ\u0001J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0019¨\u0006E"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Message;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "id", "", "pubId", "created", "Lorg/threeten/bp/LocalDateTime;", "inmateId", "visitorId", "body", "sender", "Lcom/forasoft/homewavvisitor/model/data/Sender;", "type", "Lcom/forasoft/homewavvisitor/model/data/MessageType;", "status", "Lcom/forasoft/homewavvisitor/model/data/MessageStatus;", "views", "senderName", "streamName", "streamUrl", "imageUrl", "videoUrl", "senderStatus", "(Ljava/lang/String;Ljava/lang/String;Lorg/threeten/bp/LocalDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/Sender;Lcom/forasoft/homewavvisitor/model/data/MessageType;Lcom/forasoft/homewavvisitor/model/data/MessageStatus;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "getId", "getImageUrl", "getInmateId", "getPubId", "getSender", "()Lcom/forasoft/homewavvisitor/model/data/Sender;", "getSenderName", "getSenderStatus", "getStatus", "()Lcom/forasoft/homewavvisitor/model/data/MessageStatus;", "getStreamName", "getStreamUrl", "getType", "()Lcom/forasoft/homewavvisitor/model/data/MessageType;", "getVideoUrl", "getViews", "getVisitorId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Message.kt */
public final class Message implements ChatItem {
    private final String body;
    private final LocalDateTime created;
    private final String id;
    @SerializedName("image_url")
    private final String imageUrl;
    @SerializedName("inmate_id")
    private final String inmateId;
    @SerializedName("pubid")
    private final String pubId;
    private final Sender sender;
    @SerializedName("sender_name")
    private final String senderName;
    private final String senderStatus;
    private final MessageStatus status;
    @SerializedName("stream_name")
    private final String streamName;
    @SerializedName("stream_url")
    private final String streamUrl;
    private final MessageType type;
    @SerializedName("video_url")
    private final String videoUrl;
    @SerializedName("view_count")
    private final String views;
    @SerializedName("visitor_id")
    private final String visitorId;

    public static /* synthetic */ Message copy$default(Message message, String str, String str2, LocalDateTime localDateTime, String str3, String str4, String str5, Sender sender2, MessageType messageType, MessageStatus messageStatus, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i, Object obj) {
        Message message2 = message;
        int i2 = i;
        return message.copy((i2 & 1) != 0 ? message2.id : str, (i2 & 2) != 0 ? message2.pubId : str2, (i2 & 4) != 0 ? message.getCreated() : localDateTime, (i2 & 8) != 0 ? message2.inmateId : str3, (i2 & 16) != 0 ? message2.visitorId : str4, (i2 & 32) != 0 ? message2.body : str5, (i2 & 64) != 0 ? message2.sender : sender2, (i2 & 128) != 0 ? message2.type : messageType, (i2 & 256) != 0 ? message2.status : messageStatus, (i2 & 512) != 0 ? message2.views : str6, (i2 & 1024) != 0 ? message2.senderName : str7, (i2 & 2048) != 0 ? message2.streamName : str8, (i2 & 4096) != 0 ? message2.streamUrl : str9, (i2 & 8192) != 0 ? message2.imageUrl : str10, (i2 & 16384) != 0 ? message2.videoUrl : str11, (i2 & 32768) != 0 ? message2.senderStatus : str12);
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.views;
    }

    public final String component11() {
        return this.senderName;
    }

    public final String component12() {
        return this.streamName;
    }

    public final String component13() {
        return this.streamUrl;
    }

    public final String component14() {
        return this.imageUrl;
    }

    public final String component15() {
        return this.videoUrl;
    }

    public final String component16() {
        return this.senderStatus;
    }

    public final String component2() {
        return this.pubId;
    }

    public final LocalDateTime component3() {
        return getCreated();
    }

    public final String component4() {
        return this.inmateId;
    }

    public final String component5() {
        return this.visitorId;
    }

    public final String component6() {
        return this.body;
    }

    public final Sender component7() {
        return this.sender;
    }

    public final MessageType component8() {
        return this.type;
    }

    public final MessageStatus component9() {
        return this.status;
    }

    public final Message copy(String str, String str2, LocalDateTime localDateTime, String str3, String str4, String str5, Sender sender2, MessageType messageType, MessageStatus messageStatus, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        String str13 = str;
        Intrinsics.checkParameterIsNotNull(str13, "id");
        Intrinsics.checkParameterIsNotNull(str2, "pubId");
        Intrinsics.checkParameterIsNotNull(localDateTime, "created");
        Intrinsics.checkParameterIsNotNull(str3, "inmateId");
        Intrinsics.checkParameterIsNotNull(str4, "visitorId");
        Intrinsics.checkParameterIsNotNull(str5, "body");
        Intrinsics.checkParameterIsNotNull(sender2, "sender");
        Intrinsics.checkParameterIsNotNull(messageType, "type");
        Intrinsics.checkParameterIsNotNull(messageStatus, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(str6, "views");
        Intrinsics.checkParameterIsNotNull(str7, "senderName");
        Intrinsics.checkParameterIsNotNull(str8, "streamName");
        return new Message(str13, str2, localDateTime, str3, str4, str5, sender2, messageType, messageStatus, str6, str7, str8, str9, str10, str11, str12);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.pubId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        LocalDateTime created2 = getCreated();
        int hashCode3 = (hashCode2 + (created2 != null ? created2.hashCode() : 0)) * 31;
        String str3 = this.inmateId;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.visitorId;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.body;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Sender sender2 = this.sender;
        int hashCode7 = (hashCode6 + (sender2 != null ? sender2.hashCode() : 0)) * 31;
        MessageType messageType = this.type;
        int hashCode8 = (hashCode7 + (messageType != null ? messageType.hashCode() : 0)) * 31;
        MessageStatus messageStatus = this.status;
        int hashCode9 = (hashCode8 + (messageStatus != null ? messageStatus.hashCode() : 0)) * 31;
        String str6 = this.views;
        int hashCode10 = (hashCode9 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.senderName;
        int hashCode11 = (hashCode10 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.streamName;
        int hashCode12 = (hashCode11 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.streamUrl;
        int hashCode13 = (hashCode12 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.imageUrl;
        int hashCode14 = (hashCode13 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.videoUrl;
        int hashCode15 = (hashCode14 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.senderStatus;
        if (str12 != null) {
            i = str12.hashCode();
        }
        return hashCode15 + i;
    }

    public String toString() {
        return "Message(id=" + this.id + ", pubId=" + this.pubId + ", created=" + getCreated() + ", inmateId=" + this.inmateId + ", visitorId=" + this.visitorId + ", body=" + this.body + ", sender=" + this.sender + ", type=" + this.type + ", status=" + this.status + ", views=" + this.views + ", senderName=" + this.senderName + ", streamName=" + this.streamName + ", streamUrl=" + this.streamUrl + ", imageUrl=" + this.imageUrl + ", videoUrl=" + this.videoUrl + ", senderStatus=" + this.senderStatus + ")";
    }

    public Message(String str, String str2, LocalDateTime localDateTime, String str3, String str4, String str5, Sender sender2, MessageType messageType, MessageStatus messageStatus, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        String str13 = str2;
        LocalDateTime localDateTime2 = localDateTime;
        String str14 = str3;
        String str15 = str4;
        String str16 = str5;
        Sender sender3 = sender2;
        MessageType messageType2 = messageType;
        MessageStatus messageStatus2 = messageStatus;
        String str17 = str6;
        String str18 = str7;
        String str19 = str8;
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(str13, "pubId");
        Intrinsics.checkParameterIsNotNull(localDateTime2, "created");
        Intrinsics.checkParameterIsNotNull(str14, "inmateId");
        Intrinsics.checkParameterIsNotNull(str15, "visitorId");
        Intrinsics.checkParameterIsNotNull(str16, "body");
        Intrinsics.checkParameterIsNotNull(sender3, "sender");
        Intrinsics.checkParameterIsNotNull(messageType2, "type");
        Intrinsics.checkParameterIsNotNull(messageStatus2, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(str17, "views");
        Intrinsics.checkParameterIsNotNull(str18, "senderName");
        Intrinsics.checkParameterIsNotNull(str19, "streamName");
        this.id = str;
        this.pubId = str13;
        this.created = localDateTime2;
        this.inmateId = str14;
        this.visitorId = str15;
        this.body = str16;
        this.sender = sender3;
        this.type = messageType2;
        this.status = messageStatus2;
        this.views = str17;
        this.senderName = str18;
        this.streamName = str19;
        this.streamUrl = str9;
        this.imageUrl = str10;
        this.videoUrl = str11;
        this.senderStatus = str12;
    }

    public int compareTo(ChatItem chatItem) {
        Intrinsics.checkParameterIsNotNull(chatItem, "other");
        return ChatItem.DefaultImpls.compareTo(this, chatItem);
    }

    public final String getId() {
        return this.id;
    }

    public final String getPubId() {
        return this.pubId;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public final String getInmateId() {
        return this.inmateId;
    }

    public final String getVisitorId() {
        return this.visitorId;
    }

    public final String getBody() {
        return this.body;
    }

    public final Sender getSender() {
        return this.sender;
    }

    public final MessageType getType() {
        return this.type;
    }

    public final MessageStatus getStatus() {
        return this.status;
    }

    public final String getViews() {
        return this.views;
    }

    public final String getSenderName() {
        return this.senderName;
    }

    public final String getStreamName() {
        return this.streamName;
    }

    public final String getStreamUrl() {
        return this.streamUrl;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Message(String str, String str2, LocalDateTime localDateTime, String str3, String str4, String str5, Sender sender2, MessageType messageType, MessageStatus messageStatus, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, localDateTime, str3, str4, str5, sender2, messageType, messageStatus, str6, str7, str8, str9, str10, str11, (i & 32768) != 0 ? null : str12);
    }

    public final String getSenderStatus() {
        return this.senderStatus;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Message) && Intrinsics.areEqual((Object) ((Message) obj).id, (Object) this.id);
    }
}
