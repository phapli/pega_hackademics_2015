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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import schooltasklist.pega.com.config.Configs;
import schooltasklist.pega.com.customview.ContactsCompletionView;
import schooltasklist.pega.com.customview.customedittext.FilteredArrayAdapter;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;
import schooltasklist.pega.com.utils.UtilFunctions;

public class ActivityAddTask extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    ContactsCompletionView cvCompletionView;
    User[] people;
    ArrayAdapter<User> adapter;

    private TextView tvYear;
    private TextView tvMonth;
    private TextView tvDay;
    private TextView tvHour;
    private TextView tvMinute;

    private EditText etNameTask;

    private LinearLayout llDate;
    private LinearLayout llTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        loadComponent();
        setEventForComponent();
        people = new User[]{
                new User("Marshall Weir", "marshall@example.com","1"),
                new User("Margaret Smith", "margaret@example.com","2"),
                new User("Max Jordan", "1max@example.com","3"),
                new User("Max Jordan1", "2max@example.com","4"),
                new User("Max Jordan2", "3max@example.com","5"),
                new User("Max Jordan3", "4max@example.com","6"),
                new User("Max Jordan4", "5max@example.com","7"),
                new User("Max Jordan5", "6max@example.com","8"),
                new User("Meg Peterson", "7meg@example.com","9"),
                new User("Amanda Johnson", "8amanda@example.com","10"),
                new User("Terry Anderson", "9terry@example.com","11")
        };

        adapter = new FilteredArrayAdapter<User>(this, android.R.layout.simple_list_item_1, people) {
            @Override
            protected boolean keepObject(User obj, String mask) {
                mask = mask.toLowerCase();
                String[] ls = mask.split(",");
                String maskCurrent = ls [0];
                boolean blFirst =  obj.getName().toLowerCase().startsWith(mask) || obj.getEmail().toLowerCase().startsWith(mask);
                boolean blAnother = true;
                int i = 1;
                for ( i=1; i<ls.length; i++) {
                    String maskTemp = ls[i];
                    blAnother = blAnother || obj.getId().toLowerCase().equals(maskTemp.trim());
                }
                return blAnother;
            }
        };


        cvCompletionView.setAdapter(adapter);


        updateComponent();
    }

    private void setEventForComponent() {
        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        llTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });

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

        cvCompletionView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cvCompletionView.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext_focus));

                }
                else {
                    cvCompletionView.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext));
                }
                int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                int paddingLeft = cvCompletionView.getPaddingLeft();
                int paddingRight = cvCompletionView.getPaddingRight();
                cvCompletionView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        });
    }

    private void showDateDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ActivityAddTask.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void showTimeDialog() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dpd = TimePickerDialog.newInstance(
                ActivityAddTask.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                Configs.IS_24H_MODE
        );
        dpd.show(getFragmentManager(), "Timepickerdialog");
    }


    private void updateComponent() {

        int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        int paddingLeft = etNameTask.getPaddingLeft();
        int paddingRight = etNameTask.getPaddingRight();
        etNameTask.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        cvCompletionView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        cvCompletionView.allowDuplicates(false);
        cvCompletionView.setThreshold(0);

    }



    private void loadComponent() {
        tvDay = (TextView) findViewById(R.id.tv_activityaddtask_day);
        tvYear = (TextView) findViewById(R.id.tv_activityaddtask_year);
        tvMonth = (TextView) findViewById(R.id.tv_activityaddtask_month);
        tvHour = (TextView) findViewById(R.id.tv_activityaddtask_hour);
        tvMinute = (TextView) findViewById(R.id.tv_activityaddtask_minute);
        etNameTask = (EditText) findViewById(R.id.et_activityaddtask_nametask);
        llDate = (LinearLayout) findViewById(R.id.ll_activity_addtask_dateholder);
        llTime = (LinearLayout) findViewById(R.id.ll_activity_addtask_timeholder);
        cvCompletionView = (ContactsCompletionView)findViewById(R.id.cv_activityaddtask_searchView);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        updateDateInfoView(year, monthOfYear+1, dayOfMonth);
    }

    private void updateDateInfoView(int year, int monthOfYear, int dayOfMonth) {
        tvYear.setText(UtilFunctions.applyBoldStyle("YEAR: ").append(String.valueOf(year)));
        tvMonth.setText(UtilFunctions.applyBoldStyle("MONTH: ").append(String.valueOf(monthOfYear)));
        tvDay.setText(UtilFunctions.applyBoldStyle("DAY: ").append(String.valueOf(dayOfMonth)));

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        updateTimeInfoView(hourOfDay, minute);
    }

    private void updateTimeInfoView(int hourOfDay, int minute) {
        tvHour.setText(UtilFunctions.applyBoldStyle("HOUR: ").append(String.valueOf(hourOfDay)));
        tvMinute.setText(UtilFunctions.applyBoldStyle("MINUTE: ").append(String.valueOf(minute)));
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
