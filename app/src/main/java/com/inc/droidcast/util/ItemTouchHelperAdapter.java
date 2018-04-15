package com.inc.droidcast.util;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPostion);
    void onItemDismiss(int position);
}

