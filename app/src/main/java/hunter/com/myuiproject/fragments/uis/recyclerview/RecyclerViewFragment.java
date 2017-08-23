package hunter.com.myuiproject.fragments.uis.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hunter.com.myuiproject.R;
import hunter.com.myuiproject.fragments.BaseFragment;

/**
 * RecycleView 实现listView效果
 * Created by hcgan on 2017/8/9.
 */

public class RecyclerViewFragment extends BaseFragment {
    private View v;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recyclerview,null);
        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView)v.findViewById(R.id.uis_recyclerView);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置垂直布局，默认也是垂直布局
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置adapter



    }
}
