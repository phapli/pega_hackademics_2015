/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pega.schooltasklist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    private static final int C_SUCCESS = 0;
    private static final String M_SUCCESS = "Success";
    private static final int C_UNKNOWN = -1;
    private static final String M_UNKNOWN = "Unknown Error";
    private static final int C_LOGIN_FAIL = 1;
    private static final String M_LOGIN_FAIL = "Login Fail";
    

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
                    break;
                case F_GET_ALL_CHILD:
                    break;
                case F_GET_ALL_GROUP:
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
            JSONObject resJson = new JSONObject();
            resJson.remove(T_CODE);
            resJson.put(T_CODE, C_SUCCESS);
            resJson.put(T_MESSAGE, M_SUCCESS);
            return resJson;
        } catch (JSONException ex) {
            LOGGER.error("", ex);
        }
        return null;
    }

}