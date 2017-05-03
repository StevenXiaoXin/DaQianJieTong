package daqianjietong.com.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import daqianjietong.com.activity.MainActivity;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class ConnectWifi {
    String rest="";
    private Context context;
    private Activity act;
    private void testApi(String username, Context context, Activity act) {

        this.context=context;
        this.act=act;

        RequestParams params = new RequestParams(username);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("解析列表数据成功---》", result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("解析列表数据失败---》", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });

//        iswifi();

    }





//    public void iswifi(){
//        if(rest==""||rest==null){
//            Toast.makeText(context,"请求无网络",Toast.LENGTH_SHORT).show();
//            Log.e("asdasdasdasdasdas---》","dasdasdasdasd"+rest);
//            Intent intent2=new Intent(act,MainActivity.class);
//            startActivity(intent2);
//            act.finish();
//        }else{
//            Toast.makeText(context,"请求成功",Toast.LENGTH_SHORT).show();
//            Log.e("asdasdasdasdasdas---》","请求成功");
//            Intent intent2=new Intent(act,MainActivity.class);
//            startActivity(intent2);
//            act.finish();
//        }
//    }

}