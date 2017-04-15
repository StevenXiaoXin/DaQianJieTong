package daqianjietong.com.factry;

import daqianjietong.com.BaseFragment;
import daqianjietong.com.HttpFragment;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class FragmentFactry {

    public static BaseFragment createFragment(int position){
        BaseFragment fragment=null;
        switch (position) {
            case 0:
                fragment=new HttpFragment();

        }
        return fragment;
    }
}
