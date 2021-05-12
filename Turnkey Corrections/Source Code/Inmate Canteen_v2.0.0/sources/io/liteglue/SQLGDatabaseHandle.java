package io.liteglue;

class SQLGDatabaseHandle implements SQLDatabaseHandle {
    String dbfilename = null;
    /* access modifiers changed from: private */
    public long dbhandle = 0;
    int openflags = 0;

    public SQLGDatabaseHandle(String str, int i) {
        this.dbfilename = str;
        this.openflags = i;
    }

    public int open() {
        String str = this.dbfilename;
        if (str == null || this.dbhandle != 0) {
            return 21;
        }
        long sqlc_db_open = SQLiteNative.sqlc_db_open(str, this.openflags);
        if (sqlc_db_open < 0) {
            return (int) (-sqlc_db_open);
        }
        this.dbhandle = sqlc_db_open;
        return 0;
    }

    public int keyNativeString(String str) {
        long j = this.dbhandle;
        if (j == 0) {
            return 21;
        }
        return SQLiteNative.sqlc_db_key_native_string(j, str);
    }

    public int close() {
        long j = this.dbhandle;
        if (j == 0) {
            return 21;
        }
        return SQLiteNative.sqlc_db_close(j);
    }

    public boolean isOpen() {
        return this.dbhandle != 0;
    }

    public SQLStatementHandle newStatementHandle(String str) {
        if (this.dbhandle == 0) {
            return null;
        }
        return new SQLGStatementHandle(str);
    }

    public long getLastInsertRowid() {
        long j = this.dbhandle;
        if (j == 0) {
            return -1;
        }
        return SQLiteNative.sqlc_db_last_insert_rowid(j);
    }

    public int getTotalChanges() {
        long j = this.dbhandle;
        if (j == 0) {
            return -1;
        }
        return SQLiteNative.sqlc_db_total_changes(j);
    }

    public String getLastErrorMessage() {
        long j = this.dbhandle;
        if (j == 0) {
            return null;
        }
        return SQLiteNative.sqlc_db_errmsg_native(j);
    }

    private class SQLGStatementHandle implements SQLStatementHandle {
        String sql;
        private long sthandle;

        private SQLGStatementHandle(String str) {
            this.sql = null;
            this.sthandle = 0;
            this.sql = str;
        }

        public int prepare() {
            if (this.sql == null || this.sthandle != 0) {
                return 21;
            }
            long sqlc_db_prepare_st = SQLiteNative.sqlc_db_prepare_st(SQLGDatabaseHandle.this.dbhandle, this.sql);
            if (sqlc_db_prepare_st < 0) {
                return (int) (-sqlc_db_prepare_st);
            }
            this.sthandle = sqlc_db_prepare_st;
            return 0;
        }

        public int bindDouble(int i, double d) {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_bind_double(j, i, d);
        }

        public int bindInteger(int i, int i2) {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_bind_int(j, i, i2);
        }

        public int bindLong(int i, long j) {
            long j2 = this.sthandle;
            if (j2 == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_bind_long(j2, i, j);
        }

        public int bindNull(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_bind_null(j, i);
        }

        public int bindTextNativeString(int i, String str) {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_bind_text_native(j, i, str);
        }

        public int step() {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            return SQLiteNative.sqlc_st_step(j);
        }

        public int getColumnCount() {
            long j = this.sthandle;
            if (j == 0) {
                return -1;
            }
            return SQLiteNative.sqlc_st_column_count(j);
        }

        public String getColumnName(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return null;
            }
            return SQLiteNative.sqlc_st_column_name(j, i);
        }

        public int getColumnType(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return -1;
            }
            return SQLiteNative.sqlc_st_column_type(j, i);
        }

        public double getColumnDouble(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return -1.0d;
            }
            return SQLiteNative.sqlc_st_column_double(j, i);
        }

        public int getColumnInteger(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return -1;
            }
            return SQLiteNative.sqlc_st_column_int(j, i);
        }

        public long getColumnLong(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return -1;
            }
            return SQLiteNative.sqlc_st_column_long(j, i);
        }

        public String getColumnTextNativeString(int i) {
            long j = this.sthandle;
            if (j == 0) {
                return null;
            }
            return SQLiteNative.sqlc_st_column_text_native(j, i);
        }

        public int finish() {
            long j = this.sthandle;
            if (j == 0) {
                return 21;
            }
            this.sql = null;
            this.sthandle = 0;
            return SQLiteNative.sqlc_st_finish(j);
        }
    }
}
