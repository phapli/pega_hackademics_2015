package schooltasklist.pega.com.model;

import java.io.Serializable;

/**
 * Created by Tran on 8/1/2015.
 */
public class User implements Serializable{
    private String name;
    private String email;
    private String id;

    public User(String n, String e, String id) { name = n; email = e; this.id = id;}

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getId() { return id; }

    @Override
    public String toString() { return name; }

}
