package schooltasklist.pega.com.customview.customedittext;

import android.content.res.ColorStateList;
import android.text.style.TextAppearanceSpan;

/**
 * Subclass of TextAppearanceSpan just to work with how Spans get detected
 */
public class HintSpan extends TextAppearanceSpan {
    public HintSpan(String family, int style, int size, ColorStateList color, ColorStateList linkColor) {
        super(family, style, size, color, linkColor);
    }
}
