package daqianjietong.com.api;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import daqianjietong.com.bean.UserInfoBean;
import daqianjietong.com.utils.HttpUtil;
import daqianjietong.com.utils.StrHexStr;

/**
 * Created by liuzhuang on 2017/5/1.
 */

public class Api  {
    /**
     * api访问方法（post/get）
     */

    public enum  API_METHOD{
        POST,GET
    }
    private static  String HOST = "https://www.baidu.com";

    private static final String LOGIN="/dologin";

    /**
     * 是否debug调试（切换测试环境和生产环境）
     */
    private static final boolean Debug= false;


    private static  Api api;

    static {
        if(Debug){
            HOST = "";
        }
    }

    public static Api getInstance(){
        if(api == null)
            api = new Api();

        return api;
    }

    /**
     * 示例  登陆接口调用；
     * @param username
     * @param password
     * @param listenter
     */
  public  void login(String username, String password, HttpUtil.URLListenter<UserInfoBean> listenter){
      Map<String,String> params = new HashMap<>();
      params.put("username",username);
      params.put("password",password);
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<UserInfoBean>(){}.getType()).seturllisenter(listenter).start();
  }

  public  void texturl( HttpUtil.URLListenter<String> listenter){
      HttpUtil httpUtil = new HttpUtil();

      httpUtil.setUrl("http://192.168.0.185/dianapi.php/Parking/payList?token=f60btdiaVuSfyA7gUmVSY6Uc9smB_rq5RL0JEwad")
              .setMethod(API_METHOD.GET).setTypetoken(new TypeToken<String>(){}.getType()).seturllisenter(listenter).start();
  }

  /**
     * 示例  登陆接口调用；
     * @param username
     * @param password
     * @param listenter
     */

  String encrypt="n=18611607505&u=987654321&t=2014-12-11-06-49-34&l=47";
  String encrypt1= StrHexStr.str2HexStr(encrypt);

    String text="http://2.2.2.1/wx.html?href="+encrypt1+"&id=123456789";
  public  void register_wifi(Activity act,HttpUtil.URLListenter<String> listenter){
//      Map<String,String> params = new HashMap<>();
      HttpUtil httpUtil = new HttpUtil();
//      Toast.makeText(act,text,Toast.LENGTH_LONG).show();
      httpUtil.setUrl("http://2.2.2.1/wx.html?href=6E3D313836313136303735303526753D39383736353433323126743D323031342D31322D31312D30362D34392D3334266C3D3437&id=123456789")
              .setMethod(API_METHOD.GET).setParams(null).setTypetoken(new TypeToken<String>(){}.getType()).seturllisenter(listenter).start();
//      httpUtil.setUrl("http://2.2.2.1/wx.html?href="+encrypt1+"&id=123456789").setMethod(API_METHOD.GET).setParams(null).setTypetoken(new TypeToken<String>(){}.getType()).seturllisenter(listenter).start();
  }
    /**
     * 示例1  测试百度接口调用；（默认返回String类型的数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void test( HttpUtil.URLListenter<String> listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         *
         */

        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).seturllisenter(listenter).start();
    }
    /**
     * 示例2  测试百度接口调用；（设置返回解析后的list数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void testList(HttpUtil.URLListenter<ArrayList<String>>listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         * 获取list数据时示例如下：
         */

        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).setTypetoken(new TypeToken<ArrayList<UserInfoBean>>(){}.getType()).seturllisenter(listenter).start();
    }

}
