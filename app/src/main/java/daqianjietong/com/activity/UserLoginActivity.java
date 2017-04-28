package daqianjietong.com.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;
import daqianjietong.com.utils.ExitByClick;
import daqianjietong.com.utils.NetWorkUtil;


/**
 * Created by Administrator on 2017/3/14 0014.
 * 用户登录
 */
@ContentView(R.layout.uer_login)
public class UserLoginActivity extends BaseActivity implements View.OnClickListener {

    private Activity act;
    private Context context;

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
    private TextView tv_forget_psd;

    private boolean isNetWork=false;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        if (NetWorkUtil.isNetworkConnected(context)) {
            isNetWork=true;
            initData();
        } else {
            isNetWork=false;
            Toast.makeText(act, "无可用网络，请检查网络连接", Toast.LENGTH_SHORT).show();
        }

    }

    private void initData() {
        tv_new_user.setOnClickListener(this);
        tv_forget_psd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_new_user:
                Intent intent=new Intent(act,RegisterUserActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_psd:
                Intent intent1=new Intent(act,FrogetPsdActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_login:
                Intent intent2=new Intent(act,MainActivity.class);
                startActivity(intent2);
                act.finish();
                break;

        }


    }
    @Override
    public void onBackPressed() {
        ExitByClick.exitBy2Click(act);
    }

}
