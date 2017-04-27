package daqianjietong.com.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 * 首页广告轮播图
 */

public class BannerPagerAdapter extends PagerAdapter{

    private List<ImageView> images;
    public BannerPagerAdapter(List<ImageView> images) {
        super();
        this.images=images;
    }

    @Override
    public int getCount() {

//        return images.size();
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
        view.removeView(images.get(position%images.size()));

    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(images.get(position%images.size()));
        return images.get(position%images.size());
    }
}
