package schooltasklist.pega.com.connection;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tran on 8/1/2015.
 */
public class AsyncTaskConnect extends AsyncTask<String, Void, String> {
    private Exception exception;
    private IOnGetDataFromServerComplete listener;

    public AsyncTaskConnect(IOnGetDataFromServerComplete listener) {
        this.listener = listener;
    }

    protected String doInBackground(String... urls) {
        try {
            testConnection();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        return null;
    }

    protected void onPostExecute(String feed) {
        listener.onGetDataComplete();
    }





    public void testConnection() throws JSONException {
        ConnectServer con = new ConnectServer();
        final JSONObject jsonObject= new JSONObject();
        jsonObject.put("Function",1);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new NameValuePair() {
            @Override
            public String getName() {
                return "req";
            }

            @Override
            public String getValue() {
                return jsonObject.toString();
            }
        });
        JSONObject jsonObjectReceive = con.makeHttpRequest("http://10.0.239.202:8080/SchoolTaskList/query","GET",params);

    }


}
