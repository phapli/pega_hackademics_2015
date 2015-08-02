package schooltasklist.pega.com.connection;

import android.net.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import schooltasklist.pega.com.model.Group;
import schooltasklist.pega.com.model.Task;
import schooltasklist.pega.com.model.User;

/**
 * Created by phapli on 8/2/2015.
 */
public class MessageParse {

    private static final String T_CODE = "ResponseCode";
    private static final String T_MESSAGE = "ResponseMessage";
    private static final String T_FUNCTION = "Function";
    private static final String T_USER_ID = "UserID";
    private static final String T_PASSWORD = "Password";
    private static final String T_FIRST_NAME = "FirstName";
    private static final String T_GRADE = "Grade";
    private static final String T_LAST_NAME = "LastName";
    private static final String T_ROLE = "Role";
    private static final String T_TASK_ID = "TaskID";
    private static final String T_DEADLINE = "Deadline";
    private static final String T_GROUP_ID = "GroupID";
    private static final String T_GROUP_NAME = "GroupName";
    private static final String T_TASK = "Task";
    private static final String T_TASKS = "Tasks";
    private static final String T_USERS = "Users";
    private static final String T_GROUP_DESCRIPTION = "GroupDescription";
    private static final String T_GROUPS = "Groups";
    private static final String T_EMAIL = "Email";

    private static final int C_SUCCESS = 0;
    private static final String M_SUCCESS = "Success";
    private static final int C_UNKNOWN = -1;
    private static final String M_UNKNOWN = "Unknown Error";
    private static final int C_LOGIN_FAIL = 1;
    private static final String M_LOGIN_FAIL = "UserID or Password is incorrect";
    private static final int C_SET_DONE_FAIL = 2;
    private static final String M_SET_DONE_FAIL = "This task is not exist";
    private static final int C_GET_GROUP_FAIL = 3;
    private static final String M_GET_GROUP_FAIL = "This group is not exist";

    private static final int F_LOGIN = 1;
    private static final int F_GET_ALL_TASK = 2;
    private static final int F_GET_ALL_CHILD = 3;
    private static final int F_GET_ALL_GROUP = 4;
    private static final int F_SET_DONE = 5;
    private static final int F_GET_GROUP = 6;
    private static final int F_GET_GROUP_TASKS = 7;
    private static final int F_GET_GROUP_MEMBERS = 8;
    private static final int F_ADD_TASK = 9;
    private static final int F_DELETE_TASK = 10;
    private static final int F_ADD_MEMBER = 11;
    private static final int F_QUERY_USER = 12;
    private static final int F_DELETE_MEMBER = 13;
    private static final int F_CREATE_GROUP = 14;

    public static void parseData(JSONObject resJson){
        int function = 1;
        
        switch (function){
            case F_LOGIN:
                login(resJson);
                break;
            case F_GET_ALL_TASK:
                getAllTask(resJson);
                break;
            case F_GET_ALL_CHILD:
                getAllChid(resJson);
                break;
            case F_GET_ALL_GROUP:
                getAllGroup(resJson);
                break;
            case F_SET_DONE:
                setDone(resJson);
                break;
            case F_GET_GROUP:
                getGroup(resJson);
                break;
            case F_GET_GROUP_TASKS:
                getGroupTasks(resJson);
                break;
            case F_GET_GROUP_MEMBERS:
                getGroupMembers(resJson);
                break;
            case F_ADD_TASK:
                addTask(resJson);
                break;
            case F_DELETE_TASK:
                deleteTask(resJson);
                break;
            case F_ADD_MEMBER:
                addMember(resJson);
                break;
            case F_QUERY_USER:
                queryUser(resJson);
                break;
            case F_DELETE_MEMBER:
                deleteMember(resJson);
                break;
            case F_CREATE_GROUP:
                createGroup(resJson);
                break;

            default:
                break;
        }
    }

    private static void getAllChid(JSONObject resJson) {
    }

    private static void getAllTask(JSONObject resJson) {
        try {
            int code = resJson.getInt(T_CODE);
            String mess = resJson.getString(T_MESSAGE);
            if(code==C_SUCCESS){
                JSONArray taskArray = resJson.getJSONArray(T_TASKS);
                ArrayList<Task> tasks = new ArrayList<>();
                for(int i=0; i<taskArray.length();i++){
                    JSONObject taskObj = taskArray.getJSONObject(i);
                    long taskID = taskObj.getLong(T_TASK_ID);
                    Date deadline = formatDate(taskObj.getString(T_DEADLINE));
                    long groupID = taskObj.getLong(T_GROUP_ID);
                    String groupName = taskObj.getString(T_GROUP_NAME);
                    String taskContent = taskObj.getString(T_TASK);
                    tasks.add(new Task(taskID, new Group(groupID, null, groupName, null), taskContent, deadline));
                }
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void login(JSONObject resJson) {
        try {
            int code = resJson.getInt(T_CODE);
            String mess = resJson.getString(T_MESSAGE);
            if(code==C_SUCCESS){
                String id = resJson.getString(T_USER_ID);
                String fName = resJson.getString(T_FIRST_NAME);
                String grade = resJson.getString(T_GRADE);
                String lName = resJson.getString(T_LAST_NAME);
                String email = resJson.getString(T_EMAIL);
                int role = resJson.getInt(T_ROLE);
                User user = new User(email,id, lName,fName,grade, role);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static Date formatDate(String deadlineString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date result = sdf.parse(deadlineString);
            return result;
        }catch (java.text.ParseException e) {
        }
        return null;
    }
}
