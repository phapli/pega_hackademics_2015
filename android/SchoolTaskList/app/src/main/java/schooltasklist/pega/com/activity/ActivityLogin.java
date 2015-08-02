package schooltasklist.pega.com.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import schooltasklist.pega.com.connection.ConnectServer;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;


public class ActivityLogin extends ActionBarActivity {
    private EditText et_username;
    private EditText et_password;
    private TextView tv_login;
    private TextView tv_forgot;

    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
        Log.d("TEST", "login successful!");
    }

    private void loadComponent() {
        et_username = (EditText) findViewById(R.id.et_activitylogin_username);
        et_password = (EditText) findViewById(R.id.et_activitylogin_password);

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
