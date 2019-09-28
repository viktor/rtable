package com.viktor.rtable.components;

/**
 * converts width (float) to dp units.
 * headerText is the header of the column.
 */
public class ColumnHeader {

    private float width;
    private String headerText;

    public ColumnHeader(float width, String headerText) {
        this.width = width;
        this.headerText = headerText;
    }

    public float getWidth() {
        return width;
    }

    public String getHeaderText() {
        return headerText;
    }
}
