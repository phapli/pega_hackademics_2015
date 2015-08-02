package schooltasklist.pega.com.model;

import java.io.Serializable;

/**
 * Created by Tran on 8/1/2015.
 */
public class Group implements Serializable{

    private Long id;
    private User admin;
    private String name;
    private String description;

    public Group(Long id, User admin, String name, String description) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() { return name;}

}
