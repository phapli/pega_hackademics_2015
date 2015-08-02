package schooltasklist.pega.com.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.customview.ContactsCompletionView;
import schooltasklist.pega.com.customview.customedittext.FilteredArrayAdapter;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivityCreateGroup extends Activity {

    private EditText etNameGroup;
    private EditText etDesGroup;
    private User mUser;

    ArrayAdapter<User> mAdapter;
    private ImageView ivSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        initValueComponent();
        loadComponent();
        setEventComponent();
        updateComponent();
    }

    private void initValueComponent() {
        mUser = DataCurrent.user_current;


    }



    private void updateComponent() {
        int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        int paddingLeft = etNameGroup.getPaddingLeft();
        int paddingRight = etNameGroup.getPaddingRight();
        etNameGroup.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        etDesGroup.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);

    }

    private void setEventComponent() {
        etNameGroup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etNameGroup.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext_focus));

                }
                else {
                    etNameGroup.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext));
                }
                int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                int paddingLeft = etNameGroup.getPaddingLeft();
                int paddingRight = etNameGroup.getPaddingRight();
                etNameGroup.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        });

        etDesGroup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etDesGroup.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext_focus));

                }
                else {
                    etDesGroup.setBackground((Drawable)getResources().getDrawable(R.drawable.cv_background_edittext));
                }
                int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
                int paddingLeft = etDesGroup.getPaddingLeft();
                int paddingRight = etDesGroup.getPaddingRight();
                etDesGroup.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        });

        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    actionSubmitCode();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void actionSubmitCode() throws Exception {
        ManageConnection.getInstance().createGroupFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) {
                try {
                    MessageParse.createGroupResponse(jsonResponse);
                    Log.d("TEST", "add OK");
                    ActivityCreateGroup.this.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, etNameGroup.getText().toString(), etDesGroup.getText().toString(), mUser.getId());  //mGroupCurrent.getId()
    }

    private void loadComponent() {
        etNameGroup = (EditText) findViewById(R.id.et_activitycreategroup_nametask);
        etDesGroup = (EditText) findViewById(R.id.et_activitycreategroup_descrip);
        ivSubmit = (ImageView) findViewById(R.id.iv_activitycreategroup_submit);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity_create_group, menu);
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
