package daqianjietong.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import daqianjietong.com.BaseActivity;
import daqianjietong.com.adapter.BannerPagerAdapter;
import daqianjietong.com.api.Api;
import daqianjietong.com.banner.BannerImageLoader;
import daqianjietong.com.bean.UserInfoBean;
import daqianjietong.com.daqianjietong.R;
import daqianjietong.com.utils.FixedSpeedScroller;
import daqianjietong.com.utils.HttpUtil;


@ContentView(R.layout.activity_main_test)
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Activity act;
    private Context context;
    @ViewInject(R.id.iv_personal_center)
    private ImageView iv_personal_center;
    @ViewInject(R.id.lv_main)
    private ScrollView lv_main;

    @ViewInject(R.id.btn_order)
    private Button btn_order;
    @ViewInject(R.id.btn_nearby)
    private Button btn_nearby;
    @ViewInject(R.id.btn_rent)
    private Button btn_rent;
    @ViewInject(R.id.btn_card)
    private Button btn_card;
    @ViewInject(R.id.btn_pay)
    private TextView btn_pay;
//    @ViewInject(R.id.vp)
//    private ViewPager mViewPager;

    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.iv_arrows_up)
    private ImageView iv_arrows_up;
    @ViewInject(R.id.iv_arrows_down)
    private ImageView iv_arrows_down;
    @ViewInject(R.id.rl_tab)
    private RelativeLayout rl_tab;
    @ViewInject(R.id.ll_park)
    private LinearLayout ll_park;
    @ViewInject(R.id.ll_car)
    private LinearLayout ll_car;
    @ViewInject(R.id.ll_share)
    private LinearLayout ll_share;
    @ViewInject(R.id.ll_local_service)
    private LinearLayout ll_local_service;
    @ViewInject(R.id.ll_travel_play)
    private LinearLayout ll_travel_play;
    @ViewInject(R.id.ll_business)
    private LinearLayout ll_business;
    @ViewInject(R.id.ll_find_job)
    private LinearLayout ll_find_job;
    @ViewInject(R.id.ll_fitment)
    private LinearLayout ll_fitment;
    Handler handler=new Handler();
    Handler handler1;
    private List<ImageView> images;
    private int currentItem;
    //记录上一次点的位置
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.banner1,
            R.mipmap.banner2,
            R.mipmap.banner3
    };
    private BannerPagerAdapter mAdapter;
    private List<String> imageUrl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        initData();
//        testApi();
        getImageUrls();
        initBanner(imageUrl);
    }

    /**
     * 获取图片网络url
     */
    private void getImageUrls(){
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493655509971&di=58b6e4b78602475c1be22885e136bff3&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F61%2F00%2F61a58PICtPr_1024.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493655509970&di=44952618a060a834964d5e6125923784&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150407%2F139-15040GZ046-50.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493655641129&di=20ada68f75b601d7556839c68ab48970&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F140501%2F137-140501105911.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493655714965&di=c7c418de8a533752ab0821796e7e2b56&imgtype=0&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201406%2F368542562.jpg");
        imageUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493655761308&di=f042c3c58396cd855c3cb411ee71bc16&imgtype=jpg&src=http%3A%2F%2Fimages.china.cn%2Fattachement%2Fjpg%2Fsite1000%2F20160308%2Fc03fd54abb76184888ac01.jpg");
    }
    private List<String> getTitles(){
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
        return titles;
    }

    private void initBanner(List<String> imagesUrl){
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner.setImages(imagesUrl);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(getTitles());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    private void testApi(){
//        Api.getInstance().login("test", "test", new HttpUtil.URLListenter<UserInfoBean>() {
//            @Override
//            public void onsucess(UserInfoBean userInfoBean) {
//                Log.e("打印访问成功----》",userInfoBean.toString());
//            }
//
//            @Override
//            public void onfaild(String error) {
//                Log.e("访问失败-----》",error);
//            }
//        });
Api.getInstance().testList(new HttpUtil.URLListenter<ArrayList<String>>() {
    @Override
    public void onsucess(ArrayList<String> strings) {
        Log.e("打印解析列表数据成功---》",strings.toString());
    }

    @Override
    public void onfaild(String error) {
        Log.e("yayinyichang ---》",error);
    }
});

    }


    private void initData() {
        iv_arrows_up.setOnClickListener(this);
        iv_arrows_down.setOnClickListener(this);
        iv_personal_center.setOnClickListener(this);
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
//
//        mAdapter = new BannerPagerAdapter(images);
//        mViewPager.setAdapter(mAdapter);

        try {
//            Field field = ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(),
//                    new AccelerateInterpolator());
//            field.set(mViewPager, scroller);
//            scroller.setmDuration(3000);
        } catch (Exception e) {
            Log.e("mmmm", "", e);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_arrows_up:
                rl_tab.setVisibility(View.VISIBLE);
                iv_arrows_up.setVisibility(View.GONE);
                iv_arrows_down.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(),"btn",Toast.LENGTH_SHORT).show();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lv_main.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                break;
            case R.id.iv_arrows_down:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lv_main.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                rl_tab.setVisibility(View.GONE);
                iv_arrows_down.setVisibility(View.GONE);
                iv_arrows_up.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_order:
                break;
            case R.id.btn_nearby:
                break;
            case R.id.btn_rent:
                break;
            case R.id.btn_card:
                break;
            case R.id.btn_pay:
                break;
            case R.id.ll_park:
                break;
            case R.id.ll_car:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_local_service:
                break;
            case R.id.ll_travel_play:
                break;
            case R.id.ll_business:
                break;
            case R.id.ll_find_job:
                break;
            case R.id.ll_fitment:
                break;
            case R.id.iv_personal_center:
                Intent intent=new Intent(act,PersonalActivity.class);
                startActivity(intent);
                act.finish();
                break;

        }

    }
    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    protected void onStart() {
        super.onStart();
//        mViewPager.setCurrentItem(1000*images.size());
//        handler1 = new Handler();
//        handler1.postDelayed(new ViewPageTask(),3000);
        banner.startAutoPlay();
    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
//            int curItem = mViewPager.getCurrentItem();
//            mViewPager.setCurrentItem(curItem+1);
//            mViewPager.setCurrentItem(1000*images.size());
            if (handler1!=null){
                handler1.postDelayed(this,3000);
            }
        }

    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {

//            mViewPager.setCurrentItem(currentItem);
//            mViewPager.setCurrentItem(1000*images.size());
        };
    };
    @Override
    protected void onStop() {
        super.onStop();
//        mHandler=null;
        banner.stopAutoPlay();
    }
}
