package schooltasklist.pega.com.connection;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import schooltasklist.pega.com.config.Configs;

/**
 * Created by Tran on 8/1/2015.
 */
public class AsyncTaskConnect extends AsyncTask<String, Void, String> {
    private Exception exception;
    private IOnGetDataFromServerComplete listener;
    private JSONObject jsonQuery;
    private JSONObject jsonResponse;

    public AsyncTaskConnect(IOnGetDataFromServerComplete listener, JSONObject query) {
        this.listener = listener;
        this.jsonQuery = query;
    }

    protected String doInBackground(String... urls) {
        try {
            jsonResponse = connectionToServer();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        return null;
    }

    protected void onPostExecute(String feed) {
        try {
            listener.onGetDataComplete(jsonResponse);
        } catch (JSONException e)
        {

        }

    }



    public JSONObject connectionToServer() throws JSONException {
        ConnectServer con = new ConnectServer();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new NameValuePair() {
            @Override
            public String getName() {
                return "req";
            }

            @Override
            public String getValue() {
                return jsonQuery.toString();
            }
        });
        return con.makeHttpRequest(Configs.LINK_WS,Configs.METHOD_GET,params);

    }


}
