package daqianjietong.com.interfaces;

import daqianjietong.com.bean.UserInfoBean;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(UserInfoBean userinfo);

    void showFailedError();
}
