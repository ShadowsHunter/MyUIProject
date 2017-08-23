package hunter.com.myuiproject.utils;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

import hunter.com.myuiproject.fragments.BaseFragment;
import hunter.com.myuiproject.fragments.navigation.BottomNavigationFragment;
import hunter.com.myuiproject.fragments.uis.recyclerview.RecyclerViewFragment;
import hunter.com.myuiproject.fragments.uis.roundimage.RoundImageFragment;
import hunter.com.myuiproject.fragments.uis.soundrecord.SoundRecordFragment;

/**
 * Created by hcgan on 2017/7/24.
 */

public class SlideMenuLeftFragmentFactory {
    private static SlideMenuLeftFragmentFactory mInstance ;
    private static SparseArray<BaseFragment> lFragments;
    private static Map<Integer, SparseArray<BaseFragment>> mFragments;
    private static BaseFragment fragment = null;

    public static SlideMenuLeftFragmentFactory newInstance(){
        if(mInstance == null){
            mInstance = new SlideMenuLeftFragmentFactory();
            mFragments = new HashMap<>();
            lFragments = new SparseArray<>();
        }
        return mInstance;
    }

    public BaseFragment createFragment(int pos, int pos1) {
        lFragments = mFragments.get(pos);
        if (lFragments == null)
        {
            fragment = null;
            lFragments = new SparseArray<>();
        } else
        {
            fragment = lFragments.get(pos1);
        }
        if (fragment == null)
        {
            //TODO:添加Fragment
            if (pos == 0)
            {
                switch (pos1)
                {
                    case 0://圆形图片
                        fragment = new RoundImageFragment();
                        break;
                    case 1://自定义组件
                        break;
                    case 2://RecycleView
                        fragment = new RecyclerViewFragment();
                        break;
                    case 3://SoundRecord
                        fragment = new SoundRecordFragment();
                        break;
                    default:
                        break;
                }
            }

            if (pos == 1)
            {
                switch (pos1)
                {
                    case 0:
                        break;
                    default:
                        break;
                }
            }

            if (pos == 2)
            {
                switch (pos1)
                {
                    case 0:
                        break;
                    default:
                        break;
                }
            }

            if (pos == 3)
            {
                switch (pos1)
                {
                    case 0:
                        fragment = new BottomNavigationFragment();//底部导航栏
                        break;
                    default:
                        break;
                }
            }


            if (fragment != null)
            {
                lFragments.put(pos1, fragment);
                mFragments.put(pos, lFragments);
            }
        }


        return fragment;
    }
}
