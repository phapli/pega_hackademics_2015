package schooltasklist.pega.com.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import schooltasklist.pega.com.adapter.AdapterListTask;
import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.model.Task;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by D.PHI on 8/1/2015.
 */
public class FragmentTask extends Fragment{
    private ListView listview;
    private ArrayList<Task> list_task = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            actiongetTask();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


            listview = (ListView) view.findViewById(R.id.lv_fragmenttask);
            AdapterListTask adapterListTask = new AdapterListTask(getActivity(),DataCurrent.list_task);


    }
    private void actiongetTask() throws JSONException {
        ManageConnection.getInstance().getAllTaskFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) throws JSONException {
                try {
                    processReceiDataServer(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, DataCurrent.user_current.getId());
    }
    private void processReceiDataServer(JSONObject jsonResponse) throws JSONException {
        DataCurrent.list_task = MessageParse.getAllTaskResponse(jsonResponse);

        AdapterListTask adapterListTask = new AdapterListTask(getActivity(),DataCurrent.list_task);

        listview.setAdapter(adapterListTask);

    }

}
