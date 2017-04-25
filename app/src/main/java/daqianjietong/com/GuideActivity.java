package daqianjietong.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import daqianjietong.com.activity.UserLoginActivity;
import daqianjietong.com.adapter.ViewPagerAdapter;
import daqianjietong.com.daqianjietong.R;

/**
 * Created by Administrator on 2017/4/19 0019.
 */
@ContentView(R.layout.guide_activity)
public class GuideActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.guide_viewpager)
    private ViewPager guide_viewpager;

    //定义ViewPager适配器
    private ViewPagerAdapter vpAdapter;
    //定义一个ArrayList来存放View
    private ArrayList<View> mViews;
    // 定义各个界面View对象
    private View view1, view2, view3;

    //记录当前选中位置
//    private int currentIndex;
    private ImageView jump_image;
    private Activity act;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        initView();
        initData();
    }

    private void initData() {
        // 设置监听
//        guide_viewpager.setOnPageChangeListener(this);
        // 设置适配器数据
        guide_viewpager.setAdapter(vpAdapter);

        jump_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(act, UserLoginActivity.class);
                startActivity(intent);
                act.finish();
            }
        });
    }


    private void initView() {

        //实例化各个界面的布局对象
        LayoutInflater mLi = LayoutInflater.from(this);
        view1 = mLi.inflate(R.layout.guide1, null);
        view2 = mLi.inflate(R.layout.guide2, null);
        view3 = mLi.inflate(R.layout.guide3, null);
        // 实例化ArrayList对象
        mViews = new ArrayList<View>();
        //将要分页显示的View装入数组中
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        // 实例化ViewPager适配器
        vpAdapter = new ViewPagerAdapter(mViews);
        jump_image= (ImageView) view3.findViewById(R.id.iv_guide3);
    }
    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position){
        if (position < 0 || position >= 3) {
            return;
        }
        guide_viewpager.setCurrentItem(position);
    }
    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        setCurView(position);
    }
//    /**
//     * 当当前页面被滑动时调用
//     */
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//    /**
//     * 当新的页面被选中时调用
//     */
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//    /**
//     * 当滑动状态改变时调用
//     */
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
}
