package daqianjietong.com.interfaces;

import daqianjietong.com.bean.UserInfoBean;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public interface IUserOnClickListener {

    void loginSuccess(UserInfoBean user);
    void loginFailed();

}
