package daqianjietong.com.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;

/**
 * Created by Administrator on 2017/4/24 0024.
 * 新用户注册
 */
@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends BaseActivity implements View.OnClickListener{

    private Activity act;
    private Context context;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.tv_auth_code)
    private TextView tv_auth_code;

    @ViewInject(R.id.tv_user_agreement)
    private TextView tv_user_agreement;

    @ViewInject(R.id.et_phone_num)
    private EditText et_phone_num;

    @ViewInject(R.id.et_auth_code)
    private EditText et_auth_code;

    @ViewInject(R.id.et_new_psd)
    private EditText et_new_psd;

    @ViewInject(R.id.et_make_sure_psd)
    private EditText et_make_sure_psd;

    @ViewInject(R.id.cb_user_agreement)
    private CheckBox cb_user_agreement;

    @ViewInject(R.id.btn_register)
    private Button btn_register;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        initData();

    }
    private void initData() {
        tv_title.setText("注册帐号");
        iv_back.setOnClickListener(this);
        tv_auth_code.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                act.finish();
                break;
            case R.id.tv_auth_code:
                break;
            case R.id.btn_register:
                break;

        }

    }
}
