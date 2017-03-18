package daqianjietong.com.bean;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class UserInfoBean {

    public UserInfoBean() {
        super();
    }

    public UserInfoBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
