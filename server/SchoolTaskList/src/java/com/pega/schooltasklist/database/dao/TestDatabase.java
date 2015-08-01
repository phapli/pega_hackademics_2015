/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.User;
import java.util.List;

/**
 *
 * @author phapli
 */
public class TestDatabase {
    public static void main(String[] args) {
        User user = UserDAO.getInstance().login("ST0000001", "123456");
        System.out.println(user.getFirstName());
        List<User> a = UserDAO.getInstance().getAllChild("PA0000001");
        for(User u: a){
            System.out.println(u.getFirstName());
        }
    }
}
