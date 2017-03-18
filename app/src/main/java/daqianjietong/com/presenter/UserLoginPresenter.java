package daqianjietong.com.presenter;

import android.os.Handler;

import daqianjietong.com.bean.UserInfoBean;
import daqianjietong.com.interfaces.IUserBiz;
import daqianjietong.com.interfaces.IUserLoginView;
import daqianjietong.com.interfaces.IUserOnClickListener;
import daqianjietong.com.module.UserBiz;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login()
    {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new IUserOnClickListener() {
            @Override
            public void loginSuccess(final UserInfoBean user) {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
//需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });


            }
        });

    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

}
