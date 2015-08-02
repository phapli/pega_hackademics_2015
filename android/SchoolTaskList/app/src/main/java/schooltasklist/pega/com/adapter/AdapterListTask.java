package schooltasklist.pega.com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import schooltasklist.pega.com.model.Task;
import schooltasklist.pega.com.schooltasklist.R;
import schooltasklist.pega.com.utils.UtilFunctions;

/**tv_itemtask_datetime_deadline
 * Created by D.PHI on 8/2/2015.
 */
public class AdapterListTask extends BaseAdapter {

    private TextView tv_title;
    private ImageView iv_done;
    private TextView tv_deadline;
    private TextView tv_groupname;
    private LinearLayout layoutshowhide;
    private Context context;
    private int resource;
    private List<Task> list;
    private boolean check = false;


    public AdapterListTask (Context context,  List<Task> list)
    {
        this.context = context;
        this.list = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_task, parent, false);

        setUpView(rowView , position);
        setEvenView(rowView);


        return rowView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private void setUpView(View view , int positon)
    {
        tv_title = (TextView) view.findViewById(R.id.tv_itemtask_title);
        iv_done = (ImageView) view.findViewById(R.id.iv_itemtask_isdone);
        tv_deadline = (TextView) view.findViewById(R.id.tv_itemtask_datetime_deadline);
        tv_groupname= (TextView) view.findViewById(R.id.tv_itemtask_groupname);
        layoutshowhide = (LinearLayout) view.findViewById(R.id.layout_showhide);

        tv_deadline.setText(UtilFunctions.formatDate(list.get(positon).getDeadline()));
        iv_done.setImageResource(R.drawable.nodone);
        tv_title.setText(list.get(positon).getTaskContent());
        tv_groupname.setText(list.get(positon).getGroup().getName());
        layoutshowhide.setVisibility(View.VISIBLE);
    }

    private void setEvenView(View view)
    {
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check)
                {
                    layoutshowhide.setVisibility(View.GONE);
                    check = true;
                }else
                {
                    layoutshowhide.setVisibility(View.VISIBLE);
                    check = false;
                    Log.d("bbbbbbbb","bbbbbbbbbbbbbb");
                }
            }
        });

        iv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_done.setImageResource(R.drawable.done);
            }
        });
        tv_groupname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
