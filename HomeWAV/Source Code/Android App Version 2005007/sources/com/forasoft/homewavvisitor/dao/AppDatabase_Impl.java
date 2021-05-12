package com.forasoft.homewavvisitor.dao;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.actions.FetchDeviceInfoAction;
import com.urbanairship.util.Attributes;
import java.util.HashMap;
import java.util.HashSet;

public final class AppDatabase_Impl extends AppDatabase {
    private volatile CallDao _callDao;
    private volatile CreditDao _creditDao;
    private volatile InmateDao _inmateDao;
    private volatile MessageDao _messageDao;
    private volatile NotificationDao _notificationDao;
    private volatile UserAnalyticsDao _userAnalyticsDao;
    private volatile UserDao _userDao;
    private volatile VisitDao _visitDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(14) {
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` TEXT NOT NULL, `visitor_id` TEXT NOT NULL, `created` TEXT NOT NULL, `pin` TEXT, `birthDate` INTEGER NOT NULL, `username` TEXT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `fullName` TEXT NOT NULL, `phone` TEXT NOT NULL, `email` TEXT NOT NULL, `state` TEXT NOT NULL, `city` TEXT NOT NULL, `street` TEXT NOT NULL, `zip` TEXT NOT NULL, `photoProfileUrl` TEXT, `photoIdUrl` TEXT, `photoProfile` TEXT, `photoId` TEXT, `verified` INTEGER NOT NULL, `isAdmin` INTEGER NOT NULL, `isNotificationSubscriptionEnabled` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `inmates` (`id` TEXT NOT NULL, `visitor_id` TEXT NOT NULL, `created` TEXT, `dob` TEXT, `dob_formatted` TEXT, `first_name` TEXT, `identifier` TEXT, `inmate_location` TEXT, `last_name` TEXT, `full_name` TEXT, `location` TEXT, `middle_name` TEXT, `prison_id` TEXT, `prison_name` TEXT, `suffix` TEXT, `approved` TEXT, `credit_balance` TEXT, `status` TEXT, `calls_disabled` TEXT, `calls_disabled_until` TEXT, `can_text_message` INTEGER NOT NULL, `can_video_message` INTEGER NOT NULL, `can_image_message` INTEGER NOT NULL, `deleted` TEXT, `deleted_warnings` TEXT, `description` TEXT NOT NULL, `error_reported` TEXT NOT NULL, `fmt_name` TEXT NOT NULL, `inmate_general_funds_auto_recharge_enabled` TEXT NOT NULL, `internal_notes` TEXT, `invisible` TEXT, `is_fraud` TEXT NOT NULL, `is_fraud_set_by` TEXT, `notes` TEXT, `occupant_id` TEXT NOT NULL, `prison_max_text_message_length` TEXT NOT NULL, `prison_max_video_message_length` TEXT NOT NULL, `prison_price_per_text_message` TEXT NOT NULL, `prison_price_per_video_message` TEXT NOT NULL, `prison_price_per_image_message` TEXT NOT NULL, `prison_price_per_gif_message` TEXT, `per_minute_charging_enabled` TEXT, `pubid` TEXT NOT NULL, `relationship` TEXT NOT NULL, `require_approval` TEXT, `should_record` TEXT NOT NULL, `tags` TEXT, `talk_to_me_funds_auto_recharge_enabled` TEXT NOT NULL, `visitor_city` TEXT NOT NULL, `visitor_created` TEXT NOT NULL, `visitor_dob` TEXT NOT NULL, `visitor_first_name` TEXT NOT NULL, `visitor_last_name` TEXT NOT NULL, `visitor_name` TEXT NOT NULL, `visitor_phone` TEXT NOT NULL, `visitor_pin` TEXT NOT NULL, `visitor_should_record` TEXT NOT NULL, `visitor_state` TEXT NOT NULL, `visitor_state_abbreviation` TEXT NOT NULL, `visitor_street1` TEXT NOT NULL, `visitor_street2` TEXT, `visitor_username` TEXT NOT NULL, `visitor_zipcode` TEXT NOT NULL, `voice_calls_disabled` TEXT NOT NULL, `voice_calls_disabled_until` TEXT, `zone` TEXT, `prison_video_calls_disabled` INTEGER NOT NULL, `prison_voice_calls_disabled` INTEGER NOT NULL, `prison_state` TEXT, `prison_payment_gateway` TEXT, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `notifications` (`id` INTEGER NOT NULL, `type` TEXT NOT NULL, `body` TEXT NOT NULL, `created` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `messages` (`id` TEXT NOT NULL, `pubId` TEXT NOT NULL, `created` INTEGER NOT NULL, `inmateId` TEXT NOT NULL, `visitorId` TEXT NOT NULL, `body` TEXT NOT NULL, `sender` INTEGER NOT NULL, `type` INTEGER NOT NULL, `status` INTEGER NOT NULL, `views` TEXT NOT NULL, `senderName` TEXT NOT NULL, `streamName` TEXT NOT NULL, `streamUrl` TEXT, `imageUrl` TEXT, `videoUrl` TEXT, `senderStatus` TEXT, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `visits` (`slotId` TEXT NOT NULL, `inmateId` TEXT NOT NULL, `inmate` TEXT NOT NULL, `facility` TEXT NOT NULL, `station` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `timezone` TEXT NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`slotId`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `calls` (`id` TEXT NOT NULL, `protocol` TEXT, `disconnect_cause` TEXT, `visitor_username` TEXT, `notes` TEXT, `splicing_priority` TEXT, `timezone` TEXT, `splicing_finished` TEXT, `visitor_id` TEXT, `visitor_checkin` TEXT, `beta_splicer` TEXT, `splicing_outcome` TEXT, `visitor_location` TEXT, `prison_name` TEXT, `splicing_hints` TEXT, `disconnected` INTEGER, `ui` TEXT, `internal_notes` TEXT, `zone` TEXT, `station` TEXT, `reviewed` TEXT, `etype` TEXT, `purged` TEXT, `splicing_started` TEXT, `inmate_id` TEXT, `extension_count` TEXT, `inmate_name` TEXT, `recorded` TEXT, `visitor_email` TEXT, `visitor_station_id` TEXT, `visitor_name` TEXT, `tags` TEXT, `connected` INTEGER, `warning_message` TEXT, `pubid` TEXT NOT NULL, `free_seconds` TEXT, `invalidated` TEXT, `inmate_checkin` TEXT, `disconnect_required` TEXT, `account` TEXT, `age` TEXT, `prison_id` TEXT, `inmate_station_id` TEXT, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `credits` (`id` TEXT NOT NULL, `inmate_id` TEXT, `braintree_transaction_id` TEXT, `stripe_transaction_id` TEXT, `created` INTEGER NOT NULL, `creator` TEXT NOT NULL, `notes` TEXT NOT NULL, `type` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `UserAnalytics` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_id` INTEGER NOT NULL, `first_call_done` INTEGER NOT NULL, `call_day_timestamp` INTEGER, `call_day_count` INTEGER NOT NULL, `call_week_timestamp` INTEGER, `call_week_count` INTEGER NOT NULL, `no_money_reported` INTEGER NOT NULL, `less_two_funds_reported` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"2619f7ca37628ed143e66969f12e013c\")");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `User`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `inmates`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `notifications`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `messages`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `visits`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `calls`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `credits`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `UserAnalytics`");
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = AppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                AppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(22);
                hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                hashMap.put(UploadWorker.KEY_VISITOR_ID, new TableInfo.Column(UploadWorker.KEY_VISITOR_ID, "TEXT", true, 0));
                hashMap.put("created", new TableInfo.Column("created", "TEXT", true, 0));
                hashMap.put("pin", new TableInfo.Column("pin", "TEXT", false, 0));
                hashMap.put("birthDate", new TableInfo.Column("birthDate", "INTEGER", true, 0));
                hashMap.put(Attributes.USERNAME, new TableInfo.Column(Attributes.USERNAME, "TEXT", true, 0));
                hashMap.put("firstName", new TableInfo.Column("firstName", "TEXT", true, 0));
                hashMap.put("lastName", new TableInfo.Column("lastName", "TEXT", true, 0));
                hashMap.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0));
                hashMap.put(ShippingInfoWidget.PHONE_FIELD, new TableInfo.Column(ShippingInfoWidget.PHONE_FIELD, "TEXT", true, 0));
                hashMap.put("email", new TableInfo.Column("email", "TEXT", true, 0));
                hashMap.put("state", new TableInfo.Column("state", "TEXT", true, 0));
                hashMap.put("city", new TableInfo.Column("city", "TEXT", true, 0));
                hashMap.put("street", new TableInfo.Column("street", "TEXT", true, 0));
                hashMap.put("zip", new TableInfo.Column("zip", "TEXT", true, 0));
                hashMap.put("photoProfileUrl", new TableInfo.Column("photoProfileUrl", "TEXT", false, 0));
                hashMap.put("photoIdUrl", new TableInfo.Column("photoIdUrl", "TEXT", false, 0));
                hashMap.put("photoProfile", new TableInfo.Column("photoProfile", "TEXT", false, 0));
                hashMap.put("photoId", new TableInfo.Column("photoId", "TEXT", false, 0));
                hashMap.put("verified", new TableInfo.Column("verified", "INTEGER", true, 0));
                hashMap.put("isAdmin", new TableInfo.Column("isAdmin", "INTEGER", true, 0));
                hashMap.put("isNotificationSubscriptionEnabled", new TableInfo.Column("isNotificationSubscriptionEnabled", "INTEGER", true, 0));
                TableInfo tableInfo = new TableInfo("User", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "User");
                if (tableInfo.equals(read)) {
                    HashMap hashMap2 = new HashMap(70);
                    hashMap2.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                    hashMap2.put(UploadWorker.KEY_VISITOR_ID, new TableInfo.Column(UploadWorker.KEY_VISITOR_ID, "TEXT", true, 0));
                    hashMap2.put("created", new TableInfo.Column("created", "TEXT", false, 0));
                    hashMap2.put("dob", new TableInfo.Column("dob", "TEXT", false, 0));
                    hashMap2.put("dob_formatted", new TableInfo.Column("dob_formatted", "TEXT", false, 0));
                    hashMap2.put(Attributes.FIRST_NAME, new TableInfo.Column(Attributes.FIRST_NAME, "TEXT", false, 0));
                    hashMap2.put("identifier", new TableInfo.Column("identifier", "TEXT", false, 0));
                    hashMap2.put("inmate_location", new TableInfo.Column("inmate_location", "TEXT", false, 0));
                    hashMap2.put(Attributes.LAST_NAME, new TableInfo.Column(Attributes.LAST_NAME, "TEXT", false, 0));
                    hashMap2.put(Attributes.FULL_NAME, new TableInfo.Column(Attributes.FULL_NAME, "TEXT", false, 0));
                    hashMap2.put("location", new TableInfo.Column("location", "TEXT", false, 0));
                    hashMap2.put("middle_name", new TableInfo.Column("middle_name", "TEXT", false, 0));
                    hashMap2.put("prison_id", new TableInfo.Column("prison_id", "TEXT", false, 0));
                    hashMap2.put("prison_name", new TableInfo.Column("prison_name", "TEXT", false, 0));
                    hashMap2.put("suffix", new TableInfo.Column("suffix", "TEXT", false, 0));
                    hashMap2.put("approved", new TableInfo.Column("approved", "TEXT", false, 0));
                    hashMap2.put("credit_balance", new TableInfo.Column("credit_balance", "TEXT", false, 0));
                    hashMap2.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", false, 0));
                    hashMap2.put("calls_disabled", new TableInfo.Column("calls_disabled", "TEXT", false, 0));
                    hashMap2.put("calls_disabled_until", new TableInfo.Column("calls_disabled_until", "TEXT", false, 0));
                    hashMap2.put("can_text_message", new TableInfo.Column("can_text_message", "INTEGER", true, 0));
                    hashMap2.put("can_video_message", new TableInfo.Column("can_video_message", "INTEGER", true, 0));
                    hashMap2.put("can_image_message", new TableInfo.Column("can_image_message", "INTEGER", true, 0));
                    hashMap2.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED, new TableInfo.Column(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED, "TEXT", false, 0));
                    hashMap2.put("deleted_warnings", new TableInfo.Column("deleted_warnings", "TEXT", false, 0));
                    hashMap2.put("description", new TableInfo.Column("description", "TEXT", true, 0));
                    hashMap2.put("error_reported", new TableInfo.Column("error_reported", "TEXT", true, 0));
                    hashMap2.put("fmt_name", new TableInfo.Column("fmt_name", "TEXT", true, 0));
                    hashMap2.put("inmate_general_funds_auto_recharge_enabled", new TableInfo.Column("inmate_general_funds_auto_recharge_enabled", "TEXT", true, 0));
                    hashMap2.put("internal_notes", new TableInfo.Column("internal_notes", "TEXT", false, 0));
                    hashMap2.put("invisible", new TableInfo.Column("invisible", "TEXT", false, 0));
                    hashMap2.put("is_fraud", new TableInfo.Column("is_fraud", "TEXT", true, 0));
                    hashMap2.put("is_fraud_set_by", new TableInfo.Column("is_fraud_set_by", "TEXT", false, 0));
                    hashMap2.put("notes", new TableInfo.Column("notes", "TEXT", false, 0));
                    String str = "\n Found:\n";
                    hashMap2.put("occupant_id", new TableInfo.Column("occupant_id", "TEXT", true, 0));
                    hashMap2.put("prison_max_text_message_length", new TableInfo.Column("prison_max_text_message_length", "TEXT", true, 0));
                    hashMap2.put("prison_max_video_message_length", new TableInfo.Column("prison_max_video_message_length", "TEXT", true, 0));
                    hashMap2.put("prison_price_per_text_message", new TableInfo.Column("prison_price_per_text_message", "TEXT", true, 0));
                    hashMap2.put("prison_price_per_video_message", new TableInfo.Column("prison_price_per_video_message", "TEXT", true, 0));
                    hashMap2.put("prison_price_per_image_message", new TableInfo.Column("prison_price_per_image_message", "TEXT", true, 0));
                    hashMap2.put("prison_price_per_gif_message", new TableInfo.Column("prison_price_per_gif_message", "TEXT", false, 0));
                    hashMap2.put("per_minute_charging_enabled", new TableInfo.Column("per_minute_charging_enabled", "TEXT", false, 0));
                    hashMap2.put(UploadWorker.KEY_PUB_ID, new TableInfo.Column(UploadWorker.KEY_PUB_ID, "TEXT", true, 0));
                    String str2 = "prison_id";
                    hashMap2.put("relationship", new TableInfo.Column("relationship", "TEXT", true, 0));
                    hashMap2.put("require_approval", new TableInfo.Column("require_approval", "TEXT", false, 0));
                    hashMap2.put("should_record", new TableInfo.Column("should_record", "TEXT", true, 0));
                    hashMap2.put(FetchDeviceInfoAction.TAGS_KEY, new TableInfo.Column(FetchDeviceInfoAction.TAGS_KEY, "TEXT", false, 0));
                    String str3 = UploadWorker.KEY_PUB_ID;
                    hashMap2.put("talk_to_me_funds_auto_recharge_enabled", new TableInfo.Column("talk_to_me_funds_auto_recharge_enabled", "TEXT", true, 0));
                    hashMap2.put("visitor_city", new TableInfo.Column("visitor_city", "TEXT", true, 0));
                    hashMap2.put("visitor_created", new TableInfo.Column("visitor_created", "TEXT", true, 0));
                    hashMap2.put("visitor_dob", new TableInfo.Column("visitor_dob", "TEXT", true, 0));
                    hashMap2.put("visitor_first_name", new TableInfo.Column("visitor_first_name", "TEXT", true, 0));
                    hashMap2.put("visitor_last_name", new TableInfo.Column("visitor_last_name", "TEXT", true, 0));
                    hashMap2.put("visitor_name", new TableInfo.Column("visitor_name", "TEXT", true, 0));
                    String str4 = FetchDeviceInfoAction.TAGS_KEY;
                    hashMap2.put("visitor_phone", new TableInfo.Column("visitor_phone", "TEXT", true, 0));
                    hashMap2.put("visitor_pin", new TableInfo.Column("visitor_pin", "TEXT", true, 0));
                    hashMap2.put("visitor_should_record", new TableInfo.Column("visitor_should_record", "TEXT", true, 0));
                    hashMap2.put("visitor_state", new TableInfo.Column("visitor_state", "TEXT", true, 0));
                    hashMap2.put("visitor_state_abbreviation", new TableInfo.Column("visitor_state_abbreviation", "TEXT", true, 0));
                    hashMap2.put("visitor_street1", new TableInfo.Column("visitor_street1", "TEXT", true, 0));
                    hashMap2.put("visitor_street2", new TableInfo.Column("visitor_street2", "TEXT", false, 0));
                    hashMap2.put("visitor_username", new TableInfo.Column("visitor_username", "TEXT", true, 0));
                    String str5 = "visitor_name";
                    hashMap2.put("visitor_zipcode", new TableInfo.Column("visitor_zipcode", "TEXT", true, 0));
                    hashMap2.put("voice_calls_disabled", new TableInfo.Column("voice_calls_disabled", "TEXT", true, 0));
                    hashMap2.put("voice_calls_disabled_until", new TableInfo.Column("voice_calls_disabled_until", "TEXT", false, 0));
                    hashMap2.put("zone", new TableInfo.Column("zone", "TEXT", false, 0));
                    String str6 = "zone";
                    hashMap2.put("prison_video_calls_disabled", new TableInfo.Column("prison_video_calls_disabled", "INTEGER", true, 0));
                    hashMap2.put("prison_voice_calls_disabled", new TableInfo.Column("prison_voice_calls_disabled", "INTEGER", true, 0));
                    hashMap2.put("prison_state", new TableInfo.Column("prison_state", "TEXT", false, 0));
                    hashMap2.put("prison_payment_gateway", new TableInfo.Column("prison_payment_gateway", "TEXT", false, 0));
                    TableInfo tableInfo2 = new TableInfo("inmates", hashMap2, new HashSet(0), new HashSet(0));
                    TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "inmates");
                    if (tableInfo2.equals(read2)) {
                        HashMap hashMap3 = new HashMap(4);
                        hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
                        hashMap3.put("type", new TableInfo.Column("type", "TEXT", true, 0));
                        String str7 = "internal_notes";
                        hashMap3.put("body", new TableInfo.Column("body", "TEXT", true, 0));
                        hashMap3.put("created", new TableInfo.Column("created", "INTEGER", true, 0));
                        String str8 = "prison_name";
                        TableInfo tableInfo3 = new TableInfo("notifications", hashMap3, new HashSet(0), new HashSet(0));
                        TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "notifications");
                        if (tableInfo3.equals(read3)) {
                            HashMap hashMap4 = new HashMap(16);
                            hashMap4.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                            hashMap4.put("pubId", new TableInfo.Column("pubId", "TEXT", true, 0));
                            hashMap4.put("created", new TableInfo.Column("created", "INTEGER", true, 0));
                            hashMap4.put("inmateId", new TableInfo.Column("inmateId", "TEXT", true, 0));
                            String str9 = "created";
                            hashMap4.put("visitorId", new TableInfo.Column("visitorId", "TEXT", true, 0));
                            hashMap4.put("body", new TableInfo.Column("body", "TEXT", true, 0));
                            hashMap4.put("sender", new TableInfo.Column("sender", "INTEGER", true, 0));
                            hashMap4.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
                            hashMap4.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0));
                            hashMap4.put("views", new TableInfo.Column("views", "TEXT", true, 0));
                            hashMap4.put("senderName", new TableInfo.Column("senderName", "TEXT", true, 0));
                            hashMap4.put("streamName", new TableInfo.Column("streamName", "TEXT", true, 0));
                            hashMap4.put("streamUrl", new TableInfo.Column("streamUrl", "TEXT", false, 0));
                            hashMap4.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0));
                            hashMap4.put("videoUrl", new TableInfo.Column("videoUrl", "TEXT", false, 0));
                            hashMap4.put("senderStatus", new TableInfo.Column("senderStatus", "TEXT", false, 0));
                            TableInfo tableInfo4 = new TableInfo("messages", hashMap4, new HashSet(0), new HashSet(0));
                            TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "messages");
                            if (tableInfo4.equals(read4)) {
                                HashMap hashMap5 = new HashMap(8);
                                hashMap5.put("slotId", new TableInfo.Column("slotId", "TEXT", true, 1));
                                hashMap5.put("inmateId", new TableInfo.Column("inmateId", "TEXT", true, 0));
                                hashMap5.put("inmate", new TableInfo.Column("inmate", "TEXT", true, 0));
                                hashMap5.put("facility", new TableInfo.Column("facility", "TEXT", true, 0));
                                hashMap5.put("station", new TableInfo.Column("station", "TEXT", true, 0));
                                hashMap5.put(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, new TableInfo.Column(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, "INTEGER", true, 0));
                                hashMap5.put("timezone", new TableInfo.Column("timezone", "TEXT", true, 0));
                                hashMap5.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", true, 0));
                                TableInfo tableInfo5 = new TableInfo("visits", hashMap5, new HashSet(0), new HashSet(0));
                                TableInfo read5 = TableInfo.read(supportSQLiteDatabase2, "visits");
                                if (tableInfo5.equals(read5)) {
                                    HashMap hashMap6 = new HashMap(43);
                                    hashMap6.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                    hashMap6.put("protocol", new TableInfo.Column("protocol", "TEXT", false, 0));
                                    hashMap6.put("disconnect_cause", new TableInfo.Column("disconnect_cause", "TEXT", false, 0));
                                    hashMap6.put("visitor_username", new TableInfo.Column("visitor_username", "TEXT", false, 0));
                                    hashMap6.put("notes", new TableInfo.Column("notes", "TEXT", false, 0));
                                    hashMap6.put("splicing_priority", new TableInfo.Column("splicing_priority", "TEXT", false, 0));
                                    hashMap6.put("timezone", new TableInfo.Column("timezone", "TEXT", false, 0));
                                    hashMap6.put("splicing_finished", new TableInfo.Column("splicing_finished", "TEXT", false, 0));
                                    hashMap6.put(UploadWorker.KEY_VISITOR_ID, new TableInfo.Column(UploadWorker.KEY_VISITOR_ID, "TEXT", false, 0));
                                    hashMap6.put("visitor_checkin", new TableInfo.Column("visitor_checkin", "TEXT", false, 0));
                                    hashMap6.put("beta_splicer", new TableInfo.Column("beta_splicer", "TEXT", false, 0));
                                    hashMap6.put("splicing_outcome", new TableInfo.Column("splicing_outcome", "TEXT", false, 0));
                                    hashMap6.put("visitor_location", new TableInfo.Column("visitor_location", "TEXT", false, 0));
                                    String str10 = str8;
                                    hashMap6.put(str10, new TableInfo.Column(str10, "TEXT", false, 0));
                                    hashMap6.put("splicing_hints", new TableInfo.Column("splicing_hints", "TEXT", false, 0));
                                    hashMap6.put("disconnected", new TableInfo.Column("disconnected", "INTEGER", false, 0));
                                    hashMap6.put("ui", new TableInfo.Column("ui", "TEXT", false, 0));
                                    String str11 = str7;
                                    hashMap6.put(str11, new TableInfo.Column(str11, "TEXT", false, 0));
                                    String str12 = str6;
                                    hashMap6.put(str12, new TableInfo.Column(str12, "TEXT", false, 0));
                                    hashMap6.put("station", new TableInfo.Column("station", "TEXT", false, 0));
                                    hashMap6.put("reviewed", new TableInfo.Column("reviewed", "TEXT", false, 0));
                                    hashMap6.put("etype", new TableInfo.Column("etype", "TEXT", false, 0));
                                    hashMap6.put("purged", new TableInfo.Column("purged", "TEXT", false, 0));
                                    hashMap6.put("splicing_started", new TableInfo.Column("splicing_started", "TEXT", false, 0));
                                    hashMap6.put(UploadWorker.KEY_INMATE_ID, new TableInfo.Column(UploadWorker.KEY_INMATE_ID, "TEXT", false, 0));
                                    hashMap6.put("extension_count", new TableInfo.Column("extension_count", "TEXT", false, 0));
                                    hashMap6.put("inmate_name", new TableInfo.Column("inmate_name", "TEXT", false, 0));
                                    hashMap6.put("recorded", new TableInfo.Column("recorded", "TEXT", false, 0));
                                    hashMap6.put("visitor_email", new TableInfo.Column("visitor_email", "TEXT", false, 0));
                                    hashMap6.put("visitor_station_id", new TableInfo.Column("visitor_station_id", "TEXT", false, 0));
                                    String str13 = str5;
                                    hashMap6.put(str13, new TableInfo.Column(str13, "TEXT", false, 0));
                                    String str14 = str4;
                                    hashMap6.put(str14, new TableInfo.Column(str14, "TEXT", false, 0));
                                    hashMap6.put("connected", new TableInfo.Column("connected", "INTEGER", false, 0));
                                    hashMap6.put("warning_message", new TableInfo.Column("warning_message", "TEXT", false, 0));
                                    String str15 = str3;
                                    hashMap6.put(str15, new TableInfo.Column(str15, "TEXT", true, 0));
                                    hashMap6.put("free_seconds", new TableInfo.Column("free_seconds", "TEXT", false, 0));
                                    hashMap6.put("invalidated", new TableInfo.Column("invalidated", "TEXT", false, 0));
                                    hashMap6.put("inmate_checkin", new TableInfo.Column("inmate_checkin", "TEXT", false, 0));
                                    hashMap6.put("disconnect_required", new TableInfo.Column("disconnect_required", "TEXT", false, 0));
                                    hashMap6.put("account", new TableInfo.Column("account", "TEXT", false, 0));
                                    hashMap6.put(Attributes.AGE, new TableInfo.Column(Attributes.AGE, "TEXT", false, 0));
                                    String str16 = str2;
                                    hashMap6.put(str16, new TableInfo.Column(str16, "TEXT", false, 0));
                                    hashMap6.put("inmate_station_id", new TableInfo.Column("inmate_station_id", "TEXT", false, 0));
                                    TableInfo tableInfo6 = new TableInfo("calls", hashMap6, new HashSet(0), new HashSet(0));
                                    TableInfo read6 = TableInfo.read(supportSQLiteDatabase2, "calls");
                                    if (tableInfo6.equals(read6)) {
                                        HashMap hashMap7 = new HashMap(9);
                                        hashMap7.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                                        hashMap7.put(UploadWorker.KEY_INMATE_ID, new TableInfo.Column(UploadWorker.KEY_INMATE_ID, "TEXT", false, 0));
                                        hashMap7.put("braintree_transaction_id", new TableInfo.Column("braintree_transaction_id", "TEXT", false, 0));
                                        hashMap7.put("stripe_transaction_id", new TableInfo.Column("stripe_transaction_id", "TEXT", false, 0));
                                        String str17 = str9;
                                        hashMap7.put(str17, new TableInfo.Column(str17, "INTEGER", true, 0));
                                        hashMap7.put("creator", new TableInfo.Column("creator", "TEXT", true, 0));
                                        hashMap7.put("notes", new TableInfo.Column("notes", "TEXT", true, 0));
                                        hashMap7.put("type", new TableInfo.Column("type", "TEXT", true, 0));
                                        hashMap7.put(CommonProperties.VALUE, new TableInfo.Column(CommonProperties.VALUE, "TEXT", true, 0));
                                        TableInfo tableInfo7 = new TableInfo("credits", hashMap7, new HashSet(0), new HashSet(0));
                                        TableInfo read7 = TableInfo.read(supportSQLiteDatabase2, "credits");
                                        if (tableInfo7.equals(read7)) {
                                            HashMap hashMap8 = new HashMap(9);
                                            hashMap8.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
                                            hashMap8.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0));
                                            hashMap8.put("first_call_done", new TableInfo.Column("first_call_done", "INTEGER", true, 0));
                                            hashMap8.put("call_day_timestamp", new TableInfo.Column("call_day_timestamp", "INTEGER", false, 0));
                                            hashMap8.put("call_day_count", new TableInfo.Column("call_day_count", "INTEGER", true, 0));
                                            hashMap8.put("call_week_timestamp", new TableInfo.Column("call_week_timestamp", "INTEGER", false, 0));
                                            hashMap8.put("call_week_count", new TableInfo.Column("call_week_count", "INTEGER", true, 0));
                                            hashMap8.put("no_money_reported", new TableInfo.Column("no_money_reported", "INTEGER", true, 0));
                                            hashMap8.put("less_two_funds_reported", new TableInfo.Column("less_two_funds_reported", "INTEGER", true, 0));
                                            TableInfo tableInfo8 = new TableInfo("UserAnalytics", hashMap8, new HashSet(0), new HashSet(0));
                                            TableInfo read8 = TableInfo.read(supportSQLiteDatabase2, "UserAnalytics");
                                            if (!tableInfo8.equals(read8)) {
                                                throw new IllegalStateException("Migration didn't properly handle UserAnalytics(com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics).\n Expected:\n" + tableInfo8 + str + read8);
                                            }
                                            return;
                                        }
                                        throw new IllegalStateException("Migration didn't properly handle credits(com.forasoft.homewavvisitor.model.data.payment.Credit).\n Expected:\n" + tableInfo7 + str + read7);
                                    }
                                    throw new IllegalStateException("Migration didn't properly handle calls(com.forasoft.homewavvisitor.model.data.CallEntity).\n Expected:\n" + tableInfo6 + str + read6);
                                }
                                throw new IllegalStateException("Migration didn't properly handle visits(com.forasoft.homewavvisitor.model.data.account.ScheduledVisit).\n Expected:\n" + tableInfo5 + str + read5);
                            }
                            throw new IllegalStateException("Migration didn't properly handle messages(com.forasoft.homewavvisitor.model.data.Message).\n Expected:\n" + tableInfo4 + str + read4);
                        }
                        throw new IllegalStateException("Migration didn't properly handle notifications(com.forasoft.homewavvisitor.model.data.Notification).\n Expected:\n" + tableInfo3 + str + read3);
                    }
                    throw new IllegalStateException("Migration didn't properly handle inmates(com.forasoft.homewavvisitor.model.data.Inmate).\n Expected:\n" + tableInfo2 + str + read2);
                }
                throw new IllegalStateException("Migration didn't properly handle User(com.forasoft.homewavvisitor.model.data.auth.User).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "2619f7ca37628ed143e66969f12e013c", "17a2798798443e87f188509edb03028b")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "User", "inmates", "notifications", "messages", "visits", "calls", "credits", "UserAnalytics");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `User`");
            writableDatabase.execSQL("DELETE FROM `inmates`");
            writableDatabase.execSQL("DELETE FROM `notifications`");
            writableDatabase.execSQL("DELETE FROM `messages`");
            writableDatabase.execSQL("DELETE FROM `visits`");
            writableDatabase.execSQL("DELETE FROM `calls`");
            writableDatabase.execSQL("DELETE FROM `credits`");
            writableDatabase.execSQL("DELETE FROM `UserAnalytics`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    public UserDao userDao() {
        UserDao userDao;
        if (this._userDao != null) {
            return this._userDao;
        }
        synchronized (this) {
            if (this._userDao == null) {
                this._userDao = new UserDao_Impl(this);
            }
            userDao = this._userDao;
        }
        return userDao;
    }

    public NotificationDao notificationDao() {
        NotificationDao notificationDao;
        if (this._notificationDao != null) {
            return this._notificationDao;
        }
        synchronized (this) {
            if (this._notificationDao == null) {
                this._notificationDao = new NotificationDao_Impl(this);
            }
            notificationDao = this._notificationDao;
        }
        return notificationDao;
    }

    public MessageDao messageDao() {
        MessageDao messageDao;
        if (this._messageDao != null) {
            return this._messageDao;
        }
        synchronized (this) {
            if (this._messageDao == null) {
                this._messageDao = new MessageDao_Impl(this);
            }
            messageDao = this._messageDao;
        }
        return messageDao;
    }

    public InmateDao inmateDao() {
        InmateDao inmateDao;
        if (this._inmateDao != null) {
            return this._inmateDao;
        }
        synchronized (this) {
            if (this._inmateDao == null) {
                this._inmateDao = new InmateDao_Impl(this);
            }
            inmateDao = this._inmateDao;
        }
        return inmateDao;
    }

    public VisitDao visitDao() {
        VisitDao visitDao;
        if (this._visitDao != null) {
            return this._visitDao;
        }
        synchronized (this) {
            if (this._visitDao == null) {
                this._visitDao = new VisitDao_Impl(this);
            }
            visitDao = this._visitDao;
        }
        return visitDao;
    }

    public CallDao callDao() {
        CallDao callDao;
        if (this._callDao != null) {
            return this._callDao;
        }
        synchronized (this) {
            if (this._callDao == null) {
                this._callDao = new CallDao_Impl(this);
            }
            callDao = this._callDao;
        }
        return callDao;
    }

    public CreditDao creditDao() {
        CreditDao creditDao;
        if (this._creditDao != null) {
            return this._creditDao;
        }
        synchronized (this) {
            if (this._creditDao == null) {
                this._creditDao = new CreditDao_Impl(this);
            }
            creditDao = this._creditDao;
        }
        return creditDao;
    }

    public UserAnalyticsDao userAnalyticsDao() {
        UserAnalyticsDao userAnalyticsDao;
        if (this._userAnalyticsDao != null) {
            return this._userAnalyticsDao;
        }
        synchronized (this) {
            if (this._userAnalyticsDao == null) {
                this._userAnalyticsDao = new UserAnalyticsDao_Impl(this);
            }
            userAnalyticsDao = this._userAnalyticsDao;
        }
        return userAnalyticsDao;
    }
}
