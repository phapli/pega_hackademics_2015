package schooltasklist.pega.com.connection;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tran on 8/1/2015.
 */
public class ManageConnection {
    private static ManageConnection instances = null;  //singleton design pattern


   public ManageConnection() {
   }

   public static ManageConnection getInstance() {
        if (instances==null) {
            return new ManageConnection();
        }
        return instances;
   }

//   public void testConnection(IOnGetDataFromServerComplete listener) {
//       // connect to server and getData
//       new AsyncTaskConnect(listener, loginFunction()).execute();
//   }

    public void loginFunction(IOnGetDataFromServerComplete listener, String username, String password) throws JSONException {
        JSONObject jsonQuery = MessageParse.loginQueyParam(username,password);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

}
