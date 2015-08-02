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

import schooltasklist.pega.com.adapter.AdapterListMember;
import schooltasklist.pega.com.config.DataCurrent;
import schooltasklist.pega.com.connection.IOnGetDataFromServerComplete;
import schooltasklist.pega.com.connection.ManageConnection;
import schooltasklist.pega.com.connection.MessageParse;
import schooltasklist.pega.com.schooltasklist.R;

/**
 * Created by D.PHI on 8/2/2015.
 */
public class FragmentMember extends Fragment {

    private ListView list;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            actiongetMember();
        } catch (JSONException e)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView) view.findViewById(R.id.lv_fragmentmember);
    }

    private void actiongetMember() throws JSONException {
        String a = ""+DataCurrent.group_current.getId();
        int b = Integer.parseInt(a);
        ManageConnection.getInstance().getGroupMemberFunction(new IOnGetDataFromServerComplete() {
            @Override
            public void onGetDataComplete(JSONObject jsonResponse) throws JSONException {
                processReceiDataServer(jsonResponse);
            }
        },b);
    }
    private void processReceiDataServer(JSONObject jsonResponse) throws JSONException {
        DataCurrent.list_member = MessageParse.getGroupMemberResponse(jsonResponse);

        AdapterListMember adapterListMember = new AdapterListMember(getActivity(),DataCurrent.list_member);

        list.setAdapter(adapterListMember);

    }
}
