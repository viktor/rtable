package com.viktor.rtable.events;

import android.view.View;

/**
 * Program your click event here.
 */
public interface OnRowClicked {

    /**
     * delegates the click event View object and also shares
     * the rowNumber that was selected.
     *
     * @param view row layout object
     * @param rowNumber starts from 1, this number represents the row Num.
     *
     */
    void onClick(View view, int rowNumber);

}
