package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.payment.Credit;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class CreditDao_Impl implements CreditDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfCredit;
    /* access modifiers changed from: private */
    public final LocalDateTimeConverter __localDateTimeConverter = new LocalDateTimeConverter();
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CreditDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCredit = new EntityInsertionAdapter<Credit>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `credits`(`id`,`inmate_id`,`braintree_transaction_id`,`stripe_transaction_id`,`created`,`creator`,`notes`,`type`,`value`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Credit credit) {
                if (credit.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, credit.getId());
                }
                if (credit.getInmate_id() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, credit.getInmate_id());
                }
                if (credit.getBraintree_transaction_id() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, credit.getBraintree_transaction_id());
                }
                if (credit.getStripe_transaction_id() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, credit.getStripe_transaction_id());
                }
                Long fromLocalDateTime = CreditDao_Impl.this.__localDateTimeConverter.fromLocalDateTime(credit.getCreated());
                if (fromLocalDateTime == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, fromLocalDateTime.longValue());
                }
                if (credit.getCreator() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, credit.getCreator());
                }
                if (credit.getNotes() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, credit.getNotes());
                }
                if (credit.getType() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, credit.getType());
                }
                if (credit.getValue() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, credit.getValue());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM credits";
            }
        };
    }

    public void saveCredit(Credit credit) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCredit.insert(credit);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteAll() {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public Flowable<List<Credit>> getCreditsForInmate(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM credits WHERE inmate_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"credits"}, new Callable<List<Credit>>() {
            public List<Credit> call() throws Exception {
                Long l;
                Cursor query = CreditDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_INMATE_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("braintree_transaction_id");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stripe_transaction_id");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("creator");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(CommonProperties.VALUE);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        String string3 = query.getString(columnIndexOrThrow3);
                        String string4 = query.getString(columnIndexOrThrow4);
                        if (query.isNull(columnIndexOrThrow5)) {
                            l = null;
                        } else {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow5));
                        }
                        arrayList.add(new Credit(string, string2, string3, string4, CreditDao_Impl.this.__localDateTimeConverter.toLocalDateTime(l), query.getString(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8), query.getString(columnIndexOrThrow9)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
