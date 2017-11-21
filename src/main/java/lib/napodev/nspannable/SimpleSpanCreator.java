package lib.napodev.nspannable;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by opannapo on 11/19/17.
 */
public class SimpleSpanCreator {
    private String textFull;
    private String[] textToFind;
    private int color[];
    private boolean isBold = false;
    private boolean isItalic = false;
    private SpanClickListener clickListener;

    private int colorDefault = Color.parseColor("#000000");

    public SimpleSpanCreator() {
    }

    public SimpleSpanCreator ofText(String textFull) {
        this.textFull = textFull;
        return this;
    }

    public SimpleSpanCreator find(String... textToFind) {
        this.textToFind = textToFind;
        return this;
    }

    public SimpleSpanCreator spandColor(int... color) {
        this.color = color;
        return this;
    }

    public SimpleSpanCreator setBold(boolean isBold) {
        this.isBold = isBold;
        return this;
    }

    public SimpleSpanCreator setItalic(boolean isItalic) {
        this.isItalic = isItalic;
        return this;
    }

    public SimpleSpanCreator setSpanClickListener(TextView tv, SpanClickListener clickListener) {
        this.clickListener = clickListener;
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public Spannable create() {
        Spannable wordtoSpan = new SpannableString(textFull);
        for (int i = 0; i < textToFind.length; i++) {
            int start = textFull.indexOf(textToFind[i]);
            int end = start + textToFind[i].length();

            if (this.clickListener != null) {
                final int finalI = i;
                wordtoSpan.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View v) {
                        clickListener.clicked(textToFind[finalI]);
                    }

                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }

            wordtoSpan.setSpan(new ForegroundColorSpan(color.length != textToFind.length ? colorDefault : color[i]), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            StyleSpan style = null;
            if (isBold) {
                style = new StyleSpan(isItalic ? Typeface.BOLD_ITALIC : Typeface.BOLD);
            } else if (isItalic) {
                style = new StyleSpan(Typeface.ITALIC);
            } else {
                style = new StyleSpan(Typeface.NORMAL);
            }
            wordtoSpan.setSpan(style, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        return wordtoSpan;
    }


}
