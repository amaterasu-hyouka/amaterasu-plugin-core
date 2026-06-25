package jp.amaterasu_hyouka.core.inventory;

import jp.amaterasu_hyouka.core.listener.CustomInventoryClickListener;

public class CustomInventoryPageHolder extends CustomInventoryHolder {
    private final int page;

    public CustomInventoryPageHolder(CustomInventoryClickListener handler, int page) {
        super(handler);
        this.page = page;
    }

    public int page(){
        return page;
    }

    public CustomInventoryPageHolder addPage(int delta){
        return new CustomInventoryPageHolder(handler(), page + delta);
    }
}
