package schooltasklist.pega.com.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import schooltasklist.pega.com.customview.ContactsCompletionView;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;

public class ActivityAddTask extends ActionBarActivity {

    ContactsCompletionView completionView;
    User[] people;
    ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        people = new User[]{
                new User("Marshall Weir", "marshall@example.com"),
                new User("Margaret Smith", "margaret@example.com"),
                new User("Max Jordan", "max@example.com"),
                new User("Meg Peterson", "meg@example.com"),
                new User("Amanda Johnson", "amanda@example.com"),
                new User("Terry Anderson", "terry@example.com")
        };

        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, people);

        completionView = (ContactsCompletionView)findViewById(R.id.searchView);
        completionView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_add_task, menu);
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
