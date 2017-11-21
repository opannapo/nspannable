package lib.napodev.nspannable;

import android.graphics.Color;

/**
 * Created by opannapo on 11/21/17.
 */

public class SpanObj {
    private String textToFind;
    private int color;
    private boolean isBold;
    private boolean isItalic;

    public SpanObj() {
        //default
        color = Color.BLACK;
        isBold = true;
        isItalic = false;
    }

    public String getTextToFind() {
        return textToFind;
    }

    public SpanObj find(String textToFind) {
        this.textToFind = textToFind;
        return this;
    }

    public int getColor() {
        return color;
    }

    public SpanObj color(int color) {
        this.color = color;
        return this;
    }

    public boolean isBold() {
        return isBold;
    }

    public SpanObj bold(boolean bold) {
        isBold = bold;
        return this;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public SpanObj italic(boolean italic) {
        isItalic = italic;
        return this;
    }
}
