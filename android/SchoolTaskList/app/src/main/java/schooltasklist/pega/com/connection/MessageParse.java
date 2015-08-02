package schooltasklist.pega.com.connection;

import android.net.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    public static void parseData(JSONObject resJson){
//        int function = 1;
//
//        switch (function){
//            case F_LOGIN:
//                login(resJson);
//                break;
//            case F_GET_ALL_TASK:
//                getAllTask(resJson);
//                break;
//            case F_GET_ALL_CHILD:
//                getAllChid(resJson);
//                break;
//            case F_GET_ALL_GROUP:
//                getAllGroup(resJson);
//                break;
//            case F_SET_DONE:
//                setDone(resJson);
//                break;
//            case F_GET_GROUP:
//                getGroup(resJson);
//                break;
//            case F_GET_GROUP_TASKS:
//                getGroupTasks(resJson);
//                break;
//            case F_GET_GROUP_MEMBERS:
//                getGroupMembers(resJson);
//                break;
//            case F_ADD_TASK:
//                addTask(resJson);
//                break;
//            case F_DELETE_TASK:
//                deleteTask(resJson);
//                break;
//            case F_ADD_MEMBER:
//                addMember(resJson);
//                break;
//            case F_QUERY_USER:
//                queryUser(resJson);
//                break;
//            case F_DELETE_MEMBER:
//                deleteMember(resJson);
//                break;
//            case F_CREATE_GROUP:
//                createGroup(resJson);
//                break;
//
//            default:
//                break;
//        }
//    }

    private static void createGroup(JSONObject resJson) {

    }

    private static void deleteMember(JSONObject resJson) {

    }

    private static void queryUser(JSONObject resJson) {

    }

    private static void addMember(JSONObject resJson) {

    }

    private static void deleteTask(JSONObject resJson) {

    }

    private static void addTask(JSONObject resJson) {

    }

    private static void getGroupMembers(JSONObject resJson) {

    }

    private static void getGroupTasks(JSONObject resJson) {

    }

    private static void getGroup(JSONObject resJson) {

    }


    private static void getAllGroup(JSONObject resJson) {

    }

    private static void getAllChid(JSONObject resJson) {
    }

    private static void setDone(JSONObject resJson) {

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

    public static User loginResponseParse(JSONObject resJson) {
        User user = null;
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
                user = new User(email,id, lName,fName,grade, role);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static JSONObject loginQueyParam(String username, String pass) throws JSONException {
        JSONObject paramJson = new JSONObject();
        paramJson.put(T_FUNCTION,F_LOGIN);
        paramJson.put(T_USER_ID, username);
        paramJson.put(T_PASSWORD, pass);
        return paramJson;
    }

    public static JSONObject getAllTaskQuery(String userID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_ALL_TASK);
        //TODO edit set variable here
        reqJson.put(T_USER_ID, userID);
        return reqJson;
    }

    public static ArrayList<Task> getAllTaskResponse(JSONObject resJson) throws JSONException {
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
            return tasks;
        }
        else {
            return null;
        }
    }

    public static JSONObject getAllChildQuery(String userID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_ALL_CHILD);
        reqJson.put(T_USER_ID, userID);
        return reqJson;
    }

    public static ArrayList<User> getAllChildResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            JSONArray userArray = resJson.getJSONArray(T_USERS);
            ArrayList<User> users = new ArrayList<>();
            for(int i=0; i<userArray.length();i++){
                JSONObject userObj = userArray.getJSONObject(i);
                String id = userObj.getString(T_USER_ID);
                String fName = userObj.getString(T_FIRST_NAME);
                String grade = userObj.getString(T_GRADE);
                String lName = userObj.getString(T_LAST_NAME);
                String email = userObj.getString(T_EMAIL);
                int role = userObj.getInt(T_ROLE);
                users.add(new User(email,id, lName,fName,grade, role));
            }
            return users;
        }
        else return null;
    }



    public static JSONObject getAllGroupQuery(String userID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_ALL_GROUP);
        reqJson.put(T_USER_ID, userID);
        return reqJson;
    }

    public static ArrayList<Group> getAllGroupResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            JSONArray groupArray = resJson.getJSONArray(T_GROUPS);
            ArrayList<Group> groups = new ArrayList<>();
            for(int i=0; i<groupArray.length();i++){
                JSONObject groupObj = groupArray.getJSONObject(i);
                long id = groupObj.getLong(T_GROUP_ID);
                String name = groupObj.getString(T_GROUP_NAME);
                String description = groupObj.getString(T_GROUP_DESCRIPTION);

                groups.add(new Group(id, null, name, description));
            }
            return groups;
        }
        return null;
    }

    public static JSONObject setDoneQuery(String userID, long taskID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_SET_DONE);
        reqJson.put(T_USER_ID, userID);
        reqJson.put(T_TASK_ID, taskID);
        return reqJson;
    }

    public static boolean setDoneResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }

    public static JSONObject getGroupQuery(long groupID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_GROUP);
        reqJson.put(T_GROUP_ID, groupID);
        return reqJson;
    }

    public static Group getGroupResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            long id = resJson.getLong(T_GROUP_ID);
            String name = resJson.getString(T_GROUP_NAME);
            String description = resJson.getString(T_GROUP_DESCRIPTION);
            Group group = new Group(id, null, name, description);
            return group;
        }
        return null;
    }

    public static JSONObject getGroupTasksQuery(long groupID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_GROUP_TASKS);
        reqJson.put(T_GROUP_ID, groupID);
        return reqJson;
    }

    public static ArrayList<Task> getGroupTasksResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            JSONArray taskArray = resJson.getJSONArray(T_TASKS);
            ArrayList<Task> tasks = new ArrayList<>();
            for(int i=0; i<taskArray.length();i++){
                JSONObject taskObj = taskArray.getJSONObject(i);
                long taskID = resJson.getLong(T_TASK_ID);
                Date deadline = formatDate(resJson.getString(T_DEADLINE));
                String taskContent = resJson.getString(T_TASK);
                tasks.add(new Task(taskID, null, taskContent, deadline));
            }
            return tasks;
        }
        return null;
    }

    public static JSONObject getGroupMemberQuery(long groupID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_GET_GROUP_MEMBERS);
        reqJson.put(T_GROUP_ID, groupID);
        return reqJson;
    }

    public static ArrayList<User> getGroupMemberResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            JSONArray userArray = resJson.getJSONArray(T_USERS);
            ArrayList<User> users = new ArrayList<>();
            for(int i=0; i<userArray.length();i++){
                JSONObject userObj = userArray.getJSONObject(i);
                String id = userObj.getString(T_USER_ID);
                String fName = userObj.getString(T_FIRST_NAME);
                String grade = userObj.getString(T_GRADE);
                String lName = userObj.getString(T_LAST_NAME);
                String email = userObj.getString(T_EMAIL);
                int role = userObj.getInt(T_ROLE);
                users.add(new User(email,id, lName,fName,grade, role));
            }
            return users;
        }
        return null;
    }


    public static JSONObject addTaskQuery(long groupID, String nameTask, String date, List<User> users) throws JSONException {

        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_ADD_TASK);
        reqJson.put(T_GROUP_ID, groupID);
        reqJson.put(T_TASK, nameTask);
        reqJson.put(T_DEADLINE, date);  //formatDate("2015-08-02 10:15:12")
        JSONArray userArray = new JSONArray();
        for(User u: users){
            userArray.put(u.getId());
        }
        reqJson.put(T_USERS, userArray);
        return reqJson;
    }

    public static boolean addTaskResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }



    public static JSONObject deleteTaskQuery(long taskID) throws JSONException {
        ArrayList<User> users = new ArrayList<>();
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_DELETE_TASK);
        reqJson.put(T_TASK_ID, 1);
        return reqJson;
    }

    public static boolean deleteTaskResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }


    public static JSONObject queryUserQuery(long taskID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_QUERY_USER);
        return reqJson;
    }

    public static ArrayList<User> queryUserResponse(JSONObject resJson) throws JSONException, Exception {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            JSONArray userArray = resJson.getJSONArray(T_USERS);
            ArrayList<User> users = new ArrayList<>();
            for(int i=0; i<userArray.length();i++){
                JSONObject userObj = userArray.getJSONObject(i);
                String id = userObj.getString(T_USER_ID);
                String fName = userObj.getString(T_FIRST_NAME);
                String grade = userObj.getString(T_GRADE);
                String lName = userObj.getString(T_LAST_NAME);
                String email = userObj.getString(T_EMAIL);
                int role = userObj.getInt(T_ROLE);
                users.add(new User(email,id, lName,fName,grade, role));
            }
            return users;
        }
        return null;
    }


    public static JSONObject createGroupQuery(String groupName, String desc, String userID) throws JSONException {
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_CREATE_GROUP);
        reqJson.put(T_GROUP_NAME, groupName);
        reqJson.put(T_GROUP_DESCRIPTION, desc);
        reqJson.put(T_USER_ID, userID);
       return reqJson;
    }

    public static boolean createGroupResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }

    public static JSONObject deleteMemberQuery(long groupID) throws JSONException {
        ArrayList<User> users = new ArrayList<>();
        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_DELETE_MEMBER);
        reqJson.put(T_GROUP_ID, groupID);
        JSONArray userArray = new JSONArray();
        for(User u: users){
            userArray.put(u.getId());
        }
        reqJson.put(T_USERS, userArray);
        return reqJson;
    }

    public static boolean deleteMemberResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }


    public static JSONObject addMemberQuery(long groupID, List<User> users) throws JSONException {

        JSONObject reqJson = new JSONObject();
        reqJson.put(T_FUNCTION,F_ADD_MEMBER);
        reqJson.put(T_GROUP_ID, groupID);
        JSONArray userArray = new JSONArray();
        for(User u: users){
            userArray.put(u.getId());
        }
        reqJson.put(T_USERS, userArray);
        return reqJson;
    }

    public static boolean addMemberResponse(JSONObject resJson) throws JSONException {
        int code = resJson.getInt(T_CODE);
        String mess = resJson.getString(T_MESSAGE);
        if(code==C_SUCCESS){
            return true;
        }
        return false;
    }
    private static Date formatDate(String deadlineString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date result = sdf.parse(deadlineString);
            return result;
        }catch (java.text.ParseException e) {
        }
        return null;
    }
}
