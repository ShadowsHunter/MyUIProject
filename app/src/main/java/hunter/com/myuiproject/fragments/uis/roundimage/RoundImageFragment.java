package hunter.com.myuiproject.fragments.uis.roundimage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hunter.com.myuiproject.R;
import hunter.com.myuiproject.fragments.BaseFragment;
import hunter.com.myuiproject.myviews.RoundImage;

/**
 * 圆形图片展示
 * Created by hcgan on 2017/8/9.
 */

public class RoundImageFragment extends BaseFragment {
    private View v ;
    private RoundImage img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_round_image,null);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    protected void initView() {
        img = (RoundImage)v.findViewById(R.id.roundImage);
    }

    @Override
    protected void initData() {
//        img.setBackgroundResource(R.mipmap.range);
    }
}
