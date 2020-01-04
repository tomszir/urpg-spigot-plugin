package xyz.tomszir.urpg.enums;

public enum ItemMenuSize {
    ONE_ROW(9),
    TWO_ROWS(18),
    THREE_ROWS(27),
    FOUR_ROWS(36),
    FIVE_ROWS(45),
    SIX_ROWS(34);

    private int size;

    ItemMenuSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
