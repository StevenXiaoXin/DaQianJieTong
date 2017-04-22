package daqianjietong.com.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;



/**
 * Created by Administrator on 2017/3/14 0014.
 */
@ContentView(R.layout.uer_login)
public class UserLoginActivity extends BaseActivity{

    @ViewInject(R.id.user_login_image)
    private ImageView user_login_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
