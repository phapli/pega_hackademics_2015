package com.pega.schooltasklist.database.object;
// Generated Aug 1, 2015 11:17:17 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Task generated by hbm2java
 */
public class Task  implements java.io.Serializable {


     private Long id;
     private Group group;
     private String task;
     private Boolean done;
     private Date createDate;
     private Date deadline;
     private Boolean active;
     private Set taskusers = new HashSet(0);

    public Task() {
    }

    public Task(Group group, String task, Boolean done, Date createDate, Date deadline, Boolean active, Set taskusers) {
       this.group = group;
       this.task = task;
       this.done = done;
       this.createDate = createDate;
       this.deadline = deadline;
       this.active = active;
       this.taskusers = taskusers;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    public String getTask() {
        return this.task;
    }
    
    public void setTask(String task) {
        this.task = task;
    }
    public Boolean getDone() {
        return this.done;
    }
    
    public void setDone(Boolean done) {
        this.done = done;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getDeadline() {
        return this.deadline;
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Set getTaskusers() {
        return this.taskusers;
    }
    
    public void setTaskusers(Set taskusers) {
        this.taskusers = taskusers;
    }




}


