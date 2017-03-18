package daqianjietong.com.module;

import daqianjietong.com.bean.UserInfoBean;
import daqianjietong.com.interfaces.IUserBiz;
import daqianjietong.com.interfaces.IUserOnClickListener;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final IUserOnClickListener loginListener) {
        //模拟子线程耗时操作
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("zhy".equals(username) && "123".equals(password))
                {
                    UserInfoBean userinfo = new UserInfoBean();
                    userinfo.setUsername(username);
                    userinfo.setPassword(password);
                    loginListener.loginSuccess(userinfo);
                } else
                {
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}

