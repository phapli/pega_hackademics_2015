package schooltasklist.pega.com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import schooltasklist.pega.com.fragment.FragmentGroup;
import schooltasklist.pega.com.fragment.FragmentTask;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivityGroup extends FragmentActivity {
    private ImageView iv_setting;
    private ImageView iv_add;
    private ImageView iv_task;
    private ImageView iv_member;
    private TextView tv_groupname;

    private boolean isTask =  true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        iv_setting = (ImageView) findViewById(R.id.iv_activitygroup_setting);
        iv_add = (ImageView) findViewById(R.id.iv_activitygroup_add);
        iv_task = (ImageView) findViewById(R.id.iv_activitygroup_task);
        iv_member = (ImageView) findViewById(R.id.iv_activitygroup_member);
        tv_groupname = (TextView) findViewById(R.id.tv_activitygroup_title);
        if(isTask) {
            iv_task.setImageResource(R.drawable.leftdeactive);
            iv_member.setImageResource(R.drawable.memberrightactive);

        } else {
            iv_task.setImageResource(R.drawable.leftactive);
            iv_member.setImageResource(R.drawable.memberrightdeactive);

        }
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentTask fragment = new FragmentTask();
            transaction.replace(R.id.fragment_group_content, fragment);
            transaction.commit();
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
                    iv_member.setImageResource(R.drawable.memberrightactive);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    FragmentTask fragment = new FragmentTask();
                    transaction.replace(R.id.fragment_group_content, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    isTask = true;
                }
            }
        });
        iv_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTask)
                {}
                else
                {
                    iv_task.setImageResource(R.drawable.leftactive);
                    iv_member.setImageResource(R.drawable.memberrightdeactive);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    FragmentGroup fragment = new FragmentGroup();
                    transaction.replace(R.id.fragment_group_content, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    isTask = false;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_group, menu);
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
