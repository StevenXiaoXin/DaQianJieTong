package daqianjietong.com.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

    @ViewInject(R.id.user_name)
    private EditText user_name;

    @ViewInject(R.id.user_password)
    private EditText user_password;

    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @ViewInject(R.id.tv_new_user)
    private TextView tv_new_user;

    @ViewInject(R.id.tv_forget_psd)
    private TextView tv_froget_psd;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
