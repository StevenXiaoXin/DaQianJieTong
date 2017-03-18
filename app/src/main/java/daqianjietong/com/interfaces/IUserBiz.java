package daqianjietong.com.interfaces;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public interface IUserBiz {

    public void login(String username, String password, IUserOnClickListener loginListener);
}
