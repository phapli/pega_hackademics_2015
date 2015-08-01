package schooltasklist.pega.com.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import schooltasklist.pega.com.customview.customedittext.TokenCompleteTextView;
import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by Tran on 8/1/2015.
 */
public class ContactsCompletionView extends TokenCompleteTextView<User> {


    public ContactsCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View getViewForObject(User user) {
        LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout)l.inflate(R.layout.cv_contact_token, (ViewGroup)ContactsCompletionView.this.getParent(), false);
        ((TextView)view.findViewById(R.id.name)).setText(user.getEmail());
        return view;
    }

    @Override
    protected User defaultObject(String completionText) {
        int index = completionText.indexOf('@');
        if (index == -1) {
            return new User(completionText, completionText.replace(" ", "") + "@example.com","-1");
        } else {
            return new User(completionText.substring(0, index), completionText,"-1");
        }
    }


}
