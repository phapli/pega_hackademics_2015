package schooltasklist.pega.com.model;

import java.io.Serializable;

/**
 * Created by Tran on 8/1/2015.
 */
public class User implements Serializable{
    private String email;
    private String id;
    private String lastName;
    private String firstName;
    private String grade;
    private int role;

    public User(String email, String id, String lastName, String firstName, String grade, int role) {
        this.email = email;
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.grade = grade;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() { return firstName + " " + lastName; }

}
