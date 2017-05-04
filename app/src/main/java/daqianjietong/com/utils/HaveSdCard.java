package daqianjietong.com.utils;

import android.os.Environment;
public class HaveSdCard {
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
