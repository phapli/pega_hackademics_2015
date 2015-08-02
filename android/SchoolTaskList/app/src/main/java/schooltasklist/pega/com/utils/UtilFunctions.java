package schooltasklist.pega.com.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.ViewConfiguration;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tran on 8/1/2015.
 */
public class UtilFunctions {
    public static SpannableStringBuilder applyBoldStyle(String text) {
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }
      /**
     ﻿   * set always show Action Overflow if device has hardware option key
     ﻿   *
     ﻿   * @param context
     ﻿   */
    public void setAlwaysShowActionOverflow(Context context) {
        try {
             ViewConfiguration config = ViewConfiguration.get(context);
             java.lang.reflect.Field menuKeyField = ViewConfiguration.class
             .getDeclaredField("sHasPermanentMenuKey");
             if (menuKeyField != null) {
                 menuKeyField.setAccessible(true);
                 menuKeyField.setBoolean(config, false);
                }
              } catch (Exception e) {
                 e.printStackTrace();
        }
    }

    public void showToast(Context context, String message) {
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
     }

    public static Date formatDate(String deadlineString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" +
                    "" +
                    "-MM-dd HH:mm:ss");
            Date result = sdf.parse(deadlineString);
            return result;
        }catch (java.text.ParseException e) {
        }
        return null;
    }

    public static String formatDate(Date deadline) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(deadline);
        return result;
    }
}
