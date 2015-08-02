package schooltasklist.pega.com.connection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tran on 8/1/2015.
 */
public interface IOnGetDataFromServerComplete {

    void onGetDataComplete(JSONObject jsonResponse) throws JSONException;

}
