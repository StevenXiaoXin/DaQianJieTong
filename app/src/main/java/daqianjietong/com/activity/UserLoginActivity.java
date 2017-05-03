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

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
    String rest="";





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
//                testApi();
                break;

        }

    }


    private void testApi(){


        RequestParams params = new RequestParams("http://2.2.2.1/wx.html?href=6E3D313836313136303735303526753D39383736353433323126743D323031342D31322D31312D30362D34392D3334266C3D3437&id=123456789");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                    Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.e("解析列表数据成功---》",result);
                rest =result;
                Log.e("ASDFGH---》",rest);
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

//        return rest;
        iswifi();

    }

    public void iswifi(){
        if(rest==""||rest==null){
            Toast.makeText(getApplicationContext(),"请求无网络",Toast.LENGTH_SHORT).show();
            Log.e("asdasdasdasdasdas---》","dasdasdasdasd"+rest);
            Intent intent2=new Intent(act,MainActivity.class);
            startActivity(intent2);
            act.finish();
        }else{
            Toast.makeText(getApplicationContext(),"请求成功",Toast.LENGTH_SHORT).show();
            Log.e("asdasdasdasdasdas---》","请求成功");
            Intent intent2=new Intent(act,MainActivity.class);
            startActivity(intent2);
            act.finish();
        }
    }








    @Override
    public void onBackPressed() {
        ExitByClick.exitBy2Click(act);
    }

}
