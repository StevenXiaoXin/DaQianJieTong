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
 * Created by Administrator on 2017/4/27 0027.
 * 个人中心
 */
@ContentView(R.layout.activity_personal)
public class PersonalActivity extends BaseActivity implements View.OnClickListener{
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
    @ViewInject(R.id.ll_user_info)
    private LinearLayout ll_user_info;
    @ViewInject(R.id.ll_my_order)
    private LinearLayout ll_my_order;
    @ViewInject(R.id.ll_restore_psd)
    private LinearLayout ll_restore_psd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        iv_back.setOnClickListener(this);
        ll_user_info.setOnClickListener(this);
        ll_my_order.setOnClickListener(this);
        ll_restore_psd.setOnClickListener(this);

        tv_title.setText("个人中心");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent intent=new Intent(PersonalActivity.this,MainActivity.class);
                startActivity(intent);
                PersonalActivity.this.finish();
                break;
            case R.id.ll_user_info:
                Intent intent1=new Intent(PersonalActivity.this,PersonalInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_my_order:
                //跳转到我的订单
                break;
            case R.id.ll_restore_psd:
                Intent intent3=new Intent(PersonalActivity.this,RestorePsdActivity.class);
                startActivity(intent3);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PersonalActivity.this,MainActivity.class);
        startActivity(intent);
        PersonalActivity.this.finish();
    }
}
