package com.pega.schooltasklist.database.object;
// Generated Aug 1, 2015 11:17:17 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Taskuser generated by hbm2java
 */
public class Taskuser  implements java.io.Serializable {


     private long id;
     private Task task;
     private User user;
     private Date createDate;
     private Boolean active;

    public Taskuser() {
    }

	
    public Taskuser(long id, Task task, User user) {
        this.id = id;
        this.task = task;
        this.user = user;
    }
    public Taskuser(long id, Task task, User user, Date createDate, Boolean active) {
       this.id = id;
       this.task = task;
       this.user = user;
       this.createDate = createDate;
       this.active = active;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Task getTask() {
        return this.task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }




}


