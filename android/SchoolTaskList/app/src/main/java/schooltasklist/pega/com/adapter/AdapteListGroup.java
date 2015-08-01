package schooltasklist.pega.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by D.PHI on 8/2/2015.
 */
public class AdapteListGroup extends ArrayAdapter{


    public AdapteListGroup(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getPosition(Object item) {
        return super.getPosition(item);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
