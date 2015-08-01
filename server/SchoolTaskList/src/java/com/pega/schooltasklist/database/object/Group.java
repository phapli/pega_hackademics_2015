package com.pega.schooltasklist.database.object;
// Generated Aug 2, 2015 12:38:57 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Group generated by hbm2java
 */
public class Group  implements java.io.Serializable {


     private Long id;
     private User user;
     private String name;
     private String description;
     private Date createDate;
     private Boolean active;
     private Set tasks = new HashSet(0);
     private Set groupusers = new HashSet(0);

    public Group() {
    }

	
    public Group(User user) {
        this.user = user;
    }
    public Group(User user, String name, String description, Date createDate, Boolean active, Set tasks, Set groupusers) {
       this.user = user;
       this.name = name;
       this.description = description;
       this.createDate = createDate;
       this.active = active;
       this.tasks = tasks;
       this.groupusers = groupusers;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    public Set getTasks() {
        return this.tasks;
    }
    
    public void setTasks(Set tasks) {
        this.tasks = tasks;
    }
    public Set getGroupusers() {
        return this.groupusers;
    }
    
    public void setGroupusers(Set groupusers) {
        this.groupusers = groupusers;
    }




}


