package daqianjietong.com.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViews;

    public ViewPagerAdapter(ArrayList<View> mViews) {
        this.mViews=mViews;
    }

    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }
        return 0;
    }

    /**
     * 初始化position位置的界面
     */
    @Override
    public Object instantiateItem(View view, int position) {

        ((ViewPager) view).addView(mViews.get(position), 0);

        return mViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  (view == object);
    }

    /**
     * 销毁position位置的界面
     */
    @Override
    public void destroyItem(View view, int position, Object arg2) {
        ((ViewPager) view).removeView(mViews.get(position));
    }
}
