package schooltasklist.pega.com.connection;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import schooltasklist.pega.com.model.User;

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

    public void loginFunction(IOnGetDataFromServerComplete listener, String username, String password) throws JSONException {
        JSONObject jsonQuery = MessageParse.loginQueyParam(username,password);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void getAllTaskFunction(IOnGetDataFromServerComplete listener, String id) throws JSONException {
        JSONObject jsonQuery = MessageParse.getAllTaskQuery(id);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void getAllChildFunction(IOnGetDataFromServerComplete listener, String id) throws JSONException {
        JSONObject jsonQuery = MessageParse.getAllChildQuery(id);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void getAllGroupFunction(IOnGetDataFromServerComplete listener, String id) throws JSONException {
        JSONObject jsonQuery = MessageParse.getAllGroupQuery(id);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void setDoneFunction(IOnGetDataFromServerComplete listener, String userID, long taskID) throws JSONException {
        JSONObject jsonQuery = MessageParse.setDoneQuery(userID, taskID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void getGroupFunction(IOnGetDataFromServerComplete listener, long groupID) throws JSONException {
        JSONObject jsonQuery = MessageParse.getGroupQuery(groupID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void getGroupTaskFunction(IOnGetDataFromServerComplete listener, long groupID) throws JSONException {
        JSONObject jsonQuery = MessageParse.getGroupTasksQuery(groupID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }
    public void getGroupMemberFunction(IOnGetDataFromServerComplete listener, long groupID) throws JSONException {
        JSONObject jsonQuery = MessageParse.getGroupMemberQuery(groupID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void addTaskFunction(IOnGetDataFromServerComplete listener, long groupID, String nameTask, String date, List<User> users) throws JSONException {
        JSONObject jsonQuery = MessageParse.addTaskQuery(groupID, nameTask, date, users);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void deleteTaskFunction(IOnGetDataFromServerComplete listener, long taskID) throws JSONException {
        JSONObject jsonQuery = MessageParse.deleteTaskQuery(taskID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void createGroupFunction(IOnGetDataFromServerComplete listener, String groupName, String desc, String userID) throws JSONException {
        JSONObject jsonQuery = MessageParse.createGroupQuery(groupName, desc, userID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void deleteMemberFunction(IOnGetDataFromServerComplete listener, long groupID) throws JSONException {
        JSONObject jsonQuery = MessageParse.deleteMemberQuery(groupID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void queryUserFunction(IOnGetDataFromServerComplete listener, long taskID) throws JSONException {
        JSONObject jsonQuery = MessageParse.queryUserQuery(taskID);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }

    public void addMemberFunction(IOnGetDataFromServerComplete listener, long groupID, List<User> users) throws JSONException, Exception {
        JSONObject jsonQuery = MessageParse.addMemberQuery(groupID, users);
        new AsyncTaskConnect(listener, jsonQuery).execute();
    }
}
