package schooltasklist.pega.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.model.Task;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;


public class ActivityLogin extends Activity {
    private EditText et_username;
    private EditText et_password;
    private TextView tv_login;
    private TextView tv_forgot;

    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ManageConnection.getInstance().testConnection(new IOnGetDataFromServerComplete() {
//            @Override
//            public void onGetDataComplete() {
//                Log.d("TEST", "test callback");
//            }
//        });

        loadComponent();
        setEventComponent();
        updateComponent();


    }

    private void updateComponent() {

    }

    private void setEventComponent() {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    actionLogin();
                    //testFunction();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void testFunction() throws JSONException {
        //test getAllTask Function
        String id = "ST0000001";
        ManageConnection.getInstance().getAllTaskFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) {
                //processReceiDataServerCom(jsonResponse);
                try {
                    ArrayList<Task> tasks = MessageParse.getAllTaskResponse(jsonResponse);
                    if (tasks != null) {
                        Log.d("TEST", "successful!");
                    } else {
                        Log.d("TEST", "unsuccessful!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, id);
    }




    private void actionLogin() throws JSONException {
        ManageConnection.getInstance().loginFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) {
                processReceiDataServer(jsonResponse);
            }
        },      et_username.getText().toString().trim(),
                et_password.getText().toString().trim());
    }

    private void processReceiDataServer(JSONObject jsonResponse) {
        mUser = MessageParse.loginResponseParse(jsonResponse);
        if (mUser!= null) {
            actionLoginSuccess();
        }
        else {
            actionLoginUnsuccess();
        }
    }

    private void actionLoginUnsuccess() {
        Log.d("TEST", "login unsuccessful!");
    }

    private void actionLoginSuccess() {
        Intent intent  = new Intent(ActivityLogin.this, ActivityMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        DataCurrent.user_current = mUser;
        startActivity(intent);
        finish();
    }

    private void loadComponent() {
        et_username = (EditText) findViewById(R.id.et_activitylogin_username);
        et_password = (EditText) findViewById(R.id.et_activitylogin_password);

        et_username.setText("ST0000001");
        et_password.setText("123456");

        tv_login = (TextView) findViewById(R.id.tv_activitylogin_login);
        tv_forgot = (TextView) findViewById(R.id.tv_activitylogin_forgot);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






}
