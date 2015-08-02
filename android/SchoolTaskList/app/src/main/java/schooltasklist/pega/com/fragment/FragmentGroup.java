package schooltasklist.pega.com.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;
import org.json.JSONObject;

import schooltasklist.pega.com.activity.ActivityGroup;
import schooltasklist.pega.com.adapter.AdapteListGroup;
import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by D.PHI on 8/1/2015.
 */
public class FragmentGroup extends Fragment{
    private GridView grid;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            actiongetGroup();
        }catch (JSONException e)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        grid = (GridView) view.findViewById(R.id.gv_fragmentgroup);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ActivityGroup.class);
                DataCurrent.group_current = DataCurrent.list_group.get(position);
                Log.d("aaaaaaaaaaaaaaaaaaaaaa",DataCurrent.list_group.get(position).getId()+"");
                startActivity(new Intent(getActivity(), ActivityGroup.class));
            }
        });


    }

    private void actiongetGroup() throws JSONException {
        ManageConnection.getInstance().getAllGroupFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) throws JSONException {
                processReceiDataServer(jsonResponse);
            }
        },DataCurrent.user_current.getId());
    }
    private void processReceiDataServer(JSONObject jsonResponse) throws JSONException {
            DataCurrent.list_group = MessageParse.getAllGroupResponse(jsonResponse);
            Log.d("aaaaaaaaaaaaaaaaaaaa", " + " + DataCurrent.list_group.size());
            AdapteListGroup adapterListTask = new AdapteListGroup(getActivity(),DataCurrent.list_group);

            grid.setAdapter(adapterListTask);

    }
}
