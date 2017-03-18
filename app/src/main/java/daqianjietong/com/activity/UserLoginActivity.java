package daqianjietong.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import daqianjietong.com.bean.UserInfoBean;
import daqianjietong.com.daqianjietong.R;
import daqianjietong.com.interfaces.IUserLoginView;
import daqianjietong.com.presenter.UserLoginPresenter;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class UserLoginActivity extends Activity implements IUserLoginView {


    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(UserInfoBean userinfo) {

    }

    @Override
    public void showFailedError() {

    }
}
