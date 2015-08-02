package schooltasklist.pega.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import schooltasklist.pega.com.model.User;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by D.PHI on 8/2/2015.
 */
public class AdapterListMember extends BaseAdapter {

    private TextView tv_itemmember_name;
    private Context context;
    private List<User> list_user;

    public AdapterListMember(Context context,  List<User> objects) {
        this.context = context;
        this.list_user = objects;
    }

    @Override
    public int getCount() {
        return list_user.size();
    }

    @Override
    public Object getItem(int position) {
        return list_user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_member, parent, false);

        setUpView(rowView , position);

        return rowView;

    }
    public void setUpView (View view , int position)
    {
        tv_itemmember_name = (TextView) view.findViewById(R.id.tv_itemmember_name);
        tv_itemmember_name.setText(list_user.get(position).getFirstName() + " " + list_user.get(position).getLastName());
//        tv_itemmember_name.setOnTouchListener(new OnSwipeTouchListener(context)
//        {});
    }




}
