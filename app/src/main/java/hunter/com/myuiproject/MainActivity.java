package hunter.com.myuiproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import hunter.com.myuiproject.fragments.MenuRightFragment;
import hunter.com.myuiproject.fragments.SlideMenuFragment;
import hunter.com.myuiproject.utils.SlideMenuLeftFragmentFactory;

/**
 * 程序主入口Activity
 * Created by hcgan on 2017/7/24.
 */
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener{
    private ImageView menuToggle;
    private TextView tv_title;
    private SlidingMenu menu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMenu();
    }
    private void initView(){
        menuToggle = (ImageView)findViewById(R.id.img_menu_toggle);
        tv_title = (TextView)findViewById(R.id.tv_title);

        menuToggle.setOnClickListener(this);
    }

    private void initMenu(){
        Fragment fragment = new SlideMenuFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame,fragment).commit();
        menu = getSlidingMenu();
//        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setMode(SlidingMenu.LEFT);//设置菜单滑动模式，菜单是出现在左侧还是右侧，还是左右两侧都有
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置滑动的区域
        menu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影的宽度
        menu.setShadowDrawable(R.mipmap.slideshadow);//设置阴影的图片资源
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.mipmap.slideshadow);
        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.right_menu_frame);
        Fragment rightMenuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_menu_toggle:
                menu.toggle();
                break;
            default:
                break;
        }
    }

    public void changeFragment(int pos,int pos1,String title){
        menu.toggle();
        tv_title.setText(title);

        Fragment fragment = SlideMenuLeftFragmentFactory.newInstance().createFragment(pos,pos1);
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout_content, fragment).commit();
        }
    }
}
