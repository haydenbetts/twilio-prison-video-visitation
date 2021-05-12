package io.sqlc;

import android.util.Log;
import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SQLitePlugin extends CordovaPlugin {
    /* access modifiers changed from: private */
    public Map<String, DBRunner> dbrmap = new ConcurrentHashMap();

    private enum Action {
        echoStringValue,
        open,
        close,
        delete,
        executeSqlBatch,
        backgroundExecuteSqlBatch
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            try {
                return executeAndPossiblyThrow(Action.valueOf(str), jSONArray, callbackContext);
            } catch (JSONException e) {
                Log.e(SQLitePlugin.class.getSimpleName(), "unexpected error", e);
                return false;
            }
        } catch (IllegalArgumentException e2) {
            Log.e(SQLitePlugin.class.getSimpleName(), "unexpected error", e2);
            return false;
        }
    }

    private boolean executeAndPossiblyThrow(Action action, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case echoStringValue:
                callbackContext.success(jSONArray.getJSONObject(0).getString("value"));
                return true;
            case open:
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                startDatabase(jSONObject.getString("name"), jSONObject, callbackContext);
                return true;
            case close:
                closeDatabase(jSONArray.getJSONObject(0).getString("path"), callbackContext);
                return true;
            case delete:
                deleteDatabase(jSONArray.getJSONObject(0).getString("path"), callbackContext);
                return true;
            case executeSqlBatch:
            case backgroundExecuteSqlBatch:
                JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                String string = jSONObject2.getJSONObject("dbargs").getString("dbname");
                JSONArray jSONArray2 = jSONObject2.getJSONArray("executes");
                if (jSONArray2.isNull(0)) {
                    callbackContext.error("INTERNAL PLUGIN ERROR: missing executes list");
                    return true;
                }
                int length = jSONArray2.length();
                String[] strArr = new String[length];
                JSONArray[] jSONArrayArr = new JSONArray[length];
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                    strArr[i] = jSONObject3.getString("sql");
                    jSONArrayArr[i] = jSONObject3.getJSONArray("params");
                }
                DBQuery dBQuery = new DBQuery(strArr, jSONArrayArr, callbackContext);
                DBRunner dBRunner = this.dbrmap.get(string);
                if (dBRunner != null) {
                    try {
                        dBRunner.q.put(dBQuery);
                        return true;
                    } catch (Exception e) {
                        Log.e(SQLitePlugin.class.getSimpleName(), "couldn't add to queue", e);
                        callbackContext.error("INTERNAL PLUGIN ERROR: couldn't add to queue");
                        return true;
                    }
                } else {
                    callbackContext.error("INTERNAL PLUGIN ERROR: database not open");
                    return true;
                }
            default:
                return true;
        }
    }

    public void onDestroy() {
        while (!this.dbrmap.isEmpty()) {
            String next = this.dbrmap.keySet().iterator().next();
            closeDatabaseNow(next);
            try {
                this.dbrmap.get(next).q.put(new DBQuery());
            } catch (Exception e) {
                Log.e(SQLitePlugin.class.getSimpleName(), "INTERNAL PLUGIN CLEANUP ERROR: could not stop db thread due to exception", e);
            }
            this.dbrmap.remove(next);
        }
    }

    private void startDatabase(String str, JSONObject jSONObject, CallbackContext callbackContext) {
        if (this.dbrmap.get(str) != null) {
            callbackContext.error("INTERNAL ERROR: database already open for db name: " + str);
            return;
        }
        DBRunner dBRunner = new DBRunner(str, jSONObject, callbackContext);
        this.dbrmap.put(str, dBRunner);
        this.cordova.getThreadPool().execute(dBRunner);
    }

    /* access modifiers changed from: private */
    public SQLiteAndroidDatabase openDatabase(String str, CallbackContext callbackContext, boolean z) throws Exception {
        try {
            File databasePath = this.cordova.getActivity().getDatabasePath(str);
            if (!databasePath.exists()) {
                databasePath.getParentFile().mkdirs();
            }
            Log.v("info", "Open sqlite db: " + databasePath.getAbsolutePath());
            SQLiteAndroidDatabase sQLiteAndroidDatabase = z ? new SQLiteAndroidDatabase() : new SQLiteConnectorDatabase();
            sQLiteAndroidDatabase.open(databasePath);
            if (callbackContext != null) {
                callbackContext.success();
            }
            return sQLiteAndroidDatabase;
        } catch (Exception e) {
            if (callbackContext != null) {
                callbackContext.error("can't open database " + e);
            }
            throw e;
        }
    }

    private void closeDatabase(String str, CallbackContext callbackContext) {
        DBRunner dBRunner = this.dbrmap.get(str);
        if (dBRunner != null) {
            try {
                dBRunner.q.put(new DBQuery(false, callbackContext));
            } catch (Exception e) {
                if (callbackContext != null) {
                    callbackContext.error("couldn't close database" + e);
                }
                Log.e(SQLitePlugin.class.getSimpleName(), "couldn't close database", e);
            }
        } else if (callbackContext != null) {
            callbackContext.success();
        }
    }

    /* access modifiers changed from: private */
    public void closeDatabaseNow(String str) {
        SQLiteAndroidDatabase sQLiteAndroidDatabase;
        DBRunner dBRunner = this.dbrmap.get(str);
        if (dBRunner != null && (sQLiteAndroidDatabase = dBRunner.mydb) != null) {
            sQLiteAndroidDatabase.closeDatabaseNow();
        }
    }

    private void deleteDatabase(String str, CallbackContext callbackContext) {
        DBRunner dBRunner = this.dbrmap.get(str);
        if (dBRunner != null) {
            try {
                dBRunner.q.put(new DBQuery(true, callbackContext));
            } catch (Exception e) {
                if (callbackContext != null) {
                    callbackContext.error("couldn't close database" + e);
                }
                Log.e(SQLitePlugin.class.getSimpleName(), "couldn't close database", e);
            }
        } else if (deleteDatabaseNow(str)) {
            callbackContext.success();
        } else {
            callbackContext.error("couldn't delete database");
        }
    }

    /* access modifiers changed from: private */
    public boolean deleteDatabaseNow(String str) {
        try {
            return this.cordova.getActivity().deleteDatabase(this.cordova.getActivity().getDatabasePath(str).getAbsolutePath());
        } catch (Exception e) {
            Log.e(SQLitePlugin.class.getSimpleName(), "couldn't delete database", e);
            return false;
        }
    }

    private class DBRunner implements Runnable {
        private boolean bugWorkaround;
        final String dbname;
        SQLiteAndroidDatabase mydb;
        private boolean oldImpl;
        final CallbackContext openCbc;
        final BlockingQueue<DBQuery> q;

        DBRunner(String str, JSONObject jSONObject, CallbackContext callbackContext) {
            this.dbname = str;
            this.oldImpl = jSONObject.has("androidOldDatabaseImplementation");
            Log.v(SQLitePlugin.class.getSimpleName(), "Android db implementation: built-in android.database.sqlite package");
            this.bugWorkaround = this.oldImpl && jSONObject.has("androidBugWorkaround");
            if (this.bugWorkaround) {
                Log.v(SQLitePlugin.class.getSimpleName(), "Android db closing/locking workaround applied");
            }
            this.q = new LinkedBlockingQueue();
            this.openCbc = callbackContext;
        }

        public void run() {
            try {
                this.mydb = SQLitePlugin.this.openDatabase(this.dbname, this.openCbc, this.oldImpl);
                DBQuery dBQuery = null;
                try {
                    DBQuery take = this.q.take();
                    while (true) {
                        dBQuery = take;
                        if (dBQuery.stop) {
                            break;
                        }
                        this.mydb.executeSqlBatch(dBQuery.queries, dBQuery.jsonparams, dBQuery.cbc);
                        if (this.bugWorkaround && dBQuery.queries.length == 1 && dBQuery.queries[0] == "COMMIT") {
                            this.mydb.bugWorkaround();
                        }
                        take = this.q.take();
                    }
                } catch (Exception e) {
                    Log.e(SQLitePlugin.class.getSimpleName(), "unexpected error", e);
                }
                if (dBQuery != null && dBQuery.close) {
                    try {
                        SQLitePlugin.this.closeDatabaseNow(this.dbname);
                        SQLitePlugin.this.dbrmap.remove(this.dbname);
                        if (!dBQuery.delete) {
                            dBQuery.cbc.success();
                            return;
                        }
                        try {
                            if (SQLitePlugin.this.deleteDatabaseNow(this.dbname)) {
                                dBQuery.cbc.success();
                            } else {
                                dBQuery.cbc.error("couldn't delete database");
                            }
                        } catch (Exception e2) {
                            Log.e(SQLitePlugin.class.getSimpleName(), "couldn't delete database", e2);
                            CallbackContext callbackContext = dBQuery.cbc;
                            callbackContext.error("couldn't delete database: " + e2);
                        }
                    } catch (Exception e3) {
                        Log.e(SQLitePlugin.class.getSimpleName(), "couldn't close database", e3);
                        if (dBQuery.cbc != null) {
                            CallbackContext callbackContext2 = dBQuery.cbc;
                            callbackContext2.error("couldn't close database: " + e3);
                        }
                    }
                }
            } catch (Exception e4) {
                Log.e(SQLitePlugin.class.getSimpleName(), "unexpected error, stopping db thread", e4);
                SQLitePlugin.this.dbrmap.remove(this.dbname);
            }
        }
    }

    private final class DBQuery {
        final CallbackContext cbc;
        final boolean close;
        final boolean delete;
        final JSONArray[] jsonparams;
        final String[] queries;
        final boolean stop;

        DBQuery(String[] strArr, JSONArray[] jSONArrayArr, CallbackContext callbackContext) {
            this.stop = false;
            this.close = false;
            this.delete = false;
            this.queries = strArr;
            this.jsonparams = jSONArrayArr;
            this.cbc = callbackContext;
        }

        DBQuery(boolean z, CallbackContext callbackContext) {
            this.stop = true;
            this.close = true;
            this.delete = z;
            this.queries = null;
            this.jsonparams = null;
            this.cbc = callbackContext;
        }

        DBQuery() {
            this.stop = true;
            this.close = false;
            this.delete = false;
            this.queries = null;
            this.jsonparams = null;
            this.cbc = null;
        }
    }
}
