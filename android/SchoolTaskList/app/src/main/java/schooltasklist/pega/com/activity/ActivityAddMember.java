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
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.customview.ContactsCompletionView;
import schooltasklist.pega.com.customview.customedittext.FilteredArrayAdapter;
import schooltasklist.pega.com.model.Group;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivityAddMember extends Activity {

    private ContactsCompletionView cvCompletionView;
    private Group mGroupCurrent;
    private ArrayList<User> mUser;

    ArrayAdapter<User> mAdapter;
    private ImageView ivSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        initValueComponent();
        loadComponent();
        setEventComponent();
        updateComponent();
    }

    private void initValueComponent() {
        long groupID = 1;
        try {
            getAllUserFromServer(groupID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getAllUserFromServer(long groupID) throws JSONException {

        ManageConnection.getInstance().queryUserFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) {
                try {
                    mUser = MessageParse.queryUserResponse(jsonResponse);
//                    for (int i=0; i<mUser.size(); i++) {
//                        people[i] = mUser.get(i);
//                    }
                    mAdapter = new FilteredArrayAdapter<User>(ActivityAddMember.this, android.R.layout.simple_list_item_1, mUser) {
                        @Override
                        protected boolean keepObject(User obj, String mask) {
                            mask = mask.toLowerCase();
//                        String[] ls = mask.split(",");
//                        String maskCurrent = ls [0];
                            return obj.getFirstName().toLowerCase().startsWith(mask) || obj.getLastName().toLowerCase().startsWith(mask)
                                    || obj.getGrade().toLowerCase().equals(mask);
//                        boolean blAnother = true;
//                        int i = 1;
//                        for ( i=1; i<ls.length; i++) {
//                            String maskTemp = ls[i];
//                            blAnother = blAnother || obj.getId().toLowerCase().equals(maskTemp.trim());
//                        }
//                        return blAnother;
//                            return true;
//                        }
                        }
                    };
                    cvCompletionView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }}, groupID);

    }

    private void updateComponent() {
        int paddingTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        int paddingBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        int paddingLeft = cvCompletionView.getPaddingLeft();
        int paddingRight = cvCompletionView.getPaddingRight();
        cvCompletionView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        cvCompletionView.setThreshold(1);
    }

    private void setEventComponent() {
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
        ManageConnection.getInstance().addMemberFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) {
                try {
                    MessageParse.addMemberResponse(jsonResponse);
                    Log.d("TEST", "add OK");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 1, cvCompletionView.getObjects());  //mGroupCurrent.getId()
    }

    private void loadComponent() {
        ivSubmit = (ImageView) findViewById(R.id.iv_activityaddmember_submit);
        cvCompletionView = (ContactsCompletionView)findViewById(R.id.cv_activityaddmember_searchView);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity_add_member, menu);
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
