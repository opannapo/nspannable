package lib.napodev.nspannable;

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
public class ObjSpanCreator {
    private String textFull;
    private SpanObj[] spanObjs;
    private SpanClickListener clickListener;


    public ObjSpanCreator() {
    }

    public ObjSpanCreator ofText(String textFull) {
        this.textFull = textFull;
        return this;
    }

    public ObjSpanCreator find(SpanObj... spanObjs) {
        this.spanObjs = spanObjs;
        return this;
    }

    public ObjSpanCreator setSpanClickListener(TextView tv, SpanClickListener clickListener) {
        this.clickListener = clickListener;
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public Spannable create() {
        Spannable wordtoSpan = new SpannableString(textFull);
        for (int i = 0; i < spanObjs.length; i++) {
            final SpanObj obj = spanObjs[i];
            int start = textFull.indexOf(obj.getTextToFind());
            int end = start + obj.getTextToFind().length();

            if (this.clickListener != null) {
                wordtoSpan.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View v) {
                        clickListener.clicked(obj.getTextToFind());
                    }

                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }

            wordtoSpan.setSpan(new ForegroundColorSpan(obj.getColor()),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            StyleSpan style = null;
            if (obj.isBold()) {
                style = new StyleSpan(obj.isItalic() ? Typeface.BOLD_ITALIC : Typeface.BOLD);
            } else if (obj.isItalic()) {
                style = new StyleSpan(Typeface.ITALIC);
            } else {
                style = new StyleSpan(Typeface.NORMAL);
            }
            wordtoSpan.setSpan(style, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        return wordtoSpan;
    }


}
