package schooltasklist.pega.com.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by D.PHI on 8/2/2015.
 */
public class AdapterListMember extends ArrayAdapter {
    public AdapterListMember(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
}
