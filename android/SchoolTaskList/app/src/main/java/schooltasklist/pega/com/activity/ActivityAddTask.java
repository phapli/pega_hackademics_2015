package schooltasklist.pega.com.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import schooltasklist.pega.com.customview.ContactsCompletionView;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;
import schooltasklist.pega.com.utils.UtilFunctions;

public class ActivityAddTask extends Activity {

    ContactsCompletionView completionView;
    User[] people;
    ArrayAdapter<User> adapter;

    private TextView tvYear;
    private TextView tvMonth;
    private TextView tvDay;
    private TextView tvHour;
    private TextView tvMinute;

    private EditText etNameTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        loadComponent();
        people = new User[]{
                new User("Marshall Weir", "marshall@example.com"),
                new User("Margaret Smith", "margaret@example.com"),
                new User("Max Jordan", "max@example.com"),
                new User("Max Jordan1", "max@example.com"),
                new User("Max Jordan2", "max@example.com"),
                new User("Max Jordan3", "max@example.com"),
                new User("Max Jordan4", "max@example.com"),
                new User("Max Jordan5", "max@example.com"),
                new User("Meg Peterson", "meg@example.com"),
                new User("Amanda Johnson", "amanda@example.com"),
                new User("Terry Anderson", "terry@example.com")
        };

        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, people);

        completionView = (ContactsCompletionView)findViewById(R.id.cv_activityaddtask_searchView);
        completionView.setAdapter(adapter);

        updateComponent();
    }

    private void updateComponent() {

        int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        int paddingLeft = etNameTask.getPaddingLeft();
        int paddingRight = etNameTask.getPaddingRight();
        etNameTask.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        etNameTask.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etNameTask.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext_focus));

                }
                else {
                    etNameTask.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext));
                }
                int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                int paddingLeft = etNameTask.getPaddingLeft();
                int paddingRight = etNameTask.getPaddingRight();
                etNameTask.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        });

        tvYear.setText(UtilFunctions.applyBoldStyle("YEAR: ").append("2013"));
        tvMonth.setText(UtilFunctions.applyBoldStyle("MONTH: ").append("12"));
        tvDay.setText(UtilFunctions.applyBoldStyle("DAY: ").append("25"));
        tvHour.setText(UtilFunctions.applyBoldStyle("HOUR: ").append(String.valueOf(12)));
        tvMinute.setText(UtilFunctions.applyBoldStyle("MINUTE: ").append(String.valueOf(11)));


    }



    private void loadComponent() {
        tvDay = (TextView) findViewById(R.id.tv_activityaddtask_day);
        tvYear = (TextView) findViewById(R.id.tv_activityaddtask_year);
        tvMonth = (TextView) findViewById(R.id.tv_activityaddtask_month);
        tvHour = (TextView) findViewById(R.id.tv_activityaddtask_hour);
        tvMinute = (TextView) findViewById(R.id.tv_activityaddtask_minute);
        etNameTask = (EditText) findViewById(R.id.et_activityaddtask_nametask);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity_add_task, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
