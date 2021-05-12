package fm.liveswitch;

public class LayoutTable {
    private int _cellHeight;
    private int _cellWidth;
    private int _columnCount;
    private int _rowCount;

    public int getCellHeight() {
        return this._cellHeight;
    }

    public int getCellWidth() {
        return this._cellWidth;
    }

    public int getColumnCount() {
        return this._columnCount;
    }

    public int getRowCount() {
        return this._rowCount;
    }

    public LayoutTable(int i, int i2, int i3, int i4) {
        setColumnCount(i);
        setRowCount(i2);
        setCellWidth(i3);
        setCellHeight(i4);
    }

    public void setCellHeight(int i) {
        this._cellHeight = i;
    }

    public void setCellWidth(int i) {
        this._cellWidth = i;
    }

    public void setColumnCount(int i) {
        this._columnCount = i;
    }

    public void setRowCount(int i) {
        this._rowCount = i;
    }
}
