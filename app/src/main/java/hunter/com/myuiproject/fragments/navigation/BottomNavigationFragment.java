package hunter.com.myuiproject.fragments.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import hunter.com.myuiproject.R;
import hunter.com.myuiproject.adapters.ViewPagerAdapter;
import hunter.com.myuiproject.fragments.BaseFragment;
import hunter.com.myuiproject.utils.BottomNavigationViewHelper;

/**
 * 底部导航栏Demo
 * Created by hcgan on 2017/7/27.
 */

public class BottomNavigationFragment extends BaseFragment {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bottom_navigation,null);
        super.onCreateView(inflater,container,savedInstanceState);
        return v;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager)v.findViewById(R.id.viewPager_botton_navigation);
        bottomNavigationView = (BottomNavigationView)v.findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_news:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_lib:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_find:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_more:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem!=null){
                    menuItem.setChecked(false);
                }else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initData() {
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(ExampleFragment.newInstance("新闻"));
        adapter.addFragment(ExampleFragment.newInstance("图书"));
        adapter.addFragment(ExampleFragment.newInstance("发现"));
        adapter.addFragment(ExampleFragment.newInstance("更多"));
        viewPager.setAdapter(adapter);
    }


}
