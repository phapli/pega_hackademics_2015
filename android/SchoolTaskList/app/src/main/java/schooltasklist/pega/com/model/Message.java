package schooltasklist.pega.com.model;

import java.io.Serializable;

/**
 * Created by Tran on 8/1/2015.
 */
public class Message implements Serializable{
    private String message;
    private int code;

    public Message(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() { return message; }

}
