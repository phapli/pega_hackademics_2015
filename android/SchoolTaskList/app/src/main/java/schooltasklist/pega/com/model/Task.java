package schooltasklist.pega.com.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tran on 8/1/2015.
 */
public class Task implements Serializable{

    private Long id;
    private Group group;
    private String taskContent;
    private Date deadline;

    public Task(Long id, Group group, String taskContent, Date deadline) {
        this.id = id;
        this.group = group;
        this.taskContent = taskContent;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() { return taskContent;}

}
