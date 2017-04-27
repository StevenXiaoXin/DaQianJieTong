package daqianjietong.com.utils;

import org.xutils.http.RequestParams;

import java.util.List;

import daqianjietong.com.bean.UserInfoBean;


public class Constant {
		public static RequestParams requestParams;
		static{
			requestParams=new RequestParams();
		}
		public static String FileName = "user";
		public static UserInfoBean user = new UserInfoBean();
}
 