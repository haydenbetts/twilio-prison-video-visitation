package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.actions.FetchDeviceInfoAction;
import com.urbanairship.util.Attributes;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class InmateDao_Impl implements InmateDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfInmate;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteInmate;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfInmate;

    public InmateDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfInmate = new EntityInsertionAdapter<Inmate>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `inmates`(`id`,`visitor_id`,`created`,`dob`,`dob_formatted`,`first_name`,`identifier`,`inmate_location`,`last_name`,`full_name`,`location`,`middle_name`,`prison_id`,`prison_name`,`suffix`,`approved`,`credit_balance`,`status`,`calls_disabled`,`calls_disabled_until`,`can_text_message`,`can_video_message`,`can_image_message`,`deleted`,`deleted_warnings`,`description`,`error_reported`,`fmt_name`,`inmate_general_funds_auto_recharge_enabled`,`internal_notes`,`invisible`,`is_fraud`,`is_fraud_set_by`,`notes`,`occupant_id`,`prison_max_text_message_length`,`prison_max_video_message_length`,`prison_price_per_text_message`,`prison_price_per_video_message`,`prison_price_per_image_message`,`prison_price_per_gif_message`,`per_minute_charging_enabled`,`pubid`,`relationship`,`require_approval`,`should_record`,`tags`,`talk_to_me_funds_auto_recharge_enabled`,`visitor_city`,`visitor_created`,`visitor_dob`,`visitor_first_name`,`visitor_last_name`,`visitor_name`,`visitor_phone`,`visitor_pin`,`visitor_should_record`,`visitor_state`,`visitor_state_abbreviation`,`visitor_street1`,`visitor_street2`,`visitor_username`,`visitor_zipcode`,`voice_calls_disabled`,`voice_calls_disabled_until`,`zone`,`prison_video_calls_disabled`,`prison_voice_calls_disabled`,`prison_state`,`prison_payment_gateway`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Inmate inmate) {
                if (inmate.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, inmate.getId());
                }
                if (inmate.getVisitor_id() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, inmate.getVisitor_id());
                }
                if (inmate.getCreated() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, inmate.getCreated());
                }
                if (inmate.getDob() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, inmate.getDob());
                }
                if (inmate.getDob_formatted() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, inmate.getDob_formatted());
                }
                if (inmate.getFirst_name() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, inmate.getFirst_name());
                }
                if (inmate.getIdentifier() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, inmate.getIdentifier());
                }
                if (inmate.getInmate_location() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, inmate.getInmate_location());
                }
                if (inmate.getLast_name() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, inmate.getLast_name());
                }
                if (inmate.getFull_name() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, inmate.getFull_name());
                }
                if (inmate.getLocation() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, inmate.getLocation());
                }
                if (inmate.getMiddle_name() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, inmate.getMiddle_name());
                }
                if (inmate.getPrison_id() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, inmate.getPrison_id());
                }
                if (inmate.getPrison_name() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, inmate.getPrison_name());
                }
                if (inmate.getSuffix() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, inmate.getSuffix());
                }
                if (inmate.getApproved() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, inmate.getApproved());
                }
                if (inmate.getCredit_balance() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, inmate.getCredit_balance());
                }
                if (inmate.getStatus() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, inmate.getStatus());
                }
                if (inmate.getCalls_disabled() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, inmate.getCalls_disabled());
                }
                if (inmate.getCalls_disabled_until() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, inmate.getCalls_disabled_until());
                }
                supportSQLiteStatement.bindLong(21, inmate.getCan_text_message() ? 1 : 0);
                supportSQLiteStatement.bindLong(22, inmate.getCan_video_message() ? 1 : 0);
                supportSQLiteStatement.bindLong(23, inmate.getCan_image_message() ? 1 : 0);
                if (inmate.getDeleted() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, inmate.getDeleted());
                }
                if (inmate.getDeleted_warnings() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, inmate.getDeleted_warnings());
                }
                if (inmate.getDescription() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, inmate.getDescription());
                }
                if (inmate.getError_reported() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, inmate.getError_reported());
                }
                if (inmate.getFmt_name() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, inmate.getFmt_name());
                }
                if (inmate.getInmate_general_funds_auto_recharge_enabled() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, inmate.getInmate_general_funds_auto_recharge_enabled());
                }
                if (inmate.getInternal_notes() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, inmate.getInternal_notes());
                }
                if (inmate.getInvisible() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, inmate.getInvisible());
                }
                if (inmate.is_fraud() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, inmate.is_fraud());
                }
                if (inmate.is_fraud_set_by() == null) {
                    supportSQLiteStatement.bindNull(33);
                } else {
                    supportSQLiteStatement.bindString(33, inmate.is_fraud_set_by());
                }
                if (inmate.getNotes() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, inmate.getNotes());
                }
                if (inmate.getOccupant_id() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, inmate.getOccupant_id());
                }
                if (inmate.getPrison_max_text_message_length() == null) {
                    supportSQLiteStatement.bindNull(36);
                } else {
                    supportSQLiteStatement.bindString(36, inmate.getPrison_max_text_message_length());
                }
                if (inmate.getPrison_max_video_message_length() == null) {
                    supportSQLiteStatement.bindNull(37);
                } else {
                    supportSQLiteStatement.bindString(37, inmate.getPrison_max_video_message_length());
                }
                if (inmate.getPrison_price_per_text_message() == null) {
                    supportSQLiteStatement.bindNull(38);
                } else {
                    supportSQLiteStatement.bindString(38, inmate.getPrison_price_per_text_message());
                }
                if (inmate.getPrison_price_per_video_message() == null) {
                    supportSQLiteStatement.bindNull(39);
                } else {
                    supportSQLiteStatement.bindString(39, inmate.getPrison_price_per_video_message());
                }
                if (inmate.getPrison_price_per_image_message() == null) {
                    supportSQLiteStatement.bindNull(40);
                } else {
                    supportSQLiteStatement.bindString(40, inmate.getPrison_price_per_image_message());
                }
                if (inmate.getPrison_price_per_gif_message() == null) {
                    supportSQLiteStatement.bindNull(41);
                } else {
                    supportSQLiteStatement.bindString(41, inmate.getPrison_price_per_gif_message());
                }
                if (inmate.getPer_minute_charging_enabled() == null) {
                    supportSQLiteStatement.bindNull(42);
                } else {
                    supportSQLiteStatement.bindString(42, inmate.getPer_minute_charging_enabled());
                }
                if (inmate.getPubid() == null) {
                    supportSQLiteStatement.bindNull(43);
                } else {
                    supportSQLiteStatement.bindString(43, inmate.getPubid());
                }
                if (inmate.getRelationship() == null) {
                    supportSQLiteStatement.bindNull(44);
                } else {
                    supportSQLiteStatement.bindString(44, inmate.getRelationship());
                }
                if (inmate.getRequire_approval() == null) {
                    supportSQLiteStatement.bindNull(45);
                } else {
                    supportSQLiteStatement.bindString(45, inmate.getRequire_approval());
                }
                if (inmate.getShould_record() == null) {
                    supportSQLiteStatement.bindNull(46);
                } else {
                    supportSQLiteStatement.bindString(46, inmate.getShould_record());
                }
                if (inmate.getTags() == null) {
                    supportSQLiteStatement.bindNull(47);
                } else {
                    supportSQLiteStatement.bindString(47, inmate.getTags());
                }
                if (inmate.getTalk_to_me_funds_auto_recharge_enabled() == null) {
                    supportSQLiteStatement.bindNull(48);
                } else {
                    supportSQLiteStatement.bindString(48, inmate.getTalk_to_me_funds_auto_recharge_enabled());
                }
                if (inmate.getVisitor_city() == null) {
                    supportSQLiteStatement.bindNull(49);
                } else {
                    supportSQLiteStatement.bindString(49, inmate.getVisitor_city());
                }
                if (inmate.getVisitor_created() == null) {
                    supportSQLiteStatement.bindNull(50);
                } else {
                    supportSQLiteStatement.bindString(50, inmate.getVisitor_created());
                }
                if (inmate.getVisitor_dob() == null) {
                    supportSQLiteStatement.bindNull(51);
                } else {
                    supportSQLiteStatement.bindString(51, inmate.getVisitor_dob());
                }
                if (inmate.getVisitor_first_name() == null) {
                    supportSQLiteStatement.bindNull(52);
                } else {
                    supportSQLiteStatement.bindString(52, inmate.getVisitor_first_name());
                }
                if (inmate.getVisitor_last_name() == null) {
                    supportSQLiteStatement.bindNull(53);
                } else {
                    supportSQLiteStatement.bindString(53, inmate.getVisitor_last_name());
                }
                if (inmate.getVisitor_name() == null) {
                    supportSQLiteStatement.bindNull(54);
                } else {
                    supportSQLiteStatement.bindString(54, inmate.getVisitor_name());
                }
                if (inmate.getVisitor_phone() == null) {
                    supportSQLiteStatement.bindNull(55);
                } else {
                    supportSQLiteStatement.bindString(55, inmate.getVisitor_phone());
                }
                if (inmate.getVisitor_pin() == null) {
                    supportSQLiteStatement.bindNull(56);
                } else {
                    supportSQLiteStatement.bindString(56, inmate.getVisitor_pin());
                }
                if (inmate.getVisitor_should_record() == null) {
                    supportSQLiteStatement.bindNull(57);
                } else {
                    supportSQLiteStatement.bindString(57, inmate.getVisitor_should_record());
                }
                if (inmate.getVisitor_state() == null) {
                    supportSQLiteStatement.bindNull(58);
                } else {
                    supportSQLiteStatement.bindString(58, inmate.getVisitor_state());
                }
                if (inmate.getVisitor_state_abbreviation() == null) {
                    supportSQLiteStatement.bindNull(59);
                } else {
                    supportSQLiteStatement.bindString(59, inmate.getVisitor_state_abbreviation());
                }
                if (inmate.getVisitor_street1() == null) {
                    supportSQLiteStatement.bindNull(60);
                } else {
                    supportSQLiteStatement.bindString(60, inmate.getVisitor_street1());
                }
                if (inmate.getVisitor_street2() == null) {
                    supportSQLiteStatement.bindNull(61);
                } else {
                    supportSQLiteStatement.bindString(61, inmate.getVisitor_street2());
                }
                if (inmate.getVisitor_username() == null) {
                    supportSQLiteStatement.bindNull(62);
                } else {
                    supportSQLiteStatement.bindString(62, inmate.getVisitor_username());
                }
                if (inmate.getVisitor_zipcode() == null) {
                    supportSQLiteStatement.bindNull(63);
                } else {
                    supportSQLiteStatement.bindString(63, inmate.getVisitor_zipcode());
                }
                if (inmate.getVoice_calls_disabled() == null) {
                    supportSQLiteStatement.bindNull(64);
                } else {
                    supportSQLiteStatement.bindString(64, inmate.getVoice_calls_disabled());
                }
                if (inmate.getVoice_calls_disabled_until() == null) {
                    supportSQLiteStatement.bindNull(65);
                } else {
                    supportSQLiteStatement.bindString(65, inmate.getVoice_calls_disabled_until());
                }
                if (inmate.getZone() == null) {
                    supportSQLiteStatement.bindNull(66);
                } else {
                    supportSQLiteStatement.bindString(66, inmate.getZone());
                }
                supportSQLiteStatement.bindLong(67, inmate.getPrison_video_calls_disabled() ? 1 : 0);
                supportSQLiteStatement.bindLong(68, inmate.getPrison_voice_calls_disabled() ? 1 : 0);
                if (inmate.getPrison_state() == null) {
                    supportSQLiteStatement.bindNull(69);
                } else {
                    supportSQLiteStatement.bindString(69, inmate.getPrison_state());
                }
                if (inmate.getPrison_payment_gateway() == null) {
                    supportSQLiteStatement.bindNull(70);
                } else {
                    supportSQLiteStatement.bindString(70, inmate.getPrison_payment_gateway());
                }
            }
        };
        this.__updateAdapterOfInmate = new EntityDeletionOrUpdateAdapter<Inmate>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `inmates` SET `id` = ?,`visitor_id` = ?,`created` = ?,`dob` = ?,`dob_formatted` = ?,`first_name` = ?,`identifier` = ?,`inmate_location` = ?,`last_name` = ?,`full_name` = ?,`location` = ?,`middle_name` = ?,`prison_id` = ?,`prison_name` = ?,`suffix` = ?,`approved` = ?,`credit_balance` = ?,`status` = ?,`calls_disabled` = ?,`calls_disabled_until` = ?,`can_text_message` = ?,`can_video_message` = ?,`can_image_message` = ?,`deleted` = ?,`deleted_warnings` = ?,`description` = ?,`error_reported` = ?,`fmt_name` = ?,`inmate_general_funds_auto_recharge_enabled` = ?,`internal_notes` = ?,`invisible` = ?,`is_fraud` = ?,`is_fraud_set_by` = ?,`notes` = ?,`occupant_id` = ?,`prison_max_text_message_length` = ?,`prison_max_video_message_length` = ?,`prison_price_per_text_message` = ?,`prison_price_per_video_message` = ?,`prison_price_per_image_message` = ?,`prison_price_per_gif_message` = ?,`per_minute_charging_enabled` = ?,`pubid` = ?,`relationship` = ?,`require_approval` = ?,`should_record` = ?,`tags` = ?,`talk_to_me_funds_auto_recharge_enabled` = ?,`visitor_city` = ?,`visitor_created` = ?,`visitor_dob` = ?,`visitor_first_name` = ?,`visitor_last_name` = ?,`visitor_name` = ?,`visitor_phone` = ?,`visitor_pin` = ?,`visitor_should_record` = ?,`visitor_state` = ?,`visitor_state_abbreviation` = ?,`visitor_street1` = ?,`visitor_street2` = ?,`visitor_username` = ?,`visitor_zipcode` = ?,`voice_calls_disabled` = ?,`voice_calls_disabled_until` = ?,`zone` = ?,`prison_video_calls_disabled` = ?,`prison_voice_calls_disabled` = ?,`prison_state` = ?,`prison_payment_gateway` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Inmate inmate) {
                if (inmate.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, inmate.getId());
                }
                if (inmate.getVisitor_id() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, inmate.getVisitor_id());
                }
                if (inmate.getCreated() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, inmate.getCreated());
                }
                if (inmate.getDob() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, inmate.getDob());
                }
                if (inmate.getDob_formatted() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, inmate.getDob_formatted());
                }
                if (inmate.getFirst_name() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, inmate.getFirst_name());
                }
                if (inmate.getIdentifier() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, inmate.getIdentifier());
                }
                if (inmate.getInmate_location() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, inmate.getInmate_location());
                }
                if (inmate.getLast_name() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, inmate.getLast_name());
                }
                if (inmate.getFull_name() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, inmate.getFull_name());
                }
                if (inmate.getLocation() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, inmate.getLocation());
                }
                if (inmate.getMiddle_name() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, inmate.getMiddle_name());
                }
                if (inmate.getPrison_id() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, inmate.getPrison_id());
                }
                if (inmate.getPrison_name() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, inmate.getPrison_name());
                }
                if (inmate.getSuffix() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, inmate.getSuffix());
                }
                if (inmate.getApproved() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, inmate.getApproved());
                }
                if (inmate.getCredit_balance() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, inmate.getCredit_balance());
                }
                if (inmate.getStatus() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, inmate.getStatus());
                }
                if (inmate.getCalls_disabled() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, inmate.getCalls_disabled());
                }
                if (inmate.getCalls_disabled_until() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, inmate.getCalls_disabled_until());
                }
                supportSQLiteStatement.bindLong(21, inmate.getCan_text_message() ? 1 : 0);
                supportSQLiteStatement.bindLong(22, inmate.getCan_video_message() ? 1 : 0);
                supportSQLiteStatement.bindLong(23, inmate.getCan_image_message() ? 1 : 0);
                if (inmate.getDeleted() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, inmate.getDeleted());
                }
                if (inmate.getDeleted_warnings() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, inmate.getDeleted_warnings());
                }
                if (inmate.getDescription() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, inmate.getDescription());
                }
                if (inmate.getError_reported() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, inmate.getError_reported());
                }
                if (inmate.getFmt_name() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, inmate.getFmt_name());
                }
                if (inmate.getInmate_general_funds_auto_recharge_enabled() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, inmate.getInmate_general_funds_auto_recharge_enabled());
                }
                if (inmate.getInternal_notes() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, inmate.getInternal_notes());
                }
                if (inmate.getInvisible() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, inmate.getInvisible());
                }
                if (inmate.is_fraud() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, inmate.is_fraud());
                }
                if (inmate.is_fraud_set_by() == null) {
                    supportSQLiteStatement.bindNull(33);
                } else {
                    supportSQLiteStatement.bindString(33, inmate.is_fraud_set_by());
                }
                if (inmate.getNotes() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, inmate.getNotes());
                }
                if (inmate.getOccupant_id() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, inmate.getOccupant_id());
                }
                if (inmate.getPrison_max_text_message_length() == null) {
                    supportSQLiteStatement.bindNull(36);
                } else {
                    supportSQLiteStatement.bindString(36, inmate.getPrison_max_text_message_length());
                }
                if (inmate.getPrison_max_video_message_length() == null) {
                    supportSQLiteStatement.bindNull(37);
                } else {
                    supportSQLiteStatement.bindString(37, inmate.getPrison_max_video_message_length());
                }
                if (inmate.getPrison_price_per_text_message() == null) {
                    supportSQLiteStatement.bindNull(38);
                } else {
                    supportSQLiteStatement.bindString(38, inmate.getPrison_price_per_text_message());
                }
                if (inmate.getPrison_price_per_video_message() == null) {
                    supportSQLiteStatement.bindNull(39);
                } else {
                    supportSQLiteStatement.bindString(39, inmate.getPrison_price_per_video_message());
                }
                if (inmate.getPrison_price_per_image_message() == null) {
                    supportSQLiteStatement.bindNull(40);
                } else {
                    supportSQLiteStatement.bindString(40, inmate.getPrison_price_per_image_message());
                }
                if (inmate.getPrison_price_per_gif_message() == null) {
                    supportSQLiteStatement.bindNull(41);
                } else {
                    supportSQLiteStatement.bindString(41, inmate.getPrison_price_per_gif_message());
                }
                if (inmate.getPer_minute_charging_enabled() == null) {
                    supportSQLiteStatement.bindNull(42);
                } else {
                    supportSQLiteStatement.bindString(42, inmate.getPer_minute_charging_enabled());
                }
                if (inmate.getPubid() == null) {
                    supportSQLiteStatement.bindNull(43);
                } else {
                    supportSQLiteStatement.bindString(43, inmate.getPubid());
                }
                if (inmate.getRelationship() == null) {
                    supportSQLiteStatement.bindNull(44);
                } else {
                    supportSQLiteStatement.bindString(44, inmate.getRelationship());
                }
                if (inmate.getRequire_approval() == null) {
                    supportSQLiteStatement.bindNull(45);
                } else {
                    supportSQLiteStatement.bindString(45, inmate.getRequire_approval());
                }
                if (inmate.getShould_record() == null) {
                    supportSQLiteStatement.bindNull(46);
                } else {
                    supportSQLiteStatement.bindString(46, inmate.getShould_record());
                }
                if (inmate.getTags() == null) {
                    supportSQLiteStatement.bindNull(47);
                } else {
                    supportSQLiteStatement.bindString(47, inmate.getTags());
                }
                if (inmate.getTalk_to_me_funds_auto_recharge_enabled() == null) {
                    supportSQLiteStatement.bindNull(48);
                } else {
                    supportSQLiteStatement.bindString(48, inmate.getTalk_to_me_funds_auto_recharge_enabled());
                }
                if (inmate.getVisitor_city() == null) {
                    supportSQLiteStatement.bindNull(49);
                } else {
                    supportSQLiteStatement.bindString(49, inmate.getVisitor_city());
                }
                if (inmate.getVisitor_created() == null) {
                    supportSQLiteStatement.bindNull(50);
                } else {
                    supportSQLiteStatement.bindString(50, inmate.getVisitor_created());
                }
                if (inmate.getVisitor_dob() == null) {
                    supportSQLiteStatement.bindNull(51);
                } else {
                    supportSQLiteStatement.bindString(51, inmate.getVisitor_dob());
                }
                if (inmate.getVisitor_first_name() == null) {
                    supportSQLiteStatement.bindNull(52);
                } else {
                    supportSQLiteStatement.bindString(52, inmate.getVisitor_first_name());
                }
                if (inmate.getVisitor_last_name() == null) {
                    supportSQLiteStatement.bindNull(53);
                } else {
                    supportSQLiteStatement.bindString(53, inmate.getVisitor_last_name());
                }
                if (inmate.getVisitor_name() == null) {
                    supportSQLiteStatement.bindNull(54);
                } else {
                    supportSQLiteStatement.bindString(54, inmate.getVisitor_name());
                }
                if (inmate.getVisitor_phone() == null) {
                    supportSQLiteStatement.bindNull(55);
                } else {
                    supportSQLiteStatement.bindString(55, inmate.getVisitor_phone());
                }
                if (inmate.getVisitor_pin() == null) {
                    supportSQLiteStatement.bindNull(56);
                } else {
                    supportSQLiteStatement.bindString(56, inmate.getVisitor_pin());
                }
                if (inmate.getVisitor_should_record() == null) {
                    supportSQLiteStatement.bindNull(57);
                } else {
                    supportSQLiteStatement.bindString(57, inmate.getVisitor_should_record());
                }
                if (inmate.getVisitor_state() == null) {
                    supportSQLiteStatement.bindNull(58);
                } else {
                    supportSQLiteStatement.bindString(58, inmate.getVisitor_state());
                }
                if (inmate.getVisitor_state_abbreviation() == null) {
                    supportSQLiteStatement.bindNull(59);
                } else {
                    supportSQLiteStatement.bindString(59, inmate.getVisitor_state_abbreviation());
                }
                if (inmate.getVisitor_street1() == null) {
                    supportSQLiteStatement.bindNull(60);
                } else {
                    supportSQLiteStatement.bindString(60, inmate.getVisitor_street1());
                }
                if (inmate.getVisitor_street2() == null) {
                    supportSQLiteStatement.bindNull(61);
                } else {
                    supportSQLiteStatement.bindString(61, inmate.getVisitor_street2());
                }
                if (inmate.getVisitor_username() == null) {
                    supportSQLiteStatement.bindNull(62);
                } else {
                    supportSQLiteStatement.bindString(62, inmate.getVisitor_username());
                }
                if (inmate.getVisitor_zipcode() == null) {
                    supportSQLiteStatement.bindNull(63);
                } else {
                    supportSQLiteStatement.bindString(63, inmate.getVisitor_zipcode());
                }
                if (inmate.getVoice_calls_disabled() == null) {
                    supportSQLiteStatement.bindNull(64);
                } else {
                    supportSQLiteStatement.bindString(64, inmate.getVoice_calls_disabled());
                }
                if (inmate.getVoice_calls_disabled_until() == null) {
                    supportSQLiteStatement.bindNull(65);
                } else {
                    supportSQLiteStatement.bindString(65, inmate.getVoice_calls_disabled_until());
                }
                if (inmate.getZone() == null) {
                    supportSQLiteStatement.bindNull(66);
                } else {
                    supportSQLiteStatement.bindString(66, inmate.getZone());
                }
                supportSQLiteStatement.bindLong(67, inmate.getPrison_video_calls_disabled() ? 1 : 0);
                supportSQLiteStatement.bindLong(68, inmate.getPrison_voice_calls_disabled() ? 1 : 0);
                if (inmate.getPrison_state() == null) {
                    supportSQLiteStatement.bindNull(69);
                } else {
                    supportSQLiteStatement.bindString(69, inmate.getPrison_state());
                }
                if (inmate.getPrison_payment_gateway() == null) {
                    supportSQLiteStatement.bindNull(70);
                } else {
                    supportSQLiteStatement.bindString(70, inmate.getPrison_payment_gateway());
                }
                if (inmate.getId() == null) {
                    supportSQLiteStatement.bindNull(71);
                } else {
                    supportSQLiteStatement.bindString(71, inmate.getId());
                }
            }
        };
        this.__preparedStmtOfDeleteInmate = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM inmates WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM inmates";
            }
        };
    }

    public void saveInmates(List<Inmate> list) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfInmate.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void saveInmate(Inmate inmate) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfInmate.insert(inmate);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void updateInmate(Inmate inmate) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfInmate.handle(inmate);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteInmate(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteInmate.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfDeleteInmate.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfDeleteInmate.release(acquire);
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

    public Flowable<List<Inmate>> getPendingInmate(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM inmates WHERE visitor_id = ? AND (approved = 'pending' OR is_fraud = '1') LIMIT 2", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"inmates"}, new Callable<List<Inmate>>() {
            public List<Inmate> call() throws Exception {
                boolean z;
                int i;
                boolean z2;
                int i2;
                Cursor query = InmateDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("dob");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("dob_formatted");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(Attributes.FIRST_NAME);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("identifier");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("inmate_location");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(Attributes.LAST_NAME);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow(Attributes.FULL_NAME);
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("location");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("middle_name");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("prison_id");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("suffix");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("approved");
                    int columnIndexOrThrow17 = query.getColumnIndexOrThrow("credit_balance");
                    int columnIndexOrThrow18 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow19 = query.getColumnIndexOrThrow("calls_disabled");
                    int columnIndexOrThrow20 = query.getColumnIndexOrThrow("calls_disabled_until");
                    int columnIndexOrThrow21 = query.getColumnIndexOrThrow("can_text_message");
                    int columnIndexOrThrow22 = query.getColumnIndexOrThrow("can_video_message");
                    int columnIndexOrThrow23 = query.getColumnIndexOrThrow("can_image_message");
                    int columnIndexOrThrow24 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED);
                    int columnIndexOrThrow25 = query.getColumnIndexOrThrow("deleted_warnings");
                    int columnIndexOrThrow26 = query.getColumnIndexOrThrow("description");
                    int columnIndexOrThrow27 = query.getColumnIndexOrThrow("error_reported");
                    int columnIndexOrThrow28 = query.getColumnIndexOrThrow("fmt_name");
                    int columnIndexOrThrow29 = query.getColumnIndexOrThrow("inmate_general_funds_auto_recharge_enabled");
                    int columnIndexOrThrow30 = query.getColumnIndexOrThrow("internal_notes");
                    int columnIndexOrThrow31 = query.getColumnIndexOrThrow("invisible");
                    int columnIndexOrThrow32 = query.getColumnIndexOrThrow("is_fraud");
                    int columnIndexOrThrow33 = query.getColumnIndexOrThrow("is_fraud_set_by");
                    int columnIndexOrThrow34 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow35 = query.getColumnIndexOrThrow("occupant_id");
                    int columnIndexOrThrow36 = query.getColumnIndexOrThrow("prison_max_text_message_length");
                    int columnIndexOrThrow37 = query.getColumnIndexOrThrow("prison_max_video_message_length");
                    int columnIndexOrThrow38 = query.getColumnIndexOrThrow("prison_price_per_text_message");
                    int columnIndexOrThrow39 = query.getColumnIndexOrThrow("prison_price_per_video_message");
                    int columnIndexOrThrow40 = query.getColumnIndexOrThrow("prison_price_per_image_message");
                    int columnIndexOrThrow41 = query.getColumnIndexOrThrow("prison_price_per_gif_message");
                    int columnIndexOrThrow42 = query.getColumnIndexOrThrow("per_minute_charging_enabled");
                    int columnIndexOrThrow43 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                    int columnIndexOrThrow44 = query.getColumnIndexOrThrow("relationship");
                    int columnIndexOrThrow45 = query.getColumnIndexOrThrow("require_approval");
                    int columnIndexOrThrow46 = query.getColumnIndexOrThrow("should_record");
                    int columnIndexOrThrow47 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                    int columnIndexOrThrow48 = query.getColumnIndexOrThrow("talk_to_me_funds_auto_recharge_enabled");
                    int columnIndexOrThrow49 = query.getColumnIndexOrThrow("visitor_city");
                    int columnIndexOrThrow50 = query.getColumnIndexOrThrow("visitor_created");
                    int columnIndexOrThrow51 = query.getColumnIndexOrThrow("visitor_dob");
                    int columnIndexOrThrow52 = query.getColumnIndexOrThrow("visitor_first_name");
                    int columnIndexOrThrow53 = query.getColumnIndexOrThrow("visitor_last_name");
                    int columnIndexOrThrow54 = query.getColumnIndexOrThrow("visitor_name");
                    int columnIndexOrThrow55 = query.getColumnIndexOrThrow("visitor_phone");
                    int columnIndexOrThrow56 = query.getColumnIndexOrThrow("visitor_pin");
                    int columnIndexOrThrow57 = query.getColumnIndexOrThrow("visitor_should_record");
                    int columnIndexOrThrow58 = query.getColumnIndexOrThrow("visitor_state");
                    int columnIndexOrThrow59 = query.getColumnIndexOrThrow("visitor_state_abbreviation");
                    int columnIndexOrThrow60 = query.getColumnIndexOrThrow("visitor_street1");
                    int columnIndexOrThrow61 = query.getColumnIndexOrThrow("visitor_street2");
                    int columnIndexOrThrow62 = query.getColumnIndexOrThrow("visitor_username");
                    int columnIndexOrThrow63 = query.getColumnIndexOrThrow("visitor_zipcode");
                    int columnIndexOrThrow64 = query.getColumnIndexOrThrow("voice_calls_disabled");
                    int columnIndexOrThrow65 = query.getColumnIndexOrThrow("voice_calls_disabled_until");
                    int columnIndexOrThrow66 = query.getColumnIndexOrThrow("zone");
                    int columnIndexOrThrow67 = query.getColumnIndexOrThrow("prison_video_calls_disabled");
                    int columnIndexOrThrow68 = query.getColumnIndexOrThrow("prison_voice_calls_disabled");
                    int columnIndexOrThrow69 = query.getColumnIndexOrThrow("prison_state");
                    int columnIndexOrThrow70 = query.getColumnIndexOrThrow("prison_payment_gateway");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        String string3 = query.getString(columnIndexOrThrow3);
                        String string4 = query.getString(columnIndexOrThrow4);
                        String string5 = query.getString(columnIndexOrThrow5);
                        String string6 = query.getString(columnIndexOrThrow6);
                        String string7 = query.getString(columnIndexOrThrow7);
                        String string8 = query.getString(columnIndexOrThrow8);
                        String string9 = query.getString(columnIndexOrThrow9);
                        String string10 = query.getString(columnIndexOrThrow10);
                        String string11 = query.getString(columnIndexOrThrow11);
                        String string12 = query.getString(columnIndexOrThrow12);
                        String string13 = query.getString(columnIndexOrThrow13);
                        int i4 = i3;
                        String string14 = query.getString(i4);
                        int i5 = columnIndexOrThrow;
                        int i6 = columnIndexOrThrow15;
                        String string15 = query.getString(i6);
                        columnIndexOrThrow15 = i6;
                        int i7 = columnIndexOrThrow16;
                        String string16 = query.getString(i7);
                        columnIndexOrThrow16 = i7;
                        int i8 = columnIndexOrThrow17;
                        String string17 = query.getString(i8);
                        columnIndexOrThrow17 = i8;
                        int i9 = columnIndexOrThrow18;
                        String string18 = query.getString(i9);
                        columnIndexOrThrow18 = i9;
                        int i10 = columnIndexOrThrow19;
                        String string19 = query.getString(i10);
                        columnIndexOrThrow19 = i10;
                        int i11 = columnIndexOrThrow20;
                        String string20 = query.getString(i11);
                        columnIndexOrThrow20 = i11;
                        int i12 = columnIndexOrThrow21;
                        boolean z3 = query.getInt(i12) != 0;
                        int i13 = columnIndexOrThrow22;
                        int i14 = i12;
                        int i15 = i13;
                        boolean z4 = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow23;
                        int i17 = i15;
                        int i18 = i16;
                        boolean z5 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        int i20 = i18;
                        int i21 = i19;
                        String string21 = query.getString(i21);
                        int i22 = i21;
                        int i23 = columnIndexOrThrow25;
                        String string22 = query.getString(i23);
                        columnIndexOrThrow25 = i23;
                        int i24 = columnIndexOrThrow26;
                        String string23 = query.getString(i24);
                        columnIndexOrThrow26 = i24;
                        int i25 = columnIndexOrThrow27;
                        String string24 = query.getString(i25);
                        columnIndexOrThrow27 = i25;
                        int i26 = columnIndexOrThrow28;
                        String string25 = query.getString(i26);
                        columnIndexOrThrow28 = i26;
                        int i27 = columnIndexOrThrow29;
                        String string26 = query.getString(i27);
                        columnIndexOrThrow29 = i27;
                        int i28 = columnIndexOrThrow30;
                        String string27 = query.getString(i28);
                        columnIndexOrThrow30 = i28;
                        int i29 = columnIndexOrThrow31;
                        String string28 = query.getString(i29);
                        columnIndexOrThrow31 = i29;
                        int i30 = columnIndexOrThrow32;
                        String string29 = query.getString(i30);
                        columnIndexOrThrow32 = i30;
                        int i31 = columnIndexOrThrow33;
                        String string30 = query.getString(i31);
                        columnIndexOrThrow33 = i31;
                        int i32 = columnIndexOrThrow34;
                        String string31 = query.getString(i32);
                        columnIndexOrThrow34 = i32;
                        int i33 = columnIndexOrThrow35;
                        String string32 = query.getString(i33);
                        columnIndexOrThrow35 = i33;
                        int i34 = columnIndexOrThrow36;
                        String string33 = query.getString(i34);
                        columnIndexOrThrow36 = i34;
                        int i35 = columnIndexOrThrow37;
                        String string34 = query.getString(i35);
                        columnIndexOrThrow37 = i35;
                        int i36 = columnIndexOrThrow38;
                        String string35 = query.getString(i36);
                        columnIndexOrThrow38 = i36;
                        int i37 = columnIndexOrThrow39;
                        String string36 = query.getString(i37);
                        columnIndexOrThrow39 = i37;
                        int i38 = columnIndexOrThrow40;
                        String string37 = query.getString(i38);
                        columnIndexOrThrow40 = i38;
                        int i39 = columnIndexOrThrow41;
                        String string38 = query.getString(i39);
                        columnIndexOrThrow41 = i39;
                        int i40 = columnIndexOrThrow42;
                        String string39 = query.getString(i40);
                        columnIndexOrThrow42 = i40;
                        int i41 = columnIndexOrThrow43;
                        String string40 = query.getString(i41);
                        columnIndexOrThrow43 = i41;
                        int i42 = columnIndexOrThrow44;
                        String string41 = query.getString(i42);
                        columnIndexOrThrow44 = i42;
                        int i43 = columnIndexOrThrow45;
                        String string42 = query.getString(i43);
                        columnIndexOrThrow45 = i43;
                        int i44 = columnIndexOrThrow46;
                        String string43 = query.getString(i44);
                        columnIndexOrThrow46 = i44;
                        int i45 = columnIndexOrThrow47;
                        String string44 = query.getString(i45);
                        columnIndexOrThrow47 = i45;
                        int i46 = columnIndexOrThrow48;
                        String string45 = query.getString(i46);
                        columnIndexOrThrow48 = i46;
                        int i47 = columnIndexOrThrow49;
                        String string46 = query.getString(i47);
                        columnIndexOrThrow49 = i47;
                        int i48 = columnIndexOrThrow50;
                        String string47 = query.getString(i48);
                        columnIndexOrThrow50 = i48;
                        int i49 = columnIndexOrThrow51;
                        String string48 = query.getString(i49);
                        columnIndexOrThrow51 = i49;
                        int i50 = columnIndexOrThrow52;
                        String string49 = query.getString(i50);
                        columnIndexOrThrow52 = i50;
                        int i51 = columnIndexOrThrow53;
                        String string50 = query.getString(i51);
                        columnIndexOrThrow53 = i51;
                        int i52 = columnIndexOrThrow54;
                        String string51 = query.getString(i52);
                        columnIndexOrThrow54 = i52;
                        int i53 = columnIndexOrThrow55;
                        String string52 = query.getString(i53);
                        columnIndexOrThrow55 = i53;
                        int i54 = columnIndexOrThrow56;
                        String string53 = query.getString(i54);
                        columnIndexOrThrow56 = i54;
                        int i55 = columnIndexOrThrow57;
                        String string54 = query.getString(i55);
                        columnIndexOrThrow57 = i55;
                        int i56 = columnIndexOrThrow58;
                        String string55 = query.getString(i56);
                        columnIndexOrThrow58 = i56;
                        int i57 = columnIndexOrThrow59;
                        String string56 = query.getString(i57);
                        columnIndexOrThrow59 = i57;
                        int i58 = columnIndexOrThrow60;
                        String string57 = query.getString(i58);
                        columnIndexOrThrow60 = i58;
                        int i59 = columnIndexOrThrow61;
                        String string58 = query.getString(i59);
                        columnIndexOrThrow61 = i59;
                        int i60 = columnIndexOrThrow62;
                        String string59 = query.getString(i60);
                        columnIndexOrThrow62 = i60;
                        int i61 = columnIndexOrThrow63;
                        String string60 = query.getString(i61);
                        columnIndexOrThrow63 = i61;
                        int i62 = columnIndexOrThrow64;
                        String string61 = query.getString(i62);
                        columnIndexOrThrow64 = i62;
                        int i63 = columnIndexOrThrow65;
                        String string62 = query.getString(i63);
                        columnIndexOrThrow65 = i63;
                        int i64 = columnIndexOrThrow66;
                        String string63 = query.getString(i64);
                        columnIndexOrThrow66 = i64;
                        int i65 = columnIndexOrThrow67;
                        if (query.getInt(i65) != 0) {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = true;
                        } else {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = false;
                        }
                        if (query.getInt(i) != 0) {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = true;
                        } else {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = false;
                        }
                        String string64 = query.getString(i2);
                        columnIndexOrThrow69 = i2;
                        int i66 = columnIndexOrThrow70;
                        columnIndexOrThrow70 = i66;
                        arrayList.add(new Inmate(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, z3, z4, z5, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, string32, string33, string34, string35, string36, string37, string38, string39, string40, string41, string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, string52, string53, string54, string55, string56, string57, string58, string59, string60, string61, string62, string63, z, z2, string64, query.getString(i66)));
                        columnIndexOrThrow21 = i14;
                        columnIndexOrThrow22 = i17;
                        columnIndexOrThrow23 = i20;
                        columnIndexOrThrow = i5;
                        columnIndexOrThrow24 = i22;
                        i3 = i4;
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

    public Flowable<List<Inmate>> getApprovedInmates(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM inmates WHERE visitor_id = ? AND approved = 'yes' AND NOT is_fraud='1'", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"inmates"}, new Callable<List<Inmate>>() {
            public List<Inmate> call() throws Exception {
                boolean z;
                int i;
                boolean z2;
                int i2;
                Cursor query = InmateDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("dob");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("dob_formatted");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(Attributes.FIRST_NAME);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("identifier");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("inmate_location");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(Attributes.LAST_NAME);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow(Attributes.FULL_NAME);
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("location");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("middle_name");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("prison_id");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("suffix");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("approved");
                    int columnIndexOrThrow17 = query.getColumnIndexOrThrow("credit_balance");
                    int columnIndexOrThrow18 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow19 = query.getColumnIndexOrThrow("calls_disabled");
                    int columnIndexOrThrow20 = query.getColumnIndexOrThrow("calls_disabled_until");
                    int columnIndexOrThrow21 = query.getColumnIndexOrThrow("can_text_message");
                    int columnIndexOrThrow22 = query.getColumnIndexOrThrow("can_video_message");
                    int columnIndexOrThrow23 = query.getColumnIndexOrThrow("can_image_message");
                    int columnIndexOrThrow24 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED);
                    int columnIndexOrThrow25 = query.getColumnIndexOrThrow("deleted_warnings");
                    int columnIndexOrThrow26 = query.getColumnIndexOrThrow("description");
                    int columnIndexOrThrow27 = query.getColumnIndexOrThrow("error_reported");
                    int columnIndexOrThrow28 = query.getColumnIndexOrThrow("fmt_name");
                    int columnIndexOrThrow29 = query.getColumnIndexOrThrow("inmate_general_funds_auto_recharge_enabled");
                    int columnIndexOrThrow30 = query.getColumnIndexOrThrow("internal_notes");
                    int columnIndexOrThrow31 = query.getColumnIndexOrThrow("invisible");
                    int columnIndexOrThrow32 = query.getColumnIndexOrThrow("is_fraud");
                    int columnIndexOrThrow33 = query.getColumnIndexOrThrow("is_fraud_set_by");
                    int columnIndexOrThrow34 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow35 = query.getColumnIndexOrThrow("occupant_id");
                    int columnIndexOrThrow36 = query.getColumnIndexOrThrow("prison_max_text_message_length");
                    int columnIndexOrThrow37 = query.getColumnIndexOrThrow("prison_max_video_message_length");
                    int columnIndexOrThrow38 = query.getColumnIndexOrThrow("prison_price_per_text_message");
                    int columnIndexOrThrow39 = query.getColumnIndexOrThrow("prison_price_per_video_message");
                    int columnIndexOrThrow40 = query.getColumnIndexOrThrow("prison_price_per_image_message");
                    int columnIndexOrThrow41 = query.getColumnIndexOrThrow("prison_price_per_gif_message");
                    int columnIndexOrThrow42 = query.getColumnIndexOrThrow("per_minute_charging_enabled");
                    int columnIndexOrThrow43 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                    int columnIndexOrThrow44 = query.getColumnIndexOrThrow("relationship");
                    int columnIndexOrThrow45 = query.getColumnIndexOrThrow("require_approval");
                    int columnIndexOrThrow46 = query.getColumnIndexOrThrow("should_record");
                    int columnIndexOrThrow47 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                    int columnIndexOrThrow48 = query.getColumnIndexOrThrow("talk_to_me_funds_auto_recharge_enabled");
                    int columnIndexOrThrow49 = query.getColumnIndexOrThrow("visitor_city");
                    int columnIndexOrThrow50 = query.getColumnIndexOrThrow("visitor_created");
                    int columnIndexOrThrow51 = query.getColumnIndexOrThrow("visitor_dob");
                    int columnIndexOrThrow52 = query.getColumnIndexOrThrow("visitor_first_name");
                    int columnIndexOrThrow53 = query.getColumnIndexOrThrow("visitor_last_name");
                    int columnIndexOrThrow54 = query.getColumnIndexOrThrow("visitor_name");
                    int columnIndexOrThrow55 = query.getColumnIndexOrThrow("visitor_phone");
                    int columnIndexOrThrow56 = query.getColumnIndexOrThrow("visitor_pin");
                    int columnIndexOrThrow57 = query.getColumnIndexOrThrow("visitor_should_record");
                    int columnIndexOrThrow58 = query.getColumnIndexOrThrow("visitor_state");
                    int columnIndexOrThrow59 = query.getColumnIndexOrThrow("visitor_state_abbreviation");
                    int columnIndexOrThrow60 = query.getColumnIndexOrThrow("visitor_street1");
                    int columnIndexOrThrow61 = query.getColumnIndexOrThrow("visitor_street2");
                    int columnIndexOrThrow62 = query.getColumnIndexOrThrow("visitor_username");
                    int columnIndexOrThrow63 = query.getColumnIndexOrThrow("visitor_zipcode");
                    int columnIndexOrThrow64 = query.getColumnIndexOrThrow("voice_calls_disabled");
                    int columnIndexOrThrow65 = query.getColumnIndexOrThrow("voice_calls_disabled_until");
                    int columnIndexOrThrow66 = query.getColumnIndexOrThrow("zone");
                    int columnIndexOrThrow67 = query.getColumnIndexOrThrow("prison_video_calls_disabled");
                    int columnIndexOrThrow68 = query.getColumnIndexOrThrow("prison_voice_calls_disabled");
                    int columnIndexOrThrow69 = query.getColumnIndexOrThrow("prison_state");
                    int columnIndexOrThrow70 = query.getColumnIndexOrThrow("prison_payment_gateway");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        String string3 = query.getString(columnIndexOrThrow3);
                        String string4 = query.getString(columnIndexOrThrow4);
                        String string5 = query.getString(columnIndexOrThrow5);
                        String string6 = query.getString(columnIndexOrThrow6);
                        String string7 = query.getString(columnIndexOrThrow7);
                        String string8 = query.getString(columnIndexOrThrow8);
                        String string9 = query.getString(columnIndexOrThrow9);
                        String string10 = query.getString(columnIndexOrThrow10);
                        String string11 = query.getString(columnIndexOrThrow11);
                        String string12 = query.getString(columnIndexOrThrow12);
                        String string13 = query.getString(columnIndexOrThrow13);
                        int i4 = i3;
                        String string14 = query.getString(i4);
                        int i5 = columnIndexOrThrow;
                        int i6 = columnIndexOrThrow15;
                        String string15 = query.getString(i6);
                        columnIndexOrThrow15 = i6;
                        int i7 = columnIndexOrThrow16;
                        String string16 = query.getString(i7);
                        columnIndexOrThrow16 = i7;
                        int i8 = columnIndexOrThrow17;
                        String string17 = query.getString(i8);
                        columnIndexOrThrow17 = i8;
                        int i9 = columnIndexOrThrow18;
                        String string18 = query.getString(i9);
                        columnIndexOrThrow18 = i9;
                        int i10 = columnIndexOrThrow19;
                        String string19 = query.getString(i10);
                        columnIndexOrThrow19 = i10;
                        int i11 = columnIndexOrThrow20;
                        String string20 = query.getString(i11);
                        columnIndexOrThrow20 = i11;
                        int i12 = columnIndexOrThrow21;
                        boolean z3 = query.getInt(i12) != 0;
                        int i13 = columnIndexOrThrow22;
                        int i14 = i12;
                        int i15 = i13;
                        boolean z4 = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow23;
                        int i17 = i15;
                        int i18 = i16;
                        boolean z5 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        int i20 = i18;
                        int i21 = i19;
                        String string21 = query.getString(i21);
                        int i22 = i21;
                        int i23 = columnIndexOrThrow25;
                        String string22 = query.getString(i23);
                        columnIndexOrThrow25 = i23;
                        int i24 = columnIndexOrThrow26;
                        String string23 = query.getString(i24);
                        columnIndexOrThrow26 = i24;
                        int i25 = columnIndexOrThrow27;
                        String string24 = query.getString(i25);
                        columnIndexOrThrow27 = i25;
                        int i26 = columnIndexOrThrow28;
                        String string25 = query.getString(i26);
                        columnIndexOrThrow28 = i26;
                        int i27 = columnIndexOrThrow29;
                        String string26 = query.getString(i27);
                        columnIndexOrThrow29 = i27;
                        int i28 = columnIndexOrThrow30;
                        String string27 = query.getString(i28);
                        columnIndexOrThrow30 = i28;
                        int i29 = columnIndexOrThrow31;
                        String string28 = query.getString(i29);
                        columnIndexOrThrow31 = i29;
                        int i30 = columnIndexOrThrow32;
                        String string29 = query.getString(i30);
                        columnIndexOrThrow32 = i30;
                        int i31 = columnIndexOrThrow33;
                        String string30 = query.getString(i31);
                        columnIndexOrThrow33 = i31;
                        int i32 = columnIndexOrThrow34;
                        String string31 = query.getString(i32);
                        columnIndexOrThrow34 = i32;
                        int i33 = columnIndexOrThrow35;
                        String string32 = query.getString(i33);
                        columnIndexOrThrow35 = i33;
                        int i34 = columnIndexOrThrow36;
                        String string33 = query.getString(i34);
                        columnIndexOrThrow36 = i34;
                        int i35 = columnIndexOrThrow37;
                        String string34 = query.getString(i35);
                        columnIndexOrThrow37 = i35;
                        int i36 = columnIndexOrThrow38;
                        String string35 = query.getString(i36);
                        columnIndexOrThrow38 = i36;
                        int i37 = columnIndexOrThrow39;
                        String string36 = query.getString(i37);
                        columnIndexOrThrow39 = i37;
                        int i38 = columnIndexOrThrow40;
                        String string37 = query.getString(i38);
                        columnIndexOrThrow40 = i38;
                        int i39 = columnIndexOrThrow41;
                        String string38 = query.getString(i39);
                        columnIndexOrThrow41 = i39;
                        int i40 = columnIndexOrThrow42;
                        String string39 = query.getString(i40);
                        columnIndexOrThrow42 = i40;
                        int i41 = columnIndexOrThrow43;
                        String string40 = query.getString(i41);
                        columnIndexOrThrow43 = i41;
                        int i42 = columnIndexOrThrow44;
                        String string41 = query.getString(i42);
                        columnIndexOrThrow44 = i42;
                        int i43 = columnIndexOrThrow45;
                        String string42 = query.getString(i43);
                        columnIndexOrThrow45 = i43;
                        int i44 = columnIndexOrThrow46;
                        String string43 = query.getString(i44);
                        columnIndexOrThrow46 = i44;
                        int i45 = columnIndexOrThrow47;
                        String string44 = query.getString(i45);
                        columnIndexOrThrow47 = i45;
                        int i46 = columnIndexOrThrow48;
                        String string45 = query.getString(i46);
                        columnIndexOrThrow48 = i46;
                        int i47 = columnIndexOrThrow49;
                        String string46 = query.getString(i47);
                        columnIndexOrThrow49 = i47;
                        int i48 = columnIndexOrThrow50;
                        String string47 = query.getString(i48);
                        columnIndexOrThrow50 = i48;
                        int i49 = columnIndexOrThrow51;
                        String string48 = query.getString(i49);
                        columnIndexOrThrow51 = i49;
                        int i50 = columnIndexOrThrow52;
                        String string49 = query.getString(i50);
                        columnIndexOrThrow52 = i50;
                        int i51 = columnIndexOrThrow53;
                        String string50 = query.getString(i51);
                        columnIndexOrThrow53 = i51;
                        int i52 = columnIndexOrThrow54;
                        String string51 = query.getString(i52);
                        columnIndexOrThrow54 = i52;
                        int i53 = columnIndexOrThrow55;
                        String string52 = query.getString(i53);
                        columnIndexOrThrow55 = i53;
                        int i54 = columnIndexOrThrow56;
                        String string53 = query.getString(i54);
                        columnIndexOrThrow56 = i54;
                        int i55 = columnIndexOrThrow57;
                        String string54 = query.getString(i55);
                        columnIndexOrThrow57 = i55;
                        int i56 = columnIndexOrThrow58;
                        String string55 = query.getString(i56);
                        columnIndexOrThrow58 = i56;
                        int i57 = columnIndexOrThrow59;
                        String string56 = query.getString(i57);
                        columnIndexOrThrow59 = i57;
                        int i58 = columnIndexOrThrow60;
                        String string57 = query.getString(i58);
                        columnIndexOrThrow60 = i58;
                        int i59 = columnIndexOrThrow61;
                        String string58 = query.getString(i59);
                        columnIndexOrThrow61 = i59;
                        int i60 = columnIndexOrThrow62;
                        String string59 = query.getString(i60);
                        columnIndexOrThrow62 = i60;
                        int i61 = columnIndexOrThrow63;
                        String string60 = query.getString(i61);
                        columnIndexOrThrow63 = i61;
                        int i62 = columnIndexOrThrow64;
                        String string61 = query.getString(i62);
                        columnIndexOrThrow64 = i62;
                        int i63 = columnIndexOrThrow65;
                        String string62 = query.getString(i63);
                        columnIndexOrThrow65 = i63;
                        int i64 = columnIndexOrThrow66;
                        String string63 = query.getString(i64);
                        columnIndexOrThrow66 = i64;
                        int i65 = columnIndexOrThrow67;
                        if (query.getInt(i65) != 0) {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = true;
                        } else {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = false;
                        }
                        if (query.getInt(i) != 0) {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = true;
                        } else {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = false;
                        }
                        String string64 = query.getString(i2);
                        columnIndexOrThrow69 = i2;
                        int i66 = columnIndexOrThrow70;
                        columnIndexOrThrow70 = i66;
                        arrayList.add(new Inmate(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, z3, z4, z5, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, string32, string33, string34, string35, string36, string37, string38, string39, string40, string41, string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, string52, string53, string54, string55, string56, string57, string58, string59, string60, string61, string62, string63, z, z2, string64, query.getString(i66)));
                        columnIndexOrThrow21 = i14;
                        columnIndexOrThrow22 = i17;
                        columnIndexOrThrow23 = i20;
                        columnIndexOrThrow = i5;
                        columnIndexOrThrow24 = i22;
                        i3 = i4;
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

    public Flowable<List<Inmate>> getInmates(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM inmates WHERE visitor_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"inmates"}, new Callable<List<Inmate>>() {
            public List<Inmate> call() throws Exception {
                boolean z;
                int i;
                boolean z2;
                int i2;
                Cursor query = InmateDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("dob");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("dob_formatted");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(Attributes.FIRST_NAME);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("identifier");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("inmate_location");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(Attributes.LAST_NAME);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow(Attributes.FULL_NAME);
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("location");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("middle_name");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("prison_id");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("suffix");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("approved");
                    int columnIndexOrThrow17 = query.getColumnIndexOrThrow("credit_balance");
                    int columnIndexOrThrow18 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow19 = query.getColumnIndexOrThrow("calls_disabled");
                    int columnIndexOrThrow20 = query.getColumnIndexOrThrow("calls_disabled_until");
                    int columnIndexOrThrow21 = query.getColumnIndexOrThrow("can_text_message");
                    int columnIndexOrThrow22 = query.getColumnIndexOrThrow("can_video_message");
                    int columnIndexOrThrow23 = query.getColumnIndexOrThrow("can_image_message");
                    int columnIndexOrThrow24 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED);
                    int columnIndexOrThrow25 = query.getColumnIndexOrThrow("deleted_warnings");
                    int columnIndexOrThrow26 = query.getColumnIndexOrThrow("description");
                    int columnIndexOrThrow27 = query.getColumnIndexOrThrow("error_reported");
                    int columnIndexOrThrow28 = query.getColumnIndexOrThrow("fmt_name");
                    int columnIndexOrThrow29 = query.getColumnIndexOrThrow("inmate_general_funds_auto_recharge_enabled");
                    int columnIndexOrThrow30 = query.getColumnIndexOrThrow("internal_notes");
                    int columnIndexOrThrow31 = query.getColumnIndexOrThrow("invisible");
                    int columnIndexOrThrow32 = query.getColumnIndexOrThrow("is_fraud");
                    int columnIndexOrThrow33 = query.getColumnIndexOrThrow("is_fraud_set_by");
                    int columnIndexOrThrow34 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow35 = query.getColumnIndexOrThrow("occupant_id");
                    int columnIndexOrThrow36 = query.getColumnIndexOrThrow("prison_max_text_message_length");
                    int columnIndexOrThrow37 = query.getColumnIndexOrThrow("prison_max_video_message_length");
                    int columnIndexOrThrow38 = query.getColumnIndexOrThrow("prison_price_per_text_message");
                    int columnIndexOrThrow39 = query.getColumnIndexOrThrow("prison_price_per_video_message");
                    int columnIndexOrThrow40 = query.getColumnIndexOrThrow("prison_price_per_image_message");
                    int columnIndexOrThrow41 = query.getColumnIndexOrThrow("prison_price_per_gif_message");
                    int columnIndexOrThrow42 = query.getColumnIndexOrThrow("per_minute_charging_enabled");
                    int columnIndexOrThrow43 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                    int columnIndexOrThrow44 = query.getColumnIndexOrThrow("relationship");
                    int columnIndexOrThrow45 = query.getColumnIndexOrThrow("require_approval");
                    int columnIndexOrThrow46 = query.getColumnIndexOrThrow("should_record");
                    int columnIndexOrThrow47 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                    int columnIndexOrThrow48 = query.getColumnIndexOrThrow("talk_to_me_funds_auto_recharge_enabled");
                    int columnIndexOrThrow49 = query.getColumnIndexOrThrow("visitor_city");
                    int columnIndexOrThrow50 = query.getColumnIndexOrThrow("visitor_created");
                    int columnIndexOrThrow51 = query.getColumnIndexOrThrow("visitor_dob");
                    int columnIndexOrThrow52 = query.getColumnIndexOrThrow("visitor_first_name");
                    int columnIndexOrThrow53 = query.getColumnIndexOrThrow("visitor_last_name");
                    int columnIndexOrThrow54 = query.getColumnIndexOrThrow("visitor_name");
                    int columnIndexOrThrow55 = query.getColumnIndexOrThrow("visitor_phone");
                    int columnIndexOrThrow56 = query.getColumnIndexOrThrow("visitor_pin");
                    int columnIndexOrThrow57 = query.getColumnIndexOrThrow("visitor_should_record");
                    int columnIndexOrThrow58 = query.getColumnIndexOrThrow("visitor_state");
                    int columnIndexOrThrow59 = query.getColumnIndexOrThrow("visitor_state_abbreviation");
                    int columnIndexOrThrow60 = query.getColumnIndexOrThrow("visitor_street1");
                    int columnIndexOrThrow61 = query.getColumnIndexOrThrow("visitor_street2");
                    int columnIndexOrThrow62 = query.getColumnIndexOrThrow("visitor_username");
                    int columnIndexOrThrow63 = query.getColumnIndexOrThrow("visitor_zipcode");
                    int columnIndexOrThrow64 = query.getColumnIndexOrThrow("voice_calls_disabled");
                    int columnIndexOrThrow65 = query.getColumnIndexOrThrow("voice_calls_disabled_until");
                    int columnIndexOrThrow66 = query.getColumnIndexOrThrow("zone");
                    int columnIndexOrThrow67 = query.getColumnIndexOrThrow("prison_video_calls_disabled");
                    int columnIndexOrThrow68 = query.getColumnIndexOrThrow("prison_voice_calls_disabled");
                    int columnIndexOrThrow69 = query.getColumnIndexOrThrow("prison_state");
                    int columnIndexOrThrow70 = query.getColumnIndexOrThrow("prison_payment_gateway");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        String string3 = query.getString(columnIndexOrThrow3);
                        String string4 = query.getString(columnIndexOrThrow4);
                        String string5 = query.getString(columnIndexOrThrow5);
                        String string6 = query.getString(columnIndexOrThrow6);
                        String string7 = query.getString(columnIndexOrThrow7);
                        String string8 = query.getString(columnIndexOrThrow8);
                        String string9 = query.getString(columnIndexOrThrow9);
                        String string10 = query.getString(columnIndexOrThrow10);
                        String string11 = query.getString(columnIndexOrThrow11);
                        String string12 = query.getString(columnIndexOrThrow12);
                        String string13 = query.getString(columnIndexOrThrow13);
                        int i4 = i3;
                        String string14 = query.getString(i4);
                        int i5 = columnIndexOrThrow;
                        int i6 = columnIndexOrThrow15;
                        String string15 = query.getString(i6);
                        columnIndexOrThrow15 = i6;
                        int i7 = columnIndexOrThrow16;
                        String string16 = query.getString(i7);
                        columnIndexOrThrow16 = i7;
                        int i8 = columnIndexOrThrow17;
                        String string17 = query.getString(i8);
                        columnIndexOrThrow17 = i8;
                        int i9 = columnIndexOrThrow18;
                        String string18 = query.getString(i9);
                        columnIndexOrThrow18 = i9;
                        int i10 = columnIndexOrThrow19;
                        String string19 = query.getString(i10);
                        columnIndexOrThrow19 = i10;
                        int i11 = columnIndexOrThrow20;
                        String string20 = query.getString(i11);
                        columnIndexOrThrow20 = i11;
                        int i12 = columnIndexOrThrow21;
                        boolean z3 = query.getInt(i12) != 0;
                        int i13 = columnIndexOrThrow22;
                        int i14 = i12;
                        int i15 = i13;
                        boolean z4 = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow23;
                        int i17 = i15;
                        int i18 = i16;
                        boolean z5 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        int i20 = i18;
                        int i21 = i19;
                        String string21 = query.getString(i21);
                        int i22 = i21;
                        int i23 = columnIndexOrThrow25;
                        String string22 = query.getString(i23);
                        columnIndexOrThrow25 = i23;
                        int i24 = columnIndexOrThrow26;
                        String string23 = query.getString(i24);
                        columnIndexOrThrow26 = i24;
                        int i25 = columnIndexOrThrow27;
                        String string24 = query.getString(i25);
                        columnIndexOrThrow27 = i25;
                        int i26 = columnIndexOrThrow28;
                        String string25 = query.getString(i26);
                        columnIndexOrThrow28 = i26;
                        int i27 = columnIndexOrThrow29;
                        String string26 = query.getString(i27);
                        columnIndexOrThrow29 = i27;
                        int i28 = columnIndexOrThrow30;
                        String string27 = query.getString(i28);
                        columnIndexOrThrow30 = i28;
                        int i29 = columnIndexOrThrow31;
                        String string28 = query.getString(i29);
                        columnIndexOrThrow31 = i29;
                        int i30 = columnIndexOrThrow32;
                        String string29 = query.getString(i30);
                        columnIndexOrThrow32 = i30;
                        int i31 = columnIndexOrThrow33;
                        String string30 = query.getString(i31);
                        columnIndexOrThrow33 = i31;
                        int i32 = columnIndexOrThrow34;
                        String string31 = query.getString(i32);
                        columnIndexOrThrow34 = i32;
                        int i33 = columnIndexOrThrow35;
                        String string32 = query.getString(i33);
                        columnIndexOrThrow35 = i33;
                        int i34 = columnIndexOrThrow36;
                        String string33 = query.getString(i34);
                        columnIndexOrThrow36 = i34;
                        int i35 = columnIndexOrThrow37;
                        String string34 = query.getString(i35);
                        columnIndexOrThrow37 = i35;
                        int i36 = columnIndexOrThrow38;
                        String string35 = query.getString(i36);
                        columnIndexOrThrow38 = i36;
                        int i37 = columnIndexOrThrow39;
                        String string36 = query.getString(i37);
                        columnIndexOrThrow39 = i37;
                        int i38 = columnIndexOrThrow40;
                        String string37 = query.getString(i38);
                        columnIndexOrThrow40 = i38;
                        int i39 = columnIndexOrThrow41;
                        String string38 = query.getString(i39);
                        columnIndexOrThrow41 = i39;
                        int i40 = columnIndexOrThrow42;
                        String string39 = query.getString(i40);
                        columnIndexOrThrow42 = i40;
                        int i41 = columnIndexOrThrow43;
                        String string40 = query.getString(i41);
                        columnIndexOrThrow43 = i41;
                        int i42 = columnIndexOrThrow44;
                        String string41 = query.getString(i42);
                        columnIndexOrThrow44 = i42;
                        int i43 = columnIndexOrThrow45;
                        String string42 = query.getString(i43);
                        columnIndexOrThrow45 = i43;
                        int i44 = columnIndexOrThrow46;
                        String string43 = query.getString(i44);
                        columnIndexOrThrow46 = i44;
                        int i45 = columnIndexOrThrow47;
                        String string44 = query.getString(i45);
                        columnIndexOrThrow47 = i45;
                        int i46 = columnIndexOrThrow48;
                        String string45 = query.getString(i46);
                        columnIndexOrThrow48 = i46;
                        int i47 = columnIndexOrThrow49;
                        String string46 = query.getString(i47);
                        columnIndexOrThrow49 = i47;
                        int i48 = columnIndexOrThrow50;
                        String string47 = query.getString(i48);
                        columnIndexOrThrow50 = i48;
                        int i49 = columnIndexOrThrow51;
                        String string48 = query.getString(i49);
                        columnIndexOrThrow51 = i49;
                        int i50 = columnIndexOrThrow52;
                        String string49 = query.getString(i50);
                        columnIndexOrThrow52 = i50;
                        int i51 = columnIndexOrThrow53;
                        String string50 = query.getString(i51);
                        columnIndexOrThrow53 = i51;
                        int i52 = columnIndexOrThrow54;
                        String string51 = query.getString(i52);
                        columnIndexOrThrow54 = i52;
                        int i53 = columnIndexOrThrow55;
                        String string52 = query.getString(i53);
                        columnIndexOrThrow55 = i53;
                        int i54 = columnIndexOrThrow56;
                        String string53 = query.getString(i54);
                        columnIndexOrThrow56 = i54;
                        int i55 = columnIndexOrThrow57;
                        String string54 = query.getString(i55);
                        columnIndexOrThrow57 = i55;
                        int i56 = columnIndexOrThrow58;
                        String string55 = query.getString(i56);
                        columnIndexOrThrow58 = i56;
                        int i57 = columnIndexOrThrow59;
                        String string56 = query.getString(i57);
                        columnIndexOrThrow59 = i57;
                        int i58 = columnIndexOrThrow60;
                        String string57 = query.getString(i58);
                        columnIndexOrThrow60 = i58;
                        int i59 = columnIndexOrThrow61;
                        String string58 = query.getString(i59);
                        columnIndexOrThrow61 = i59;
                        int i60 = columnIndexOrThrow62;
                        String string59 = query.getString(i60);
                        columnIndexOrThrow62 = i60;
                        int i61 = columnIndexOrThrow63;
                        String string60 = query.getString(i61);
                        columnIndexOrThrow63 = i61;
                        int i62 = columnIndexOrThrow64;
                        String string61 = query.getString(i62);
                        columnIndexOrThrow64 = i62;
                        int i63 = columnIndexOrThrow65;
                        String string62 = query.getString(i63);
                        columnIndexOrThrow65 = i63;
                        int i64 = columnIndexOrThrow66;
                        String string63 = query.getString(i64);
                        columnIndexOrThrow66 = i64;
                        int i65 = columnIndexOrThrow67;
                        if (query.getInt(i65) != 0) {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = true;
                        } else {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = false;
                        }
                        if (query.getInt(i) != 0) {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = true;
                        } else {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = false;
                        }
                        String string64 = query.getString(i2);
                        columnIndexOrThrow69 = i2;
                        int i66 = columnIndexOrThrow70;
                        columnIndexOrThrow70 = i66;
                        arrayList.add(new Inmate(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, z3, z4, z5, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, string32, string33, string34, string35, string36, string37, string38, string39, string40, string41, string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, string52, string53, string54, string55, string56, string57, string58, string59, string60, string61, string62, string63, z, z2, string64, query.getString(i66)));
                        columnIndexOrThrow21 = i14;
                        columnIndexOrThrow22 = i17;
                        columnIndexOrThrow23 = i20;
                        columnIndexOrThrow = i5;
                        columnIndexOrThrow24 = i22;
                        i3 = i4;
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

    public Flowable<List<Inmate>> getPendingInmates(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM inmates WHERE visitor_id = ? AND (approved = 'pending' OR is_fraud = '1')", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"inmates"}, new Callable<List<Inmate>>() {
            public List<Inmate> call() throws Exception {
                boolean z;
                int i;
                boolean z2;
                int i2;
                Cursor query = InmateDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("dob");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("dob_formatted");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(Attributes.FIRST_NAME);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("identifier");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("inmate_location");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(Attributes.LAST_NAME);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow(Attributes.FULL_NAME);
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("location");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("middle_name");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("prison_id");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("suffix");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("approved");
                    int columnIndexOrThrow17 = query.getColumnIndexOrThrow("credit_balance");
                    int columnIndexOrThrow18 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow19 = query.getColumnIndexOrThrow("calls_disabled");
                    int columnIndexOrThrow20 = query.getColumnIndexOrThrow("calls_disabled_until");
                    int columnIndexOrThrow21 = query.getColumnIndexOrThrow("can_text_message");
                    int columnIndexOrThrow22 = query.getColumnIndexOrThrow("can_video_message");
                    int columnIndexOrThrow23 = query.getColumnIndexOrThrow("can_image_message");
                    int columnIndexOrThrow24 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED);
                    int columnIndexOrThrow25 = query.getColumnIndexOrThrow("deleted_warnings");
                    int columnIndexOrThrow26 = query.getColumnIndexOrThrow("description");
                    int columnIndexOrThrow27 = query.getColumnIndexOrThrow("error_reported");
                    int columnIndexOrThrow28 = query.getColumnIndexOrThrow("fmt_name");
                    int columnIndexOrThrow29 = query.getColumnIndexOrThrow("inmate_general_funds_auto_recharge_enabled");
                    int columnIndexOrThrow30 = query.getColumnIndexOrThrow("internal_notes");
                    int columnIndexOrThrow31 = query.getColumnIndexOrThrow("invisible");
                    int columnIndexOrThrow32 = query.getColumnIndexOrThrow("is_fraud");
                    int columnIndexOrThrow33 = query.getColumnIndexOrThrow("is_fraud_set_by");
                    int columnIndexOrThrow34 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow35 = query.getColumnIndexOrThrow("occupant_id");
                    int columnIndexOrThrow36 = query.getColumnIndexOrThrow("prison_max_text_message_length");
                    int columnIndexOrThrow37 = query.getColumnIndexOrThrow("prison_max_video_message_length");
                    int columnIndexOrThrow38 = query.getColumnIndexOrThrow("prison_price_per_text_message");
                    int columnIndexOrThrow39 = query.getColumnIndexOrThrow("prison_price_per_video_message");
                    int columnIndexOrThrow40 = query.getColumnIndexOrThrow("prison_price_per_image_message");
                    int columnIndexOrThrow41 = query.getColumnIndexOrThrow("prison_price_per_gif_message");
                    int columnIndexOrThrow42 = query.getColumnIndexOrThrow("per_minute_charging_enabled");
                    int columnIndexOrThrow43 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                    int columnIndexOrThrow44 = query.getColumnIndexOrThrow("relationship");
                    int columnIndexOrThrow45 = query.getColumnIndexOrThrow("require_approval");
                    int columnIndexOrThrow46 = query.getColumnIndexOrThrow("should_record");
                    int columnIndexOrThrow47 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                    int columnIndexOrThrow48 = query.getColumnIndexOrThrow("talk_to_me_funds_auto_recharge_enabled");
                    int columnIndexOrThrow49 = query.getColumnIndexOrThrow("visitor_city");
                    int columnIndexOrThrow50 = query.getColumnIndexOrThrow("visitor_created");
                    int columnIndexOrThrow51 = query.getColumnIndexOrThrow("visitor_dob");
                    int columnIndexOrThrow52 = query.getColumnIndexOrThrow("visitor_first_name");
                    int columnIndexOrThrow53 = query.getColumnIndexOrThrow("visitor_last_name");
                    int columnIndexOrThrow54 = query.getColumnIndexOrThrow("visitor_name");
                    int columnIndexOrThrow55 = query.getColumnIndexOrThrow("visitor_phone");
                    int columnIndexOrThrow56 = query.getColumnIndexOrThrow("visitor_pin");
                    int columnIndexOrThrow57 = query.getColumnIndexOrThrow("visitor_should_record");
                    int columnIndexOrThrow58 = query.getColumnIndexOrThrow("visitor_state");
                    int columnIndexOrThrow59 = query.getColumnIndexOrThrow("visitor_state_abbreviation");
                    int columnIndexOrThrow60 = query.getColumnIndexOrThrow("visitor_street1");
                    int columnIndexOrThrow61 = query.getColumnIndexOrThrow("visitor_street2");
                    int columnIndexOrThrow62 = query.getColumnIndexOrThrow("visitor_username");
                    int columnIndexOrThrow63 = query.getColumnIndexOrThrow("visitor_zipcode");
                    int columnIndexOrThrow64 = query.getColumnIndexOrThrow("voice_calls_disabled");
                    int columnIndexOrThrow65 = query.getColumnIndexOrThrow("voice_calls_disabled_until");
                    int columnIndexOrThrow66 = query.getColumnIndexOrThrow("zone");
                    int columnIndexOrThrow67 = query.getColumnIndexOrThrow("prison_video_calls_disabled");
                    int columnIndexOrThrow68 = query.getColumnIndexOrThrow("prison_voice_calls_disabled");
                    int columnIndexOrThrow69 = query.getColumnIndexOrThrow("prison_state");
                    int columnIndexOrThrow70 = query.getColumnIndexOrThrow("prison_payment_gateway");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        String string3 = query.getString(columnIndexOrThrow3);
                        String string4 = query.getString(columnIndexOrThrow4);
                        String string5 = query.getString(columnIndexOrThrow5);
                        String string6 = query.getString(columnIndexOrThrow6);
                        String string7 = query.getString(columnIndexOrThrow7);
                        String string8 = query.getString(columnIndexOrThrow8);
                        String string9 = query.getString(columnIndexOrThrow9);
                        String string10 = query.getString(columnIndexOrThrow10);
                        String string11 = query.getString(columnIndexOrThrow11);
                        String string12 = query.getString(columnIndexOrThrow12);
                        String string13 = query.getString(columnIndexOrThrow13);
                        int i4 = i3;
                        String string14 = query.getString(i4);
                        int i5 = columnIndexOrThrow;
                        int i6 = columnIndexOrThrow15;
                        String string15 = query.getString(i6);
                        columnIndexOrThrow15 = i6;
                        int i7 = columnIndexOrThrow16;
                        String string16 = query.getString(i7);
                        columnIndexOrThrow16 = i7;
                        int i8 = columnIndexOrThrow17;
                        String string17 = query.getString(i8);
                        columnIndexOrThrow17 = i8;
                        int i9 = columnIndexOrThrow18;
                        String string18 = query.getString(i9);
                        columnIndexOrThrow18 = i9;
                        int i10 = columnIndexOrThrow19;
                        String string19 = query.getString(i10);
                        columnIndexOrThrow19 = i10;
                        int i11 = columnIndexOrThrow20;
                        String string20 = query.getString(i11);
                        columnIndexOrThrow20 = i11;
                        int i12 = columnIndexOrThrow21;
                        boolean z3 = query.getInt(i12) != 0;
                        int i13 = columnIndexOrThrow22;
                        int i14 = i12;
                        int i15 = i13;
                        boolean z4 = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow23;
                        int i17 = i15;
                        int i18 = i16;
                        boolean z5 = query.getInt(i18) != 0;
                        int i19 = columnIndexOrThrow24;
                        int i20 = i18;
                        int i21 = i19;
                        String string21 = query.getString(i21);
                        int i22 = i21;
                        int i23 = columnIndexOrThrow25;
                        String string22 = query.getString(i23);
                        columnIndexOrThrow25 = i23;
                        int i24 = columnIndexOrThrow26;
                        String string23 = query.getString(i24);
                        columnIndexOrThrow26 = i24;
                        int i25 = columnIndexOrThrow27;
                        String string24 = query.getString(i25);
                        columnIndexOrThrow27 = i25;
                        int i26 = columnIndexOrThrow28;
                        String string25 = query.getString(i26);
                        columnIndexOrThrow28 = i26;
                        int i27 = columnIndexOrThrow29;
                        String string26 = query.getString(i27);
                        columnIndexOrThrow29 = i27;
                        int i28 = columnIndexOrThrow30;
                        String string27 = query.getString(i28);
                        columnIndexOrThrow30 = i28;
                        int i29 = columnIndexOrThrow31;
                        String string28 = query.getString(i29);
                        columnIndexOrThrow31 = i29;
                        int i30 = columnIndexOrThrow32;
                        String string29 = query.getString(i30);
                        columnIndexOrThrow32 = i30;
                        int i31 = columnIndexOrThrow33;
                        String string30 = query.getString(i31);
                        columnIndexOrThrow33 = i31;
                        int i32 = columnIndexOrThrow34;
                        String string31 = query.getString(i32);
                        columnIndexOrThrow34 = i32;
                        int i33 = columnIndexOrThrow35;
                        String string32 = query.getString(i33);
                        columnIndexOrThrow35 = i33;
                        int i34 = columnIndexOrThrow36;
                        String string33 = query.getString(i34);
                        columnIndexOrThrow36 = i34;
                        int i35 = columnIndexOrThrow37;
                        String string34 = query.getString(i35);
                        columnIndexOrThrow37 = i35;
                        int i36 = columnIndexOrThrow38;
                        String string35 = query.getString(i36);
                        columnIndexOrThrow38 = i36;
                        int i37 = columnIndexOrThrow39;
                        String string36 = query.getString(i37);
                        columnIndexOrThrow39 = i37;
                        int i38 = columnIndexOrThrow40;
                        String string37 = query.getString(i38);
                        columnIndexOrThrow40 = i38;
                        int i39 = columnIndexOrThrow41;
                        String string38 = query.getString(i39);
                        columnIndexOrThrow41 = i39;
                        int i40 = columnIndexOrThrow42;
                        String string39 = query.getString(i40);
                        columnIndexOrThrow42 = i40;
                        int i41 = columnIndexOrThrow43;
                        String string40 = query.getString(i41);
                        columnIndexOrThrow43 = i41;
                        int i42 = columnIndexOrThrow44;
                        String string41 = query.getString(i42);
                        columnIndexOrThrow44 = i42;
                        int i43 = columnIndexOrThrow45;
                        String string42 = query.getString(i43);
                        columnIndexOrThrow45 = i43;
                        int i44 = columnIndexOrThrow46;
                        String string43 = query.getString(i44);
                        columnIndexOrThrow46 = i44;
                        int i45 = columnIndexOrThrow47;
                        String string44 = query.getString(i45);
                        columnIndexOrThrow47 = i45;
                        int i46 = columnIndexOrThrow48;
                        String string45 = query.getString(i46);
                        columnIndexOrThrow48 = i46;
                        int i47 = columnIndexOrThrow49;
                        String string46 = query.getString(i47);
                        columnIndexOrThrow49 = i47;
                        int i48 = columnIndexOrThrow50;
                        String string47 = query.getString(i48);
                        columnIndexOrThrow50 = i48;
                        int i49 = columnIndexOrThrow51;
                        String string48 = query.getString(i49);
                        columnIndexOrThrow51 = i49;
                        int i50 = columnIndexOrThrow52;
                        String string49 = query.getString(i50);
                        columnIndexOrThrow52 = i50;
                        int i51 = columnIndexOrThrow53;
                        String string50 = query.getString(i51);
                        columnIndexOrThrow53 = i51;
                        int i52 = columnIndexOrThrow54;
                        String string51 = query.getString(i52);
                        columnIndexOrThrow54 = i52;
                        int i53 = columnIndexOrThrow55;
                        String string52 = query.getString(i53);
                        columnIndexOrThrow55 = i53;
                        int i54 = columnIndexOrThrow56;
                        String string53 = query.getString(i54);
                        columnIndexOrThrow56 = i54;
                        int i55 = columnIndexOrThrow57;
                        String string54 = query.getString(i55);
                        columnIndexOrThrow57 = i55;
                        int i56 = columnIndexOrThrow58;
                        String string55 = query.getString(i56);
                        columnIndexOrThrow58 = i56;
                        int i57 = columnIndexOrThrow59;
                        String string56 = query.getString(i57);
                        columnIndexOrThrow59 = i57;
                        int i58 = columnIndexOrThrow60;
                        String string57 = query.getString(i58);
                        columnIndexOrThrow60 = i58;
                        int i59 = columnIndexOrThrow61;
                        String string58 = query.getString(i59);
                        columnIndexOrThrow61 = i59;
                        int i60 = columnIndexOrThrow62;
                        String string59 = query.getString(i60);
                        columnIndexOrThrow62 = i60;
                        int i61 = columnIndexOrThrow63;
                        String string60 = query.getString(i61);
                        columnIndexOrThrow63 = i61;
                        int i62 = columnIndexOrThrow64;
                        String string61 = query.getString(i62);
                        columnIndexOrThrow64 = i62;
                        int i63 = columnIndexOrThrow65;
                        String string62 = query.getString(i63);
                        columnIndexOrThrow65 = i63;
                        int i64 = columnIndexOrThrow66;
                        String string63 = query.getString(i64);
                        columnIndexOrThrow66 = i64;
                        int i65 = columnIndexOrThrow67;
                        if (query.getInt(i65) != 0) {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = true;
                        } else {
                            columnIndexOrThrow67 = i65;
                            i = columnIndexOrThrow68;
                            z = false;
                        }
                        if (query.getInt(i) != 0) {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = true;
                        } else {
                            columnIndexOrThrow68 = i;
                            i2 = columnIndexOrThrow69;
                            z2 = false;
                        }
                        String string64 = query.getString(i2);
                        columnIndexOrThrow69 = i2;
                        int i66 = columnIndexOrThrow70;
                        columnIndexOrThrow70 = i66;
                        arrayList.add(new Inmate(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, z3, z4, z5, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, string32, string33, string34, string35, string36, string37, string38, string39, string40, string41, string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, string52, string53, string54, string55, string56, string57, string58, string59, string60, string61, string62, string63, z, z2, string64, query.getString(i66)));
                        columnIndexOrThrow21 = i14;
                        columnIndexOrThrow22 = i17;
                        columnIndexOrThrow23 = i20;
                        columnIndexOrThrow = i5;
                        columnIndexOrThrow24 = i22;
                        i3 = i4;
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

    public Single<Inmate> getInmate(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM inmates WHERE id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Single.fromCallable(new Callable<Inmate>() {
            public Inmate call() throws Exception {
                Inmate inmate;
                boolean z;
                int i;
                boolean z2;
                int i2;
                boolean z3;
                int i3;
                boolean z4;
                int i4;
                boolean z5;
                int i5;
                Cursor query = InmateDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("dob");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("dob_formatted");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(Attributes.FIRST_NAME);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("identifier");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("inmate_location");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(Attributes.LAST_NAME);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow(Attributes.FULL_NAME);
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("location");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("middle_name");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("prison_id");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    try {
                        int columnIndexOrThrow15 = query.getColumnIndexOrThrow("suffix");
                        int columnIndexOrThrow16 = query.getColumnIndexOrThrow("approved");
                        int columnIndexOrThrow17 = query.getColumnIndexOrThrow("credit_balance");
                        int columnIndexOrThrow18 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                        int columnIndexOrThrow19 = query.getColumnIndexOrThrow("calls_disabled");
                        int columnIndexOrThrow20 = query.getColumnIndexOrThrow("calls_disabled_until");
                        int columnIndexOrThrow21 = query.getColumnIndexOrThrow("can_text_message");
                        int columnIndexOrThrow22 = query.getColumnIndexOrThrow("can_video_message");
                        int columnIndexOrThrow23 = query.getColumnIndexOrThrow("can_image_message");
                        int columnIndexOrThrow24 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_DELETED);
                        int columnIndexOrThrow25 = query.getColumnIndexOrThrow("deleted_warnings");
                        int columnIndexOrThrow26 = query.getColumnIndexOrThrow("description");
                        int columnIndexOrThrow27 = query.getColumnIndexOrThrow("error_reported");
                        int columnIndexOrThrow28 = query.getColumnIndexOrThrow("fmt_name");
                        int columnIndexOrThrow29 = query.getColumnIndexOrThrow("inmate_general_funds_auto_recharge_enabled");
                        int columnIndexOrThrow30 = query.getColumnIndexOrThrow("internal_notes");
                        int columnIndexOrThrow31 = query.getColumnIndexOrThrow("invisible");
                        int columnIndexOrThrow32 = query.getColumnIndexOrThrow("is_fraud");
                        int columnIndexOrThrow33 = query.getColumnIndexOrThrow("is_fraud_set_by");
                        int columnIndexOrThrow34 = query.getColumnIndexOrThrow("notes");
                        int columnIndexOrThrow35 = query.getColumnIndexOrThrow("occupant_id");
                        int columnIndexOrThrow36 = query.getColumnIndexOrThrow("prison_max_text_message_length");
                        int columnIndexOrThrow37 = query.getColumnIndexOrThrow("prison_max_video_message_length");
                        int columnIndexOrThrow38 = query.getColumnIndexOrThrow("prison_price_per_text_message");
                        int columnIndexOrThrow39 = query.getColumnIndexOrThrow("prison_price_per_video_message");
                        int columnIndexOrThrow40 = query.getColumnIndexOrThrow("prison_price_per_image_message");
                        int columnIndexOrThrow41 = query.getColumnIndexOrThrow("prison_price_per_gif_message");
                        int columnIndexOrThrow42 = query.getColumnIndexOrThrow("per_minute_charging_enabled");
                        int columnIndexOrThrow43 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                        int columnIndexOrThrow44 = query.getColumnIndexOrThrow("relationship");
                        int columnIndexOrThrow45 = query.getColumnIndexOrThrow("require_approval");
                        int columnIndexOrThrow46 = query.getColumnIndexOrThrow("should_record");
                        int columnIndexOrThrow47 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                        int columnIndexOrThrow48 = query.getColumnIndexOrThrow("talk_to_me_funds_auto_recharge_enabled");
                        int columnIndexOrThrow49 = query.getColumnIndexOrThrow("visitor_city");
                        int columnIndexOrThrow50 = query.getColumnIndexOrThrow("visitor_created");
                        int columnIndexOrThrow51 = query.getColumnIndexOrThrow("visitor_dob");
                        int columnIndexOrThrow52 = query.getColumnIndexOrThrow("visitor_first_name");
                        int columnIndexOrThrow53 = query.getColumnIndexOrThrow("visitor_last_name");
                        int columnIndexOrThrow54 = query.getColumnIndexOrThrow("visitor_name");
                        int columnIndexOrThrow55 = query.getColumnIndexOrThrow("visitor_phone");
                        int columnIndexOrThrow56 = query.getColumnIndexOrThrow("visitor_pin");
                        int columnIndexOrThrow57 = query.getColumnIndexOrThrow("visitor_should_record");
                        int columnIndexOrThrow58 = query.getColumnIndexOrThrow("visitor_state");
                        int columnIndexOrThrow59 = query.getColumnIndexOrThrow("visitor_state_abbreviation");
                        int columnIndexOrThrow60 = query.getColumnIndexOrThrow("visitor_street1");
                        int columnIndexOrThrow61 = query.getColumnIndexOrThrow("visitor_street2");
                        int columnIndexOrThrow62 = query.getColumnIndexOrThrow("visitor_username");
                        int columnIndexOrThrow63 = query.getColumnIndexOrThrow("visitor_zipcode");
                        int columnIndexOrThrow64 = query.getColumnIndexOrThrow("voice_calls_disabled");
                        int columnIndexOrThrow65 = query.getColumnIndexOrThrow("voice_calls_disabled_until");
                        int columnIndexOrThrow66 = query.getColumnIndexOrThrow("zone");
                        int columnIndexOrThrow67 = query.getColumnIndexOrThrow("prison_video_calls_disabled");
                        int columnIndexOrThrow68 = query.getColumnIndexOrThrow("prison_voice_calls_disabled");
                        int columnIndexOrThrow69 = query.getColumnIndexOrThrow("prison_state");
                        int columnIndexOrThrow70 = query.getColumnIndexOrThrow("prison_payment_gateway");
                        if (query.moveToFirst()) {
                            String string = query.getString(columnIndexOrThrow);
                            String string2 = query.getString(columnIndexOrThrow2);
                            String string3 = query.getString(columnIndexOrThrow3);
                            String string4 = query.getString(columnIndexOrThrow4);
                            String string5 = query.getString(columnIndexOrThrow5);
                            String string6 = query.getString(columnIndexOrThrow6);
                            String string7 = query.getString(columnIndexOrThrow7);
                            String string8 = query.getString(columnIndexOrThrow8);
                            String string9 = query.getString(columnIndexOrThrow9);
                            String string10 = query.getString(columnIndexOrThrow10);
                            String string11 = query.getString(columnIndexOrThrow11);
                            String string12 = query.getString(columnIndexOrThrow12);
                            String string13 = query.getString(columnIndexOrThrow13);
                            String string14 = query.getString(columnIndexOrThrow14);
                            String string15 = query.getString(columnIndexOrThrow15);
                            String string16 = query.getString(columnIndexOrThrow16);
                            String string17 = query.getString(columnIndexOrThrow17);
                            String string18 = query.getString(columnIndexOrThrow18);
                            String string19 = query.getString(columnIndexOrThrow19);
                            String string20 = query.getString(columnIndexOrThrow20);
                            if (query.getInt(columnIndexOrThrow21) != 0) {
                                i = columnIndexOrThrow22;
                                z = true;
                            } else {
                                i = columnIndexOrThrow22;
                                z = false;
                            }
                            if (query.getInt(i) != 0) {
                                i2 = columnIndexOrThrow23;
                                z2 = true;
                            } else {
                                i2 = columnIndexOrThrow23;
                                z2 = false;
                            }
                            if (query.getInt(i2) != 0) {
                                i3 = columnIndexOrThrow24;
                                z3 = true;
                            } else {
                                i3 = columnIndexOrThrow24;
                                z3 = false;
                            }
                            String string21 = query.getString(i3);
                            String string22 = query.getString(columnIndexOrThrow25);
                            String string23 = query.getString(columnIndexOrThrow26);
                            String string24 = query.getString(columnIndexOrThrow27);
                            String string25 = query.getString(columnIndexOrThrow28);
                            String string26 = query.getString(columnIndexOrThrow29);
                            String string27 = query.getString(columnIndexOrThrow30);
                            String string28 = query.getString(columnIndexOrThrow31);
                            String string29 = query.getString(columnIndexOrThrow32);
                            String string30 = query.getString(columnIndexOrThrow33);
                            String string31 = query.getString(columnIndexOrThrow34);
                            String string32 = query.getString(columnIndexOrThrow35);
                            String string33 = query.getString(columnIndexOrThrow36);
                            String string34 = query.getString(columnIndexOrThrow37);
                            String string35 = query.getString(columnIndexOrThrow38);
                            String string36 = query.getString(columnIndexOrThrow39);
                            String string37 = query.getString(columnIndexOrThrow40);
                            String string38 = query.getString(columnIndexOrThrow41);
                            String string39 = query.getString(columnIndexOrThrow42);
                            String string40 = query.getString(columnIndexOrThrow43);
                            String string41 = query.getString(columnIndexOrThrow44);
                            String string42 = query.getString(columnIndexOrThrow45);
                            String string43 = query.getString(columnIndexOrThrow46);
                            String string44 = query.getString(columnIndexOrThrow47);
                            String string45 = query.getString(columnIndexOrThrow48);
                            String string46 = query.getString(columnIndexOrThrow49);
                            String string47 = query.getString(columnIndexOrThrow50);
                            String string48 = query.getString(columnIndexOrThrow51);
                            String string49 = query.getString(columnIndexOrThrow52);
                            String string50 = query.getString(columnIndexOrThrow53);
                            String string51 = query.getString(columnIndexOrThrow54);
                            String string52 = query.getString(columnIndexOrThrow55);
                            String string53 = query.getString(columnIndexOrThrow56);
                            String string54 = query.getString(columnIndexOrThrow57);
                            String string55 = query.getString(columnIndexOrThrow58);
                            String string56 = query.getString(columnIndexOrThrow59);
                            String string57 = query.getString(columnIndexOrThrow60);
                            String string58 = query.getString(columnIndexOrThrow61);
                            String string59 = query.getString(columnIndexOrThrow62);
                            String string60 = query.getString(columnIndexOrThrow63);
                            String string61 = query.getString(columnIndexOrThrow64);
                            String string62 = query.getString(columnIndexOrThrow65);
                            String string63 = query.getString(columnIndexOrThrow66);
                            if (query.getInt(columnIndexOrThrow67) != 0) {
                                i4 = columnIndexOrThrow68;
                                z4 = true;
                            } else {
                                i4 = columnIndexOrThrow68;
                                z4 = false;
                            }
                            if (query.getInt(i4) != 0) {
                                i5 = columnIndexOrThrow69;
                                z5 = true;
                            } else {
                                i5 = columnIndexOrThrow69;
                                z5 = false;
                            }
                            inmate = new Inmate(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, z, z2, z3, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, string32, string33, string34, string35, string36, string37, string38, string39, string40, string41, string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, string52, string53, string54, string55, string56, string57, string58, string59, string60, string61, string62, string63, z4, z5, query.getString(i5), query.getString(columnIndexOrThrow70));
                        } else {
                            inmate = null;
                        }
                        if (inmate != null) {
                            query.close();
                            return inmate;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Query returned empty result set: ");
                        try {
                            sb.append(acquire.getSql());
                            throw new EmptyResultSetException(sb.toString());
                        } catch (Throwable th) {
                            th = th;
                            query.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query.close();
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
