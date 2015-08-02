package schooltasklist.pega.com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.fragment.FragmentGroup;
import schooltasklist.pega.com.fragment.FragmentTask;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivityMain extends FragmentActivity {

    private ImageView iv_setting;
    private ImageView iv_add;
    private ImageView iv_task;
    private ImageView iv_group;
    private TextView  tv_username;
    private boolean isTask =  true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_setting = (ImageView) findViewById(R.id.iv_activitymain_setting);
        iv_add = (ImageView) findViewById(R.id.iv_activitymain_add);
        iv_task = (ImageView) findViewById(R.id.iv_activitymain_task);
        iv_group = (ImageView) findViewById(R.id.iv_activitymain_group);
        tv_username = (TextView) findViewById(R.id.tv_activitymain_title);
        tv_username.setText(DataCurrent.user_current.getFirstName() + DataCurrent.user_current.getLastName());
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentTask fragment = new FragmentTask();
            transaction.replace(R.id.fragment_main_content, fragment);
            transaction.commit();
        }
        if(isTask)
        {

            iv_task.setImageResource(R.drawable.leftdeactive);
            iv_group.setImageResource(R.drawable.rightdeactive);

        }
        else
        {
            iv_task.setImageResource(R.drawable.leftactive);
            iv_group.setImageResource(R.drawable.rightactive);
        }



        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTask)
                {}
                else
                {
                    iv_task.setImageResource(R.drawable.leftdeactive);
                    iv_group.setImageResource(R.drawable.rightdeactive);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    FragmentTask fragment = new FragmentTask();
                    transaction.replace(R.id.fragment_main_content, fragment);
                    transaction.commit();
                    isTask = true;
                }
            }
        });
        iv_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTask)
                {}
                else
                {
                    iv_task.setImageResource(R.drawable.leftactive);
                    iv_group.setImageResource(R.drawable.rightactive);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    FragmentGroup fragment = new FragmentGroup();
                    transaction.replace(R.id.fragment_main_content, fragment);
                    transaction.commit();
                    isTask = false;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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
