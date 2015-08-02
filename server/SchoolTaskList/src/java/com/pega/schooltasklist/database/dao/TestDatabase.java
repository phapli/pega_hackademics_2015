/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Schoolgroup;
import com.pega.schooltasklist.database.object.Task;
import com.pega.schooltasklist.database.object.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phapli
 */
public class TestDatabase {
    public static void main(String[] args) {
        
        //Schoolgroup group = GroupDAO.getInstance().getGroup(1);
        //Task task = new Task(group, "5242", new Date(), new Date(), true, null);
        //TaskDAO.getInstance().save(task);
        Task task = TaskDAO.getInstance().getTask(11);
        TaskDAO.getInstance().delete(task);
    }
}
