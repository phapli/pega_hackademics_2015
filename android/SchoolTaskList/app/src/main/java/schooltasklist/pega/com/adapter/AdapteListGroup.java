package schooltasklist.pega.com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import schooltasklist.pega.com.model.Group;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by D.PHI on 8/2/2015.
 */
public class AdapteListGroup extends BaseAdapter{

    private TextView tv_itemgroup_name;
    private TextView tv_itemgroup_members;

    private Context context;
    private List<Group> list_group;
    public AdapteListGroup (Context context, List<Group> group)
    {
        this.context = context;
        this.list_group = group;
    }

    @Override
    public int getCount() {
        return list_group.size();
    }

    @Override
    public Object getItem(int position) {
        return list_group.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_group, parent, false);

        setUpView(rowView , position);


        return rowView;
    }

    public void setUpView(View view, int position )
    {
        tv_itemgroup_name = (TextView) view.findViewById(R.id.tv_itemgroup_name);
        tv_itemgroup_members = (TextView) view.findViewById(R.id.tv_itemgroup_members);
        Log.d("bbbbbbbbbbb" , "" + position  +" list_group "+ list_group.get(position).getName());
        tv_itemgroup_name.setText(list_group.get(position).getName());
        tv_itemgroup_members.setText(list_group.get(position).getDescription());
    }

}
