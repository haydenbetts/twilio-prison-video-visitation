package io.sqlc;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import io.liteglue.SQLiteConnection;
import io.liteglue.SQLiteConnector;
import io.liteglue.SQLiteStatement;
import java.io.File;
import java.sql.SQLException;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SQLiteConnectorDatabase extends SQLiteAndroidDatabase {
    static SQLiteConnector connector = new SQLiteConnector();
    SQLiteConnection mydb;

    /* access modifiers changed from: package-private */
    public void bugWorkaround() {
    }

    SQLiteConnectorDatabase() {
    }

    /* access modifiers changed from: package-private */
    public void open(File file) throws Exception {
        this.mydb = connector.newSQLiteConnection(file.getAbsolutePath(), 6);
    }

    /* access modifiers changed from: package-private */
    public void closeDatabaseNow() {
        try {
            if (this.mydb != null) {
                this.mydb.dispose();
            }
        } catch (Exception e) {
            Log.e(SQLitePlugin.class.getSimpleName(), "couldn't close database, ignoring", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void executeSqlBatch(String[] strArr, JSONArray[] jSONArrayArr, CallbackContext callbackContext) {
        int i;
        if (this.mydb == null) {
            callbackContext.error("database has been closed");
            return;
        }
        int length = strArr.length;
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = null;
            String str = EnvironmentCompat.MEDIA_UNKNOWN;
            try {
                jSONObject = executeSQLiteStatement(strArr[i2], jSONArrayArr[i2], callbackContext);
                long totalChanges = ((long) this.mydb.getTotalChanges()) - ((long) this.mydb.getTotalChanges());
                jSONObject.put("rowsAffected", totalChanges);
                if (totalChanges > 0) {
                    long lastInsertRowid = this.mydb.getLastInsertRowid();
                    if (lastInsertRowid > 0) {
                        jSONObject.put("insertId", lastInsertRowid);
                    }
                }
                i = 0;
            } catch (SQLException e) {
                e.printStackTrace();
                int errorCode = e.getErrorCode();
                str = e.getMessage();
                Log.v("executeSqlBatch", "SQLitePlugin.executeSql[Batch](): SQL Error code = " + errorCode + " message = " + str);
                i = errorCode != 1 ? errorCode != 13 ? errorCode != 19 ? 0 : 6 : 4 : 5;
            } catch (JSONException e2) {
                e2.printStackTrace();
                str = e2.getMessage();
                Log.e("executeSqlBatch", "SQLitePlugin.executeSql[Batch](): UNEXPECTED JSON Error=" + str);
                i = 0;
            }
            if (jSONObject != null) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "success");
                    jSONObject2.put("result", jSONObject);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    Log.e("executeSqlBatch", "SQLitePlugin.executeSql[Batch](): Error=" + e3.getMessage());
                }
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("type", "error");
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("message", str);
                jSONObject4.put("code", i);
                jSONObject3.put("result", jSONObject4);
                jSONArray.put(jSONObject3);
            }
        }
        callbackContext.success(jSONArray);
    }

    private JSONObject executeSQLiteStatement(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException, SQLException {
        JSONObject jSONObject = new JSONObject();
        SQLiteStatement prepareStatement = this.mydb.prepareStatement(str);
        try {
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.isNull(i)) {
                    prepareStatement.bindNull(i + 1);
                } else {
                    Object obj = jSONArray.get(i);
                    if (!(obj instanceof Float)) {
                        if (!(obj instanceof Double)) {
                            if (obj instanceof Number) {
                                prepareStatement.bindLong(i + 1, jSONArray.getLong(i));
                            } else {
                                prepareStatement.bindTextNativeString(i + 1, jSONArray.getString(i));
                            }
                        }
                    }
                    prepareStatement.bindDouble(i + 1, jSONArray.getDouble(i));
                }
            }
            if (prepareStatement.step()) {
                JSONArray jSONArray2 = new JSONArray();
                int columnCount = prepareStatement.getColumnCount();
                do {
                    JSONObject jSONObject2 = new JSONObject();
                    int i2 = 0;
                    while (i2 < columnCount) {
                        try {
                            String columnName = prepareStatement.getColumnName(i2);
                            int columnType = prepareStatement.getColumnType(i2);
                            if (columnType != 5) {
                                switch (columnType) {
                                    case 1:
                                        jSONObject2.put(columnName, prepareStatement.getColumnLong(i2));
                                        break;
                                    case 2:
                                        jSONObject2.put(columnName, prepareStatement.getColumnDouble(i2));
                                        break;
                                    default:
                                        jSONObject2.put(columnName, prepareStatement.getColumnTextNativeString(i2));
                                        break;
                                }
                            } else {
                                jSONObject2.put(columnName, JSONObject.NULL);
                            }
                            i2++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    jSONArray2.put(jSONObject2);
                } while (prepareStatement.step());
                try {
                    jSONObject.put("rows", jSONArray2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            prepareStatement.dispose();
            return jSONObject;
        } catch (SQLException e3) {
            e3.printStackTrace();
            String message = e3.getMessage();
            Log.v("executeSqlBatch", "SQLitePlugin.executeSql[Batch](): Error=" + message);
            prepareStatement.dispose();
            throw e3;
        } catch (JSONException e4) {
            e4.printStackTrace();
            String message2 = e4.getMessage();
            Log.v("executeSqlBatch", "SQLitePlugin.executeSql[Batch](): Error=" + message2);
            prepareStatement.dispose();
            throw e4;
        }
    }
}
