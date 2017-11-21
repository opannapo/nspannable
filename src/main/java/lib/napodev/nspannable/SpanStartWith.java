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
public class SpanStartWith {
    private String textFull;
    private String startText;
    private int color = Color.parseColor("#000000");
    private boolean isBold = false;
    private boolean isItalic = false;
    private SpanClickListener clickListener;

    public SpanStartWith() {
    }

    public SpanStartWith ofText(String textFull) {
        this.textFull = textFull;
        return this;
    }

    public SpanStartWith startWith(String textToFind) {
        this.startText = textToFind;
        return this;
    }

    public SpanStartWith spandColor(int color) {
        this.color = color;
        return this;
    }

    public SpanStartWith setBold(boolean isBold) {
        this.isBold = isBold;
        return this;
    }

    public SpanStartWith setItalic(boolean isItalic) {
        this.isItalic = isItalic;
        return this;
    }

    public SpanStartWith setSpanClickListener(TextView tv, SpanClickListener clickListener) {
        this.clickListener = clickListener;
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public Spannable create() {
        Spannable wordtoSpan = new SpannableString(textFull);
        final String[] texts = textFull.split(" ");


        for (int i = 0; i < texts.length; i++) {
            if (texts[i].startsWith(startText)) {
                int start = textFull.indexOf(texts[i]);
                int end = start + texts[i].length();

                if (this.clickListener != null) {
                    final int finalI = i;
                    wordtoSpan.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View v) {
                            clickListener.clicked(texts[finalI]);
                        }

                        public void updateDrawState(TextPaint ds) {
                            ds.setUnderlineText(false);
                        }
                    }, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                }

                wordtoSpan.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

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
        }


        return wordtoSpan;
    }


}
