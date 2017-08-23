package hunter.com.myuiproject.fragments.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hunter.com.myuiproject.R;
import hunter.com.myuiproject.fragments.BaseFragment;

/**
 * 用于展示的fragment
 * Created by hcgan on 2017/8/2.
 */

public class ExampleFragment extends BaseFragment {
    private View v;
    private TextView tv;

    public static ExampleFragment newInstance(String content){
        Bundle args = new Bundle();
        ExampleFragment fragment = new ExampleFragment();
        args.putString("content", content);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_example,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    protected void initView() {
        tv = (TextView)v.findViewById(R.id.tv_example);
    }

    @Override
    protected void initData() {
        tv.setText(getArguments().getString("content"));
    }
}
