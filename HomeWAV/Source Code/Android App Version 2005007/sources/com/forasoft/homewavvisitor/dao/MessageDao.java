package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.Message;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\t2\u0006\u0010\u000b\u001a\u00020\u0007H'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\t2\u0006\u0010\u000b\u001a\u00020\u0007H'J$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H'J$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\t2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H'J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH'J\u0016\u0010\u0013\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\n0\u0006H'¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/MessageDao;", "", "deleteAll", "", "deleteMessages", "messages", "", "", "getAll", "Lio/reactivex/Flowable;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "visitorId", "getAllUnread", "Lio/reactivex/Single;", "inmateId", "getDialog", "readDialog", "saveMessage", "message", "saveMessages", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageDao.kt */
public interface MessageDao {
    void deleteAll();

    void deleteMessages(List<String> list);

    Flowable<List<Message>> getAll(String str);

    Flowable<List<Message>> getAllUnread(String str);

    Single<List<Message>> getAllUnread(String str, String str2);

    Flowable<List<Message>> getDialog(String str, String str2);

    void readDialog(String str, String str2);

    void saveMessage(Message message);

    void saveMessages(List<Message> list);
}
