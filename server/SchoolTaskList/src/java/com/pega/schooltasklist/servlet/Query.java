/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pega.schooltasklist.servlet;

import com.pega.schooltasklist.database.dao.GroupDAO;
import com.pega.schooltasklist.database.dao.GroupUserDAO;
import com.pega.schooltasklist.database.dao.TaskUserDAO;
import com.pega.schooltasklist.database.dao.UserDAO;
import com.pega.schooltasklist.database.object.Groupuser;
import com.pega.schooltasklist.database.object.Taskuser;
import com.pega.schooltasklist.database.object.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phapli
 */
@WebServlet(name = "Query", urlPatterns = {"/query"})
public class Query extends HttpServlet {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Query.class);
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

    private static final int C_SUCCESS = 0;
    private static final String M_SUCCESS = "Success";
    private static final int C_UNKNOWN = -1;
    private static final String M_UNKNOWN = "Unknown Error";
    private static final int C_LOGIN_FAIL = 1;
    private static final String M_LOGIN_FAIL = "UserID or Password is incorrect";

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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject resJson = new JSONObject();
        try {
            resJson.put(T_CODE, C_UNKNOWN);
            resJson.put(T_MESSAGE, M_UNKNOWN);
            Map<String, String[]> params = request.getParameterMap();
            String[] req_params = params.get("req");
            String req = "";
            if (req_params != null && req_params.length > 0) {
                req = req_params[0];
            }

            JSONObject reqJson = new JSONObject(req);
            int functionID = reqJson.getInt(T_FUNCTION);
            JSONObject result = null;
            switch (functionID) {
                case F_LOGIN:
                    result = login(reqJson);
                    break;
                case F_GET_ALL_TASK:
                    result = getAllTask(reqJson);
                    break;
                case F_GET_ALL_CHILD:
                    result = getAllChid(reqJson);
                    break;
                case F_GET_ALL_GROUP:
                    result = getAllGroup(reqJson);
                    break;
                case F_SET_DONE:
                    break;
                case F_GET_GROUP:
                    break;
                case F_GET_GROUP_TASKS:
                    break;
                case F_GET_GROUP_MEMBERS:
                    break;
                case F_ADD_TASK:
                    break;
                case F_DELETE_TASK:
                    break;
                case F_ADD_MEMBER:
                    break;
                case F_QUERY_USER:
                    break;
                case F_DELETE_MEMBER:
                    break;
                case F_CREATE_GROUP:
                    break;

                default:
                    break;
            }

            if (result != null) {
                resJson = result;
            }
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println(resJson.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private JSONObject login(JSONObject reqJson) {

        try {
            String userID = reqJson.getString(T_USER_ID);
            String password = reqJson.getString(T_PASSWORD);
            User user = UserDAO.getInstance().login(userID, password);
            JSONObject resJson = new JSONObject();
            if (user != null) {
                resJson.put(T_CODE, C_SUCCESS);
                resJson.put(T_MESSAGE, M_SUCCESS);
                resJson.put(T_USER_ID, user.getId());
                resJson.put(T_FIRST_NAME, user.getFirstName());
                resJson.put(T_GRADE, user.getGrade());
                resJson.put(T_LAST_NAME, user.getLastName());
                resJson.put(T_ROLE, user.getRole().getId());
            } else {
                resJson.put(T_CODE, C_LOGIN_FAIL);
                resJson.put(T_MESSAGE, M_LOGIN_FAIL);
            }
            return resJson;
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }
        return null;
    }

    private JSONObject getAllTask(JSONObject reqJson) {
        try {
            String userID = reqJson.getString(T_USER_ID);
            List<Taskuser> taskusers = TaskUserDAO.getInstance().getAllTask(userID);
            JSONObject resJson = new JSONObject();
            resJson.put(T_CODE, C_SUCCESS);
            resJson.put(T_MESSAGE, M_SUCCESS);
            JSONArray taskArray = new JSONArray();
            if (taskusers != null) {
                for (Taskuser taskuser : taskusers) {
                    JSONObject task = new JSONObject();
                    task.put(T_TASK_ID, taskuser.getTask().getId());
                    task.put(T_DEADLINE, taskuser.getTask().getDeadline());
                    task.put(T_GROUP_ID, taskuser.getTask().getGroup().getId());
                    task.put(T_GROUP_NAME, taskuser.getTask().getGroup().getName());
                    task.put(T_TASK, taskuser.getTask().getTaskContent());
                    taskArray.put(task);
                }
            }
            resJson.put(T_TASKS, taskArray);
            return resJson;
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }
        return null;
    }

    private JSONObject getAllChid(JSONObject reqJson) {
        try {
            String userID = reqJson.getString(T_USER_ID);
            List<User> users = UserDAO.getInstance().getAllChild(userID);
            JSONObject resJson = new JSONObject();
            resJson.put(T_CODE, C_SUCCESS);
            resJson.put(T_MESSAGE, M_SUCCESS);
            JSONArray userArray = new JSONArray();
            if (users != null) {
                for (User user : users) {
                    JSONObject userObj = new JSONObject();
                    userObj.put(T_USER_ID, user.getId());
                    userObj.put(T_FIRST_NAME, user.getFirstName());
                    userObj.put(T_GRADE, user.getGrade());
                    userObj.put(T_LAST_NAME, user.getLastName());
                    userObj.put(T_ROLE, user.getRole().getId());
                    userArray.put(userObj);
                }
            }
            resJson.put(T_USERS, userArray);
            return resJson;
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }
        return null;
    }

    private JSONObject getAllGroup(JSONObject reqJson) {
        try {
            String userID = reqJson.getString(T_USER_ID);
            List<Groupuser> groupusers = GroupUserDAO.getInstance().getAllGroup(userID);
            JSONObject resJson = new JSONObject();
            resJson.put(T_CODE, C_SUCCESS);
            resJson.put(T_MESSAGE, M_SUCCESS);
            JSONArray groupArray = new JSONArray();
            if (groupusers != null) {
                for (Groupuser gu : groupusers) {
                    JSONObject groupObj = new JSONObject();
                    groupObj.put(T_GROUP_ID, gu.getGroup().getId());
                    groupObj.put(T_GROUP_NAME, gu.getGroup().getName());
                    groupObj.put(T_GROUP_DESCRIPTION, gu.getGroup().getDescription());
                    groupArray.put(groupObj);
                }
            }
            resJson.put(T_GROUPS, groupArray);
            return resJson;
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }
        return null;
    }

}
