package daqianjietong.com.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.bean.TxtBean;
import daqianjietong.com.bean.UserLoginBean;
import daqianjietong.com.daqianjietong.R;
import daqianjietong.com.utils.AESUtils;
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
//                Intent intent2=new Intent(act,MainActivity.class);
//                startActivity(intent2);
//                act.finish();
                testApi();
                break;

        }

    }


    private void testApi(){
//        {"txt_password":"12345","txt_phone":"18611607505"}

        RequestParams params = new RequestParams("http://test.daqianjietong.com/dianapi.php/Login/login");
        UserLoginBean packet = new UserLoginBean();
        packet.setTxt_password("123456");
        packet.setTxt_phone("18611607505");
        Gson gson = new Gson();
        String str=gson.toJson(packet);
        Log.e("加密前数据", str);

        try {
            str= AESUtils.encrypt(str);
            Log.e("加密数据", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.addBodyParameter("packet",str);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson asd = new Gson();
                TxtBean aaaa=asd.fromJson(result,TxtBean.class);


//                    Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
//                Log.e("s数据---》",result);
                Log.e("data数据---》",aaaa.getData());

                Log.e("解析成功---》",AESUtils.desEncrypt(aaaa.getData()));


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("解析列表数据失败---》",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });

    }







    @Override
    public void onBackPressed() {
        ExitByClick.exitBy2Click(act);
    }

}
