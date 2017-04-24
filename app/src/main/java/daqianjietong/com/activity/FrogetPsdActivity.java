package daqianjietong.com.activity;

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
 * 忘记密码
 */
@ContentView(R.layout.activity_froget_psd)
public class FrogetPsdActivity extends BaseActivity implements View.OnClickListener{

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


    @ViewInject(R.id.btn_repsd)
    private Button btn_repsd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        tv_title.setText("忘记密码");
        iv_back.setOnClickListener(this);
        btn_repsd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                break;
            case R.id.btn_repsd:
                break;

        }
    }
}
