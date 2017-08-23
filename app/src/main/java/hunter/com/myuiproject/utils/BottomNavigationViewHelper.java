package hunter.com.myuiproject.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by hcgan on 2017/7/27.
 */

public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView bottomNavigationView){
        BottomNavigationMenuView menuView  = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try
        {
            Field shiftMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftMode.setAccessible(true);
            shiftMode.setBoolean(menuView,false);
            shiftMode.setAccessible(false);

            for (int i = 0;i<menuView.getChildCount();i++){
                BottomNavigationItemView itemView = (BottomNavigationItemView)menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        }catch (NoSuchFieldException | IllegalAccessException e){

        }
    }
}
