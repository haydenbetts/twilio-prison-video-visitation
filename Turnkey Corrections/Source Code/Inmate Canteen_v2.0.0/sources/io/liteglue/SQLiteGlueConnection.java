package io.liteglue;

import java.sql.SQLException;

class SQLiteGlueConnection implements SQLiteConnection {
    /* access modifiers changed from: private */
    public SQLDatabaseHandle db = null;

    public SQLiteGlueConnection(String str, int i) throws SQLException {
        if (str != null) {
            SQLGDatabaseHandle sQLGDatabaseHandle = new SQLGDatabaseHandle(str, i);
            int open = sQLGDatabaseHandle.open();
            if (open == 0) {
                this.db = sQLGDatabaseHandle;
                return;
            }
            throw new SQLException("sqlite3_open_v2 failure: " + this.db.getLastErrorMessage(), "failure", open);
        }
        throw new SQLException("null argument", "failed", 21);
    }

    public void dispose() throws SQLException {
        SQLDatabaseHandle sQLDatabaseHandle = this.db;
        if (sQLDatabaseHandle != null) {
            int close = sQLDatabaseHandle.close();
            if (close == 0) {
                this.db = null;
                return;
            }
            throw new SQLException("sqlite3_close failure: " + this.db.getLastErrorMessage(), "failure", close);
        }
        throw new SQLException("already disposed", "failed", 21);
    }

    public void keyNativeString(String str) throws SQLException {
        SQLDatabaseHandle sQLDatabaseHandle = this.db;
        if (sQLDatabaseHandle != null) {
            int keyNativeString = sQLDatabaseHandle.keyNativeString(str);
            if (keyNativeString != 0) {
                throw new SQLException("sqlite3_key failure: " + this.db.getLastErrorMessage(), "failure", keyNativeString);
            }
            return;
        }
        throw new SQLException("already disposed", "failed", 21);
    }

    public SQLiteStatement prepareStatement(String str) throws SQLException {
        if (this.db == null) {
            throw new SQLException("already disposed", "failed", 21);
        } else if (str != null) {
            SQLGStatement sQLGStatement = new SQLGStatement(str);
            int prepare = sQLGStatement.prepare();
            if (prepare == 0) {
                return sQLGStatement;
            }
            throw new SQLException("sqlite3_prepare_v2 failure: " + this.db.getLastErrorMessage(), "failure", prepare);
        } else {
            throw new SQLException("null argument", "failed", 21);
        }
    }

    public long getLastInsertRowid() throws SQLException {
        SQLDatabaseHandle sQLDatabaseHandle = this.db;
        if (sQLDatabaseHandle != null) {
            return sQLDatabaseHandle.getLastInsertRowid();
        }
        throw new SQLException("already disposed", "failed", 21);
    }

    public int getTotalChanges() throws SQLException {
        SQLDatabaseHandle sQLDatabaseHandle = this.db;
        if (sQLDatabaseHandle != null) {
            return sQLDatabaseHandle.getTotalChanges();
        }
        throw new SQLException("already disposed", "failed", 21);
    }

    private class SQLGStatement implements SQLiteStatement {
        private int columnCount = 0;
        private boolean hasRow = false;
        private String sql = null;
        private SQLStatementHandle sthandle = null;

        SQLGStatement(String str) {
            this.sql = str;
            this.sthandle = SQLiteGlueConnection.this.db.newStatementHandle(str);
        }

        /* access modifiers changed from: package-private */
        public int prepare() {
            return this.sthandle.prepare();
        }

        public void bindDouble(int i, double d) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                int bindDouble = sQLStatementHandle.bindDouble(i, d);
                if (bindDouble != 0) {
                    throw new SQLException("sqlite3_bind_double failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", bindDouble);
                }
                return;
            }
            throw new SQLException("already disposed", "failed", 21);
        }

        public void bindInteger(int i, int i2) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                int bindInteger = sQLStatementHandle.bindInteger(i, i2);
                if (bindInteger != 0) {
                    throw new SQLException("sqlite3_bind_int failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", bindInteger);
                }
                return;
            }
            throw new SQLException("already disposed", "failed", 21);
        }

        public void bindLong(int i, long j) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                int bindLong = sQLStatementHandle.bindLong(i, j);
                if (bindLong != 0) {
                    throw new SQLException("sqlite3_bind_int64 (long) failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", bindLong);
                }
                return;
            }
            throw new SQLException("already disposed", "failed", 21);
        }

        public void bindNull(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                int bindNull = sQLStatementHandle.bindNull(i);
                if (bindNull != 0) {
                    throw new SQLException("sqlite3_bind_null failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", bindNull);
                }
                return;
            }
            throw new SQLException("already disposed", "failed", 21);
        }

        public void bindTextNativeString(int i, String str) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (str != null) {
                int bindTextNativeString = sQLStatementHandle.bindTextNativeString(i, str);
                if (bindTextNativeString != 0) {
                    throw new SQLException("sqlite3_bind_text failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", bindTextNativeString);
                }
            } else {
                throw new SQLException("null argument", "failed", 21);
            }
        }

        public boolean step() throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                int step = sQLStatementHandle.step();
                if (step == 0 || step == 100 || step == 101) {
                    this.hasRow = step == 100;
                    if (this.hasRow) {
                        this.columnCount = this.sthandle.getColumnCount();
                    } else {
                        this.columnCount = 0;
                    }
                    return this.hasRow;
                }
                throw new SQLException("sqlite3_step failure: " + SQLiteGlueConnection.this.db.getLastErrorMessage(), "failure", step);
            }
            throw new SQLException("already disposed", "failed", 21);
        }

        public int getColumnCount() throws SQLException {
            if (this.sthandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (this.hasRow) {
                return this.columnCount;
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public String getColumnName(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnName(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public int getColumnType(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnType(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public double getColumnDouble(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnDouble(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public int getColumnInteger(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnInteger(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public long getColumnLong(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnLong(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public String getColumnTextNativeString(int i) throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle == null) {
                throw new SQLException("already disposed", "failed", 21);
            } else if (!this.hasRow) {
                throw new SQLException("no result available", "failed", 21);
            } else if (i >= 0 && i < this.columnCount) {
                return sQLStatementHandle.getColumnTextNativeString(i);
            } else {
                throw new SQLException("no result available", "failed", 21);
            }
        }

        public void dispose() throws SQLException {
            SQLStatementHandle sQLStatementHandle = this.sthandle;
            if (sQLStatementHandle != null) {
                sQLStatementHandle.finish();
                this.sthandle = null;
                return;
            }
            throw new SQLException("already disposed", "failed", 21);
        }
    }
}
