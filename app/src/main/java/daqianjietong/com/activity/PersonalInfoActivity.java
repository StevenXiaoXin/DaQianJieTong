package daqianjietong.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;

/**
 * Created by Administrator on 2017/4/28 0028.
 * 个人信息页
 */

@ContentView(R.layout.activity_personal_info)
public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_user_photo)
    private ImageView iv_user_photo;
    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;
    @ViewInject(R.id.tv_user_phone)
    private TextView tv_user_phone;
    @ViewInject(R.id.tv_user_car)
    private TextView tv_user_car;
    @ViewInject(R.id.tv_login_out)
    private TextView tv_login_out;

    @ViewInject(R.id.ll_user_photo)
    private LinearLayout ll_user_photo;
    @ViewInject(R.id.ll_user_name)
    private LinearLayout ll_user_name;
    @ViewInject(R.id.ll_user_phone)
    private LinearLayout ll_user_phone;
    @ViewInject(R.id.ll_user_car)
    private LinearLayout ll_user_car;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        tv_title.setText("个人信息");
        iv_back.setOnClickListener(this);
        ll_user_photo.setOnClickListener(this);
        ll_user_name.setOnClickListener(this);
        ll_user_car.setOnClickListener(this);
        tv_login_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                PersonalInfoActivity.this.finish();
                break;
            case R.id.ll_user_photo:
                //设置头像
                break;
            case R.id.ll_user_name:
                //设置昵称
                break;
            case R.id.ll_user_car:
                //设置车牌
                break;
            case R.id.tv_login_out:
                //退出登录
                break;
        }
    }
}
