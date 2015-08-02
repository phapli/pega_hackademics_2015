package schooltasklist.pega.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import schooltasklist.pega.com.config.Configs;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivitySeting extends Activity implements AdapterView.OnItemSelectedListener {


    private ImageView iv_push ;
    private Spinner spinner_remind;
    private ImageView iv_logout;
    private boolean isPush = false;
    private int timeRemind = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        iv_push = (ImageView) findViewById(R.id.iv_activitysetting_push);
        iv_logout = (ImageView) findViewById(R.id.iv_activitysettig_logout);
        isPush = getFirstPreference(Configs.PUSH);
        timeRemind = getTimeReminder(Configs.TIME_REMINDER);
        if(isPush){
            iv_push.setImageResource(R.drawable.on);
        }else{
            iv_push.setImageResource(R.drawable.off);
        }
        iv_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPush)
                {
                    isPush = false;
                    iv_push.setImageResource(R.drawable.off);
                } else
                {
                    isPush = true;
                    iv_push.setImageResource(R.drawable.on);
                }
            }
        });

        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ActivitySeting.this,ActivityLogin.class);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(newIntent);
                finish();

            }
        });

        List<String> categories = new ArrayList<String>();
        categories.add("3 hours");
        categories.add("5 hours");
        categories.add("10 hours");
        categories.add("15 house");
        categories.add("1 day");
        categories.add("2 days");
        spinner_remind = (Spinner) findViewById(R.id.spiner_remind);
        spinner_remind.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivitySeting.this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_remind.setAdapter(adapter);



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        timeRemind = getTimeRemind(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        savePreference (isPush , timeRemind);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_seting, menu);
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
    public void savePreference (boolean first , int second)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Configs.MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(Configs.PUSH,first);
        edit.putInt(Configs.TIME_REMINDER, second);
        edit.commit();
    }
    public boolean getFirstPreference ( String key )
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Configs.MY_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }
    public int getTimeReminder ( String key )
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Configs.MY_PREFERENCES, Context.MODE_PRIVATE);

        return sharedPreferences.getInt (key, 0);
    }

    public int getTimeRemind(int a) {
        switch (a){
            case 1: return 3;
            case 2: return 5;
            case 3: return 10;
            case 4: return 15;
            case 5: return 24;
            case 6: return 48;
            default: return 48;
        }
    }
}
