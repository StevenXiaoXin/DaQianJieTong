package daqianjietong.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.daqianjietong.R;

/**
 * Created by Administrator on 2017/4/28 0028.
 * 修改密码页
 */
@ContentView(R.layout.activity_restore_psd)
public class RestorePsdActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_old_psd)
    private EditText et_old_psd;
    @ViewInject(R.id.et_new_psd)
    private EditText et_new_psd;
    @ViewInject(R.id.et_make_sure_new_psd)
    private EditText et_make_sure_new_psd;
    @ViewInject(R.id.btn_restore_psd)
    private Button btn_restore_psd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();


    }

    private void initData() {
        tv_title.setText("修改密码");
        iv_back.setOnClickListener(this);
        btn_restore_psd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                RestorePsdActivity.this.finish();
                break;
            case R.id.btn_restore_psd:
                //加网络请求，成功后结束当前Activity，失败弹窗提示停留本页。
                break;
        }

    }
}
