package daqianjietong.com.banner;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import org.xutils.x;

/**
 * Created by liuzhuang on 2017/5/1.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        x.image().bind(imageView,(String)path);
    }
}
