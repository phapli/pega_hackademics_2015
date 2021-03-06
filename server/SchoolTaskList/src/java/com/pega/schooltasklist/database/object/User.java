package com.pega.schooltasklist.database.object;
// Generated Aug 2, 2015 7:51:34 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private String id;
     private Role role;
     private User user;
     private String lastName;
     private String firstName;
     private String grade;
     private Boolean active;
     private Date createDate;
     private String password;
     private String email;
     private Set taskusers = new HashSet(0);
     private Set groupusers = new HashSet(0);
     private Set users = new HashSet(0);
     private Set schoolgroups = new HashSet(0);

    public User() {
    }

	
    public User(String id, Role role) {
        this.id = id;
        this.role = role;
    }
    public User(String id, Role role, User user, String lastName, String firstName, String grade, Boolean active, Date createDate, String password, String email, Set taskusers, Set groupusers, Set users, Set schoolgroups) {
       this.id = id;
       this.role = role;
       this.user = user;
       this.lastName = lastName;
       this.firstName = firstName;
       this.grade = grade;
       this.active = active;
       this.createDate = createDate;
       this.password = password;
       this.email = email;
       this.taskusers = taskusers;
       this.groupusers = groupusers;
       this.users = users;
       this.schoolgroups = schoolgroups;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getTaskusers() {
        return this.taskusers;
    }
    
    public void setTaskusers(Set taskusers) {
        this.taskusers = taskusers;
    }
    public Set getGroupusers() {
        return this.groupusers;
    }
    
    public void setGroupusers(Set groupusers) {
        this.groupusers = groupusers;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }
    public Set getSchoolgroups() {
        return this.schoolgroups;
    }
    
    public void setSchoolgroups(Set schoolgroups) {
        this.schoolgroups = schoolgroups;
    }




}


