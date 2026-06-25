package jp.amaterasu_hyouka.core.inventory;

public enum InventoryRow {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final int rows;

    InventoryRow(int rows) {
        this.rows = rows;
    }

    public int rows() {
        return rows;
    }

    public int size() {
        return rows * 9;
    }
}
