package jp.amaterasu_hyouka.core.inventory;

public final class InventoryPageLayout {
    private final int[] contentSlots;
    private final int prevSlot;
    private final int nextSlot;

    public InventoryPageLayout(int[] contentSlots, int prevSlot, int nextSlot) {
        this.contentSlots = contentSlots;
        this.prevSlot = prevSlot;
        this.nextSlot = nextSlot;
    }

    public int[] contentSlots() {
        return contentSlots;
    }

    public int prevSlot() {
        return prevSlot;
    }

    public int nextSlot() {
        return nextSlot;
    }
}
