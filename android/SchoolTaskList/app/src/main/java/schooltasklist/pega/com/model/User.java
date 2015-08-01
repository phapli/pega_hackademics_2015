package schooltasklist.pega.com.model;

import java.io.Serializable;

/**
 * Created by Tran on 8/1/2015.
 */
public class User implements Serializable{
    private String name;
    private String email;

    public User(String n, String e) { name = n; email = e; }

    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() { return name; }

}
